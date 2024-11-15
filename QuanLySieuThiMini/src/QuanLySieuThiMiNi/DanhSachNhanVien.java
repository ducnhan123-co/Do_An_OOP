    package QuanLySieuThiMiNi;

    import java.io.*;

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Scanner;

    public class DanhSachNhanVien implements ThaoTacFile{

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

        public boolean kiemTraTrungMaNhanVien(int manv) {
            for (int i = 0; i < size; i++) {
                if (dsnv[i] != null && dsnv[i].getManv() == manv) {
                    return true; // Trùng mã nhân viên
                }
            }
            return false; // Không trùng
        }

        public void themNhanVien() {
            if (size >= dsnv.length) {
                System.out.println("Danh sách đã đầy, không thể thêm nhân viên mới.");
                return;
            }
            Scanner sc = new Scanner(System.in);
            int manv;
            while (true) {
                System.out.print("Nhập mã nhân viên: ");
                manv = sc.nextInt();
                sc.nextLine(); // Đọc bỏ dòng trống sau `nextInt`

                if (!kiemTraTrungMaNhanVien(manv)) {
                    break; // Nếu không trùng mã, thoát khỏi vòng lặp
                }
                System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại.");
            }

            NhanVien nhanVien = new NhanVien();
            nhanVien.setManv(manv); // Gán mã nhân viên sau khi đã kiểm tra
            nhanVien.nhapNhanVien(); // Nhập các thông tin còn lại
            dsnv[size++] = nhanVien;
            System.out.println("Đã thêm nhân viên vào danh sách.");
        }

        public void themDanhSachNhanVien() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập số lượng nhân viên cần thêm: ");
            int n = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng trống sau khi nhập số

            for (int i = 0; i < n; i++) {
                if (size >= dsnv.length) {
                    System.out.println("Danh sách đã đầy, không thể thêm thêm nhân viên.");
                    break;
                }

                System.out.println("Nhập thông tin cho nhân viên thứ " + (i + 1) + ":");

                int manv;
                while (true) {
                    System.out.print("Nhập mã nhân viên: ");
                    manv = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ dòng trống sau `nextInt`

                    if (!kiemTraTrungMaNhanVien(manv)) {
                        break; // Nếu mã không trùng, thoát khỏi vòng lặp
                    }
                    System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại.");
                }

                NhanVien nhanVien = new NhanVien();
                nhanVien.setManv(manv); // Gán mã nhân viên hợp lệ vào đối tượng
                nhanVien.nhapNhanVien(); // Nhập các thông tin khác
                dsnv[size++] = nhanVien; // Thêm vào danh sách
            }

            System.out.println("Đã thêm " + n + " nhân viên vào danh sách.");
        }


        public void xuatDanhSachNhanVien() {
            if (size == 0) {
                System.out.println("Danh sách nhân viên rỗng.");
                return;
            }

            // In tiêu đề bảng
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            // In từng nhân viên
            for (int i = 0; i < size; i++) {
                dsnv[i].xuatNhanVien();
            }
            System.out.println("Danh sách trên có : " + getSize() + " nhân viên ");

    //        System.out.println("--------------------------------------------------------------------------------------------------------");
        }


        //NhanVien timKiemNhanVien theo ma tra ve nhan vien
        public NhanVien timKiemNhanVienTheoMa(int maNV) {
            for (NhanVien nv : dsnv) {
                if (nv != null && nv.getManv() == maNV) { // Kiểm tra null trước khi truy cập getManv()
                    return nv;
                }
            }
            return null; // Trả về null nếu không tìm thấy
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
            String[] parts = line.split(";");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if (parts.length == 9) { // Phải đúng 9 phần tử trong mỗi dòng
                try {
                    int manv = Integer.parseInt(parts[0].trim());
                    String chucvu = parts[1].trim();
                    String honhanvien = parts[2].trim();
                    String tennhanvien = parts[3].trim();
                    LocalDate ngaysinh = LocalDate.parse(parts[4].trim(), formatter);
                    String diachi = parts[5].trim();
                    String sodienthoai = parts[6].trim();
                    double luong = Double.parseDouble(parts[7].trim());
                    LocalDate ngaybatdau = LocalDate.parse(parts[8].trim(), formatter);

                    return new NhanVien(manv, chucvu, honhanvien, tennhanvien, ngaysinh, diachi, sodienthoai, luong, "Nam",ngaybatdau);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Lỗi định dạng dữ liệu trong dòng: " + line);
                }
            } else {
                System.out.println("Số lượng cột không khớp trong dòng: " + line);
            }
            return null;
        }

        @Override
        public void docFile() {
            String filename = "D:\\Desktop\\ALL\\DO_AN_OOP_JAVA\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\NhanVien.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    NhanVien nv = parseLineToNhanVien(line);
                    if (nv != null) {
                        if (size < dsnv.length) {
                            dsnv[size++] = nv;
                            count++;
                        } else {
                            System.out.println("Không thể thêm nhân viên từ file, danh sách đã đầy.");
                            break;
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

        // Hàm in ra danh sách nhân viên
        public void inDanhSachNhanVien() {
            System.out.println("Danh sách nhân viên:");
            for (int i = 0; i < size; i++) {
                System.out.println(dsnv[i]);
            }
        }



        @Override
        public void ghiFile() {
            String filename = "D:\\Desktop\\ALL\\DO_AN_OOP_JAVA\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\NhanVien.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (int i = 0; i < size; i++) {
                    NhanVien nv = dsnv[i];
                    String line = nv.toFileString();
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Đã ghi dữ liệu nhân viên vào tệp tin: " + filename);
            } catch (IOException e) {
                System.out.println("Lỗi khi ghi vào tệp tin: " + filename);
                e.printStackTrace();
            }
        }

        @Override
        public void capNhatFile() {
            ghiFile(); // Có thể tái sử dụng phương thức ghi tệp
            System.out.println("Đã cập nhật lại dữ liệu trong tệp tin.");
        }

        public void thongKeTheoNamLamViec() {
            if (size == 0) {
                System.out.println("Danh sách nhân viên trống.");
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập năm làm việc để thống kê: ");
            int nam = sc.nextInt();

            int truocNam = 0;
            int sauNam = 0;

            System.out.println("Nhân viên làm việc trước năm " + nam + " là:");
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < size; i++) {
                if (dsnv[i].getNgaybatdau().getYear() < nam) {
                    dsnv[i].xuatNhanVien();
                    truocNam++;
                }
            }

            System.out.println("Danh sách trên có " + truocNam + " nhân viên.");
            System.out.println("\nNhân viên làm việc sau năm " + nam + " là:");
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < size; i++) {
                if (dsnv[i].getNgaybatdau().getYear() > nam) {
                    dsnv[i].xuatNhanVien();
                    sauNam++;
                }
            }

            System.out.println("Danh sách trên có " + sauNam + " nhân viên.");
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

