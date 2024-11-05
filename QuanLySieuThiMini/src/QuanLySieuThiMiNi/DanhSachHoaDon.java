package QuanLySieuThiMiNi;

public class DanhSachHoaDon {
    private HoaDon[] dshd = new HoaDon[0];
    private int n;

    public DanhSachHoaDon() {
        this.n = 0;
    }

    public DanhSachHoaDon(HoaDon[] dshd, int n) {
        this.dshd = dshd;
        this.n = n;
    }

    public DanhSachHoaDon(DanhSachHoaDon other) {
        this.dshd = other.dshd;
        this.n = other.n;
    }

    public HoaDon[] getDshd() {
        return dshd;
    }

    public void setDshd(HoaDon[] dshd) {
        this.dshd = dshd;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

}
