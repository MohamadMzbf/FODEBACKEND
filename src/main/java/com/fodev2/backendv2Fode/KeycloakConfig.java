package com.fodev2.backendv2Fode;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "https://raagnale.force-n.sn/";
    public final static String realm = "FODE_Keyclaok";
    final static String clientId = "fk_client";
    final static String clientSecret = "VoQs23136IAFF2WM2T6G0tNJfb9nMnT0";
    final static String userName = "admin-forcen";
    final static String password = "ISID@forceN2023";
    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }

    // une manière standard de résoudre la configuration Keycloak dans une application Spring Boot en utilisant KeycloakSpringBootConfigResolver
//    @Bean
//    public KeycloakConfigResolver keycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
}