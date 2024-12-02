package QuanLySieuThiMiNi.HoaDon;
import QuanLySieuThiMiNi.ThaoTacFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.util.Arrays.copyOf;

public class DanhSachHoaDon implements ThaoTacFile {
    private static HoaDon[] dshd = new HoaDon[0];

    public DanhSachHoaDon() {}

    public DanhSachHoaDon(DanhSachHoaDon other) {
        this.dshd = other.dshd;
    }

    public boolean checkHD_datontai(int maHD) {
        for(HoaDon i: dshd) {
            if(i.getMaHD() == maHD)
                return false;
        }
        return true;
    }

    public boolean is_empty() {
        return dshd.length == 0;
    }

    public static HoaDon[] getDshd() {
        return dshd;
    }

    // Xem danh sách hóa đơn
    public void xemDSHD() {
        System.out.println("\n*Danh Sách Hóa Đơn:");
        for(HoaDon hoaDon: dshd){
            hoaDon.xuatHoaDon();
        }
        System.out.println();
    }

//    note
    public void xemAll() {
        if(dshd.length==0){
            System.out.println("Danh sách rỗng!\n");
            return;
        }
        for(HoaDon i: dshd) {
            i.xuatHoaDon();
            new DanhSachHoaDonChiTiet().xuatChiTietHoaDonTheoMHD(i.getMaHD());
            System.out.println();
        }
    }

    // Thêm 1 hóa đơn vào cuối danh sách
    public void push(HoaDon hoaDon) {
        dshd = copyOf(dshd, dshd.length+1) ;
        dshd[dshd.length-1] = hoaDon;
    }

