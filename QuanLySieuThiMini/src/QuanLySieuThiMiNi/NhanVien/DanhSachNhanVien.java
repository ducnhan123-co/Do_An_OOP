    package QuanLySieuThiMiNi.NhanVien;
    import QuanLySieuThiMiNi.ThaoTacFile;

    import java.io.*;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Arrays;
    import java.util.Scanner;
    import java.text.DecimalFormat;
    public class DanhSachNhanVien implements ThaoTacFile {
        public static NhanVien[] dsnv = new NhanVien[100]; // Tối đa 100 nhân viên
        public static int size = 0; // Đếm số nhân viên hiện có

        public DanhSachNhanVien() {};
        public DanhSachNhanVien(int maxNhanVien) {
            // docFile();
        }
        public static NhanVien[] getDsnv() {
            return dsnv;
        }
        public static int getSize() {
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
                sc.nextLine(); // Đọc bỏ dòng trống
                if (!kiemTraTrungMaNhanVien(manv)) {
                    break;
                }
                System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại.");
            }
            NhanVien nhanVien = new NhanVien();
            nhanVien.setManv(manv);
            nhanVien.nhapNhanVien();
            dsnv[size++] = nhanVien;

            // Ghi ngay vào file
            nhanVien.ghiFile();
            System.out.println("Nhân viên đã được thêm và lưu vào file.");
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
                    sc.nextLine();
                    if (!kiemTraTrungMaNhanVien(manv)) {
                        break;
                    }
                    System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại.");
                }
                NhanVien nhanVien = new NhanVien();
                nhanVien.setManv(manv);
                nhanVien.nhapNhanVien();
               dsnv[size++] = nhanVien;
                    nhanVien.ghiFile();
            }
            System.out.println("Đã thêm " + n + " nhân viên vào danh sách.");
        }
        public void xuatDanhSachNhanVien() {
            if (size == 0) {
                System.out.println("Danh sách nhân viên rỗng.");
                return;
            }
            System.out.println(new String(new char[117]).replace("\0", "-")); // Đường kẻ ngang
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
            "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
            System.out.println(new String(new char[117]).replace("\0", "-")); // Đường kẻ ngang
            
            for (int i = 0; i < size; i++) {
                dsnv[i].xuatNhanVien();
            }
            System.out.println(new String(new char[117]).replace("\0", "-")); // Đường kẻ ngang
            System.out.println("Danh sách trên có : " + getSize() + " nhân viên ");
        }
        public NhanVien timKiemNhanVienTheoMa(int maNV) {
            for (NhanVien nv : dsnv) {
                if (nv != null && nv.getManv() == maNV) { // Kiểm tra null trước khi truy cập getManv()
                    return nv;
                }
            }
            return null;
        }
        public void suaNhanVienTheoMa() {
            Scanner sc = new Scanner(System.in);
            boolean tiepTucSua = true;

            while (tiepTucSua) {
                System.out.println("Nhập mã nhân viên cần chỉnh sửa: ");
                int ma = -1; // Khởi tạo biến ma với giá trị không hợp lệ ban đầu
                boolean maHople = false;

                // Lặp lại cho đến khi người dùng nhập đúng số nguyên
                while (!maHople) {
                    try {
                        ma = Integer.parseInt(sc.nextLine()); // Cố gắng chuyển đổi đầu vào thành số nguyên
                        maHople = true; // Nếu không có lỗi thì thoát khỏi vòng lặp
                    } catch (NumberFormatException e) {
                        System.out.println("Mã nhân viên không hợp lệ. Vui lòng nhập lại một số nguyên.");
                    }
                }

                NhanVien nv = timKiemNhanVienTheoMa(ma);

                if (nv != null) {
                    // In thông tin nhân viên trước khi sửa
                    System.out.println("Thông tin nhân viên lúc cũ:");
                    System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                            "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                    nv.xuatNhanVien();  // Hàm này sẽ xuất thông tin nhân viên

                    // Sửa thông tin nhân viên
                    nv.suaNhanVien();

                    // In thông tin nhân viên sau khi sửa
                    System.out.println("Thông tin nhân viên sau khi cập nhật:");
                    System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                            "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                    nv.xuatNhanVien();

                    System.out.println("Đã sửa thông tin nhân viên với mã: " + ma);
                } else {
                    System.out.println("Không tìm thấy nhân viên nào với mã: " + ma);
                }

                // Hỏi người dùng có muốn sửa tiếp không
                System.out.print("Bạn muốn sửa tiếp không? (y/n): ");
                String luaChon = sc.nextLine().trim().toLowerCase();

                if (luaChon.equals("n")) {
                    tiepTucSua = false; // Nếu chọn 'n' thì dừng lại
                }
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
                    ghiFile();
                    System.out.println("Đã xóa nhân viên với mã: " + maNV);
                }
            } else {
                System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
            }
        }

        public NhanVien[] timKiemNhanVienTheoHo(String hoNV) {
            NhanVien[] ketQua = new NhanVien[dsnv.length]; // Mảng lưu kết quả
            int index = 0; // Biến đếm số lượng nhân viên tìm được
            for (NhanVien nhanVien : dsnv) {
                if (nhanVien != null && nhanVien.getHonhanvien().toLowerCase().contains(hoNV.toLowerCase())) {
                    ketQua[index++] = nhanVien; // Lưu nhân viên phù hợp vào mảng
                }
            }
            return Arrays.copyOf(ketQua, index);
        }

        public NhanVien[] timKiemNhanVienTheoTen(String tenNV) {
            NhanVien[] ketQua = new NhanVien[dsnv.length]; // Mảng để lưu kết quả tìm kiếm
            int index = 0;
            for (NhanVien nhanVien : dsnv) {
                if (nhanVien != null && nhanVien.getTennhanvien().equalsIgnoreCase(tenNV)) {
                    ketQua[index++] = nhanVien; // Lưu nhân viên phù hợp vào mảng
                }
            }
            if (index == 0) {
                System.out.println("Không tìm thấy nhân viên nào có tên: " + tenNV);
            }
            return Arrays.copyOf(ketQua, index);
        }

        public void thongKeTheoMucLuong() {
            if (size == 0) {
                System.out.println("Danh sách nhân viên trống.");
                return;
            }
            int tongLuong = 0;
            int soNhanVienThucTe = 0;
            for (int i = 0; i < size; i++) {
                if (dsnv[i] != null) {
                    tongLuong += dsnv[i].getLuong();
                    soNhanVienThucTe++;
                }
            }
            if (soNhanVienThucTe == 0) {
                System.out.println("Danh sách nhân viên không hợp lệ (toàn bộ null).");
                return;
            }
            double luongTB = (double) tongLuong / soNhanVienThucTe;
            DecimalFormat df = new DecimalFormat("#,###.00");
            System.out.println("Lương trung bình: " + df.format(luongTB));
            System.out.println("Các nhân viên có lương lớn hơn mức trung bình là:");
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
            boolean found = false;
            for (int i = 0; i < size; i++) {
                if (dsnv[i] != null && dsnv[i].getLuong() > luongTB) {
                    dsnv[i].xuatNhanVien();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Không có nhân viên nào có lương lớn hơn mức trung bình.");
            }
        }


        private LocalDate parseDate(String dateStr) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng 1
            DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE; // Định dạng 2: yyyy-MM-dd

            try {
                return LocalDate.parse(dateStr, formatter1);
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(dateStr, formatter2);
                } catch (DateTimeParseException ex) {
                    throw new DateTimeParseException("Không thể parse ngày: " + dateStr, dateStr, 0);
                }
            }
        }

        public NhanVien parseLineToNhanVien(String line) {
            String[] parts = line.split(";");

            if (parts.length != 10) {
                System.out.println("Số lượng cột không khớp trong dòng: " + line);
                return null;
            }

            try {
                int manv = Integer.parseInt(parts[0].trim());
                String chucvu = parts[1].trim();
                String honhanvien = parts[2].trim();
                String tennhanvien = parts[3].trim();
                LocalDate ngaysinh = parseDate(parts[4].trim());
                String diachi = parts[5].trim();
                String sodienthoai = parts[6].trim();
                double luong = Double.parseDouble(parts[7].trim());
                String gioiTinh = parts[8].trim();
                LocalDate ngaybatdau = parseDate(parts[9].trim());

                return new NhanVien(manv, chucvu, honhanvien, tennhanvien, ngaysinh, diachi, sodienthoai, luong, gioiTinh, ngaybatdau);
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Lỗi định dạng dữ liệu trong dòng: " + line);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void docFile() {
            String filename = "QuanLySieuThiMini/src/QuanLySieuThiMiNi/NhanVien/NhanVien.txt";
            File file = new File(filename); // Tạo đối tượng File

            // Kiểm tra file có tồn tại không
            if (!file.exists()) {
                System.out.println("Lỗi: File không tồn tại tại đường dẫn: " + filename);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                size = 0; // Reset danh sách nhân viên
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống
                    NhanVien nv = parseLineToNhanVien(line.trim()); // Gọi hàm parse
                    if (nv != null) {
                        dsnv[size++] = nv; // Thêm nhân viên vào danh sách
                    }
                }
//                System.out.println("Đã đọc " + size + " nhân viên từ file.");
            } catch (IOException e) {
                System.out.println("Lỗi khi đọc file: " + e.getMessage());
            }
        }


        @Override
        public void ghiFile() {
            String filename = "QuanLySieuThiMini/src/QuanLySieuThiMiNi/NhanVien/NhanVien.txt";
            File file = new File(filename); // Tạo đối tượng File

            // Kiểm tra file có tồn tại không
            if (!file.exists()) {
                System.out.println("Lỗi: File không tồn tại tại đường dẫn: " + filename);
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (NhanVien nv : dsnv) {
                    if (nv != null) {
                        writer.write(nv.toFileString()); // Ghi dữ liệu nhân viên vào file
                        writer.newLine(); // Xuống dòng
                    }
                }
                System.out.println("Đã ghi dữ liệu nhân viên vào tệp tin: " + filename);
            } catch (IOException e) {
                System.out.println("Lỗi khi ghi vào tệp tin: " + e.getMessage());
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

        public NhanVien[] timKiemNhanVienNoiSinh(String[] danhSachTinh) {
            NhanVien[] ketQua = new NhanVien[dsnv.length]; // Mảng kết quả
            int soKetQua = 0; // Đếm số nhân viên tìm được
            for (NhanVien nhanVien : dsnv) {
                if (nhanVien != null) {
                    for (String tinh : danhSachTinh) {
                        if (nhanVien.getDiachi().equalsIgnoreCase(tinh)) {
                            ketQua[soKetQua++] = nhanVien;
                            break; // Tìm thấy, không cần so sánh thêm
                        }
                    }
                }
            }
            NhanVien[] ketQuaChinhXac = new NhanVien[soKetQua];
            for (int i = 0; i < soKetQua; i++) {
                ketQuaChinhXac[i] = ketQua[i];
            }
            return ketQuaChinhXac;
        }
        public void timKiemDiaChiNhanVien() {
            try {
                String[] danhSachTinh = new String[64];
                int soTinh = 0;
                try (BufferedReader br = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/KhachHang/DanhSachTinh.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        danhSachTinh[soTinh++] = line.trim();
                    }
                }

                System.out.println("Danh sách các tỉnh thành:");
                for (int i = 0; i < soTinh; i++) {
                    System.out.printf("%d. %s\n", i + 1, danhSachTinh[i]);
                }
                System.out.print("Nhập các số tương ứng với tỉnh (cách nhau bởi khoảng trắng): ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();
                String[] inputArray = input.split("\\s+");

                // Chuyển đổi số lựa chọn thành danh sách tỉnh được chọn
                String[] tinhDuocChon = new String[inputArray.length];
                for (int i = 0; i < inputArray.length; i++) {
                    int chon = Integer.parseInt(inputArray[i]);
                    tinhDuocChon[i] = danhSachTinh[chon - 1]; // Lưu tên tỉnh (0-based index)
                }
                // Hiển thị các tỉnh được chọn
                System.out.print("Bạn đã chọn các tỉnh: ");
                System.out.println(String.join(", ", tinhDuocChon));
                // Tìm kiếm nhân viên
                NhanVien[] danhSachTimDuoc = timKiemNhanVienNoiSinh(tinhDuocChon);
                if (danhSachTimDuoc.length == 0) {
                    System.out.println("Không có nhân viên nào có nơi sinh thuộc danh sách bạn chọn.");
                } else {
                    System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                            "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");
                    for (NhanVien nhanVien : danhSachTimDuoc) {
                        if (nhanVien != null) {
                            nhanVien.xuatNhanVien();
                        }
                    }
                    System.out.println("Tổng số nhân viên tìm được: " + danhSachTimDuoc.length);
                }
            } catch (IOException e) {
                System.out.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
        public void thongKeTheoTuoi(int namHienTai) {
            if (size == 0) {
                System.out.println("Danh sách nhân viên trống.");
                return;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập tuổi tối thiểu để lọc nhân viên (ví dụ: 30): ");
            int tuoiCanTim = sc.nextInt();
            int dem=0;
            System.out.println("Danh sách nhân viên có độ tuổi lớn hơn hoặc bằng " + tuoiCanTim + ":");
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");

            for (NhanVien nhanVien : dsnv) {
                if (nhanVien != null) {
                    int tuoi = namHienTai - nhanVien.getNgaysinh().getYear();
                    if (tuoi >= tuoiCanTim) {
                        nhanVien.xuatNhanVien();
                        dem++;
                    }
                }
            }
            System.out.println("Danh sách trên có : "+ dem + " nhân viên.");
        }
    }

