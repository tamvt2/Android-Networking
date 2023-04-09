package com.example.assignment.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;

public class TruyenTranh implements Serializable {
    private String tenTruyen, moTa, tacGia, nam_xb, anh_bia, id;
//    private String anh_nd;

    public TruyenTranh() {

    }

    public TruyenTranh(JSONObject obj) throws JSONException {
        id = obj.getString("_id");
        tenTruyen = obj.getString("ten_truyen");
        moTa = obj.getString("mo_ta");
        tacGia = obj.getString("tac_gia");
        nam_xb = obj.getString("nam_xb");
        anh_bia = obj.getString("anh_bia");

    }

    public TruyenTranh(String tenTruyen, String moTa, String tacGia, String nam_xb, String anh_bia, String id) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.moTa = moTa;
        this.tacGia = tacGia;
        this.nam_xb = nam_xb;
        this.anh_bia = anh_bia;
//        this.anh_nd = anh_nd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNam_xb() {
        return nam_xb;
    }

    public void setNam_xb(String nam_xb) {
        this.nam_xb = nam_xb;
    }

    public String getAnh_bia() {
        return anh_bia;
    }

    public void setAnh_bia(String anh_bia) {
        this.anh_bia = anh_bia;
    }

//    public String getAnh_nd() {
//        return anh_nd;
//    }
//
//    public void setAnh_nd(String anh_nd) {
//        this.anh_nd = anh_nd;
//    }
}
