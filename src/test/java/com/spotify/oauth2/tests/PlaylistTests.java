package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
//
//    RequestSpecification requestSpecification;
//    ResponseSpecification responseSpecification;
//    String accessToken = "BQDRBTzAoCMCbR8vFjWGoCA90Np1_Nw0xN79QiIL36v_W3M1y7M-eIjRTqPe-I9DeN5M54l7njzCHmxMRKZF5MjhJksT-hFXQRP0HLNONBGEXJ9xFPBZ3SyKs_GWQaVjj7lsfkxG5MAJkZQraKJzQPlrl7AbJdX4E-He9AUmWq1R7nES3MScgG8KWaIsSTbAuPvuGXIX8HEYer9XWpp056wtN1YQrIx0tTeFWzQWG52MJRrjioz7TDyRcaTMnkOPMt0YHIcuZg534iE3HLvBLDHj";
//
//    @BeforeClass
//    public void before(){
//        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
//                .setBaseUri("https://api.spotify.com")
//                .setBasePath("/v1")
//                .addHeader("Authorization","Bearer "+accessToken)
//                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL);
//        requestSpecification = requestSpecBuilder.build();
//
//        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
//                .log(LogDetail.ALL);
//
//        responseSpecification = responseSpecBuilder.build();
//    }

    @Test
    public void shouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = new Playlist()
                .setName("Dhamaka")
                .setDescription("Dhamaal Playlist")
                .setPublic(false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

//        Playlist responsePlaylist = given(getRequestSepec())
//                .body(requestPlaylist)
//                .when()
//                .post("/users/313xu6cd2lseg4flvcksvjdjut2y/playlists")
//                .then()
//                .spec(getResponseSepec())
//                .contentType(ContentType.JSON)
//                .assertThat()
//                .statusCode(201)
//                .extract()
//                .as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void shouldBeAbleToGetPlaylist() {
        Playlist requestPlaylist = new Playlist()
                .setName("Dhamaal")
                .setDescription("Updated playlist from Dhamaka to Dhamaal")
                .setPublic(true);

        Response response = PlaylistApi.get("0CdK3DjFuMdxSOdGYFUsXf");
        assertThat(response.statusCode(), equalTo(200));

        Playlist responsePlaylist = response.as(Playlist.class);

//        Playlist responsePlaylist = given(getRequestSepec())
//                .when()
//                .get("/playlists/0CdK3DjFuMdxSOdGYFUsXf")
//                .then()
//                .spec(getResponseSepec())
//                .contentType(ContentType.JSON)
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .as(Playlist.class);
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
        //  .body("name",equalTo("Dhamaal"),"description",equalTo("Updated playlist from Dhamaka to Dhamaal"),"public",equalTo(true));
    }

    @Test
    public void shouldBeAbleToPutPlaylist() {
        Playlist requestPlaylist = new Playlist()
                .setName("Dhamaka")
                .setDescription("Dhamaal Playlist")
                .setPublic(false);

        Response response = PlaylistApi.put("6yyBzPPnwumgoJPxa2jpve", requestPlaylist);
        assertThat(response.statusCode(), equalTo(200));

//        given(getRequestSepec())
//                .body(requestPlaylist)
//                .when()
//                .put("/playlists/6yyBzPPnwumgoJPxa2jpve")
//                .then()
//                .spec(getResponseSepec())
//                .assertThat();
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist()
                .setDescription("Dhamaal Playlist")
                .setPublic(false);


        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(400));

        Error error = response.as(Error.class);

//        Error error = given(getRequestSepec())
//                .body(requestPlaylist)
//                .when()
//                .post("/users/313xu6cd2lseg4flvcksvjdjut2y/playlists")
//                .then()
//                .spec(getResponseSepec())
//                .contentType(ContentType.JSON)
//                .assertThat()
//                .statusCode(400)
//                .extract()
//                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutValidAccessToken() {
        Playlist requestPlaylist = new Playlist()
                .setName("Dhamaka")
                .setDescription("Dhamaal Playlist")
                .setPublic(false);

        Response response = PlaylistApi.post("Abcd123", requestPlaylist);
        assertThat(response.statusCode(), equalTo(401));

        Error error = response.as(Error.class);

//        Error error = given()
//                .baseUri("https://api.spotify.com")
//                .basePath("/v1")
//                .header("Authorization", "Bearer " + "Abcd123")
//                .contentType(ContentType.JSON)
//                .log().all()
//                .body(requestPlaylist)
//                .when()
//                .post("/users/313xu6cd2lseg4flvcksvjdjut2y/playlists")
//                .then()
//                .spec(getResponseSepec())
//                .contentType(ContentType.JSON)
//                .assertThat()
//                .statusCode(401)
//                .extract()
//                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}
