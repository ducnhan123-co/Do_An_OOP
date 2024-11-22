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
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã nhà cung cấp: ");
        this.maNCC = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập tên nhà cung cấp: ");
        this.tenNCC = sc.nextLine().trim();

        while (true) {
            try {
                System.out.print("Nhập số điện thoại nhà cung cấp: ");
                this.sdtNCC = sc.nextLine().trim();
                if (this.sdtNCC.length() != 10 || !this.sdtNCC.matches("\\d+")) {
                    throw new IllegalArgumentException("Số điện thoại phải gồm đúng 10 chữ số.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Nhập Email nhà cung cấp: ");
        this.emailNCC = sc.nextLine().trim();

        System.out.print("Nhập địa chỉ nhà cung cấp: ");
        this.diaChiNCC = sc.nextLine().trim();
    }


    
    public void xuat() {
        System.out.printf("|%-12d|%-20s|%-12s|%-25s|%-45s|\n", 
            maNCC, tenNCC, sdtNCC, emailNCC, diaChiNCC);
    }

    
}
