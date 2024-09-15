package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PlaylistApi {
//    static String accessToken = "BQDRBTzAoCMCbR8vFjWGoCA90Np1_Nw0xN79QiIL36v_W3M1y7M-eIjRTqPe-I9DeN5M54l7njzCHmxMRKZF5MjhJksT-hFXQRP0HLNONBGEXJ9xFPBZ3SyKs_GWQaVjj7lsfkxG5MAJkZQraKJzQPlrl7AbJdX4E-He9AUmWq1R7nES3MScgG8KWaIsSTbAuPvuGXIX8HEYer9XWpp056wtN1YQrIx0tTeFWzQWG52MJRrjioz7TDyRcaTMnkOPMt0YHIcuZg534iE3HLvBLDHj";

    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(Route.USERS+"/"+"313xu6cd2lseg4flvcksvjdjut2y"+Route.PLAYLISTS, TokenManager.getToken(), requestPlaylist);
//       return given(getRequestSepec())
//                .body(requestPlaylist)
//                .header("Authorization","Bearer "+accessToken)
//                .when()
//                .post("/users/313xu6cd2lseg4flvcksvjdjut2y/playlists")
//                .then()
//                .spec(getResponseSepec())
//                .extract()
//                .response();
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(Route.USERS+"/"+"313xu6cd2lseg4flvcksvjdjut2y"+Route.PLAYLISTS, token, requestPlaylist);
//        return given(getRequestSepec())
//                .body(requestPlaylist)
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .post("/users/313xu6cd2lseg4flvcksvjdjut2y/playlists")
//                .then()
//                .spec(getResponseSepec())
//                .extract()
//                .response();
    }

    public static Response get(String playlistId) {
        return RestResource.get(Route.PLAYLISTS+"/", TokenManager.getToken(), playlistId);
//       return given(getRequestSepec())
//               .header("Authorization","Bearer "+accessToken)
//               .when()
//                .get("/playlists/"+playlistId)
//                .then()
//                .spec(getResponseSepec())
//                .extract()
//                .response();
    }

    public static Response put(String playlistId, Playlist playlistRequest) {

        return RestResource.put(Route.PLAYLISTS+"/", TokenManager.getToken(), playlistId, playlistRequest);

//         return given(getRequestSepec())
//                 .header("Authorization","Bearer "+accessToken)
//                 .body(playlistRequest)
//                  .when()
//                  .put("/playlists/"+playlistId)
//                  .then()
//                  .spec(getResponseSepec())
//                  .extract()
//                 .response();
    }
}
