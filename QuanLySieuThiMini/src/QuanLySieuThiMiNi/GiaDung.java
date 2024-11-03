package QuanLySieuThiMiNi;

import java.util.Date;
import java.util.Scanner;

public class GiaDung extends SanPham{

    private String thuongHieu;
    private int baoHanh;

    public GiaDung() {}
    public GiaDung(int maSP, String tenSP, String DVT, float donGia, int soLuong, Date ngaySX, String moTa, String thuongHieu, int baoHanh) {
        super(maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa);
        this.thuongHieu = thuongHieu;
        this.baoHanh = baoHanh;
    }
    public GiaDung(GiaDung other) {
        super((SanPham) other);
        this.thuongHieu = other.thuongHieu;
        this.baoHanh = other.baoHanh;
    }

    public String getthuongHieu() {
        return thuongHieu;
    }

    public void setthuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public int getbaoHanh() {
        return baoHanh;
    }

    public void setbaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void nhap() {
        super.nhap();
        while (true) {
            try {
                Scanner in = new Scanner(System.in) ;
                System.out.print("Nhap thuong hieu: ");
                this.thuongHieu = in.nextLine();
                System.out.print("Nhap so ngay bao hanh: ");
                this.baoHanh = in.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
