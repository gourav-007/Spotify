package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

//    static String accessToken = "BQDRBTzAoCMCbR8vFjWGoCA90Np1_Nw0xN79QiIL36v_W3M1y7M-eIjRTqPe-I9DeN5M54l7njzCHmxMRKZF5MjhJksT-hFXQRP0HLNONBGEXJ9xFPBZ3SyKs_GWQaVjj7lsfkxG5MAJkZQraKJzQPlrl7AbJdX4E-He9AUmWq1R7nES3MScgG8KWaIsSTbAuPvuGXIX8HEYer9XWpp056wtN1YQrIx0tTeFWzQWG52MJRrjioz7TDyRcaTMnkOPMt0YHIcuZg534iE3HLvBLDHj";

    public static RequestSpecification getRequestSepec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(Route.BASE_PATH)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
