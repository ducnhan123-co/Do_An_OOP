package QuanLySieuThiMiNi.NhaCungCap;

import java.util.Scanner;

public class QuanLyNhaCungCap {
	public void menu(){
		Scanner sc = new Scanner(System.in);
		DanhSachNhaCungCap dSNCC = new DanhSachNhaCungCap();
		// dSNCC.docFile();
		while(true) {
			System.out.println("╔══════════════════════════════-MENU QUẢN LÝ NHÀ CUNG CẤP-══════════════════════════════╗");
			System.out.println("║ 1. Thêm nhà cung cấp                                                                  ║");
			System.out.println("║ 2. Xuất danh sách nhà cung cấp                                                        ║");
			System.out.println("║ 3. Sửa thông tin nhà cung cấp                                                         ║");
			System.out.println("║ 4. Xóa nhà cung cấp                                                                   ║");
			System.out.println("║ 5. Tìm tên nhà cung cấp                                                               ║");
			System.out.println("║ 0. Quay lại                                                                           ║");
			System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
			System.out.print("Chọn chức năng: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					dSNCC.themNCC();
					break;
				case 2:
					dSNCC.xuatNCC();
					break;
//				case 3:
//					dSNCC.docFile();
//					break;
				case 3:
					dSNCC.suaNCC();
					break;
				case 4:
					dSNCC.xoaNCC();
					break;
				case 5:
					dSNCC.timTenNCCGanDung();
					break;
				case 0:
					return;
				default:
					System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
			}
		}
	}
}
