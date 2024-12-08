package QuanLySieuThiMiNi.PhieuNhapHang;

import java.util.Scanner;

public class QuanLyPhieuNhapHang {
    DanhSachPhieuNhapHang DSPNH = new DanhSachPhieuNhapHang();
    DanhSachChiTietPhieuNhapHang DSCtPNH = new DanhSachChiTietPhieuNhapHang(DSPNH); // Truyền danh sách phiếu nhập hàng

    public QuanLyPhieuNhapHang()
    {
        DSPNH.docFile();
        DSCtPNH.docFile();
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);

        
        while (true) {
            System.out.println("╔═══════════════════════════-MENU QUẢN LÝ PHIẾU NHẬP HÀNG-═══════════════════════════╗");
            System.out.println("║ 1.  Thêm phiếu nhập hàng.                                                          ║");
            System.out.println("║ 2.  Tìm kiếm phiếu nhập hàng theo mã.                                              ║");
            System.out.println("║ 3.  Sửa thông tin phiếu nhập hàng theo mã.                                         ║");
            System.out.println("║ 4.  Xóa phiếu nhập hàng theo mã.                                                   ║");
            System.out.println("║ 5.  Thống kê phiếu nhập hàng theo tùy chọn.                                        ║");
            System.out.println("║ 6.  Quản lý chi tiết phiếu nhập hàng.                                              ║");
            System.out.println("║ 7.  Xuất danh sách phiếu nhập hàng.                                                ║");
            System.out.println("║ 8.  Xuất danh sách toàn bộ phiếu nhập hàng.                                        ║");
            System.out.println("║ 0.  Quay lại trang chính.                                                          ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DSPNH.nhapPhieuVaChiTiet(DSCtPNH);
                    break;
                case 2:
                    DSPNH.timMP();
                    break;
                case 3:
                    DSPNH.suaPhieuNhapHang();
                    break;
                case 4:
                	System.out.print("Nhập mã phiếu cần xóa: ");
                    int maPhieuXoa = sc.nextInt();
                    DSCtPNH.xoaChiTietTheoMaPhieu(maPhieuXoa); // Xóa chi tiết
                    DSPNH.xoaPhieuTheoMaPhieu(maPhieuXoa); // Xóa phiếu
                    break;
                case 5:
                    while (true) {
                        System.out.println("╔══════════════════════════════-THỐNG KÊ-══════════════════════════════╗");
                        System.out.println("║ 1. Thống kê theo ngày.                                               ║");
                        System.out.println("║ 2. Thống kê theo tháng.                                              ║");
                        System.out.println("║ 3. Thống kê theo năm.                                                ║");
                        System.out.println("║ 4. Thống kê tổng tiền theo quý.                                      ║");
                        System.out.println("║ 5. Quay lại.                                                         ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
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

                case 6:
                    // Quản lý chi tiết phiếu nhập hàng
                    QuanLyChiTietPhieuNhapHang.quanLyChiTiet(DSPNH, DSCtPNH);
                    break;
                case 7:
                	DSPNH.xuatPhieuNhapHang();
                	DSCtPNH.inDanhSachChiTiet();
                	break;
                case 8:
                    DSPNH.hienThiPhieuVaChiTiet(DSCtPNH);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}

