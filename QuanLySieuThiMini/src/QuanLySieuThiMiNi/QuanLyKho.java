package QuanLySieuThiMiNi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyKho implements Update_Kho, ThaoTacFile {
    Scanner sc = new Scanner(System.in);
    private DanhSachSanPham danhSachSanPham;

    public QuanLyKho() {
        this.danhSachSanPham = new DanhSachSanPham();
    }

    public QuanLyKho(DanhSachSanPham danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }
    
    @Override
    public void docFile() {
        danhSachSanPham.docFile();
    }

    @Override
    public void ghiFile() {
        danhSachSanPham.ghiFile();
    }

    @Override
    public void capNhatFile() {
        danhSachSanPham.ghiFile();
        System.out.println("Dữ liệu kho đã được cập nhật vào file!");
    }

    @Override
    public void truKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if (sp != null) {
            if (sp.getSoLuong() >= soLuong) {
                sp.setSoLuong(sp.getSoLuong() - soLuong);
                System.out.println("Đã trừ " + soLuong + " sản phẩm " + sp.getTenSP() + " khỏi kho!\n");
                danhSachSanPham.capNhatFile(); // Ghi lại dữ liệu vào file sau khi trừ kho
            } else {
                System.out.println("Không đủ hàng để trừ kho!");
            }
        } else {
            System.out.println("Không tìm thấy mã sản phẩm " + maSP + "!\n");
        }
    }

    @Override
    public void congKho(DanhSachSanPham danhSachSanPham, int maSP, int soLuong) {
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if (sp != null) {
            sp.setSoLuong(sp.getSoLuong() + soLuong);
            System.out.println("Đã cộng " + soLuong + " sản phẩm " + sp.getTenSP() + " vào kho!\n");
            danhSachSanPham.capNhatFile(); // Ghi lại dữ liệu vào file sau khi cộng kho
        } else {
            System.out.println("Không tìm thấy mã sản phẩm " + maSP + "!\n");
        }
    }

    // Chỉnh sửa đơn giá của sản phẩm theo mã sản phẩm
    public void changePrices(DanhSachSanPham danhSachSanPham, int maSP) {
        SanPham sp = danhSachSanPham.timSanPhamTheoMa(maSP);
        if(sp!=null) {
            while(true) {
                try {
                    System.out.print("Chỉnh sửa đơn giá của sản phẩm mã "+maSP+": ");
                    int donGiaNhap = Integer.parseInt(sc.nextLine());
                
                    if(donGiaNhap > 0) {
                        sp.setDonGia(donGiaNhap);
                        danhSachSanPham.capNhatFile();
                        System.out.println("Đã chỉnh sửa đơn giá của sản phẩm mã +"+maSP+" thành công!\n");
                        break;   
                    }
                    else {
                        System.out.println("Chỉnh sửa đơn giá âm không phù hợp. Vui lòng chỉnh sửa lại!\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Đơn giá không hợp lệ. Vui lòng chỉnh sửa lại.\n");
                }
            }
        }
        else {
            System.out.println("Không tìm thấy sản phẩm với mã " + maSP + "!\n");
        }
    }

    public void menu() {
        int choice = -1;
        docFile();
        System.out.println("Đã đọc dữ liệu từ file SanPham.txt");
        do {
            try {
                System.out.println("\n╔═══════════-QUẢN LÝ KHO-═══════════╗");
                System.out.println("║ 1. Trừ kho                        ║");
                System.out.println("║ 2. Cộng kho                       ║");
                System.out.println("║ 3. Chỉnh sửa giá theo mã sản phẩm ║");
                System.out.println("║ 4. Lấy dữ liệu từ file            ║");
                System.out.println("║ 5. Cập nhật lại dữ liệu           ║");
                System.out.println("║ 6. Thoát                          ║");
                System.out.println("╚═══════════════════════════════════╝");
                System.out.print("Nhập lựa chọn: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1: 
                        int maSPTru = -1;
                        int soLuongTru = -1;
                        while (true) {
                            try {
                                System.out.print("Nhập mã sản phẩm: ");
                                maSPTru = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Lỗi: Vui lòng nhập mã sản phẩm hợp lệ (số nguyên)!\n");
                            }
                        }
                        while (true) {
                            try {
                                System.out.print("Nhập số lượng cần trừ: ");
                                soLuongTru = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Lỗi: Vui lòng nhập số lượng hợp lệ (số nguyên)!\n");
                            }
                        }
                        truKho(danhSachSanPham, maSPTru, soLuongTru);
                        break;
                    case 2: 
                        int maSPCong = -1;
                        int soLuongCong = -1;
                        while (true) {
                            try {
                                System.out.print("Nhập mã sản phẩm: ");
                                maSPCong = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Lỗi: Vui lòng nhập mã sản phẩm hợp lệ (số nguyên)!\n");
                            }
                        }
                        while (true) {
                            try {
                                System.out.print("Nhập số lượng cần cộng: ");
                                soLuongCong = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Lỗi: Vui lòng nhập số lượng hợp lệ (số nguyên)!\n");
                            }
                        }
                        congKho(danhSachSanPham, maSPCong, soLuongCong);
                        break;
                    case 3:
                        int maSP = -1;
                        while (true) {
                            try {
                                System.out.print("Nhập mã sản phẩm: ");
                                maSP = Integer.parseInt(sc.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Lỗi: Vui lòng nhập mã sản phẩm hợp lệ (số nguyên)!\n");
                            }
                        }
                        changePrices(danhSachSanPham, maSP);
                        break;
                    case 4:
                        try {
                            docFile();
                            System.out.println("Dữ liệu đã được lấy thành công từ file SanPham.txt!\n");
                        } catch (Exception e) {
                            System.out.println("Lỗi khi đọc dữ liệu từ file: " + e.getMessage() + "\n");
                        }
                        break;
                    case 5: 
                        try {
                            capNhatFile();
                            System.out.println("Dữ liệu đã được cập nhật lại vào file thành công!\n");
                        } catch (Exception e) {
                            System.out.println("Lỗi khi cập nhật dữ liệu vào file: " + e.getMessage() + "\n");
                        }
                        break;
                    case 6: 
                        System.out.println("Thoát quản lý kho.\n");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!\n");
            }
        } while (choice != 6); 
    }

    public static void main(String[] args) {
        QuanLyKho qLyKho = new QuanLyKho();
        qLyKho.menu();
    }
}
