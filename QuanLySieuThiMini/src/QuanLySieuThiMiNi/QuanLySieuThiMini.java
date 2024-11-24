package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLySieuThiMini {
    public void menu() {
        Scanner sc = new Scanner(System.in);
        QuanLyNhanVien qlNhanVien = new QuanLyNhanVien(100); // Quản lý nhân viên
        QuanLySanPham qlSanPham = new QuanLySanPham(); // Quản lý sản phẩm
        QuanLyHoaDon qlHoaDon = new QuanLyHoaDon(); // Quản lý hóa đơn
        QuanLyPhieuNhapHang qlPhieuNhapHang = new QuanLyPhieuNhapHang();
        QuanLyKhachHang qlKhachHang = new QuanLyKhachHang(100);
        QuanLyNhaCungCap qlNhaCungCap = new QuanLyNhaCungCap(); // Quản lý nhà cung cấp
        int choice;
        do {
            System.out.println("\n================================================");
            System.out.println("             ████████████████████████            ");
            System.out.println("            █ QUẢN LÝ SIÊU  THỊ MINI █           ");
            System.out.println("             ████████████████████████            ");
            System.out.println("                   ┌─────────┐                   ");
            System.out.println("                   │         │                   ");
            System.out.println("           ┌───────┴─────────┴───────┐           ");
            System.out.println("           │                         │           ");
            System.out.println("           │   CHÀO MỪNG QUÝ KHÁCH   │           ");
            System.out.println("           │                         │           ");
            System.out.println("           └─────────────────────────┘           ");
            System.out.println("=================================================");

            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║              QUẢN LÝ SIÊU THỊ MINI            ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║    1. Quản lý nhân viên                       ║");
            System.out.println("║    2. Quản lý sản phẩm                        ║");
            System.out.println("║    3. Quản lý hóa đơn                         ║");
            System.out.println("║    4. Quản lý phiếu nhập hàng                 ║");
            System.out.println("║    5. Quản lý khách hàng                      ║");
            System.out.println("║    6. Quản lý nhà cung cấp                    ║");
            System.out.println("║    0. Thoát chương trình                      ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    qlNhanVien.menu();
                    break;
                case 2:
                    QuanLySanPham quanLySanPham = new QuanLySanPham();
                    quanLySanPham.menu();
                    break;
                case 3:
                    System.out.println("Tiến hành chức năng quản lý hóa đơn...\n");
                    qlHoaDon.menuDSHD();
                    break;
                case 4:

                     qlPhieuNhapHang.menu();
                    break;
                    case 5:
                    qlKhachHang.menu();

                    break;
                case 6:
                    qlNhaCungCap.menu();
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


