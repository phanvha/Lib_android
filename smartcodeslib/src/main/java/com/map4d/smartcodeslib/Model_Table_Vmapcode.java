package com.map4d.smartcodeslib;

import com.google.gson.annotations.SerializedName;

public class Model_Table_Vmapcode {
        private String id;
        private String address;
        private String code;
        private String doiTuongGanMa;
        private String isDeleted;
        private Double latitude;
        private Double longitude;
        private String maBuuChinh;
        private String maHuyen;
        private String maTinh;
        private String tenHuyen;
        private String tenTinh;

    public Model_Table_Vmapcode(String id, String address, String code, String doiTuongGanMa, String isDeleted, Double latitude, Double longitude, String maBuuChinh, String maHuyen, String maTinh, String tenHuyen, String tenTinh) {
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

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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
