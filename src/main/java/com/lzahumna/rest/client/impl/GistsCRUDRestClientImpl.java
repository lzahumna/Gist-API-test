package com.lzahumna.rest.client.impl;

import com.lzahumna.dto.Gist;
import com.lzahumna.rest.client.GistsCRUDRestClient;
import com.lzahumna.rest.client.exception.RestClientException;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * GistsCRUDRestClientImpl
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class GistsCRUDRestClientImpl extends BaseRestAssuredRestClient implements GistsCRUDRestClient {

    @Override
    public List<Gist> getUserGists() {
        try {
            Gist[] gistsArray = restAssured()
                    .expect()
                    .statusCode(HttpStatus.SC_OK)
                    .when()
                    .get("/gists")
                    .as(Gist[].class, ObjectMapperType.GSON);

            return Arrays.asList(gistsArray);
        } catch (Throwable e) {
            throw new RestClientException("cannot get user gists", e);
        }
    }

    @Override
    public List<Gist> getPublicGists() {
        try {
            Gist[] gistsArray = RestAssured.given()
                    .contentType("application/json")

                    .expect()
                    .statusCode(HttpStatus.SC_OK)

                    .when()
                    .get("/gists/public")

                    .as(Gist[].class, ObjectMapperType.GSON);


            return Arrays.asList(gistsArray);
        } catch (Throwable e) {
            throw new RestClientException("cannot get public gists", e);
        }
    }

    @Override
    public Gist getGistById(String gistId) {
        try {
            Gist gist = restAssured()
                    .get("/gists/{gistId}", gistId).andReturn()
                    .as(Gist.class, ObjectMapperType.GSON);
            return gist.equals(new Gist()) ? null : gist;
        } catch (Throwable e) {
            throw new RestClientException("cannot get gist with " + gistId, e);
        }
    }

    @Override
    public Gist createGist(Gist newGist) {

        try {
            return restAssured()
                    .body(newGist, ObjectMapperType.GSON)

                    .expect()
                    .statusCode(HttpStatus.SC_CREATED)

                    .when()
                    .post("/gists")

                    .as(Gist.class, ObjectMapperType.GSON);
        } catch (Throwable e) {
            throw new RestClientException("cannot create gist", e);
        }
    }

    @Override
    public Gist editGist(String gistId, Gist updatedGist) {
        try {
            return restAssured()
                    .body(updatedGist, ObjectMapperType.GSON)

                    .expect()
                    .statusCode(HttpStatus.SC_OK)

                    .when()
                    .patch("/gists/{gistId}", gistId)

                    .as(Gist.class, ObjectMapperType.GSON);
        } catch (Throwable e) {
            throw new RestClientException("cannot edit gist with " + gistId, e);
        }
    }

    @Override
    public void deleteGist(String gistId) {
        try {
            restAssured()
                    .delete("/gists/{gistId}", gistId)
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        } catch (Throwable e) {
            throw new RestClientException("cannot delete gist with " + gistId, e);
        }
    }

}
