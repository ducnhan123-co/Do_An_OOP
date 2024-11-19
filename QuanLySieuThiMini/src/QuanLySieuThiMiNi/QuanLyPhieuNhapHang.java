package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyPhieuNhapHang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachPhieuNhapHang DSPNH = new DanhSachPhieuNhapHang();
        DanhSachChiTietPhieuNhapHang DSCtPNH = new DanhSachChiTietPhieuNhapHang(DSPNH); // Truyền danh sách phiếu nhập hàng

        while (true) {
            System.out.println("---------------------------------MENU----------------------------------");
            System.out.println("1. Thêm phiếu nhập hàng");
            System.out.println("2. Xuất danh sách phiếu nhập hàng");
            System.out.println("3. Tìm kiếm phiếu nhập hàng theo mã phiếu");
            System.out.println("4. Tìm kiếm phiếu và sửa thông tin theo mã phiếu");
            System.out.println("5. Xoá phiếu nhập hàng theo mã phiếu");
            System.out.println("6. Thống kê phiếu nhập theo ngày");
            System.out.println("7. Thống kê phiếu nhập theo tháng");
            System.out.println("8. Thống kê phiếu nhập theo năm");
            System.out.println("9. Quản lý chi tiết phiếu nhập hàng");
            System.out.println("10. Xuất danh sách toàn bộ ");
            System.out.println("11. Thoát");
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
                    DSPNH.xoaTheoMa(); // chua hoàn thiện
                    break;
                case 6:
                    DSPNH.thongKeTheoNgay();
                    break;
                case 7:
                    DSPNH.thongKeTheoThang();
                    break;
                case 8:
                    DSPNH.thongKeTheoNam();
                    break;
                case 9:
                    // Quản lý chi tiết phiếu nhập hàng
                    QuanLyChiTietPhieuNhapHang.quanLyChiTiet(DSPNH, DSCtPNH);
                    break;
                case 10:
                	System.out.println("Danh Sách Phiếu");
                	DSPNH.xuatPhieuNhapHang();
                	System.out.println("\nDanh Sách Chi Tiết Phiếu ");
                	DSCtPNH.inDanhSachChiTiet();
                	break;
                case 11:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}



