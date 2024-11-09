package QuanLySieuThiMiNi;

import java.util.Scanner;
import java.util.Arrays;

public class DanhSachSanPham {
    private SanPham[] DS_SanPham = new SanPham[0];

    // Thêm sản phẩm gia dụng hoặc thực phẩm vào danh sách
    public void themGiaDungHayThucPham() {
        // Resize array dynamically
        DS_SanPham = Arrays.copyOf(DS_SanPham, DS_SanPham.length + 1);

        Scanner sc = new Scanner(System.in);
        System.out.print("Chọn loại sản phẩm (1-Gia dụng, 2-Thực phẩm): ");
        int choice = sc.nextInt();

        // Create a product based on the user's input
        if (choice == 1) {
            GiaDung giaDung = new GiaDung();
            giaDung.nhap();  // Nhập thông tin gia dụng
            DS_SanPham[DS_SanPham.length - 1] = giaDung;  // Add to the array
        } else if (choice == 2) {
            ThucPham thucPham = new ThucPham();
            thucPham.nhap();  // Nhập thông tin thực phẩm
            DS_SanPham[DS_SanPham.length - 1] = thucPham;  // Add to the array
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Xuất danh sách sản phẩm gia dụng
    public void xuatDanhSachGiaDung() {
        // Tiêu đề cho danh sách gia dụng
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|%-15s|%-20s|%-20s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Thương Hiệu", "Bảo Hành");

        // Xuất các sản phẩm gia dụng
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof GiaDung) {
                sp.xuat();  // Xuất thông tin của sản phẩm gia dụng
            }
        }
    }


    // Xuất danh sách sản phẩm thực phẩm
    public void xuatDanhSachThucPham() {
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|%-15s|%-20s|%-20s|%-15s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại TP", "Hạn SD");

        for (SanPham sp : DS_SanPham) {
            if (sp instanceof ThucPham) {
                sp.xuat();  // Xuất thông tin của sản phẩm thực phẩm
            }
        }
    }

    public void xuatDanhSach() {
        // Tiêu đề chung cho tất cả sản phẩm
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|%-15s|%-20s|%-20s|%-10s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại/Thương Hiệu", "Hạn SD/Bảo Hành");

        // Xuất danh sách sản phẩm gia dụng
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof GiaDung) {
                // Xuất thông tin của sản phẩm gia dụng
                GiaDung giaDung = (GiaDung) sp;
                giaDung.xuat();  // Gọi hàm xuất của GiaDung
            }
        }

        // Xuất danh sách sản phẩm thực phẩm
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof ThucPham) {
                // Xuất thông tin của sản phẩm thực phẩm
                ThucPham thucPham = (ThucPham) sp;
                thucPham.xuat();  // Gọi hàm xuất của ThucPham
            }
        }
    }





}


