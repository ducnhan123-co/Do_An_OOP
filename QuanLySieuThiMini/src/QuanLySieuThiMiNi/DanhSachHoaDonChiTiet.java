package QuanLySieuThiMiNi;

import static java.util.Arrays.copyOf;

public class DanhSachHoaDonChiTiet {
    private ChiTietHoaDon[] DS_ChiTietHoaDon = new ChiTietHoaDon[0]; // Mảng chứa các chi tiết hóa đơn

    // Thêm một chi tiết hóa đơn vào danh sách
//    public void themChiTietHoaDon(ChiTietHoaDon cthd) {
//        if (n >= DS_ChiTietHoaDon.length) {
//            System.out.println("Danh sách chi tiết hóa đơn đã đầy.");
//            return;
//        }
//        DS_ChiTietHoaDon[n] = cthd;
//        n++;
//        System.out.println("Đã thêm chi tiết hóa đơn vào danh sách.");
//    }
    public void push(ChiTietHoaDon chiTietHoaDon) {
        DS_ChiTietHoaDon = copyOf(DS_ChiTietHoaDon, DS_ChiTietHoaDon.length+1);
        DS_ChiTietHoaDon[DS_ChiTietHoaDon.length-1] = chiTietHoaDon;
    }

    // Xuất toàn bộ danh sách chi tiết hóa đơn
    public void xuatDanhSachChiTietHoaDon() {
        if (DS_ChiTietHoaDon.length == 0) {
            System.out.println("Danh sách chi tiết hóa đơn rỗng.");
            return;
        }
        System.out.println("Danh sách chi tiết hóa đơn:");
        for (int i = 0; i < DS_ChiTietHoaDon.length; i++) {
            DS_ChiTietHoaDon[i].xuatChiTietHoaDon(); // Gọi phương thức xuất chi tiết hóa đơn
        }
    }

    // Tìm kiếm chi tiết hóa đơn theo mã sản phẩm
    public ChiTietHoaDon timKiemTheoMaSanPham(int maSP) {
        for (int i = 0; i < DS_ChiTietHoaDon.length; i++) {
            if (DS_ChiTietHoaDon[i].getMaSP() == maSP) {
                return DS_ChiTietHoaDon[i];
            }
        }
        System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
        return null;
    }

    // Xóa chi tiết hóa đơn theo mã sản phẩm
    public void xoaChiTietHoaDon(int maSP) {
        int index = -1;
        for (int i = 0; i < DS_ChiTietHoaDon.length; i++) {
            if (DS_ChiTietHoaDon[i].getMaSP() == maSP) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
            return;
        }
        for (int i = index; i < DS_ChiTietHoaDon.length- 1; i++) {
            DS_ChiTietHoaDon[i] = DS_ChiTietHoaDon[i + 1];
        }
        DS_ChiTietHoaDon[DS_ChiTietHoaDon.length - 1] = null;
        System.out.println("Đã xóa chi tiết hóa đơn cho mã sản phẩm: " + maSP);
    }

    // Tính tổng tiền của toàn bộ hóa đơn
    public double tinhTongTien() {
        double tongTien = 0;
        for (int i = 0; i < DS_ChiTietHoaDon.length; i++) {
            tongTien += DS_ChiTietHoaDon[i].tinhTien();
        }
        return tongTien;
    }

    public void xuatChiTietHoaDonTheoMHD(int maHD) {
        for (ChiTietHoaDon i: DS_ChiTietHoaDon) {
            if (i.getMaHD() == maHD) {
                i.xuatChiTietHoaDon();
            }
        }
    }
}
