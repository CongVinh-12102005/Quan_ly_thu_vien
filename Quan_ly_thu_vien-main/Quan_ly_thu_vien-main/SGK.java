import java.util.*;

public class SGK extends Book {
    private int soLop;

    public SGK(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String maNhaCungCap, int soLuong, int soLop) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, maNhaCungCap, soLuong);
        this.soLop = soLop;
    }

    public SGK() {
        super();
    }

    public int getSoLop() {
        return soLop;
    }

    public void setSoLop(int soLop) {
        this.soLop = soLop;
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        
        // Nhap ma sach
        System.out.print("Nhap ma sach: ");
        setMaSach(sc.nextLine());
        
        // Nhap ten sach
        System.out.print("Nhap ten sach: ");
        setTenSach(sc.nextLine());
        
        // Nhap tac gia va kiem tra dieu kien
        String tacGia;
        do {
            System.out.print("Nhap tac gia: ");
            tacGia = sc.nextLine();
            if (!tacGia.matches("[a-zA-Z\\s]+")) {
                System.out.println("Tac gia chi duoc chua chu cai va khoang trang.");
            }
        } while (!tacGia.matches("[a-zA-Z\\s]+"));
        setTacGia(tacGia);
        
        // Nhap nha xuat ban va kiem tra dieu kien
        String nhaXuatBan;
        do {
            System.out.print("Nhap nha xuat ban: ");
            nhaXuatBan = sc.nextLine();
            if (!nhaXuatBan.matches("[a-zA-Z\\s]+")) {
                System.out.println("Nha xuat ban chi duoc chua chu cai va khoang trang.");
            }
        } while (!nhaXuatBan.matches("[a-zA-Z\\s]+"));
        setNhaXuatBan(nhaXuatBan);
        
        // Nhap ma nha cung cap
        System.out.print("Nhap ma nha cung cap: ");
        setmaNhaCungCap(sc.nextLine());
        
        // Nhap so luong va kiem tra dieu kien
        int soLuong;
        do {
            System.out.print("Nhap so luong: ");
            while (!sc.hasNextInt()) {
                System.out.println("So luong phai la mot so.");
                sc.next(); // Doc bo phan khong hop le
                System.out.print("Nhap so luong: "); // In lại dòng nhập nếu sai
            }
            soLuong = sc.nextInt();
            sc.nextLine(); // Doc bo dau newline
            if (soLuong <= 0) {
                System.out.println("So luong phai lon hon 0.");
                System.out.print("Nhap so luong: "); // In lại dòng nhập nếu sai
            }
        } while (soLuong <= 0); // Lặp lại yêu cầu nhập nếu số lượng không hợp lệ
        setSoLuong(soLuong);

        // Nhap so lop va kiem tra dieu kien
        int soLop;
        do {
            System.out.print("Nhap so lop: ");
            while (!sc.hasNextInt()) {
                System.out.println("So lop phai la mot so.");
                sc.next(); // Doc bo phan khong hop le
                System.out.print("Nhap so lop: "); // In lại dòng nhập nếu sai
            }
            soLop = sc.nextInt();
            sc.nextLine(); // Doc bo dau newline
            if (soLop < 1 || soLop > 12) {
                System.out.println("So lop phai nam trong khoang tu 1 den 12.");
                System.out.print("Nhap so lop: "); // In lại dòng nhập nếu sai
            }
        } while (soLop < 1 || soLop > 12); // Lặp lại yêu cầu nhập nếu số lớp không hợp lệ
        setSoLop(soLop); // Gán số lớp vào thuộc tính soLop
    }

    @Override
    public void xuat() {
        System.out.format("%-10s %-20s %-15s %-10s %-15s %-15s %-10s %-15s %-10s\n",
            getMaSach(),
            getTenSach(),
            getTacGia(),
            getLoaiSach(),
            getNhaXuatBan(),
            getmaNhaCungCap(),
            getSoLuong(),
            getSoLop(),  // In số lớp
            getTinhTrang()
        );
    }
}
