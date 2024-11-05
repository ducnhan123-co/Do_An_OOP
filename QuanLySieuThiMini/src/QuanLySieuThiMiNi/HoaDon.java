package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HoaDon {
    public static Scanner in = new Scanner(System.in);
    private int maHD;
    private KhachHang maKH;
    private NhanVien maNV;
    private Date ngayTao;
    private int soLuong;
    private float donGia;
    private float tongTien;
    private String phuongThucTinhToan;
    private float tienTra;
    private float tienThua;

    public HoaDon(int maHD, KhachHang maKH, NhanVien maNV, Date ngayTao, int soLuong, float donGia, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongtien;
        this.phuongThucTinhToan = phuongThucTinhToan;
        this.tienTra = tienTra;
        this.tienThua = tienThua;
    }

    public HoaDon(HoaDon other) {
        this.maHD = other.maHD;
        this.maKH = other.maKH;
        this.maNV = other.maNV;
        this.ngayTao = other.ngayTao;
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

    public KhachHang getMaKH() {
        return maKH;
    }

    public void setMaKH(KhachHang maKH) {
        this.maKH = maKH;
    }

    public NhanVien getMaNV() {
        return maNV;
    }

    public void setMaNV(NhanVien maNV) {
        this.maNV = maNV;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public void setTienThua(float tienThua) {
        this.tienThua = tienThua;
    }

    public void nhapHoaDon() {
        while(true) {
            try {

                System.out.println("Nhap ma hoa don: ");
                this.maHD = in.nextInt();
                System.out.print("nhap ma khach hang: ");
                int maKH = in.nextInt(); 
                System.out.print("nhap ma nhan vien: ");
                int maNV = in.nextInt();
                System.out.print("nhap ngay tao hoa don: ");
                System.out.print("nhap ngay: ");
                this.ngayTao.setDate(in.nextInt());
                System.out.print("nhap thang: ");
                this.ngayTao.setMonth(in.nextInt());
                System.out.print("nhap nam: ");
                this.ngayTao.setYear(in.nextInt());
                System.out.println("Nhap so luong: ");
                this.soLuong = in.nextInt();
                System.out.println("Nhap don gia: ");
                this.donGia = in.nextFloat();
                System.out.print("tong tien: ");
                this.tongTien = soLuong*donGia;
                System.out.print("nhap phuong thuc tinh toan: ");
                this.tongTien = in.nextFloat();
                System.out.print("nhap tien tra: ");
                this.tongTien = in.nextFloat();
                System.out.print("nhap tien thua: ");
                this.tongTien = in.nextFloat();
                
            } catch (Exception e) {
                System.out.println("Hãy nhập lại!");
            }
        }
    }

    public void xuatHoaDon() {
        System.out.printf("|%-20d|%-20d|%-20d|%-20d|%-20f|%-20s|%-20f|%-20f|", maHD, maKH, maNV, soLuong, donGia, phuongThucTinhToan, tienTra, tienThua);
        System.out.println("*Tong thanh toan: "+tongTien);
    }

    public void setTongDonGia() {
        tongTien = soLuong*donGia;
        System.out.println("Tong don gia cua hoa don: "+tongTien);
    }

    public static void main(String[] args) {
        LocalDate a = LocalDate.now() ;
        System.out.printf("%d/%d/%d", a.getDayOfMonth(), a.getMonth().getValue(), a.getYear());
    }

}
