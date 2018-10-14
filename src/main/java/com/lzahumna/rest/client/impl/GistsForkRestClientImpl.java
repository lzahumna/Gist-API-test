package com.lzahumna.rest.client.impl;

import com.lzahumna.dto.Fork;
import com.lzahumna.dto.Gist;
import com.lzahumna.rest.client.GistsForkRestClient;
import com.lzahumna.rest.client.exception.RestClientException;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * GistsForkRestClientImpl
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class GistsForkRestClientImpl extends BaseRestAssuredRestClient implements GistsForkRestClient {

    @Override
    public Gist forkGist(String gistId) {

        try {
            return restAssured()
                    .expect()
                    .statusCode(HttpStatus.SC_CREATED)

                    .when()
                    .post("/gists/{gistId}/forks", gistId)

                    .as(Gist.class, ObjectMapperType.GSON);
        } catch (Throwable e) {
            throw new RestClientException("cannot fork gist with " + gistId, e);
        }
    }

    @Override
    public List<Fork> getGistForks(String gistId) {

        try {
            Fork[] forks = restAssured()
                    .expect()
                    .statusCode(HttpStatus.SC_OK)

                    .when()
                    .get("/gists/{gistId}/forks", gistId)

                    .as(Fork[].class, ObjectMapperType.GSON);

            return Arrays.asList(forks);
        } catch (Throwable e) {
            throw new RestClientException("cannot get forks for gist with " + gistId, e);
        }
    }
}
