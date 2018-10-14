package com.lzahumna.rest.client;

/**
 * Gists REST API to star/unstar gists
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public interface GistsStarRestClient {

    /**
     * Star gist
     *
     * @param gistId id of gist to be starred
     */
    void starGist(String gistId);

    /**
     * Unstar gist
     *
     * @param gistId id of gist to be unstarred
     */
    void unstarGist(String gistId);

    /**
     * Check if gist is starred.
     *
     * @param gistId id of gist to be checked
     * @return true if starred, otherwise false
     */
    boolean isGistStarred(String gistId);

}
