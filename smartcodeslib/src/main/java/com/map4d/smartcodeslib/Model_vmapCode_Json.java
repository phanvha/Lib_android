package com.map4d.smartcodeslib;

import com.google.gson.annotations.SerializedName;

public class Model_vmapCode_Json {
    @SerializedName("id")
    private String id;
    @SerializedName("address")
    private String address;
    @SerializedName("code")
    private String code;
    @SerializedName("doiTuongGanMa")
    private String doiTuongGanMa;
    @SerializedName("isDeleted")
    private Boolean isDeleted;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("maBuuChinh")
    private String maBuuChinh;
    @SerializedName("maHuyen")
    private String maHuyen;
    @SerializedName("maTinh")
    private String maTinh;
    @SerializedName("tenHuyen")
    private String tenHuyen;
    @SerializedName("tenTinh")
    private String tenTinh;

    public Model_vmapCode_Json(String id, String address, String code, String doiTuongGanMa, Boolean isDeleted, Double latitude, Double longitude, String maBuuChinh, String maHuyen, String maTinh, String tenHuyen, String tenTinh) {
        this.id = id;
        this.address = address;
        this.code = code;
        this.doiTuongGanMa = doiTuongGanMa;
        this.isDeleted = isDeleted;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maBuuChinh = maBuuChinh;
        this.maHuyen = maHuyen;
        this.maTinh = maTinh;
        this.tenHuyen = tenHuyen;
        this.tenTinh = tenTinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDoiTuongGanMa() {
        return doiTuongGanMa;
    }

    public void setDoiTuongGanMa(String doiTuongGanMa) {
        this.doiTuongGanMa = doiTuongGanMa;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMaBuuChinh() {
        return maBuuChinh;
    }

    public void setMaBuuChinh(String maBuuChinh) {
        this.maBuuChinh = maBuuChinh;
    }

    public String getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}
