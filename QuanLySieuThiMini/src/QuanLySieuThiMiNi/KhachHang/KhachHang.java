package QuanLySieuThiMiNi.KhachHang;

import QuanLySieuThiMiNi.ThaoTacFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class KhachHang implements ThaoTacFile {
    private int maKH;
    private String hoKH;
    private String tenKH;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private float diem;

    public KhachHang() {}

    public KhachHang(int maKH, String hoKH, String tenKH, LocalDate ngaySinh, String gioiTinh, String sdt, String diaChi, float diem) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.diem = diem;
    }
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return maKH + ";" + hoKH + ";" + tenKH + ";" +
                ngaySinh.format(formatter) + ";" +
                gioiTinh + ";" + sdt + ";" + diaChi + ";" + diem;
    }


    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);

//        System.out.print("Nhập mã khách hàng: ");
//        this.maKH = sc.nextInt();
//        sc.nextLine(); // Clear buffer

        System.out.print("Nhập họ khách hàng: ");
        this.hoKH = sc.nextLine();

        System.out.print("Nhập tên khách hàng: ");
        this.tenKH = sc.nextLine();

        while (true) {
            try {
                System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                String ngaySinhStr = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập giới tính (Nam/Nữ): ");
        this.gioiTinh = sc.nextLine();

        while (true) {
            System.out.print("Nhập số điện thoại (10 số): ");
            this.sdt = sc.nextLine();
            if (this.sdt.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập địa chỉ: ");
        this.diaChi = sc.nextLine();

        System.out.print("Nhập điểm tích lũy: ");
        this.diem = sc.nextFloat();
        ghiFile();
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Sửa họ (nhấn Enter để giữ nguyên): ");
        String newHo = sc.nextLine();
        if (!newHo.isEmpty()) {
            this.hoKH = newHo;
        }

        System.out.print("Sửa tên (nhấn Enter để giữ nguyên): ");
        String newTen = sc.nextLine();
        if (!newTen.isEmpty()) {
            this.tenKH = newTen;
        }

        System.out.print("Sửa ngày sinh (dd/MM/yyyy, nhấn Enter để giữ nguyên): ");
        String newNgaySinh = sc.nextLine();
        if (!newNgaySinh.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.ngaySinh = LocalDate.parse(newNgaySinh, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        System.out.print("Sửa giới tính (nhấn Enter để giữ nguyên): ");
        String newGioiTinh = sc.nextLine();
        if (!newGioiTinh.isEmpty()) {
            this.gioiTinh = newGioiTinh;
        }

        System.out.print("Sửa số điện thoại (nhấn Enter để giữ nguyên): ");
        String newSdt = sc.nextLine();
        if (!newSdt.isEmpty() && newSdt.matches("\\d{10}")) {
            this.sdt = newSdt;
        }

        System.out.print("Sửa địa chỉ (nhấn Enter để giữ nguyên): ");
        String newDiaChi = sc.nextLine();
        if (!newDiaChi.isEmpty()) {
            this.diaChi = newDiaChi;
        }

        System.out.print("Sửa điểm tích lũy (nhấn Enter để giữ nguyên): ");
        String newDiemStr = sc.nextLine();
        if (!newDiemStr.isEmpty()) {
            try {
                this.diem = Float.parseFloat(newDiemStr);
            } catch (NumberFormatException e) {
                System.out.println("Điểm tích lũy không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }
    }

    public void xuat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("| %-8d | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8.2f |\n",
                this.maKH, this.hoKH, this.tenKH, this.ngaySinh.format(formatter),
                this.gioiTinh, this.sdt, this.diaChi, this.diem);
    }

    @Override
    public void docFile() {

    }

    @Override
    public void ghiFile() {
        String filename = "D:\\ALL\\Do_An_OOP\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\KhachHang.txt";
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

    }

}
