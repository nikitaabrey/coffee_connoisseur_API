package com.coffeecon.app.Response;

import com.coffeecon.app.Utilities.NullObjectSerializer;
import com.coffeecon.app.Utilities.NullStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpSuccess<T> {


    /**
     * SUCCESS RESPONSE
     * {
     *      success: true
     *      message: ""
     *      data: {}
     * }
     */




    private final  boolean  success = true;

    @JsonSerialize(nullsUsing = NullStringSerializer.class)
    private String message ;

    @JsonSerialize(nullsUsing = NullObjectSerializer.class)
    private T data;


    public HttpSuccess() {}



    public static class Builder<T> {
        private ResponseEntity.BodyBuilder response;
        private HttpSuccess<T> body;

        public Builder(HttpStatus status) {
            this.response = ResponseEntity.status(status);
            body = new HttpSuccess<T>();
        }

        public Builder<T> withBody(T body) {
                this.body.setBody(body);
                return this;
        }


        public  Builder<T> withMessage(String message) {
            this.body.setMessage(message);
            return this;
        }


        public Builder<T> withHeader(String headerName, String... headerValues) {
            response.header(headerName,headerValues);
                return this;
        }

        public Builder<T> withHeaders(HttpHeaders headers) {
            response.headers(headers);
            return this;
        }

        public ResponseEntity<Object> build() {
            return response.body(body);
        }

    }


    private void setBody(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
      return  message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public T getData() {
        return data;
    }



}
