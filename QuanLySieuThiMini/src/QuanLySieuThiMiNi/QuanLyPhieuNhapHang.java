package QuanLySieuThiMiNi;

import java.util.Scanner;


public class QuanLyPhieuNhapHang {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DanhSachPhieuNhapHang DSPNH = new DanhSachPhieuNhapHang();
		
		while (true) {
	        System.out.println("------------------------------MENU-------------------------------");
	        System.out.println("1. Thêm phiếu nhập hàng");
	        System.out.println("2. Xuất danh sách phiếu nhập hàng");
	        System.out.println("3. Tìm kiếm phiếu nhập hàng theo mã phiếu");
	        System.out.println("4. Tìm kiếm phiếu và sửa thông tin theo mã phiếu");
	        System.out.println("5. Xoá phiếu nhập hàng theo mã phiếu");
	        System.out.println("6. Thoát");
	        System.out.println("-----------------------------------------------------------------");
	        System.out.print("Chọn chức năng: ");
	        int choice = sc.nextInt();
	        sc.nextLine(); // Consume newline

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
	                DSPNH.suaPhieuNhapHang();;
	                break;
	            case 5:
	                DSPNH.xoaTheoMa();
	                break;
	            case 6:
	                System.out.println("Đã thoát chương trình.");
	                return;
	            default:
	                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
	        }
	    }
	}
}
