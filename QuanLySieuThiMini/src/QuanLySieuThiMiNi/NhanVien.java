package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NhanVien {
    private int manv;
    private String chucvu;
    private String honhanvien;
    private String tennhanvien;
    private Date ngaysinh = new Date();
    private String diachi;
    private String sodienthoai;
    private double luong;
    private String gioiTinh;

    private static int tongSoNhanVien = 0;
    public NhanVien() {
        tongSoNhanVien++;
    }

    public NhanVien(int manv, String chucvu, String honhanvien, String tennhanvien, Date ngaysinh, String diachi, String sodienthoai, double luong, String gioiTinh) {
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

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
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

        System.out.println("nhap ngay sinh khach hang: ");
        System.out.print("nhap ngay: ");
        this.ngaysinh.setDate(sc.nextInt());
        System.out.print("nhap thang: ");
        this.ngaysinh.setMonth(sc.nextInt());
        System.out.print("nhap nam: ");
        this.ngaysinh.setYear(sc.nextInt());
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
    public int getNamSinh() {
        return ngaysinh.getYear() + 1900; // Lấy năm và cộng lại 1900 để có năm đầy đ
    }


    // Phương thức static để trả về tổng số nhân viên
    public static int getTongSoNhanVien() {
        return tongSoNhanVien;
    }


    public void themHoaDon(DanhSachHoaDon danhSachHoaDon, DanhSachHoaDonChiTiet danhSachHoaDonChiTiet, DanhSachSanPham danhSachSanPham) {
        Scanner scanner = new Scanner(System.in);

        // Tạo hóa đơn mới
        HoaDon hoaDonMoi = new HoaDon();
        hoaDonMoi.setMaNV(this.manv);  // Lấy mã nhân viên từ đối tượng NhanVien
        hoaDonMoi.nhapHoaDon(danhSachSanPham);  // Nhập thông tin hóa đơn bao gồm sản phẩm

        // Danh sách lưu chi tiết hóa đơn
        ArrayList<ChiTietHoaDon> chiTietList = new ArrayList<>();

        // Nhập các chi tiết hóa đơn
        System.out.print("Nhập số lượng chi tiết hóa đơn: ");
        int soLuongChiTiet = scanner.nextInt();

        for (int i = 0; i < soLuongChiTiet; i++) {
            System.out.println("Nhập chi tiết hóa đơn " + (i + 1) + ":");

            // Nhập mã sản phẩm
            System.out.print("Mã sản phẩm: ");
            int maSanPham = scanner.nextInt();

            // Nhập số lượng
            System.out.print("Số lượng: ");
            int soLuong = scanner.nextInt();

            // Nhập đơn giá
            System.out.print("Đơn giá: ");
            float donGia = scanner.nextFloat();

            // Tạo đối tượng chi tiết hóa đơn mới
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maSanPham, soLuong, donGia);

            // Thêm chi tiết hóa đơn vào danh sách tạm thời
            chiTietList.add(chiTietHoaDon);

            // Thêm chi tiết vào danh sách chi tiết hóa đơn của danh sách
            danhSachHoaDonChiTiet.themHoaDonChiTiet(chiTietHoaDon); // chua co phuong thuc nay ben chi tiet hoa don ??
        }

        // Gán danh sách chi tiết cho hóa đơn
        hoaDonMoi.setChiTietList(chiTietList);

        // Thêm hóa đơn vào danh sách hóa đơn
        danhSachHoaDon.themHoaDon(hoaDonMoi);//chua co phuong thuc nay ? ben hoa don ?

        // Thông báo
        System.out.println("Hóa đơn đã được tạo bởi nhân viên: " + this.tenNhanVien);
    }

    public void xoaHoaDon(DanhSachHoaDon danhSachHoaDon, DanhSachHoaDonChiTiet danhSachHoaDonChiTiet) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn cần xóa: ");
        int maHoaDon = scanner.nextInt();

        // Tìm kiếm hóa đơn trong danh sách
        HoaDon hoaDonCanXoa = null;
        for (HoaDon hoaDon : danhSachHoaDon) {
            if (hoaDon.getMaHD()==maHoaDon)
            {
                hoaDonCanXoa = hoaDon;
                break;
            }
        }

        if (hoaDonCanXoa != null) {
            // Xóa chi tiết hóa đơn liên quan
            for (ChiTietHoaDon chiTiet : danhSachHoaDonChiTiet) {
                danhSachHoaDonChiTiet.xoaHoaDonChiTiet(chiTiet); // Phương thức xóa chi tiết
            }
            // Xóa hóa đơn
            danhSachHoaDon.xoaHoaDon(hoaDonCanXoa);

            System.out.println("Hóa đơn đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }
    public void capNhatHoaDon(DanhSachHoaDon danhSachHoaDon, DanhSachHoaDonChiTiet danhSachHoaDonChiTiet) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mã hóa đơn cần cập nhật: ");
        String maHoaDon = scanner.nextLine();

        // Tìm kiếm hóa đơn trong danh sách
        HoaDon hoaDonCanCapNhat = null;
        for (HoaDon hoaDon : danhSachHoaDon.getDanhSachHoaDon()) {
            if (hoaDon.getMaHoaDon().equals(maHoaDon)) {
                hoaDonCanCapNhat = hoaDon;
                break;
            }
        }

        if (hoaDonCanCapNhat != null) {
            System.out.println("Hóa đơn tìm thấy: " + hoaDonCanCapNhat);

            // Cập nhật thông tin hóa đơn hoặc chi tiết hóa đơn
            // Ví dụ, thay đổi số lượng sản phẩm
            System.out.print("Nhập mã sản phẩm muốn cập nhật: ");
            int maSanPham = scanner.nextInt();
            HoaDonChiTiet chiTietCanCapNhat = null;
            for (HoaDonChiTiet chiTiet : hoaDonCanCapNhat.getChiTietList()) {
                if (chiTiet.getMaSanPham() == maSanPham) {
                    chiTietCanCapNhat = chiTiet;
                    break;
                }
            }

            if (chiTietCanCapNhat != null) {
                System.out.print("Nhập số lượng mới: ");
                int soLuongMoi = scanner.nextInt();
                chiTietCanCapNhat.setSoLuong(soLuongMoi);

                // Cập nhật lại chi tiết hóa đơn
                danhSachHoaDonChiTiet.capNhatChiTietHoaDon(chiTietCanCapNhat);
                System.out.println("Chi tiết hóa đơn đã được cập nhật.");
            }
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }
    // Phương thức thanh toán hóa đơn
    public void thanhToanHoaDon(DanhSachHoaDon danhSachHoaDon) {
        Scanner scanner = new Scanner(System.in);

        // Nhập mã hóa đơn cần thanh toán
        System.out.println("Nhập mã hóa đơn cần thanh toán: ");
        String maHoaDon = scanner.nextLine();

        // Tìm kiếm hóa đơn trong danh sách
        HoaDon hoaDonCanThanhToan = null;
        for (HoaDon hoaDon : danhSachHoaDon.getDanhSachHoaDon()) {
            if (hoaDon.getMaHoaDon().equals(maHoaDon)) {
                hoaDonCanThanhToan = hoaDon;
                break;
            }
        }

        if (hoaDonCanThanhToan != null) {
            // Hiển thị thông tin hóa đơn cho nhân viên và khách hàng
            System.out.println("Thông tin hóa đơn:");
            System.out.println(hoaDonCanThanhToan);

            // Yêu cầu khách hàng nhập số tiền thanh toán
            System.out.println("Khách hàng trả tiền: ");
            float soTienThanhToan = scanner.nextFloat();

            // Kiểm tra số tiền thanh toán có đủ không
            if (soTienThanhToan >= hoaDonCanThanhToan.getTongTien()) {
                // Cập nhật trạng thái hóa đơn thành đã thanh toán
                hoaDonCanThanhToan.setThanhToan(true); //ben hoa don

                // Tính tiền thừa (nếu có)
                float tienThua = soTienThanhToan - hoaDonCanThanhToan.getTongTien();

                // In ra thông báo và tiền thừa nếu có
                System.out.println("Thanh toán thành công.");
                if (tienThua > 0) {
                    System.out.println("Tiền thừa: " + tienThua);
                }
                System.out.println("Cảm ơn khách hàng đã thanh toán.");

            } else {
                // Trường hợp số tiền thanh toán không đủ
                System.out.println("Số tiền thanh toán không đủ. Vui lòng kiểm tra lại.");
            }
        } else {
            // Trường hợp không tìm thấy hóa đơn
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }


    public void xuatNhanVien() {
        System.out.println("Mã nhân viên ");
        System.out.println("Chưc vụ");
        System.out.println("Họ nhân viên: ");
        System.out.println("Tên nhân viên: ");
        System.out.println("Ngày sinh: ");
        System.out.println("Địa chỉ: ");
        System.out.println("Số điện thoại: ");
        System.out.println("Lương cơ bản: ");
        System.out.println("Giới tính: ");
    }



}