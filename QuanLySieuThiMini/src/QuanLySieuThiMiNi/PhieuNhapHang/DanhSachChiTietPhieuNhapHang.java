package QuanLySieuThiMiNi.PhieuNhapHang;
import QuanLySieuThiMiNi.ThaoTacFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachChiTietPhieuNhapHang implements ThaoTacFile {
    private ChiTietPhieuNhapHang[] dsChiTiet = new ChiTietPhieuNhapHang[0];
    private int n = 0;
    private DanhSachPhieuNhapHang danhSachPhieuNhapHang;

    public DanhSachChiTietPhieuNhapHang() {
        
    }

    public DanhSachChiTietPhieuNhapHang(DanhSachPhieuNhapHang danhSachPhieuNhapHang) {
        this.danhSachPhieuNhapHang = danhSachPhieuNhapHang;
    }

    public ChiTietPhieuNhapHang[] getDanhSachChiTiet() {
        return dsChiTiet;
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
    
    private boolean kiemTraMaSPTrung(int maPhieu, int maSP) {
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            if (chiTiet.getMaPhieu() == maPhieu && chiTiet.getMaSp() == maSP) {
                return true; // Trùng
            }
        }
        return false; // Không trùng
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

    public void push(ChiTietPhieuNhapHang chiTietPhieuNhapHang) {
        dsChiTiet = copyOf(dsChiTiet, dsChiTiet.length+1);
        dsChiTiet[n++] = chiTietPhieuNhapHang;
    }

    public void themChiTietVaoPhieu(int maPhieu) {
        Scanner sc = new Scanner(System.in);
        ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang();
        chiTiet.setMaPhieu(maPhieu);
        chiTiet.nhap();

        // Kiểm tra mã sản phẩm trùng
        if (kiemTraMaSPTrung(maPhieu, chiTiet.getMaSp())) {
            System.out.println("Mã sản phẩm đã tồn tại trong phiếu. Vui lòng nhập lại.");
            return;
        }

        dsChiTiet = copyOf(dsChiTiet, n + 1);
        dsChiTiet[n] = chiTiet;
        n++;
        
        updateTongTien(maPhieu);
        
        System.out.println("Đã thêm chi tiết.");
    }
    
    public void inChiTietTheoMaPhieu(int maPhieu) {
        boolean coChiTiet = false;
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            if (chiTiet.getMaPhieu() == maPhieu) {
                chiTiet.xuatChiTiet();
                coChiTiet = true;
            }
        }

        if (!coChiTiet) {
            System.out.println("Không có chi tiết nào cho phiếu này.");
        }
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
                System.out.println("Mã sản phẩm đã tồn tại trong phiếu nhập hàng này. Vui lòng nhập lại mã sản phẩm.\n");
                return; // Dừng thêm chi tiết
            }

            dsChiTiet = copyOf(dsChiTiet, n + 1);
            dsChiTiet[n] = chiTiet;
            n++;

            updateTongTien(maPhieu);

            System.out.println("Đã thêm chi tiết vào phiếu nhập hàng.");
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
        }
    }
    
    
    // Hàm in danh sách chi tiết phiếu nhập
    public void inDanhSachChiTiet() {
        if (n == 0) {
            System.out.println("Chưa có chi tiết nào.");
            return;
        }
        System.out.println("Danh sách chi tiết phiếu nhập:");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-10s | %-12s | %-8s | %-10s | %-13s |\n", "Mã Phiếu", "Mã Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("---------------------------------------------------------------------");

        // In các chi tiết phiếu nhập hàng
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) 
        	chiTiet.xuatChiTiet();
        System.out.println("---------------------------------------------------------------------");
    }
  
    // Hàm sửa chi tiết theo mã phiếu
    public void suaChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu nhập hàng cần sửa chi tiết: ");
        int maPhieu = sc.nextInt();

        // Kiểm tra mã phiếu có tồn tại không
        boolean timThayPhieu = false;
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            if (chiTiet.getMaPhieu() == maPhieu) {
                timThayPhieu = true;
                break;
            }
        }

        if (!timThayPhieu) {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
            return;
        }

        System.out.print("Nhập mã sản phẩm cần sửa: ");
        int maSP = sc.nextInt();

        boolean timThaySanPham = false;

        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            // Tìm chi tiết có mã phiếu và mã sản phẩm trùng khớp
            if (chiTiet.getMaPhieu() == maPhieu && chiTiet.getMaSp() == maSP) {
                System.out.println("Thông tin hiện tại của chi tiết:");
                chiTiet.xuatChiTiet(); // Giả sử bạn có hàm xuất thông tin chi tiết

                System.out.println("Nhập thông tin mới cho chi tiết:");
                chiTiet.nhap(); // Nhập thông tin mới

                updateTongTien(maPhieu); // Cập nhật lại tổng tiền phiếu
                System.out.println("Đã sửa chi tiết phiếu nhập hàng.");
                timThaySanPham = true;
                break;
            }
        }

        if (!timThaySanPham) {
            System.out.println("Không tìm thấy sản phẩm với mã: " + maSP + " trong phiếu nhập hàng mã: " + maPhieu);
        }
    }

    
    public void xoaChiTietTheoMaPhieu(int maPhieu) {
        int count = 0;

        // Duyệt qua danh sách chi tiết phiếu nhập hàng
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i].getMaPhieu() == maPhieu) {
                // Dịch chuyển các phần tử sau vị trí xóa lên trước
                for (int j = i; j < n - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                i--; // Kiểm tra lại vị trí hiện tại sau khi dịch chuyển
                n--; // Giảm kích thước danh sách
                count++;
            }
        }

        if (count > 0) {
            dsChiTiet = copyOf(dsChiTiet, n); // Thu gọn mảng
            System.out.println("Đã xóa " + count + " chi tiết của phiếu với mã phiếu: " + maPhieu);
        } else {
            System.out.println("Không tìm thấy chi tiết của phiếu với mã phiếu: " + maPhieu);
        }
    }





    // Hàm xóa chi tiết theo mã phiếu
    public void xoaChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu nhập hàng cần xóa sản phẩm: ");
        int maPhieu = sc.nextInt();

        // Kiểm tra mã phiếu có tồn tại không
        boolean timThayPhieu = false;
        for (ChiTietPhieuNhapHang chiTiet : dsChiTiet) {
            if (chiTiet.getMaPhieu() == maPhieu) {
                timThayPhieu = true;
                break;
            }
        }

        if (!timThayPhieu) {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + maPhieu);
            return;
        }

        System.out.print("Nhập mã sản phẩm cần xóa: ");
        int maSP = sc.nextInt();

        boolean daXoa = false;

        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i].getMaPhieu() == maPhieu && dsChiTiet[i].getMaSp() == maSP) {
                // Dịch các phần tử sau để ghi đè phần tử cần xóa
                for (int j = i; j < n - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                dsChiTiet = copyOf(dsChiTiet, n - 1); // Giảm kích thước mảng
                n--;

                updateTongTien(maPhieu); // Cập nhật lại tổng tiền phiếu
                daXoa = true;
                System.out.println("Đã xóa sản phẩm với mã: " + maSP + " trong phiếu nhập hàng mã: " + maPhieu);
                break;
            }
        }

        if (!daXoa) {
            System.out.println("Không tìm thấy sản phẩm với mã: " + maSP + " trong phiếu nhập hàng mã: " + maPhieu);
        }
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
        String filename = "QuanLySieuThiMini/src/QuanLySieuThiMiNi/PhieuNhapHang/ChiTietPhieuNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                ChiTietPhieuNhapHang chiTiet = parseLineToChiTiet(line);
                if (chiTiet != null) {
                    // Kiểm tra nếu mảng đã đầy, thì tăng kích thước mảng
                    if (n >= dsChiTiet.length) {
                        dsChiTiet = copyOf(dsChiTiet, dsChiTiet.length + 10); // Tăng kích thước
                    }
                    dsChiTiet[n++] = chiTiet;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
//            System.out.println("Đã thêm " + count + " chi tiết phiếu nhập hàng từ tệp tin: " + filename);
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
		// TODO Auto-generated method stub
		
	}

}

