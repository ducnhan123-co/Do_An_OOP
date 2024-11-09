package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Scanner;

public class PhieuNhapHang {
    private int maPhieu, maNCC;
    private double tongTien;
    private LocalDate ngayNhapHang;
    private ChiTietPhieuNhapHang[] chiTietPhieu;
    private int productCount;

 
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

    // Copy constructor
    public PhieuNhapHang(PhieuNhapHang x) {
        this(x.maPhieu, x.maNCC, x.tongTien, x.ngayNhapHang);
    }

    // Getters and setters
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

            ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang(maPhieu, maSP, soLuong, donGia);
            ChiTietPhieuNhapHang[] newChiTietPhieu = new ChiTietPhieuNhapHang[productCount + 1];
            System.arraycopy(chiTietPhieu, 0, newChiTietPhieu, 0, productCount);
            newChiTietPhieu[productCount] = chiTiet;
            chiTietPhieu = newChiTietPhieu;
            productCount++;
        }
    }

    // Recalculate tongTien based on all products
    public void updateTongTien() {
        tongTien = 0;
        for (int i = 0; i < productCount; i++) {
            tongTien += chiTietPhieu[i].getSl() * chiTietPhieu[i].getDonGia();
        }
    }


    public void nhapPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu: ");
        maPhieu = sc.nextInt();
        System.out.print("Nhập mã nhà cung cấp: ");
        maNCC = sc.nextInt();
        ngayNhapHang = LocalDate.now();
        tongTien = 0;

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

    public void xuatPhieu() {
        System.out.printf("|%-8s|%-15s|%-14s|%-15s|\n", maPhieu, maNCC, ngayNhapHang, tongTien);
        System.out.println("Chi tiết sản phẩm nhập:");
        System.out.printf("|%-8s|%-8s|%-8s|\n", "Mã SP", "Số lượng", "Đơn giá");

        for (int i = 0; i < productCount; i++) {
            ChiTietPhieuNhapHang chiTiet = chiTietPhieu[i];
            System.out.printf("|%-8d|%-8d|%-8.2f|\n", chiTiet.getMaSp(), chiTiet.getSl(), chiTiet.getDonGia());
        }
    }
}

