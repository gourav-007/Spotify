package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSepec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String accessToken, Playlist requestPlaylist) {
        return given(getRequestSepec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response postAccount(Map<String,String> formParams) {
        return   given()
                .baseUri("https://accounts.spotify.com")
                .formParams(formParams)
                .log().all()
                .contentType(ContentType.URLENC)
                .when()
                .post(Route.API+Route.TOKEN)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

//    public static Response post(String path, String token,Playlist requestPlaylist){
//        return given(getRequestSepec())
//                .body(requestPlaylist)
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .post(path)
//                .then()
//                .spec(getResponseSepec())
//                .extract()
//                .response();
//    }

    public static Response get(String path, String accessToken, String playlistId) {
        return given(getRequestSepec())
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path + playlistId)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, String accessToken, String playlistId, Playlist playlistRequest) {
        return given(getRequestSepec())
                .header("Authorization", "Bearer " + accessToken)
                .body(playlistRequest)
                .when()
                .put(path + playlistId)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}
