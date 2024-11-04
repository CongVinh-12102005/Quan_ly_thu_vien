import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookManager bookManager = new BookManager(); // Khởi tạo BookManager
        int choice;

        do {
            System.out.println("============= MENU QUAN LY THU VIEN =============");
            System.out.println("1. Quan ly sach");
            System.out.println("2. Quan ly khach hang");
            System.out.println("3. Quan ly nha cung cap");
            System.out.println("4. Quan ly lich su muon/tra");
            System.out.println("5. Thoat");

            try {
                System.out.print("Chon chuc nang (1-5): ");
                choice = sc.nextInt();
                sc.nextLine();  // Xóa bộ đệm dòng
            } catch (InputMismatchException e) {
                System.out.println("Vui long nhap so hop le!");
                sc.nextLine();  // Xóa bộ đệm để tránh lỗi lặp lại
                choice = 0;     // Đặt giá trị mặc định để tiếp tục vòng lặp
            }

            switch (choice) {
                case 1:
                    System.out.println("Ban da chon quan ly sach.");
                    bookManager.run(); // Gọi phương thức chạy menu sách
                    break;
                case 2:
                    System.out.println("Ban da chon quan ly khach hang. Chuc nang nay chua duoc trien khai.");
                    break;
                case 3:
                    System.out.println("Ban da chon quan ly nha cung cap. Chuc nang nay chua duoc trien khai.");
                    break;
                case 4:
                    System.out.println("Ban da chon quan ly lich su muon/tra. Chuc nang nay chua duoc trien khai.");
                    break;
                case 5:
                    System.out.println("Da thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        } while (choice != 5);
        
        sc.close(); 
    }
}
