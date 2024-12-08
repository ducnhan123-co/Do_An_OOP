package QuanLySieuThiMiNi.HoaDon;

import QuanLySieuThiMiNi.SanPham.DanhSachSanPham;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static java.util.Arrays.copyOf;

public class DanhSachHoaDonChiTiet {
    private static ChiTietHoaDon[] dscthd = new ChiTietHoaDon[0]; // Mảng chứa các chi tiết hóa đơn
    private DanhSachHoaDon danhSachHoaDon;
    private DanhSachSanPham danhSachSanPham ;

    public void push(ChiTietHoaDon chiTietHoaDon) {
        dscthd = copyOf(dscthd, dscthd.length+1);
        dscthd[dscthd.length-1] = chiTietHoaDon;
    }

    public ChiTietHoaDon[] getDanhSachChiTiet() {
        return dscthd;
    }
    
    private boolean checkMaHD(int maHD, int maSP) {
        for(ChiTietHoaDon i: dscthd) {
            if(i.getMaHD()==maHD && i.getMaSP()==maSP) {
                return true; // trùng
            }
        }
        return false; // không trùng
    }

    public void updateThanhTien(int maHD) {
        HoaDon invoice = danhSachHoaDon.timKiemHoaDon(maHD);
        if(invoice==null) {
            System.out.println("Không tìm thấy hóa đơn mã "+maHD+"!\n");
            return;
        }
        float thanhTienMoi=0;
        for(ChiTietHoaDon i: dscthd) {
            if(i.getMaHD()==maHD) {
                thanhTienMoi+=i.getThanhTien();
            }
        }
        invoice.setTongTien(thanhTienMoi);
        System.out.println("Thành tiền của hóa đơn mã "+maHD+" đã được cập nhật lại với tổng thành tiền:"+thanhTienMoi+" VND!\n");
    }

    // Tìm kiếm chi tiết hóa đơn theo mã sản phẩm
    public ChiTietHoaDon timKiemTheoMaSanPham(int maSP) {
        for (int i = 0; i < dscthd.length; i++) {
            if (dscthd[i].getMaSP() == maSP) {
                return dscthd[i];
            }
        }
        System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
        return null;
    }

    // Xóa chi tiết hóa đơn theo mã hóa đơn
    public void xoaChiTietHoaDonTheoMaHoaDon(int maHD) {
        int count=0;
        int n = dscthd.length;
        for(int i=0; i<n; i++) {
            if(dscthd[i].getMaHD()==maHD) {
                for(int j=i; j<n-1; j++) {
                    dscthd[j] = dscthd[j+1];
                }
                i--;
                n--;
                count++;
            }
        }
        if(count>0) {
            dscthd = copyOf(dscthd, n);
        } else {
            System.out.println("Không tìm thấy chi tiết hóa đơn mã "+maHD+"!\n");
        }
    }

    // Xóa chi tiết hóa đơn theo mã sản phẩm
    public void xoaChiTietHoaDonTheoMaSanPham(int maSP) {
        int index = -1;
        for (int i = 0; i < dscthd.length; i++) {
            if (dscthd[i].getMaSP() == maSP) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Không tìm thấy chi tiết hóa đơn cho mã sản phẩm: " + maSP);
            return;
        }
        for (int i = index; i < dscthd.length- 1; i++) {
            dscthd[i] = dscthd[i + 1];
        }
        dscthd[dscthd.length - 1] = null;
        System.out.println("Đã xóa chi tiết hóa đơn cho mã sản phẩm: " + maSP);
    }
    
    // Tính tổng tiền của toàn bộ hóa đơn
    public double tinhTongTien() {
        double tongTien = 0;
        for (int i = 0; i < dscthd.length; i++) {
            tongTien += dscthd[i].tinhTien();
        }
        return tongTien;
    }
    
    // Xuất toàn bộ danh sách chi tiết hóa đơn
    public void xuatDanhSachChiTietHoaDon() {
        if (dscthd.length == 0) {
            System.out.println("Danh sách chi tiết hóa đơn rỗng.");
            return;
        }
        double tongCong = 0;
        System.out.println("\n*Danh sách chi tiết hóa đơn:");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-10s | %10s | %10s | %-11s|\n", "Mã Hóa Đơn", "Mã Sản Phẩm","Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < dscthd.length; i++) {
            dscthd[i].xuatChiTietHoaDon(); // Gọi phương thức xuất chi tiết hóa đơn
            tongCong+=dscthd[i].tinhTien();
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-65s | %-11.2f|\n", "Tổng cộng", tongCong);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("=> Có "+dscthd.length+" chi tiết hóa đơn trong danh sách chi tiết hóa đơn.\n");
    }

    public void xuatChiTietHoaDonTheoMHD(int maHD) {
        // Kiểm tra trước xem có hóa đơn nào không
        boolean coHoaDon = false;
        for(ChiTietHoaDon i: dscthd) {
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
        System.out.println("Chi tiết hóa đơn theo mã hóa đơn "+maHD+": ");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-12s | %-10s | %-10s | %-10s |\n", "Mã Hóa Đơn", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------------------");
        for (ChiTietHoaDon i: dscthd) {
            if (i.getMaHD() == maHD) {
                i.xuatChiTietHoaDon();
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
            BufferedReader br = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/HoaDon/ChiTietHoaDon.txt"));
            String line ;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(";");
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHD(Integer.parseInt(strings[0].trim()));
                chiTietHoaDon.setMaSP(Integer.parseInt(strings[1].trim()));
                chiTietHoaDon.setSoLuong(Integer.parseInt(strings[2].trim()));
                chiTietHoaDon.setDonGia(Float.parseFloat(strings[3].trim()));
                chiTietHoaDon.setThanhTien(Double.parseDouble(strings[3].trim()));
                push(chiTietHoaDon);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ghiFile() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("QuanLySieuThiMini/src/QuanLySieuThiMiNi/HoaDon/ChiTietHoaDon.txt"));
            String line;
            for (ChiTietHoaDon i: dscthd) {
                line = String.format("%d;%d;%d;%f;%f\n", i.getMaHD(),i.getMaSP(),i.getSoLuong(),i.getDonGia(),i.getThanhTien());
                bw.write(line);
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
