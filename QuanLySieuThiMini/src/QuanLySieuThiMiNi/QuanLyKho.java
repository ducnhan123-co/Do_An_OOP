package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class QuanLyKho implements Update_Kho, ThaoTacFile{
    Scanner sc = new Scanner(System.in);
    private DanhSachSanPham danhSachSanPham;

    public QuanLyKho() {
        this.danhSachSanPham = new DanhSachSanPham();
    }

    public QuanLyKho(DanhSachSanPham danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }

    @Override
    public void truKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if(sp!=null) {
            if(sp.getSoLuong() >= soLuong) {
                sp.setSoLuong(sp.getSoLuong() - soLuong);
                System.out.println("Đã trừ "+soLuong+" sản phẩm "+sp.getTenSP()+" khỏi kho!\n");
            }
            else {
                System.out.println(("Không đủ hàng để trừ kho!\n"));
            }
        }
        else {
            System.out.println("Không tìm thấy mã sản phẩm "+maSP+"!\n");
        }
    }

    @Override
    public void congKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if(sp!=null) {
            sp.setSoLuong(sp.getSoLuong() + soLuong);
            System.out.println("Đã cộng "+soLuong+" sản phẩm "+sp.getTenSP()+" vào kho!\n");
        }
        else {
            System.out.println("Không tìm thấy mã sản phẩm "+maSP+"!\n");
        }
    }

    @Override
    public void docFile() {
        danhSachSanPham.docFile();
    }

    @Override
    public void ghiFile() {
        danhSachSanPham.ghiFile();
    }

    @Override
    public void capNhatFile() {
        danhSachSanPham.ghiFile();
        System.out.println("Dữ liệu kho đã được cập nhật vào file!");
    }

    public void menu() {
        int choice = -1; // Khởi tạo giá trị để vòng lặp bắt đầu
        docFile();
        System.out.println("Đã đọc dữ liệu từ file SanPham.txt");
        do{
            try{
                System.out.println("\n=== QUẢN LÝ KHO ===");
                System.out.println("1. Trừ kho");
                System.out.println("2. Cộng kho");
                System.out.println("3. Lưu dữ liệu vào file");
                System.out.println("4. Thoát");
                System.out.print("Nhập lựa chọn: ");
                choice = Integer.parseInt(sc.nextLine()); // Nhập và chuyển đổi thành số nguyên

                switch(choice) {
                    case 1: // Trừ kho
                        System.out.print("Nhập mã sản phẩm: ");
                        int maSPTru = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập số lượng cần trừ: ");
                        int soLuongTru = Integer.parseInt(sc.nextLine());
                        truKho(danhSachSanPham, maSPTru, soLuongTru);
                        break;
                    case 2: // Cộng kho
                        System.out.print("Nhập mã sản phẩm: ");
                        int maSPCong = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập số lượng cần cộng: ");
                        int soLuongCong = Integer.parseInt(sc.nextLine());
                        congKho(danhSachSanPham, maSPCong, soLuongCong);
                        break;
                    case 3: // Lưu dữ liệu
                        capNhatFile();
                        break;
                    case 4: // Thoát
                        System.out.println("Thoát quản lý kho.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        } while (choice != 4); // Thoát khi chọn 4
    }

}
