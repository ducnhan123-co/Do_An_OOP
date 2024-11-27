package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyChiTietPhieuNhapHang {
    public static void quanLyChiTiet(DanhSachPhieuNhapHang DSPNH, DanhSachChiTietPhieuNhapHang DSCtPNH) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("╔══════════════════════════════-MENU CHI TIẾT PHIẾU NHẬP HÀNG-══════════════════════════════╗");
            System.out.println("║ 1. Thêm chi tiết vào phiếu nhập hàng                                                      ║");
            System.out.println("║ 2. Sửa chi tiết phiếu nhập hàng                                                           ║");
            System.out.println("║ 3. Xóa chi tiết phiếu nhập hàng                                                           ║");
            System.out.println("║ 4. Lấy dữ liệu từ data                                                                    ║");
            System.out.println("║ 5. Xem danh sách chi tiết phiếu nhập hàng                                                 ║");
            System.out.println("║ 0. Quay lại                                                                               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    DSCtPNH.themChiTietVaoPhieu();
                    break;
                case 2:
                    DSCtPNH.suaChiTiet(); // done
                    break;
                case 3: 
                    DSCtPNH.xoaChiTiet(); // done 
                    break;
                case 4:
                	DSCtPNH.docFile();
                	break;
                case 5:
                    DSCtPNH.inDanhSachChiTiet(); // Gọi hàm in danh sách chi tiết
                    break;
                case 0 :
                    return; // Quay lại menu quản lý phiếu nhập hàng
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}


