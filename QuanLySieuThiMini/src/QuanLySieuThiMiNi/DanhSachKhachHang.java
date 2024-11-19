package QuanLySieuThiMiNi;

import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachKhachHang {
    private KhachHang[] DS_KhachHang = new KhachHang[0];
    private int size = 0;

    public DanhSachKhachHang() {
    }

    public void push(KhachHang khachHang) {
        DS_KhachHang = copyOf(DS_KhachHang, DS_KhachHang.length + 1);
        DS_KhachHang[DS_KhachHang.length - 1] = khachHang;
        size++;
    }

    // Thêm một khách hàng mới vào danh sách
    public void them() {
        KhachHang khachHang = new KhachHang();
        khachHang.nhap();
        push(khachHang);
    }

    // Tìm kiếm khách hàng theo mã khách hàng
    public KhachHang timKiemKhachHangTheoMa(int maKH) {
        for (KhachHang khachHang : DS_KhachHang) {
            if (khachHang != null && khachHang.getMaKH() == maKH) {
                return khachHang;
            }
        }
        return null;
    }

    // Sửa thông tin khách hàng theo mã
    public void sua(int maKH) {
        KhachHang khachHang = timKiemKhachHangTheoMa(maKH);
        if (khachHang != null) {
            khachHang.sua();
        } else {
            System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
        }
    }

    // Xóa khách hàng theo mã
    public void xoa(int maKH) {
        for (int i = 0; i < DS_KhachHang.length; i++) {
            if (DS_KhachHang[i].getMaKH() == maKH) {
                for (int j = i; j < DS_KhachHang.length - 1; j++) {
                    DS_KhachHang[j] = DS_KhachHang[j + 1];
                }
                DS_KhachHang = copyOf(DS_KhachHang, DS_KhachHang.length - 1);
                size--;
                System.out.println("Đã xóa khách hàng với mã: " + maKH);
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
    }


    public void timKiemKhachHangTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ khách hàng cần tìm kiếm: ");
        String hoKH = sc.nextLine().trim();
        boolean found = false;
        KhachHang[] khachHangFound = new KhachHang[DS_KhachHang.length];
        int index = 0;  // Biến đếm số lượng khách hàng tìm được
        for (KhachHang khachHang : DS_KhachHang) {
            if (khachHang != null && khachHang.getHoKH().equalsIgnoreCase(hoKH)) {
                khachHangFound[index++] = khachHang; // Lưu khách hàng vào mảng
                found = true;
            }
        }
        if (found) {
            System.out.println("Danh sách khách hàng có họ '" + hoKH + "':");
            System.out.printf("|%-10s|%-15s|%-15s|%-12s|%-15s|%-12s|\n", "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT");
            // In các khách hàng trong mảng kết quả
            for (int i = 0; i < index; i++) {
                if (khachHangFound[i] != null) {
                    khachHangFound[i].xuat(); // In thông tin khách hàng
                }
            }
        } else {
            System.out.println("Không tìm thấy khách hàng với họ: " + hoKH);
        }
    }


    // Thống kê số lượng khách hàng
    public void thongKeKhachHang() {
        System.out.println("Tổng số khách hàng hiện tại: " + size);
        if (size == 0) {
            System.out.println("Danh sách khách hàng trống.");
        }
    }

    // Xuất danh sách khách hàng
    public void xuatDanhSachKhachHang() {
        if (size == 0) {
            System.out.println("Danh sách khách hàng rỗng.");
            return;
        }
        System.out.printf("|%-10s|%-15s|%-15s|%-12s|%-15s|%-12s|\n", "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT");
        for (KhachHang khachHang : DS_KhachHang) {
            if (khachHang != null) {
                khachHang.xuat();
            }
        }
        System.out.println("Danh sách trên có " + size + " khách hàng.");
    }
}
