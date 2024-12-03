package QuanLySieuThiMiNi.SanPham;

import QuanLySieuThiMiNi.ThaoTacFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachSanPham implements ThaoTacFile {
    public static SanPham[] DS_SanPham = new SanPham[0];

    public void push(SanPham sanPham) {
        DS_SanPham = copyOf(DS_SanPham, DS_SanPham.length+1);
        DS_SanPham[DS_SanPham.length-1] = sanPham;
    }
    // Thêm sản phẩm gia dụng hoặc thực phẩm vào danh sách
    public void themGiaDungHayThucPham() {
        // Resize array dynamically
        DS_SanPham = copyOf(DS_SanPham, DS_SanPham.length + 1);

        Scanner sc = new Scanner(System.in);
        System.out.print("Chọn loại sản phẩm (1-Gia dụng, 2-Thực phẩm): ");
        int choice = sc.nextInt();

        // Create a product based on the user's input
        if (choice == 1) {
            GiaDung giaDung = new GiaDung();
            giaDung.nhap();  // Nhập thông tin gia dụng
            DS_SanPham[DS_SanPham.length - 1] = giaDung;  // Add to the array
        } else if (choice == 2) {
            ThucPham thucPham = new ThucPham();
            thucPham.nhap();  // Nhập thông tin thực phẩm
            DS_SanPham[DS_SanPham.length - 1] = thucPham;  // Add to the array
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public boolean them1SanPham(SanPham sanPham) {
        // Kiểm tra nếu sản phẩm đã tồn tại
        for (SanPham sp : DS_SanPham) {
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
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|%-15s|%-20s|%-20s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Thương Hiệu", "Bảo Hành");

        // Xuất các sản phẩm gia dụng
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof GiaDung) {
                sp.xuat();  // Xuất thông tin của sản phẩm gia dụng
            }
        }
    }


    // Xuất danh sách sản phẩm thực phẩm
    public void xuatDanhSachThucPham() {
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|%-15s|%-20s|%-20s|%-15s|\n",
                "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại TP", "Hạn SD");

        for (SanPham sp : DS_SanPham) {
            if (sp instanceof ThucPham) {
                sp.xuat();  // Xuất thông tin của sản phẩm thực phẩm
            }
        }
    }

    public void xuatDanhSach() {
        // Tiêu đề chung cho tất cả sản phẩm
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-10s|%-12s|%-10s|%-15s|%-40s|%-20s|%-16s|\n",
        "Mã SP", "Tên SP", "ĐVT", "Đơn Giá", "Số Lượng", "Ngày SX", "Mô Tả", "Loại/Thương Hiệu", "Hạn SD/Bảo Hành");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Xuất danh sách sản phẩm gia dụng
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof GiaDung) {
                // Xuất thông tin của sản phẩm gia dụng
                GiaDung giaDung = (GiaDung) sp;
                giaDung.xuat();  // Gọi hàm xuất của GiaDung
            }
        }

        // Xuất danh sách sản phẩm thực phẩm
        for (SanPham sp : DS_SanPham) {
            if (sp instanceof ThucPham) {
                // Xuất thông tin của sản phẩm thực phẩm
                ThucPham thucPham = (ThucPham) sp;
                thucPham.xuat();  // Gọi hàm xuất của ThucPham
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    // Phương thức tìm sản phẩm theo mã sản phẩm
    public SanPham timSanPhamTheoMa(int maSP) {
        for(SanPham sp: DS_SanPham){
            if(sp.getMaSP() == maSP){
                return sp;  // Trả về sản phẩm nếu tìm thấy
            }
        }
        System.out.println("Sản phẩm với mã " + maSP + " không tồn tại!");
        return null;  // Nếu không tìm thấy, trả về null
    }

    // Phương thức tìm sản phẩm theo mã và trả về đơn giá
    public float timDonGiaTheoMa(int maSP) {
        for(SanPham sp: DS_SanPham) {
            if(sp.getMaSP() == maSP) {
                return sp.getDonGia();
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: "+maSP);
        return -1;
    }

    public boolean banSanPhamTheoMa(int maSP , int soLuongBan )
    {
        SanPham sp = timSanPhamTheoMa(maSP);
        if(sp!=null && sp.getSoLuong()>=soLuongBan){
            sp.setSoLuong(sp.getSoLuong()-soLuongBan);
            return true;
        }
        System.out.println("Khong du so luong de ban.");
        return false;
    }



    // Phương thức tìm tên sản phẩm theo mã sản phẩm
    public String timTenSanPhamTheoMa(int maSP) {
        for(SanPham sp: DS_SanPham) {
            if(sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return null;
    }

    // Nhập số lượng thêm hàng theo mã sản phẩm
    public void nhapHang(DanhSachSanPham danhSachSanPham, int maSP, int soLuongNhap) {
        // Tìm sản phẩm theo mã
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if (sp != null) {
            sp.setSoLuong(sp.getSoLuong() + soLuongNhap);
        } else {
            sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setSoLuong(soLuongNhap);
            // Thêm sản phẩm mới vào danh sách
            danhSachSanPham.them1SanPham(sp);
        }
    }

    // thống kê sản phẩm theo thực phẩm/gia dụng
    public void thongKeSanPhamTheoThucPham() {
        int countTP=0;
        float tongGiaTheoThucPham=0;
        float trungBinhTongGia=0;
        boolean found = false;

        for(SanPham i: DS_SanPham) {
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

        for(SanPham i: DS_SanPham) {
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

    public void thongKeSanPhamTheoTenLoaiORTenThuongHieu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int count=0;
                float tongGia=0;
                float trungBinhTongGia=0;

                System.out.print("Nhập tên loại hoặc thương hiệu của sản phẩm: ");
                String tenLoai_ThuongHieu = sc.nextLine(); 
                // tenLoai_ThuongHieu = tenLoai_ThuongHieu.toLowerCase();  

                boolean found = false;
                for(SanPham i: DS_SanPham) {
                    if(i instanceof ThucPham && ((ThucPham) i).getLoaiThucPham().trim().equalsIgnoreCase(tenLoai_ThuongHieu)) {    
                        count+=i.getSoLuong();
                        tongGia+=(i.getSoLuong()*i.getDonGia());
                        found = true;
                    }
                    else if(i instanceof GiaDung && ((GiaDung)i).getThuongHieu().trim().equalsIgnoreCase(tenLoai_ThuongHieu)) {        
                        count+=i.getSoLuong();
                        tongGia+=(i.getSoLuong()*i.getDonGia());
                        found = true;
                    }

                }
                if(found) {
                    trungBinhTongGia = (count > 0) ? (tongGia / count) : 0;
                    System.out.println("- Số lượng sản phẩm loại/hãng "+tenLoai_ThuongHieu+" trong kho: "+count);
                    System.out.printf("- Tổng giá trị của sản phẩm loại/hãng %s : %.2f VND\n", tenLoai_ThuongHieu, tongGia);
                    System.out.println("- Trung bình tổng giá của sản phẩm loại/hãng "+tenLoai_ThuongHieu+": "+trungBinhTongGia+" VND\n");
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

    public void thongKeSanPhamTheoLoaiVaThuongHieu() {
        Scanner sc = new Scanner(System.in);
        int countSoLuongThucPham=0, countSoLuongGiaDung=0;
        int countLoai=0, countThuongHieu=0;
        float tongGiaTheoLoai=0, tongGiaTheoThuongHieu=0;;
        float trungBinhTongGiaTheoLoai=0, trungBinhTongGiaTheoThuongHieu=0;
        while (true) {
            try {
                String tenLoai = sc.nextLine().trim(); 
                for(SanPham i: DS_SanPham) {
                    if(i instanceof ThucPham) {
                        if(((ThucPham)i).getLoaiThucPham().contains(tenLoai)) {
                            countLoai++;
                            countSoLuongThucPham+=i.getSoLuong();
                            tongGiaTheoLoai+=(i.getSoLuong()*i.getDonGia());
                        }
                    }
                    else if(i instanceof GiaDung) {
                        if(((GiaDung)i).getThuongHieu().contains(tenLoai)) {
                            countLoai++;
                            countSoLuongGiaDung+=i.getSoLuong();
                            tongGiaTheoThuongHieu+=(i.getSoLuong()*i.getDonGia());
                        }
                    } else {
                        System.out.println("Không có sản phẩm "+tenLoai+" để thống kê!\n");
                    }
                    System.out.println("--------------------------");
                }
            } catch (Exception e) {
                
            }
        }
    }

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
            for (SanPham sanPham : DS_SanPham) {
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


