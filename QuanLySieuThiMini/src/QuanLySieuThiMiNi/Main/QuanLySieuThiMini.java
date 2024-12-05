package QuanLySieuThiMiNi.Main;

import QuanLySieuThiMiNi.HoaDon.DanhSachHoaDon;
import QuanLySieuThiMiNi.HoaDon.DanhSachHoaDonChiTiet;
import QuanLySieuThiMiNi.HoaDon.QuanLyHoaDon;
import QuanLySieuThiMiNi.KhachHang.DanhSachKhachHang;
import QuanLySieuThiMiNi.KhachHang.QuanLyKhachHang;
import QuanLySieuThiMiNi.NhaCungCap.DanhSachNhaCungCap;
import QuanLySieuThiMiNi.NhaCungCap.QuanLyNhaCungCap;
import QuanLySieuThiMiNi.NhanVien.DanhSachNhanVien;
import QuanLySieuThiMiNi.NhanVien.QuanLyNhanVien;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachChiTietPhieuNhapHang;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachPhieuNhapHang;
import QuanLySieuThiMiNi.PhieuNhapHang.QuanLyPhieuNhapHang;
import QuanLySieuThiMiNi.SanPham.QuanLySanPham;
import QuanLySieuThiMiNi.SanPham.DanhSachSanPham;
import java.util.Scanner;

public class QuanLySieuThiMini {
    public void menu() {
        new DanhSachSanPham().docFile();
        new DanhSachKhachHang().docFile();
        new DanhSachNhanVien().docFile();
        new DanhSachHoaDon().docFile();
        new DanhSachHoaDonChiTiet().docFile();
        new DanhSachPhieuNhapHang().docFile();
        new DanhSachChiTietPhieuNhapHang().docFile();
        new DanhSachNhaCungCap().docFile();

        Scanner sc = new Scanner(System.in);
        QuanLyNhanVien qlNhanVien = new QuanLyNhanVien(100); // Quản lý nhân viên
        QuanLySanPham qlSanPham = new QuanLySanPham(); // Quản lý sản phẩm
        QuanLyHoaDon qlHoaDon = new QuanLyHoaDon(); // Quản lý hóa đơn
        QuanLyPhieuNhapHang qlPhieuNhapHang = new QuanLyPhieuNhapHang();
        QuanLyKhachHang qlKhachHang = new QuanLyKhachHang(100);
        QuanLyNhaCungCap qlNhaCungCap = new QuanLyNhaCungCap(); // Quản lý nhà cung cấp
    
        
        int choice;
        do {
            System.out.println("\n=================================================");
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
                    System.out.println("Tiến hành chức năng quản lý nhân viên...\n");
                    qlNhanVien.menu();
                    break;
                case 2:
                    System.out.println("Tiến hành chức năng quản lý sản phẩm...\n");
                    qlSanPham.menu();
                    break;
                case 3:
                    System.out.println("Tiến hành chức năng quản lý hóa đơn...\n");
                    qlHoaDon.menu();
                    break;
                case 4:
                    System.out.println("Tiến hành chức năng quản lý phiếu nhập hàng...\n");
                    qlPhieuNhapHang.menu();
                    break;
                case 5:
                    System.out.println("Tiến hành chức năng quản lý khách hàng...\n");
                    qlKhachHang.menu();
                    break;
                case 6:
                    System.out.println("Tiến hành chức năng quản lý nhà cung cấp...\n");
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


