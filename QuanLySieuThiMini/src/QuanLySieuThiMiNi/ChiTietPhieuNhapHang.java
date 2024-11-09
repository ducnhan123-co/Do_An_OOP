package QuanLySieuThiMiNi;

public class ChiTietPhieuNhapHang {
    private int maPhieu;
    private int maSp;
    private int sl;
    private double donGia;


    public ChiTietPhieuNhapHang(int maPhieu, int maSp, int sl, double donGia) {
        this.maPhieu = maPhieu;
        this.maSp = maSp;
        this.sl = sl;
        this.donGia = donGia;
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
}