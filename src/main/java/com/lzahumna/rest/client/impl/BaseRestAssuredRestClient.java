package com.lzahumna.rest.client.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzahumna.property.loader.PropertyLoader;
import com.lzahumna.rest.client.GistsCRUDRestClient;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static io.restassured.config.HeaderConfig.headerConfig;

/**
 * Contains common methods for all of Gists REST API clients
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
abstract class BaseRestAssuredRestClient {

    private static final String GITHUB_TOKEN = "github.token";
    private static final String GISTS_API_BASE_URI = "gists.api.base.uri";

    static {
        RestAssured.baseURI = PropertyLoader.getPropertyByName(GISTS_API_BASE_URI);
    }

    /**
     * Pre-configure RestAssured with content type and authorization header
     */
    RequestSpecification restAssured() {
        String base64Token = PropertyLoader.getPropertyByName(GITHUB_TOKEN);
        return RestAssured.given()
                .header("Authorization", "Bearer " + new String(Base64.getDecoder().decode(base64Token)))
                .contentType("application/json");
    }
}
