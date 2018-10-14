package com.lzahumna.rest.client;

import com.lzahumna.dto.Gist;
import com.lzahumna.rest.client.exception.RestClientException;
import com.lzahumna.rest.client.impl.GistsCRUDRestClientImpl;
import com.lzahumna.rest.client.impl.GistsStarRestClientImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * GistsStarRestClientTest
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class GistsStarRestClientTest extends GistsApiBaseTest {

    private GistsStarRestClient starRestClient = new GistsStarRestClientImpl();
    private GistsCRUDRestClient crudRestClient = new GistsCRUDRestClientImpl();

    /**
     * Delete all user gists after each test
     */
    @After
    public void tearDown() throws Exception {
        List<Gist> userGists = crudRestClient.getUserGists();

        if (userGists != null) {
            for (Gist userGist : userGists) {
                crudRestClient.deleteGist(userGist.getId());
            }

        }
    }

    /**
     * Check that it is possible to star existing gist by id
     */
    @Test
    public void starGistById() throws IOException {
        Gist gist = crudRestClient.createGist(readGistFromJson());

        Assert.assertFalse("Created gist should be unstarred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.starGist(gist.getId());

        Assert.assertTrue("After starring gist should be starred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.unstarGist(gist.getId());

        Assert.assertFalse("After unstarring gist should be unstarred", starRestClient.isGistStarred(gist.getId()));

    }

    /**
     * Check that after double starring gist remains starred
     */
    @Test
    public void doubleStaredGistById() throws IOException {
        Gist gist = crudRestClient.createGist(readGistFromJson());

        Assert.assertFalse("Created gist should be unstarred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.starGist(gist.getId());

        Assert.assertTrue("After the 1st time starring gist should be starred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.starGist(gist.getId());

        Assert.assertTrue("After the 2nd time starring gist should be also starred", starRestClient.isGistStarred(gist.getId()));
    }

    /**
     * Check that created gist after double unstarring remains unstarred
     */
    @Test
    public void doubleUnstarredGist() throws IOException {
        Gist gist = crudRestClient.createGist(readGistFromJson());

        Assert.assertFalse("Created gist should be unstarred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.unstarGist(gist.getId());

        Assert.assertFalse("After the 1st time unstarring gist should be unstarred", starRestClient.isGistStarred(gist.getId()));

        starRestClient.unstarGist(gist.getId());

        Assert.assertFalse("After the 2nd time unstarring gist should be also unstarred", starRestClient.isGistStarred(gist.getId()));
    }

    /**
     * Check that it is not possible to star gist with not existing id
     */
    @Test(expected = RestClientException.class)
    public void starWithInvalidId() {
        starRestClient.starGist("abc");
    }

    /**
     * Check that it is not possible to unstar gist with not existing id
     */
    @Test(expected = RestClientException.class)
    public void unstarWithInvalidId() {
        starRestClient.unstarGist("abc");
    }

    /**
     * Check that it is not possible to check if gist is starred with not existing id
     */
    @Test
    public void isStarredWithInvalidId() {
        Assert.assertFalse(starRestClient.isGistStarred("abc"));
    }

}