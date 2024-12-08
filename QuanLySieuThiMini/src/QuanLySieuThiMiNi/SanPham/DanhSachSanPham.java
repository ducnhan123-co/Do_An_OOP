package QuanLySieuThiMiNi.SanPham;

import static java.util.Arrays.copyOf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import QuanLySieuThiMiNi.ThaoTacFile;
import QuanLySieuThiMiNi.HoaDon.ChiTietHoaDon;
import QuanLySieuThiMiNi.HoaDon.DanhSachHoaDonChiTiet;
import QuanLySieuThiMiNi.PhieuNhapHang.ChiTietPhieuNhapHang;
import QuanLySieuThiMiNi.PhieuNhapHang.DanhSachChiTietPhieuNhapHang;

public  class DanhSachSanPham implements ThaoTacFile {
    public static SanPham[] dssp= new SanPham[0];

    public DanhSachSanPham() {
        
    }

    public void push(SanPham sanPham) {
        dssp = copyOf(dssp, dssp.length+1);
        dssp[dssp.length-1] = sanPham;
    }
    // Thêm sản phẩm gia dụng hoặc thực phẩm vào danh sách
    public void themGiaDungHayThucPham() {
        // Resize array dynamically
        dssp = copyOf(dssp, dssp.length + 1);

        Scanner sc = new Scanner(System.in);
        System.out.print("Chọn loại sản phẩm (1-Gia dụng, 2-Thực phẩm): ");
        int choice = sc.nextInt();

        // Create a product based on the user's input
        if (choice == 1) {
            GiaDung giaDung = new GiaDung();
            giaDung.nhap();  // Nhập thông tin gia dụng
            dssp[dssp.length - 1] = giaDung;  // Add to the array
        } else if (choice == 2) {
            ThucPham thucPham = new ThucPham();
            thucPham.nhap();  // Nhập thông tin thực phẩm
            dssp[dssp.length - 1] = thucPham;  // Add to the array
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public boolean them1SanPham(SanPham sanPham) {
        // Kiểm tra nếu sản phẩm đã tồn tại
        for (SanPham sp : dssp) {
            if (sp.getMaSP() == sanPham.getMaSP()) {
                System.out.println("Sản phẩm đã tồn tại!");
                return false; // Không thêm sản phẩm nếu đã tồn tại
            }
        }
        // Thêm sản phẩm mới
        this.push(sanPham); // Gọi phương thức push để thêm sản phẩm
        return true;
    }



    // Xuất danh sách sản phẩm gia dụng
    public void xuatDanhSachGiaDung() {
        // Tiêu đề cho danh sách gia dụng
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-10s|%-12s|%-10s|%-15s|%-40s|%-20s|%-16s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Thương Hiệu", "Bảo Hành");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        // Xuất các sản phẩm gia dụng
        for (SanPham sp : dssp) {
            if (sp instanceof GiaDung) {
                sp.xuat();  // Xuất thông tin của sản phẩm gia dụng
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }


    // Xuất danh sách sản phẩm thực phẩm
    public void xuatDanhSachThucPham() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-10s|%-12s|%-10s|%-15s|%-40s|%-20s|%-16s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại TP", "Hạn SD");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (SanPham sp : dssp) {
            if (sp instanceof ThucPham) {
                sp.xuat();  // Xuất thông tin của sản phẩm thực phẩm
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void xuatDanhSach() {
        // Tiêu đề chung cho tất cả sản phẩm
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-10s|%-12s|%-10s|%-15s|%-40s|%-20s|%-16s|\n",
        "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại/Thương Hiệu", "Hạn SD/Bảo Hành");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Xuất danh sách sản phẩm gia dụng
        for (SanPham sp : dssp) {
            if (sp instanceof GiaDung) {
                // Xuất thông tin của sản phẩm gia dụng
                GiaDung giaDung = (GiaDung) sp; // uboxing
                giaDung.xuat();  // Gọi hàm xuất của GiaDung
            }
        }

        // Xuất danh sách sản phẩm thực phẩm
        for (SanPham sp : dssp) {
            if (sp instanceof ThucPham) {
                // Xuất thông tin của sản phẩm thực phẩm
                ThucPham thucPham = (ThucPham) sp;
                thucPham.xuat();  // Gọi hàm xuất của ThucPham
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
    
    // Phương thức tìm sản phẩm theo mã sản phẩm
    public SanPham timSanPhamTheoMa(int maSP) {
        for(SanPham sp: dssp){
            if(sp.getMaSP() == maSP){
                System.out.println("Đã tìm thấy sản phẩm với mã : "+ maSP);
                return sp;  // Trả về sản phẩm nếu tìm thấy
            }
        }

        System.out.println("Sản phẩm với mã " + maSP + " không tồn tại!");
        return null;  // Nếu không tìm thấy, trả về null
    }

    public void timSanPhamTheoTen()
    {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm ");
        Scanner sc = new Scanner(System.in);
        String ten = sc.nextLine();
        boolean found = false;
        int dem=0;
        System.out.println("Danh sách sản phẩm có tên "+ten+" là : ");
        for(SanPham sanPham : dssp)
        {
            if(sanPham.getTenSP().contains(ten))
            {

                sanPham.xuat();
                dem++;
                found = true;
            }
        }
        System.out.println("Danh sách trên có :  "+ dem + " sản phẩm tên " + ten);
        if(!found)
        {
            System.out.println("Khoong tìm thấy sản phẩm nào với tên "+ ten);
        }
    }

    // Phương thức tìm sản phẩm theo mã và trả về đơn giá
    public float timDonGiaTheoMa(int maSP) {
        for(SanPham sp: dssp) {
            if(sp.getMaSP() == maSP) {
                return sp.getDonGia();
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: "+maSP);
        return -1;
    }
    
    public void capNhatTonKho(DanhSachChiTietPhieuNhapHang danhSachNhap, DanhSachHoaDonChiTiet danhSachBan) {
        for (SanPham sp : dssp) {
            sp.setSoLuong(0); // Đặt lại số lượng tồn kho

            // Cộng số lượng nhập
            for (ChiTietPhieuNhapHang chiTiet : danhSachNhap.getDanhSachChiTiet()) {
                if (chiTiet.getMaSp() == sp.getMaSP()) {
                    sp.setSoLuong(sp.getSoLuong() + chiTiet.getSl());
                }
            }

            // Trừ số lượng bán
            for (ChiTietHoaDon chiTiet : danhSachBan.getDanhSachChiTiet()) {
                if (chiTiet.getMaSP() == sp.getMaSP()) {
                    sp.setSoLuong(sp.getSoLuong() - chiTiet.getSoLuong());
                }
            }
        }
    }
    
    public void capNhatSoLuongSanPhamNhap() {
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Nhập mã sản phầm : ");
    	int maSP = sc.nextInt();
    	System.out.print("Nhập số lượng thêm vào: ");
    	int soLuongMoi = sc.nextInt();
        for (SanPham sp : dssp) {
            if (sp.getMaSP() == maSP) {
                // Cập nhật số lượng sản phẩm
                sp.setSoLuong(sp.getSoLuong() + soLuongMoi);
                System.out.println("Số lượng sản phẩm " + sp.getTenSP() + " đã được cập nhật: " + sp.getSoLuong());
                return;
            }
        }
        System.out.println("Sản phẩm với mã " + maSP + " không tồn tại!");
    }
    
    public void capNhatSoLuongSanPhamBan() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã sản phẩm: ");
        int maSP = sc.nextInt();

        System.out.print("Nhập số lượng cần lấy: ");
        int soLuongMoi = sc.nextInt();

        for (SanPham sp : dssp) {
            if (sp.getMaSP() == maSP) {
                // Kiểm tra xem số lượng sản phẩm có đủ để trừ hay không
                if (sp.getSoLuong() < soLuongMoi) {
                    System.out.println("Không đủ sản phẩm để bán!\n Số lượng hiện tại: " + sp.getSoLuong());
                } else {
                    // Cập nhật số lượng sản phẩm
                    sp.setSoLuong(sp.getSoLuong() - soLuongMoi);
                    System.out.println("Số lượng sản phẩm " + sp.getTenSP() + " đã được cập nhật: " + sp.getSoLuong());
                }
                return; // Thoát khỏi vòng lặp sau khi tìm thấy sản phẩm
            }
        }

        // Nếu không tìm thấy sản phẩm trong danh sách
        System.out.println("Sản phẩm với mã " + maSP + " không tồn tại!");
    }
    
    public void xemHangNhapBanVaTonKho(DanhSachChiTietPhieuNhapHang danhSachNhap, DanhSachHoaDonChiTiet danhSachBan) {
        System.out.println("╔═════════════╦════════════════════════╦════════════════╗");
        System.out.println("║   Mã SP     ║       Tên Sản Phẩm     ║ Số Lượng Tồn   ║");
        System.out.println("╠═════════════╬════════════════════════╬════════════════╣");

        for (SanPham sp : dssp) {
            int tongNhap = 0;
            int tongBan = 0;

            // Tính tổng số lượng nhập từ danh sách chi tiết phiếu nhập
            for (ChiTietPhieuNhapHang chiTietNhap : danhSachNhap.getDanhSachChiTiet()) {
                if (chiTietNhap.getMaSp() == sp.getMaSP()) {
                    tongNhap += chiTietNhap.getSl();
                }
            }

            // Tính tổng số lượng bán từ danh sách hóa đơn chi tiết
            for (ChiTietHoaDon chiTietBan : danhSachBan.getDanhSachChiTiet()) {
                if (chiTietBan.getMaSP() == sp.getMaSP()) {
                    tongBan += chiTietBan.getSoLuong();
                }
            }

            // In thông tin sản phẩm: Mã SP, Tên SP, Số Lượng Tồn Kho
            System.out.printf("║ %-11d ║ %-22s ║ %-14d ║\n",
                              sp.getMaSP(), sp.getTenSP(), sp.getSoLuong());
        }

        System.out.println("╚═════════════╩════════════════════════╩════════════════╝");
    }


//    public void banHang(DanhSachSanPham danhSachSanPham, int maSP, int soLuongBan) {
//        // Tìm sản phẩm
//        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
//        if (sp == null) {
//            System.out.println("Không tìm thấy sản phẩm với mã: " + maSP);
//            return;
//        }
//        // Kiểm tra tồn kho
//        if (sp.getSoLuong() < soLuongBan) {
//            System.out.println("Số lượng tồn kho không đủ để bán.");
//            return;
//        }
//        // Cập nhật số lượng tồn kho
//        sp.setSoLuong(sp.getSoLuong() - soLuongBan);
//        System.out.println("Bán hàng thành công.");
//    }




    // Phương thức tìm tên sản phẩm theo mã sản phẩm
    public String timTenSanPhamTheoMa(int maSP) {
        for(SanPham sp: dssp) {
            if(sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return null;
    }

    // Nhập số lượng thêm hàng theo mã sản phẩm
    public void nhapHang(DanhSachSanPham danhSachSanPham, DanhSachChiTietPhieuNhapHang danhSachChiTietPhieuNhapHang, int maSP, int soLuongNhap, int maPhieu) {
        // Tìm hoặc thêm sản phẩm
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if (sp != null) {
            sp.setSoLuong(sp.getSoLuong() + soLuongNhap); // Cộng thêm số lượng
        } else {
            sp.setMaSP(maSP);
            sp.setSoLuong(soLuongNhap);
            danhSachSanPham.them1SanPham(sp); // Thêm mới
        }

        // Thêm chi tiết phiếu nhập hàng
        ChiTietPhieuNhapHang chiTiet = new ChiTietPhieuNhapHang();
        chiTiet.setMaPhieu(maPhieu);
        chiTiet.setMaSp(maSP);
        chiTiet.setSl(soLuongNhap);
        danhSachChiTietPhieuNhapHang.themChiTietVaoPhieu(chiTiet.getMaPhieu());

        System.out.println("Nhập hàng thành công.");
    }


    // thống kê sản phẩm theo thực phẩm/gia dụng
    public void thongKeSanPhamTheoThucPham() {
        int countTP=0;
        float tongGiaTheoThucPham=0;
        float trungBinhTongGia=0;
        boolean found = false;

        for(SanPham i: dssp) {
            if(i instanceof ThucPham) {
                countTP+=i.getSoLuong();
                tongGiaTheoThucPham+=(i.getSoLuong()*i.getDonGia());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Không có thực phẩm nào để thống kê!\n");
            return;
        }
        trungBinhTongGia = (countTP > 0) ? (tongGiaTheoThucPham/countTP) : 0;

        System.out.println("- Tổng số lượng thực phẩm trong kho: "+countTP);
        System.out.printf("- Tổng giá các thực phẩm trong kho: %.2f VND\n", tongGiaTheoThucPham);
        System.out.println("- Trung bình tổng giá thực phẩm: "+trungBinhTongGia+" VND\n");
    }

    // Thống kê sản phẩm theo gia dụng
    public void thongKeSanPhamTheoGiaDung() {
        int countGD=0;
        float tongGiaTheoGiaDung=0;
        float trungBinhTongGia=0;
        boolean found = false;

        for(SanPham i: dssp) {
            if(i instanceof GiaDung) {
                countGD+=i.getSoLuong();
                tongGiaTheoGiaDung = tongGiaTheoGiaDung + (i.getSoLuong()*i.getDonGia());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Không có gia dụng nào để thống kê!\n");
            return;
        }
        trungBinhTongGia = (countGD > 0) ? (tongGiaTheoGiaDung/countGD) : 0;

        System.out.println("- Tổng số lượng thực phẩm trong kho: "+countGD);
        System.out.printf("- Tổng giá các thực phẩm trong kho: %.2f VND\n", tongGiaTheoGiaDung);
        System.out.println("- Trung bình tổng giá thực phẩm: "+trungBinhTongGia+" VND\n");
    }

    public void thongKeSanPhamTheoNgaySanXuat() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                int countLoaiSanPham=0;
                int count=0;
                float tongTienSanPham=0;
                float trungBinhTongTienSanPham=0;
                boolean found = false;
                
                System.out.print("Nhập ngày sản xuất sản phẩm (yyyy-MM-dd): ");
                String ngayStr = sc.nextLine().trim();

                LocalDate ngayNhap;
                try {
                    ngayNhap = LocalDate.parse(ngayStr, df);
                } catch (Exception e) {
                    System.out.println("Nhập ngày sai định dạng. Vui lòng nhập đúng định dạng!\n");
                    continue;
                }

                for(SanPham i: dssp) {
                    if(i.getNgaySX().equals(ngayStr)) {
                        countLoaiSanPham++;
                        count+=i.getSoLuong();
                        tongTienSanPham+=(i.getSoLuong()*i.getDonGia());
                        found = true;
                    }
                }
                if(!found) {
                    System.out.println("Không có hóa đơn nào trong ngày "+ngayStr);
                }
                trungBinhTongTienSanPham = (count>0) ? (tongTienSanPham/count) : 0;
                System.out.println("Kết quả thống kê sản phẩm ngày "+ngayStr+": ");
                System.out.println("- "+countLoaiSanPham+" loại sản phẩm.");
                System.out.println("- Số lượng sản phẩm trong ngày "+ngayStr+": "+count+" cái");
                System.out.println("- Tổng giá tiền các sản phẩm trong ngày "+ngayStr+": "+tongTienSanPham+" VND");
                System.out.println("- Trung bình tổng các sản phẩm trong ngày "+ngayStr+": "+trungBinhTongTienSanPham+" VND\n");
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: "+e.getMessage());
            }
        }
    }
    public boolean xoaSanPhamTheoMa(int maSP) {
        boolean timThay = false;

        for (int i = 0; i < dssp.length; i++) {
            // Kiểm tra nếu mã sản phẩm trùng khớp
            if (dssp[i] != null && dssp[i].getMaSP() == maSP) {
                timThay = true;

                // Dịch chuyển các phần tử phía sau lên trước
                for (int j = i; j < dssp.length - 1; j++) {
                    dssp[j] = dssp[j + 1];
                }

                // Gán phần tử cuối cùng là null (sau khi dịch chuyển)
                dssp[dssp.length - 1] = null;

                break; // Thoát khỏi vòng lặp sau khi xóa
            }
        }

        return timThay;
    }

    public boolean xoaSanPhamTheoTen(String tenSP) {
        boolean timThay = false;

        for (int i = 0; i < dssp.length; i++) {
            // Kiểm tra nếu tên sản phẩm trùng khớp
            if (dssp[i] != null && dssp[i].getTenSP().equalsIgnoreCase(tenSP)) {
                timThay = true;

                // Dịch chuyển các phần tử phía sau lên trước
                for (int j = i; j < dssp.length - 1; j++) {
                    dssp[j] = dssp[j + 1];
                }

                // Gán phần tử cuối cùng là null (sau khi dịch chuyển)
                dssp[dssp.length - 1] = null;

                break; // Thoát khỏi vòng lặp sau khi xóa
            }
        }

        return timThay;
    }



    public void thongKeSanPhamTheoTenLoaiORTenThuongHieu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int count=0;
                float tongGia=0;
                float trungBinhTongGia=0;

                System.out.print("Nhập tên loại hoặc thương hiệu của sản phẩm: ");
                String tenLoai_ThuongHieu = sc.nextLine(); 

                boolean found = false;
                for(SanPham i: dssp) {
                    if(i instanceof ThucPham && ((ThucPham) i).getLoaiThucPham().contains(tenLoai_ThuongHieu)) {    
                        ThucPham thucPham = new ThucPham();
                        thucPham = (ThucPham) i;
                        count+=thucPham.getSoLuong();
                        tongGia+=(thucPham.getSoLuong()*thucPham.getDonGia());
                        found = true;
                    }
                    else if(i instanceof GiaDung && ((GiaDung)i).getThuongHieu().contains(tenLoai_ThuongHieu)) {        
                        GiaDung giaDung = new GiaDung();
                        giaDung = (GiaDung) i;
                        count+=giaDung.getSoLuong();
                        tongGia+=(giaDung.getSoLuong()*giaDung.getDonGia());
                        found = true;
                    }

                }
                if(found) {
                    trungBinhTongGia = (count > 0) ? (tongGia / count) : 0;
                    System.out.println("- Số lượng sản phẩm loại/hãng "+tenLoai_ThuongHieu+" trong kho: "+count);
                    System.out.printf("- Tổng giá trị của sản phẩm loại/hãng %s : %.2f VND\n", tenLoai_ThuongHieu, tongGia);
                    System.out.printf("- Trung bình tổng giá của sản phẩm loại/hãng %s: .2f% VND\n", tenLoai_ThuongHieu, trungBinhTongGia);
                    break;
                } else {
                    System.out.println("Không có sản phẩm \""+tenLoai_ThuongHieu+"\" để thống kê!\n");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: "+e.getMessage());
            }
        }
    }


    // public void thongKeSanPhamTheoLoaiVaThuongHieu() {
    //     Scanner sc = new Scanner(System.in);
    //     int countSoLuongThucPham=0, countSoLuongGiaDung=0;
    //     int countLoai=0, countThuongHieu=0;
    //     float tongGiaTheoLoai=0, tongGiaTheoThuongHieu=0;;
    //     float trungBinhTongGiaTheoLoai=0, trungBinhTongGiaTheoThuongHieu=0;
    //     while (true) {
    //         try {
    //             String tenLoai = sc.nextLine().trim(); 
    //             for(SanPham i: dssp) {
    //                 if(i instanceof ThucPham) {
    //                     if(((ThucPham)i).getLoaiThucPham().contains(tenLoai)) {
    //                         countLoai++;
    //                         countSoLuongThucPham+=i.getSoLuong();
    //                         tongGiaTheoLoai+=(i.getSoLuong()*i.getDonGia());
    //                     }
    //                 }
    //                 else if(i instanceof GiaDung) {
    //                     if(((GiaDung)i).getThuongHieu().contains(tenLoai)) {
    //                         countLoai++;
    //                         countSoLuongGiaDung+=i.getSoLuong();
    //                         tongGiaTheoThuongHieu+=(i.getSoLuong()*i.getDonGia());
    //                     }
    //                 } else {
    //                     System.out.println("Không có sản phẩm "+tenLoai+" để thống kê!\n");
    //                 }
    //                 System.out.println("--------------------------");
    //             }
    //         } catch (Exception e) {
                
    //         }
    //     }
    // }

    public void docFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/SanPham/SanPham.txt"));
            String line;
            int count = 0; // Đếm số dòng được đọc
            while ((line = reader.readLine()) != null) {
                String[] sp = line.split(",");
                if (sp.length < 10) { // Kiểm tra cấu trúc dữ liệu
                    System.err.println("Dữ liệu không hợp lệ: " + line);
                    continue;
                }
                try {
                    if (Integer.parseInt(sp[0].trim()) == 0) {
                        push(new ThucPham(
                                Integer.parseInt(sp[1].trim()), sp[2].trim(), sp[3].trim(),
                                Float.parseFloat(sp[4].trim()), Integer.parseInt(sp[5].trim()),
                                sp[6].trim(), sp[7].trim(), sp[8].trim(), Integer.parseInt(sp[9].trim())
                        ));
                    } else {
                        push(new GiaDung(
                                Integer.parseInt(sp[1].trim()), sp[2].trim(), sp[3].trim(),
                                Float.parseFloat(sp[4].trim()), Integer.parseInt(sp[5].trim()),
                                sp[6].trim(), sp[7].trim(), sp[8].trim(), Integer.parseInt(sp[9].trim())
                        ));
                    }
                    count++;
                } catch (Exception e) {
                    System.err.println("Lỗi xử lý dòng dữ liệu: " + line);
                    e.printStackTrace();
                }
            }
            reader.close();
//            System.out.println("Đã đọc thành công " + count + " sản phẩm từ file.");
        } catch (Exception e) {
            System.err.println("Không thể đọc file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("QuanLySieuThiMini/src/QuanLySieuThiMiNi/SanPham/SanPham.txt"));
            int count = 0; // Đếm số sản phẩm được ghi
            for (SanPham sanPham : dssp) {
                String loai;
                String line;
                if (sanPham instanceof ThucPham) {
                    loai = "0";
                    ThucPham thucPham = (ThucPham) sanPham;
                    line = String.format("%s,%d,%s,%s,%f,%d,%s,%s,%s,%d\n",
                            loai, sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDVT(),
                            sanPham.getDonGia(), sanPham.getSoLuong(), sanPham.getNgaySX(),
                            sanPham.getMoTa(), thucPham.getLoaiThucPham(), thucPham.getHanSuDung());
                } else {
                    loai = "1";
                    GiaDung giaDung = (GiaDung) sanPham;
                    line = String.format("%s,%d,%s,%s,%f,%d,%s,%s,%s,%d\n",
                            loai, sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDVT(),
                            sanPham.getDonGia(), sanPham.getSoLuong(), sanPham.getNgaySX(),
                            sanPham.getMoTa(), giaDung.getThuongHieu(), giaDung.getBaoHanh());
                }
                bw.write(line);
                count++;
            }
            bw.close();
            System.out.println("Đã ghi thành công " + count + " sản phẩm vào file.");
        } catch (Exception e) {
            System.err.println("Không thể ghi file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void capNhatFile() {
        ghiFile();
    }


}


