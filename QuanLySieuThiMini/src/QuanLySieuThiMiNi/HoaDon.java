package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HoaDon {
    public static Scanner in = new Scanner(System.in);
    private int maHD;
    private KhachHang maKH;
    private NhanVien maNV;
    private LocalDate ngayTaoHoaDon;
    // private int soLuong;
    // private float donGia;
    private float tongTien;
    private String phuongThucTinhToan;
    private float tienTra;
    private float tienThua;

    public HoaDon(int maHD, KhachHang maKH, NhanVien maNV, LocalDate ngayTaoHoaDon, int soLuong, float donGia, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayTaoHoaDon = LocalDate.now();
        // this.soLuong = soLuong;
        // this.donGia = donGia;
        this.tongTien = tongtien;
        this.phuongThucTinhToan = phuongThucTinhToan;
        this.tienTra = tienTra;
        this.tienThua = tienThua;
    }

    public HoaDon(HoaDon other) {
        this.maHD = other.maHD;
        this.maKH = other.maKH;
        this.maNV = other.maNV;
        this.ngayTaoHoaDon = LocalDate.now();
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

    public LocalDate getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(LocalDate ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    // public int getSoLuong() {
    //     return soLuong;
    // }

    // public void setSoLuong(int soLuong) {
    //     this.soLuong = soLuong;
    // }

    // public float getDonGia() {
    //     return donGia;
    // }

    // public void setDonGia(float donGia) {
    //     this.donGia = donGia;
    // }

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

                System.out.println("Nhập mã hóa đơn: ");
                this.maHD = in.nextInt();
                System.out.print("nhập mã khách hàng: ");
                this.maKH.setMaKH(in.nextInt());
                System.out.print("nhập mã nhân viên: ");
                this.maNV.setManv(in.nextInt());
                System.out.print("nhập ngày tạo hóa đơn: ");
                this.ngayTaoHoaDon = LocalDate.now();
                
                
                // Tính tổng tiền từ các chi tiết mặt hàng
                this.tongTien = tinhTongTien();
                System.out.printf("Tổng tiền: %.2f\n", this.tongTien);
                
                System.out.print("nhập phương thức tính toán: ");
                this.phuongThucTinhToan = in.nextLine();

                // Nhập tiền trả và tiền thừa
                System.out.print("Nhập tiền trả: ");
                this.tienTra = in.nextFloat();
                this.tienThua = this.tienTra - tongTien;  // Tiền thừa = tiền trả - tổng tiền
                System.out.printf("Tiền thừa: %.2f\n", tienThua);
                break;
            } catch (Exception e) {
                System.out.println("Hãy nhập lại!");
                in.nextLine();
            }
        }
    }

    public void xuatHoaDon() {
        System.out.printf("|%-20d|%-20d|%-20d|%-20s|%-20f|%-20f|", maHD, maKH, maNV, /*soLuong, donGia,*/ phuongThucTinhToan, tienTra, tienThua);
    }

    // Phương thức tính tổng tiền từ chi tiết hóa đơn nhập vào
    public float tinhTongTien() {
        float tongTien = 0;
        System.out.print("Nhập số lượng mặt hàng: ");
        int soLuongMatHang = in.nextInt();

        //Nhập từng chi tiết hóa đơn và tính tổng tiền
        for(int i = 0; i < soLuongMatHang; i++) {
            System.out.println("-Nhập chi tiết hóa đơn thứ " + (i + 1) + ":");
            System.out.print("+Nhập số lượng: ");
            int soLuong = in.nextInt();
            System.out.print("+Nhập đơn giá: ");
            float donGia = in.nextFloat();

            ChiTietHoaDon chiTiet = new ChiTietHoaDon(soLuong, donGia);
            tongTien += chiTiet.getDonGia() * chiTiet.getSoLuong();  // Cộng dồn tổng tiền
        }
        return tongTien;
    }

<<<<<<< HEAD
    public static void main(String[] args) {
        LocalDate a = LocalDate.now() ;
        System.out.printf("%d/%d/%d", a.getDayOfMonth(), a.getMonth().getValue(), a.getYear());
    }
=======
    // public float getThanhTien() {
    //     return this.soLuong * this.donGia;
    // }

>>>>>>> 1de8ea724612d2118f6d357fecefeae6a3fc84f1

}
