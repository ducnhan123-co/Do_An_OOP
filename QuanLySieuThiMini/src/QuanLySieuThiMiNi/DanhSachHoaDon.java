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

    public void xemDS() {
        System.out.println("+---------------------------------------+");
        for(int i=0; i<n; i++){
            dshd[i].xuatHoaDon();
        }
        System.out.println("+---------------------------------------+");
    }

    public void push(HoaDon hd) {
        dshd = Arrays.copyOf(dshd,n+1);
        dshd[n] = hd;
        n++;
    }

    // public suaHoaDon() {

    // }

    public void xoaHoaDon(HoaDon hd) {
        for(int i=0; i<n; i++) {
            if(dshd[i].getMaHD() == hd.getMaHD()) {
                for(int j=i; j<n-1; j++) {
                    dshd[j] = dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
            }
        }
    }

    public HoaDon timKiemHoaDon(int maHD) {
        for(HoaDon i: dshd) {
            if(i.getMaHD() == maHD) {
                return i;
            }
        }
        return null;
    }

    public void thongKe() {
        System.out.println("Thống Kê: ");
        double tongTien = 0;
        for(int i=0; i<n; i++) {
            tongTien += dshd[i].getTongTien();
        }
        System.out.println("Tổng số tiền của tất cả các hóa đơn: "+tongTien);
    }

    

}
