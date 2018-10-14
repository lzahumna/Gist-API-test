package com.lzahumna.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Java representation of file object
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class GistFile {


    @SerializedName("filename")
    @Expose
    private String filename;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("raw_url")
    @Expose
    private String rawUrl;

    @SerializedName("size")
    @Expose
    private Integer size;

    @SerializedName("truncated")
    @Expose
    private Boolean truncated;

    @SerializedName("content")
    @Expose
    private String content;
}
