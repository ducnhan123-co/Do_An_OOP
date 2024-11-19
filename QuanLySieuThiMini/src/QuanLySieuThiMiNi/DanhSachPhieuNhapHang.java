package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhieuNhapHang {
    private PhieuNhapHang[] phieu = new PhieuNhapHang[0];
    private int n = 0;

    public void themPhieuNhapHang() {
        phieu = Arrays.copyOf(phieu, n + 1);
        phieu[n] = new PhieuNhapHang();
        phieu[n].nhapPhieu();
        n++;
    }
    
    public PhieuNhapHang[] getDsPhieu() {
        return phieu;
    }

    public void xuatPhieuNhapHang() {
        if (n == 0) {
            System.out.println("Danh sách trống.");
            return;
        }

        // In danh sách các phiếu nhập (phần thông tin cơ bản của từng phiếu)
        System.out.println("Danh sách phiếu nhập:");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", "Mã phiếu", "Mã NCC", "Mã NV", "Tổng tiền", "Ngày nhập");
        for (int i = 0; i < n; i++) 
            phieu[i].xuatPhieu();
        	
    }




    public int timGanDungTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            String maNCC = String.valueOf(phieu[i].getMaPhieu());
            if (maNCC.contains(ma))
                return i;
        }
        return -1;
    }
    
    public void xoaTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần xóa: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);

        if (vt != -1) {
            for (int i = vt; i < n - 1; i++)
                phieu[i] = phieu[i + 1];
            phieu = Arrays.copyOf(phieu, n - 1);
            n--;
            System.out.println("Đã xóa phiếu nhập hàng với mã: " + mp);
        } 
        else
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
    }


    public void timMP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần tìm: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);
        if (vt != -1)
            phieu[vt].xuatPhieu();
        else
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
    }

    public void suaPhieuNhapHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần sửa: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);

        if (vt != -1) {
            System.out.println("Đang sửa phiếu nhập hàng với mã: " + mp);

            System.out.print("Nhập mã nhà cung cấp mới (hiện tại: " + phieu[vt].getMaNCC() + "): ");
            int maNCC = sc.nextInt();
            phieu[vt].setMaNCC(maNCC);

            // Nhập ngày nhập hàng mới với định dạng dd/MM/yyyy
            sc.nextLine(); // Clear the buffer
            System.out.print("Nhập ngày nhập hàng mới (dd/MM/yyyy): ");
            String ngayNhapStr = sc.nextLine();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate ngayNhapHang = LocalDate.parse(ngayNhapStr, formatter);
                phieu[vt].setNgayNhapHang(ngayNhapHang);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
                return;
            }

//            while (true) {
//                System.out.print("Nhập mã sản phẩm (hoặc nhập -1 để kết thúc): ");
//                int maSP = sc.nextInt();
//
//                if (maSP == -1) {
//                    break;
//                }
//
//                System.out.print("Nhập số lượng mới: "); 
//                int soLuong = sc.nextInt();
//
//                System.out.print("Nhập đơn giá mới: ");
//                double donGia = sc.nextDouble();
//
//                phieu[vt].addOrUpdateProduct(maSP, soLuong, donGia);
//            }

            phieu[vt].updateTongTien();
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
        }
    }
    
    public void thongKeTheoNgay() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ngày (dd/MM/yyyy): ");
        String ngayStr = sc.nextLine();
        
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try {
            LocalDate ngay = LocalDate.parse(ngayStr, formatter); // Chuyển chuỗi thành LocalDate
            System.out.println("Danh sách phiếu nhập theo ngày " + ngayStr + ":");
            
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (phieu[i].getNgayNhapHang().equals(ngay)) {
                    phieu[i].xuatPhieu();
                    found = true;
                }
            }
            
            if (!found) 
                System.out.println("Không có phiếu nhập nào vào ngày " + ngayStr);
            
        } catch (Exception e) {
            System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
        }
    }


    public void thongKeTheoThang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tháng (MM/yyyy): ");
        String thangStr = sc.nextLine();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        
        try {
            // Chuyển chuỗi đầu vào thành tháng và năm
            YearMonth thang = YearMonth.parse(thangStr, formatter);
            System.out.println("Danh sách phiếu nhập theo tháng " + thangStr + ":");
            
            boolean found = false;
            for (int i = 0; i < n; i++) {
                YearMonth thangPhieu = YearMonth.from(phieu[i].getNgayNhapHang());
                if (thang.equals(thangPhieu)) {
                    phieu[i].xuatPhieu();
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("Không có phiếu nhập nào trong tháng " + thangStr);
            }
        } catch (Exception e) {
            System.out.println("Tháng nhập không hợp lệ. Vui lòng nhập theo định dạng MM/yyyy.");
        }
    }


    public void thongKeTheoNam() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm (yyyy): ");
        String namStr = sc.nextLine();
        
        try {
            int nam = Integer.parseInt(namStr);
            System.out.println("Danh sách phiếu nhập theo năm " + nam + ":");
            
            boolean found = false;
            for (int i = 0; i < n; i++) {
                int namPhieu = phieu[i].getNgayNhapHang().getYear();
                if (namPhieu == nam) {
                    phieu[i].xuatPhieu();
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("Không có phiếu nhập nào trong năm " + nam);
            }
        } catch (Exception e) {
            System.out.println("Năm nhập không hợp lệ. Vui lòng nhập theo định dạng yyyy.");
        }
    }

 
}
