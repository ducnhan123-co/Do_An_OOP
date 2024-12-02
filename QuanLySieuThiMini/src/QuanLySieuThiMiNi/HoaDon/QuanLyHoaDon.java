package QuanLySieuThiMiNi.HoaDon;

import QuanLySieuThiMiNi.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyHoaDon {
    private DanhSachSanPham danhSachSanPham = new DanhSachSanPham();
    private DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();
    private DanhSachHoaDonChiTiet danhSachHoaDonChiTiet = new DanhSachHoaDonChiTiet();
    
    Scanner sc = new Scanner(System.in);

    // Hàm nhập hóa đơn
    public void nhapHoaDon() {
        HoaDon hoaDon = new HoaDon();
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Nhập mã hóa đơn: ");
                hoaDon.setMaHD(in.nextInt());
                if (!danhSachHoaDon.checkHD_datontai(hoaDon.getMaHD()))
                    throw new Exception("Trùng lặp mã hóa đơn");
                System.out.print("Nhập mã Khách hàng: ");
                hoaDon.setMaKH(in.nextInt());
                System.out.print("Nhập mã nhân viên: ");
                hoaDon.setMaNV(in.nextInt());
                LocalDate localDate = LocalDate.now();
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String b = localDate.format(f); // Định dạng ngày thành chuỗi  "dd-MM-yyyy"
                hoaDon.setNgayTaoHoaDon(b);
                float tongTien = 0;
                // Nhập chi tiết hóa đơn
                while(true) { 
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHD(hoaDon.getMaHD());
                    System.out.println("Nhập mã sản phẩm:");
                    chiTietHoaDon.setMaSP(in.nextInt());
                    System.out.println("Nhập số lượng:");
                    chiTietHoaDon.setSoLuong(in.nextInt());
                    chiTietHoaDon.setDonGia(danhSachSanPham.timSanPhamTheoMa(chiTietHoaDon.getMaSP()).getDonGia());
                    tongTien += chiTietHoaDon.tinhTien();
                    danhSachHoaDonChiTiet.push(chiTietHoaDon);
                    danhSachHoaDonChiTiet.ghiFile();
                    System.out.println("1 - Thanh toán");
                    System.out.println("2 - Tiếp tục nhập sản phẩm");
                    System.out.print("Chọn: ");
                    if (in.nextInt() == 1)
                        break;
                }
                hoaDon.setTongTien(tongTien);
                System.out.printf("Tổng tiền: %.2f\n", tongTien);
                // Chọn phương thức thanh toán 1 hoặc 2
                while (true) {
                    System.out.println("Nhập phương thức thanh toán:");
                    System.out.println("1 - Tiền mặt");
                    System.out.println("2 - Chuyển khoản");
                    System.out.print("Chọn: ");
                    int paymentChoice = in.nextInt();

                    if(paymentChoice==1) {
                        hoaDon.setPhuongThucTinhToan("Tiền mặt");
                        break;
                    }
                    else if(paymentChoice==2) {
                        hoaDon.setPhuongThucTinhToan("Chuyển khoản");
                        break;
                    }
                    else {
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn 1 hoặc 2.");
                    }
                }
                // Nhập tiền khách trả
                while (true) {
                    System.out.println("Nhập tiền khách trả: ");
                    float tmp = in.nextFloat();
                    if (tmp >= tongTien) {
                        hoaDon.setTienTra(tmp);
                        break;
                    }
                    System.out.println("Thiếu tiền. Vui lòng nhập số tiền cần thanh toán hợp lí!");
                }
    
                hoaDon.setTienThua();
                danhSachHoaDon.push(hoaDon);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally {
                
            }
        }
        System.out.println("═══════════════════════CỬA HÀNG MINIONS══════════════════════════");
        System.out.println("═════════════════════XIN CÁM ƠN QUÝ KHÁCH════════════════════════");
        xuatHoaDon_CTHD(hoaDon.getMaHD());
        danhSachHoaDon.ghiFile();
        danhSachHoaDonChiTiet.ghiFile();
    }

    // Hàm xuất hóa đơn theo mã
    public void xuatHoaDon_CTHD(int maHD) {
        if(danhSachHoaDon.timMaHoaDon(maHD)==-1) {
            System.out.println("Không có hóa đơn mã "+maHD+"!\n");
            return;
        }
        danhSachHoaDon.xuatHoaDonTheoMa(maHD);
        danhSachHoaDonChiTiet.xuatChiTietHoaDonTheoMHD(maHD);
        System.out.println();
    }

    // Hàm xuất danh sách hóa đơn
    public void xuatDSHoaDon() {
        danhSachHoaDon.xemDSHD();
    }

    // Hàm menu quản lý hóa đơn
    public void menu() {
        while (true) {
            try {
                while (true) {
                    System.out.println("╔═══════════════════════════-QUẢN LÝ HÓA ĐƠN-═══════════════════════════╗");
                    System.out.println("║ 1.  Thêm 1 hóa đơn vào danh sách và lưu trong file                    ║");
                    System.out.println("║ 2.  Thêm danh sách hóa đơn và lưu trong file                          ║");
                    System.out.println("║ 3.  Xóa hóa đơn theo mã hóa đơn và lưu trong file                     ║");
                    System.out.println("║ 4.  Tìm kiếm hóa đơn theo tùy chọn                                    ║");
                    System.out.println("║ 5.  Thống kê hóa đơn theo tùy chọn                                    ║");
                    System.out.println("║ 6.  Liệt kê hóa đơn theo tùy chọn                                     ║");
                    System.out.println("║ 7.  Xuất hóa đơn, chi tiết hóa đơn theo tùy chọn                      ║");
                    System.out.println("║ 8.  Xem danh sách hóa đơn hiện tại                                    ║");
                    System.out.println("║ 0.  Thoát                                                             ║");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                    System.out.print("Chọn chức năng: ");

                    int choice = sc.nextInt();
                    sc.nextLine(); // Bỏ qua dòng trống

                    switch(choice) {
                        case 1:
                            nhapHoaDon();
                            break;
                        case 2:
                            System.out.println("Nhập số lượng hóa đơn: ");
                            int n=sc.nextInt();
                            for(int i=0; i<n; i++){
                                nhapHoaDon();
                            }
                            break;
                        case 3:
                            System.out.print("Nhập mã hóa đơn cần xóa: ");
                            int maHD = sc.nextInt();
                            danhSachHoaDon.xoaHoaDonTheoMa(maHD);
                            danhSachHoaDon.ghiFile();
                            danhSachHoaDonChiTiet.xoaChiTietHoaDonTheoMaHoaDon(maHD);
                            danhSachHoaDonChiTiet.ghiFile();
                            break;
                        case 4:{
                            while (true) {
                                try {
                                    //Hiển thị menu tìm kiếm
                                    System.out.println("===================== TÌM KIẾM HÓA ĐƠN =====================");
                                    System.out.println("1. Tìm kiếm hóa đơn theo mã");
                                    System.out.println("2. Tìm kiếm hóa đơn theo tổng tiền");
                                    System.out.println("3. Tìm kiếm hóa đơn có tổng tiền lớn nhất");
                                    System.out.println("4. Tìm kiếm hóa đơn có tổng tiền nhỏ nhất");
                                    System.out.println("5. Tìm kiếm hóa đơn theo ngày tạo hóa đơn");
                                    System.out.println("0. Thoát");
                                    System.out.println("============================================================");
                                    System.out.print("Nhập lựa chọn của bạn: ");
                                    int luaChon = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                                    if(luaChon==0) {
                                        System.out.println("Thoát chức năng tìm kiếm hóa đơn.\n");
                                        break;
                                    }
                                    switch (luaChon) {
                                        case 1:
                                            System.out.print("Nhập mã hóa đơn cần tìm: ");
                                            int maTim = sc.nextInt();
                                            sc.nextLine();
                                            HoaDon hoaDonCanTim = danhSachHoaDon.timKiemHoaDon(maTim);
                                            if (hoaDonCanTim != null) {
                                                xuatHoaDon_CTHD(hoaDonCanTim.getMaHD());
                                            } else {
                                                System.out.println("Không tìm thấy hóa đơn có mã " + maTim + "!");
                                            }
                                            break;
                                        case 2:
                                            System.out.print("Nhập tổng tiền hóa đơn cần tìm: ");
                                            float tong = sc.nextFloat();
                                            HoaDon tongTienTim = danhSachHoaDon.timHoaDonTheoTongTien(tong);
                                            if (tongTienTim != null) {
                                                xuatHoaDon_CTHD(tongTienTim.getMaHD());
                                            } else {
                                                System.out.println("Không tìm thấy hóa đơn có tổng tiền là " + tong + " VND!");
                                            }
                                            break;
                                        case 3:
                                            HoaDon max = danhSachHoaDon.timHoaDonTongTienLonNhat();
                                            if(max!=null) {
                                                xuatHoaDon_CTHD(max.getMaHD());
                                            }
                                            else {
                                                System.out.println("Không tìm thấy hóa đơn có tổng tiền lớn nhất!\n");
                                            }
                                            break;
                                        case 4:
                                            HoaDon min = danhSachHoaDon.timHoaDonTongTienNhoNhat();
                                            if(min!=null) {
                                                xuatHoaDon_CTHD(min.getMaHD());
                                            }
                                            else {
                                                System.out.println("Không tìm thấy hóa đơn có tổng tiền nhỏ nhất!\n");
                                            }
                                            break;
                                        case 5:
                                            // Tìm hóa đơn theo ngày tạo
                                            HoaDon[] hoaDon = danhSachHoaDon.timHoaDonTheoNgayTaoHoaDon1();
                                            if(hoaDon != null && hoaDon.length>0) {
                                                for(HoaDon i: hoaDon) {
                                                    xuatHoaDon_CTHD(i.getMaHD());
                                                }
                                            }
                                            else {
                                                System.out.println("Không tìm thấy hóa đơn nào theo ngày đã nhập!\n");
                                            }
                                            break;
                                        default:
                                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                                    }
                                } catch (InputMismatchException e){
                                    System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 5!\n");
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                                } 
                            }
                            break;
                        }
                        case 5: {
                            while (true) {
                                try {
                                    //Hiển thị menu thống kê
                                    System.out.println("================ THỐNG KÊ HÓA ĐƠN ================");
                                    System.out.println("1. Thống kê tổng doanh thu");
                                    System.out.println("2. Thống kê số lượng hóa đơn");
                                    System.out.println("3. Thống kê hóa đơn theo ngày tháng năm");
                                    System.out.println("4. Thống kê hóa đơn theo tháng năm");
                                    System.out.println("5. Thống kê hóa đơn theo năm");
                                    System.out.println("6. Thống kê hóa đơn theo quý (Từng quý)");
                                    System.out.println("7. Thống kê hóa đơn theo khoảng thời gian");
                                    System.out.println("0. Thoát");
                                    System.out.println("==================================================");
                                    System.out.print("Nhập lựa chọn của bạn: ");
                                    int luaChon = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                                    if(luaChon==0) {
                                        System.out.println("Thoát chức năng thống kê.\n");
                                        break;
                                    }
                                    switch (luaChon) {
                                        case 1:
                                            danhSachHoaDon.tongDoanhThu();
                                            break;
                                        case 2:
                                            danhSachHoaDon.thongKeSoLuongHoaDon1();
                                            break;
                                        case 3:
                                            danhSachHoaDon.thongKeHoaDonTheoNgayThangNam();
                                            break;
                                        case 4:
                                            danhSachHoaDon.thongKeHoaDonTheoThangNam();
                                            break;
                                        case 5:
                                            danhSachHoaDon.thongKeHoaDonTheoNam();
                                            break;
                                        case 6:
                                            danhSachHoaDon.thongKeHoaDonTheoQuy();
                                            break;
                                        case 7:
                                            danhSachHoaDon.thongKeHoaDonTheoKhoangThoiGian();
                                            break;                               
                                        default:
                                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 7!\n");
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                                }
                            }
                            break;
                        }    
                        case 6: {
                            while (true) {
                                try {
                                    System.out.println("====================== LIỆT KÊ HÓA ĐƠN ======================");
                                    System.out.println("1. Liệt kê các hóa đơn có lớn và nhỏ hơn giá tổng tiền nhập");
                                    System.out.println("2. Liệt kê các hóa đơn theo phương thức thanh toán");
                                    System.out.println("0. Thoát");
                                    System.out.println("=============================================================");
                                    System.out.print("Nhập lựa chọn của bạn: ");
                                    int luaChon = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                                    if(luaChon==0) {
                                        System.out.println("Thoát chức năng liệt kê.\n");
                                        break;
                                    }
                                    switch (luaChon) {
                                        case 1:
                                            System.out.print("Nhập giá tiền: ");
                                            float tongTien = sc.nextFloat();
                                            danhSachHoaDon.lietKeHoaDonTongTienLonNhoHon(tongTien);
                                            break;
                                        case 2:
                                            danhSachHoaDon.lietKeHoaDonTheoPhuongThucThanhToan();
                                            break;
                                        default:
                                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 2!\n");
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Đã xảy ra lỗi: "+e.getMessage());    
                                }
                            }
                        }
                        case 7: {
                            while (true) {
                                try {
                                    //Hiển thị menu thống kê
                                    System.out.println("=================== XUẤT HÓA ĐƠN ===================");
                                    System.out.println("1. Xuất danh sách hóa đơn");
                                    System.out.println("2. Xuất hóa đơn theo mã hóa đơn");
                                    System.out.println("3. Xuất danh sách chi tiết hóa đơn");
                                    System.out.println("4. Xuất chi tiết hóa đơn theo mã hóa đơn");
                                    System.out.println("5. Xuất hóa đơn và chi tiết hóa đơn theo mã hóa đơn");
                                    System.out.println("0. Thoát");
                                    System.out.println("====================================================");
                                    System.out.print("Nhập lựa chọn của bạn: ");
                                    int luaChon = sc.nextInt();
                                    sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                                    if(luaChon==0) {
                                        System.out.println("Thoát chức năng xuất.\n");
                                        break;
                                    }
                                    switch (luaChon) {
                                        case 1:
                                            xuatDSHoaDon();
                                            break;
                                        case 2:
                                            System.out.print("Nhập mã hóa đơn: ");
                                            int MaHoaDon = sc.nextInt();
                                            System.out.println();
                                            danhSachHoaDon.xuatHoaDonTheoMa(MaHoaDon);
                                            break;
                                        case 3:
                                            danhSachHoaDonChiTiet.xuatDanhSachChiTietHoaDon();
                                            break;
                                        case 4:
                                            System.out.print("Nhập mã hóa đơn: ");
                                            int maCTHD = sc.nextInt();
                                            danhSachHoaDonChiTiet.xuatChiTietHoaDonTheoMHD(maCTHD);
                                            break; 
                                        case 5:
                                            System.out.print("Nhập mã hóa đơn: ");
                                            int maHD_CTHD= sc.nextInt();
                                            xuatHoaDon_CTHD(maHD_CTHD);
                                            break;
                                        default:
                                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Nhập sai lựa chọn. Vui lòng chọn lại từ 0 đến 5!\n");
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                                }
                            }
                            break;
                        }
                        case 8:
                            danhSachHoaDon.xemAll();
                            break;
                        case 0:
                            System.out.println("Thoát Menu Quản lý Hóa đơn...");
                            return;
                        default:
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    }
                }    
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai lựa chọn. Vui lòng chọn từ 0 đến 8.\n");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new DanhSachSanPham().docFile();
        new DanhSachHoaDon().docFile();
        new DanhSachHoaDonChiTiet().docFile();
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
        quanLyHoaDon.menu(); // Gọi menu quản lý hóa đơn
    }
}
// case 4: {
                    // while (true) {
                    //     try {
                    //         //Hiển thị menu xóa
                    //         System.out.println("============ XÓA HÓA ĐƠN ============");
                    //         System.out.println("1. Xóa hóa đơn từ cuối danh sách");
                    //         System.out.println("2. Xóa hóa đơn theo mã");
                    //         System.out.println("3. Xóa hóa đơn theo ngày tạo hóa đơn");
                    //         System.out.println("0. Thoát");
                    //         System.out.print("Nhập lựa chọn của bạn: ");
                    //         int luaChon = sc.nextInt();
                    //         sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                    //         if(luaChon==0) {
                    //             System.out.println("Thoát chức năng xóa hóa đơn.\n");
                    //             break;
                    //         }
                    //         switch (luaChon) {
                    //             case 1:
                    //                 danhSachHoaDon.xoaHoaDonCuoi();
                    //                 break;
                    //             case 2:
                    //                 System.out.print("Nhập mã hóa đơn cần xóa: ");
                    //                 int maHD = sc.nextInt();
                    //                 sc.nextLine();
                    //                 danhSachHoaDon.xoaHoaDonTheoMa(maHD);
                    //                 break;
                    //             case 3:
                    //                 System.out.print("Nhập ngày tạo hóa đơn cần xóa: ");
                    //                 String ngay = sc.nextLine();
                    //                 danhSachHoaDon.xoaHoaDonTheoNgay(ngay);
                    //                 break;
                    //             default:
                    //                 System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    //         }
                    //     } catch (Exception e) {
                    //         System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                    //     }
                    // }
                    // break;
                //}
