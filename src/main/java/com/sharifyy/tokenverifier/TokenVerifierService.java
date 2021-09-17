package com.sharifyy.tokenverifier;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TokenVerifierService {

    @Value("${keycloak.realm.target}")
    private String targetRealm;

    private static final String PERMISSION_KEY = "permission";
    private static final String AUDIENCE_KEY = "audience";
    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String GRANT_TYPE_VALUE = "urn:ietf:params:oauth:grant-type:uma-ticket";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String SCOPE_DELIMITER = "#";


    private final Keycloak keycloak;
    private final KeycloakFeignClient feignClient;

    public ResponseEntity<PermissionEvaluationResponse> verifyPermission(String clientId, String resourceUrl, String token) {
        return getResourceId(clientId, resourceUrl)
                .map(resourceId -> {
                    Map<String, ?> params = Map.of(AUDIENCE_KEY, clientId,
                            PERMISSION_KEY, resourceId,
                            GRANT_TYPE_KEY, GRANT_TYPE_VALUE);
                    return feignClient.verifyPermission(TOKEN_PREFIX + token, targetRealm, params);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no resource found for url"));

    }

    public ResponseEntity<PermissionEvaluationResponse> verifyPermission(String clientId, String resourceUrl, String token, String scope) {
        return getResourceId(clientId, resourceUrl)
                .map(resourceId -> {
                    Map<String, ?> params = Map.of(AUDIENCE_KEY, clientId,
                            PERMISSION_KEY, resourceId + SCOPE_DELIMITER + scope,
                            GRANT_TYPE_KEY, GRANT_TYPE_VALUE);
                    return feignClient.verifyPermission(TOKEN_PREFIX + token, targetRealm, params);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no resource found for url"));
    }

    private Optional<String> getResourceId(String clientId, String resourceUrl) {
        return keycloak.realm(targetRealm).clients()
                .get(getClientHexId(clientId))
                .authorization()
                .resources().resources().stream()
                .filter(resourceRepresentation -> resourceRepresentation.getUris().contains(resourceUrl))
                .findFirst()
                .map(ResourceRepresentation::getId);
    }

    private String getClientHexId(String clientId) {
        return Optional.ofNullable(keycloak.realm(targetRealm).clients().findByClientId(clientId).get(0))
                .map(ClientRepresentation::getId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"client not found"));
    }
}
