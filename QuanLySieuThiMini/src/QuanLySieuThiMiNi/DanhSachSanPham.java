package QuanLySieuThiMiNi;

import static java.util.Arrays.copyOf;

public class DanhSachSanPham {
    SanPham[] DS_SanPham = new SanPham[0];

    void push(SanPham sanPham) {
        DS_SanPham = copyOf(DS_SanPham, DS_SanPham.length+1);
        DS_SanPham[DS_SanPham.length-1]=sanPham;
    }

    void them() {
        SanPham sanPham = new SanPham();
        sanPham.nhap();
        push(sanPham);
    }

    void sua (int maSP) {
        for (SanPham i: DS_SanPham)
            if (i.getMaSP() == maSP) {
                i.sua();
                break;
            }
    }

    void xoa (int maSP) {
        for (int i = 0 ; i < DS_SanPham.length ; i++) {
            if (DS_SanPham[i].getMaSP() == maSP) {
                for (int j = i ; j < DS_SanPham.length-1 ; j++) {
                    DS_SanPham[j] = DS_SanPham[j+1];
                }
                DS_SanPham = copyOf(DS_SanPham, DS_SanPham.length-1);
                break;
            }
        }
    }
}
