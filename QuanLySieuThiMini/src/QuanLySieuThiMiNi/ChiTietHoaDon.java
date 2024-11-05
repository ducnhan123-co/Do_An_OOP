package QuanLySieuThiMiNi;

import java.util.Scanner;

public class ChiTietHoaDon {
    private int soLuong;
    private float donGia;
    static Scanner in = new Scanner(System.in);

    public ChiTietHoaDon() {

    }

    public ChiTietHoaDon(int soLuong, float donGia) {
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHoaDon(ChiTietHoaDon other) {
        this.soLuong = other.soLuong;
        this.donGia = other.donGia;
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

    // Tính tổng tiền cho từng mặt hàng
   
    // Giá nếu mua sản phẩm trên 500k thì được giảm 10%

}
