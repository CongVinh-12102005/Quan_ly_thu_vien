import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books; // Sử dụng List để quản lý sách
    private Scanner sc;
    private final String filePath = "book.txt"; // Đường dẫn đến file lưu trữ sách

    public BookManager() {
        books = new ArrayList<>();
        sc = new Scanner(System.in);
        docSachTuFile(); // Đọc sách từ file khi khởi tạo
    }

    // Phương thức đọc sách từ file
    private void docSachTuFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String maSach = data[0];
                String tenSach = data[1];
                String tacGia = data[2];
                String loaiSach = data[3];
                String nhaXuatBan = data[4];
                String nhaCungCap = data[5];
                int soLuong = Integer.parseInt(data[6]);

                if (loaiSach.equals("SGK")) {
                    String soLop = data[7];
                    books.add(new SGK(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, soLop));
                } else if (loaiSach.equals("Truyen")) {
                    String theLoai = data[7];
                    books.add(new Truyen(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, theLoai));
                } else if (loaiSach.equals("GiaoTrinh")) {
                    String monHoc = data[7];
                    books.add(new GiaoTrinh(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, monHoc));
                }
            }
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

    // Phương thức ghi sách vào file (ghi đè)
    private void ghiSachVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                String line = book.getMaSach() + "," +
                        book.getTenSach() + "," +
                        book.getTacGia() + "," +
                        book.getLoaiSach() + "," +
                        book.getNhaXuatBan() + "," +
                        book.getNhaCungCap() + "," +
                        book.getSoLuong();

                if (book instanceof SGK) {
                    line += "," + ((SGK) book).getSoLop();
                } else if (book instanceof Truyen) {
                    line += "," + ((Truyen) book).getTheLoai();
                } else if (book instanceof GiaoTrinh) {
                    line += "," + ((GiaoTrinh) book).getMonHoc();
                }

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    // Phương thức để thêm sách
    public void themSach() {
        System.out.println("Chon loai sach:");
        System.out.println("1. Sach giao khoa (SGK)");
        System.out.println("2. Truyen");
        System.out.println("3. Giao trinh");
        
        int choice = sc.nextInt();
        sc.nextLine(); // Đọc bỏ dòng xuống hàng

        String maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLop = null, monHoc = null, theLoai = null;
        int soLuong;

        System.out.print("Nhap ma sach: ");
        maSach = sc.nextLine();
        System.out.print("Nhap ten sach: ");
        tenSach = sc.nextLine();
        System.out.print("Nhap tac gia: ");
        tacGia = sc.nextLine();
        System.out.print("Nhap loai sach: ");
        loaiSach = sc.nextLine();
        System.out.print("Nhap nha xuat ban: ");
        nhaXuatBan = sc.nextLine();
        System.out.print("Nhap nha cung cap: ");
        nhaCungCap = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        sc.nextLine(); // Đọc bỏ dòng xuống hàng

        switch (choice) {
            case 1:
                System.out.print("Nhap so lop: ");
                soLop = sc.nextLine();
                books.add(new SGK(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, soLop));
                break;
            case 2:
                System.out.print("Nhap the loai: ");
                theLoai = sc.nextLine();
                books.add(new Truyen(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, theLoai));
                break;
            case 3:
                System.out.print("Nhap mon hoc: ");
                monHoc = sc.nextLine();
                books.add(new GiaoTrinh(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, monHoc));
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }
        System.out.println("Da them sach thanh cong!");
        ghiSachVaoFile(); // Ghi sách vào file sau khi thêm
    }

    // Phương thức để sửa sách
    public void suaSach() {
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine();
        for (Book book : books) {
            if (book.getMaSach().equals(maSach)) {
                System.out.println("Nhap lai thong tin cho sach:");
                book.nhap(); // Giả sử rằng phương thức nhap() sẽ cập nhật thông tin sách
                System.out.println("Da cap nhat thong tin sach.");
                ghiSachVaoFile(); // Ghi vào file sau khi sửa
                return;
            }
        }
        System.out.println("Khong tim thay sach voi ma " + maSach);
    }

    // Phương thức để xóa sách
    public void xoaSach() {
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getMaSach().equals(maSach)) {
                books.remove(i);
                System.out.println("Da xoa sach voi ma " + maSach);
                ghiSachVaoFile(); // Ghi vào file sau khi xóa
                return;
            }
        }
        System.out.println("Khong tim thay sach voi ma " + maSach);
    }

    // Phương thức để tìm kiếm sách
    public void timKiemSach() {
        System.out.print("Nhap tu khoa tim kiem (ma sach hoac ten sach): ");
        String search = sc.nextLine();
        for (Book book : books) {
            if (book.getMaSach().equalsIgnoreCase(search) || book.getTenSach().equalsIgnoreCase(search)) {
                book.xuat(); // Hiển thị thông tin sách
                return;
            }
        }
        System.out.println("Khong tim thay sach voi tu khoa: " + search);
    }

    // Phương thức để hiển thị danh sách sách
    public void hienThiDanhSach() {
        if (books.isEmpty()) {  
            System.out.println("Danh sach sach rong!");
            return;
        }

        System.out.println("============= DANH SACH SACH =============");
        for (Book book : books) {
            book.xuat(); // Giả sử rằng phương thức xuat() sẽ in ra thông tin sách
        }
    }

    // Phương thức để chạy menu quản lý sách
    public void run() {
        int choice;
        do {
            System.out.println("============= MENU QUAN LY SACH =============");
            System.out.println("1. Them sach");
            System.out.println("2. Sua sach");
            System.out.println("3. Xoa sach");
            System.out.println("4. Tim kiem sach");
            System.out.println("5. Hien thi danh sach sach"); // Thêm tùy chọn mới
            System.out.println("6. Quay lai");
        
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng xuống hàng

            switch (choice) {
                case 1:
                    themSach();
                    break;
                case 2:
                    suaSach();
                    break;
                case 3:
                    xoaSach();
                    break;
                case 4:
                    timKiemSach();
                    break;
                case 5:
                    hienThiDanhSach(); // Gọi phương thức hiển thị danh sách
                    break;
                case 6:
                    System.out.println("Quay lai menu chinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        } while (choice != 6);
    }

}
