package com.lzahumna.rest.client.impl;

import com.lzahumna.rest.client.GistsStarRestClient;
import com.lzahumna.rest.client.exception.RestClientException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

/**
 * GistsStarRestClientImpl
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class GistsStarRestClientImpl extends BaseRestAssuredRestClient implements GistsStarRestClient {

    @Override
    public void starGist(String gistId) {
        try {
            restAssured()
                    .expect()
                    .statusCode(HttpStatus.SC_NO_CONTENT)
                    .when()
                    .put("/gists/{gistId}/star", gistId);
        } catch (Throwable e) {
            throw new RestClientException("cannot star gist with " + gistId, e);
        }
    }

    @Override
    public void unstarGist(String gistId) {
        try {
            restAssured()
                    .expect()
                    .statusCode(HttpStatus.SC_NO_CONTENT)
                    .when()
                    .delete("/gists/{gistId}/star", gistId);
        } catch (Throwable e) {
            throw new RestClientException("cannot unstar gist with " + gistId, e);
        }

    }

    @Override
    public boolean isGistStarred(String gistId) {
        try {
            Response response = restAssured()
                    .expect()
                    .statusCode(Matchers.anyOf(Matchers.is(HttpStatus.SC_NO_CONTENT), Matchers.is(HttpStatus.SC_NOT_FOUND)))
                    .when()
                    .get("/gists/{gistId}/star", gistId)
                    .andReturn();

            return response.statusCode() == HttpStatus.SC_NO_CONTENT;
        } catch (Throwable e) {
            throw new RestClientException("cannot check if gist is starred with " + gistId, e);
        }
    }
}