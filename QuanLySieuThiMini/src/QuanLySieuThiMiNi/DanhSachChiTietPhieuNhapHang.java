package QuanLySieuThiMiNi;
import static java.util.Arrays.*;

public class DanhSachChiTietPhieuNhapHang {
	private ChiTietPhieuNhapHang[] DS = new ChiTietPhieuNhapHang[0];
	
	public void push(ChiTietPhieuNhapHang x) {
		DS = copyOf(DS, DS.length+1) ;
		DS[DS.length-1] = x;
	}
}
