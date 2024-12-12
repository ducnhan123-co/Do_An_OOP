package QuanLySieuThiMiNi.KhachHang;

import QuanLySieuThiMiNi.HoaDon.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyKhachHang {
    public DanhSachKhachHang ds;

    public QuanLyKhachHang(int maxKhachHang) {
     this.ds = new DanhSachKhachHang();
//        ds.docFile();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("╔═══════════════════════-MENU QUẢN LÝ KHÁCH HÀNG-══════════════════════╗");
            System.out.println("║ 1.  Thêm khách hàng theo tùy chọn.                                   ║");
            System.out.println("║ 2.  Sửa thông tin khách hàng theo mã.                                ║");
            System.out.println("║ 3.  Xóa khách hàng theo mã.                                          ║");
            System.out.println("║ 4.  Tìm kiếm khách hàng theo tùy chọn.                               ║");
            System.out.println("║ 5.  Thống kê khách hàng theo tùy chọn.                               ║");
            System.out.println("║ 6.  Cập nhật danh sách khách hàng hiện tại.                          ║");
            System.out.println("║ 7.  Tìm khách hàng nâng cao với địa chỉ do người dùng chọn.          ║");
            System.out.println("║ 8.  In danh sách khách hàng.                                         ║");
            System.out.println("║ 0.  Thoát.                                                           ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");

            int choice = -1; // Khởi tạo giá trị mặc định không hợp lệ

            // Lặp lại cho đến khi người dùng nhập lựa chọn hợp lệ
            while (choice < 0 || choice > 9) {
                try {
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
                case 1: {
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
                case 2 : ds.suaKhachHangTheoMa(); // Sửa thông tin khách hàng theo mã
                case 3 : {
                    while (true) {
                        try {
                            System.out.print("Nhập mã khách hàng cần xóa: ");
                            int maXoa = sc.nextInt(); // Kiểm tra nhập đúng số nguyên
                            sc.nextLine(); // Xóa ký tự xuống dòng
                            ds.xoa(maXoa);
                            break; // Thoát vòng lặp nếu không có lỗi
                        } catch (InputMismatchException e) {
                            System.out.println("Mã khách hàng không hợp lệ. Vui lòng nhập lại một số nguyên.");
                            sc.nextLine(); // Xóa input lỗi
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                        }
                    }
                }

                case 4 : {
                    boolean tiepTucTimKiem = true; // Biến để kiểm soát vòng lặp tìm kiếm
                    while (tiepTucTimKiem) {
                        System.out.println("======= TÌM KIẾM KHÁCH HÀNG =======");
                        System.out.println("╔══════════════════════════════════╗");
                        System.out.println("║ 1. Tìm kiếm theo mã khách hàng.  ║");
                        System.out.println("║ 2. Tìm kiếm theo họ khách hàng.  ║");
                        System.out.println("║ 3. Tìm kiếm theo tên khách hàng. ║");
                        System.out.println("║ 0. Thoát.                        ║");
                        System.out.println("╚══════════════════════════════════╝");
                        System.out.print("Nhập lựa chọn của bạn: ");

                        int luaChon;
                        try {
                            luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại
                        } catch (InputMismatchException e) {
                            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
                            sc.nextLine(); // Đọc bỏ dữ liệu không hợp lệ
                            continue; // Quay lại vòng lặp
                        }

                        switch (luaChon) {
                            case 1 : {
                                // Tìm kiếm khách hàng theo mã
                                System.out.print("Nhập mã khách hàng cần tìm: ");
                                try {
                                    int maTim = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại
                                    KhachHang khachHangTim = ds.timKiemKhachHangTheoMa(maTim);
                                    if (khachHangTim != null) {
                                        System.out.println("Thông tin khách hàng tìm thấy với mã: " + maTim);
                                        khachHangTim.xuat();
                                    } else {
                                        System.out.println("Không tìm thấy khách hàng với mã: " + maTim);
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
                                    sc.nextLine(); // Đọc bỏ dữ liệu không hợp lệ
                                }
                            }
                            case 2 : ds.timKiemKhachHangTheoHo(); // Tìm kiếm theo họ
                            case 3 : ds.timKiemKhachHangTheoTen(); // Tìm kiếm theo tên
                            case 0 : {
                                System.out.println("Thoát tìm kiếm khách hàng.");
                                tiepTucTimKiem = false; // Thoát vòng lặp tìm kiếm
                            }
                            default : System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                    }
                }


                case 5 : {
                    boolean tiepTucTimThongKe = true; // Biến để kiểm soát vòng lặp tìm kiếm
                    while (tiepTucTimThongKe) {
                        System.out.println("====== THỐNG KÊ KHÁCH HÀNG ======");
                        System.out.println("╔════════════════════════════════════════════╗");
                        System.out.println("║ 1. Thống kê theo giới tính                 ║");
                        System.out.println("║ 2. Thống kê theo tuổi                      ║");
                        System.out.println("║ 3. Thống kê khách hàng mua nhiều đơn hàng  ║");
                        System.out.println("║    nhất theo quý.                          ║");
                        System.out.println("║ 0. Quay lại                                ║");
                        System.out.println("╚════════════════════════════════════════════╝");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        int luaChon = sc.nextInt();
                        sc.nextLine();

                        switch (luaChon) {
                            case 1 : ds.thongKeTheoGioiTinh();
                            case 2 : ds.thongKeTheoTuoi();
                            case 3 : {
                                DanhSachHoaDon dsHoaDon = new DanhSachHoaDon();
                                dsHoaDon.docFile();
                                ds.thongKeDonHangTheoQuy();
                            }
                            case 0 : {
                                System.out.println("Quay lại menu khách hàng.");
                                tiepTucTimThongKe=false;
                            }
                            default : System.out.println("Lựa chọn không hợp lệ.");
                        }
                    }
                }

                case 6: ds.capNhapSoLuongKhachHang(); // Cập nhật danh sách khách hàng hiện tại

                case 7: ds.timKiemKhachHangNangCao(); // Tìm kiếm nâng cao với địa chỉ
                case 8 : ds.xuatDanhSachKhachHang(); // In danh sách khách hàng
                case 0:{
                    System.out.println("Đã thoát chương trình.");
                    return;
                }
                default : System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
