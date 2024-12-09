package QuanLySieuThiMiNi.HoaDon;

public class HoaDon {
    private int maHD;
    private int maKH;
    private int maNV;
    private String ngayTaoHoaDon;
    private float tongTien=0;
    private String phuongThucTinhToan;
    private float tienTra;
    private float tienThua;

    public HoaDon(){}

    public HoaDon(int maHD, int maKH, int maNV, String ngayTaoHoaDon, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.tongTien = tongtien;
        this.phuongThucTinhToan = phuongThucTinhToan;
        this.tienTra = tienTra;
        this.tienThua = tienThua;
    }

    public HoaDon(HoaDon other) {
        this.maHD = other.maHD;
        this.maKH = other.maKH;
        this.maNV = other.maNV;
        this.ngayTaoHoaDon = other.ngayTaoHoaDon;
        this.tongTien = other.tongTien;
        this.phuongThucTinhToan = other.phuongThucTinhToan;
        this.tienTra = other.tienTra;
        this.tienThua = other.tienThua;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhuongThucTinhToan() {
        return phuongThucTinhToan;
    }

    public void setPhuongThucTinhToan(String phuongThucTinhToan) {
        this.phuongThucTinhToan = phuongThucTinhToan;
    }

    public float getTienTra() {
        return tienTra;
    }

    public void setTienTra(float tienTra) {
        this.tienTra = tienTra;
    }

    public float getTienThua() {
        return tienThua;
    }

    public void setTienThua() {
        this.tienThua = tienTra - tongTien;
    }

    // Hàm xuất hóa đơn
    public void xuatHoaDon() {
        System.out.println("Mã hóa đơn: "+this.maHD);
        System.out.println("Mã khách hàng: "+this.maKH);
        System.out.println("Mã nhân viên: "+this.maNV);
        System.out.println("Ngày lập hóa đơn: "+ngayTaoHoaDon);
        System.out.println("Tổng tiền: "+tongTien+" VND");
        System.out.println("Phương thức thanh toán: "+phuongThucTinhToan);
        System.out.println("Tiền trả: "+tienTra+" VND");
        System.out.println("Tiền thừa: "+tienThua+" VND");
        System.out.println("------------------------------------");
    }
}