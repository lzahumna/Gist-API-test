
package com.lzahumna.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Java representation of ChangeStatus attribute
 */
public class ChangeStatus {

    @SerializedName("deletions")
    @Expose
    public Integer deletions;
    @SerializedName("additions")
    @Expose
    public Integer additions;
    @SerializedName("total")
    @Expose
    public Integer total;

}
