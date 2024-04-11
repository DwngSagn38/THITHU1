package com.example.onthi_app.Model;

public class Xemay {
    private String _id,ten_xe_ph1234,mau_sac_ph1234,mo_ta_ph1234,hinh_anh_ph1234;
    private int gia_ban_ph1234;

    public Xemay() {
    }

    public Xemay(String ten_xe_ph1234, String mau_sac_ph1234, String mo_ta_ph1234, String hinh_anh_ph1234, int gia_ban_ph1234) {
        this.ten_xe_ph1234 = ten_xe_ph1234;
        this.mau_sac_ph1234 = mau_sac_ph1234;
        this.mo_ta_ph1234 = mo_ta_ph1234;
        this.hinh_anh_ph1234 = hinh_anh_ph1234;
        this.gia_ban_ph1234 = gia_ban_ph1234;
    }

    public Xemay(String _id, String ten_xe_ph1234, String mau_sac_ph1234, String mo_ta_ph1234, String hinh_anh_ph1234, int gia_ban_ph1234) {
        this._id = _id;
        this.ten_xe_ph1234 = ten_xe_ph1234;
        this.mau_sac_ph1234 = mau_sac_ph1234;
        this.mo_ta_ph1234 = mo_ta_ph1234;
        this.hinh_anh_ph1234 = hinh_anh_ph1234;
        this.gia_ban_ph1234 = gia_ban_ph1234;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen_xe_ph1234() {
        return ten_xe_ph1234;
    }

    public void setTen_xe_ph1234(String ten_xe_ph1234) {
        this.ten_xe_ph1234 = ten_xe_ph1234;
    }

    public String getMau_sac_ph1234() {
        return mau_sac_ph1234;
    }

    public void setMau_sac_ph1234(String mau_sac_ph1234) {
        this.mau_sac_ph1234 = mau_sac_ph1234;
    }

    public String getMo_ta_ph1234() {
        return mo_ta_ph1234;
    }

    public void setMo_ta_ph1234(String mo_ta_ph1234) {
        this.mo_ta_ph1234 = mo_ta_ph1234;
    }

    public String getHinh_anh_ph1234() {
        return hinh_anh_ph1234;
    }

    public void setHinh_anh_ph1234(String hinh_anh_ph1234) {
        this.hinh_anh_ph1234 = hinh_anh_ph1234;
    }

    public int getGia_ban_ph1234() {
        return gia_ban_ph1234;
    }

    public void setGia_ban_ph1234(int gia_ban_ph1234) {
        this.gia_ban_ph1234 = gia_ban_ph1234;
    }
}
