package QuanLySieuThiMiNi;

import java.util.Scanner;
public class PhieuNhapHang {
    private int maPhieu, maNCC;
    private double tongTien;
    private Ngay ngayNhapHang;

    // Constructor
    public PhieuNhapHang(int maPhieu, int maNCC, double tongTien, Ngay ngayNhapHang) {
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhapHang = ngayNhapHang;
    }
    
    // Default constructor
    public PhieuNhapHang() {
        
    }
    
    // Copy constructor
    public PhieuNhapHang(PhieuNhapHang x) {
        this.maPhieu = x.maPhieu;
        this.maNCC = x.maNCC;
        this.tongTien = x.tongTien;
        this.ngayNhapHang = x.ngayNhapHang;
    }

    // Getters and Setters
    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Ngay getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(Ngay ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }
    
    // Hàm nhập dữ liệu
    public void nhapPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu: ");
        maPhieu = sc.nextInt();
        System.out.print("Nhập mã nhà cung cấp: ");
        maNCC = sc.nextInt();
        ngayNhapHang.nhap(); 
        System.out.print("Nhập tổng tiền: ");
        tongTien = sc.nextDouble();
    }
    
    // Hàm xuất dữ liệu
    public void xuatPhieu() {
        System.out.printf("|%-6s|%-12s|%-13s|%-15s|\n", 
                maPhieu,maNCC,ngayNhapHang,tongTien);
    }
}

