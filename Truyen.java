import java.util.Arrays;
import java.util.Scanner;

public class Truyen extends Book {
    private String theLoai;

    // Constructor
    public Truyen(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong, String theLoai) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong);
        this.theLoai = theLoai;
    }

    // Constructor mac dinh
    public Truyen() {
        super();
    }

    // Getter va Setter cho theLoai
    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    // Cac thuoc tinh va mang luu tru danh sach truyen
    private static Scanner sc = new Scanner(System.in);
    private Truyen[] truyenList = new Truyen[0];
    private int n = 0;

    // Nhap thong tin cho tung truyen
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
        System.out.print("Nhap the loai: ");
        this.theLoai = sc.nextLine();
    }

    // Xuat thong tin truyen
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
        System.out.println("The loai: " + getTheLoai());
    }

    // Them truyen moi vao danh sach
    public void them() {
        truyenList = Arrays.copyOf(truyenList, n + 1);
        Truyen newTruyen = new Truyen();
        newTruyen.nhap();
        truyenList[n] = newTruyen;
        n++;
    }

    // Xoa truyen theo ma
    public void xoa() {
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (truyenList[i].getMaSach().equals(maSach)) {
                for (int j = i; j < n - 1; j++) {
                    truyenList[j] = truyenList[j + 1];
                }
                truyenList = Arrays.copyOf(truyenList, n - 1);
                n--;
                found = true;
                System.out.println("Da xoa truyen co ma " + maSach);
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay truyen co ma " + maSach);
        }
    }

    // Tim kiem truyen theo ma hoac ten
    public Truyen timKiem() {
        System.out.print("Nhap tu khoa tim kiem (ma sach hoac ten sach): ");
        String search = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (truyenList[i].getMaSach().equalsIgnoreCase(search) || truyenList[i].getTenSach().equalsIgnoreCase(search)) {
                truyenList[i].xuat();
                return truyenList[i];
            }
        }

        System.out.println("Khong tim thay truyen voi tu khoa: " + search);
        return null;
    }

    // Sua thong tin truyen theo ma
    public void sua() {
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (truyenList[i].getMaSach().equals(maSach)) {
                System.out.println("Nhap lai thong tin cho truyen ma " + maSach + ":");
                truyenList[i].nhap();
                System.out.println("Da cap nhat thong tin.");
                return;
            }
        }

        System.out.println("Khong tim thay truyen co ma " + maSach);
    }

    // Xuat danh sach tat ca truyen
    public void xuatDanhSach() {
        System.out.println("Danh sach truyen:");
        for (int i = 0; i < n; i++) {
            truyenList[i].xuat();
            System.out.println("--------------------");
        }
    }
}
