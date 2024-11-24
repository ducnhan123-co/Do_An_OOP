package QuanLySieuThiMiNi;

import java.util.Scanner;

import java.time.LocalDate;

public class HoaDon {
    private int maHD;
    private int maKH;
    private int maNV;
    private String ngayTaoHoaDon;
    private float tongTien=0;
    private String phuongThucTinhToan;
    private float tienTra;
    private float tienThua;

    public HoaDon(){}

    public HoaDon(int maHD, int maKH, int maNV, String ngayTaoHoaDon, float tongtien, String phuongThucTinhToan, float tienTra, float tienThua) {
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

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
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

    public void setTienThua() {
        this.tienThua = tienTra - tongTien;
    }

    // Hàm xuất hóa đơn
    public void xuatHoaDon() {
        System.out.println("Mã hóa đơn: "+this.maHD);
        System.out.println("Mã khách hàng: "+this.maKH);
        System.out.println("Mã nhân viên: "+this.maNV);
        System.out.println("Ngày lập hóa đơn: "+ngayTaoHoaDon);
        System.out.println("Tổng : "+tongTien);
        System.out.println("Phương thức thanh toán: "+phuongThucTinhToan);
        System.out.println("Tiền trả: "+tienTra+"đ");
        System.out.println("Tiền thừa: "+tienThua+"đ");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void suaHoaDon(DanhSachSanPham danhSachSanPham) {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Chọn thông tin muốn sửa đổi:");
                System.out.println("1. Mã khách hàng");
                System.out.println("2. Mã nhân viên");
                System.out.println("3. Ngày tạo hóa đơn");
                System.out.println("4. Phương thức thanh toán");
                System.out.println("5. Tổng tiền");
                System.out.println("6. Tiền trả");
                System.out.println("7. Tiền thừa");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn: ");
                int choice = in.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Nhập mã khách hàng mới: ");
                        this.maKH = in.nextInt();
                        break;
                    case 2:
                        System.out.print("Nhập mã nhân viên mới: ");
                        this.maNV = in.nextInt();
                        break;
                    case 3:
                        System.out.print("Nhập ngày tạo hóa đơn mới (dd-MM-YYYY): ");
                        this.ngayTaoHoaDon = in.nextLine();
                        break;
                    case 4:
                        System.out.print("Nhập phương thức thanh toán mới: ");
                        in.nextLine(); // Đọc bỏ newline sau khi đọc int
                        this.phuongThucTinhToan = in.nextLine();
                        break;
                    case 5:
                        // Cập nhật tổng tiền, bạn có thể tính lại tổng tiền từ chi tiết hóa đơn nếu cần
                        this.tongTien = 0; // Reset tổng tiền trước khi tính lại
                        System.out.println("Nhập chi tiết hóa đơn: ");
                        while (true) {
                            System.out.print("Nhập mã sản phẩm: ");
                            int maSP = in.nextInt();
                            System.out.print("Nhập số lượng: ");
                            int soLuong = in.nextInt();
                            // Tính lại tổng tiền từ danh sách sản phẩm
                            tongTien += soLuong * (danhSachSanPham.timSanPhamTheoMa(maSP).getDonGia());
                            System.out.println("(1) Thanh toán");
                            System.out.println("(0) Tiếp tục");
                            if (in.nextInt() == 1)
                                break;
                        }
                        System.out.printf("Tổng tiền mới: %.2f\n", this.tongTien);
                        break;
                    case 6:
                        System.out.print("Nhập tiền trả mới: ");
                        this.tienTra = in.nextFloat();
                        break;
                    case 7:
                        this.tienThua = this.tienTra - this.tongTien;
                        System.out.printf("Tiền thừa mới: %.2f\n", this.tienThua);
                        break;
                    case 0:
                        System.out.println("Đã thoát khỏi sửa hóa đơn.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                }
            }catch(Exception e) {
                System.out.println("Có lỗi xảy ra, vui lòng nhập lại!");
                // Đọc bỏ input không hợp lệ
            }
        }
    }
}