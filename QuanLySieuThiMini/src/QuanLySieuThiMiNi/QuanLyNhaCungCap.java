package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyNhaCungCap {
    private DanhSachNhaCungCap ds;

    public QuanLyNhaCungCap(int maxNhaCungCap) {
        ds = new DanhSachNhaCungCap();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("╔═══════════════════════-MENU QUẢN LÝ NHÀ CUNG CẤP-══════════════════════╗");
            System.out.println("║1.  Thêm nhà cung cấp theo tùy chọn.                                  ║");
            System.out.println("║2.  Sửa thông tin nhà cung cấp theo mã.                               ║");
            System.out.println("║3.  Xóa nhà cung cấp theo mã.                                         ║");
            System.out.println("║4.  Tìm kiếm nhà cung cấp theo tùy chọn.                              ║");
            System.out.println("║5.  Thống kê nhà cung cấp theo tùy chọn.                              ║");
            System.out.println("║6.  Cập nhật danh sách nhà cung cấp hiện tại.                         ║");
            System.out.println("║7.  Lấy thông tin từ file.                                           ║");
            System.out.println("║8.  Tìm nhà cung cấp nâng cao với địa chỉ do người dùng chọn.         ║");
            System.out.println("║9.  In danh sách nhà cung cấp.                                        ║");
            System.out.println("║0.  Thoát.                                                           ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
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
                            System.out.println("====== THÊM NHÀ CUNG CẤP ======");
                            System.out.println("╔══════════════════════════════╗");
                            System.out.println("║ 1. Thêm 1 nhà cung cấp.      ║");
                            System.out.println("║ 2. Thêm danh sách nhà cung cấp. ║");
                            System.out.println("║ 0. Thoát.                    ║");
                            System.out.println("╚══════════════════════════════╝");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc bỏ ký tự xuống dòng còn sót lại sau khi nhập số

                            if (luaChon == 0) {
                                System.out.println("Thoát thêm nhà cung cấp.");
                                break;
                            } else if (luaChon == 1) {
                                ds.them(); // Gọi phương thức thêm 1 nhà cung cấp
                            } else if (luaChon == 2) {
//                                ds.themDanhSachNhaCungCap(); // Gọi phương thức thêm danh sách nhà cung cấp
                            } else {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                            sc.nextLine(); // Đọc bỏ dữ liệu lỗi để tránh vòng lặp vô hạn
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Nhập mã nhà cung cấp cần sửa: ");
                    int maSua = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ dòng dư
                    ds.sua(maSua); // Gọi phương thức sửa thông tin nhà cung cấp theo mã
                }
                case 3 -> {
                    System.out.print("Nhập mã nhà cung cấp cần xóa: ");
                    int maXoa = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ dòng dư
                    ds.xoa(maXoa); // Gọi phương thức xóa nhà cung cấp theo mã
                }
                case 4 -> {
                    while (true) {
                        System.out.println("====== TÌM KIẾM NHÀ CUNG CẤP ======");
                        System.out.println("╔══════════════════════════════╗");
                        System.out.println("║ 1. Tìm kiếm theo mã nhà cung cấp. ║");
                        System.out.println("║ 2. Tìm kiếm theo tên nhà cung cấp. ║");
                        System.out.println("║ 3. Tìm kiếm theo địa chỉ nhà cung cấp. ║");
                        System.out.println("║ 0. Thoát.                      ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        int luaChon = sc.nextInt();
                        sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại

                        switch (luaChon) {
                            case 1 -> {
                                // Tìm kiếm nhà cung cấp theo mã
                                System.out.print("Nhập mã nhà cung cấp cần tìm: ");
                                int maTim = sc.nextInt();
                                sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại
//                                NhaCungCap nhaCungCapTim = ds.timKiemNhaCungCapTheoMa(maTim);
//                                if (nhaCungCapTim != null) {
//                                    nhaCungCapTim.xuat();
//                                } else {
////                                    System.out.println("Không tìm thấy nhà cung cấp với mã " + maTim);
//                                }
                            }
                            case 2 -> {
                                // Tìm kiếm nhà cung cấp theo tên
                                System.out.print("Nhập tên nhà cung cấp cần tìm: ");
                                String tenTim = sc.nextLine().trim();
//                                ds.timKiemNhaCungCapTheoTen(tenTim);
                            }
                            case 3 -> {
                                // Tìm kiếm nhà cung cấp theo địa chỉ
                                System.out.print("Nhập địa chỉ nhà cung cấp cần tìm: ");
                                String diaChiTim = sc.nextLine().trim();
//                                ds.timKiemNhaCungCapTheoDiaChi(diaChiTim);
                            }
                            case 0 -> {
                                System.out.println("Thoát tìm kiếm nhà cung cấp.");
                                break;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                    }
                }
//                case 5 -> ds.thongKe(); // Thống kê nhà cung cấp theo tùy chọn
//                case 6 -> ds.capNhatDanhSachNhaCungCap(); // Cập nhật danh sách nhà cung cấp hiện tại
//                case 7 -> ds.docFile(); // Đọc thông tin nhà cung cấp từ file
//                case 8 -> ds.timNhaCungCapNangCao(); // Tìm nhà cung cấp nâng cao với địa chỉ
//                case 9 -> ds.xuatDanhSachNhaCungCap(); // In danh sách nhà cung cấp
                case 0 -> {
                    System.out.println("Đã thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
