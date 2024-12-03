package QuanLySieuThiMiNi.SanPham;

import QuanLySieuThiMiNi.HoaDon.DanhSachHoaDonChiTiet;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachChiTietPhieuNhapHang;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachPhieuNhapHang;

import java.util.Scanner;

public class QuanLySanPham {
    DanhSachSanPham dsSanPham = new DanhSachSanPham();
    DanhSachPhieuNhapHang danhSachPhieuNhapHang = new DanhSachPhieuNhapHang();
    DanhSachChiTietPhieuNhapHang dsChiTietPhieuNhap= new DanhSachChiTietPhieuNhapHang(danhSachPhieuNhapHang);
    DanhSachHoaDonChiTiet dsChiTietHoaDon = new DanhSachHoaDonChiTiet();
    Scanner sc = new Scanner(System.in);

    public QuanLySanPham() {
        dsSanPham.docFile();
    }

    public void menu() {
        // Menu

        while (true) {
            System.out.println("╔═══════════════════════════-MENU QUẢN LÝ SẢN PHẨM-═══════════════════════════╗");
            System.out.println("║ 1. Thêm sản phẩm theo tùy chọn                                              ║");
            System.out.println("║ 2. Sửa sản phẩm theo tùy chọn                                               ║");
            System.out.println("║ 3. Xóa sản phẩm theo tùy chọn                                               ║");
            System.out.println("║ 4. Tìm kiếm sản phẩm theo tùy chọn                                          ║");
            System.out.println("║ 5. Thống kê sản phẩm theo tùy chọn                                          ║");
            System.out.println("║ 6. Xuất danh sách sản phẩm                                                  ║");
            System.out.println("║ 7. Xuất danh sách gia dụng                                                  ║");
            System.out.println("║ 8. Xuất danh sách thực phẩm                                                 ║");
            System.out.println("║ 9. Cập nhật số lượng danh sách sản phẩm tùy chọn                            ║");
            System.out.println("║ 0. Thoát                                                                    ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng trống còn sót

            switch (choice) {
                case 1 -> {
                    boolean quayLaiMenu = true;
                    while (quayLaiMenu) { // Vòng lặp giữ menu con
                        System.out.println("╔════════════════════════════-THÊM SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Thêm sản phẩm gia dụng                                             ║");
                        System.out.println("║ 2. Thêm sản phẩm thực phẩm                                            ║");
                        System.out.println("║ 0. Quay lại menu quản lý                                              ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            case 1 -> {
                                // Nhập thông tin sản phẩm gia dụng
                                SanPham giaDung = new GiaDung();
                                giaDung.nhap(); // Giả định lớp GiaDung có phương thức nhập
                                boolean added = dsSanPham.them1SanPham(giaDung);
                                if (added) {
                                    System.out.println("Thêm sản phẩm gia dụng thành công!");
                                } else {
                                    System.out.println("Thêm sản phẩm thất bại (mã sản phẩm đã tồn tại).");
                                }
                            }
                            case 2 -> {
                                // Nhập thông tin sản phẩm thực phẩm
                                SanPham thucPham = new ThucPham();
                                thucPham.nhap();
                                boolean added = dsSanPham.them1SanPham(thucPham);
                                if (added) {
                                    System.out.println("Thêm sản phẩm thực phẩm thành công!");
                                } else {
                                    System.out.println("Thêm sản phẩm thất bại (mã sản phẩm đã tồn tại).");
                                }
                            }
                            case 0 -> {
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = false;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }


                case 2 -> {
                    boolean quayLaiMenu = false;
                    while (!quayLaiMenu) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔════════════════════════════-SỬA SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Sửa sản phẩm theo mã                                              ║");
                        System.out.println("║ 2. Sửa sản phẩm theo tên                                             ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            // case 1 -> dsSanPham.suaSanPhamTheoMa(); // Sửa sản phẩm theo mã
                            // case 2 -> dsSanPham.suaSanPhamTheoTen(); // Sửa sản phẩm theo tên
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = true;
                                break;// Thoát khỏi case 2
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }    
                }

                case 3 -> {
                    boolean quayLaiMenu = false;
                    while (!quayLaiMenu) {
                        System.out.println("╔════════════════════════════-XÓA SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Xóa sản phẩm theo mã                                              ║");
                        System.out.println("║ 2. Xóa sản phẩm theo tên                                             ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
//                            case 1 -> {
//                                System.out.print("Nhập mã sản phẩm cần xóa: ");
//                                int maSP = sc.nextInt();
//                                sc.nextLine();
//                                boolean xoaThanhCong = dsSanPham.xoaSanPhamTheoMa(maSP);
//                                if (xoaThanhCong) {
//                                    System.out.println("Xóa sản phẩm thành công.");
//                                } else {
//                                    System.out.println("Không tìm thấy sản phẩm với mã: " + maSP);
//                                }
//                            }
//                            case 2 -> {
//                                System.out.print("Nhập tên sản phẩm cần xóa: ");
//                                String tenSP = sc.nextLine();
//                                boolean xoaThanhCong = dsSanPham.xoaSanPhamTheoTen(tenSP);
//                                if (xoaThanhCong) {
//                                    System.out.println("Xóa sản phẩm thành công.");
//                                } else {
//                                    System.out.println("Không tìm thấy sản phẩm có tên: " + tenSP);
//                                }
//                            }
                            case 0 -> {
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = false;
                                break;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }


                case 4 -> {
                    boolean quayLaiMenu = true;
                    while (!quayLaiMenu) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔═══════════════════════════-TÌM KIẾM SẢN PHẨM-════════════════════════╗");
                        System.out.println("║ 1. Tìm kiếm sản phẩm theo mã                                         ║");
                        System.out.println("║ 2. Tìm kiếm sản phẩm theo tên                                        ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            case 1 -> { // Tìm kiếm sản phẩm theo mã
                                System.out.print("Nhập mã sản phẩm cần tìm: ");
                                int maSanPham = sc.nextInt();
                                sc.nextLine();
                                dsSanPham.timSanPhamTheoMa(maSanPham);
                            }
//            case 2 -> dsSanPham.timSanPhamTheoTen(); // Tìm kiếm sản phẩm theo tên
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = true;
                                return; // Thoát khỏi case 4
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 5 -> {
                    boolean quayLaiMenu = false;
                    while (!quayLaiMenu) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔═════════════════════════════-THỐNG KÊ SẢN PHẨM-═════════════════════════════╗");
                        System.out.println("║ 1. Thống kê sản phẩm theo tên loại/thương hiệu                              ║");
                        System.out.println("║ 2. Thống kê sản phẩm theo ngày sản xuất                                     ║");
                        System.out.println("║ 3. Thống kê sản phẩm theo thực phẩm                                         ║");
                        System.out.println("║ 4. Thống kê sản phẩm theo gia dụng                                          ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                                   ║");
                        System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();
                        switch (subChoice) {
                            case 1 -> {
                                dsSanPham.thongKeSanPhamTheoTenLoaiORTenThuongHieu();;
                                break;
                            }
                            case 2 -> {
                                dsSanPham.thongKeSanPhamTheoNgaySanXuat();
                                break;
                            }
                            case 3 -> {
                                dsSanPham.thongKeSanPhamTheoThucPham();
                                break;
                            }
                            case 4 -> {
                                dsSanPham.thongKeSanPhamTheoGiaDung();
                                break;
                            }
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = true;
                                return; // Thoát khỏi case 5
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 6 -> dsSanPham.xuatDanhSach(); // Xuất danh sách sản phẩm
                case 7 -> dsSanPham.xuatDanhSachGiaDung(); // Xuất danh sách gia dụng
                case 8 -> dsSanPham.xuatDanhSachThucPham(); // Xuất danh sách thực phẩm
//                case 9 -> dsSanPham.capNhatSoLuong(); // Cập nhật số lượng sản phẩm
                case 9 -> {
                    System.out.println("╔═══════════════════════════-CẬP NHẬT SỐ LƯỢNG-══════════════════════════╗");
                    System.out.println("║ 1. Nhập hàng                                                           ║");
                    System.out.println("║ 2. Bán hàng                                                            ║");
                    System.out.println("║ 0. Quay lại menu quản lý                                               ║");
                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
                    System.out.print("Chọn chức năng: ");
                    int subChoice = sc.nextInt();
                    sc.nextLine();

                    switch (subChoice) {
                        case 1 -> {
                            System.out.print("Nhập mã sản phẩm: ");
                            int maSPNhap = sc.nextInt();
                            System.out.print("Nhập số lượng nhập: ");
                            int soLuongNhap = sc.nextInt();
                            System.out.print("Nhập mã phiếu nhập: ");
                            int maPhieu = sc.nextInt();
                            dsSanPham.nhapHang(dsSanPham,dsChiTietPhieuNhap , maSPNhap, soLuongNhap, maPhieu);
                            break;
                        }
                        case 2 -> {
                        	dsSanPham.xemHangNhapBanVaTonKho(dsChiTietPhieuNhap, dsChiTietHoaDon);
                            break;
                        }
                        case 0 -> {
                            System.out.println("Quay lại menu quản lý...");
                        }
                        default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    }
                }

                case 0 -> {
                    System.out.println("Đã thoát chương trình quản lý sản phẩm.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }
    public static void main(String[] args) {
        QuanLySanPham qlsp = new QuanLySanPham();
        qlsp.menu();
    }
}