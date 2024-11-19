package QuanLySieuThiMiNi;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class PhieuNhapHang {
    private int maPhieu, maNCC, maNV;
    private double tongTien;
    private LocalDate ngayNhapHang;
    private ChiTietPhieuNhapHang[] chiTietPhieu;
    private int productCount;

   
    private static int[] maPhieuList = new int[0];  // Dùng để kiểm tra trùng mã phiếu
    private static int maPhieuCount = 0;  // Số lượng mã phiếu đã nhập

    public PhieuNhapHang(int maNV,int maPhieu, int maNCC, double tongTien, LocalDate ngayNhapHang) {
    	this.maNV = maNV;
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhapHang = ngayNhapHang;
    }
    
    public PhieuNhapHang() {
        this.maNV = 0;               
        this.maPhieu = 0;           
        this.maNCC = 0;              
        this.tongTien = 0.0;         
        this.ngayNhapHang = LocalDate.now();
        this.chiTietPhieu = new ChiTietPhieuNhapHang[0];
        this.productCount = 0;
    }
    
    // Getter và setter
    
    public int getMaNhanVien() {
        return maNV;
    }

    public void setMaNhanVien(int maNV) {
        this.maNV = maNV;
    }
    
    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDate getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(LocalDate ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }

    // Kiểm tra mã phiếu đã tồn tại trong danh sách
    private boolean checkMaPhieu(int maPhieu) {
        for (int i = 0; i < maPhieuCount; i++) {
            if (maPhieuList[i] == maPhieu) 
                return true;
        }
        return false;
    }

     //Phương thức thêm hoặc cập nhật sản phẩm trong phiếu
    private int findProductIndex(int maSP) {
        for (int i = 0; i < productCount; i++) {
            if (chiTietPhieu[i].getMaSp() == maSP) {
                return i;
            }
        }
        return -1;
    }

    public void addOrUpdateProduct(int maSP, int soLuong, double donGia) {
        int index = findProductIndex(maSP);
        if (index != -1) {
            // Cập nhật sản phẩm nếu đã tồn tại
            chiTietPhieu[index].setSl(soLuong);
            chiTietPhieu[index].setDonGia(donGia);
            chiTietPhieu[index].setThanhTien(soLuong * donGia);
        } else {
            // Thêm sản phẩm mới
            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGia, soLuong * donGia);
            chiTietPhieu = Arrays.copyOf(chiTietPhieu, productCount + 1); 
            chiTietPhieu[productCount++] = chiTiet; 
        }
    }


    // Cập nhật tổng tiền của phiếu
    public void updateTongTien() {
        tongTien = 0;
        for (int i = 0; i < productCount; i++) {
            chiTietPhieu[i].updateThanhTien();
            tongTien += chiTietPhieu[i].getThanhTien();
        }
    }

    // Nhập phiếu nhập hàng, kiểm tra mã phiếu không trùng
    public void nhapPhieu() {
        Scanner sc = new Scanner(System.in);
        int mp;
        
        System.out.print("Nhập mã nhân viên: ");
        maNV = sc.nextInt();
        
        // Yêu cầu nhập mã phiếu không trùng lặp
        while (true) {
            System.out.print("Nhập mã phiếu: ");
            mp = sc.nextInt();
            if (!checkMaPhieu(mp)) {
                // Nếu mã phiếu không trùng, lưu lại mã phiếu vào mảng
                maPhieuList = Arrays.copyOf(maPhieuList, maPhieuCount + 1);
                maPhieuList[maPhieuCount] = mp;
                maPhieuCount++;
                break;
            } 
            else 
                System.out.println("Mã phiếu đã tồn tại, vui lòng nhập lại.");
        }

        this.maPhieu = mp;

        System.out.print("Nhập mã nhà cung cấp: ");
        maNCC = sc.nextInt();
        ngayNhapHang = LocalDate.now();
        tongTien = 0;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.print("Nhập ngày nhập hàng (dd/MM/yyyy): ");
            sc.nextLine(); // Clear buffer
            String ngayNhapStr = sc.nextLine();
            try {
                ngayNhapHang = LocalDate.parse(ngayNhapStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
            }
        }

        updateTongTien();
        System.out.println("Tổng tiền của phiếu nhập hàng là: " + tongTien);
    }

    public void xuatPhieu() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = ngayNhapHang.format(formatter);
        System.out.printf("|%-12d|%-12d|%-12d|%-12.2f|%-12s|\n",
                this.maPhieu, this.maNCC, this.maNV, this.tongTien, formattedDate);
    }
}


