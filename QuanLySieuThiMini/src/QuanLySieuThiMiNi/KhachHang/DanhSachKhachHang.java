package QuanLySieuThiMiNi.KhachHang;

import QuanLySieuThiMiNi.HoaDon.*;
import QuanLySieuThiMiNi.ThaoTacFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachKhachHang implements ThaoTacFile {
    public static KhachHang[] dskh = new KhachHang[0];
    public static int size = 0;

    public DanhSachKhachHang() {
    }

    public void push(KhachHang khachHang) {
        if (size >= dskh.length) {
            dskh = copyOf(dskh, dskh.length + 10); // Tăng kích thước mảng
        }
        dskh[size++] = khachHang;
    }


    // Thêm một khách hàng mới vào danh sách
    public void them() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Nhập mã khách hàng: ");
                int maKH = sc.nextInt();
                sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại

                // Kiểm tra mã khách hàng đã tồn tại
                if (timKiemKhachHangTheoMa(maKH) != null) {
                    System.out.println("Khách hàng này đã có trong danh sách. Vui lòng nhập lại mã khác.");
                    continue;
                }

                // Tạo khách hàng mới và nhập thông tin
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(maKH); // Gán mã khách hàng vừa nhập
                khachHang.nhap(); // Nhập các thông tin còn lại
                push(khachHang); // Thêm khách hàng vào danh sách
                System.out.println("Thêm khách hàng thành công!");
                break; // Thoát vòng lặp sau khi thêm thành công
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu: " + e.getMessage());
                sc.nextLine(); // Đọc bỏ dữ liệu lỗi
            }
        }
    }


    // Tìm kiếm khách hàng theo mã khách hàng
    public KhachHang timKiemKhachHangTheoMa(int maKH) {
        for (KhachHang khachHang : dskh) {
            if (khachHang != null && khachHang.getMaKH() == maKH) {
                return khachHang;
            }
        }
        return null;
    }
    public void timKiemKhachHangTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ khách hàng cần tìm kiếm: ");
        String hoKH = sc.nextLine().trim();
        boolean found = false;
        int dem=0;
        KhachHang[] khachHangFound = new KhachHang[dskh.length];
        int index = 0;  // Biến đếm số lượng khách hàng tìm được
        for (KhachHang khachHang : dskh) {
            if (khachHang != null && khachHang.getHoKH().equalsIgnoreCase(hoKH)) {
                khachHangFound[index++] = khachHang; // Lưu khách hàng vào mảng
                found = true;
            }
        }
        if (found) {
            System.out.println("Danh sách khách hàng có họ '" + hoKH + "':");
            System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                    "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");

            // In các khách hàng trong mảng kết quả
            for (int i = 0; i < index; i++) {
                if (khachHangFound[i] != null) {
                    dem++;
                    khachHangFound[i].xuat(); // In thông tin khách hàng
                }
            }
            System.out.println("Danh sách trên có : "+ dem + " khách hàng với họ là : "+ hoKH);
        } else {
            System.out.println("Không tìm thấy khách hàng với họ: " + hoKH);
        }
    }
    // Sửa thông tin khách hàng theo mã
    public void suaKhachHangTheoMa() {
        Scanner sc = new Scanner(System.in);
        boolean tiepTucSua = true;

        while (tiepTucSua) {
            System.out.println("Nhập mã khách hàng cần chỉnh sửa: ");
            int ma = -1; // Khởi tạo biến mã với giá trị không hợp lệ ban đầu
            boolean maHopLe = false;

            // Lặp lại cho đến khi người dùng nhập đúng số nguyên
            while (!maHopLe) {
                try {
                    ma = Integer.parseInt(sc.nextLine()); // Cố gắng chuyển đổi đầu vào thành số nguyên
                    maHopLe = true; // Nếu không có lỗi thì thoát khỏi vòng lặp
                } catch (NumberFormatException e) {
                    System.out.println("Mã khách hàng không hợp lệ. Vui lòng nhập lại một số nguyên.");
                }
            }

            KhachHang kh = timKiemKhachHangTheoMa(ma);

            if (kh != null) {
                // In thông tin khách hàng trước khi sửa
                System.out.println("Thông tin khách hàng trước khi sửa:");
                System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                        "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");

                kh.xuat(); // Gọi phương thức xuất thông tin khách hàng

                // Sửa thông tin khách hàng
                kh.sua();

                // In thông tin khách hàng sau khi sửa
                System.out.println("Thông tin khách hàng sau khi cập nhật:");
                System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                        "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");
                kh.xuat();

                System.out.println("Đã sửa thông tin khách hàng với mã: " + ma);
            } else {
                System.out.println("Không tìm thấy khách hàng nào với mã: " + ma);
            }

            // Hỏi người dùng có muốn sửa tiếp không
            System.out.print("Bạn muốn sửa tiếp không? (y/n): ");
            String luaChon = sc.nextLine().trim().toLowerCase();

            if (luaChon.equals("n")) {
                tiepTucSua = false; // Nếu chọn 'n' thì dừng lại
            }
        }
    }


    // Xóa khách hàng theo mã
    public void xoa(int maKH) {
        for (int i = 0; i < dskh.length; i++) {
            if (dskh[i].getMaKH() == maKH) {
                for (int j = i; j < dskh.length - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                dskh = copyOf(dskh, dskh.length - 1);
                size--;
                System.out.println("Đã xóa khách hàng với mã: " + maKH);
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
    }


    public void timKiemKhachHangTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên khách hàng cần tìm kiếm (hoặc một phần tên): ");
        String tenKH = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        int dem = 0;
        KhachHang[] khachHangFound = new KhachHang[dskh.length];
        int index = 0; // Biến đếm số lượng khách hàng tìm được

        for (KhachHang khachHang : dskh) {
            if (khachHang != null && khachHang.getTenKH().toLowerCase().contains(tenKH)) {
                khachHangFound[index++] = khachHang; // Lưu khách hàng vào mảng
                found = true;
            }
        }

        if (found) {
            System.out.println("Danh sách khách hàng có tên chứa '" + tenKH + "':");
            System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                    "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");

            // In các khách hàng trong mảng kết quả
            for (int i = 0; i < index; i++) {
                if (khachHangFound[i] != null) {
                    dem++;
                    khachHangFound[i].xuat(); // In thông tin khách hàng
                }
            }
            System.out.println("Danh sách trên có: " + dem + " khách hàng chứa tên là: " + tenKH);
        } else {
            System.out.println("Không tìm thấy khách hàng với tên chứa: " + tenKH);
        }
    }



    // Thống kê số lượng khách hàng
    public void thongKeKhachHang() {
        System.out.println("Tổng số khách hàng hiện tại: " + size);
        if (size == 0) {
            System.out.println("Danh sách khách hàng trống.");
        }
    }

    public KhachHang parseLineToKhachHang(String line) {
        String[] parts = line.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Cập nhật định dạng ngày
        if (parts.length == 8) { // Đảm bảo đúng số lượng thuộc tính
            try {
                int maKH = Integer.parseInt(parts[0].trim());
                String hoKH = parts[1].trim();
                String tenKH = parts[2].trim();
                LocalDate ngaySinh = LocalDate.parse(parts[3].trim(), formatter); // Sử dụng đúng định dạng ngày
                String gioiTinh = parts[4].trim();
                String sdt = parts[5].trim();
                String diaChi = parts[6].trim();
                float diem = Float.parseFloat(parts[7].trim());
                return new KhachHang(maKH, hoKH, tenKH, ngaySinh, gioiTinh, sdt, diaChi, diem);
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/QuanLySieuThiMiNi/KhachHang/KhachHang.txt")))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                KhachHang kh = parseLineToKhachHang(line);
                if (kh != null) {
                    // Kiểm tra và mở rộng mảng nếu cần
                    if (size >= dskh.length) {
                        dskh = Arrays.copyOf(dskh, dskh.length + 10);
                    }
                    dskh[size++] = kh;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
//            System.out.println("Đã đọc " + count + " khách hàng từ file KhachHang.txt.");
        } catch (NullPointerException e) {
            System.out.println("Không tìm thấy file: KhachHang.txt. Đảm bảo file tồn tại trong thư mục /QuanLySieuThiMiNi.");
        } catch (IOException ex) {
            System.out.println("Đã xảy ra lỗi khi đọc file: " + ex.getMessage());
        }
    }

    @Override
        public  void ghiFile() {}

        @Override
        public void capNhatFile(){}
    // Xuất danh sách khách hàng
    public void xuatDanhSachKhachHang() {
        if (size == 0) {
            System.out.println("Danh sách khách hàng rỗng.");
            return;
        }
        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");


        for (KhachHang khachHang : dskh) {
            if (khachHang != null) {
                khachHang.xuat();
            }
        }
        System.out.println("Danh sách trên có " + size + " khách hàng.");
    }
    public void thongKeTheoGioiTinh() {
        int nam = 0;
        int nu = 0;

        for (KhachHang kh : dskh) {
            if (kh != null) {
                if (kh.getGioiTinh().equalsIgnoreCase("Nam")) {
                    nam++;
                } else if (kh.getGioiTinh().equalsIgnoreCase("Nu")) {
                    nu++;
                }
            }
        }

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║ Thống kê số lượng khách hàng     ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf(" ║ Số lượng khách hàng Nam: %-8d    ║\n",nam);
        System.out.printf(" ║ Số lượng khách hàng Nữ: %-8d     ║\n", nu);
        System.out.println("╚══════════════════════════════════╝");
    }

    public void thongKeTheoTuoi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tuổi để thống kê: ");
        int tuoiNhap = sc.nextInt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        KhachHang[] truocTuoi = new KhachHang[size];
        KhachHang[] sauTuoi = new KhachHang[size];
        int truocIndex = 0;
        int sauIndex = 0;
        int demNho=0,demHon=0;
        for (KhachHang kh : dskh) {
            if (kh != null) {
                int tuoiKh = now.getYear() - kh.getNgaySinh().getYear();
                if (tuoiKh < tuoiNhap) {
                    demNho++;
                    truocTuoi[truocIndex++] = kh;
                } else {
                    sauTuoi[sauIndex++] = kh;
                    demHon++;
                }
            }
        }

        System.out.println("Danh sách khách hàng có tuổi nhỏ hơn " + tuoiNhap + ":");
        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");
        for (int i = 0; i < truocIndex; i++) {
            if (truocTuoi[i] != null) {
                truocTuoi[i].xuat();
            }
        }
        System.out.println("Danh sách trên có : "+ demNho+ " khách hàng.");

        System.out.println("\nDanh sách khách hàng có tuổi lớn hơn hoặc bằng " + tuoiNhap + ":");
        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");
        for (int i = 0; i < sauIndex; i++) {
            if (sauTuoi[i] != null) {
                sauTuoi[i].xuat();
            }
        }
        System.out.println("Danh sách treen có : "+demHon+ " khách hàng.");
    }

    public void thongKeDonHangTheoQuy() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm cần thống kê: ");
        int namCanTim = sc.nextInt();
        sc.nextLine(); // Đọc ký tự xuống dòng

        int[] maxHoaDon = {0, 0, 0, 0}; // Số lượng hóa đơn nhiều nhất cho các quý
        float[] maxChiTieu = {0, 0, 0, 0}; // Chi tiêu lớn nhất cho các quý
        KhachHang[] topKhachHang = new KhachHang[4]; // Khách hàng tương ứng với các quý
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Định dạng ngày khớp với file

        for (KhachHang kh : dskh) {
            if (kh == null) continue;
            int[] soHoaDonTheoQuy = {0, 0, 0, 0};
            float[] chiTieuTheoQuy = {0, 0, 0, 0};

            for (HoaDon hd : DanhSachHoaDon.getDshd()) { // Sử dụng getter để lấy danh sách hóa đơn
                if (hd != null && hd.getMaKH() == kh.getMaKH()) {
                    try {
                        // Chuyển đổi ngày từ String sang LocalDate
                        LocalDate ngayHoaDon = LocalDate.parse(hd.getNgayTaoHoaDon(), formatter);
                        if (ngayHoaDon.getYear() == namCanTim) { // Kiểm tra nếu hóa đơn thuộc năm cần tìm
                            int month = ngayHoaDon.getMonthValue(); // Lấy tháng từ ngày
                            int quy = (month - 1) / 3; // Quy 1: 0, Quy 2: 1, Quy 3: 2, Quy 4: 3
                            soHoaDonTheoQuy[quy]++;
                            chiTieuTheoQuy[quy] += hd.getTongTien(); // Tính tổng chi tiêu
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Lỗi định dạng ngày trong hóa đơn: " + hd.getNgayTaoHoaDon());
                    }
                }
            }

            for (int quy = 0; quy < 4; quy++) {
                if (soHoaDonTheoQuy[quy] > maxHoaDon[quy] ||
                        (soHoaDonTheoQuy[quy] == maxHoaDon[quy] && chiTieuTheoQuy[quy] > maxChiTieu[quy])) {
                    maxHoaDon[quy] = soHoaDonTheoQuy[quy];
                    maxChiTieu[quy] = chiTieuTheoQuy[quy];
                    topKhachHang[quy] = kh;
                }
            }
        }

        // In bảng thống kê
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║          Thống kê khách hàng mua nhiều đơn hàng nhất theo từng quý    ║");
        System.out.println("║                                Năm: " + namCanTim + "                               ║");
        System.out.println("╠═══════╦════════════╦══════════════════════╦═════════════╦═══════════════════════╣");
        System.out.println("║ Quý   ║ Mã KH      ║ Tên khách hàng       ║ Số hóa đơn  ║ Chi tiêu              ║");
        System.out.println("╠═══════╬════════════╬══════════════════════╬═════════════╬═══════════════════════╣");
        for (int quy = 0; quy < 4; quy++) {
            if (topKhachHang[quy] != null) {
                System.out.printf("║ %-5d ║ %-10d ║ %-20s ║ %-11d ║ %-23.2f ║\n",
                        quy + 1,
                        topKhachHang[quy].getMaKH(),
                        topKhachHang[quy].getHoKH() + " " + topKhachHang[quy].getTenKH(),
                        maxHoaDon[quy],
                        maxChiTieu[quy]);
            } else {
                System.out.printf("║ %-5d ║ %-10s ║ %-20s ║ %-11s ║ %-23s ║\n",
                        quy + 1, "Không có", "Không có", "Không có", "Không có");
            }
        }
        System.out.println("╚═══════╩════════════╩══════════════════════╩═════════════╩═══════════════════════╝");
    }


    public void timKiemKhachHangNangCao() {
        try {
            // Đọc danh sách tỉnh từ file
            String[] danhSachTinh = new String[64];
            int soTinh = 0;
            try (BufferedReader br = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/KhachHang/DanhSachTinh.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    danhSachTinh[soTinh++] = line.trim();
                }
            }

            // Hiển thị danh sách tỉnh
            System.out.println("Danh sách các tỉnh thành:");
            for (int i = 0; i < soTinh; i++) {
                System.out.printf("%d. %s\n", i + 1, danhSachTinh[i]);
            }

            // Nhập các tỉnh người dùng muốn tìm
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

            // Tìm kiếm khách hàng
            KhachHang[] danhSachTimDuoc = timKiemKhachHangTheoDiaChi(tinhDuocChon);
            if (danhSachTimDuoc.length == 0) {
                System.out.println("Không có khách hàng nào có địa chỉ thuộc danh sách bạn chọn.");
            } else {
                System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-10s | %-12s | %-18s | %-8s |\n",
                        "Mã KH", "Họ KH", "Tên KH", "Ngày Sinh", "Giới tính", "SĐT", "Địa chỉ", "Điểm");
                for (KhachHang khachHang : danhSachTimDuoc) {
                    if (khachHang != null) {
                        khachHang.xuat();
                    }
                }
                System.out.println("Tổng số khách hàng tìm được: " + danhSachTimDuoc.length);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }


    // Phương thức hỗ trợ tìm kiếm khách hàng theo danh sách địa chỉ
    public KhachHang[] timKiemKhachHangTheoDiaChi(String[] danhSachTinh) {
        KhachHang[] ketQua = new KhachHang[dskh.length];
        int index = 0;

        for (KhachHang khachHang : dskh) {
            if (khachHang == null) continue;

            for (String tinh : danhSachTinh) {
                if (khachHang.getDiaChi().contains(tinh)) {
                    ketQua[index++] = khachHang;
                    break; // Tìm thấy một địa chỉ khớp, không cần kiểm tra thêm
                }
            }
        }

        return Arrays.copyOf(ketQua, index);
    }

    public void capNhapSoLuongKhachHang() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (dskh[i] != null) {
                count++;
            }
        }
        size = count; // Cập nhật lại giá trị của size
        System.out.println("Số lượng khách hàng hiện có trong danh sách: " + size);
    }
}
