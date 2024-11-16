import java.util.*;

public class GiaoTrinh extends Book {
    private String monHoc;

    public GiaoTrinh(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String maNhaCungCap, int soLuong, String monHoc) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, maNhaCungCap, soLuong);
        this.monHoc = monHoc;
    }

    public GiaoTrinh() {
        super();
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
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
        
        // Nhap mon hoc va kiem tra dieu kien
        String monHoc;
        do {
            System.out.print("Nhap mon hoc: ");
            monHoc = sc.nextLine();
            if (!monHoc.matches("[a-zA-Z\\s]+")) {
                System.out.println("Mon hoc chi duoc chua chu cai va khoang trang.");
            }
        } while (!monHoc.matches("[a-zA-Z\\s]+"));
        this.monHoc = monHoc;
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
            getMonHoc(),
            getTinhTrang()
        );
    }
}
