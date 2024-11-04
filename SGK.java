import java.util.Arrays;
import java.util.Scanner;

public class SGK extends Book {
    private String soLop;

    // Constructor
    public SGK(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong, String soLop) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong);
        this.soLop = soLop;
    }

    // Constructor mac dinh
    public SGK() {
        super();
    }

    // Getter va Setter cho soLop
    public String getSoLop() {
        return soLop;
    }

    public void setSoLop(String soLop) {
        this.soLop = soLop;
    }

    // Cac thuoc tinh va mang luu tru sach giao khoa
    private static Scanner sc = new Scanner(System.in);
    private SGK[] sgkList = new SGK[0];
    private int n = 0;

    // Nhap thong tin cho tung sach giao khoa
    @Override
    public void nhap() {
        System.out.print("Nhap ma sach: ");
        setMaSach(sc.nextLine());
        System.out.print("Nhap ten sach: ");
        setTenSach(sc.nextLine());
        System.out.print("Nhap tac gia: ");
        setTacGia(sc.nextLine());
        System.out.print("Nhap loai sach: ");
        setLoaiSach(sc.nextLine());
        System.out.print("Nhap nha xuat ban: ");
        setNhaXuatBan(sc.nextLine());
        System.out.print("Nhap nha cung cap: ");
        setNhaCungCap(sc.nextLine());
        System.out.print("Nhap so luong: ");
        setSoLuong(sc.nextInt());
        sc.nextLine(); // Doc bo dong xuong hang
        System.out.print("Nhap lop: ");
        this.soLop = sc.nextLine();
    }

    // Xuat thong tin sach giao khoa
    @Override
    public void xuat() {
        System.out.println("Ma sach: " + getMaSach());
        System.out.println("Ten sach: " + getTenSach());
        System.out.println("Tac gia: " + getTacGia());
        System.out.println("Loai sach: " + getLoaiSach());
        System.out.println("Nha xuat ban: " + getNhaXuatBan());
        System.out.println("Nha cung cap: " + getNhaCungCap());
        System.out.println("So luong: " + getSoLuong());
        System.out.println("Tinh trang: " + getTinhTrang());
        System.out.println("Lop: " + getSoLop());
    }

    // Them sach giao khoa moi vao danh sach
    public void them() {
        sgkList = Arrays.copyOf(sgkList, n + 1);
        SGK newSGK = new SGK();
        newSGK.nhap();
        sgkList[n] = newSGK;
        n++;
    }

    // Xoa sach giao khoa theo ma
    public void xoa() {
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (sgkList[i].getMaSach().equals(maSach)) {
                for (int j = i; j < n - 1; j++) {
                    sgkList[j] = sgkList[j + 1];
                }
                sgkList = Arrays.copyOf(sgkList, n - 1);
                n--;
                found = true;
                System.out.println("Da xoa sach giao khoa co ma " + maSach);
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay sach giao khoa co ma " + maSach);
        }
    }

    // Tim kiem sach giao khoa theo ma hoac ten
    public SGK timKiem() {
        System.out.print("Nhap tu khoa tim kiem (ma sach hoac ten sach): ");
        String search = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (sgkList[i].getMaSach().equalsIgnoreCase(search) || sgkList[i].getTenSach().equalsIgnoreCase(search)) {
                sgkList[i].xuat();
                return sgkList[i];
            }
        }

        System.out.println("Khong tim thay sach giao khoa voi tu khoa: " + search);
        return null;
    }

    // Sua thong tin sach giao khoa theo ma
    public void sua() {
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (sgkList[i].getMaSach().equals(maSach)) {
                System.out.println("Nhap lai thong tin cho sach giao khoa ma " + maSach + ":");
                sgkList[i].nhap();
                System.out.println("Da cap nhat thong tin.");
                return;
            }
        }

        System.out.println("Khong tim thay sach giao khoa co ma " + maSach);
    }

    // Xuat danh sach tat ca sach giao khoa
    public void xuatDanhSach() {
        System.out.println("Danh sach sach giao khoa:");
        for (int i = 0; i < n; i++) {
            sgkList[i].xuat();
            System.out.println("--------------------");
        }
    }
}
