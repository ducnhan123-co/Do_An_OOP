package QuanLySieuThiMiNi;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SanPham {
    private int maSP;
    private String tenSP, DVT;
    private float donGia;
    private int soLuong;
    private String ngaySX;
    private String moTa;

    // Constructor mặc định
    public SanPham() {
        this.maSP = 0;
        this.tenSP = "";
        this.DVT = "";
        this.donGia = 0.0f;
        this.soLuong = 0;
        this.moTa = "";
    }

    // Constructor có tham số
    public SanPham(int maSP, String tenSP, String DVT, float donGia, int soLuong, String ngaySX, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.DVT = DVT;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngaySX = ngaySX;
        this.moTa = moTa;
    }

    // Getters and Setters
    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getDVT() { return DVT; }
    public void setDVT(String DVT) { this.DVT = DVT; }

    public float getDonGia() { return donGia; }
    public void setDonGia(float donGia) { this.donGia = donGia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getNgaySX() { return ngaySX; }
    public void setNgaySX(String ngaySX) { this.ngaySX = ngaySX; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    // Nhập thông tin sản phẩm
    public void nhap() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã sản phẩm: ");
        maSP = sc.nextInt();
        sc.nextLine(); // Đọc ký tự còn lại sau khi nhập số

        System.out.print("Nhập tên sản phẩm: ");
        tenSP = sc.nextLine();

        System.out.print("Nhập đơn vị tính (ĐVT): ");
        DVT = sc.nextLine();

        System.out.print("Nhập đơn giá: ");
        donGia = sc.nextFloat();

        System.out.print("Nhập số lượng: ");
        soLuong = sc.nextInt();
        sc.nextLine(); // Đọc dòng trống

        System.out.print("Nhập mô tả sản phẩm: ");
        moTa = sc.nextLine();

        System.out.print("Nhập ngày sản xuất (yyyy-MM-dd): ");
        String dateInput = sc.nextLine();
    }

    // Xuất thông tin sản phẩm
    public void xuat() {
        System.out.printf("|%-10d|%-15s|%-10s|%-10.2f|%-10d|%-15s|%-40s",
                maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa);
    }
}