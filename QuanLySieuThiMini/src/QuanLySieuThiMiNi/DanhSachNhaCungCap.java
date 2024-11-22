package QuanLySieuThiMiNi;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachNhaCungCap implements ThaoTacFile{
    private NhaCungCap[] dsNCC = new NhaCungCap[0];
    private int n = 0;
    
    public void themNCC() {
        NhaCungCap ncc = new NhaCungCap();
        ncc.nhap();

        // Kiểm tra mã nhà cung cấp trùng lặp
        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getMaNCC() == ncc.getMaNCC()) {
                System.out.println("Mã nhà cung cấp bị trùng. Không thể thêm.\n");
                return; // Dừng nếu trùng
            }
        }

        // Thêm nhà cung cấp vào danh sách nếu không trùng
        dsNCC = Arrays.copyOf(dsNCC, n + 1);
        dsNCC[n] = ncc;
        n++;
        System.out.println("Thêm nhà cung cấp thành công!");
    }

    
    public void xuatNCC() {
        if (n == 0) {
            System.out.println("Danh sách rỗng");
            return;
        }

        // Tiêu đề bảng
        System.out.printf("|%-12s|%-20s|%-12s|%-25s|%-45s|\n",  
            "Mã NCC", "Tên NCC", "SĐT", "Email", "Địa chỉ");
        System.out.println(new String(new char[120]).replace("\0", "-")); // Đường kẻ ngang

        // Xuất từng nhà cung cấp
        for (int i = 0; i < n; i++) {
            dsNCC[i].xuat();
        }
    }
    
    public void suaNCC() {
        if (n == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp cần sửa: ");
        int maCanSua = sc.nextInt();
        sc.nextLine();

        // Tìm nhà cung cấp cần sửa
        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getMaNCC() == maCanSua) {
                System.out.println("Thông tin hiện tại của nhà cung cấp:");
                dsNCC[i].xuat();

                // Nhập thông tin mới
                System.out.println("Nhập thông tin mới:");
                dsNCC[i].nhap();
                return;
            }
            System.out.println("Sửa thông tin mã nhà cung cấp "+ maCanSua +" thành công");
        }

        // Nếu không tìm thấy
        System.out.println("Không tìm thấy nhà cung cấp có mã: " + maCanSua);
    }
    
    public void xoaNCC() {
    	if (n == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp cần xóa: ");
        int maNCC = sc.nextInt();
        
        // Tìm vị trí của nhà cung cấp cần xóa
        int vt = -1;
        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getMaNCC() == maNCC) {
                vt = i;
                break;
            }
        }

        if (vt == -1) {
            System.out.println("Không tìm thấy nhà cung cấp với mã: " + maNCC);
            return;
        }

        // Xóa nhà cung cấp khỏi mảng
        for (int i = vt; i < n - 1; i++) {
            dsNCC[i] = dsNCC[i + 1]; // Dịch chuyển phần tử về trước
        }

        // Gán phần tử cuối cùng là null (dù không cần thiết nhưng để tránh lỗi)
        dsNCC[n - 1] = null;
        n--; // Giảm số lượng nhà cung cấp trong danh sách
        System.out.println("Đã xóa nhà cung cấp với mã: " + maNCC);
    }

    
    
    public void timTenNCCGanDung() {
        if (n == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên nhà cung cấp cần tìm : ");
        String tenCanTim = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.printf("|%-12s|%-20s|%-12s|%-25s|%-45s|\n", 
            "Mã NCC", "Tên NCC", "SĐT", "Email", "Địa chỉ");
        System.out.println(new String(new char[120]).replace("\0", "-"));

        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getTenNCC().toLowerCase().contains(tenCanTim)) {
                dsNCC[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy nhà cung cấp có tên gần đúng với: " + tenCanTim);
        }
    }
    
    public NhaCungCap parseLineToNhaCungCap(String line) {
        String[] parts = line.split(";");
        if (parts.length == 5) { 
            try {
                int maNCC = Integer.parseInt(parts[0].trim()); 
                String tenNCC = parts[1].trim(); 
                String sdtNCC = parts[2].trim(); 
                String emailNCC = parts[3].trim(); 
                String diaChiNCC = parts[4].trim(); 

                return new NhaCungCap(maNCC, tenNCC, sdtNCC, emailNCC, diaChiNCC);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng số trong dòng: " + line);
            }
        } else {
            System.out.println("Số lượng cột không khớp trong dòng: " + line);
        }
        return null;
    }

    

    @Override
    public void docFile() {
        String filename = "src\\QuanLySieuThiMiNi\\NhaCungCap.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                NhaCungCap nhaCungCap = parseLineToNhaCungCap(line);
                if (nhaCungCap != null) {
                    // Kiểm tra nếu mảng đã đầy, thì tăng kích thước mảng
                    if (n >= dsNCC.length) {
                        // Tăng kích thước mảng gấp đôi khi mảng đầy
                        dsNCC = Arrays.copyOf(dsNCC, dsNCC.length + 10);
                    }
                    dsNCC[n++] = nhaCungCap;
                    count++;
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
            System.out.println("Đã thêm " + count + " nhà cung cấp từ tệp tin: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin: " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void ghiFile() {
        String filename = "src\\QuanLySieuThiMiNi\\NhaCungCap.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                NhaCungCap nhaCungCap = dsNCC[i];
                writer.write(nhaCungCap.getMaNCC() + ";" +
                             nhaCungCap.getTenNCC() + ";" +
                             nhaCungCap.getSdtNCC() + ";" +
                             nhaCungCap.getEmailNCC() + ";" +
                             nhaCungCap.getDiaChiNCC());
                writer.newLine();
            }
            System.out.println("Đã ghi file thành công.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



	@Override
	public void capNhatFile() {
		ghiFile();
		
	}

    
    
}
