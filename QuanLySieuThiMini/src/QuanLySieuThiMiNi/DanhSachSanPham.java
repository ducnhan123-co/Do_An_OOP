package QuanLySieuThiMiNi;

import static java.util.Arrays.copyOf;

public class DanhSachSanPham {
    private SanPham[] DS_SanPham = new SanPham[0];

    public void push(SanPham sanPham) {
        DS_SanPham = copyOf(DS_SanPham, DS_SanPham.length+1);
        DS_SanPham[DS_SanPham.length-1]=sanPham;
    }

    public void them() {
        SanPham sanPham = new SanPham();
        sanPham.nhap();
        push(sanPham);
    }

    public void sua (int maSP) {
        for (SanPham i: DS_SanPham)
            if (i.getMaSP() == maSP) {
                i.sua();
                break;
            }
    }

    public void xoa (int maSP) {
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

    public SanPham tim (int maSP) {
        for (SanPham i: DS_SanPham) {
            if (i.getMaSP() == maSP)
                return i;
        }
        return null;
    }
}
