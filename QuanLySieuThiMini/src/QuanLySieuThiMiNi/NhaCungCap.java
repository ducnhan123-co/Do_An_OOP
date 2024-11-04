package QuanLySieuThiMiNi;

import java.util.Scanner;

public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String sdtNCC;
    private String emailNCC;
    private String diaChiNCC;

    public NhaCungCap(int maNCC, String tenNCC, String sdtNCC, String emailNCC, String diaChiNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdtNCC = sdtNCC;
        this.emailNCC = emailNCC;
        this.diaChiNCC = diaChiNCC;
    }

    public NhaCungCap() {}
    public NhaCungCap(NhaCungCap other) {
        this.diaChiNCC = other.diaChiNCC;
        this.emailNCC = other.emailNCC;
        this.sdtNCC = other.sdtNCC;
        this.tenNCC = other.tenNCC;
        this.maNCC = other.maNCC;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSdtNCC() {
        return sdtNCC;
    }

    public void setSdtNCC(String sdtNCC) {
        this.sdtNCC = sdtNCC;
    }

    public String getEmailNCC() {
        return emailNCC;
    }

    public void setEmailNCC(String emailNCC) {
        this.emailNCC = emailNCC;
    }

    public String getDiaChiNCC() {
        return diaChiNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.diaChiNCC = diaChiNCC;
    }
    public void nhap() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("nhap ma nha cung cap");
                this.maNCC = in.nextInt();
                in.nextLine().trim();
                System.out.print("nhap ten nha cung cap");
                this.tenNCC = in.nextLine().trim();
                System.out.print("nhap so dien thoai nha cung cap");
                this.sdtNCC = in.nextLine().trim();
                System.out.print("nhap email nha cung cap");
                this.emailNCC = in.nextLine().trim();
                System.out.print("nhap dia chi nha cung cap");
                this.diaChiNCC = in.nextLine().trim();

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void sua() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("nhap ten nha cung cap");
                this.tenNCC = in.nextLine().trim();
                System.out.print("nhap so dien thoai nha cung cap");
                this.sdtNCC = in.nextLine().trim();
                System.out.print("nhap email nha cung cap");
                this.emailNCC = in.nextLine().trim();
                System.out.print("nhap dia chi nha cung cap");
                this.diaChiNCC = in.nextLine().trim();

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
