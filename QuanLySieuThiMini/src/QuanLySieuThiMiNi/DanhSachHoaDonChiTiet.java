package QuanLySieuThiMiNi;

public class DanhSachHoaDonChiTiet {
    private ChiTietHoaDon[] dscthd; // Mảng chứa các chi tiết hóa đơn
    private int n; // Số lượng chi tiết hóa đơn hiện có

    public DanhSachHoaDonChiTiet(int kichThuoc) {
        dscthd = new ChiTietHoaDon[kichThuoc];
        n = 0;
    }

    // Thêm một chi tiết hóa đơn vào danh sách
    public void themChiTietHoaDon(ChiTietHoaDon cthd) {
        if (n >= dscthd.length) {
            System.out.println("Danh sách chi tiết hóa đơn đã đầy.");
            return;
        }
        dscthd[n] = cthd;
        n++;
        System.out.println("Đã thêm chi tiết hóa đơn vào danh sách.");
    }

    // Xuất toàn bộ danh sách chi tiết hóa đơn
    public void xuatDanhSachChiTietHoaDon() {
        if (n == 0) {
            System.out.println("Danh sách chi tiết hóa đơn rỗng.");
            return;
        }
        System.out.println("Danh sách chi tiết hóa đơn:");
        for (int i = 0; i < n; i++) {
            dscthd[i].xuatChiTietHoaDon(); // Gọi phương thức xuất chi tiết hóa đơn
        }
    }

    // Tìm kiếm chi tiết hóa đơn theo mã sản phẩm
    public ChiTietHoaDon timKiemTheoMaSanPham(int maSP) {
        for (int i = 0; i < n; i++) {
            if (dscthd[i].getMaSP() == maSP) {
                return dscthd[i];
            }
        }
        System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
        return null;
    }

    // Xóa chi tiết hóa đơn theo mã sản phẩm
    public void xoaChiTietHoaDon(int maSP) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dscthd[i].getMaSP() == maSP) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
            return;
        }
        for (int i = index; i < n - 1; i++) {
            dscthd[i] = dscthd[i + 1];
        }
        dscthd[n - 1] = null;
        n--;
        System.out.println("Đã xóa chi tiết hóa đơn cho mã sản phẩm: " + maSP);
    }

    // Tính tổng tiền của toàn bộ hóa đơn
    public double tinhTongTien() {
        double tongTien = 0;
        for (int i = 0; i < n; i++) {
            tongTien += dscthd[i].tinhTien();
        }
        return tongTien;
    }
}
