package com.sharifyy.tokenverifier;

import feign.Logger;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
@EnableFeignClients
public class KeycloakAdminConfig {

    @Value("${client.admin-cli.secret}")
    private String clientSecret;
    @Value("${keycloak.server.url}")
    private String serverUrl;
    @Value("${keycloak.realm.master}")
    private String masterRealm;
    @Value("${keycloak.admin-cli.name}")
    private String adminClient;

    @Bean
    public Keycloak admin(){
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(serverUrl)
                .realm(masterRealm)
                .clientId(adminClient)
                .clientSecret(clientSecret)
                .build();
    }


}
