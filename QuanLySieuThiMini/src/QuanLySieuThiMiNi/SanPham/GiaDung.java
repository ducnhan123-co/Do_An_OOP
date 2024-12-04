package QuanLySieuThiMiNi.SanPham;

import java.util.Scanner;

public class GiaDung extends SanPham {
    private String thuongHieu;
    private int baoHanh;

    public GiaDung() {
        super();  // Constructor mặc định của lớp cha (SanPham)
        this.thuongHieu = "";
        this.baoHanh = 0;
    }

    public GiaDung(int maSP, String tenSP, String DVT, float donGia, int soLuong, String ngaySX, String moTa, String thuongHieu, int baoHanh) {
        super(maSP, tenSP, DVT, donGia, soLuong, ngaySX, moTa); // Gọi constructor của lớp cha
        this.thuongHieu = thuongHieu;
        this.baoHanh = baoHanh;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        super.nhap();  // Nhập thông tin sản phẩm từ lớp cha

        System.out.print("Nhập thương hiệu: ");
        thuongHieu = sc.nextLine();

        System.out.print("Nhập số tháng bảo hành: ");
        baoHanh = sc.nextInt();
    }

    public void xuat() {
        super.xuat();  // Gọi phương thức xuat() từ lớp cha (SanPham)
        System.out.printf("|%-20s|%-10d tháng|\n", thuongHieu, baoHanh);  // Thêm thông tin thương hiệu và bảo hành
    }

    @Override
    public boolean kiemTraDacThu() {
        return baoHanh > 12; // Trả về true nếu bảo hành trên 12 tháng
    }

    @Override
    public float tinhPhiVanChuyen() {
        return soLuong * 10_000; // Giả sử mỗi sản phẩm có phí 10.000 VNĐ
    }
    @Override
    public boolean kiemTraChatLuong() {
        return baoHanh >= 12 && !thuongHieu.isEmpty(); // Bảo hành dài và có thương hiệu
    }
}

