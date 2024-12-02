package QuanLySieuThiMiNi.NhanVien;

import java.util.Scanner;

public class QuanLyNhanVien {
    public DanhSachNhanVien ds;

    public QuanLyNhanVien(int maxNhanVien) {
        ds = new DanhSachNhanVien(maxNhanVien);
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("╔═══════════════════════-MENU QUẢN LÝ NHÂN VIÊN-══════════════════════╗");
            System.out.println("║1.  Thêm nhân viên theo tùy chọn.                                    ║");
            System.out.println("║2.  Sửa thông tin nhân viên theo mã.                                 ║");
            System.out.println("║3.  Xóa nhân viên theo mã.                                           ║");
            System.out.println("║4.  Tìm kiếm nhân viên theo tùy chọn.                                ║");
            System.out.println("║5.  Thống kê nhân viên theo tùy chọn.                                ║");
            System.out.println("║6.  Cập nhật danh sách nhân viên hiện tại.                           ║");
            System.out.println("║7.  Tìm nhân viên nâng cao với địa chỉ do người dùng chọn.           ║");
            System.out.println("║8.  In danh sách nhân viên.                                          ║");
            System.out.println("║0.  Thoát.                                                           ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");

            int choice = -1; // Khởi tạo giá trị mặc định không hợp lệ

            // Lặp lại cho đến khi người dùng nhập lựa chọn hợp lệ
            while (choice < 0 || choice > 9) {
                try {
//                    System.out.print("Chọn chức năng: ");
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
                            System.out.println("====== THÊM NHÂN VIÊN ======");
                            System.out.println("╔══════════════════════════════╗");
                            System.out.println("║ 1. Thêm 1 nhân viên.         ║");
                            System.out.println("║ 2. Thêm danh sách nhân viên. ║");
                            System.out.println("║ 0. Thoát.                    ║");
                            System.out.println("╚══════════════════════════════╝");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc bỏ ký tự xuống dòng còn sót lại sau khi nhập số

                            if (luaChon == 0) {
                                System.out.println("Thoát thêm nhân viên.");
                                break;
                            } else if (luaChon == 1) {
                                ds.themNhanVien(); // Gọi phương thức thêm 1 nhân viên
                            } else if (luaChon == 2) {
                                ds.themDanhSachNhanVien(); // Gọi phương thức thêm danh sách nhân viên
                            } else {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                            sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh vòng lặp vô hạn
                        }
                    }
                }
                case 2 -> ds.suaNhanVienTheoMa(); // Sửa1 nhân viên theo mã
                case 3 -> {
                    boolean tiepTucXoa = true;
                    while (tiepTucXoa) {
                        System.out.print("Nhập mã nhân viên cần xóa: ");
                        int maXoa = sc.nextInt();
                        sc.nextLine(); // Đọc bỏ dòng dư
                        // Gọi phương thức xóa nhân viên theo mã
                        ds.xoaNhanVienTheoMaCach1(maXoa);
                        // Hỏi người dùng có muốn xóa tiếp không
                        System.out.print("Bạn muốn xóa tiếp không? (y/n): ");
                        String luaChon = sc.nextLine().trim().toLowerCase();
                        if (luaChon.equals("n")) {
                            tiepTucXoa = false; // Dừng lại nếu người dùng chọn 'n'
                        }
                    }
                }
                case 4 -> {
                    while (true) {
                        try {
                            // Hiển thị menu tìm kiếm
                            System.out.println("====== THÊM NHÂN VIÊN ======");
                            System.out.println("╔══════════════════════════════╗");
                            System.out.println("║ 1. Thêm 1 nhân viên.         ║");
                            System.out.println("║ 2. Thêm danh sách nhân viên. ║");
                            System.out.println("║ 0. Thoát.                    ║");
                            System.out.println("╚══════════════════════════════╝");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số
                            if (luaChon == 0) {
                                System.out.println("Thoát tìm kiếm nhân viên.");
                                break;
                            }
                            switch (luaChon) {
                                case 1 -> {
                                    // Tìm kiếm nhân viên theo mã
                                    System.out.print("Nhập mã nhân viên cần tìm: ");
                                    int maTim = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại
                                    NhanVien nhanVienTim = ds.timKiemNhanVienTheoMa(maTim);
                                    if (nhanVienTim != null) {
                                        System.out.println("Thông tin nhân viên với mã " + maTim + ":");
                                        System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                                                "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                                        nhanVienTim.xuatNhanVien();
                                    } else {
                                        System.out.println("Không tìm thấy nhân viên với mã " + maTim);
                                    }
                                }
                                case 2 -> {
                                    // Tìm kiếm nhân viên theo họ
                                    System.out.print("Nhập họ nhân viên cần tìm: ");
                                    String hoTim = sc.nextLine().trim();
                                    NhanVien[] danhSachTimDuoc = ds.timKiemNhanVienTheoHo(hoTim);
                                    if (danhSachTimDuoc.length == 0) {
                                        System.out.println("Không tìm thấy nhân viên nào có họ: " + hoTim);
                                    } else {
                                        System.out.println("Danh sách nhân viên có họ " + hoTim + ":");
                                        System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                                                "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                                        for (NhanVien nhanVien : danhSachTimDuoc) {
                                            if (nhanVien != null) {
                                                nhanVien.xuatNhanVien();
                                            }
                                        }
                                        System.out.println("Tổng cộng: " + danhSachTimDuoc.length + " nhân viên.");
                                    }
                                }
                                case 3 -> {
                                    // Tìm kiếm nhân viên theo tên
                                    System.out.print("Nhập tên nhân viên cần tìm: ");
                                    String tenTim = sc.nextLine().trim();
                                    NhanVien[] danhSachTimDuoc = ds.timKiemNhanVienTheoTen(tenTim);
                                    if (danhSachTimDuoc.length == 0) {
                                        System.out.println("Không tìm thấy nhân viên nào có tên: " + tenTim);
                                    } else {
                                        System.out.println("Danh sách nhân viên có tên " + tenTim + ":");
                                        System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                                                "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                                        for (NhanVien nhanVien : danhSachTimDuoc) {
                                            if (nhanVien != null) {
                                                nhanVien.xuatNhanVien();
                                            }
                                        }
                                        System.out.println("Tổng cộng: " + danhSachTimDuoc.length + " nhân viên.");
                                    }
                                }
                                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                            }
                        } catch (Exception e) {
                            // Xử lý lỗi ngoại lệ
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                            sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh vòng lặp vô hạn
                        }
                    }
                }
                case 5 -> {
                    boolean thongKe=true;
                    while (thongKe) {
                        try {
                            // Hiển thị menu thống kê
                            System.out.println("====== THỐNG KÊ NHÂN VIÊN ======");
                            System.out.println("╔══════════════════════════════════════════╗");
                            System.out.println("║ 1. Thống kê nhân viên theo mức lương.    ║");
                            System.out.println("║ 2. Thống kê nhân viên theo năm làm việc. ║");
                            System.out.println("║ 3. Thống kê nhân viên theo độ tuổi.      ║");
                            System.out.println("║ 0. Thoát.                                ║");
                            System.out.println("╚══════════════════════════════════════════╝");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            switch (luaChon) {
                                case 1 -> ds.thongKeTheoMucLuong(); // Gọi phương thức thống kê theo mức lương
                                case 2 -> {
                                    ds.thongKeTheoNamLamViec(); // Gọi phương thức thống kê theo năm làm việc
                                }
                                case 3 -> {
                                    ds.thongKeTheoTuoi(2024); // Gọi phương thức thống kê theo tuổi
                                }
                                case 0 -> {
                                    System.out.println("Thoát thống kê nhân viên.");
                                    thongKe=false;
                                }
                                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {

                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                            sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh lặp vô hạn
                        }
                    }
                }
                case 6 -> ds.capNhatSoLuongNhanVien(); // Cập nhật số lượng nhân viên hiện tại

                case 7 -> {
                    do {
                        ds.timKiemDiaChiNhanVien(); // Gọi phương thức tìm kiếm địa chỉ nhân viên
                        Scanner scanner = new Scanner(System.in);
                        String tiepTucTimKiem;
                        do {
                            System.out.print("Bạn có muốn tìm kiếm tiếp không? (y/n): ");
                            tiepTucTimKiem = scanner.nextLine().trim().toLowerCase();
                            if (!tiepTucTimKiem.equals("y") && !tiepTucTimKiem.equals("n")) {
                                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                            }
                        } while (!tiepTucTimKiem.equals("y") && !tiepTucTimKiem.equals("n"));

                        // Nếu chọn "n", thoát vòng lặp
                        if (tiepTucTimKiem.equals("n")) {
                            System.out.println("Đã thoát chức năng tìm kiếm.");
                            break;
                        }
                    } while (true);
                }
                case 8 -> {
                    ds.xuatDanhSachNhanVien(); // In danh sách nhân viên
                }
                case 0 -> {
                    System.out.println("Đã thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}