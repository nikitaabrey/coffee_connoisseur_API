package com.coffeecon.app.ExceptionHandlers;

import com.amazonaws.services.cognitoidp.model.*;
import com.coffeecon.app.Response.Error;
import com.coffeecon.app.Response.HttpFailure;
import com.coffeecon.app.Response.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // error for all exceptioms
    //

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




}
