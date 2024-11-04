import java.util.*;
public class Truyen extends Book {
    private String theLoai;

    public Truyen(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong, String theLoai) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong);
        this.theLoai = theLoai;
    }

    public Truyen() {
        super();
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach: ");
        setMaSach(sc.nextLine());
        System.out.print("Nhap ten sach: ");
        setTenSach(sc.nextLine());
        System.out.print("Nhap tac gia: ");
        setTacGia(sc.nextLine());
        System.out.print("Nhap nha xuat ban: ");
        setNhaXuatBan(sc.nextLine());
        System.out.print("Nhap nha cung cap: ");
        setNhaCungCap(sc.nextLine());
        System.out.print("Nhap so luong: ");
        setSoLuong(sc.nextInt());
        sc.nextLine(); 
        System.out.print("Nhap the loai: ");
        this.theLoai = sc.nextLine();
    }

    @Override
    public void xuat(){
        System.out.format("%-10s %-20s %-15s %-10s %-15s %-15s %-10s %-15s %-10s\n",
            getMaSach(),
            getTenSach(),
            getTacGia(),
            getLoaiSach(),
            getNhaXuatBan(),
            getNhaCungCap(),
            getSoLuong(),
            getTheLoai(),
            getTinhTrang()
        );
    }
}
