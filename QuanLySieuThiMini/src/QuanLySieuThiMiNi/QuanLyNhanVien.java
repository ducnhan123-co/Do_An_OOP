package QuanLySieuThiMiNi;

import java.util.Scanner;

public class QuanLyNhanVien {
    private DanhSachNhanVien ds;

    public QuanLyNhanVien(int maxNhanVien) {
        ds = new DanhSachNhanVien(maxNhanVien);
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------MENU-------------------------------");
            System.out.println("1. Thêm 1 nhân viên");
            System.out.println("2. Thêm danh sách nhân viên");
            System.out.println("3. Sửa nhân viên theo mã");
            System.out.println("4. Xóa nhân viên theo mã");
            System.out.println("5. Tìm kiếm nhân viên theo mã");
            System.out.println("6. Tìm kiếm nhân viên theo họ");
            System.out.println("7. Tìm kiếm nhân viên theo tên");
            System.out.println("8. Thống kê theo mức lương");
            System.out.println("9. Thống kê theo chức vụ");
            System.out.println("10. Cập nhật danh sách nhân viên hiện tại");
            System.out.println("11. In danh sách nhân viên");
            System.out.println("0. Thoát");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Bỏ qua dòng trống

            switch (choice) {
                case 1 -> ds.themNhanVien(); // Thêm một nhân viên
                case 2 -> ds.themDanhSachNhanVien(); // Thêm danh sách nhân viên
                case 3 -> ds.suaNhanVienTheoMa(); // Sửa nhân viên theo mã
                case 4 -> {
                    System.out.print("Nhập mã nhân viên cần xóa: ");
                    int maXoa = sc.nextInt();
                    sc.nextLine();
                    ds.xoaNhanVienTheoMaCach1(maXoa);
                }
                case 5 -> {
                    System.out.print("Nhập mã nhân viên cần tìm: ");
                    int maTim = sc.nextInt();
                    sc.nextLine();
                    NhanVien nhanVienTim = ds.timKiemNhanVienTheoMa(maTim);
                    if (nhanVienTim != null) {
                        nhanVienTim.xuatNhanVien();
                    } else {
                        System.out.println("Không tìm thấy nhân viên với mã " + maTim);
                    }
                }
                case 6 -> {
                    System.out.print("Nhập họ nhân viên cần tìm kiếm: ");
                    String hoTim = sc.nextLine().trim();
                    ds.timKiemNhanVienTheoHo(hoTim);
                }
                case 7 -> {
                    System.out.print("Nhập tên nhân viên cần tìm kiếm: ");
                    String tenTim = sc.nextLine().trim();
                    ds.timKiemNhanVienTheoTen(tenTim);
                }
                case 8 -> ds.thongKeTheoMucLuong(); // Thống kê theo mức lương
                case 9 -> ds.thongKeTheoChucVu(); // Thống kê theo chức vụ
                case 10 -> ds.capNhatSoLuongNhanVien(); // Cập nhật số lượng nhân viên hiện tại
                case 11 -> ds.xuatDanhSachNhanVien(); // In danh sách nhân viên
                case 0 -> {
                    System.out.println("Đã thoát chương trình.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
