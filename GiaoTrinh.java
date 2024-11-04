import java.util.Arrays;
import java.util.Scanner;

public class GiaoTrinh extends Book {
    private String monHoc;

    // Constructor
    public GiaoTrinh(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong, String monHoc) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong);
        this.monHoc = monHoc;
    }

    // Constructor mac dinh
    public GiaoTrinh() {
        super();
    }

    // Getter va Setter cho monHoc
    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    // Cac thuoc tinh va mang luu tru danh sach giao trinh
    private static Scanner sc = new Scanner(System.in);
    private GiaoTrinh[] giaoTrinhList = new GiaoTrinh[0];
    private int n = 0;

    // Nhap thong tin cho tung giao trinh
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
        System.out.print("Nhap mon hoc: ");
        this.monHoc = sc.nextLine();
    }

    // Xuat thong tin giao trinh
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
        System.out.println("Mon hoc: " + getMonHoc());
    }

    // Them giao trinh moi vao danh sach
    public void them() {
        giaoTrinhList = Arrays.copyOf(giaoTrinhList, n + 1);
        GiaoTrinh newGiaoTrinh = new GiaoTrinh();
        newGiaoTrinh.nhap();
        giaoTrinhList[n] = newGiaoTrinh;
        n++;
    }

    // Xoa giao trinh theo ma
    public void xoa() {
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (giaoTrinhList[i].getMaSach().equals(maSach)) {
                for (int j = i; j < n - 1; j++) {
                    giaoTrinhList[j] = giaoTrinhList[j + 1];
                }
                giaoTrinhList = Arrays.copyOf(giaoTrinhList, n - 1);
                n--;
                found = true;
                System.out.println("Da xoa giao trinh co ma " + maSach);
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay giao trinh co ma " + maSach);
        }
    }

    // Tim kiem giao trinh theo ma hoac ten
    public GiaoTrinh timKiem() {
        System.out.print("Nhap tu khoa tim kiem (ma sach hoac ten sach): ");
        String search = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (giaoTrinhList[i].getMaSach().equalsIgnoreCase(search) || giaoTrinhList[i].getTenSach().equalsIgnoreCase(search)) {
                giaoTrinhList[i].xuat();
                return giaoTrinhList[i];
            }
        }

        System.out.println("Khong tim thay giao trinh voi tu khoa: " + search);
        return null;
    }

    // Sua thong tin giao trinh theo ma
    public void sua() {
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (giaoTrinhList[i].getMaSach().equals(maSach)) {
                System.out.println("Nhap lai thong tin cho giao trinh ma " + maSach + ":");
                giaoTrinhList[i].nhap();
                System.out.println("Da cap nhat thong tin.");
                return;
            }
        }

        System.out.println("Khong tim thay giao trinh co ma " + maSach);
    }

    // Xuat danh sach tat ca giao trinh
    public void xuatDanhSach() {
        System.out.println("Danh sach giao trinh:");
        for (int i = 0; i < n; i++) {
            giaoTrinhList[i].xuat();
            System.out.println("--------------------");
        }
    }
}
