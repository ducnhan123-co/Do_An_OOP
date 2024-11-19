package QuanLySieuThiMiNi;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

<<<<<<< Updated upstream
public class DanhSachPhieuNhapHang implements ThaoTacFile {
    private PhieuNhapHang[] phieu = new PhieuNhapHang[10];
=======
public class DanhSachPhieuNhapHang implements ThaoTacFile, Update_Kho{
    private PhieuNhapHang[] phieu = new PhieuNhapHang[0];
>>>>>>> Stashed changes
    private int n = 0;
    
    public void themPhieuNhapHang() {
        if (n >= phieu.length) {
            // Tăng kích thước mảng khi đã đầy
            phieu = Arrays.copyOf(phieu, phieu.length * 2); // Tăng kích thước gấp đôi
            System.out.println("Danh sách đã đầy, tăng kích thước mảng.");
        }

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

    public PhieuNhapHang parseLineToPhieuNhapHang(String line) {
        String[] parts = line.split(";");
        if (parts.length == 5) { // Đảm bảo đủ 5 trường dữ liệu
            try {
                int maPhieuNhap = Integer.parseInt(parts[0].trim()); // Mã phiếu nhập
                int maNV = Integer.parseInt(parts[1].trim()); // Mã nhân viên
                int maNCC = Integer.parseInt(parts[2].trim()); // Mã nhà cung cấp

                // Xử lý ngày nhập hàng
                LocalDate ngayNH = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Ngày nhập hàng

                // Xử lý tổng tiền, nếu có giá trị không hợp lệ thì gán về 0
                double tongTien = 0.0;
                try {
                    tongTien = Double.parseDouble(parts[4].trim()); // Tổng tiền
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi định dạng tổng tiền trong dòng: " + line + ". Gán giá trị tổng tiền = 0.");
                }

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
        String filename = "D:\\ALL\\Do_An_OOP\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\PhieuNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                PhieuNhapHang phieuNhap = parseLineToPhieuNhapHang(line);
                if (phieuNhap != null) {
                    // Kiểm tra nếu danh sách đầy, sẽ tự động mở rộng
                    if (n >= phieu.length) {
                        phieu = Arrays.copyOf(phieu, phieu.length * 2); // Tăng kích thước mảng khi cần thiết
                        System.out.println("Danh sách phiếu nhập hàng đã đầy, tăng kích thước mảng.");
                    }
                    phieu[n++] = phieuNhap;
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

    }

    @Override
    public void capNhatFile() {
        ghiFile(); // Cập nhật file bằng cách ghi đè toàn bộ nội dung
    }

	@Override
	public void truKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void congKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
		// TODO Auto-generated method stub
		
	}

    
}
