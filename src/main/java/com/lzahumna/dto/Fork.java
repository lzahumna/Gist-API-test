
package com.lzahumna.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Java representation of Fork object
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Fork {

    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

}
