package com.fodev2.backendv2Fode.Security;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

//cette classe est utilisée pour configurer des aspects liés
// à la conversion des jetons JWT dans une application sécurisée par Keycloak
@Configuration
@ConfigurationProperties(prefix = "token.converter")
public class TokenConverterProperties {

    private String resourceId;
    private String principalAttribute;

    public String getResourceId() {
        return resourceId;
    }

    public Optional<String> getPrincipalAttribute() {
        return Optional.ofNullable(principalAttribute);
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setPrincipalAttribute(String principalAttribute) {
        this.principalAttribute = principalAttribute;
    }

}
