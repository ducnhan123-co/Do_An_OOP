package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLySanPham {
    DanhSachSanPham dsSanPham = new DanhSachSanPham();
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
            System.out.println("║ 5. Thống kê sản phẩm                                                        ║");
            System.out.println("║ 6. Xuất danh sách sản phẩm                                                  ║");
            System.out.println("║ 7. Xuất danh sách gia dụng                                                  ║");
            System.out.println("║ 8. Xuất danh sách thực phẩm                                                 ║");
            System.out.println("║ 9. Cập nhật số lượng danh sách sản phẩm                                     ║");
            System.out.println("║ 0. Thoát                                                                    ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng trống còn sót

            switch (choice) {
                case 1 -> {
                    boolean quaylaimenu=true;
                    while (quaylaimenu) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔════════════════════════════-THÊM SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Thêm một sản phẩm                                                  ║");
                        System.out.println("║ 2. Thêm nhiều sản phẩm                                                ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                             ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            case 1 -> dsSanPham.themGiaDungHayThucPham(); // Thêm một sản phẩm
//            case 2 -> dsSanPham.themNhieuSanPham(); // Thêm nhiều sản phẩm
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                              quaylaimenu=false;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 2 -> {
                    while (true) { // Vòng lặp để giữ menu hiện tại
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
                                return; // Thoát khỏi case 2
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 3 -> {
                    while (true) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔════════════════════════════-XÓA SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Xóa sản phẩm theo mã                                              ║");
                        System.out.println("║ 2. Xóa sản phẩm theo tên                                             ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            // case 1 -> dsSanPham.xoaSanPhamTheoMa(); // Xóa sản phẩm theo mã
                            // case 2 -> dsSanPham.xoaSanPhamTheoTen(); // Xóa sản phẩm theo tên
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                                return; // Thoát khỏi case 3
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 4 -> {
                    while (true) { // Vòng lặp để giữ menu hiện tại
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
                                return; // Thoát khỏi case 4
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 5 -> {
                    while (true) { // Vòng lặp để giữ menu hiện tại
                        System.out.println("╔═════════════════════════════-THỐNG KÊ SẢN PHẨM-═════════════════════════════╗");
                        System.out.println("║ 1. Thống kê sản phẩm theo thương hiệu                                       ║");
                        System.out.println("║ 2. Thống kê sản phẩm theo năm sản xuất                                      ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                                   ║");
                        System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();
                        switch (subChoice) {
                            // case 1 -> dsSanPham.thongKeTheoThuongHieu(); // Thống kê theo thương hiệu
                            // case 2 -> dsSanPham.thongKeTheoNamSanXuat(); // Thống kê theo năm sản xuất
                            case 0 -> { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
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
                case 0 -> {
                    System.out.println("Đã thoát chương trình quản lý sản phẩm.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }
}