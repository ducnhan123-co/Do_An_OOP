package QuanLySieuThiMiNi.SanPham;

import QuanLySieuThiMiNi.HoaDon.DanhSachHoaDonChiTiet;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachChiTietPhieuNhapHang;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachPhieuNhapHang;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLySanPham {
    DanhSachSanPham dsSanPham = new DanhSachSanPham();
    DanhSachPhieuNhapHang danhSachPhieuNhapHang = new DanhSachPhieuNhapHang();
    DanhSachChiTietPhieuNhapHang dsChiTietPhieuNhap= new DanhSachChiTietPhieuNhapHang(danhSachPhieuNhapHang);
    DanhSachHoaDonChiTiet dsChiTietHoaDon = new DanhSachHoaDonChiTiet();
    Scanner sc = new Scanner(System.in);

    public QuanLySanPham() {

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
                case 1 : {
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
                            case 1 : {
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
                            case 2 : {
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
                            case 0 : {
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = false;
                            }
                            default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }


                case 2 : {
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
                             case 1 : dsSanPham.suaSanPhamTheoMa(); // Sửa sản phẩm theo mã
//                             case 2 : dsSanPham.suaSanPhamTheoTen(); // Sửa sản phẩm theo tên
                            case 0 : { // Quay lại menu chính
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = true;
                                break;// Thoát khỏi case 2
                            }
                            default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }    
                }

                case 3 : {
                    boolean quayLaiMenu = true;
                    while (quayLaiMenu) {
                        System.out.println("╔════════════════════════════-XÓA SẢN PHẨM-════════════════════════════╗");
                        System.out.println("║ 1. Xóa sản phẩm theo mã                                              ║");
                        System.out.println("║ 2. Xóa sản phẩm theo tên                                             ║");
                        System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                        System.out.print("Chọn chức năng: ");
                        int subChoice = sc.nextInt();
                        sc.nextLine();

                        switch (subChoice) {
                            case 1 : {
                                System.out.print("Nhập mã sản phẩm cần xóa: ");
                                int maSP = sc.nextInt();
                                sc.nextLine();

                                boolean xoaThanhCong = dsSanPham.xoaSanPhamTheoMa(maSP);
                                if (xoaThanhCong) {
                                    System.out.println("Xóa sản phẩm thành công.");
                                } else {
                                    System.out.println("Không tìm thấy sản phẩm với mã: " + maSP);
                                }
                            }

                            case 2 : {
                                System.out.print("Nhập tên sản phẩm cần xóa: ");
                                String tenSP = sc.nextLine();

                                boolean xoaThanhCong = dsSanPham.xoaSanPhamTheoTen(tenSP);
                                if (xoaThanhCong) {
                                    System.out.println("Xóa sản phẩm thành công.");
                                } else {
                                    System.out.println("Không tìm thấy sản phẩm có tên: " + tenSP);
                                }
                            }

                            case 0 : {
                                System.out.println("Quay lại menu quản lý...");
                                quayLaiMenu = false;

                            }
                            default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                        }
                    }
                }

                case 4 : {
                    boolean quayLaiMenu = true;
                    while (quayLaiMenu) { 
                        try {  
                            System.out.println("╔═══════════════════════════-TÌM KIẾM SẢN PHẨM-════════════════════════╗");
                            System.out.println("║ 1. Tìm kiếm sản phẩm theo mã                                         ║");
                            System.out.println("║ 2. Tìm kiếm sản phẩm theo tên                                        ║");
                            System.out.println("║ 0. Quay lại menu quản lý.                                            ║");
                            System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
                            System.out.print("Chọn chức năng: ");
                            int subChoice = sc.nextInt();
                            sc.nextLine();

                            switch (subChoice) {
                                case 1 : { // Tìm kiếm sản phẩm theo mã
                                    try {
                                        System.out.print("Nhập mã sản phẩm cần tìm: ");
                                        int maSanPham = sc.nextInt();
                                        sc.nextLine();
                                        SanPham sanPham = dsSanPham.timSanPhamTheoMa(maSanPham);

                                        if (sanPham != null) {
                                            System.out.println("Thông tin sản phẩm tìm được:");
                                            sanPham.xuat();
                                        } else {
                                            System.out.println("Không tìm thấy sản phẩm có mã \"" + maSanPham + "\".\n");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Mã sản phẩm phải là số nguyên. Vui lòng nhập lại!\n");
                                        sc.nextLine(); // Đọc bỏ giá trị nhập sai
                                    }
                                }
                                case 2 : { // Tìm kiếm sản phẩm theo tên
                                    try {
                                        dsSanPham.timSanPhamTheoTen();
                                    } catch (Exception e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }
                                case 0 : { // Quay lại menu chính
                                    System.out.println("Quay lại menu quản lý...");
                                    quayLaiMenu = false;
                                }
                                default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 0 đến 2.\n");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Vui lòng nhập số từ 0 đến 2.\n");
                            sc.nextLine(); // Đọc bỏ giá trị nhập sai
                        } catch (Exception e) {
                            System.out.println("Lỗi không xác định: " + e.getMessage());
                        }
                    }
                }


                case 5 : {
                    boolean quayLaiMenu = true;
                    while (quayLaiMenu) { // Vòng lặp để giữ menu hiện tại
                        try {
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
                                case 1 : {
                                    dsSanPham.thongKeSanPhamTheoTenLoaiORTenThuongHieu();;
                                    break;
                                }
                                case 2 : {
                                    dsSanPham.thongKeSanPhamTheoNgaySanXuat();
                                    break;
                                }
                                case 3 : {
                                    dsSanPham.thongKeSanPhamTheoThucPham();
                                    break;
                                }
                                case 4 : {
                                    dsSanPham.thongKeSanPhamTheoGiaDung();
                                    break;
                                }
                                case 0 : { // Quay lại menu chính
                                    System.out.println("Quay lại menu quản lý...");
                                    quayLaiMenu = false;
    
                                }
                                default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 4!\n");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                case 6 : dsSanPham.xuatDanhSach(); // Xuất danh sách sản phẩm
                case 7 : dsSanPham.xuatDanhSachGiaDung(); // Xuất danh sách gia dụng
                case 8 : dsSanPham.xuatDanhSachThucPham(); // Xuất danh sách thực phẩm
//                case 9 : dsSanPham.capNhatSoLuong(); // Cập nhật số lượng sản phẩm
                case 9 : {
                    boolean quayLaiMenu = true;
                    while (quayLaiMenu) {
                        try {
                            System.out.println("╔═══════════════════════════-CẬP NHẬT SỐ LƯỢNG-══════════════════════════╗");
                            System.out.println("║ 1. Nhập hàng                                                           ║");
                            System.out.println("║ 2. Bán hàng                                                            ║");
                            System.out.println("║ 3. Xem danh sách số lượng hiện tại                                     ║");
                            System.out.println("║ 0. Quay lại menu quản lý                                               ║");
                            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
                            System.out.print("Chọn chức năng: ");
                            int subChoice = sc.nextInt();
                            sc.nextLine();
        
                            switch (subChoice) {
                                case 1 : {
                                    dsSanPham.capNhatSoLuongSanPhamNhap();
                                    break;
                                }
                                case 2 : {
                                    dsSanPham.capNhatSoLuongSanPhamBan();
                                    break;
                                }
                                
                                case 3 :{
                                    dsSanPham.xemHangNhapBanVaTonKho(dsChiTietPhieuNhap, dsChiTietHoaDon);
                                    break;
                                }
                                
                                case 0 : {
                                    System.out.println("Quay lại menu quản lý...");
                                    quayLaiMenu = false;
                                }
                                default : System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 3!\n");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                case 0 : {
                    System.out.println("Đã thoát chương trình quản lý sản phẩm.");
                    return;
                }
                default : System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }


}