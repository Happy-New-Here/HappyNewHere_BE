package com.example.HappyNewHere.service;

import com.example.HappyNewHere.dto.TokenInfo;
import com.example.HappyNewHere.dto.UserInfo;
import com.example.HappyNewHere.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final AccountRepository accountRepository;

    @Value("${kakao.clientId}")
    String clientId;

    @Value("${kakao.redirectUrl}")
    String redirectUrl;

    public void kakaoLogin(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("response_type", "code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUrl);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakao.clientId}&redirect_uri=${kakao.redirectUrl}",
                HttpMethod.GET,
                null,
                String.class
        );
    }


    public String kakaoToken(String code) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", String.valueOf(APPLICATION_FORM_URLENCODED));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUrl);
        params.add("code", code);


        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        TokenInfo tokenInfo = new TokenInfo();
        try {
            tokenInfo = objectMapper.readValue(accessTokenResponse.getBody(), TokenInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return tokenInfo.getAccess_token();

    }

    public UserInfo kakaoUser(String token) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> kakaoProfileResponse = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );


        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = new UserInfo();
        try {
            userInfo = objectMapper.readValue(kakaoProfileResponse.getBody(), UserInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userInfo;
    }



}
