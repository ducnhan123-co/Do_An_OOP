package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLySanPham {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachSanPham dssp = new DanhSachSanPham();

        while (true) {
            System.out.println("------------------------------MENU-------------------------------");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xuất sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dssp.themGiaDungHayThucPham();
                    break;
                case 2:
                    dssp.xuatDanhSach();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        }
    }
}