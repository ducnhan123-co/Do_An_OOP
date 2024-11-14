package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

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

    public void setTienThua(float tienThua) {
        this.tienThua = tienThua;
    }

    // Không biết có dùng được DanhSachSanPham ko nhỉ ?
    DanhSachSanPham danhSachSanPham;
    public void nhapHoaDon() { // Ko nên truyền tham số 
        while(true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Nhập mã hóa đơn: ");
                this.maHD = in.nextInt(); 
                System.out.println("Nhập mã khách hàng: ");
                this.maKH = in.nextInt();
                
                // Nhập ngày lập hóa đơn
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.ngayTaoHoaDon = LocalDate.now().format(formatter);
                in.nextLine();
                while (true) {
                    System.out.print("Nhập ngày lập hóa đơn (dd/MM/yyyy): ");
                    in.nextLine(); // Clear buffer
                    String ngayNhapStr = in.nextLine();
                    try{
                        LocalDate.parse(ngayNhapStr, formatter);
                        this.ngayTaoHoaDon = ngayNhapStr;
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
                    }
                }
            
                System.out.print("Nhập phương thức tính toán: ");
                this.phuongThucTinhToan = in.nextLine();

                System.out.println("-Nhập chi tiết hóa đơn: ");
                while(true) {
                    System.out.print("+Nhập mã sản phẩm: ");
                    int maSP = in.nextInt();
                    
                    float donGia = danhSachSanPham.timDonGiaTheoMa(maSP);
                    if(donGia == -1) {
                        System.out.println("Sản phẩm không tồn tại!");
                        continue;
                    }
                    System.out.print("+Nhập số lượng: ");
                    int soLuong = in.nextInt();
                    
                    // Cách 1:
                    tongTien += soLuong*(danhSachSanPham.timSanPhamTheoMa(maSP).getDonGia());
                    // Cách 2:
                    // tongTien += soLuong*(danhSachSanPham.timDonGiaTheoMa(maSP));

                    System.out.println("(1) Thanh toán");
                    System.out.println("(0) Tiếp tục");
                    if (in.nextInt() == 1)
                        break;
                }
                System.out.printf("Tổng tiền: %.2f\n", this.tongTien);

                // Nhập tiền trả và tiền thừa
                System.out.print("Nhập tiền trả: ");
                this.tienTra = in.nextFloat();
                // this.tienThua = this.tienTra - tongTien;  // Tiền thừa = tiền trả - tổng tiền
                tinhTienThua();
                System.out.printf("Tiền thừa: %.2f\n", tienThua);
                break;
            } catch (Exception e) {
                System.out.println("Hãy nhập lại!");
            }
        }
    }

    public static int chiTietCount;
    private ChiTietHoaDon[] chiTietArray;
    
    public void tinhTienThua() {
        tienThua = tienTra - tongTien;
    }

    // Hàm xuất hóa đơn
    public void xuatHoaDon() {
        System.out.println("Mã hóa đơn: "+this.maHD);
        System.out.println("Mã khách hàng: "+this.maKH);
        System.out.println("Mã nhân viên: "+this.maNV);
        System.out.println("Ngày lập hóa đơn: "+ngayTaoHoaDon);
        System.out.println("Phương thức thanh toán: "+phuongThucTinhToan);
        System.out.println("Tiền trả: "+tienTra+"đ");
        
        // Kiểm tra danh sách chi tiết hóa đơn
        if(chiTietCount == 0) {
            System.out.println("Chưa có sản phẩm trong hóa đơn.");
        }else {
            System.out.println("STT | Mã HD | Mã SP | Số Lượng | Đơn Giá | Thành Tiền |");
            for(int i = 0; i < chiTietCount; i++) {
                System.out.printf("|%-4d | %-6d | %-9d | %-8.2f | %-11.2f |\n", i+1, chiTietArray[i].getMaSP(), chiTietArray[i].getSoLuong(), chiTietArray[i].getDonGia(), chiTietArray[i].tinhThanhTien());
            }
        } 
        tinhTienThua();
        if(tienThua >= 0) {
            System.out.println("Tiền thừa: "+tienThua+"đ");
        }
        else {
            System.out.println("Khách hàng thiếu: "+(-tienThua)+"đ");
        }
        System.out.println("-------------------------------------------------------");
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate ngay = LocalDate.parse(this.ngayTaoHoaDon, formatter);
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