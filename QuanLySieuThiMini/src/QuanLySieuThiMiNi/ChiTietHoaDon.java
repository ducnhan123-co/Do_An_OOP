package QuanLySieuThiMiNi;

public class ChiTietHoaDon {
    private int maHD;
    private int maSP;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(int maHD, int maSP, int soLuong, float donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
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

    // Tính tổng tiền cho chi tiết hóa đơn
    public double tinhTien() {
        return soLuong * donGia;
    }

    // Xuất thông tin chi tiết hóa đơn
    public void xuatChiTietHoaDon() {
        System.out.printf("| %10d |  %10d | %10d | %10.2f | %10.2f |\n", maHD, maSP, soLuong, donGia, tinhTien());
        // System.out.println("Mã HD: " + maHD + ", Mã SP: " + maSP + ", Số lượng: " + soLuong + ", Đơn giá: " + donGia + ", Thành tiền: " + tinhTien());
    }
}
