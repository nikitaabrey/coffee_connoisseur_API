package com.coffeecon.app.ExceptionHandlers;

import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.services.xray.model.Http;
import com.coffeecon.app.Models.HttpResponseModels.Error;
import com.coffeecon.app.Models.HttpResponseModels.HttpFailure;
import com.coffeecon.app.Models.HttpResponseModels.ValidationError;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.MimeType;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;





@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Internal server error")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Internal server error")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Internal server error")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return new HttpFailure.Builder(HttpStatus.REQUEST_TIMEOUT)
                .withMessage("Request timed out")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Request method not supported")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Internal server error")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Media type not supported")
                .build();
    }


    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Missing path variable")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Internal server error")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            List<Error> errors = ex.getBindingResult()
                    .getFieldErrors()
                    .stream().map(fieldError ->  new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());

        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withErrors(errors)
                .withMessage("One or more fields are invalid")
                .build();

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String message = new StringBuilder()
                .append(ex.getContentType())
                .append("is not supported. Supported media types are ")
                .append(ex.getSupportedMediaTypes().stream().map(MimeType::toString).collect(Collectors.joining(",")))
                .toString();

        return new HttpFailure.Builder(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .withMessage(message).build();
    }

// ------------ cognito exceptions ---------------/

    /**
     * Invalid username nd password, user not found
     * @param ex
     * @return
     */
    @ExceptionHandler(NotAuthorizedException.class)
    protected ResponseEntity<Object> handleNotAuthorised(NotAuthorizedException ex) {
        return new HttpFailure.Builder( HttpStatus.UNAUTHORIZED)
                .withMessage(ex.getErrorMessage())
                .build();
    }

    /**
     * Resource not found
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected  ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
            return new HttpFailure.Builder(HttpStatus.NOT_FOUND)
                    .withMessage("Server could not find the requested resource")
                    .build();
    }


    /**
     * Too many requests
     * @param ex
     * @return
     */
    @ExceptionHandler(TooManyRequestsException.class)
    protected  ResponseEntity<Object> handleTooManyRequests(TooManyRequestsException ex) {
        return new HttpFailure.Builder(HttpStatus.TOO_MANY_REQUESTS)
                .withMessage("Too many requests, please try again after a few minutes")
                .withHeader("Retry-After","3600")
                .build();
    }




    /**
     * User not confirmed
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotConfirmedException.class)
    protected  ResponseEntity<Object> handleUserNotConfirmed(UserNotConfirmedException ex) {
//        HttpFailure error = new HttpFailure("User has not been confirmed");
//        return buildResponseEntity(error, HttpStatus.UNAUTHORIZED);
        return new HttpFailure.Builder(HttpStatus.UNAUTHORIZED)
                .withMessage("User not confirmed, please confirm your account")
                .build();
    }




    /**
     * User required to reset password
     * @param ex
     * @return
     */
    @ExceptionHandler(PasswordResetRequiredException.class)
    protected  ResponseEntity<Object> handleUserPassword(PasswordResetRequiredException ex) {
        return new HttpFailure.Builder(HttpStatus.UNAUTHORIZED)
                .withMessage(ex.getErrorMessage())
                .build();
    }



    /**
     * User required to reset password
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidParameterException.class)
    protected  ResponseEntity<Object> handleInvalidParam(InvalidParameterException ex) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getErrorMessage())
                .build();
    }



    /**
     * User not found
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotFoundException .class)
    protected  ResponseEntity<Object> handleUserNotFound(UserNotFoundException  ex) {
        return new HttpFailure.Builder(HttpStatus.UNAUTHORIZED)
                .withMessage(ex.getErrorMessage())
                .build();
    }


    @ExceptionHandler(CodeDeliveryFailureException.class)
    protected  ResponseEntity<Object> handleCodeDeliveryFailure(CodeDeliveryFailureException  ex) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Failed to send confirmation code")
                .build();
    }


    /**
     * invalid hwt token
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(RuntimeException ex, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.UNAUTHORIZED)
                .withMessage("Unauthorised")
                .build();
    }

    /**
     * handle any unexpected error with cognito
     * @param ex
     * @return
     */
    @ExceptionHandler({UnexpectedLambdaException .class,InvalidUserPoolConfigurationException.class,UserLambdaValidationException.class,
            InvalidLambdaResponseException.class,InternalErrorException.class,InvalidSmsRoleAccessPolicyException.class,
            InvalidSmsRoleTrustRelationshipException.class
    })
    protected  ResponseEntity<Object> handleUnexpectedException(AWSCognitoIdentityProviderException ex) {
        return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage("Could not fulfill request due to an expected server error")
                .build();
    }


    /**
     * user already exists
     * @param ex
     * @return
     */
    @ExceptionHandler(UsernameExistsException.class)
    protected  ResponseEntity<Object> handleUsernameExists(UsernameExistsException ex) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getErrorMessage())
                .build();
    }





    /**
     * too many failed attempts
     * @param ex
     * @return
     */
    @ExceptionHandler(TooManyFailedAttemptsException.class)
    protected  ResponseEntity<Object> handleTooManyFailedAttempts(TooManyFailedAttemptsException ex) {
        return new HttpFailure.Builder(HttpStatus.FORBIDDEN)
                .withMessage("Too many failed attempts")
                .build();
    }



    /**
     * too many failed attempts
     * @param ex
     * @return
     */
    @ExceptionHandler(ExpiredCodeException.class)
    protected  ResponseEntity<Object> handleExpiredCode(ExpiredCodeException ex) {
        return new HttpFailure.Builder(HttpStatus.GONE)
                .withMessage("Confirmation code expired")
                .build();
    }




    /**
     * too many failed attempts
     * @param ex
     * @return
     */
    @ExceptionHandler(AliasExistsException.class)
    protected  ResponseEntity<Object> handleEmailALreadyExistsCode(AliasExistsException ex) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Email already exists")
                .build();
    }


    /**
     * invalid code
     * @param ex
     * @return
     */
    @ExceptionHandler(CodeMismatchException.class)
    protected  ResponseEntity<Object> handleInvalidCode(CodeMismatchException ex) {
        return new HttpFailure.Builder(HttpStatus.UNAUTHORIZED)
                .withMessage("Invalid confirmation code, please enter the correct otp")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Malformed JSON request")
                .build();
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            String message = "Error writing JSON output";
            return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .withMessage(message)
                    .build();
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()))
                .build();
    }



    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage(String.format("The parameter '%s' could not be converted to type '%s'", ex.getName(), ex.getRequiredType().getSimpleName()))
                .build();
    }





    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
       return new HttpFailure.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
               .withMessage("Internal server error")
               .build();
    }

    /**
     * missing servlet request
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getParameterName() + " parameter is missing";
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage(message)
                .build();
    }



    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        return new HttpFailure.Builder(HttpStatus.BAD_REQUEST)
                .withMessage("Validation error")
                .withErrors(ex.getConstraintViolations().stream().map(errors -> {
                return new ValidationError(errors.getRootBeanClass().getSimpleName(),errors.getMessage());
                }).collect(Collectors.toList()))
                .build();
    }
}
