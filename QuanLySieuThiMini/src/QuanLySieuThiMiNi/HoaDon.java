package QuanLySieuThiMiNi;

import java.util.Date;
import java.util.Scanner;

public class HoaDon {
    Scanner in = new Scanner(System.in);
    private int maHD;
    private int maKH;
    private int maNV;
    private Date ngayTao;
    private float tongTien;
    private String phuongThucTinhToan;
    private float tienTra;
    private float tienThua;

    public HoaDon(int maHD, int maKH, int maNV, Date ngayTao, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
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
        this.tongTien = other.tongtien;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public void nhap() {
        while (true) {
            try {

                System.out.println("Nhap ma hoa don: ");
                this.maHD = in.nextInt();
                System.out.print("nhap ma khach hang: ");
                this.maKH = in.nextInt();
                System.out.print("nhap ma nhan vien: ");
                this.maKH = in.nextInt();
                System.out.print("nhap ngay tao hoa don: ");
                System.out.print("nhap ngay: ");
                this.ngayTao.setDate(in.nextInt());
                System.out.print("nhap thang: ");
                this.ngayTao.setMonth(in.nextInt());
                System.out.print("nhap nam: ");
                this.ngayTao.setYear(in.nextInt());
                System.out.print("nhap tong tien: ");
                this.tongTien = in.nextFloat();
                System.out.print("nhap phuong thuc tinh toan: ");
                this.tongTien = in.nextLine();
                System.out.print("nhap tien tra: ");
                this.tongTien = in.nextFloat();
                System.out.print("nhap tien thua: ");
                this.tongTien = in.nextFloat();
                
            } catch (Exception e) {
                System.out.println("Hãy nhập lại!");
            }
        }
    }
}
