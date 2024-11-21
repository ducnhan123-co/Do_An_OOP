package QuanLySieuThiMiNi;

import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhieuNhapHang implements ThaoTacFile{
    private PhieuNhapHang[] dsPhieu = new PhieuNhapHang[0];
    private int n = 0;

    public void themPhieuNhapHang() {
        while (true) {
            // Tạo phiếu mới
            PhieuNhapHang phieuMoi = new PhieuNhapHang();
            phieuMoi.nhapPhieu(); // Nhập thông tin phiếu (bao gồm cả mã phiếu)

            // Kiểm tra mã phiếu trùng lặp
            if (kiemTraMaPhieuTonTai(phieuMoi.getMaPhieu())) {
                System.out.println("Mã phiếu đã tồn tại. Vui lòng nhập lại thông tin phiếu.");
            } else {
                // Mã phiếu hợp lệ, thêm vào danh sách
                dsPhieu = Arrays.copyOf(dsPhieu, n + 1);
                dsPhieu[n] = phieuMoi;
                n++;
                break; // Thoát khỏi vòng lặp khi thêm phiếu thành công
            }
        }
    }

    // Kiểm tra mã phiếu trùng lặp
    private boolean kiemTraMaPhieuTonTai(int maPhieuMoi) {
        for (int i = 0; i < n; i++) {
            if (dsPhieu[i].getMaPhieu() == maPhieuMoi) {
                return true; // Mã phiếu đã tồn tại
            }
        }
        return false; // Mã phiếu chưa tồn tại
    }

    
    public PhieuNhapHang[] getDsPhieu() {
        return dsPhieu;
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
            dsPhieu[i].xuatPhieu();
        	
    }




    public int timGanDungTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            String maNCC = String.valueOf(dsPhieu[i].getMaPhieu());
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
                dsPhieu[i] = dsPhieu[i + 1];
            dsPhieu = Arrays.copyOf(dsPhieu, n - 1);
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
            dsPhieu[vt].xuatPhieu();
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

            System.out.print("Nhập mã nhà cung cấp mới (hiện tại: " + dsPhieu[vt].getMaNCC() + "): ");
            int maNCC = sc.nextInt();
            dsPhieu[vt].setMaNCC(maNCC);

            // Nhập ngày nhập hàng mới với định dạng dd/MM/yyyy
            System.out.print("Nhập ngày nhập hàng mới (dd/MM/yyyy): ");
            sc.nextLine(); 
            String ngayNhapStr = sc.nextLine();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate ngayNhapHang = LocalDate.parse(ngayNhapStr, formatter);
                dsPhieu[vt].setNgayNhapHang(ngayNhapHang);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
                return;
            }
            
            System.out.println("Đã sửa thành công mã phiếu: " + mp);
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
                if (dsPhieu[i].getNgayNhapHang().equals(ngay)) {
                    dsPhieu[i].xuatPhieu();
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
                YearMonth thangPhieu = YearMonth.from(dsPhieu[i].getNgayNhapHang());
                if (thang.equals(thangPhieu)) {
                    dsPhieu[i].xuatPhieu();
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
                int namPhieu = dsPhieu[i].getNgayNhapHang().getYear();
                if (namPhieu == nam) {
                    dsPhieu[i].xuatPhieu();
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
    
    public PhieuNhapHang parseLineToPhieuNhapHang(String line) {
        String[] parts = line.split(";");
        if (parts.length == 5) { // Đảm bảo đủ 5 trường dữ liệu
            try {
                int maPhieuNhap = Integer.parseInt(parts[0].trim()); // Mã phiếu nhập
                int maNV = Integer.parseInt(parts[1].trim()); // Mã nhân viên
                int maNCC = Integer.parseInt(parts[2].trim()); // Mã nhà cung cấp
                LocalDate ngayNH = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Ngày nhập hàng
                double tongTien = Double.parseDouble(parts[4].trim()); // Tổng tiền

                // Tạo đối tượng PhieuNhapHang với các tham số vừa đọc
                return new PhieuNhapHang(maPhieuNhap, maNV, maNCC, tongTien, ngayNH);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng số trong dòng: " + line);
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi định dạng ngày tháng trong dòng: " + line);
            }
        } else {
            System.out.println("Số lượng cột không khớp trong dòng: " + line);
        }
        return null;
    }

    @Override
    public void docFile() {
        String filename = "src\\QuanLySieuThiMiNi\\PhieuNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                PhieuNhapHang phieuNhap = parseLineToPhieuNhapHang(line);
                if (phieuNhap != null) {
                    // Kiểm tra nếu mảng đã đầy, thì tăng kích thước mảng
                    if (n >= dsPhieu.length) {
                        // Tăng kích thước mảng gấp đôi khi mảng đầy
                        dsPhieu = Arrays.copyOf(dsPhieu, dsPhieu.length + 10);
                    }
                    dsPhieu[n++] = phieuNhap;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
            System.out.println("Đã thêm " + count + " phiếu nhập hàng từ tệp tin: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin: " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void ghiFile() {
        String filename = "D:\\Desktop\\ALL\\DO_AN_OOP_JAVA\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\PhieuNhapHang.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                PhieuNhapHang phieuNhap = dsPhieu[i];
                writer.write(phieuNhap.getMaPhieu() + ";" +
                             phieuNhap.getMaNhanVien() + ";" +
                             phieuNhap.getMaNCC() + ";" +
                             phieuNhap.getNgayNhapHang().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                             phieuNhap.getTongTien());
                writer.newLine();
            }
            System.out.println("Đã ghi file thành công.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void capNhatFile() {
        ghiFile(); // Cập nhật file bằng cách ghi đè toàn bộ nội dung
    }


 

   
    
}