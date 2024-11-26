package QuanLySieuThiMiNi;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static QuanLySieuThiMiNi.DanhSachNhanVien.dsnv;
public class NhanVien implements ThaoTacFile {
    private int manv;
    private String chucvu;
    private String honhanvien;
    private String tennhanvien;
    private LocalDate ngaysinh;
    private String diachi;
    private String sodienthoai;
    private double luong;
    private String gioiTinh;
    private LocalDate ngaybatdau;
    private static int tongSoNhanVien = 0;
    public NhanVien() {
        tongSoNhanVien++;
    }
    public NhanVien(int manv, String chucvu, String honhanvien, String tennhanvien, LocalDate ngaysinh, String diachi, String sodienthoai, double luong, String gioiTinh,LocalDate ngaybatdau) {
        this.manv = manv;
        this.chucvu = chucvu;
        this.honhanvien = honhanvien;
        this.tennhanvien = tennhanvien;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.luong = luong;
        this.gioiTinh = gioiTinh;
        this.ngaybatdau=ngaybatdau;
        tongSoNhanVien++;
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
    public LocalDate getNgaysinh() {
        return ngaysinh;
    }
    public void setNgaysinh(LocalDate ngaysinh) {
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
    public void setNgaybatdau(LocalDate ngaybatdau)
    {
        this.ngaybatdau=ngaybatdau;
    }
    public LocalDate getNgaybatdau()
    {
        return ngaybatdau;
    }
    public static void setTongSoNhanVien(int tongSoNhanVien) {
        NhanVien.tongSoNhanVien = tongSoNhanVien;
    }
    public String toFileString() {
        return manv + ";" + chucvu + ";" + honhanvien + ";" + tennhanvien + ";" +
                ngaysinh.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                diachi + ";" + sodienthoai + ";" + luong + ";" + gioiTinh + ";" + ngaybatdau;
    }
    public void nhapNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin nhân viên");
        System.out.print("Nhập chức vụ: ");
        this.chucvu = sc.nextLine();
        System.out.print("Nhập họ của nhân viên: ");
        this.honhanvien = sc.nextLine();
        System.out.print("Nhập tên nhân viên: ");
        this.tennhanvien = sc.nextLine();
        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                this.ngaysinh = LocalDate.parse(dateInput, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ, vui lòng nhập lại.");
            }
        }
        System.out.print("Nhập địa chỉ: ");
        this.diachi = sc.nextLine();
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            this.sodienthoai = sc.nextLine();
            if (this.sodienthoai.matches("\\d+")) {
                break;
            } else {
                System.out.println("Số điện thoại chỉ nên chứa chữ số. Vui lòng nhập lại.");
            }
        }
        while (true) {
            System.out.print("Nhập lương nhân viên: ");
            this.luong = sc.nextDouble();
            sc.nextLine(); // Consume newline
            if (this.luong >= 0) {
                break;
            } else {
                System.out.println("Lương không thể là giá trị âm. Vui lòng nhập lại.");
            }
        }
        System.out.print("Nhập giới tính nhân viên: ");
        this.gioiTinh = sc.nextLine();
        while (true) {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                this.ngaybatdau = LocalDate.parse(dateInput, formatter); // Biến `ngaybatdau` phải là kiểu `LocalDate`
                break; // Thoát vòng lặp nếu nhập hợp lệ
            } catch (DateTimeParseException e) {
                System.out.println("Ngày bắt đầu không hợp lệ, vui lòng nhập lại.");
            }
        }
//        ghiFile();
    }
    public void suaNhanVien() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sửa thông tin nhân viên");

        // Nhập họ mới
        System.out.print("Nhập họ mới (nhấn Enter để giữ nguyên): ");
        String newHo = sc.nextLine();
        if (!newHo.trim().isEmpty()) {
            this.honhanvien = newHo;
        }

        // Nhập tên mới
        System.out.print("Nhập tên mới (nhấn Enter để giữ nguyên): ");
        String newTen = sc.nextLine();
        if (!newTen.trim().isEmpty()) {
            this.tennhanvien = newTen;
        }

        // Nhập chức vụ mới
        System.out.print("Nhập chức vụ mới (nhấn Enter để giữ nguyên): ");
        String newChucVu = sc.nextLine();
        if (!newChucVu.trim().isEmpty()) {
            this.chucvu = newChucVu;
        }

        // Nhập địa chỉ mới
        System.out.print("Nhập địa chỉ mới (nhấn Enter để giữ nguyên): ");
        String newDiaChi = sc.nextLine();
        if (!newDiaChi.trim().isEmpty()) {
            this.diachi = newDiaChi;
        }

        // Nhập số điện thoại mới
        while (true) {
            System.out.print("Nhập số điện thoại mới (nhấn Enter để giữ nguyên): ");
            String newSoDienThoai = sc.nextLine();
            if (newSoDienThoai.trim().isEmpty()) {  // Giữ nguyên nếu không nhập
                break;
            } else if (newSoDienThoai.matches("\\d+")) {  // Chỉ chấp nhận số
                this.sodienthoai = newSoDienThoai;
                break;
            } else {
                System.out.println("Số điện thoại chỉ nên chứa chữ số. Vui lòng nhập lại.");
            }
        }

        // Nhập lương mới
        while (true) {
            System.out.print("Nhập lương mới (nhập -1 để giữ nguyên): ");
            double newLuong = sc.nextDouble();
            sc.nextLine();  // Đọc dòng trống sau khi nhập số
            if (newLuong == -1) {  // Giữ nguyên nếu nhập -1
                break;
            } else if (newLuong >= 0) {  // Chỉ chấp nhận lương không âm
                this.luong = newLuong;
                break;
            } else {
                System.out.println("Lương không thể là giá trị âm. Vui lòng nhập lại.");
            }
        }

        System.out.println("Cập nhật thông tin nhân viên hoàn tất!");
    }

    public void xuatNhanVien() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("|%-10d|%-15s|%-15s|%-10s|%-15s|%-10s|%-12.2f|%-8s|%-12s|\n",
                manv, chucvu, honhanvien, tennhanvien, ngaysinh.format(formatter), diachi, luong, gioiTinh, ngaybatdau.format(formatter));
    }
    public static int getTongSoNhanVien() {
        return tongSoNhanVien;
    }
    public void xoaNhanVien() {
        System.out.println("Đã xóa nhân viên mã " + this.manv);
        tongSoNhanVien--;
    }

    @Override
    public void docFile() {

    }



    @Override
    public void ghiFile() {
        String filename = "QuanLySieuThiMini/src/QuanLySieuThiMiNi/NhanVien.txt";
        File file = new File(filename); // Tạo đối tượng File

        // Kiểm tra file có tồn tại không
        if (!file.exists()) {
            System.out.println("Lỗi: File không tồn tại tại đường dẫn: " + filename);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // Ghi thêm vào file
            writer.write(this.toFileString()); // Ghi thông tin nhân viên
            writer.newLine(); // Xuống dòng
            System.out.println("Nhân viên đã được lưu vào file: " + filename);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
    @Override
    public void capNhatFile() {
        return;
    }
}
