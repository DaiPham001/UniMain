package com.example.uniman.Model;

public class Semester {
    private int id, khoa ,mgv,tinchi,tongtc,thu;
    private String manganh,tenmh, hocki ,mamh,lop, namhoc,ngaydk,tengv;
    private double hocphi;
    private boolean isSelected;

    public String getTengv() {
        return tengv;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTongtc() {
        return tongtc;
    }

    public void setTongtc(int tongtc) {
        this.tongtc = tongtc;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public String getNgaydk() {
        return ngaydk;
    }

    public void setNgaydk(String ngaydk) {
        this.ngaydk = ngaydk;
    }

    public double getHocphi() {
        return hocphi;
    }

    public void setHocphi(double hocphi) {
        this.hocphi = hocphi;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public int getKhoa() {
        return khoa;
    }

    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }

    public int getMgv() {
        return mgv;
    }

    public void setMgv(int mgv) {
        this.mgv = mgv;
    }

    public int getTinchi() {
        return tinchi;
    }

    public void setTinchi(int tinchi) {
        this.tinchi = tinchi;
    }

    public String getManganh() {
        return manganh;
    }

    public void setManganh(String manganh) {
        this.manganh = manganh;
    }

    public String getHocki() {
        return hocki;
    }

    public void setHocki(String hocki) {
        this.hocki = hocki;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Semester other = (Semester) obj;
        return this.namhoc.equals(other.namhoc) && this.hocki.equals(other.hocki);
    }
}
