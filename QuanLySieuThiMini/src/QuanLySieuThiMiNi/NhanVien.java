package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class NhanVien {
    private int manv;
    private String chucvu;
    private String honhanvien;
    private String tennhanvien;
    private String ngaysinh;
    private String diachi;
    private String sodienthoai;
    private double luong;
    private String gioiTinh;

    public NhanVien() {
    }

    public NhanVien(int manv, String chucvu, String honhanvien, String tennhanvien, String ngaysinh, String diachi, String sodienthoai, double luong, String gioiTinh) {
        this.manv = manv;
        this.chucvu = chucvu;
        this.honhanvien = honhanvien;
        this.tennhanvien = tennhanvien;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.luong = luong;
        this.gioiTinh = gioiTinh;
    }

    public NhanVien(NhanVien nhanVien)
    {
        this.manv = nhanVien.manv;
        this.chucvu = nhanVien.chucvu;
        this.honhanvien = nhanVien.honhanvien;
        this.tennhanvien= nhanVien.tennhanvien;
        this.ngaysinh = nhanVien.ngaysinh;
        this.diachi = nhanVien.diachi;
        this.sodienthoai = nhanVien.sodienthoai;
        this.luong = nhanVien.luong;
        this.gioiTinh = nhanVien.gioiTinh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getHonhanvien() {
        return honhanvien;
    }

    public void setHonhanvien(String honhanvien) {
        this.honhanvien = honhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

//    private int manv;
//    private String chucvu;
//    private String honhanvien;
//    private String tennhanvien;
//    private String ngaysinh;
//    private String diachi;
//    private String sodienthoai;
//    private double luong;
//    private String gioiTinh;

    public void nhapNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin nhân viên");

        System.out.println("Nhập mã nhân viên:");
        this.manv = sc.nextInt();
        sc.nextLine();  // Đọc dòng trống sau khi nhập số

        System.out.println("Nhập chức vụ:");
        this.chucvu = sc.nextLine();

        System.out.println("Nhập họ của nhân viên:");
        this.honhanvien = sc.nextLine();

        System.out.println("Nhập tên nhân viên:");
        this.tennhanvien = sc.nextLine();

        // Nhập ngày sinh với xử lý try-catch
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Nhập ngày sinh (định dạng yyyy-MM-dd): ");
            String ngaySinhStr = sc.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
                this.ngaysinh = ngaySinh.toString();
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            }
        }

        System.out.println("Nhập địa chỉ:");
        this.diachi = sc.nextLine();

        System.out.println("Nhập số điện thoại:");
        this.sodienthoai = sc.nextLine();

        System.out.println("Nhập lương nhân viên:");
        this.luong = sc.nextDouble();
        sc.nextLine();

        System.out.println("Nhập giới tính nhân viên:");
        this.gioiTinh = sc.nextLine();
    }

    public void suaNhanVien() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sửa thông tin nhân viên");

        // Sửa chức vụ
        System.out.print("Nhập chức vụ mới (nhấn Enter để giữ nguyên): ");
        String newChucVu = sc.nextLine();
        if (!newChucVu.isEmpty()) {
            this.chucvu = newChucVu;
        }

        // Sửa họ
        System.out.print("Nhập họ mới (nhấn Enter để giữ nguyên): ");
        String newHo = sc.nextLine();
        if (!newHo.isEmpty()) {
            this.honhanvien = newHo;
        }

        // Sửa tên
        System.out.print("Nhập tên mới (nhấn Enter để giữ nguyên): ");
        String newTen = sc.nextLine();
        if (!newTen.isEmpty()) {
            this.tennhanvien = newTen;
        }

        // Sửa địa chỉ
        System.out.print("Nhập địa chỉ mới (nhấn Enter để giữ nguyên): ");
        String newDiaChi = sc.nextLine();
        if (!newDiaChi.isEmpty()) {
            this.diachi = newDiaChi;
        }

        // Sửa số điện thoại
        System.out.print("Nhập số điện thoại mới (nhấn Enter để giữ nguyên): ");
        String newSoDienThoai = sc.nextLine();
        if (!newSoDienThoai.isEmpty()) {
            this.sodienthoai = newSoDienThoai;
        }

        // Sửa lương
        System.out.print("Nhập lương mới (nhập 0 để giữ nguyên): ");
        double newLuong = sc.nextDouble();
        sc.nextLine();  // Đọc dòng trống sau khi nhập số
        if (newLuong != 0) {  // Giữ nguyên nếu người dùng nhập 0
            this.luong = newLuong;
        }

        System.out.println("Cập nhật thông tin nhân viên hoàn tất!");
    }
   public int getNamSinh(NhanVien nhanVien)
   {
       String ngaySinh=nhanVien.getNgaysinh();
       try {
           DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
           LocalDate birthDate = LocalDate.parse(ngaySinh, formatter);
           return birthDate.getYear();
       } catch (DateTimeParseException e) {
           System.out.println("Ngay sinh khong hop le: "+ngaySinh);
           return -1;
       }
   }

}
