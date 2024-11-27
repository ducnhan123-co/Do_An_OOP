package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyNhaCungCap {
	public void menu(){
		Scanner sc = new Scanner(System.in);
		DanhSachNhaCungCap dSNCC = new DanhSachNhaCungCap();
		
		while(true) {
			System.out.println("╔══════════════════════════════-MENU QUẢN LÝ NHÀ CUNG CẤP-══════════════════════════════╗");
			System.out.println("║ 1. Thêm nhà cung cấp                                                                  ║");
			System.out.println("║ 2. Xuất danh sách nhà cung cấp                                                        ║");
			System.out.println("║ 3. Lấy dữ liệu từ data                                                                ║");
			System.out.println("║ 4. Sửa thông tin nhà cung cấp                                                         ║");
			System.out.println("║ 5. Xóa nhà cung cấp                                                                   ║");
			System.out.println("║ 6. Tìm tên nhà cung cấp                                                               ║");
			System.out.println("║ 7. Quay lại                                                                           ║");
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
				case 3:
					dSNCC.docFile();
					break;
				case 4:
					dSNCC.suaNCC();
					break;
				case 5:
					dSNCC.xoaNCC();
					break;
				case 6:
					dSNCC.timTenNCCGanDung();
					break;
				case 7:
					return;
				default:
					System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
			}
		}
	}
}
