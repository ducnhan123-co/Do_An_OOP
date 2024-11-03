package QuanLySieuThiMiNi;

import static java.util.Arrays.copyOf;

public class DanhSachKhachHang {
    KhachHang[] DS_KhachHang = new KhachHang[0];

    public void push(KhachHang khachHang) {
        DS_KhachHang = copyOf(DS_KhachHang, DS_KhachHang.length+1);
        DS_KhachHang[DS_KhachHang.length-1] = khachHang;
    }

    public void them() {
        KhachHang khachHang = new KhachHang();
        khachHang.nhap();
        push(khachHang);
    }

    public void sua(int maKH) {
        for (KhachHang i: DS_KhachHang) {
            if (i.getMaKH() == maKH) {
                i.sua();
                return ;
            }
        }
    }

    public void xoa(int maKH) {
        for (int i = 0 ; i < DS_KhachHang.length ; i++) {
            if (DS_KhachHang[i].getMaKH() == maKH) {
                for (int j = i ; j < DS_KhachHang.length-1 ; j++) {
                    DS_KhachHang[j] =DS_KhachHang[j+1];
                }
                DS_KhachHang= copyOf(DS_KhachHang, DS_KhachHang.length-1);
                return;
            }
        }
    }

}
