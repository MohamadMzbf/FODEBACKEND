package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.Credentials;
import com.fodev2.backendv2Fode.KeycloakConfig;
import com.fodev2.backendv2Fode.dto.UpdateKeycloakRequest;
import com.fodev2.backendv2Fode.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class KeyCloakService {
    public void addUser(UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        UsersResource instance = getInstance();
        instance.create(user);
    }

    public List<UserRepresentation> getUser(String userName){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;
    }

//    public void updateUser(String userId, UserDTO userDTO){
    public void updateUser(String userId, UpdateKeycloakRequest updateKeycloakRequest){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(updateKeycloakRequest.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(updateKeycloakRequest.getUserName());
        user.setFirstName(updateKeycloakRequest.getFirstname());
        user.setLastName(updateKeycloakRequest.getLastname());
        user.setEmail(updateKeycloakRequest.getEmailId());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }

    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public String authenticateUser(String username, String password)
    {
        return null;
    }

    public String federateIdentity(String username, String password)
    {
        return null;
    }


    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
    }


}
