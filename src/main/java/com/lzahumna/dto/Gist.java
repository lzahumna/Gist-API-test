
package com.lzahumna.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Java representation of Gist object
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Gist {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("forks_url")
    @Expose
    private String forksUrl;

    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("node_id")
    @Expose
    private String nodeId;

    @SerializedName("git_pull_url")
    @Expose
    private String gitPullUrl;

    @SerializedName("git_push_url")
    @Expose
    private String gitPushUrl;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @SerializedName("files")
    @Expose
    private Map<String, GistFile> files;

    @SerializedName("private")
    @Expose
    private Boolean _private;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("comments")
    @Expose
    private Integer comments;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;

    @SerializedName("owner")
    @Expose
    private User owner;

    @SerializedName("truncated")
    @Expose
    private Boolean truncated;

    @SerializedName("forks")
    @Expose
    private List<Fork> forks = null;

    @SerializedName("history")
    @Expose
    private List<CommitHistory> history = null;

}
