package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HoaDon {
    private int maHD;
    private KhachHang maKH;
    private int maNV;
    private LocalDate ngayTaoHoaDon;
    private float tongTien=0;
    private String phuongThucTinhToan;
//    private boolean trangthaithanhtoan; ???
    private float tienTra;
    private float tienThua;

    public HoaDon(){}

    public HoaDon(int maHD, KhachHang maKH, int maNV, LocalDate ngayTaoHoaDon, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
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

    public KhachHang getMaKH() {
        return maKH;
    }

    public void setMaKH(KhachHang maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public LocalDate getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(LocalDate ngayTaoHoaDon) {
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

    public void setTienThua(float tienThua) {
        this.tienThua = tienThua;
    }

    public void nhapHoaDon(DanhSachSanPham danhSachSanPham) {
        while(true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Nhập mã hóa đơn: ");
                this.maHD = in.nextInt();
                System.out.println("nhập mã khách hàng: ");
                this.maKH.setMaKH(in.nextInt());
//                System.out.print("nhập mã nhân viên: ");
                //Này không cần nhập mã nhân viên cũng được ha ,tại bên Nhân Viên là nhập + set ngày khi tạo hoá đơn mới rồi a nên ko cần cũng được
//                this.maNV.setManv(in.nextInt());
                this.ngayTaoHoaDon = LocalDate.now();
                System.out.println("nhập chi tiết hóa đơn: ");
                while (true) {
                    System.out.print("nhập mã sản : ");
                    int maSP = in.nextInt();
                    System.out.print("nhập số lượng: ");
                    int soLuong = in.nextInt();
                    tongTien += soLuong*(danhSachSanPham.tim(maSP).getDonGia());
                    System.out.println("(1) thanh toan");
                    System.out.println("(0) tiep tuc");
                    if (in.nextInt() == 1)
                        break;
                }
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
            }
        }
    }

    public void xuatHoaDon() {
        System.out.printf("|%-20d|%-20d|%-20d|%-20s|%-20f|%-20f|", maHD, maKH, maNV, phuongThucTinhToan, tienTra, tienThua);
    }

    // Phương thức tính tổng tiền từ chi tiết hóa đơn nhập vào
//    public float tinhTongTien() {
//        float tongTien = 0;
//        System.out.print("Nhập số lượng mặt hàng: ");
//        int soLuongMatHang = in.nextInt();
//
//        //Nhập từng chi tiết hóa đơn và tính tổng tiền
//        for(int i = 0; i < soLuongMatHang; i++) {
//            System.out.println("-Nhập chi tiết hóa đơn thứ " + (i + 1) + ":");
//            System.out.print("+Nhập số lượng: ");
//            int soLuong = in.nextInt();
//            System.out.print("+Nhập đơn giá: ");
//            float donGia = in.nextFloat();
//
//            ChiTietHoaDon chiTiet = new ChiTietHoaDon(soLuong, donGia);
//            tongTien += chiTiet.getDonGia() * chiTiet.getSoLuong();  // Cộng dồn tổng tiền
//        }
//        return tongTien;
//    }
}
