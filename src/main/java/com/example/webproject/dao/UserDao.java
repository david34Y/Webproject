package com.example.webproject.dao;

import com.example.webproject.dto.UserLoginDto;
import com.example.webproject.dto.UsersDto;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@Component
public class UserDao {

    private static final String SIGNUP_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyAwEw0pPth1LsNeqqMnxWz8Y1LfUGLsE9U";
    private static final String SIGNIN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAwEw0pPth1LsNeqqMnxWz8Y1LfUGLsE9U";
    private static final String SIGNIN_GOOGLE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key=AIzaSyAwEw0pPth1LsNeqqMnxWz8Y1LfUGLsE9U";


    public String login(UserLoginDto userLoginDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserLoginDto> httpEntity = new HttpEntity<>(userLoginDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VerifyPasswordResponse> responseEntity = restTemplate.postForEntity(SIGNIN_URL, httpEntity, VerifyPasswordResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            VerifyPasswordResponse responseBody = responseEntity.getBody();
            assert responseBody != null;
            System.out.println(responseBody.getIdToken());
            return responseBody.getLocalId();
        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new RuntimeException("Error en la solicitud: USUARIO INVALIDO");
        } else {
            throw new RuntimeException("Error en la solicitud: " + responseEntity.getStatusCodeValue());
        }
    }

    public String register(UserLoginDto userLoginDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserLoginDto> httpEntity = new HttpEntity<>(userLoginDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VerifyPasswordResponse> responseEntity = restTemplate.postForEntity(SIGNUP_URL, httpEntity, VerifyPasswordResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            VerifyPasswordResponse responseBody = responseEntity.getBody();
            assert responseBody != null;
            System.out.println(responseBody.getIdToken());
            return responseBody.getLocalId();
        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new RuntimeException("Error en la solicitud: USUARIO YA EXISTE");
        } else {
            throw new RuntimeException("Error en la solicitud: " + responseEntity.getStatusCodeValue());
        }
    }

    @Data
    public static class VerifyPasswordResponse implements Serializable {
        private String kind;
        private String localId;
        private String email;
        private String idToken;
        private Boolean registered;
        private String refreshToken;
    }



}
