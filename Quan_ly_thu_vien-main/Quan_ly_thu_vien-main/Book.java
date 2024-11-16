public abstract class Book implements BookOperations {
    private static int soLuongSach = 0;
    
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String loaiSach;
    private String nhaXuatBan;
    private String maNhaCungCap;
    private int soLuong;
    private String tinhTrang;

    // Constructor
    public Book(String maSach, String tenSach, String tacGia, String loaiSach, String nhaXuatBan, String maNhaCungCap, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.loaiSach = loaiSach;
        this.nhaXuatBan = nhaXuatBan;
        this.maNhaCungCap = maNhaCungCap;
        this.soLuong = soLuong;
        this.tinhTrang = capNhatTinhTrang();
    }
    public Book(){}
    // Constructor mặc định
    public void tangSoLuong() {
        soLuongSach++;
    }

    public static int getSoLuongSach() {
        return soLuongSach;
    }

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

    public String getmaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setmaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
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
