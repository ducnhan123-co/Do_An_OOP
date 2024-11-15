package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLySieuThiMini {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuanLyNhanVien qlNhanVien = new QuanLyNhanVien(100); // Quản lý nhân viên
//        QuanLySanPham qlSanPham = new QuanLySanPham(100); // Quản lý sản phẩm
        QuanLyHoaDon qlHoaDon = new QuanLyHoaDon(); // Quản lý hóa đơn
//        QuanLyPhieuNhapHang qlPhieuNhapHang = new QuanLyPhieuNhapHang(100); // Quản lý phiếu nhập hàng
        QuanLyPhieuNhapHang qlPhieuNhapHang = new QuanLyPhieuNhapHang();
//        QuanLyNhaCungCap qlNhaCungCap = new QuanLyNhaCungCap(100); // Quản lý nhà cung cấp
        int choice;
        do {
            System.out.println("\n======================================");
            System.out.println("         ████████████████████████       ");
            System.out.println("        █ QUẢN LÝ SIÊU  THỊ MINI █      ");
            System.out.println("         ████████████████████████       ");
            System.out.println("               ┌─────────┐              ");
            System.out.println("               │         │              ");
            System.out.println("       ┌───────┴─────────┴───────┐      ");
            System.out.println("       │                         │      ");
            System.out.println("       │   CHÀO MỪNG QUÝ KHÁCH   │      ");
            System.out.println("       │                         │      ");
            System.out.println("       └─────────────────────────┘      ");
            System.out.println("========================================");

            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║              QUẢN LÝ SIÊU THỊ MINI            ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║    1. Quản lý nhân viên                       ║");
            System.out.println("║    2. Quản lý sản phẩm                        ║");
            System.out.println("║    3. Quản lý hóa đơn                         ║");
            System.out.println("║    4. Quản lý phiếu nhập hàng                 ║");
            System.out.println("║    5. Quản lý nhà cung cấp                    ║");
            System.out.println("║    0. Thoát chương trình                      ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    qlNhanVien.menu();
                    break;
                case 2:
                    System.out.println("Chức năng quản lý sản phẩm đang được phát triển...");
                    break;
                case 3:
                    qlHoaDon.menuDSHD();
                    break;
                case 4:
                qlPhieuNhapHang.menu();
                    break;
                case 5:
                    System.out.println("Chức năng quản lý nhà cung cấp đang được phát triển...");
                    break;
                case 0:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        } while (choice != 0);
    }
}


