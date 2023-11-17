package com.fodev2.backendv2Fode.Security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class KeycloakJwtTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {


    private static final String RESOURCE_ACCESS = "realm_access";
    private static final String ROLES = "roles";
    private static final String ROLE_PREFIX = "ROLE_";
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConverterProperties properties;

    public KeycloakJwtTokenConverter(
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
            TokenConverterProperties properties) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = properties;
    }

    @Override
    public JwtAuthenticationToken convert(@NonNull Jwt jwt) {

        // On extrait les roles associé à l'utilisateur
        final var realmAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("realm_access", Map.of());
        final var realmRoles = (Collection<String>) realmAccess.getOrDefault("roles", List.of());

        // On véérifie s'il y'a pas d'autre dans l'objet ressources_access
        final var resourceAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("resource_access", Map.of());
        final var confidentialUserAccess = (Map<String, Object>) resourceAccess.getOrDefault("account", Map.of());
        final var confidentialUserRoles = (Collection<String>) confidentialUserAccess.getOrDefault("roles", List.of());

        // On regroupe l'ensemble des roles dans un tableau SimpleGrantedAuthority
        Stream<SimpleGrantedAuthority> accesses = Stream.concat(realmRoles.stream(), confidentialUserRoles.stream())
                .map(SimpleGrantedAuthority::new);

        String principalClaimName = properties.getPrincipalAttribute()
                .map(jwt::getClaimAsString)
                .orElse(jwt.getClaimAsString(JwtClaimNames.SUB));

        System.out.println(realmRoles);
        // On retourne les informations extraites
        return new JwtAuthenticationToken(jwt,  accesses.collect(Collectors.toSet()), principalClaimName);
    }
}
