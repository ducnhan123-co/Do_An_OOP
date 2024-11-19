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
<<<<<<< Updated upstream
    private ChiTietPhieuNhapHang[] dsChiTiet = new ChiTietPhieuNhapHang[10];
    private int n = 0;
=======
    private ChiTietPhieuNhapHang[] dsChiTiet = new ChiTietPhieuNhapHang[0];
    int n = 0;
>>>>>>> Stashed changes
    private DanhSachPhieuNhapHang danhSachPhieuNhapHang;

    public DanhSachChiTietPhieuNhapHang(DanhSachPhieuNhapHang danhSachPhieuNhapHang) {
        this.danhSachPhieuNhapHang = danhSachPhieuNhapHang;
    }

    // Hàm tìm phiếu nhập hàng theo mã
    public PhieuNhapHang timGanDungTheoMa(int maPhieu) {
        // Kiểm tra danh sách không null và không rỗng
        if (danhSachPhieuNhapHang != null && danhSachPhieuNhapHang.getDsPhieu() != null) {
            for (PhieuNhapHang phieu : danhSachPhieuNhapHang.getDsPhieu()) {
                if (phieu.getMaPhieu() == maPhieu) {
                    return phieu; // Trả về phiếu nếu tìm thấy
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    
    
    
    // Hàm thêm chi tiết vào phiếu nhập hàng
    public void themChiTietVaoPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu nhập hàng cần thêm chi tiết: ");
        int maPhieu = sc.nextInt();

        // Tìm phiếu nhập hàng theo mã
        PhieuNhapHang phieu = timGanDungTheoMa(maPhieu);
        if (phieu != null) {
<<<<<<< Updated upstream
            // Tạo chi tiết phiếu nhập hàng mới
            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang();
            chiTiet.setMaPhieu(maPhieu);
            chiTiet.nhap();

            // Nếu danh sách chi tiết đã đầy, tăng kích thước mảng
            if (n >= dsChiTiet.length) {
                dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length * 2); // Tăng kích thước mảng gấp đôi
                System.out.println("Danh sách chi tiết đã đầy, tăng kích thước mảng.");
            }

            // Thêm chi tiết vào mảng
            dsChiTiet[n] = chiTiet;
            n++;

            // Cập nhật thông tin sản phẩm vào phiếu nhập hàng
            phieu.addOrUpdateProduct(chiTiet.getMaSp(), chiTiet.getSl(), chiTiet.getDonGia());
            phieu.updateTongTien();
=======
            // Nhập chi tiết phiếu nhập hàng
            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang();
            chiTiet.setMaPhieu(maPhieu);
            chiTiet.nhap();

            // Kiểm tra mã sản phẩm trong danh sách chi tiết của phiếu
            boolean isExist = false;
            for (int i = 0; i < n; i++) {
                if (dsChiTiet[i].getMaSp() == chiTiet.getMaSp()) {
                    isExist = true;
                    break;
                }
            }
>>>>>>> Stashed changes

            if (isExist) {
                System.out.println("Mã sản phẩm đã tồn tại trong phiếu. Không thể thêm chi tiết này.");
            } else {
                // Thêm chi tiết vào mảng nếu mã sản phẩm chưa tồn tại
                dsChiTiet = Arrays.copyOf(dsChiTiet, n + 1);
                dsChiTiet[n] = chiTiet;
                n++;

                // Cập nhật phiếu nhập hàng với chi tiết mới
                phieu.addOrUpdateProduct(chiTiet.getMaSp(), chiTiet.getSl(), chiTiet.getDonGia());
                phieu.updateTongTien();

                System.out.println("Đã thêm chi tiết vào phiếu nhập hàng.");
            }
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }

<<<<<<< Updated upstream



=======
    
    
    
>>>>>>> Stashed changes
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
        String filename = "D:\\ALL\\Do_An_OOP\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\ChiTietPhieuNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;

            // Đọc từng dòng trong file
            while ((line = reader.readLine()) != null) {
                ChiTietPhieuNhapHang chiTiet = parseLineToChiTiet(line);

                if (chiTiet != null) {
                    // Kiểm tra xem mảng chi tiết có đủ dung lượng không
                    if (n >= dsChiTiet.length) {
                        dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length * 100); // Tăng kích thước mảng gấp đôi nếu đầy
                        System.out.println("Danh sách chi tiết đã đầy, tăng kích thước mảng.");
                    }

                    // Thêm chi tiết vào mảng
                    dsChiTiet[n++] = chiTiet;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }

            // Thông báo số lượng chi tiết đã thêm từ tệp tin
            System.out.println("Đã thêm " + count + " chi tiết phiếu nhập hàng từ tệp tin: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin: " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ghiFile() {

    }

    @Override
    public void capNhatFile() {
        ghiFile();
    }

}

