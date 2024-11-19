package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Scanner;

public class QuanLyHoaDon {
    private DanhSachSanPham danhSachSanPham = new DanhSachSanPham();
    private DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();
    private DanhSachHoaDonChiTiet danhSachHoaDonChiTiet = new DanhSachHoaDonChiTiet();
    
    public QuanLyHoaDon() {
        danhSachSanPham.docFile();
        danhSachHoaDon.docFile();
        danhSachHoaDonChiTiet.docFile();
    }
    
    Scanner sc = new Scanner(System.in);

    // Hàm nhập hóa đơn
    public void nhapHoaDon() {
        HoaDon hoaDon = new HoaDon();
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Nhập mã hóa đơn: ");
                hoaDon.setMaHD(in.nextInt());
                if (!danhSachHoaDon.checkHD(hoaDon.getMaHD()))
                    throw new Exception("Trùng lặp mã hóa đơn");
                System.out.print("Nhập mã Khách hàng: ");
                hoaDon.setMaKH(in.nextInt());
                System.out.print("Nhập mã nhân viên: ");
                hoaDon.setMaNV(in.nextInt());
                LocalDate localDate = LocalDate.now();
                String b = String.format("%d-%d-%d", localDate.getDayOfMonth(), localDate.getMonth().getValue(), localDate.getYear());
                hoaDon.setNgayTaoHoaDon(b);
                float TongTien = 0;
                while(true) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHD(hoaDon.getMaHD());
                    System.out.println("Nhập mã sản phẩm:");
                    chiTietHoaDon.setMaSP(in.nextInt());
                    System.out.println("Nhập số lượng:");
                    chiTietHoaDon.setSoLuong(in.nextInt());
                    chiTietHoaDon.setDonGia(danhSachSanPham.timDonGiaTheoMa(chiTietHoaDon.getMaSP()));
                    TongTien += chiTietHoaDon.tinhTien();
                    danhSachHoaDonChiTiet.push(chiTietHoaDon);
                    System.out.println("1 - Thanh toán");
                    System.out.println("2 - Tiếp tục nhập sản phẩm");
                    if (in.nextInt() == 1)
                        break;
                }
                hoaDon.setTongTien(TongTien);
                System.out.printf("Tổng tiền: %.2f\n", TongTien);
                // Chọn phương thức thanh toán 1 hoặc 2
                while (true) {
                    System.out.println("Nhập phương thức thanh toán:");
                    System.out.println("1 - Tiền mặt");
                    System.out.println("2 - Chuyển khoản");
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
                while (true) {
                    System.out.println("Nhập tiền khách trả: ");
                    float tmp = in.nextFloat();
                    if (tmp >= TongTien) {
                        hoaDon.setTienTra(tmp);
                        break;
                    }
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
        xuatHoaDon(hoaDon.getMaHD());
        new Scanner(System.in).nextLine();
        danhSachHoaDon.ghiFile();
        danhSachHoaDonChiTiet.ghiFile();
    }

    // Hàm xuất hóa đơn theo mã
    public void xuatHoaDon(int maHD) {
        danhSachHoaDon.xuatHoaDonTheoMa(maHD);
        danhSachHoaDonChiTiet.xuatChiTietHoaDonTheoMHD(maHD);
    }

    // Hàm xuất danh sách hóa đơn
    public void xuatDSHoaDon() {
        danhSachHoaDon.xemDS();
    }

    // Hàm menu quản lý hóa đơn
    public void menuDSHD() {
        while (true) {
            System.out.println("╔═════════════════════════════-QUẢN LÝ HÓA ĐƠN-═════════════════════════╗");
            System.out.println("║1.  Thêm 1 hóa đơn vào danh sách và lưu trong file                     ║");
            System.out.println("║2.  Thêm danh sách hóa đơn và lưu trong file                           ║");
            System.out.println("║3.  Xóa hóa đơn theo mã hóa đơn                                        ║");
            System.out.println("║4.  Tìm kiếm hóa đơn theo tùy chọn                          (done 3/3) ║");
            System.out.println("║5.  Thống kê hóa đơn theo tùy chọn                          (done 4/6) ║");
            System.out.println("║6.  Liệt kê các hóa đơn có tổng tiền lớn hơn giá trị nhập              ║");
            System.out.println("║7.  Liệt kê các hóa đơn có tổng tiền nhỏ hơn giá trị nhập              ║");
            System.out.println("║8.  Xuất hóa đơn, chi tiết hóa đơn theo tùy chọn                       ║");
            System.out.println("║9.  Xem danh sách hóa đơn hiện tại                                     ║");
            System.out.println("║0.  Thoát                                                              ║");
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
                    System.out.println("Nhập mã hóa đơn cần xóa: ");
                    int maHD = sc.nextInt();
                    danhSachHoaDon.xoaHoaDonTheoMa(maHD);
                    break;
                case 4:{
                    while (true) {
                        try {
                            //Hiển thị menu tìm kiếm
                            System.out.println("============ TÌM KIẾM HÓA ĐƠN ============");
                            System.out.println("1. Tìm kiếm hóa đơn theo mã");
                            System.out.println("2. Tìm kiếm hóa đơn theo tổng tiền");
                            System.out.println("3. Tìm kiếm hóa đơn có tổng tiền lớn nhất");
                            System.out.println("4. Tìm kiếm hóa đơn theo ngày tạo hóa đơn");
                            System.out.println("5. Tìm kiếm các hóa đơn có tổng tiền lớn hơn giá trị nhập");
                            System.out.println("6. Tìm kiếm các hóa đơn có tổng tiền nhỏ hơn giá trị nhập");
                            System.out.println("0. Thoát");
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
                                        hoaDonCanTim.xuatHoaDon();
                                    } else {
                                        System.out.println("Không tìm thấy hóa đơn có mã " + maTim + "!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Nhập tổng tiền hóa đơn cần tìm: ");
                                    float tong = sc.nextFloat();
                                    HoaDon tongTienTim = danhSachHoaDon.timHoaDonTheoTongTien(tong);
                                    if (tongTienTim != null) {
                                        tongTienTim.xuatHoaDon();
                                    } else {
                                        System.out.println("Không tìm thấy hóa đơn có tổng tiền là " + tongTienTim + "vnd!");
                                    }
                                    break;
                                case 3:
                                    
                                    break;
                                case 4:
                                    // Tìm hóa đơn theo ngày tạo
                                    danhSachHoaDon.timHoaDonTheoNgayTaoHoaDon();
                                    break;
                                case 5:
                                    System.out.println("Nhập giá tiền: ");
                                    float tongTien1 = sc.nextFloat();
                                    danhSachHoaDon.lietKeHoaDonTongTienLonHon(tongTien1);
                                    break;
                                case 6:
                                    System.out.println("Nhập giá tiền: ");
                                    float tongTien2 = sc.nextFloat();
                                    danhSachHoaDon.lietKeHoaDonTongTienNhoHon(tongTien2);
                                    break;
                                default:
                                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                        }
                    }
                    break;
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
                            System.out.println("0. Thoát");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                            if(luaChon==0) {
                                System.out.println("Thoát chức năng thống kê.\n");
                                break;
                            }
                            switch (luaChon) {
                                case 1:
                                    danhSachHoaDon.thongKeTongDoanhThu();
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
                                    danhSachHoaDon.thongKeHoaDonTheoNam(); // OK
                                case 6:
                                    danhSachHoaDon.thongKeHoaDonTheoQuy();
                                    break;
                                case 7:

                                case 8:

                                default:
                                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                        }
                    }
                    break;
                }
                    
                case 6:
                    
                    break;
                case 7:

                break;
                case 8: {
                    while (true) {
                        try {
                            //Hiển thị menu thống kê
                            System.out.println("================ XUẤT HÓA ĐƠN ================");
                            System.out.println("1. Xuất hóa đơn theo mã hóa đơn");
                            System.out.println("2. Xuất các chi tiết hóa đơn");
                            System.out.println("3. Xuất chi tiết hóa đơn theo mã hóa đơn");
                            System.out.println("0. Thoát");
                            System.out.print("Nhập lựa chọn của bạn: ");
                            int luaChon = sc.nextInt();
                            sc.nextLine(); // Đọc ký tự xuống dòng còn sót lại sau khi nhập số

                            if(luaChon==0) {
                                System.out.println("Thoát chức năng xuất.\n");
                                break;
                            }
                            switch (luaChon) {
                                case 1:
                                    System.out.println("Nhập mã hóa đơn để xuất: ");
                                    int maCTHD = sc.nextInt();
                                    danhSachHoaDon.xuatHoaDonTheoMa(maCTHD);
                                    break;
                                case 2:
                                    danhSachHoaDonChiTiet.xuatDanhSachChiTietHoaDon();
                                    break;
                                case 3:
                                    System.out.println("Nhập mã hóa đơn: ");
                                    int maCTHD1 = sc.nextInt();
                                    danhSachHoaDonChiTiet.xuatChiTietHoaDonTheoMHD(maCTHD1);
                                    break; 
                                default:
                                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            }
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: "+e.getMessage());
                        }
                    }
                    break;
                }
                case 9:
                    danhSachHoaDon.xemDS();;
                    break;
                case 0:
                    System.out.println("Thoát Menu Quản lý Hóa đơn...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public static void main(String[] args) {
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
        quanLyHoaDon.menuDSHD(); // Gọi menu quản lý hóa đơn
    }
}
