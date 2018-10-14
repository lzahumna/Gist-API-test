package com.lzahumna.rest.client;

import com.lzahumna.dto.Fork;
import com.lzahumna.dto.Gist;
import com.lzahumna.rest.client.exception.RestClientException;
import com.lzahumna.rest.client.impl.GistsCRUDRestClientImpl;
import com.lzahumna.rest.client.impl.GistsForkRestClientImpl;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * GistsForkRestClientTest
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class GistsForkRestClientTest extends GistsApiBaseTest {

    private GistsForkRestClient forkRestClient = new GistsForkRestClientImpl();
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
     * Check that it is possible to fork public gist by id
     */
    @Test
    public void forkGistById() throws IOException {
        Gist gist = crudRestClient.getPublicGists().get(0);
        Gist forkGist = forkRestClient.forkGist(gist.getId());

        assertNotEquals("The gist id should be changed", gist.getId(), forkGist.getId());
        assertEquals("Description should remain the same", gist.getDescription(), forkGist.getDescription());
        assertNotEquals("The gist owner should be changed", gist.getOwner(), forkGist.getOwner());
        assertEquals("Files size should remain the same", gist.getFiles().size(), forkGist.getFiles().size());
    }

    /**
     * Check that it is possible to fork user gist by id
     */
    @Test(expected = RestClientException.class)
    public void forkOwnGistById() throws IOException {
        Gist gist = crudRestClient.createGist(readGistFromJson());
        Gist forkedUserGist = forkRestClient.forkGist(gist.getId());

        assertNotEquals("The gist id should be changed", gist.getId(), forkedUserGist.getId());
        assertEquals("Description should remain the same", gist.getDescription(), forkedUserGist.getDescription());
        assertNotEquals("The gist owner should be changed", gist.getOwner(), forkedUserGist.getOwner());
        assertEquals("Files size should remain the same", gist.getFiles().size(), forkedUserGist.getFiles().size());
    }

    /**
     * Check that user can fork the same gist several times but the number of forked gist remains 1
     */
    @Test
    public void getGistForks() {
        Gist gist = crudRestClient.getPublicGists().get(0);
        forkRestClient.forkGist(gist.getId());
        forkRestClient.forkGist(gist.getId());
        forkRestClient.forkGist(gist.getId());

        List<Fork> gistForks = forkRestClient.getGistForks(gist.getId());
        assertEquals("Number of forks should be 1",1, gistForks.size());
    }
}