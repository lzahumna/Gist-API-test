package com.lzahumna.rest.client;

import com.google.gson.Gson;
import com.lzahumna.dto.Gist;
import io.restassured.internal.util.IOUtils;

import java.io.IOException;

/**
 * Contains common methods for all the tests
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
abstract class GistsApiBaseTest {

    Gist readGistFromJson() throws IOException {
        byte[] bytes = IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream("createGist.json"));
        String gistToCreate = new String(bytes);
        return new Gson().fromJson(gistToCreate, Gist.class);
    }
}
