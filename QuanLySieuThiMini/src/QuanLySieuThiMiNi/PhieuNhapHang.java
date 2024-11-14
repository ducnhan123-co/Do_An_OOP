package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class PhieuNhapHang {
    private int maPhieu, maNCC;
    private double tongTien;
    private LocalDate ngayNhapHang;
    private ChiTietPhieuNhapHang[] chiTietPhieu;
    private int productCount;

    // Mảng lưu trữ các mã phiếu đã nhập (static để dùng chung cho tất cả các đối tượng)
    private static int[] maPhieuList = new int[0];  // Dùng để kiểm tra trùng mã phiếu
    private static int maPhieuCount = 0;  // Số lượng mã phiếu đã nhập

    public PhieuNhapHang() {
        this.chiTietPhieu = new ChiTietPhieuNhapHang[0];
        this.productCount = 0;
    }

    public PhieuNhapHang(int maPhieu, int maNCC, double tongTien, LocalDate ngayNhapHang) {
        this();
        this.maPhieu = maPhieu;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhapHang = ngayNhapHang;
    }

    // Getter và setter

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

    // Phương thức thêm hoặc cập nhật sản phẩm trong phiếu
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
            chiTietPhieu[index].setSl(soLuong);
            chiTietPhieu[index].setDonGia(donGia);
        } else {
            // Add new product
            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGia, soLuong * donGia);
            ChiTietPhieuNhapHang[] newChiTietPhieu = new ChiTietPhieuNhapHang[productCount + 1];
            System.arraycopy(chiTietPhieu, 0, newChiTietPhieu, 0, productCount);
            newChiTietPhieu[productCount] = chiTiet;
            chiTietPhieu = newChiTietPhieu;
            productCount++;
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
        
        while (true) {
            System.out.print("Nhập mã sản phẩm (hoặc nhập -1 để kết thúc): ");
            int maSP = sc.nextInt();
            if (maSP == -1) break;

            System.out.print("Nhập số lượng: ");
            int soLuong = sc.nextInt();

            System.out.print("Nhập đơn giá: ");
            double donGia = sc.nextDouble();

            addOrUpdateProduct(maSP, soLuong, donGia);
        }

        updateTongTien();
        System.out.println("Tổng tiền của phiếu nhập hàng là: " + tongTien);
    }

    // Xuất thông tin phiếu nhập hàng
    public void xuatPhieu() {
        System.out.println("Chi tiết sản phẩm của mã phiếu " + getMaPhieu());
        System.out.printf("|%-8s|%-8s|%-10s|%-10s|\n", "Mã SP", "Số lượng", "Đơn giá", "Thành Tiền");

        for (int i = 0; i < productCount; i++) {
            ChiTietPhieuNhapHang chiTiet = chiTietPhieu[i];
            System.out.printf("|%-8d|%-8d|%-10.2f|%-10.2f|\n", chiTiet.getMaSp(), chiTiet.getSl(), chiTiet.getDonGia(), chiTiet.getThanhTien());
        }
    }
}


