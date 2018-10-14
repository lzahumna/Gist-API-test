package com.lzahumna.rest.client;

import com.lzahumna.dto.Gist;
import com.lzahumna.dto.GistFile;
import com.lzahumna.rest.client.exception.RestClientException;
import com.lzahumna.rest.client.impl.GistsCRUDRestClientImpl;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * GistsCRUDRestClientTest
 */
public class GistsCRUDRestClientTest extends GistsApiBaseTest {

    private GistsCRUDRestClient crudRestClient = new GistsCRUDRestClientImpl();

    /**
     * Delete all existing user gists after each test
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
     * Check that all public gists are retrieved
     */
    @Test
    public void getPublicGists() {
        List<Gist> publicGists = crudRestClient.getPublicGists();

        assertTrue("All public gists (at least one) should be retrieved", publicGists.size() > 0);
    }

    /**
     * Check that it is possible to create new gist
     */
    @Test
    public void createNewGist() throws IOException {
        Gist gistBeforeSave = readGistFromJson();
        Gist gistAfterSave = crudRestClient.createGist(gistBeforeSave);

        assertNotNull(gistAfterSave.getId());
        assertNotNull(gistAfterSave.getForksUrl());
        assertNotNull(gistAfterSave.getCommitsUrl());
        assertEquals("Description should remain the same",
                gistBeforeSave.getDescription(), gistAfterSave.getDescription());
        assertEquals("Number of files should be the same",
                gistBeforeSave.getFiles().size(), gistAfterSave.getFiles().size());

        for (Map.Entry<String, GistFile> beforeSave : gistBeforeSave.getFiles().entrySet()) {
            // check if file from gist which we had to create contains in file list after saving
            String fileNameBeforeSave = beforeSave.getKey();

            assertTrue("File list in gist after saving should contain each file, which was sent",
                    gistAfterSave.getFiles().keySet().contains(fileNameBeforeSave));

            GistFile fileAfterSave = gistAfterSave.getFiles().get(fileNameBeforeSave);

            assertEquals("Content of files before and after saving must be the same",
                    beforeSave.getValue().getContent(), fileAfterSave.getContent());

            assertTrue("File size must be bigger than 0", fileAfterSave.getSize() > 0);

            assertEquals("File names should remain the same", fileNameBeforeSave, fileAfterSave.getFilename());
        }
    }

    /**
     * Check that it is possible to edit existing gist by id
     */
    @Test
    public void editExistingGist() throws IOException {
        createNewGist();
        Gist gistBeforeEdit = crudRestClient.getUserGists().get(0);

        Gist newGist = new Gist();

        newGist.setFiles(new HashMap<>());

        for (int i = 0; i < 5; i++) {
            GistFile file = new GistFile();
            file.setContent("abc");
            file.setFilename("file" + i);
            newGist.getFiles().put(file.getFilename(), file);
        }

        newGist.setDescription("New");

        crudRestClient.editGist(gistBeforeEdit.getId(), newGist);

        Gist gistAfterEdit = crudRestClient.getGistById(gistBeforeEdit.getId());

        assertEquals("Description should be updated", "New", gistAfterEdit.getDescription());
        assertEquals("Size of files should be increased", gistBeforeEdit.getFiles().size() + newGist.getFiles().size(), gistAfterEdit.getFiles().size());
    }

    /**
     * Check that it is possible to delete existing gist by id
     */
    @Test
    public void deleteExistingGist() throws IOException {
        createNewGist();
        Gist gistBeforeDelete = crudRestClient.getUserGists().get(0);
        crudRestClient.deleteGist(gistBeforeDelete.getId());

        assertNull("Check by id that gist was deleted", crudRestClient.getGistById(gistBeforeDelete.getId()));
    }

    /**
     * Check that it is not possible to delete gist by not existing id
     */
    @Test(expected = RestClientException.class)
    public void deleteGistWithInvalidId() {
        crudRestClient.deleteGist("123abc");
    }

    /**
     * Check that it is not possible to edit gist by not existing id
     */
    @Test(expected = RestClientException.class)
    public void editGhistWithInvalidId() {
        crudRestClient.editGist("abc123", new Gist());
    }

    /**
     * Check that it is not possible to create new gist using invalid body
     */
    @Test(expected = RestClientException.class)
    public void createGistWithInvalidBody() {
        crudRestClient.createGist(new Gist());
    }
}