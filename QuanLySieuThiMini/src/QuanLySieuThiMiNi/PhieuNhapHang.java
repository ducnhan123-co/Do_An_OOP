    package QuanLySieuThiMiNi;


    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Scanner;

    public class PhieuNhapHang {
        private int maPhieu, maNCC, maNV;
        private double tongTien;
        private LocalDate ngayNhapHang;


        public PhieuNhapHang(int maNV,int maPhieu, int maNCC, double tongTien, LocalDate ngayNhapHang) {
            this.maNV = maNV;
            this.maPhieu = maPhieu;
            this.maNCC = maNCC;
            this.tongTien = tongTien;
            this.ngayNhapHang = ngayNhapHang;
        }

        public PhieuNhapHang() {
            this.maNV = 0;
            this.maPhieu = 0;
            this.maNCC = 0;
            this.tongTien = 0.0;
            this.ngayNhapHang = LocalDate.now();
        }

        // Getter và setter

        public int getMaNhanVien() {
            return maNV;
        }

        public void setMaNhanVien(int maNV) {
            this.maNV = maNV;
        }

        public int getMaPhieu() {
            return maPhieu;
        }

        public void setMaPhieu(int maPhieu) {
            this.maPhieu = maPhieu;
        }

        public int getMaNCC() {
            return maNCC;
        }

        public void setMaNCC(int maNCC) {
            this.maNCC = maNCC;
        }

        public double getTongTien() {
            return tongTien;
        }

        public void setTongTien(double tongTien) {
            this.tongTien = tongTien;
        }

        public LocalDate getNgayNhapHang() {
            return ngayNhapHang;
        }

        public void setNgayNhapHang(LocalDate ngayNhapHang) {
            this.ngayNhapHang = ngayNhapHang;
        }

        // Nhập phiếu nhập hàng, kiểm tra mã phiếu không trùng
        public void nhapPhieu() {
            Scanner sc = new Scanner(System.in);


            System.out.print("Nhập mã phiếu: ");
            maPhieu = sc.nextInt();

            System.out.print("Nhập mã nhân viên: ");
            maNV = sc.nextInt();

            System.out.print("Nhập mã nhà cung cấp: ");
            maNCC = sc.nextInt();
            ngayNhapHang = LocalDate.now();
            tongTien = 0;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (true) {
                System.out.print("Nhập ngày nhập hàng (dd/MM/yyyy): ");
                sc.nextLine(); // Clear buffer
                String ngayNhapStr = sc.nextLine();
                try {
                    ngayNhapHang = LocalDate.parse(ngayNhapStr, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
                }
            }

            System.out.println("Tổng tiền của phiếu nhập hàng là: " + tongTien);
        }

        public void xuatPhieu() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = ngayNhapHang.format(formatter);
            System.out.printf("|%-12d|%-12d|%-12d|%-12.2f|%-12s|\n",
                    this.maPhieu, this.maNCC, this.maNV, this.tongTien, formattedDate);
        }
    }
