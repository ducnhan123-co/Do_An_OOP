package QuanLySieuThiMiNi;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachChiTietPhieuNhapHang implements ThaoTacFile {
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
    
    
 // Hàm cập nhật tổng tiền cho phiếu nhập hàng
    public void updateTongTien(int maPhieu) {
        PhieuNhapHang phieu = timGanDungTheoMa(maPhieu); // Tìm phiếu nhập hàng dựa vào mã phiếu
        if (phieu == null) {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
            return;
        }

        double tongTienMoi = 0; // Biến để lưu tổng tiền mới
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            if (chiTiet.getMaPhieu() == maPhieu) {
                tongTienMoi += chiTiet.getThanhTien(); // Cộng dồn thành tiền của các chi tiết thuộc phiếu
            }
        }

        // Cập nhật tổng tiền cho phiếu
        phieu.setTongTien(tongTienMoi);
        System.out.println("Tổng tiền của phiếu mã " + maPhieu + " đã được cập nhật: " + tongTienMoi);
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

            // Kiểm tra xem mã sản phẩm đã tồn tại trong chi tiết của phiếu này chưa
            if (kiemTraMaSPTrung(maPhieu, chiTiet.getMaSp())) {
                System.out.println("Mã sản phẩm đã tồn tại trong phiếu nhập hàng này. Vui lòng nhập lại mã sản phẩm.");
                return; // Dừng thêm chi tiết
            }

            dsChiTiet = Arrays.copyOf(dsChiTiet, n + 1);
            dsChiTiet[n] = chiTiet;
            n++;

            updateTongTien(maPhieu);

            System.out.println("Đã thêm chi tiết vào phiếu nhập hàng.");
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }
    
 // Kiểm tra mã sản phẩm đã tồn tại trong chi tiết phiếu nhập hàng chưa
    private boolean kiemTraMaSPTrung(int maPhieu, int maSP) {
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            // Nếu mã phiếu và mã sản phẩm trùng nhau, thì sản phẩm đã tồn tại
            if (chiTiet.getMaPhieu() == maPhieu && chiTiet.getMaSp() == maSP) {
                return true; // Trùng
            }
        }
        return false; // Không trùng
    }
    
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
                double donGia = sc.nextDouble();// Cập nhật lại chi tiết
                chiTiet.setSl(soLuong);
                chiTiet.setDonGia(donGia);
                
                chiTiet.updateThanhTien();

                // Cập nhật lại tổng tiền của phiếu
                updateTongTien(maPhieu);

                System.out.println("Đã sửa chi tiết cho sản phẩm mã " + maSP);
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
                updateTongTien(maPhieu);

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
    
    public ChiTietPhieuNhapHang parseLineToChiTiet(String line) {
        String[] parts = line.split(";");
        if (parts.length == 5) { // Cần đủ 5 trường dữ liệu
            try {
                int maPhieuNhap = Integer.parseInt(parts[0].trim());
                int maSanPham = Integer.parseInt(parts[1].trim());
                int soLuong = Integer.parseInt(parts[2].trim());
                double donGia = Double.parseDouble(parts[3].trim());
                double thanhTien = Double.parseDouble(parts[4].trim());
                
                return new ChiTietPhieuNhapHang(maPhieuNhap, maSanPham, soLuong, donGia, thanhTien);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng số trong dòng: " + line);
            }
        } else {
            System.out.println("Số lượng cột không khớp trong dòng: " + line);
        }
        return null;
    }

    @Override
    public void docFile() {
        
    }

	@Override
	public void ghiFile() {
		
		
	}

	@Override
	public void capNhatFile() {
		// TODO Auto-generated method stub
		
	}

}

