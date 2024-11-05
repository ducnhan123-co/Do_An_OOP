package QuanLySieuThiMiNi;

import java.util.Arrays;

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
        System.out.printf("|%-8s|%-15s|%-14s|%-15s|\n","Mã Phiếu","Mã Nhà Cung Cấp","Ngày Nhập Hàng","Tổng tiền");
        for (int i = 0; i < n; i++) 
            phieu[i].xuatPhieu(); 
    }
    
    
    
    
}
