package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyKhachHang {
    private DanhSachKhachHang ds;

    public QuanLyKhachHang() {
    }

    public QuanLyKhachHang(int maxKhachHang) {
        ds = new DanhSachKhachHang();
//        ds.docFile();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("╔═══════════════════════-MENU QUẢN LÝ KHÁCH HÀNG-══════════════════════╗");
            System.out.println("║1.  Thêm khách hàng theo tùy chọn.                                    ║");
            System.out.println("║2.  Sửa thông tin khách hàng theo mã.                                 ║");
            System.out.println("║3.  Xóa khách hàng theo mã.                                           ║");
            System.out.println("║4.  Tìm kiếm khách hàng theo tùy chọn.                                ║");
            System.out.println("║5.  Thống kê khách hàng theo tùy chọn.                                ║");
            System.out.println("║6.  Cập nhật danh sách khách hàng hiện tại.                           ║");
            System.out.println("║7.  Lấy thông tin từ file.                                            ║");
            System.out.println("║8.  Tìm khách hàng nâng cao với địa chỉ do người dùng chọn.           ║");
            System.out.println("║9.  In danh sách khách hàng.                                          ║");
            System.out.println("║0.  Thoát.                                                            ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");

            int choice = -1; // Khởi tạo giá trị mặc định không hợp lệ

            // Lặp lại cho đến khi người dùng nhập lựa chọn hợp lệ
            while (choice < 0 || choice > 9) {
                try {
                    System.out.print("Chọn chức năng: ");
                    choice = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ dòng trống

                    if (choice < 0 || choice > 9) {
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
                    sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh vòng lặp vô hạn
                }
            }

            switch (choice) {
                case 1 -> {
                    while (true) {
                        try {
                            System.out.println("====== THÊM KHÁCH HÀNG ======");
                            System.out.println("╔══════════════════════════════╗");
                            System.out.println("║ 1. Thêm 1 khách hàng.        ║");
                            System.out.println("║ 2. Thêm danh sách khách hàng.║");
                            System.out.println("║ 0. Thoát.                    ║");
                            System.out.println("╚══════════════════════════════╝");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc bỏ ký tự xuống dòng còn sót lại sau khi nhập số

                            if (luaChon == 0) {
                                System.out.println("Thoát thêm khách hàng.");
                                break;
                            } else if (luaChon == 1) {
                                ds.them(); // Gọi phương thức thêm 1 khách hàng
                            } else if (luaChon == 2) {
//                                ds.themDanhSachKhachHang(); // Gọi phương thức thêm danh sách khách hàng
                            } else {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                            sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh vòng lặp vô hạn
                        }
                    }
                }
                case 2 -> ds.sua(1); // Sửa thông tin khách hàng theo mã
                case 3 -> {
                    System.out.print("Nhập mã khách hàng cần xóa: ");
                    int maXoa = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ dòng dư
                    ds.xoa(maXoa); // Gọi phương thức xóa khách hàng theo mã
                }
                case 4 -> {
                    while (true) {
                        System.out.println("====== TÌM KIẾM KHÁCH HÀNG ======");
                        System.out.println("╔══════════════════════════════════╗");
                        System.out.println("║ 1. Tìm kiếm theo mã khách hàng.  ║");
                        System.out.println("║ 2. Tìm kiếm theo họ khách hàng.  ║");
                        System.out.println("║ 3. Tìm kiếm theo tên khách hàng. ║");
                        System.out.println("║ 0. Thoát.                        ║");
                        System.out.println("╚══════════════════════════════════╝");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        int luaChon = sc.nextInt();
                        sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại

                        switch (luaChon) {
                            case 1 -> {
                                // Tìm kiếm khách hàng theo mã
                                System.out.print("Nhập mã khách hàng cần tìm: ");
                                int maTim = sc.nextInt();
                                sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại
                                KhachHang khachHangTim = ds.timKiemKhachHangTheoMa(maTim);
                                if (khachHangTim != null) {
                                    khachHangTim.xuat();
                                } else {
                                    System.out.println("Không tìm thấy khách hàng với mã " + maTim);
                                }
                            }
                            case 2 -> {
                                // Tìm kiếm khách hàng theo họ
                                System.out.print("Nhập họ khách hàng cần tìm: ");
                                String hoTim = sc.nextLine().trim();
//                                ds.timKiemKhachHangTheoHo(hoTim);
                            }
                            case 3 -> {
                                // Tìm kiếm khách hàng theo tên
                                System.out.print("Nhập tên khách hàng cần tìm: ");
                                String tenTim = sc.nextLine().trim();
//                                ds.timKiemKhachHangTheoTen(tenTim);
                            }
                            case 0 -> {
                                System.out.println("Thoát tìm kiếm khách hàng.");
                                break;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                    }
                }
//                case 5 -> ds.thongKe(); // Thống kê khách hàng theo tùy chọn
//                case 6 -> ds.capNhatDanhSachKhachHang(); // Cập nhật danh sách khách hàng hiện tại
//                case 7 -> ds.docFile(); // Đọc thông tin khách hàng từ file
//                case 8 -> ds.timKiemKhachHangNangCao(); // Tìm kiếm nâng cao với địa chỉ
                case 9 -> ds.xuatDanhSachKhachHang(); // In danh sách khách hàng
                case 0 -> {
                    System.out.println("Đã thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
