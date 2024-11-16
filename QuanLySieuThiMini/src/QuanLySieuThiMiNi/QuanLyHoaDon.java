package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Scanner;

public class QuanLyHoaDon {
    private DanhSachSanPham danhSachSanPham = new DanhSachSanPham();
    private DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();
    private DanhSachHoaDonChiTiet danhSachHoaDonChiTiet = new DanhSachHoaDonChiTiet();
    public QuanLyHoaDon() {
        danhSachSanPham.init();
    }
    Scanner sc = new Scanner(System.in);

    public void nhapHoaDon() {
        HoaDon hoaDon = new HoaDon();
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Nhập mã hóa đơn");
                hoaDon.setMaHD(in.nextInt());
                System.out.print("Nhập mã Khách hàng ");
                hoaDon.setMaKH(in.nextInt());
                System.out.print("Nhập mã nhân viên ");
                hoaDon.setMaNV(in.nextInt());
                LocalDate localDate = LocalDate.now();
                String b = String.format("%d-%d-%d", localDate.getDayOfMonth(), localDate.getMonth().getValue(), localDate.getYear());
                hoaDon.setNgayTaoHoaDon(b);
                float TongTien = 0;
                while(true) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHD(hoaDon.getMaHD());
                    System.out.println("Nhập mã sản phẩm:");
                    chiTietHoaDon.setMaSP(in.nextInt());
                    System.out.println("Nhập số lượng:");
                    chiTietHoaDon.setSoLuong(in.nextInt());
                    chiTietHoaDon.setDonGia(
                            danhSachSanPham.timDonGiaTheoMa(chiTietHoaDon.getMaSP()) * chiTietHoaDon.getSoLuong()
                    );
                    TongTien += chiTietHoaDon.getDonGia();
                    danhSachHoaDonChiTiet.push(chiTietHoaDon);
                    System.out.print("1 - Thanh toán");
                    System.out.print("2 - Tiếp tục nhập sản phẩm");
                    if (in.nextInt() == 1)
                        break;
                }
                hoaDon.setTongTien(TongTien);
                System.out.println("Nhập phương thức thanh toán");
                in.nextLine();
                hoaDon.setPhuongThucTinhToan(in.nextLine());
                System.out.println("Nhập tiền khách trả:");
                hoaDon.setTienTra(in.nextFloat());
                hoaDon.setTienThua();
                danhSachHoaDon.push(hoaDon);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void xuatHoaDon(int maHD) {
        danhSachHoaDon.xuatHoaDonTheoMa(maHD);
        danhSachHoaDonChiTiet.xuatChiTietHoaDonTheoMHD(maHD);
    }

    public static void main(String[] args) {
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
        quanLyHoaDon.nhapHoaDon();
        quanLyHoaDon.xuatHoaDon(1);
    }
//
//    public void menuDSHD() {
//        while (true) {
//            System.out.println("------------------------------MENU HÓA ĐƠN-------------------------------");
//            System.out.println("1. Thêm 1 hóa đơn vào danh sách");
//            System.out.println("2. Thêm danh sách hóa đơn");
//            System.out.println("3. Sửa hóa đơn");
//            System.out.println("4. Xóa hóa đơn từ cuối danh sách");
//            System.out.println("5. Xóa hóa đơn theo mã");
//            System.out.println("6. Xóa hóa đơn theo ngày tạo hóa đơn");
//            System.out.println("7. Tìm kiếm hóa đơn theo mã");
//            System.out.println("8. Tìm kiếm các hóa đơn theo ngày tạo hóa đơn");
//            System.out.println("9. Tìm kiếm các hóa đơn theo tổng tiền nhập");
//            System.out.println("10. Liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập");
//            System.out.println("11. Liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập");
//            System.out.println("12. Thống kê hóa đơn theo tổng tiền");
//            System.out.println("13. Xem danh sách hóa đơn");
//            System.out.println("0. Thoát");
//            System.out.println("-------------------------------------------------------------------------");
//            System.out.print("Chọn chức năng: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // Bỏ qua dòng trống
//
//            // try {
//            //     choice = Integer.parseInt(sc.nextLine());
//            // } catch (NumberFormatException e) {
//            //     System.out.println("Vui lòng nhập lại số hợp lệ");
//            //     continue;
//            // }
//
//            switch(choice) {
//                case 1:
//                    ds.themHoaDon();
//                    break;
//                case 2:
//                    ds.nhapDanhSachHoaDon();
//                    break;
//                case 3:
//                    // ds.suaHoaDon();
//                    break;
//                case 4:
//                    ds.delete();
//                    break;
//                case 5:
//                    System.out.print("Nhập mã hóa đơn cần xóa: ");
//                    int maHD = sc.nextInt();
//                    sc.nextLine();
//                    ds.xoaHoaDonTheoMa(maHD);
//                    break;
//                case 6:
//                    System.out.print("Nhập ngày tạo hóa đơn: ");
//                    String ngay = sc.nextLine();
//                    sc.nextLine();
//                    ds.xoaHoaDonTheoNgay(ngay);
//                    break;
//                case 7:
//                    System.out.println("Nhập mã hóa đơn cần tìm: ");
//                    int maTim = sc.nextInt();
//                    sc.nextLine();
//                    HoaDon hoaDonCanTim = ds.timKiemHoaDon(maTim);
//                    if(hoaDonCanTim != null) {
//                        hoaDonCanTim.xuatHoaDon();
//                    } else {
//                        System.out.println("Không tìm thấy hóa đơn có mã "+maTim+"!");
//                    }
//                    break;
//                case 8:
//                    ds.timHoaDonTheoNgayTaoHoaDon();
//                    break;
//                case 9:
//                    System.out.println("Nhập tổng tiền hóa đơn cần tìm: ");
//                    float tong = sc.nextFloat();
//                    HoaDon tongTienTim = ds.timHoaDonTheoTongTien(tong);
//                    if(tongTienTim != null) {
//                        tongTienTim.xuatHoaDon();
//                    } else {
//                        System.out.println("Không tìm thấy hóa đơn có tổng tiền là "+tongTienTim+"đ!");
//                    }
//                    break;
//                case 10:
//                    System.out.println("Nhập giá tiền: ");
//                    float tongTien1 = sc.nextFloat();
//                    ds.lietKeHoaDonTongTienLonHon(tongTien1);
//                    break;
//                case 11:
//                    System.out.println("Nhập giá tiền: ");
//                    float tongTien2 = sc.nextFloat();
//                    ds.lietKeHoaDonTongTienNhoHon(tongTien2);
//                    break;
//                case 12:
//                    ds.thongKeTongTienHoaDon();
//                    break;
//                case 13:
//                    ds.xemDS();
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
//            }
//        }
//    }

}
