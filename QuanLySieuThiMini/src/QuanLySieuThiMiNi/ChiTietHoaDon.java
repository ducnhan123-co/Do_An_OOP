package QuanLySieuThiMiNi;

import java.util.Scanner;

public class ChiTietHoaDon {
    private int maHD;
    private int maSP;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon( int maSP, int soLuong ,float donGia ) {
//      this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHoaDon(int maHD, int maSP, int soLuong) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    // Tính thành tiền cho từng sản phẩm mua
    public float tinhThanhTien() {
        return this.soLuong*this.donGia;
    }
   
    // Giá nếu mua sản phẩm trên 500k thì được giảm 10%
}