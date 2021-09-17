package com.sharifyy.tokenverifier;

import feign.Headers;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "evaluator",url = "http://localhost:8080/auth",configuration = KeycloakFeignClient.Configuration.class)
public interface KeycloakFeignClient {


    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token",consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<PermissionEvaluationResponse> verifyPermission(@RequestHeader(name = "Authorization") String token,
                                    @PathVariable String realm,
                                    Map<String, ?> formParams);

    class Configuration {

        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }

        @Bean
        FeignErrorDecoder errorDecoder(){
            return new FeignErrorDecoder();
        }

        @Bean
        Logger.Level feignLogger(){
            return Logger.Level.FULL;
        }
    }
}
