package QuanLySieuThiMiNi.PhieuNhapHang;

import QuanLySieuThiMiNi.NhaCungCap.DanhSachNhaCungCap;
import QuanLySieuThiMiNi.NhanVien.DanhSachNhanVien;
import QuanLySieuThiMiNi.SanPham.DanhSachSanPham;
import QuanLySieuThiMiNi.SanPham.SanPham;
import QuanLySieuThiMiNi.ThaoTacFile;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

import static java.util.Arrays.copyOf;


public class DanhSachPhieuNhapHang implements ThaoTacFile {
    private PhieuNhapHang[] dsPhieu;
    private DanhSachSanPham danhSachSanPham;
    private int n ;
//    private DanhSachChiTietPhieuNhapHang dsChiTiet;
    
    public DanhSachPhieuNhapHang() {
        // Khởi tạo mảng phiếu nhập hàng với kích thước mặc định
        dsPhieu = new PhieuNhapHang[100]; // Hoặc kích thước cần thiết
        n = 0; // Số lượng phiếu bắt đầu là 0
    }

    public void push (PhieuNhapHang phieuNhapHang) {
        dsPhieu = copyOf(dsPhieu, dsPhieu.length+1);
        dsPhieu[n++] = phieuNhapHang;
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
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-12s | %-12s | %-10s |\n", "Mã phiếu", "Mã Nhà Cung Cấp", "Mã Nhân Viên", "Tổng tiền", "Ngày nhập");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) 
            dsPhieu[i].xuatPhieu();
        System.out.println("---------------------------------------------------------------------------\n");
    }



