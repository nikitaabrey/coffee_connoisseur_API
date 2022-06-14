package com.coffeecon.app.Models.HttpResponseModels;

import com.coffeecon.app.Utilities.NullObjectSerializer;
import com.coffeecon.app.Utilities.NullStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class HttpCreated<T> {

    /**
     * CREATED RESPONSE
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

    public HttpCreated() {}

    public static class Builder<T> {
        private ResponseEntity.BodyBuilder response;
        private HttpCreated<T> body;

        public Builder(URI uri) {
            this.response = ResponseEntity.created(uri);
            body = new HttpCreated<T>();
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
            return response.body(this.body);
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
