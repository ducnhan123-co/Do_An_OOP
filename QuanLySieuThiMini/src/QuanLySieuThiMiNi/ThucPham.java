package QuanLySieuThiMiNi;

import java.util.Date;
import java.util.Scanner;

public class ThucPham extends SanPham{
    private String loaiThucPham;
    private int hanSuDung;

    public ThucPham() {}
    public ThucPham(int maSP, String tenSP, String DVT, float donGia, int soLuong, Date ngaySX, String moTa, String loaiThucPham, int hanSuDung) {
        super(maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa);
        this.loaiThucPham = loaiThucPham;
        this.hanSuDung = hanSuDung;
    }
    public ThucPham(ThucPham other) {
        super((SanPham) other);
        this.loaiThucPham = other.loaiThucPham;
        this.hanSuDung = other.hanSuDung;
    }

    public String getLoaiThucPham() {
        return loaiThucPham;
    }

    public void setLoaiThucPham(String loaiThucPham) {
        this.loaiThucPham = loaiThucPham;
    }

    public int getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(int hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public void nhap() {
        super.nhap();
        while (true) {
            try {
                Scanner in = new Scanner(System.in) ;
                System.out.print("Nhap loai thuc pham: ");
                this.loaiThucPham = in.nextLine();
                System.out.print("Nhap han su dung thuc pham: ");
                this.hanSuDung = in.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
