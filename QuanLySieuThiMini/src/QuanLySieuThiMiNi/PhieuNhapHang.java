package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
public class  PhieuNhapHang {
    private int maPhieu, maNCC;
    private double tongTien;
    private LocalDate ngayNhapHang;

    
    public PhieuNhapHang(int maPhieu, int maNCC, double tongTien,LocalDate ngayNhapHang) {
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhapHang = ngayNhapHang;
    }
    
    public PhieuNhapHang() {
        
    }
    
    
    public PhieuNhapHang(PhieuNhapHang x) {
        this.maPhieu = x.maPhieu;
        this.maNCC = x.maNCC;
        this.tongTien = x.tongTien;
        this.ngayNhapHang = x.ngayNhapHang;
    }

    
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

    public LocalDate getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(LocalDate DangayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }
    
    private void push_chitietPNH (DanhSachChiTietPhieuNhapHang a, int maPhieu, int maSP, int soLuong) {
    	a.push(new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong));
    }
    
    // Hàm nhập dữ liệu
    public void nhapPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu: ");
        maPhieu = sc.nextInt();
        System.out.print("Nhập mã nhà cung cấp: ");
        maNCC = sc.nextInt();
        System.out.println("Nhập ngày nhập hàng");
        ngayNhapHang = LocalDate.now();
        tongTien = 0;
        while(true) {
        	System.out.println("Nhập mã sản phẩm");
        	int maSP = sc.nextInt();
        	System.out.println("Nhập sô lượng");
        	int sl  = sc.nextInt();
        	push_chitietPNH(null, sl, maSP, sl);
        }
//        System.out.print("Nhập tổng tiền: ");
//        tongTien = sc.nextDouble();
    }
    
    // Hàm xuất dữ liệu
    public void xuatPhieu() {
        System.out.printf("|%-8s|%-15s|%-14s|%-15s|\n", 
                maPhieu,maNCC,ngayNhapHang,tongTien);
    }
}

