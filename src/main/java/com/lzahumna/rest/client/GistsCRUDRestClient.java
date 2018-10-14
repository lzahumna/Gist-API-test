package com.lzahumna.rest.client;

import com.lzahumna.dto.Gist;
import com.lzahumna.dto.CommitHistory;

import java.util.List;

/**
 * Gists REST API client to manipulate gists
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public interface GistsCRUDRestClient {

    /**
     * Get user gists.
     * <p>
     * NOTE: user must be authorized to do it
     *
     * @return list of gists
     */
    List<Gist> getUserGists();

    /**
     * Get public gists
     * <p>
     * NOTE: user hasn't to be authorized to do it
     *
     * @return list of public gists
     */
    List<Gist> getPublicGists();

    /**
     * Get gist by id
     *
     * @param gistId
     * @return gist
     */
    Gist getGistById(String gistId);

    /**
     * Create gist
     *
     * @param newGist gist to be sent
     * @return created gist
     */
    Gist createGist(Gist newGist);

    /**
     * Edit gist
     *
     * @param gistId      id of gist to be editted
     * @param updatedGist gist to be sent to be updated
     * @return updated gist
     */
    Gist editGist(String gistId, Gist updatedGist);

    /**
     * Delete gist
     *
     * @param gistId id of gist to be deleted
     */
    void deleteGist(String gistId);
}
