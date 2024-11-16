package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachHoaDon {
    private HoaDon[] dshd = new HoaDon[0];
    private int n;

    public DanhSachHoaDon() {
        this.n = 0;
    }

    public DanhSachHoaDon(int n) {
        dshd = new HoaDon[n];
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

    // Xem danh sách hóa đơn
    public void xemDS() {
        System.out.println("Danh Sách Hóa Đơn:");
        for(int i=0; i<n; i++){
            dshd[i].xuatHoaDon();
        }
    }

    // Thêm 1 hóa đơn vào cuối danh sách
    public void themHoaDon() {
        if(n > dshd.length) {
            System.out.println("Danh sách đã đầy, không thể thêm hóa đơn mới.");
            return;
        }
        dshd = Arrays.copyOf(dshd, n+1);
        dshd[n] = new HoaDon();
        dshd[n].nhapHoaDon();
        n++;
        System.out.println("Đã thêm 1 hóa đơn vào cuối danh sách thành công.");
    }

    // Thêm nhiều hóa đơn vào danh sách
    public void nhapDanhSachHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng hóa đơn muốn thêm: ");
        int soLuong = sc.nextInt();

        // Nếu mảng không đủ chỗ, tăng kích thước mảng
        if (n + soLuong > dshd.length) {
            dshd = Arrays.copyOf(dshd, n + soLuong);
        }

        // Nhập thông tin cho từng hóa đơn
        for (int i = n; i < n + soLuong; i++) {
            System.out.println("Nhập thông tin cho hóa đơn thứ " + (i + 1) + ": ");
            dshd[i] = new HoaDon();  // Khởi tạo đối tượng HoaDon mới
            dshd[i].nhapHoaDon();    // Gọi phương thức nhập thông tin cho hóa đơn
        }
        // Cập nhật số lượng hóa đơn đã nhập
        this.n += soLuong;
    }

    // Sửa hóa đơn
    // public suaHoaDon() {

    // }

    // Xóa hóa đơn theo Mã hóa đơn
    public void xoaHoaDonTheoMa(int maHD) {
        for(int i=0; i<n; i++) {
            if(dshd[i].getMaHD() == maHD) {
                for(int j=i; j<n-1; j++) {
                    dshd[j] = dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
                // dshd[dshd.length-1] = null;
                // n--;
                System.out.println("Đã xóa hóa đơn có mã: "+maHD);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn có mã: " + maHD);
    }

    // Xóa hóa đơn theo ngày (LocalDate)
    public void xoaHoaDonTheoNgay(String ngayXoa) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            // Kiểm tra nếu ngày tạo hóa đơn trùng với ngày xóa
            if (dshd[i].getNgayTaoHoaDon().equals(ngayXoa)) {
                // Nếu có, xóa hóa đơn đó
                for (int j = i; j < n - 1; j++) {
                    dshd[j] = dshd[j + 1];  // Dịch chuyển các phần tử sau nó lên
                }
                dshd = Arrays.copyOf(dshd, n - 1);  // Cắt bỏ phần tử cuối cùng
                n--;  // Giảm số lượng hóa đơn
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
    public void delete() {
        dshd = Arrays.copyOf(dshd, n-1);
        n--;
    }

    // Tìm kiếm hóa đơn theo mã (trả về vị trí int)
    public int timMaHoaDon(int maHD) {
        for(int i=0; i<n; i++) {
            if(dshd[i].getMaHD() == maHD) {
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD);
        return -1;
    }

    // Tìm kiếm hóa đơn theo mã (trả về HoaDon)
    public HoaDon timKiemHoaDon(int maHD) {
        for(HoaDon i: dshd) {
            if(i.getMaHD() == maHD) {
                System.out.println("Đã tìm thấy hóa đơn có mã "+maHD+"!");
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD+"!");
        return null;
    }

    // Tìm kiếm và liệt kê các hóa đơn theo ngày tạo hóa đơn
    public void timHoaDonTheoNgayTaoHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ngày tạo hóa đơn (dd-mm-yyyy): ");
        String ngayTao = sc.nextLine() ;
        boolean found=false;
        
        // Duyệt qua tất cả các hóa đơn để tìm những hóa đơn có ngày tạo bằng ngày nhập
        System.out.println("DANH SÁCH HÓA ĐƠN ");
        for (int i = 0; i < n; i++) {
            if (dshd[i].getNgayTaoHoaDon().equals(ngayTao)) {  // So sánh ngày tạo
                dshd[i].xuatHoaDon();;  // Xuất thông tin của hóa đơn tìm được
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
        for(HoaDon i: dshd) {
            if(i.getTongTien() == tongTien) {
                return i;
            }
            System.out.println("Không tìm thấy hóa đơn có tổng tiền: " + tongTien+"đ!");
        }
        return null;
    }

    // Tìm và liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập
    public void lietKeHoaDonTongTienLonHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền lớn hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN LỚN HƠN "+tongTienNhap+"đ-------------+");
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                dshd[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn lớn hơn "+tongTienNhap+"đ");
    }

    // Tìm và liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập
    public void lietKeHoaDonTongTienNhoHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền nhỏ hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN NHỎ HƠN "+tongTienNhap+"đ-------------+");
        for(int i=0; i<n; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                dshd[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn nhỏ hơn "+tongTienNhap+"đ");
    }

    // Thống kê theo tổng tiền của tất cả hóa đơn
    public void thongKeTongTienHoaDon() {
        // System.out.println("Thống kê: ");
        float tongTien = 0;
        for(int i=0; i<n; i++) {
            tongTien += dshd[i].getTongTien();
        }
        System.out.println("Tổng số tiền của tất cả các hóa đơn: "+tongTien);
    }

    // Đọc file
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("HoaDon.txt"))) {
            String line;
            int count=0;
            while((line=br.readLine()) != null && count < dshd.length) {
                String[] parts = line.split(";");




                dshd[count++] = new HoaDon(/* */);
            }
        } catch (Exception e) {
            System.out.println("Đọc file không thành công!\n");
        }
    }

    // Ghi file
    public void ghiFile() {
        
    }
}