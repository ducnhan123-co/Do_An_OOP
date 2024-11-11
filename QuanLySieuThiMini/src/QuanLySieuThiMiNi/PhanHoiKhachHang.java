package QuanLySieuThiMiNi;

import java.time.LocalDate;

public class PhanHoiKhachHang {
    private int maPhanHoi;
    private int maKhachHang;
    private String noiDung;
    private LocalDate ngayPhanHoi;

    // Constructor
    public PhanHoiKhachHang(int maPhanHoi, int maKhachHang, String noiDung, LocalDate ngayPhanHoi) {
        this.maPhanHoi = maPhanHoi;
        this.maKhachHang = maKhachHang;
        this.noiDung = noiDung;
        this.ngayPhanHoi = ngayPhanHoi;
    }

    // Getters và Setters
    public int getMaPhanHoi() {
        return maPhanHoi;
    }

    public void setMaPhanHoi(int maPhanHoi) {
        this.maPhanHoi = maPhanHoi;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDate getNgayPhanHoi() {
        return ngayPhanHoi;
    }

    public void setNgayPhanHoi(LocalDate ngayPhanHoi) {
        this.ngayPhanHoi = ngayPhanHoi;
    }

    // Phương thức để hiển thị thông tin phản hồi
    public void xuatThongTinPhanHoi() {
        System.out.println("Mã phản hồi: " + maPhanHoi);
        System.out.println("Mã khách hàng: " + maKhachHang);
        System.out.println("Nội dung phản hồi: " + noiDung);
        System.out.println("Ngày phản hồi: " + ngayPhanHoi);
    }
}
