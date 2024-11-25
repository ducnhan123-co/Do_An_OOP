package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyPhieuNhapHang {
    public void menu() {
        Scanner sc = new Scanner(System.in);
        DanhSachPhieuNhapHang DSPNH = new DanhSachPhieuNhapHang();
        DanhSachChiTietPhieuNhapHang DSCtPNH = new DanhSachChiTietPhieuNhapHang(DSPNH); // Truyền danh sách phiếu nhập hàng
        
        while (true) {
            System.out.println("---------------------------------MENU----------------------------------");
            System.out.println("1. Thêm phiếu nhập hàng");
            System.out.println("2. Xuất danh sách phiếu nhập hàng");
            System.out.println("3. Tìm kiếm phiếu nhập hàng");
            System.out.println("4. Sửa thông tin phiếu");
            System.out.println("5. Xoá phiếu nhập hàng");
            System.out.println("6. Thống kê");
            System.out.println("7. Lấy dữ liệu từ data");
            System.out.println("8. Quản lý chi tiết phiếu nhập hàng");
            System.out.println("9. Xuất danh sách toàn bộ ");
            System.out.println("10. Quay Lại Trang Chính");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DSPNH.themPhieuNhapHang();
                    break;
                case 2:
                    DSPNH.xuatPhieuNhapHang();
                    break;
                case 3:
                    DSPNH.timMP();
                    break;
                case 4:
                    DSPNH.suaPhieuNhapHang();
                    break;
                case 5:
                	System.out.print("Nhập mã phiếu cần xóa: ");
                    int maPhieuXoa = sc.nextInt();
                    DSCtPNH.xoaChiTietTheoMaPhieu(maPhieuXoa); // Xóa chi tiết
                    DSPNH.xoaPhieuTheoMaPhieu(maPhieuXoa); // Xóa phiếu
                    break;
                case 6:
                    while (true) {
                        System.out.println("----------------- THỐNG KÊ --------------");
                        System.out.println("1. Thống kê theo ngày");
                        System.out.println("2. Thống kê theo tháng");
                        System.out.println("3. Thống kê theo năm");
                        System.out.println("4. Thống kê tổng tiền theo quý");
                        System.out.println("5. Quay lại");
                        System.out.println("------------------------------------------");
                        System.out.print("Chọn chức năng: ");
                        int chon = sc.nextInt();
                        sc.nextLine();

                        switch (chon) {
                            case 1:
                                DSPNH.thongKeTheoNgay();
                                break;
                            case 2:
                                DSPNH.thongKeTheoThang();
                                break;
                            case 3:
                                DSPNH.thongKeTheoNam();
                                break;
                            case 4:
                                DSPNH.thongKeTongTienTheoQuyVaNam();
                                break;
                            case 5:
                                System.out.println("Đã quay lại trang chính");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }

                        if (chon == 5) {
                            break;
                        }
                    }
                    break;
                case 7:
                	DSPNH.docFile();
                	break;    
                case 8:
                    // Quản lý chi tiết phiếu nhập hàng
                    QuanLyChiTietPhieuNhapHang.quanLyChiTiet(DSPNH, DSCtPNH);
                    break;
                case 9:
                	DSPNH.xuatPhieuNhapHang();
                	DSCtPNH.inDanhSachChiTiet();
                	break;
                case 10:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
