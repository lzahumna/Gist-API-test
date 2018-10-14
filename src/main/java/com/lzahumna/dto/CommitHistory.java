package com.lzahumna.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Java representation of history attribute
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommitHistory {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("change_status")
    @Expose
    private ChangeStatus changeStatus;

    @SerializedName("committed_at")
    @Expose
    private String committedAt;

}
