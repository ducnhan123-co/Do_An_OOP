package QuanLySieuThiMiNi;

import com.sun.source.tree.EnhancedForLoopTree;

import static java.util.Arrays.copyOf;

public class DanhSachNhaCungCap {
    NhaCungCap[] DS_NhaCungCap = new NhaCungCap[0];
    public void push(NhaCungCap nhaCungCap) {
        DS_NhaCungCap = copyOf(DS_NhaCungCap, DS_NhaCungCap.length+1);
        DS_NhaCungCap[DS_NhaCungCap.length-1] = nhaCungCap;
    }

    public void them() {
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.nhap();
        push(nhaCungCap);
    }

    public void sua(int maNCC) {
        for (NhaCungCap i: DS_NhaCungCap) {
            if (i.getMaNCC() == maNCC) {
                i.sua();
            }
        }
    }
    public void xoa(int maNCC) {
        for (int i = 0 ; i < DS_NhaCungCap.length ; i++) {
            if (DS_NhaCungCap[i].getMaNCC() == maNCC) {
                for (int j = i ; j < DS_NhaCungCap.length-1 ; j++) {
                    DS_NhaCungCap[j] = DS_NhaCungCap[j+1];
                }
                DS_NhaCungCap = copyOf(DS_NhaCungCap, DS_NhaCungCap.length-1);
                return;
            }
        }
    }
}
