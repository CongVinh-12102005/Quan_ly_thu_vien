public abstract class Book {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String loaiSach;
    private String nhaXuatBan;
    private String nhaCungCap;
    private int soLuong;
    private String tinhTrang;

    // Constructor
    public Book(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String nhaCungCap, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.loaiSach = loaiSach;
        this.nhaXuatBan = nhaXuatBan;
        this.nhaCungCap = nhaCungCap;
        this.soLuong = soLuong;
        this.tinhTrang = capNhatTinhTrang();
    }

    // Constructor mặc định
    public Book() {}

    protected String capNhatTinhTrang() {
        return soLuong > 0 ? "Con hang" : "Het hang";
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.tinhTrang = capNhatTinhTrang(); 
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    // Các hàm trừu tượng 
    public abstract void nhap();
    public abstract void xuat();
}
