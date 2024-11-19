    package QuanLySieuThiMiNi;
    import java.io.*;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Arrays;
    import java.util.Scanner;
    import java.text.DecimalFormat;
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
            }
            System.out.println("Đã thêm " + n + " nhân viên vào danh sách.");
        }
        public void xuatDanhSachNhanVien() {
            if (size == 0) {
                System.out.println("Danh sách nhân viên rỗng.");
                return;
            }
            System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-15s|%-10s|%-12s|%-8s|%-12s|\n",
                    "Mã NV", "Chức vụ", "Họ NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Lương", "GT", "Ngày bắt đầu");

            for (int i = 0; i < size; i++) {
                dsnv[i].xuatNhanVien();
            }
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
        public void timKiemNhanVienTheoHo() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập họ nhân viên cần tìm kiếm: ");
            String ho = sc.nextLine().trim();
            int dem = 0;
            for (int i = 0; i < size; i++) { // Chỉ duyệt đến size
                if (dsnv[i].getHonhanvien().equalsIgnoreCase(ho)) {
                    dem++;
                    dsnv[i].xuatNhanVien();
                }
            }
            System.out.println("Danh sách có: " + dem + " nhân viên có họ: " + ho);
            if (dem == 0) {
                System.out.println("Không tìm thấy nhân viên nào!");
            }
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
            String filename = "D:\\ALL\\Do_An_OOP\\QuanLySieuThiMini\\src\\QuanLySieuThiMiNi\\NhanVien.txt";
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
//                continue;
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

        public NhanVien[] timKiemNhanVienNoiSinh(String noi1, String noi2) {
            NhanVien[] ketqua = new NhanVien[dsnv.length]; // Mảng lưu kết quả
            int index = 0; // Biến đếm số lượng kết quả
            for (NhanVien nhanVien : dsnv) {
                if (nhanVien != null && (nhanVien.getDiachi().contains(noi1) || nhanVien.getDiachi().contains(noi2))) {
                    ketqua[index] = nhanVien; // Lưu nhân viên phù hợp vào mảng
                    index++;
                }
            }
            if (index == 0) {
                System.out.println("Không tìm thấy nhân viên nào có nơi sinh ở " + noi1 + " hoặc " + noi2 + ".");
            }
            return Arrays.copyOf(ketqua, index);
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
                // Đọc danh sách tỉnh từ file vào mảng
                String[] danhSachTinh = new String[64]; // Giả sử file có tối đa 64 tỉnh
                int soTinh = 0; // Đếm số tỉnh trong file
                try (BufferedReader br = new BufferedReader(new FileReader("D:/ALL/Do_An_OOP/QuanLySieuThiMini/src/QuanLySieuThiMiNi/DanhSachTinh.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        danhSachTinh[soTinh++] = line.trim();
                    }
                }
                System.out.println("Danh sách các tỉnh thành (chọn số tương ứng):");
                for (int i = 0; i < soTinh; i++) {
                    System.out.printf("%d. %s\n", i + 1, danhSachTinh[i]);
                }
                int[] luaChon = new int[soTinh]; // Lưu lựa chọn của người dùng
                int soLuaChon = 0; // Đếm số lượng tỉnh đã chọn
                Scanner scanner = new Scanner(System.in);
                boolean tiepTuc = true;
                while (tiepTuc) {
                    System.out.print("Nhập số tương ứng với tỉnh (VD: 1 2): ");
                    String input = scanner.nextLine().trim();  // Loại bỏ khoảng trắng đầu và cuối
                    String[] inputArray = input.split("\\s+");
                    boolean validInput = true;
                    for (String s : inputArray) {
                        try {
                            int chon = Integer.parseInt(s);
                            // Kiểm tra số nhập có hợp lệ và không vượt quá số lượng tỉnh
                            if (chon > 0 && chon <= soTinh) {
                                // Kiểm tra xem tỉnh đã được chọn chưa
                                boolean isDuplicate = false;
                                for (int i = 0; i < soLuaChon; i++) {
                                    if (luaChon[i] == chon - 1) {
                                        isDuplicate = true;  // Nếu trùng, bỏ qua
                                        break;
                                    }
                                }
                                if (!isDuplicate) {
                                    luaChon[soLuaChon++] = chon - 1;  // Lưu chỉ số tỉnh (0-based)
                                }
                            } else {
                                System.out.println("Số " + chon + " không hợp lệ, vui lòng thử lại.");
                                validInput = false;  // Đánh dấu là đầu vào không hợp lệ
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi nhập: '" + s + "' không phải là số hợp lệ. Mời nhập lại.");
                            validInput = false;  // Đánh dấu là đầu vào không hợp lệ
                        }
                    }
                    if (!validInput) {
                        continue;
                    }
                    String tiep;
                    do {
                        System.out.print("Bạn có muốn nhập thêm không? (y/n): ");
                        tiep = scanner.nextLine().trim().toLowerCase();
                        if (!tiep.equals("y") && !tiep.equals("n")) {
                            System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                        }
                    } while (!tiep.equals("y") && !tiep.equals("n"));

                    tiepTuc = tiep.equals("y");
                }
                String[] tinhDuocChon = new String[soLuaChon];
                int index = 0;
                for (int chon : luaChon) {
                    try {
                        tinhDuocChon[index++] = danhSachTinh[chon];
                    } catch (ArrayIndexOutOfBoundsException e) {
                       continue;
                    }
                }
                System.out.print("Bạn đã chọn các tỉnh: ");
                for (int i = 0; i < tinhDuocChon.length; i++) {
                    System.out.print(tinhDuocChon[i] + (i < tinhDuocChon.length - 1 ? ", " : "\n"));
                }
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

