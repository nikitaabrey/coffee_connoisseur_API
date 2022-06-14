package com.coffeecon.app.Models.HttpResponseModels;

import com.coffeecon.app.Utilities.NullListSerializer;
import com.coffeecon.app.Utilities.NullStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


public class HttpFailure {


    /**
     * FORMAT OF ERROR RESPONSE
     * {
     *      success: false
     *      message: ""
     *      errors: [{}]
     * }
     */


    private boolean success;

    @JsonSerialize(nullsUsing = NullStringSerializer.class)
    private String message;

    @JsonSerialize(nullsUsing = NullListSerializer.class)
    private List<Error> errors;


    public HttpFailure() {


    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public static class Builder {

        private ResponseEntity.BodyBuilder response;
        private HttpFailure obj ;

        public Builder(HttpStatus status) {
            response = ResponseEntity.status(status);
            obj = new HttpFailure();
        }

        public Builder withMessage(String message) {
                obj.setMessage(message);
                return this;
        }

        public Builder withError(Error error) {
            obj.addSubError(error);
            return this;
        }

        public Builder withErrors(List<Error> errors) {
            obj.addSubErrors(errors);
            return this;
        }



        public Builder withHeader(String headerName, String... headerValues) {
            response.header(headerName,headerValues);
            return this;
        }

        public Builder withHeaders(HttpHeaders headers) {
            response.headers(headers);
            return this;
        }

        public ResponseEntity<Object> build() {
            return response.body(obj);
        }

    }


    private void addSubErrors(List<Error> error) {
        if(errors == null) {
            this.errors = new ArrayList<Error>();
        }
        errors.addAll(error);
    }

    private void addSubError(Error error) {
        if(errors == null) {
            this.errors = new ArrayList<Error>();
        }
        errors.add(error);
    }


//    private void addError(String field,String message) {
//        addSubError(new ValidationError(field,message));
//    }
//
//    private void addFieldError(FieldError err) {
//        this.addError(err.getField(), err.getDefaultMessage());
//    }
//
//
//
//    public void addFieldErrors(List<FieldError> errs) {
//        errs.forEach(err -> {
//            addFieldError(err);
//        });
//    }


//    private void addValidationErr(ObjectError err) {
//        this.addValidationErr(err.getObjectName(),err.getDefaultMessage());
//    }
//
//
//    public void addObjectValidationErrs(List<ObjectError> globErrs) {
//        globErrs.forEach(err -> {
//            addValidationErr(err);
//        });
//    }


//    private void addConstraintViolationErr(ConstraintViolation<?> cv) {
//        this.addError(
//            cv.getRootBeanClass().getSimpleName(),cv.getMessage()
//        );
//    }
//
//    public void addConstraintViolationErrors(Set<ConstraintViolation<?>> constraintViolations) {
//        constraintViolations.forEach(err -> addConstraintViolationErr(err));
//    }



//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public List<Error> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<Error> errors) {
//        this.errors = errors;
//    }


}