    public void nhapPhieuVaChiTiet() {
        while (true) {

            try {
                DanhSachNhaCungCap NCC = new DanhSachNhaCungCap();
                DanhSachChiTietPhieuNhapHang danhSachChiTietPhieuNhapHang = new DanhSachChiTietPhieuNhapHang();
                DanhSachSanPham danhSachSanPham = new DanhSachSanPham();
                DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();

                Scanner in = new Scanner(System.in);
                PhieuNhapHang phieuNhapHang = new PhieuNhapHang();

                System.out.println("nhap ma phieu");
                phieuNhapHang.setMaPhieu(in.nextInt());
                if (kiemTraMaPhieuTonTai(phieuNhapHang.getMaPhieu()))
                    throw new Exception("ma phieu da ton tai");

                System.out.println("nhap ma nha cung cap: ");
                phieuNhapHang.setMaNCC(in.nextInt());
                if (!NCC.checkMa(phieuNhapHang.getMaNCC()))
                    throw new Exception("khong ton tai nha cung cap");

                System.out.println("nhap ma nhan vien:");
                phieuNhapHang.setMaNhanVien(in.nextInt());
                if (!danhSachNhanVien.kiemTraTrungMaNhanVien(phieuNhapHang.getMaNhanVien()))
                    throw new Exception("khong tim thay nhan vien");

                double tongtien = 0;

                System.out.println("nhap chi tiet phieu nhap hang:");
                while (true) {
                    try {
                        Scanner inn = new Scanner(System.in);
                        ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang();

                        chiTietPhieuNhapHang.setMaPhieu(phieuNhapHang.getMaPhieu());

                        System.out.println("nhap ma san pham: ");
                        chiTietPhieuNhapHang.setMaSp(inn.nextInt());
                        if (danhSachSanPham.timSanPhamTheoMa(chiTietPhieuNhapHang.getMaSp()) == null)
                            throw new Exception("ma san pham khong ton tai");

                        System.out.println("nhap so luong:");
                        chiTietPhieuNhapHang.setSl(inn.nextInt());

                        SanPham sanPham = danhSachSanPham.timSanPhamTheoMa(chiTietPhieuNhapHang.getMaSp());
                        chiTietPhieuNhapHang.setDonGia(sanPham.getDonGia());
                        chiTietPhieuNhapHang.setThanhTien(sanPham.getDonGia()*chiTietPhieuNhapHang.getSl());

                        tongtien += chiTietPhieuNhapHang.getThanhTien();

                        danhSachChiTietPhieuNhapHang.push(chiTietPhieuNhapHang);

                        System.out.println("(0) tiep tuc");
                        System.out.println("(1) dung lai");
                        if (inn.nextInt() == 1) {
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                phieuNhapHang.setTongTien(tongtien);
                LocalDate time = LocalDate.now();
                phieuNhapHang.setNgayNhapHang(time);
                push(phieuNhapHang);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hienThiPhieuVaChiTiet(DanhSachChiTietPhieuNhapHang dsChiTiet) {
        if (n == 0) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            DANH SÁCH PHIẾU NHẬP TRỐNG                       ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            return;
        }

        System.out.println("╔═════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                     DANH SÁCH PHIẾU NHẬP HÀNG VÀ CHI TIẾT                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");

        for (int i = 0; i < n; i++) {
            System.out.printf("╔═════════════════════════════════════════════════════════════════════════════╗\n");
            System.out.printf("║ Phiếu nhập hàng %-4d                                                        ║\n", dsPhieu[i].getMaPhieu());
            System.out.printf("║ %-10s │ %-15s │ %-12s │ %-12s │ %-15s║\n", "Mã phiếu", "Mã NCC", "Mã NV", "Tổng tiền", "Ngày nhập");
            System.out.printf("║ %-10d │ %-15d │ %-12d │ %-12.2f │ %-15s║\n",
                    dsPhieu[i].getMaPhieu(), dsPhieu[i].getMaNCC(), dsPhieu[i].getMaNhanVien(), dsPhieu[i].getTongTien(), dsPhieu[i].getNgayNhapHang());
            System.out.printf("╚═════════════════════════════════════════════════════════════════════════════╝\n");

            System.out.println("Chi tiết phiếu nhập hàng thứ : "+ (i+1));
            System.out.printf("┌────────────┬──────────────┬──────────┬────────────┬───────────────┐\n");
            System.out.printf("│ %-10s │ %-12s │ %-8s │ %-10s │ %-13s │\n",
                    "Mã Phiếu", "Mã SP", "Số Lượng", "Đơn Giá", "Thành Tiền");
            System.out.printf("├────────────┼──────────────┼──────────┼────────────┼───────────────┤\n");



            dsChiTiet.inChiTietTheoMaPhieu(dsPhieu[i].getMaPhieu());

            // Kết thúc bảng chi tiết
            System.out.printf("└────────────┴──────────────┴──────────┴────────────┴───────────────┘\n");

        }
    }




    public void xoaPhieuTheoMaPhieu(int maPhieu) {
        int vt = -1;

        // Tìm vị trí phiếu nhập hàng cần xóa
        for (int i = 0; i < n; i++) {
            if (dsPhieu[i].getMaPhieu() == maPhieu) {
                vt = i;
                break;
            }
        }

        if (vt != -1) {
            // Dịch chuyển các phần tử sau vị trí xóa lên trước
            for (int i = vt; i < n - 1; i++) {
                dsPhieu[i] = dsPhieu[i + 1];
            }
            dsPhieu = copyOf(dsPhieu, n - 1); // Thu gọn mảng
            n--;
            System.out.println("Đã xóa phiếu với mã phiếu: " + maPhieu);
        } else {
            System.out.println("Không tìm thấy phiếu với mã phiếu: " + maPhieu);
        }
    }




    public int timGanDungTheoMa(int ma) {
        for (int i = 0; i < n; i++) {
            if (dsPhieu[i].getMaPhieu() == ma) // So sánh trực tiếp kiểu int
                return i;
        }
        return -1;
    }

    public void timMP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần tìm: ");
        int mp = sc.nextInt();
        int vt = timGanDungTheoMa(mp);
        if (vt != -1)
            dsPhieu[vt].xuatPhieu();
        else
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
    }

    public void suaPhieuNhapHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần sửa: ");
        int mp = sc.nextInt();
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
    
    public void thongKeTongTienTheoQuyVaNam() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm cần thống kê: ");
        int namCanThongKe = sc.nextInt();

        double tongTienQuy1 = 0;
        double tongTienQuy2 = 0;
        double tongTienQuy3 = 0;
        double tongTienQuy4 = 0;

        for (int i = 0; i < n; i++) {
            PhieuNhapHang phieu = dsPhieu[i];
            int thang = phieu.getNgayNhapHang().getMonthValue();
            int nam = phieu.getNgayNhapHang().getYear();
            double tongTien = phieu.getTongTien();

            // Chỉ tính tổng tiền của năm được chỉ định
            if (nam == namCanThongKe) {
                if (thang >= 1 && thang <= 3) {
                    tongTienQuy1 += tongTien;
                } else if (thang >= 4 && thang <= 6) {
                    tongTienQuy2 += tongTien;
                } else if (thang >= 7 && thang <= 9) {
                    tongTienQuy3 += tongTien;
                } else if (thang >= 10 && thang <= 12) {
                    tongTienQuy4 += tongTien;
                }
            }
        }

        // Sử dụng DecimalFormat để thay đổi dấu phân cách
        DecimalFormat df = new DecimalFormat("#,###.###");

        System.out.println("Thống kê tổng tiền theo quý trong năm " + namCanThongKe + ":");
        System.out.println("╔═════════╦════════════════════╗");
        System.out.printf("║ %-7s ║ %-18s ║\n", "Quý", "Tổng tiền");
        System.out.println("╠═════════╬════════════════════╣");
        System.out.printf("║ %-7s ║ %-18s ║\n", "1", df.format(tongTienQuy1));
        System.out.printf("║ %-7s ║ %-18s ║\n", "2", df.format(tongTienQuy2));
        System.out.printf("║ %-7s ║ %-18s ║\n", "3", df.format(tongTienQuy3));
        System.out.printf("║ %-7s ║ %-18s ║\n", "4", df.format(tongTienQuy4));
        System.out.println("╚═════════╩════════════════════╝");
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
        String filename = "QuanLySieuThiMini/src/QuanLySieuThiMiNi/PhieuNhapHang/PhieuNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                PhieuNhapHang phieuNhap = parseLineToPhieuNhapHang(line);
                if (phieuNhap != null) {
                    // Kiểm tra nếu mảng đã đầy, thì tăng kích thước mảng
                    if (n >= dsPhieu.length) {
                        // Tăng kích thước mảng gấp đôi khi mảng đầy
                        dsPhieu = copyOf(dsPhieu, dsPhieu.length + 10);
                    }
                    dsPhieu[n++] = phieuNhap;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
//            System.out.println("Đã thêm " + count + " phiếu nhập hàng từ tệp tin: " + filename);
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