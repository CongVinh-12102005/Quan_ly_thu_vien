import java.util.*;
public class GiaoTrinh extends Book {
    private String monHoc;

    public GiaoTrinh(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong, String monHoc) {
        super(maSach, tenSach, tacGia, loaiSach, nhaXuatBan, nhaCungCap, soLuong);
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
        System.out.print("Nhap mon hoc: ");
        this.monHoc = sc.nextLine();
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
            getMonHoc(),
            getTinhTrang()
        );
    }
}
