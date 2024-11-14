package QuanLySieuThiMiNi;

public class ChiTietPhieuNhapHang {
    private int maPhieu;
    private int maSp;
    private int sl;
    private double thanhtien;
    private double donGia;


    public ChiTietPhieuNhapHang(int maPhieu, int maSp, int sl, double donGia, double thanhTien) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.sl = sl;
        this.donGia = donGia;
        this.thanhtien = thanhTien;
    }
    
    public ChiTietPhieuNhapHang() {
    	this.maPhieu = 0;
    	this.maSp = 0;
    	this.sl = 0;
    	this.donGia = 0;
    	this.thanhtien = 0;
    }
    
    public ChiTietPhieuNhapHang(ChiTietPhieuNhapHang x) {
    	this.maPhieu = x.maPhieu;
        this.maSp = x.maSp;
        this.sl = x.sl;
        this.donGia = x.donGia;
        this.thanhtien = x.thanhtien;
    }


    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    public double getThanhTien() {
        return thanhtien;
    }

    public void setThanhTien(double donGia) {
        this.donGia = donGia;
    }
    
    public void updateThanhTien() {
        this.thanhtien = this.sl * this.donGia;
    }
    
}