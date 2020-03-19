package com.map4d.smartcodeslib;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_Geometry_tb {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private String coordinates;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;

    public Model_Geometry_tb(String id, String code, String type, String coordinates, Boolean isDeleted) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.coordinates = coordinates;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
