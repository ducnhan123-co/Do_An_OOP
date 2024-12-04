package QuanLySieuThiMiNi.SanPham;

import java.util.Scanner;

public class ThucPham extends SanPham {
    private String loaiThucPham;
    private int hanSuDung;

    public ThucPham() {
        super();  // Constructor mặc định của lớp cha (SanPham)
        this.loaiThucPham = "";
        this.hanSuDung = 0;
    }

    public ThucPham(int maSP, String tenSP, String DVT, float donGia, int soLuong, String ngaySX, String moTa, String loaiThucPham, int hanSuDung) {
        super(maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa); // Gọi constructor của lớp cha
        this.loaiThucPham = loaiThucPham;
        this.hanSuDung = hanSuDung;
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
        super.nhap();  // Nhập thông tin sản phẩm từ lớp cha
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập loại thực phẩm: ");
        loaiThucPham = sc.nextLine();

        System.out.print("Nhập hạn sử dụng (ngày): ");
        hanSuDung = sc.nextInt();
    }

    public void xuat() {
        super.xuat();  // Gọi phương thức xuat() từ lớp cha (SanPham)
        System.out.printf("|%-20s|%-10d  ngày|\n", loaiThucPham, hanSuDung);  // Thêm thông tin loại thực phẩm và hạn sử dụng
    }

    @Override
    public boolean kiemTraDacThu() {
        return this.hanSuDung>0;
    }

    @Override
    public float tinhPhiVanChuyen() {
        return this.soLuong*5000;
    }

    @Override
    public boolean kiemTraChatLuong() {
        return hanSuDung>0 && !loaiThucPham.isEmpty();
    }
}

