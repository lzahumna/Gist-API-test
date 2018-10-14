package com.lzahumna.rest.client;

import com.lzahumna.dto.Fork;
import com.lzahumna.dto.Gist;

import java.util.List;

/**
 * Gists REST API client to manage forks
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public interface GistsForkRestClient {

    /**
     * Fork gist by gist id
     *
     * @param gistId id of gist to be forked
     * @return forked gist
     */
    Gist forkGist(String gistId);

    /**
     * Fetch gist forks by gist id
     *
     * @param gistId id of gist
     * @return list of forks
     */
    List<Fork> getGistForks(String gistId);

}
