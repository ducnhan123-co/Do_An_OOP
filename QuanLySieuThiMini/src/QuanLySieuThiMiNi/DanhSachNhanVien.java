package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachNhanVien {

    private NhanVien[] dsnv;
    private int size;

    public DanhSachNhanVien(int soluong) {
        dsnv = new NhanVien[soluong]; // Khởi tạo mảng với kích thước cho trước
        size = 0; // Bắt đầu với 0 nhân viên
    }

    public NhanVien[] getDsnv() {
        return dsnv;
    }

    public int getSize() {
        return size;
    }

    public void themNhanVien() {
        if (size >= dsnv.length) {
            System.out.println("Danh sách đã đầy, không thể thêm nhân viên mới.");
            return;
        }

        NhanVien nhanVien = new NhanVien();
        nhanVien.nhapNhanVien();
        dsnv[size] = nhanVien;
        size++;
        System.out.println("Đã thêm 1 nhân viên vào cuối danh sách thành công.");
    }

    public void themDanhSachNhanVien(int n) {
        if (size + n > dsnv.length) {
            System.out.println("Danh sách không đủ để thêm " + n + " nhân viên.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho nhân viên thứ " + (i + 1) + ":");
            NhanVien nhanVien = new NhanVien();
            nhanVien.nhapNhanVien();
            dsnv[size] = nhanVien;
            size++;
        }
        System.out.println("Đã thêm " + n + " nhân viên vào danh sách.");
    }

    public void xuatDanhSachNhanVien() {
        if (size == 0) {
            System.out.println("Danh sách nhân viên rỗng.");
            return;
        }
        for (int i = 0; i < size; i++) {
            dsnv[i].xuatNhanVien();
        }
    }

    public void suaNhanVienTheoMa(int ma) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getManv() == ma) {
                found = true;
                dsnv[i].suaNhanVien();
                System.out.println("Đã sửa thông tin nhân viên với mã: " + ma);
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + ma);
        }
    }

    public void xoaNhanVienTheoMa(int ma) {
        if (size == 0) {
            System.out.println("Danh sách nhân viên trống.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getManv() == ma) {
                found = true;
                for (int j = i; j < size - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv[size - 1] = null;
                size--;
                System.out.println("Đã xóa nhân viên với mã: " + ma);
                return;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + ma);
        }
    }

    public void timKiemNhanVienTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ nhân viên cần tìm kiếm: ");
        String ho = sc.nextLine().trim();

        int dem = 0;

        // Duyệt qua danh sách nhân viên dsnv
        for (int i = 0; i < size; i++) { // Chỉ duyệt đến size
            if (dsnv[i].getHonhanvien().equalsIgnoreCase(ho)) {
                dem++;
                dsnv[i].xuatNhanVien(); // Xuất thông tin nhân viên ngay khi tìm thấy
            }
        }

        System.out.println("Danh sách có: " + dem + " nhân viên có họ: " + ho);

        if (dem == 0) {
            System.out.println("Không tìm thấy nhân viên nào!");
        }
    }

    public void timKiemNhanVienTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên nhân viên cần tìm kiếm: ");
        String ten = sc.nextLine().trim(); // Xóa khoảng trắng ở đầu và cuối

        int dem = 0;

        // Duyệt qua danh sách nhân viên dsnv
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getTennhanvien().equalsIgnoreCase(ten)) {
                dem++;
                dsnv[i].xuatNhanVien();
            }
        }

        System.out.println("Danh sách có: " + dem + " nhân viên có tên: " + ten);

        if (dem == 0) {
            System.out.println("Không tìm thấy nhân viên nào!");
        }
    }

    public void thongKeTheoMucLuong() {
        if (size == 0) {
            System.out.println("Danh sách nhân viên trống.");
            return;
        }

        int tongLuong = 0;

        // Tính tổng lương
        for (int i = 0; i < size; i++) {
            tongLuong += dsnv[i].getLuong();
        }

        // Tính lương trung bình
        double luongTB = (double) tongLuong / size;

        // In thông tin
        System.out.println("Lương trung bình: " + luongTB);
        System.out.println("Các nhân viên có lương lớn hơn mức trung bình là:");

        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getLuong() > luongTB) {
                System.out.print(dsnv[i].getTennhanvien() + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có nhân viên nào có lương lớn hơn mức trung bình.");
        } else {
            System.out.println();
        }
    }

    public void thongKeTheoChucVu() {
        System.out.println("Thống kê theo chức vụ:");


        int tongluongchucvu1 = 0;
        int tongluongchucvu2 = 0;
        int tongluongchucvu3 = 0;


        for (int i = 0; i < size; i++) {
            String chucVu = dsnv[i].getChucvu();

            switch (chucVu) {
                case "1":
                    tongluongchucvu1 += dsnv[i].getLuong();
                    break;
                case "2":
                    tongluongchucvu2 += dsnv[i].getLuong();
                    break;
                case "3":
                    tongluongchucvu3 += dsnv[i].getLuong();
                    break;
                default:
                    System.out.println("Chức vụ không hợp lệ cho nhân viên mã " + dsnv[i].getManv());
            }
        }

        // In kết quả thống kê
        System.out.println("Lương nhân viên chức vụ 1 = " + tongluongchucvu1);
        System.out.println("Lương nhân viên chức vụ 2 = " + tongluongchucvu2);
        System.out.println("Lương nhân viên chức vụ 3 = " + tongluongchucvu3);
    }

    public NhanVien parseLineToNhanVien(String line) {
        String[] parts = line.split("[;, ]+"); // Phân tách theo dấu ';', ',' hoặc khoảng trắng

        if (parts.length == 9) {
            // Thay đổi cách lấy giá trị và khởi tạo đối tượng NhanVien
            int manv = Integer.parseInt(parts[0]);
            String chucvu = parts[1];
            String honhanvien = parts[2];
            String tennhanvien = parts[3];
            String ngaysinh = parts[4];
            String diachi = parts[5];
            String sodienthoai = parts[6];
            double luong = Double.parseDouble(parts[7]);
            String gioitinh = parts[8];

            return new NhanVien(manv, chucvu, honhanvien, tennhanvien, ngaysinh, diachi, sodienthoai, luong, gioitinh);
        }

        return null;
    }

    public void taiThongTinTuFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0; // Đếm số nhân viên được thêm
            while ((line = reader.readLine()) != null) {
                NhanVien nv = parseLineToNhanVien(line);
                if (nv != null) {
                    if (size < dsnv.length) { // Kiểm tra xem mảng có còn chỗ không
                        dsnv[size++] = nv; // Thêm nhân viên vào danh sách và tăng size
                        count++; // Tăng số lượng nhân viên đã thêm
                    } else {
                        System.out.println("Không thể thêm nhân viên từ file, danh sách đã đầy.");
                        break; // Dừng lại nếu danh sách đã đầy
                    }
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
            System.out.println("Đã thêm " + count + " nhân viên từ tệp tin: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin: " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public NhanVien timKiemNhanVienTheoMa(int manv) {
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getManv() == manv) {
                return dsnv[i];
            }
        }
        System.out.println("Không tìm thấy nhân viên với mã: " + manv);
        return null;
    }

    public void capNhatSoLuongNhanVien() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (dsnv[i] != null) {
                count++;
            }
        }
        size = count; // Cập nhật lại giá trị của size
        System.out.println("Số lượng nhân viên hiện có trong danh sách: " + size);
    }
}

