package QuanLySieuThiMiNi;

import java.util.Date;
import java.util.Scanner;

public class SanPham {
    private int maSP;
    private String tenSP, DVT;
    private float donGia;
    private int soLuong;
    private Date ngaySX = new Date();
    private String moTa;

    public SanPham(){}
    public SanPham(int maSP, String tenSP, String DVT, float donGia, int soLuong, Date ngaySX, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.DVT = DVT;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngaySX = ngaySX;
        this.moTa = moTa;
    }
    public SanPham(SanPham other) {
        this.maSP = other.maSP;
        this.tenSP = other.tenSP;
        this.DVT = other.DVT;
        this.donGia = other.donGia;
        this.soLuong = other.soLuong;
        this.ngaySX = other.ngaySX;
        this.moTa = other.moTa;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void nhap() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Nhap ma san pham: ");
                this.maSP = in.nextInt();
                in.nextLine();
                System.out.print("Nhap ten san pham: ");
                this.tenSP = in.nextLine().trim();
                System.out.print("Nhap don vi tinh san pham: ");
                this.DVT = in.nextLine().trim();
                System.out.print("Nhap gia san pham: ");
                this.donGia = in.nextFloat();
                System.out.print("Nhap so luong san pham: ");
                this.soLuong = in.nextInt();
                System.out.println("Nhap ngay san xuat san pham: ");
                System.out.print("Nhap ngay: ");
                ngaySX.setDate(in.nextInt());
                System.out.print("Nhap thang: ");
                ngaySX.setMonth(in.nextInt());
                System.out.print("Nhap nam: ");
                ngaySX.setYear(in.nextInt());
                System.out.print("Nhap mo ta san pham: ");
                in.nextLine();
                this.moTa = in.nextLine().trim();

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void sua() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Nhap ten san pham: ");
                this.tenSP = in.nextLine().trim();
                System.out.print("Nhap don vi tinh san pham: ");
                this.DVT = in.nextLine().trim();
                System.out.print("Nhap gia san pham: ");
                this.donGia = in.nextFloat();
                System.out.print("Nhap so luong san pham: ");
                this.soLuong = in.nextInt();
                System.out.println("Nhap ngay san xuat san pham: ");
                System.out.print("Nhap ngay: ");
                ngaySX.setDate(in.nextInt());
                System.out.print("Nhap thang: ");
                ngaySX.setMonth(in.nextInt());
                System.out.print("Nhap nam: ");
                ngaySX.setYear(in.nextInt());
                System.out.print("Nhap mo ta san pham: ");
                in.nextLine();
                this.moTa = in.nextLine().trim();

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String a = new Scanner(System.in).nextLine().trim().trim();
        System.out.println(a);
    }
}
