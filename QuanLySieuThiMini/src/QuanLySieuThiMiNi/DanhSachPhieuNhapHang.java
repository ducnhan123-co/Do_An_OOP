package QuanLySieuThiMiNi;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhieuNhapHang {
    private PhieuNhapHang[] phieu = new PhieuNhapHang[0];
    private int n = 0;

    public void themPhieuNhapHang() {
        phieu = Arrays.copyOf(phieu, n + 1);
        phieu[n] = new PhieuNhapHang();
        phieu[n].nhapPhieu();
        n++;
    }

    
    public void xuatPhieuNhapHang() {
        if (n == 0) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.printf("|%-8s|%-15s|%-14s|%-15s|\n", "Mã Phiếu", "Mã Nhà Cung Cấp", "Ngày Nhập Hàng", "Tổng tiền");
        for (int i = 0; i < n; i++)
            phieu[i].xuatPhieu();
    }


    public int timGanDungTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            String maNCC = String.valueOf(phieu[i].getMaPhieu());
            if (maNCC.contains(ma))
                return i;
        }
        return -1;
    }
    
    public void xoaTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần xóa: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);

        if (vt != -1) {
            for (int i = vt; i < n - 1; i++)
                phieu[i] = phieu[i + 1];
            phieu = Arrays.copyOf(phieu, n - 1);
            n--;
            System.out.println("Đã xóa phiếu nhập hàng với mã: " + mp);
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
        }
    }


    public void timMP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần tìm: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);
        if (vt != -1)
            phieu[vt].xuatPhieu();
        else
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
    }

    public void suaPhieuNhapHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu cần sửa: ");
        String mp = sc.nextLine();
        int vt = timGanDungTheoMa(mp);

        if (vt != -1) {
            System.out.println("Đang sửa phiếu nhập hàng với mã: " + mp);


            System.out.print("Nhập mã nhà cung cấp mới (hiện tại: " + phieu[vt].getMaNCC() + "): ");
            int maNCC = sc.nextInt();
            phieu[vt].setMaNCC(maNCC);

     
            System.out.print("Nhập ngày nhập hàng mới (hiện tại: " + phieu[vt].getNgayNhapHang() + "): ");
            sc.nextLine(); 
            String ngayNhapStr = sc.nextLine();
            LocalDate ngayNhapHang = LocalDate.parse(ngayNhapStr);
            phieu[vt].setNgayNhapHang(ngayNhapHang);


            while (true) {
                System.out.print("Nhập mã sản phẩm (hoặc nhập -1 để kết thúc): ");
                int maSP = sc.nextInt();

                if (maSP == -1) {
                    break;
                }

                System.out.print("Nhập số lượng mới: ");
                int soLuong = sc.nextInt();

                System.out.print("Nhập đơn giá mới: ");
                double donGia = sc.nextDouble();

                phieu[vt].addOrUpdateProduct(maSP, soLuong, donGia);
            }

            phieu[vt].updateTongTien();
            System.out.println("Phiếu nhập hàng đã được cập nhật thành công với tổng tiền mới: " + phieu[vt].getTongTien());
        } else {
            System.out.println("Không tìm thấy phiếu nhập hàng với mã: " + mp);
        }
    }
}
