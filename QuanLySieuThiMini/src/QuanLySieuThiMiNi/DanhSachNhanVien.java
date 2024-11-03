package QuanLySieuThiMiNi;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class DanhSachNhanVien {

    private NhanVien[] dsnv;
    private int size;


    public DanhSachNhanVien(int soluong) {
        dsnv = new NhanVien[soluong];
        size = 0;
    }
    public NhanVien[] getDsnv() {
        return dsnv;
    }

    public int getSize() {
        return size;
    }

    public void themNhanVien(NhanVien nhanVien) {
        if (nhanVien == null) {
            System.out.println("Nhân viên không hợp lệ. Vui lòng kiểm tra lại.");
            return; // Trả về nếu nhanVien là null
        }

        if (size >= dsnv.length) {
            // Mở rộng mảng nếu cần thiết
            NhanVien[] newDsnv = new NhanVien[dsnv.length * 2];
            for (int i = 0; i < dsnv.length; i++) {
                newDsnv[i] = dsnv[i];
            }
            dsnv = newDsnv; // Gán mảng mới vào dsnv
        }
        dsnv[size] = nhanVien; // Thêm nhân viên vào danh sách
        size++; // Tăng kích thước danh sách
        System.out.println("Thêm nhân viên thành công.");
    }
    public NhanVien themNhanVien2(NhanVien nv) {
        if (nv == null) {
            System.out.println("Nhân viên không hợp lệ. Vui lòng kiểm tra lại.");
            return null; // Trả về null nếu nhân viên không hợp lệ
        }

        if (size >= dsnv.length) {
            // Mở rộng mảng nếu cần thiết
            NhanVien[] newDsnv = new NhanVien[dsnv.length * 2];
            for (int i = 0; i < dsnv.length; i++) {
                newDsnv[i] = dsnv[i];
            }
            dsnv = newDsnv; // Gán mảng mới vào dsnv
        }
        dsnv[size] = nv; // Thêm nhân viên vào danh sách
        size++; // Tăng kích thước danh sách
        System.out.println("Thêm nhân viên thành công.");
        return nv; // Trả về đối tượng nhân viên đã thêm
    }

    public void xoaNhanVien() {
        System.out.println("Nhập mã nhân viên cần xóa:");
        Scanner scanner = new Scanner(System.in);
        int maNV = scanner.nextInt(); // Đọc mã nhân viên từ người dùng

        boolean found = false; // Biến để kiểm tra xem đã tìm thấy hay chưa
        for (int i = 0; i < size; i++) { // Duyệt qua danh sách nhân viên
            if (dsnv[i] != null && dsnv[i].getManv() == maNV) { // Kiểm tra mã nhân viên
                found = true; // Đánh dấu là đã tìm thấy
                // Di chuyển các phần tử phía sau lên một vị trí
                for (int j = i; j < size - 1; j++) {
                    dsnv[j] = dsnv[j + 1]; // Dịch chuyển phần tử
                }
                dsnv[size - 1] = null; // Đặt vị trí cuối thành null để tránh rò rỉ bộ nhớ
                size--; // Giảm kích thước danh sách
                System.out.println("Đã xóa nhân viên với mã " + maNV);
                break; // Ngắt vòng lặp sau khi đã xóa
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã " + maNV);
        }
    }



    public NhanVien xoaNhanVien2(String ma) {
        if (ma == null) {
            System.out.println("Mã nhân viên không hợp lệ.");
            return null; // Trả về null nếu mã không hợp lệ
        }

        boolean found = false; // Biến để kiểm tra xem đã tìm thấy hay chưa
        for (int i = 0; i < size; i++) { // Duyệt qua danh sách nhân viên
            if (dsnv[i] != null && dsnv[i].getManv()==parseInt(ma)) { // Kiểm tra mã nhân viên
                found = true; // Đánh dấu là đã tìm thấy
                NhanVien removed = dsnv[i]; // Lưu lại nhân viên bị xóa
                // Di chuyển các phần tử phía sau lên một vị trí
                for (int j = i; j < size - 1; j++) {
                    dsnv[j] = dsnv[j + 1]; // Dịch chuyển phần tử
                }
                dsnv[size - 1] = null; // Đặt vị trí cuối thành null để tránh rò rỉ bộ nhớ
                size--; // Giảm kích thước danh sách
                System.out.println("Đã xóa nhân viên với mã " + ma);
                return removed; // Trả về nhân viên đã xóa
            }
        }
        if (found==false) {
            System.out.println("Không tìm thấy nhân viên với mã " + ma);
        }
        return null; // Trả về null nếu không tìm thấy
    }



}
