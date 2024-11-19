package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class KhachHang {
    private int maKH;
    private String hoKH;
    private String tenKH;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private float diem;

    public KhachHang() {}

    public KhachHang(int maKH, String hoKH, String tenKH, LocalDate ngaySinh, String gioiTinh, String sdt, String diaChi) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHang(KhachHang other) {
        this.maKH = other.maKH;
        this.hoKH = other.hoKH;
        this.tenKH = other.tenKH;
        this.ngaySinh = other.ngaySinh;
        this.gioiTinh = other.gioiTinh;
        this.sdt = other.sdt;
        this.diaChi = other.diaChi;
        this.diem = other.diem;
    }

    // Getter và setter
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

    // Nhập thông tin khách hàng
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin khách hàng");

        System.out.print("Nhập mã khách hàng: ");
        this.maKH = sc.nextInt();
        sc.nextLine(); // Consume the newline

        System.out.print("Nhập họ khách hàng: ");
        this.hoKH = sc.nextLine().trim();

        System.out.print("Nhập tên khách hàng: ");
        this.tenKH = sc.nextLine().trim();

        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                this.ngaySinh = LocalDate.parse(dateInput, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = sc.nextLine().trim();

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            this.sdt = sc.nextLine().trim();
            if (this.sdt.matches("\\d{10}")) {  // Kiểm tra số điện thoại hợp lệ
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập địa chỉ: ");
        this.diaChi = sc.nextLine().trim();
    }

    // Sửa thông tin khách hàng
    public void sua() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sửa thông tin khách hàng");

        System.out.print("Nhập họ mới (nhấn Enter để giữ nguyên): ");
        String newHo = sc.nextLine();
        if (!newHo.trim().isEmpty()) {
            this.hoKH = newHo;
        }

        System.out.print("Nhập tên mới (nhấn Enter để giữ nguyên): ");
        String newTen = sc.nextLine();
        if (!newTen.trim().isEmpty()) {
            this.tenKH = newTen;
        }

        while (true) {
            System.out.print("Nhập ngày sinh mới (dd/MM/yyyy, nhấn Enter để giữ nguyên): ");
            String dateInput = sc.nextLine();
            if (dateInput.isEmpty()) {  // Giữ nguyên nếu không nhập
                break;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    this.ngaySinh = LocalDate.parse(dateInput, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Ngày sinh không hợp lệ, vui lòng nhập lại.");
                }
            }
        }

        System.out.print("Nhập giới tính mới (nhấn Enter để giữ nguyên): ");
        String newGioiTinh = sc.nextLine();
        if (!newGioiTinh.trim().isEmpty()) {
            this.gioiTinh = newGioiTinh;
        }

        while (true) {
            System.out.print("Nhập số điện thoại mới (nhấn Enter để giữ nguyên): ");
            String newSoDienThoai = sc.nextLine();
            if (newSoDienThoai.trim().isEmpty()) {  // Giữ nguyên nếu không nhập
                break;
            } else if (newSoDienThoai.matches("\\d{10}")) {
                this.sdt = newSoDienThoai;
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập địa chỉ mới (nhấn Enter để giữ nguyên): ");
        String newDiaChi = sc.nextLine();
        if (!newDiaChi.trim().isEmpty()) {
            this.diaChi = newDiaChi;
        }

        System.out.println("Cập nhật thông tin khách hàng hoàn tất!");
    }

    // Xuất thông tin khách hàng
    public void xuat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("|%-10d|%-15s|%-15s|%-12s|%-15s|%-10s|%-15s|\n",
                maKH, hoKH, tenKH, ngaySinh.format(formatter), gioiTinh, sdt, diaChi);
    }
}
