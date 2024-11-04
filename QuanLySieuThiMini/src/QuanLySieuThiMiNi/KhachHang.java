package QuanLySieuThiMiNi;

import java.time.Month;
import java.util.Date;
import java.util.Scanner;

public class KhachHang {
    private int maKH;
    private String hoKH;
    private String tenKH;
    private Date ngaySinh = new Date();
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private float diem;

    public KhachHang() {}
    public KhachHang(int maKH, String hoKH, String tenKH, Date ngaySinh, String gioiTinh, String sdt, String diaChi) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }
    public KhachHang(KhachHang other) {
        this.maKH = other.maKH;
        this.hoKH = other.hoKH;
        this.tenKH = other.tenKH;
        this.ngaySinh = other.ngaySinh;
        this.gioiTinh = other.gioiTinh;
        this.sdt = other.sdt;
        this.diaChi = other.diaChi;
        this.diem = other.diem;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public void nhap() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("nhap ma khach hang: ");
                this.maKH = in.nextInt();
                System.out.print("nhap ho khach hang: ");
                in.nextLine();
                this.hoKH = in.nextLine().trim();
                System.out.print("nhap ten khach hang: ");
                this.tenKH = in.nextLine().trim();
                System.out.println("nhap ngay sinh khach hang: ");
                System.out.print("nhap ngay: ");
                this.ngaySinh.setDate(in.nextInt());
                System.out.print("nhap thang: ");
                this.ngaySinh.setMonth(in.nextInt());
                System.out.print("nhap nam: ");
                this.ngaySinh.setYear(in.nextInt());
                System.out.print("nhap gioi tinh khach hang: ");
                in.nextLine();
                this.gioiTinh = in.nextLine().trim();
                System.out.print("nhap sdt khach hang: ");
                this.sdt = in.nextLine().trim();
                System.out.print("nhap dia chi khach hang: ");
                this.diaChi = in.nextLine().trim();

                if (sdt.length() != 10)
                    throw new Exception("so dien thoai khong hop le");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sua () {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("nhap ho khach hang: ");
                this.hoKH = in.nextLine().trim();
                System.out.print("nhap ten khach hang: ");
                this.tenKH = in.nextLine().trim();
                System.out.println("nhap ngay sinh khach hang: ");
                System.out.print("nhap ngay: ");
                this.ngaySinh.setDate(in.nextInt());
                System.out.print("nhap thang: ");
                this.ngaySinh.setMonth(in.nextInt());
                System.out.print("nhap nam: ");
                this.ngaySinh.setYear(in.nextInt());
                System.out.print("nhap gioi tinh khach hang: ");
                in.nextLine();
                this.gioiTinh = in.nextLine().trim();
                System.out.print("nhap sdt khach hang: ");
                this.sdt = in.nextLine().trim();
                System.out.print("nhap dia chi khach hang: ");
                this.diaChi = in.nextLine().trim();

                if (sdt.length() != 10)
                    throw new Exception("so dien thoai khong hop le");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
