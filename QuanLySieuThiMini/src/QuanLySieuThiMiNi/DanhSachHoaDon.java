package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachHoaDon implements ThaoTacFile {
    private HoaDon[] DS_hoaDon = new HoaDon[0];

    public DanhSachHoaDon() {}
    public DanhSachHoaDon(DanhSachHoaDon other) {
        this.DS_hoaDon = other.DS_hoaDon;
    }

    // Xem danh sách hóa đơn
    public void xemDS() {
        System.out.println("Danh Sách Hóa Đơn:");
        for(HoaDon hoaDon: DS_hoaDon){
            hoaDon.xuatHoaDon();
        }
    }

    // Thêm 1 hóa đơn vào cuối danh sách
    public void push(HoaDon hoaDon) {
        DS_hoaDon = copyOf(DS_hoaDon, DS_hoaDon.length+1) ;
        DS_hoaDon[DS_hoaDon.length-1] = hoaDon;
    }

    // Xóa hóa đơn theo Mã hóa đơn
    public void xoaHoaDonTheoMa(int maHD) {
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getMaHD() == maHD) {
                for(int j=i; j<DS_hoaDon.length-1; j++) {
                    DS_hoaDon[j] = DS_hoaDon[j+1];
                }
                DS_hoaDon = copyOf(DS_hoaDon, DS_hoaDon.length-1);
                System.out.println("Đã xóa hóa đơn có mã: "+maHD);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn có mã: " + maHD);
    }

    // Xóa hóa đơn theo ngày (LocalDate)
    public void xoaHoaDonTheoNgay(String ngayXoa) {
        boolean found = false;
        for (int i = 0; i < DS_hoaDon.length; i++) {
            // Kiểm tra nếu ngày tạo hóa đơn trùng với ngày xóa
            if (DS_hoaDon[i].getNgayTaoHoaDon().equals(ngayXoa)) {
                // Nếu có, xóa hóa đơn đó
                for (int j = i; j < DS_hoaDon.length-1; j++) {
                    DS_hoaDon[j] = DS_hoaDon[j + 1];  // Dịch chuyển các phần tử sau nó lên
                }
                DS_hoaDon = copyOf(DS_hoaDon, DS_hoaDon.length - 1);  // Cắt bỏ phần tử cuối cùng
                // Giảm số lượng hóa đơn
                System.out.println("Đã xóa hóa đơn có ngày tạo: " + ngayXoa);
                found = true;
                i--;  // Lùi chỉ số i để không bỏ qua phần tử tiếp theo
            }
        }
        if (!found) {
            System.out.println("Không có hóa đơn nào có ngày tạo: " + ngayXoa);
        }
    }

    // Xóa hóa đơn từ cuối danh sách
    public void xoaHoaDonCuoi() {
        DS_hoaDon = copyOf(DS_hoaDon, DS_hoaDon.length-1);
    }

    // Tìm kiếm hóa đơn theo mã (trả về vị trí int)
    public int timMaHoaDon(int maHD) {
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getMaHD() == maHD) {
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD);
        return -1;
    }

    // Tìm kiếm hóa đơn theo mã (trả về HoaDon)
    public HoaDon timKiemHoaDon(int maHD) {
        for(HoaDon i: DS_hoaDon) {
            if(i.getMaHD() == maHD) {
                System.out.println("Đã tìm thấy hóa đơn có mã "+maHD+"!");
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD+"!");
        return null;
    }

    public void xuatHoaDonTheoMa(int maHD) {
        for (HoaDon i: DS_hoaDon) {
            if (i.getMaHD() == maHD) {
                i.xuatHoaDon();
                return ;
            }
        }
        System.out.println("Không tìm thấy hóa đơn!\n");
    }

    // Tìm kiếm và liệt kê các hóa đơn theo ngày tạo hóa đơn
    public void timHoaDonTheoNgayTaoHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ngày tạo hóa đơn (dd-mm-yyyy): ");
        String ngayTao = sc.nextLine() ;
        boolean found=false;
        
        // Duyệt qua tất cả các hóa đơn để tìm những hóa đơn có ngày tạo bằng ngày nhập
        System.out.println("DANH SÁCH HÓA ĐƠN ");
        for (int i = 0; i < DS_hoaDon.length; i++) {
            if (DS_hoaDon[i].getNgayTaoHoaDon().equals(ngayTao)) {  // So sánh ngày tạo
                DS_hoaDon[i].xuatHoaDon();;  // Xuất thông tin của hóa đơn tìm được
                found = true;  // Đánh dấu là đã tìm thấy ít nhất một hóa đơn
            }
        }

        // Nếu không tìm thấy hóa đơn nào
        if (!found) {
            System.out.println("Không có hóa đơn nào được tạo vào ngày " + ngayTao);
        }
    }

    // Tìm kiếm hóa đơn theo giá trị tổng tiền (Trả về kiểu HoaDon)
    public HoaDon timHoaDonTheoTongTien(float tongTien) {
        for(HoaDon i: DS_hoaDon) {
            if(i.getTongTien() == tongTien) {
                System.out.println("Đã tìm thấy hóa đơn có tổng tiền: " + tongTien+"đ!");
                return i;
            }
        }
        return null;
    }

    // Thống kê theo tổng tiền của tất cả hóa đơn
    public void thongKeTongTienHoaDon() {
        float tongTien = 0;
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i] != null) {
                tongTien += DS_hoaDon[i].getTongTien();
            }
        }
        System.out.println("Tổng số tiền của tất cả các hóa đơn: "+tongTien+" VND.");
    }

    // Thống kê số lượng hóa đơn trả kiểu void
    public void thongKeSoLuongHoaDon1() {
        int countHoaDon=0;
        for(HoaDon i: DS_hoaDon) {
            if(i != null) {
                countHoaDon++;
            }
        }
        System.out.println("Số lượng hóa đơn: "+countHoaDon+" cái hóa đơn.");
    }

    // Thống kê số lượng hóa đơn trả về kiểu int
    public int thongKeSoLuongHoaDon2() {
        int countHoaDon=0;
        for(HoaDon i: DS_hoaDon) {
            if(i != null) {
                countHoaDon++;
            }
        }
        return countHoaDon;
    }

    // Thống kê hóa đơn theo ngày tạo hóa đơn

    // Thống kê hóa đơn theo ngày tháng năm
    public void thongKeHoaDonTheoNgayThangNam() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while (true) {
            try {
                System.out.println("Nhập ngày (dd/MM/yyyy): ");
                String ngayStr = sc.nextLine().trim();

                LocalDate date = LocalDate.parse(ngayStr, formatter);
                int ngay = date.getDayOfMonth();
                int thang = date.getMonthValue();
                int nam = date.getYear();

                float tongDoanhThu=0;
                int hoaDonCount=0;
                float tongDoanhThuTrungBinh=0;

                for(HoaDon hoaDon: DS_hoaDon) {
                    if(hoaDon != null) {
                        LocalDate ngayNhap = LocalDate.parse(hoaDon.getNgayTaoHoaDon(), formatter);

                        if(ngayNhap.getDayOfMonth()==ngay && ngayNhap.getMonthValue()==thang && ngayNhap.getYear()==nam) {
                            hoaDonCount++;
                            tongDoanhThu+=hoaDon.getTongTien();
                        }
                    }
                }
                if(hoaDonCount==0) {
                    System.out.println("Không có hóa đơn nào trong "+ngayStr+".");
                    //////////////////
                    return;
                }
                tongDoanhThuTrungBinh=tongDoanhThu/hoaDonCount;

                System.out.println("Số lượng hóa đơn trong ngày " + ngayStr + ": " + hoaDonCount);
                System.out.printf("Tổng doanh thu trong ngày " + ngayStr + ": %.2f VND", tongDoanhThu);
                System.out.printf("Doanh thu trung bình: %.2f VND\n", tongDoanhThuTrungBinh);
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một giá trị hợp lệ.");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: "+e.getMessage());
            }
        }
    }

    // Thống kê hóa đơn theo tháng năm
    public void thongKeHoaDonTheoThangNam() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        while (true) {
            try {
                System.out.println("Nhập tháng năm: ");
                String ngayNhap = sc.nextLine().trim();

                LocalDateTime date = LocalDateTime.parse(ngayNhap, formatter);
                int thang = date.getMonthValue();
                int nam = date.getYear();

                int soluongHoaDon=0;
                float tongDoanhThu=0;
                float tongDoanhThuTrungBinh=0;
                
                for(HoaDon hoaDon: DS_hoaDon) {
                    if(hoaDon != null) {
                        LocalDateTime ngay = LocalDateTime.parse(ngayNhap, formatter);
                        if(ngay.getMonthValue()==thang && ngay.getYear()==nam) {
                            soluongHoaDon++;
                            tongDoanhThu+=hoaDon.getTongTien();
                        }
                    }
                }
                tongDoanhThuTrungBinh = tongDoanhThu / soluongHoaDon;

                if(soluongHoaDon==0) {
                    System.out.println("Không có hóa đơn nào trong tháng "+thang+" năm "+nam);
                }

                System.out.println("Số lượng hóa đơn trong "+ngayNhap+": "+soluongHoaDon);
                System.out.println("Tổng doanh thu trong "+ngayNhap+": "+tongDoanhThu);
                System.out.println("Số lượng hóa đơn trong "+ngayNhap+": "+tongDoanhThuTrungBinh);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng MM/yyyy.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập giá trị hợp lệ.");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: "+e.getMessage());
            }
        }
    }


    // Tìm và liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập
    public void lietKeHoaDonTongTienLonHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getTongTien() > tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền lớn hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN LỚN HƠN "+tongTienNhap+"vnd-------------+");
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getTongTien() > tongTienNhap) {
                DS_hoaDon[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn lớn hơn "+tongTienNhap+"vnd");
    }

    // Tìm và liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập
    public void lietKeHoaDonTongTienNhoHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i< DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getTongTien() < tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền nhỏ hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN NHỎ HƠN "+tongTienNhap+"đ-------------+");
        for(int i=0; i<DS_hoaDon.length; i++) {
            if(DS_hoaDon[i].getTongTien() < tongTienNhap) {
                DS_hoaDon[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn nhỏ hơn "+tongTienNhap+"vnd");
    }

    // Đọc file
    public void docFile() {}

    // Ghi file
    public void ghiFile() {
        
    }

    public void capNhatFile() {
        
    }
}