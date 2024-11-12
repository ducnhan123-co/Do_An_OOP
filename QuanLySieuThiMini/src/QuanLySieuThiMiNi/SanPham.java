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
    private Date ngaySX;
    private String moTa;

    // Constructor mặc định
    public SanPham() {
        this.maSP = 0;
        this.tenSP = "";
        this.DVT = "";
        this.donGia = 0.0f;
        this.soLuong = 0;
        this.ngaySX = new Date();
        this.moTa = "";
    }

    // Constructor có tham số
    public SanPham(int maSP, String tenSP, String DVT, float donGia, int soLuong, Date ngaySX, String moTa) {
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

    public Date getNgaySX() { return ngaySX; }
    public void setNgaySX(Date ngaySX) { this.ngaySX = ngaySX; }

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
        try {
            ngaySX = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Ngày không hợp lệ, sử dụng ngày hiện tại.");
            ngaySX = new Date();
        }
    }

    // Xuất thông tin sản phẩm
    public void xuat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày là "yyyy-MM-dd"
        String formattedDate = sdf.format(ngaySX); // Chuyển đổi ngày thành chuỗi theo định dạng

        System.out.printf("|%-10d|%-15s|%-10s|%-10.2f|%-10d|%-15s|%-20s|\n",
                maSP, tenSP, DVT, donGia, soLuong, formattedDate, moTa);
    }
}