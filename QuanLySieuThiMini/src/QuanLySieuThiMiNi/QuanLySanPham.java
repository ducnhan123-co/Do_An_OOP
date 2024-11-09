package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLySanPham {
    public static void main(String[] args) {
        DanhSachSanPham dsSanPham = new DanhSachSanPham();
        Scanner sc = new Scanner(System.in);
        
        // Menu
        while (true) {
            System.out.println("===== Quản lý sản phẩm =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xuất danh sách sản phẩm");
            System.out.println("3. Xuất danh sách gia dụng");
            System.out.println("4. Xuất danh sách thực phẩm");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            
            if (choice == 1) {
                dsSanPham.themGiaDungHayThucPham();
            } else if (choice == 2) {
                dsSanPham.xuatDanhSach();
            } else if (choice == 3) {
                dsSanPham.xuatDanhSachGiaDung();
            } else if (choice == 4) {
                dsSanPham.xuatDanhSachThucPham();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}

