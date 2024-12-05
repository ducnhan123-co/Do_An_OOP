package QuanLySieuThiMiNi.HoaDon;
import QuanLySieuThiMiNi.SanPham.DanhSachSanPham;

public class ChiTietHoaDon {
    private int maHD;
    private int maSP;
    private int soLuong;
    private float donGia;
    private double thanhTien;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(int maHD, int maSP, int soLuong, float donGia, double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon(ChiTietHoaDon other) {
        this.maHD = other.maHD;
        this.maSP = other.maSP;
        this.soLuong = other.soLuong;
        this.donGia = other.donGia;
        this.thanhTien = other.thanhTien;
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

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Tính tổng tiền cho chi tiết hóa đơn
    public double tinhTien() {
        return soLuong * donGia;
    }

    // Xuất thông tin chi tiết hóa đơn
    public void xuatChiTietHoaDon() {
        String tenSP = new DanhSachSanPham().timTenSanPhamTheoMa(maSP);
        thanhTien = tinhTien();
        System.out.printf("| %10d |  %10d | %-12s | %-10d | %-10.2f | %10.2f |\n", maHD, maSP, tenSP, soLuong, donGia, thanhTien);
    }
}

