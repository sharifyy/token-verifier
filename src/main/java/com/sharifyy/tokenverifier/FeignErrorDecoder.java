package com.sharifyy.tokenverifier;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST,response.reason());
            case 401:
                return new ResponseStatusException(HttpStatus.UNAUTHORIZED,response.reason());
            case 403:
                return new ResponseStatusException(HttpStatus.FORBIDDEN,response.reason());
            case 404:
                return new ResponseStatusException(HttpStatus.NOT_FOUND,response.reason());
            default:
                return new Exception(response.reason());
        }
    }
}
