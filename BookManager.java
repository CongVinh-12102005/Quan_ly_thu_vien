import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books; 
    private Scanner sc;
    private final String filePath = "book.txt"; 

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
                String maSach = data[0].trim();
                String tenSach = data[1].trim();
                String tacGia = data[2].trim();
                String loaiSach = data[3].trim();
                String nhaXuatBan = data[4].trim();
                String nhaCungCap = data[5].trim();
                int soLuong = Integer.parseInt(data[6].trim());

                if (loaiSach.equals("SGK")) {
                    String soLop = data[7].trim();
                    books.add(new SGK(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, soLop));
                } else if (loaiSach.equals("Truyen")) {
                    String theLoai = data[7].trim();
                    books.add(new Truyen(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong, theLoai));
                } else if (loaiSach.equals("GiaoTrinh")) {
                    String monHoc = data[7].trim();
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
        System.out.print("Chon loai sach:");
        int choice = sc.nextInt();
        sc.nextLine(); // Đọc bỏ dòng xuống hàng
    
        String loaiSach = null; 
    
        switch (choice) {
            case 1:
                loaiSach = "SGK"; // Gán giá trị cho loaiSach là "SGK"
                break;
            case 2:
                loaiSach = "Truyen"; // Gán giá trị cho loaiSach là "Truyen"
                break;
            case 3:
                loaiSach = "GiaoTrinh"; // Gán giá trị cho loaiSach là "GiaoTrinh"
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }
    
        // Tạo một đối tượng mới dựa trên loại sách đã chọn
        Book newBook = null;
    
        switch (choice) {
            case 1:
                newBook = new SGK(); // Tạo mới sách giáo khoa
                break;
            case 2:
                newBook = new Truyen(); // Tạo mới truyện
                break;
            case 3:
                newBook = new GiaoTrinh(); // Tạo mới giáo trình
                break;
        }
    
        newBook.nhap(); 
        newBook.setLoaiSach(loaiSach); // Gán giá trị cho loaiSach của sách vừa tạo
        books.add(newBook); 
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
                book.nhap(); 
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
                book.xuat(); 
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
        else{
            System.out.println("============= DANH SACH SACH =============");
            System.out.println("Dac tinh: SGK(soLop); Truyen(theLoai); GiaoTrinh(monHoc)");
            System.out.format("%-10s %-20s %-15s %-10s %-15s %-15s %-10s %-15s %-10s\n",
"Ma sach","Ten sach","Tac gia","Loai sach","Nha xuat ban","Nha cung cap","So luong","Dac tinh","Tinh trang");
            for (Book book : books) {
                book.xuat(); 
                System.out.println("");
            }
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
            System.out.println("5. Hien thi danh sach sach"); 
            System.out.println("6. Quay lai");
            System.out.print("Chon chuc nang:");
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
                    hienThiDanhSach(); 
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
