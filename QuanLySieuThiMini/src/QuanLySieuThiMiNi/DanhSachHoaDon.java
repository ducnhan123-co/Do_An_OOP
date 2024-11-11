package QuanLySieuThiMiNi;

import java.util.Arrays;

public class DanhSachHoaDon {
    private HoaDon[] dshd = new HoaDon[0];
    private int n;

    public DanhSachHoaDon() {
        this.n = 0;
    }

    public DanhSachHoaDon(HoaDon[] dshd, int n) {
        this.dshd = dshd;
        this.n = n;
    }

    public DanhSachHoaDon(DanhSachHoaDon other) {
        this.dshd = other.dshd;
        this.n = other.n;
    }

    public HoaDon[] getDshd() {
        return dshd;
    }

    public void setDshd(HoaDon[] dshd) {
        this.dshd = dshd;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    // Xem danh sách hóa đơn
    public void xemDS() {
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN-------------+");
        for(int i=0; i<n; i++){
            dshd[i].xuatHoaDon();
        }
        System.out.println("+-------------------------------------------+");
    }

    // Thêm 1 hóa đơn vào cuối danh sách
    public void push(HoaDon hd) {
        dshd = Arrays.copyOf(dshd,n+1);
        dshd[n] = hd;
        n++;
    }

    // public suaHoaDon() {

    // }

    // Xóa hóa đơn theo mã hóa đơn
    public void xoaHoaDon(int maHD) {
        for(int i=0; i<n; i++) {
            if(dshd[i].getMaHD() == maHD) {
                for(int j=i; j<n-1; j++) {
                    dshd[j] = dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
                System.out.println("Đã xóa hóa đơn có mã: "+maHD);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn có mã: " + maHD);
    }

    // Tìm kiếm hóa đơn theo mã (trả về HoaDon)
    public HoaDon timKiemHoaDon(int maHD) {
        for(HoaDon i: dshd) {
            if(i.getMaHD() == maHD) {
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD);
        return null;
    }

    // Tìm kiếm hóa đơn theo mã (trả về HoaDon)
    public HoaDon timMaHoaDon(int maHD) {
        for(int i=0; i<n; i++) {
            if(dshd[i].getMaHD() == maHD) {
                return dshd[i];
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD);
        return null;
    }

    // Tìm kiếm hóa đơn theo giá trị tổng tiền (Trả về kiểu HoaDon)
    public HoaDon timTheoTongTien(float tongTien) {
        for(HoaDon i: dshd) {
            if(i.getTongTien() == tongTien) {
                return i;
            }
            System.out.println("Không tìm thấy hóa đơn có tổng tiền: " + tongTien);
        }
        return null;
    }

    // Liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập
    public void lietKeHoaDonTongTienLonHon(float tongTienNhap) {
        boolean found = false;
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền lớn hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN LỚN HƠN "+tongTienNhap+"đ-------------+");
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                dshd[i].xuatHoaDon();   
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
    }

    // Liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập
    public void lietKeHoaDonTongTienNhoHon(float tongTienNhap) {
        boolean found = false;
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền nhỏ hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN NHỎ HƠN "+tongTienNhap+"đ-------------+");
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                dshd[i].xuatHoaDon();   
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
    }

    // Thống kê theo tổng tiền của tất cả hóa đơn
    public void thongKeTongTienHoaDon() {
        // System.out.println("Thống kê: ");
        float tongTien = 0;
        for(int i=0; i<n; i++) {
            tongTien += dshd[i].getTongTien();
        }
        System.out.println("Tổng số tiền của tất cả các hóa đơn: "+tongTien);
    }



    // Đọc file
    public void docFile() {}

    // Ghi file
    public void ghiFile() {}
}
