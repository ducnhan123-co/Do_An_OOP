package QuanLySieuThiMiNi;

import java.util.Date;
import java.util.Scanner;

public class GiaDung extends SanPham {
    private String thuongHieu;
    private int baoHanh;

    public GiaDung() {
        super();  // Constructor mặc định của lớp cha (SanPham)
        this.thuongHieu = "";
        this.baoHanh = 0;
    }

    public GiaDung(int maSP, String tenSP, String DVT, float donGia, int soLuong, Date ngaySX, String moTa, String thuongHieu, int baoHanh) {
        super(maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa); // Gọi constructor của lớp cha
        this.thuongHieu = thuongHieu;
        this.baoHanh = baoHanh;
    }

    public void nhap() {
        super.nhap();  // Nhập thông tin sản phẩm từ lớp cha
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập thương hiệu: ");
        thuongHieu = sc.nextLine();

        System.out.print("Nhập số tháng bảo hành: ");
        baoHanh = sc.nextInt();
    }

    public void xuat() {
        super.xuat();  // Gọi phương thức xuat() từ lớp cha (SanPham)
        System.out.printf("|%-20s|%-10d tháng|\n", thuongHieu, baoHanh);  // Thêm thông tin thương hiệu và bảo hành
    }
}

