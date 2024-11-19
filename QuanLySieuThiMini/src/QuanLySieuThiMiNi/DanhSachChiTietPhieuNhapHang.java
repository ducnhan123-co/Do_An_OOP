package QuanLySieuThiMiNi;

import java.util.Arrays;
import java.util.Scanner;

public class DanhSachChiTietPhieuNhapHang {
    private ChiTietPhieuNhapHang[] dsChiTiet = new ChiTietPhieuNhapHang[0];
    private int n = 0;
    private DanhSachPhieuNhapHang danhSachPhieuNhapHang;

    public DanhSachChiTietPhieuNhapHang(DanhSachPhieuNhapHang danhSachPhieuNhapHang) {
        this.danhSachPhieuNhapHang = danhSachPhieuNhapHang;
    }

    // Hàm tìm phiếu nhập hàng theo mã
    public PhieuNhapHang timGanDungTheoMa(int maPhieu) {
        for (PhieuNhapHang phieu : danhSachPhieuNhapHang.getDsPhieu()) {
            if (phieu.getMaPhieu() == maPhieu) {
                return phieu;
            }
        }
        return null; // Nếu không tìm thấy
    }

    // Hàm thêm chi tiết vào phiếu nhập hàng
    public void themChiTietVaoPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu nhập hàng cần thêm chi tiết: ");
        int maPhieu = sc.nextInt();

        PhieuNhapHang phieu = timGanDungTheoMa(maPhieu);
        if (phieu != null) {
            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang();
            chiTiet.setMaPhieu(maPhieu);
            chiTiet.nhap();
            
            dsChiTiet = Arrays.copyOf(dsChiTiet, n + 1);
            dsChiTiet[n] = chiTiet;
            n++;

            phieu.addOrUpdateProduct(chiTiet.getMaSp(), chiTiet.getSl(), chiTiet.getDonGia());
            phieu.updateTongTien();

            System.out.println("Đã thêm chi tiết vào phiếu nhập hàng.");
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }
    
    
    
    // Hàm sửa chi tiết theo mã phiếu
    public void suaChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần sửa chi tiết: ");
        int maPhieu = sc.nextInt();

        // Kiểm tra xem phiếu có tồn tại không
        PhieuNhapHang phieu = timGanDungTheoMa(maPhieu);
        if (phieu != null) {
            System.out.print("Nhập mã sản phẩm cần sửa: ");
            int maSP = sc.nextInt();

            // Tìm chi tiết sản phẩm trong phiếu nhập hàng
            ChiTietPhieuNhapHang chiTiet = findChiTietByMaSP(maPhieu, maSP);

            if (chiTiet != null) {
                // Hiển thị chi tiết hiện tại
                System.out.println("Chi tiết hiện tại: ");
                System.out.printf("| %-10s | %-12s | %-8s | %-9s | %-13s |\n", "Mã Phiếu", "Mã Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
                chiTiet.xuatChiTiet();

                // Nhập lại thông tin sửa chữa
                System.out.print("Nhập số lượng mới: ");
                int soLuong = sc.nextInt();
                System.out.print("Nhập đơn giá mới: ");
                double donGia = sc.nextDouble();

                // Cập nhật lại chi tiết
                chiTiet.setSl(soLuong);
                chiTiet.setDonGia(donGia);
                
                phieu.addOrUpdateProduct(maSP, soLuong, donGia);
                chiTiet.updateThanhTien();

                // Cập nhật lại tổng tiền của phiếu
                phieu.updateTongTien();

                System.out.println("Đã sửa chi tiết cho sản phẩm mã " + maSP);
//                System.out.println("Tổng tiền của phiếu nhập hàng hiện tại: " + phieu.getTongTien());
            } else {
                System.out.println("Không tìm thấy sản phẩm trong phiếu nhập hàng.");
            }
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }



    // Hàm xóa chi tiết theo mã phiếu
    public void xoaChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần xóa chi tiết: ");
        int maPhieu = sc.nextInt();

        // Kiểm tra xem phiếu có tồn tại không
        PhieuNhapHang phieu = timGanDungTheoMa(maPhieu);
        if (phieu != null) {
            System.out.print("Nhập mã sản phẩm cần xóa: ");
            int maSP = sc.nextInt();

            // Tìm chi tiết sản phẩm trong phiếu nhập hàng
            int index = findChiTietIndex(maPhieu, maSP);

            if (index != -1) {
                // Xóa chi tiết sản phẩm trong mảng
                for (int i = index; i < n - 1; i++) {
                    dsChiTiet[i] = dsChiTiet[i + 1]; // Dịch chuyển các phần tử tiếp theo lên một vị trí
                }
                dsChiTiet = Arrays.copyOf(dsChiTiet, n - 1); // Cập nhật lại kích thước mảng
                n--; // Giảm số lượng chi tiết

                // Cập nhật lại tổng tiền của phiếu
                phieu.addOrUpdateProduct(maSP, 0, 0);
                phieu.updateTongTien();

                System.out.println("Đã xóa chi tiết sản phẩm mã " + maSP + " khỏi phiếu nhập hàng.");
                System.out.println("Tổng tiền của phiếu nhập hàng hiện tại: " + phieu.getTongTien());
            } else {
                System.out.println("Không tìm thấy sản phẩm trong phiếu nhập hàng.");
            }
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }
    
    // Hàm tìm chi tiết sản phẩm theo mã phiếu và mã sản phẩm
    private ChiTietPhieuNhapHang findChiTietByMaSP(int maPhieu, int maSP) {
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            System.out.println("Đang kiểm tra: Mã phiếu = " + chiTiet.getMaPhieu() + ", Mã sản phẩm = " + chiTiet.getMaSp());
            if (chiTiet.getMaPhieu() == maPhieu && chiTiet.getMaSp() == maSP) {
                return chiTiet;
            }
        }
        return null;
    }

    // Hàm tìm index của chi tiết sản phẩm theo mã phiếu và mã sản phẩm
    private int findChiTietIndex(int maPhieu, int maSP) {
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i].getMaPhieu() == maPhieu && dsChiTiet[i].getMaSp() == maSP) {
                return i;
            }
        }
        return -1;
    }

    // Hàm in danh sách chi tiết phiếu nhập
    public void inDanhSachChiTiet() {
        if (n == 0) {
            System.out.println("Chưa có chi tiết nào.");
            return;
        }

        System.out.printf("| %-10s | %-12s | %-8s | %-9s | %-13s |\n", "Mã Phiếu", "Mã Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("-------------------------------------------------------------");

        // In các chi tiết phiếu nhập hàng
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) 
        	chiTiet.xuatChiTiet();
            
    }

}

