package QuanLySieuThiMiNi;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DanhSachNhanVien {

    private NhanVien[] dsnv;
    private int size;

    public DanhSachNhanVien() {
    }

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

    public void themDanhSachNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong nhan vien can them");
        int n=sc.nextInt();
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

    //NhanVien timKiemNhanVien theo ma tra ve nhan vien
    public NhanVien timKiemNhanVienTheoMa(int maNV) {
        for (NhanVien nv : dsnv) {
            if (nv.getManv() == maNV) {
                return nv;
            }
        }
        System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
        return null;
    }

    public void suaNhanVienTheoMa() {
       Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma nhan vien can chinh sua: ");
        int ma=sc.nextInt();
        NhanVien nv = timKiemNhanVienTheoMa(ma);
        if(nv != null) {
            nv.suaNhanVien();
        }
        else {
            System.out.println("Khong thay nhan vien nao: ");
        }
    }

    public void xoaNhanVienTheoMaCach1(int maNV) {
        NhanVien nv = timKiemNhanVienTheoMa(maNV);
        if (nv != null) {
            int indexToRemove = -1;
            for (int i = 0; i < size; i++) {
                if (dsnv[i] == nv) {
                    indexToRemove = i;
                    break;
                }
            }
            if (indexToRemove != -1) {
                for (int i = indexToRemove; i < size - 1; i++) {
                    dsnv[i] = dsnv[i + 1];
                }
                dsnv[size - 1] = null;
                size--;
                System.out.println("Đã xóa nhân viên với mã: " + maNV);
            }
        } else {
            System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
        }
    }

    public void xoaNhanVienTheoMaCach2(int maNV) {
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (dsnv[i].getManv() == maNV) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < size - 1; i++) {
                dsnv[i] = dsnv[i + 1];
            }
            dsnv[size - 1] = null; // Đặt phần tử cuối cùng là null
            size--; // Giảm kích thước mảng
            System.out.println("Đã xóa nhân viên với mã: " + maNV);
        } else {
            System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
        }
    }

    public NhanVien timKiemNhanVienTheoHo(String hoNV) {
        for (NhanVien nhanVien : dsnv)
        {
            if(nhanVien.getHonhanvien().equalsIgnoreCase(hoNV))
            {
                return nhanVien;
            }
        }
        System.out.println("Khong thay nhan vien voi ho: "+ hoNV);
        return null;
    }

    public void timKiemNhanVienTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ nhân viên cần tìm kiếm: ");
        String ho = sc.nextLine().trim();
        int dem = 0;
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
    public NhanVien timKiemNhanVienTheoTen(String tenNV) {
        for (NhanVien nhanVien : dsnv)
        {
            if(nhanVien.getHonhanvien().equalsIgnoreCase(tenNV))
            {
                return nhanVien;
            }
        }
        System.out.println("Khong thay nhan vien voi ho: "+ tenNV);
        return null;
    }

    public void timKiemNhanVienTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên nhân viên cần tìm kiếm: ");
        String ten = sc.nextLine().trim(); // Xóa khoảng trắng ở đầu và cuối
        int dem = 0;
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
        for (int i = 0; i < size; i++) {
            tongLuong += dsnv[i].getLuong();
        }
        double luongTB = (double) tongLuong / size;
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
        System.out.println("Lương nhân viên chức vụ 1 = " + tongluongchucvu1);
        System.out.println("Lương nhân viên chức vụ 2 = " + tongluongchucvu2);
        System.out.println("Lương nhân viên chức vụ 3 = " + tongluongchucvu3);
    }

    public NhanVien parseLineToNhanVien(String line) {
        String[] parts = line.split("[;, ]+"); // Phân tách theo dấu ';', ',' hoặc khoảng trắng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (parts.length == 9) {
            try {
                int manv = Integer.parseInt(parts[0]);
                String chucvu = parts[1];
                String honhanvien = parts[2];
                String tennhanvien = parts[3];
                LocalDate ngaysinh = LocalDate.parse(parts[4], formatter);
                String diachi = parts[5];
                String sodienthoai = parts[6];
                double luong = Double.parseDouble(parts[7]);
                String gioitinh = parts[8];

                return new NhanVien(manv, chucvu, honhanvien, tennhanvien, ngaysinh, diachi, sodienthoai, luong, gioitinh);
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Lỗi định dạng dữ liệu trong dòng: " + line);
            }
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