    // Xóa hóa đơn theo Mã hóa đơn
    public void xoaHoaDonTheoMa(int maHD) {
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i].getMaHD() == maHD) {
                for(int j=i; j<dshd.length-1; j++) {
                    dshd[j] = dshd[j+1];
                }
                dshd = copyOf(dshd, dshd.length-1);
                System.out.println("Đã xóa hóa đơn có mã: "+maHD);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn có mã" + maHD);
    }

    // Tìm kiếm hóa đơn theo mã (trả về vị trí int)
    public int timMaHoaDon(int maHD) {
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i].getMaHD() == maHD) {
                return i;
            }
        }
        return -1;
    }

    // Tìm kiếm hóa đơn theo mã (trả về HoaDon)
    public HoaDon timKiemHoaDon(int maHD) {
        for(HoaDon i: dshd) {
            if(i.getMaHD() == maHD) {
                System.out.println("\nĐã tìm thấy hóa đơn có mã "+maHD+"!");
                return i;
            }
        }
        System.out.println("Không tìm thấy mã hóa đơn: "+maHD+"!");
        return null;
    }

    public void xuatHoaDonTheoMa(int maHD) {
        for (HoaDon i: dshd) {
            if (i.getMaHD() == maHD) {
                i.xuatHoaDon();
                return ;
            }
        }
        System.out.println("Không tìm thấy hóa đơn!\n");
    }

    // Tìm kiếm và liệt kê các hóa đơn theo ngày tạo hóa đơn
    public void timHoaDonTheoNgayTaoHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ngày tạo hóa đơn (dd-mm-yyyy): ");
        String ngayTao = sc.nextLine() ;
        boolean found=false;
        
        // Duyệt qua tất cả các hóa đơn để tìm những hóa đơn có ngày tạo bằng ngày nhập
        System.out.println("DANH SÁCH HÓA ĐƠN "+ngayTao+": ");
        for (int i = 0; i < dshd.length; i++) {
            if (dshd[i].getNgayTaoHoaDon().equals(ngayTao)) {  // So sánh ngày tạo
                dshd[i].xuatHoaDon();  // Xuất thông tin của hóa đơn tìm được
                found = true;  // Đánh dấu là đã tìm thấy ít nhất một hóa đơn
            }
        }

        // Nếu không tìm thấy hóa đơn nào
        if (!found) {
            System.out.println("Không có hóa đơn nào được tạo vào ngày " + ngayTao);
            return;
        }
    }

    // Tìm kiếm và liệt kê các hóa đơn theo ngày tạo hóa đơn kiểu HoaDon[]
    public HoaDon[] timHoaDonTheoNgayTaoHoaDon1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ngày hóa đơn được tạo (dd-mm-yyyy): ");
        String ngayStr = sc.nextLine();
        System.out.println();
        int count=0;

        for(HoaDon i: dshd) {
            if(i!=null && i.getNgayTaoHoaDon().equals(ngayStr)) {
                count++;
                
            }
        }
        if(count==0) {
            return new HoaDon[0];
        }

        HoaDon[] tmp = new HoaDon[count];
        int index=0;
        for(HoaDon i: dshd) {
            if(i != null && i.getNgayTaoHoaDon().equals(ngayStr)) {
                tmp[index++] = i;
            }
        }
        return tmp;
    }

    // Tìm kiếm hóa đơn theo giá trị tổng tiền (Trả về kiểu HoaDon)
    public HoaDon timHoaDonTheoTongTien(float tongTien) {
        for(HoaDon i: dshd) {
            if(i.getTongTien() == tongTien) {
                System.out.println("\nĐã tìm thấy hóa đơn có tổng tiền: " + tongTien+" VND!");
                return i;
            }
        }
        return null;
    }

    // Tìm kiếm hóa đơn có tổng tiền lớn nhất
    public HoaDon timHoaDonTongTienLonNhat() {
        if(dshd.length==0 || dshd==null) {
            System.out.println("Không có hóa đơn nào!");
            return null;
        } 
        double maxTongTien = 0;
        HoaDon hoaDonMax = null;
        for(HoaDon i: dshd) {
            if(i.getTongTien() > maxTongTien) {
                maxTongTien = i.getTongTien();
                hoaDonMax = i;
            }
        }
        return hoaDonMax;
    }

    // Tìm kiếm hóa đơn có tổng tiền nhỏ nhất
    public HoaDon timHoaDonTongTienNhoNhat() {
        if(dshd.length==0 || dshd==null) {
            System.out.println("Không có hóa đơn nào!");
            return null;
        } 
        double minTongTien = Integer.MAX_VALUE;
        HoaDon hoaDonMin = null;
        for(HoaDon i: dshd) {
            if(i.getTongTien() < minTongTien) {
                minTongTien = i.getTongTien();
                hoaDonMin = i;
            }
        }
        return hoaDonMin;
    }

    // Thống kê theo tổng tiền của tất cả hóa đơn
    public void tongDoanhThu() {
        float tongTien = 0;
        int count=0;
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i] != null) {
                count++;
                tongTien += dshd[i].getTongTien();
            }
        }
        
        if (count == 0) {
            System.out.println("Không có hóa đơn để thống kê.");
        } else {
            System.out.println("Tổng số hóa đơn: "+count+" cái hóa đơn");
            System.out.println("Tổng doanh thu: "+tongTien+" VND.");
            System.out.println("Trung bình tổng tiền các hóa đơn: " + ((float) tongTien / count) + " VND.");
        }
    }

    // Thống kê số lượng hóa đơn trả kiểu void
    public void thongKeSoLuongHoaDon1() {
        System.out.println("Số lượng hóa đơn: "+dshd.length+" cái hóa đơn.\n");
    }

    // Thống kê số lượng hóa đơn trả về kiểu int
    public int thongKeSoLuongHoaDon2() {
        return dshd.length;
    }

    // Thống kê hóa đơn theo ngày tạo hóa đơn

    // Thống kê hóa đơn theo ngày tháng năm
    public void thongKeHoaDonTheoNgayThangNam() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập ngày (dd-MM-yyyy): ");
                String ngayStr = sc.nextLine().trim();

                String[] tmp = ngayStr.split("-");
                if(tmp.length != 3)
                    throw new Exception("Nhập sai định dạng (dd-mm-yyyy).");

                float tongDoanhThu=0;
                int hoaDonCount=0;
                float tongDoanhThuTrungBinh=0;

                for(HoaDon hoaDon: dshd) {
                    if(hoaDon.getNgayTaoHoaDon().equals(ngayStr)) {
                        hoaDonCount++;
                        tongDoanhThu+=hoaDon.getTongTien();
                    }
                }
                if(hoaDonCount==0) {
                    System.out.println("Không có hóa đơn nào trong "+ngayStr+".");
                    return;
                }
                tongDoanhThuTrungBinh=tongDoanhThu/hoaDonCount;

                System.out.println("\n- Số lượng hóa đơn trong " + ngayStr + ": " + hoaDonCount+" cái hóa đơn");
                System.out.println("- Tổng doanh thu trong " + ngayStr + ": "+tongDoanhThu+" VND");
                System.out.println("- Trung bình tổng tiền các hóa đơn trong "+ngayStr+": "+tongDoanhThuTrungBinh+" VND\n");
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một giá trị hợp lệ.");
            } catch (Exception e) {
                System.out.println("Lỗi: "+e.getMessage());
            }
        }
    }

    // Thống kê hóa đơn theo tháng năm
    public void thongKeHoaDonTheoThangNam() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập tháng năm: ");
                String ngayNhap = sc.nextLine().trim();

                String[] tmp = ngayNhap.split("-");
                if (tmp.length != 2) 
                    throw new Exception("Nhập sai định dạng(mm-yyyy)");
                String thang = tmp[0].trim();
                String nam = tmp[1].trim();

                int soluongHoaDon=0;
                float tongDoanhThu=0;
                float tongDoanhThuTrungBinh=0;
                
                for(HoaDon hoaDon: dshd) {
                    if(hoaDon != null) {
                        tmp = hoaDon.getNgayTaoHoaDon().split("-");
                        if (tmp[1].trim().equals(thang) && tmp[2].trim().equals(nam)) {
                            soluongHoaDon++;
                            tongDoanhThu+=hoaDon.getTongTien();
                        }
                    }
                }
                tongDoanhThuTrungBinh = tongDoanhThu / soluongHoaDon;

                if(soluongHoaDon==0) {
                    System.out.println("Không có hóa đơn nào trong tháng "+thang+" năm "+nam);
                    return;
                }

                System.out.println("\n- Số lượng hóa đơn trong tháng "+thang+" năm "+nam+": "+soluongHoaDon+" cái hóa đơn");
                System.out.println("- Tổng doanh thu trong tháng "+thang+" năm "+nam+": "+tongDoanhThu+" VND");
                System.out.println("- Trung bình tổng tiền các hóa đơn trong tháng "+thang+" năm "+nam+": "+tongDoanhThuTrungBinh+" VND\n");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập giá trị hợp lệ.");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: "+e.getMessage());
            }
        }
    }

    // Thống kê hóa đơn theo năm
    public void thongKeHoaDonTheoNam() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập năm (yyyy): ");
                String namStr = sc.nextLine();
                int namNhap = Integer.parseInt(namStr);

                int countHoaDon=0;
                float tongTien=0;
                float tongTienTrungBinh=0;

                for(HoaDon hoaDon: dshd) {
                    int year = Integer.parseInt((hoaDon.getNgayTaoHoaDon().split("-")[2].trim()));
                    if (year == namNhap) {
                        countHoaDon++;
                        tongTien+=hoaDon.getTongTien();
                    }
                }
                tongTienTrungBinh = tongTien / countHoaDon;

                if(countHoaDon==0) {
                    System.out.println("Không có hóa đơn nào trong năm "+namNhap+".");
                    return;
                }

                System.out.println("\n- Số lượng hóa đơn trong năm "+namNhap+": "+countHoaDon+" cái hóa đơn");
                System.out.println("- Tổng doanh thu trong năm "+namNhap+": "+tongTien+" VND");
                System.out.println("- Tổng doanh thu trung bình trong năm "+namNhap+": "+tongTienTrungBinh+" VND\n");
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng yyyy.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập giá trị hợp lệ.");
            } catch (Exception e) {
                System.out.println("Lỗi: "+e.getMessage());
            }
        }
    }

    // Thống kê hóa đơn theo quý
    public void thongKeHoaDonTheoQuy() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm cần thống kê theo quý:");
        int namCanThongKe = sc.nextInt();

        double tongTienQuy1 = 0;
        double tongTienQuy2 = 0;
        double tongTienQuy3 = 0;
        double tongTienQuy4 = 0;

        int soLuongHoaDonQuy1 = 0;
        int soLuongHoaDonQuy2 = 0;
        int soLuongHoaDonQuy3 = 0;
        int soLuongHoaDonQuy4 = 0;

        
        boolean foundNam = false;
        
        for(int i=0; i<dshd.length; i++){
            if(dshd[i] != null) {
                String[] tmp = dshd[i].getNgayTaoHoaDon().split("-");
                int thang = Integer.parseInt(tmp[1]);
                int nam = Integer.parseInt(tmp[2]);

                if(namCanThongKe == nam) {
                    foundNam = true;
                    if(thang>=1 && thang<=3) {
                        tongTienQuy1+=dshd[i].getTongTien();
                        soLuongHoaDonQuy1++;
                    } else if(thang>=4 && thang<=6) {
                        tongTienQuy2+=dshd[i].getTongTien();
                        soLuongHoaDonQuy2++;
                    } else if(thang>=7 && thang<=9) {
                        tongTienQuy3+=dshd[i].getTongTien();
                        soLuongHoaDonQuy3++;
                    } else if(thang>=10 && thang<=12) {
                        tongTienQuy4+=dshd[i].getTongTien();
                        soLuongHoaDonQuy4++;
                    }
                }
            } 
        }

        double maxTongTienDoanhThu = tongTienQuy1;
        double minTongTienDoanhThu = Integer.MAX_VALUE;
        int quyMax = 1;
        int quyMin = -1;
        // Tìm quý tổng doanh thu lớn nhất
        if(tongTienQuy2 > maxTongTienDoanhThu){
            maxTongTienDoanhThu = tongTienQuy2;
            quyMax = 2;
        }
        if(tongTienQuy3 > maxTongTienDoanhThu) {
            maxTongTienDoanhThu = tongTienQuy3;
            quyMax = 3;
        }
        if(tongTienQuy4 > maxTongTienDoanhThu) {
            maxTongTienDoanhThu = tongTienQuy4;
            quyMax = 4;
        }

        // Tìm quý tổng doanh thu nhỏ nhất
        if((tongTienQuy1 < minTongTienDoanhThu) && (soLuongHoaDonQuy1>0)) {
            minTongTienDoanhThu = tongTienQuy1;
            quyMin = 1;
        }
        if((tongTienQuy2 < minTongTienDoanhThu) && (soLuongHoaDonQuy2>0)) {
            minTongTienDoanhThu = tongTienQuy2;
            quyMin = 2;
        }
        if((tongTienQuy3 < minTongTienDoanhThu) && (soLuongHoaDonQuy3>0)) {
            minTongTienDoanhThu = tongTienQuy3;
            quyMin = 3;
        }
        if((tongTienQuy4 < minTongTienDoanhThu) && (soLuongHoaDonQuy4>0)) {
            minTongTienDoanhThu = tongTienQuy4;
            quyMin = 4;
        }

        if(!foundNam) {
            System.out.println("Không có hóa đơn nào trong năm "+namCanThongKe+" để thống kê!");
            return;
        }
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║                      THỐNG KÊ HÓA ĐƠN THEO QUÝ NĂM %d                       ║\n", namCanThongKe);
        System.out.println("╠════════════╦════════════════════╦══════════════════════╦══════════════════════╣");
        System.out.printf("║ %-10s ║ %-18s ║ %-20s ║ %-20s ║\n", "Quý", "Số Lượng Hóa Đơn", "Tổng Doanh Thu", "Doanh Thu Trung Bình");
        System.out.println("╠════════════╬════════════════════╬══════════════════════╬══════════════════════╣");
        System.out.printf("║ %-10s ║ %-18d ║ %-20.2f ║ %-20.2f ║\n", "Quý 1", soLuongHoaDonQuy1, tongTienQuy1, (soLuongHoaDonQuy1 > 0 ? (tongTienQuy1 / soLuongHoaDonQuy1) : 0));
        System.out.printf("║ %-10s ║ %-18d ║ %-20.2f ║ %-20.2f ║\n", "Quý 2", soLuongHoaDonQuy2, tongTienQuy2, (soLuongHoaDonQuy2 > 0 ? (tongTienQuy2 / soLuongHoaDonQuy2) : 0));
        System.out.printf("║ %-10s ║ %-18d ║ %-20.2f ║ %-20.2f ║\n", "Quý 3", soLuongHoaDonQuy3, tongTienQuy3, (soLuongHoaDonQuy3 > 0 ? (tongTienQuy3 / soLuongHoaDonQuy3) : 0));
        System.out.printf("║ %-10s ║ %-18d ║ %-20.2f ║ %-20.2f ║\n", "Quý 4", soLuongHoaDonQuy4, tongTienQuy4, (soLuongHoaDonQuy4 > 0 ? (tongTienQuy4 / soLuongHoaDonQuy4) : 0));
        System.out.println("╚════════════╩════════════════════╩══════════════════════╩══════════════════════╝");
        System.out.println("=> Quý "+quyMax+" có tổng doanh thu lớn nhất: "+maxTongTienDoanhThu+" VND");
        System.out.println("=> Quý "+quyMin+" có tổng doanh thu nhỏ nhất: "+minTongTienDoanhThu+" VND");
    }

    // Thống kê hóa đơn theo khoảng thời gian
    public void thongKeHoaDonTheoKhoangThoiGian() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Nhập khoảng thời gian (từ ngày A -> ngày B):");
                
                System.out.print("Từ ngày (dd-MM-yyyy): ");
                String ngayStrBD = sc.nextLine().trim();
                String[] tmp1 = ngayStrBD.split("-");
                if(tmp1.length!=3)
                    throw new Exception("Nhập sai định dạng.\n");
                int ngayBD = Integer.parseInt(tmp1[0].trim());
                int thangBD = Integer.parseInt(tmp1[1].trim());
                int namBD = Integer.parseInt(tmp1[2].trim());

                System.out.print("Đến ngày (dd-MM-yyyy): ");
                String ngayStrKT = sc.nextLine().trim();
                String[] tmp2 = ngayStrKT.split("-");
                if(tmp2.length!=3)
                    throw new Exception("Nhập sai định dạng.\n");
                int ngayKT = Integer.parseInt(tmp2[0].trim());
                int thangKT = Integer.parseInt(tmp2[1].trim());
                int namKT = Integer.parseInt(tmp2[2].trim());

                if(namBD>namKT || (namBD==namKT && thangBD>thangKT) || (namBD==namKT && thangBD==thangKT && ngayBD>ngayKT)) {
                    throw new Exception("Sai thứ tự thời gian. Vui lòng nhập lại.\n");
                }

                double tongDoanhThu=0;
                int soluongHoaDon=0;
                double trungBinhTongDoanhThu=0;

                for(HoaDon i: dshd) {
                    String[] tmp3 = i.getNgayTaoHoaDon().split("-");
                    int ngay = Integer.parseInt(tmp3[0]);
                    int thang = Integer.parseInt(tmp3[1]);
                    int nam = Integer.parseInt(tmp3[2]);
                    if((nam>namBD || (nam==namBD && thang>thangBD) || (nam==namBD && thang==thangBD && ngay>=ngayBD)) 
                        && (nam<namKT || (nam==namKT && thang<thangKT) || (nam==namKT && thang==thangKT && ngay<=ngayKT))) {
                        tongDoanhThu+=i.getTongTien();
                        soluongHoaDon++;
                    }        
                }

                trungBinhTongDoanhThu = (soluongHoaDon>0) ? (tongDoanhThu/soluongHoaDon) : 0;

                System.out.println("\n- Tổng số lượng hóa đơn từ "+ngayStrBD+" đến "+ngayStrKT+": "+soluongHoaDon+" cái");
                System.out.println("- Tổng doanh thu từ "+ngayStrBD+" đến "+ngayStrKT+": "+tongDoanhThu+" VND");
                System.out.println("- Trung bình tổng doanh thu từ "+ngayStrBD+" đến ngày "+ngayStrKT+": "+trungBinhTongDoanhThu+" VND\n");
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: "+e.getMessage());
            }
        }
    }

    // liệt kê các hóa đơn có tổng tiền lớn và nhỏ hơn giá trị tổng tiền nhập
    public void lietKeHoaDonTongTienLonNhoHon(float tongTienNhap) {
        int countLonHon=0;
        int countNhoHon=0;

        // Liệt kê hóa đơn có tổng tiền lớn hơn giá trị nhập
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN LỚN HƠN " + tongTienNhap + " VND-------------+");
        for (HoaDon hoaDon : dshd) {
            if (hoaDon != null && hoaDon.getTongTien() > tongTienNhap) {
                hoaDon.xuatHoaDon();
                countLonHon++;
            }
        }
        if (countLonHon == 0) {
            System.out.println("Không có hóa đơn nào có tổng tiền lớn hơn " + tongTienNhap + " VND!");
        }
        System.out.println("+-----------------------------------------------------------------------------+");
        System.out.println("Có " + countLonHon + " hóa đơn với tổng tiền lớn hơn " + tongTienNhap + " VND\n");

        // Liệt kê hóa đơn có tổng tiền nhỏ hơn giá trị nhập
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN NHỎ HƠN " + tongTienNhap + " VND-------------+");
        for (HoaDon hoaDon : dshd) {
            if (hoaDon != null && hoaDon.getTongTien() < tongTienNhap) {
                hoaDon.xuatHoaDon();
                countNhoHon++;
            }
        }
        if (countNhoHon == 0) {
            System.out.println("Không có hóa đơn nào có tổng tiền nhỏ hơn " + tongTienNhap + " VND!");
        }
        System.out.println("+-----------------------------------------------------------------------------+");
        System.out.println("Có " + countNhoHon + " hóa đơn với tổng tiền nhỏ hơn " + tongTienNhap + " VND\n");
    }

    // Tìm và liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập
    public void lietKeHoaDonTongTienLonHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền lớn hơn "+tongTienNhap+"đ!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN LỚN HƠN "+tongTienNhap+"vnd-------------+");
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i].getTongTien() > tongTienNhap) {
                dshd[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn lớn hơn "+tongTienNhap+"vnd");
    }

    // Tìm và liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập
    public void lietKeHoaDonTongTienNhoHon(float tongTienNhap) {
        boolean found = false;
        int count=0;
        for(int i=0; i< dshd.length; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Không có hóa đơn nào có tổng tiền nhỏ hơn "+tongTienNhap+" VND!");
            return;
        }
        System.out.println("+-------------DANH SÁCH HÓA ĐƠN CÓ TỔNG TIỀN NHỎ HƠN "+tongTienNhap+" VND-------------+");
        for(int i=0; i<dshd.length; i++) {
            if(dshd[i].getTongTien() < tongTienNhap) {
                dshd[i].xuatHoaDon();
                count++;
            }
        }
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.println("Có "+count+" hóa đơn với tổng tiền mỗi hóa đơn nhỏ hơn "+tongTienNhap+" VND");
    }

    public void lietKeHoaDonTheoPhuongThucThanhToan() {
        Scanner sc =new Scanner(System.in);
        while (true) {
            System.out.println("Nhập phương thức thanh toán để liệt kê: ");
            System.out.println("1 - Tiền mặt");
            System.out.println("2 - Chuyển khoản");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            if(choice==1) {
                for(HoaDon i: dshd) {
                    if(i.getNgayTaoHoaDon().equals("Tiền mặt")) {
                        i.xuatHoaDon();
                        new DanhSachHoaDonChiTiet().xuatChiTietHoaDonTheoMHD(i.getMaHD());
                    }
                }
            } else if(choice==2) {
                for(HoaDon i: dshd) {
                    if(i.getNgayTaoHoaDon().equals("Chuyển khoản")) {
                        i.xuatHoaDon();
                        new DanhSachHoaDonChiTiet().xuatChiTietHoaDonTheoMHD(i.getMaHD());
                    }
                }
            } else {
                System.out.println("Nhập sai yêu cầu. Vui lòng nhập 1 hoặc 2!\n");
            }
        }
    }

    // Đọc file
    public void docFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("QuanLySieuThiMini/src/QuanLySieuThiMiNi/HoaDon/HoaDon.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                HoaDon tmp = new HoaDon();
                String[] strings = line.split(";");
                tmp.setMaHD(Integer.parseInt(strings[0].trim()));
                tmp.setMaKH(Integer.parseInt(strings[1].trim()));
                tmp.setMaNV(Integer.parseInt(strings[2].trim()));
                tmp.setNgayTaoHoaDon(strings[3].trim());
                tmp.setTongTien(Float.parseFloat(strings[4].trim()));
                tmp.setPhuongThucTinhToan(strings[5].trim());
                tmp.setTienTra(Float.parseFloat(strings[6].trim()));
                tmp.setTienThua();
                push(tmp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ghi file
    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("QuanLySieuThiMini/src/QuanLySieuThiMiNi/HoaDon/HoaDon.txt"));
            String line;
            for (HoaDon i: dshd) {
                line = String.format("%d;%d;%d;%s;%f;%s;%f;%f\n", i.getMaHD(), i.getMaKH(), i.getMaNV(), i.getNgayTaoHoaDon(), i.getTongTien(), i.getPhuongThucTinhToan(), i.getTienTra(), i.getTienThua());
                bw.write(line);
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capNhatFile() {
        
    }
}