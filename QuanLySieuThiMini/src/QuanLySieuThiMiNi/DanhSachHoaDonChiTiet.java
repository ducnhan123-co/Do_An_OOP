package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static java.util.Arrays.copyOf;

public class DanhSachHoaDonChiTiet {
    private ChiTietHoaDon[] DS_ChiTietHoaDon = new ChiTietHoaDon[0]; // Mảng chứa các chi tiết hóa đơn
    private DanhSachSanPham danhSachSanPham = new DanhSachSanPham();

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
        double tongCong = 0;
        System.out.println("\nDanh sách chi tiết hóa đơn:");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |\n", "Mã Hóa Đơn", "Mã Sản Phẩm","Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < DS_ChiTietHoaDon.length; i++) {
            DS_ChiTietHoaDon[i].xuatChiTietHoaDon(danhSachSanPham); // Gọi phương thức xuất chi tiết hóa đơn
            tongCong+=DS_ChiTietHoaDon[i].tinhTien();
        }
        System.out.println("----------------------------------------------------------------------------------");
        // System.out.printf("| Tổng cộng                                                         | %-10.2f |\n", tongCong);
        System.out.printf("| %-65s | %10.2f |\n", "Tổng cộng", tongCong);
        System.out.println("----------------------------------------------------------------------------------");
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
        // Kiểm tra trước xem có hóa đơn nào không
        boolean coHoaDon = false;
        for(ChiTietHoaDon i: DS_ChiTietHoaDon) {
            if(i.getMaHD() == maHD) {
                coHoaDon = true;
                break; // Chỉ cần tìm thấy một hóa đơn là đủ
            }
        }
        if(!coHoaDon) {
            System.out.println("Không có mã hóa đơn " + maHD+"!");
            return; // Kết thúc phương thức sớm nếu không tìm thấy
        }

        double tongTienThanhToan=0;
        System.out.println("\nDanh sách chi tiết hóa đơn theo mã hóa đơn "+maHD+": ");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-12s | %-10s | %-10s | %-10s |\n", "Mã Hóa Đơn", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------------------");
        for (ChiTietHoaDon i: DS_ChiTietHoaDon) {
            if (i.getMaHD() == maHD) {
                i.xuatChiTietHoaDon(danhSachSanPham);
                tongTienThanhToan+=i.tinhTien();
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        // System.out.printf("| Tổng cộng                                                         |  %-10.2f|\n", tongTienThanhToan);
        System.out.printf("| %-65s | %10.2f |\n", "Tổng cộng", tongTienThanhToan);
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void docFile () {
        try {
            BufferedReader br = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/ChiTietHoaDon.txt"));
            String line ;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(";");
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHD(Integer.parseInt(strings[0].trim()));
                chiTietHoaDon.setMaSP(Integer.parseInt(strings[1].trim()));
                chiTietHoaDon.setSoLuong(Integer.parseInt(strings[2].trim()));
                chiTietHoaDon.setDonGia(Float.parseFloat(strings[3].trim()));
                push(chiTietHoaDon);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("QuanLySieuThiMini/src/QuanLySieuThiMiNi/ChiTietHoaDon.txt"));
            String line;
            for (ChiTietHoaDon i: DS_ChiTietHoaDon) {
                line = String.format("%d;%d;%d;%f\n", i.getMaHD(),i.getMaSP(),i.getSoLuong(),i.getDonGia());
                bw.write(line);
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
