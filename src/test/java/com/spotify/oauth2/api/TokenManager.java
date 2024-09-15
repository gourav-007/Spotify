package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try {
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing Token...");
                Response response = refreshToken();
                 access_token = response.path("access_token");
                int expiryDurationInSec = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSec - 300);
            }else{
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Token!!");
        }
        return access_token;
    }

    private static Response refreshToken() {

        Map<String, String> formEncoded = new HashMap<>();
        formEncoded.put("client_id", "bca30d5a41ec400d956b84c5b27edb67");
        formEncoded.put("grant_type", "refresh_token");
        formEncoded.put("client_secret", "6e34a99131534a05a4992ea0571bfae8");
        formEncoded.put("refresh_token", "AQDqKNYdMnpWT1J1TYuRi7vLOqxfnYg2FI3mToSz0ySY3yS1qpDlp37q0RjHCeDnakuuzBBdSoGlBsFlZutVx8VsyliykRu2_1QGH7ytXafoSY1cWhbv9G-MQ5wXPcwhFog");

        Response response = RestResource.postAccount(formEncoded);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew Token Failed !!");
        }

        return response;

    }
}
