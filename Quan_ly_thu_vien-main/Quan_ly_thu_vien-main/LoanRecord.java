

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LoanRecord {
    private Book book;
    private int soLuong;
    private String maMuon;
    // private User user;
    private LocalDateTime ngayMuon;
    private LocalDateTime ngayTraDuKien;
    private LocalDateTime ngayTraThucTe;
    private String trangThaiSach;
    private long tienPhat;
    private final long MUC_PHAT = 15000;
    public LoanRecord(Book book, int soLuong, String maMuon, LocalDateTime ngayMuon){
        this.book = book;
        this.soLuong = soLuong;
        this.maMuon=maMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayMuon.plusDays(14);
        this.ngayTraThucTe = null;
        this.trangThaiSach = "Dang Muon";
        this.tienPhat = 0;
    }
    
    public LoanRecord(Book book, int soLuong,String maMuon, LocalDateTime ngayMuon, LocalDateTime ngayTraDuKien){
        this.book = book;
        this.soLuong = soLuong;
        this.maMuon=maMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.ngayTraThucTe = null;
        this.trangThaiSach = "Dang Muon";
        this.tienPhat = 0;
    }
    public LoanRecord(Book book, int soLuong,String maMuon, LocalDateTime ngayMuon, LocalDateTime ngayTraDuKien, 
                     String trangThai){
        this.book = book;
        this.soLuong = soLuong;
        this.maMuon=maMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.trangThaiSach =trangThai;
        this.tienPhat = 0;
    }

    
    public void tinhTienPhat(){
        if (ngayTraThucTe != null && ngayTraThucTe.isAfter(ngayTraDuKien)) {
            Duration tgianQuaHan = Duration.between(ngayTraDuKien, ngayTraThucTe);
            long soNgayQuaHan = tgianQuaHan.toDays();
            this.tienPhat = soNgayQuaHan * MUC_PHAT;
        } else {
            this.tienPhat = 0;
        }
    }

    public String capNhatTrangThaiSach() {
        if (ngayTraThucTe == null) {
            LocalDateTime now = LocalDateTime.now();
            if (!(trangThaiSach=="Da Tra") && now.isAfter(ngayTraDuKien)) {
                trangThaiSach = "Qua Han";
            } else {
                trangThaiSach = "Dang Muon";
            }
        } else {
            trangThaiSach = "Da Tra";
        }
        return trangThaiSach;
    }


    @Override
    public String toString() {
        capNhatTrangThaiSach();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return String.format("%-10s %-20s %-10s %-10d %-20s %-20s %-10s %-20s",
                book.getMaSach(),
                book.getTenSach(),
                maMuon,
                soLuong,
                ngayMuon.format(formatter),
                ngayTraDuKien.format(formatter),
                trangThaiSach,
                (ngayTraThucTe != null) ? ngayTraThucTe.format(formatter) : "N/A");
    }
    /*FORMAT CHO GHI VAO FILE */
    public String toFileFormatString() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("%s,%s,%s,%d,%s,%s,%s,%s",
                book.getMaSach(),
                book.getTenSach(),
                maMuon,
                soLuong,
                ngayMuon.format(fileFormatter),
                ngayTraDuKien.format(fileFormatter),
                trangThaiSach,
                ngayTraThucTe != null ? ngayTraThucTe.format(fileFormatter) : "null");
    }

    // Getter

    public Book getBook(){
        return this.book;
    }
    public String getMaMuon(){
        return this.maMuon;
    }
    public LocalDateTime getNgayMuon(){
        return this.ngayMuon;
    }
    public LocalDateTime getNgayTraDuKien(){
        return this.ngayTraDuKien;
    }
    public LocalDateTime getNgayTraThucTe(){
        return this.ngayTraThucTe;
    }
    public String getTrangThaiSach(){
        return this.trangThaiSach;
    }   
    public int getSoLuong(){
        return this.soLuong;
    }
    public long getTienPhat(){
        return this.tienPhat;
    }

    // Setter

    public void setBook(Book book){
        this.book = book;
    }
    public void setMaMuon(String maMuon){
        this.maMuon=maMuon;
    }
    public void setNgayMuon(LocalDateTime ngayMuon){
        this.ngayMuon = ngayMuon;
    }
    public void setNgayTraDuKien(LocalDateTime ngayTraDuKien){
        this.ngayTraDuKien = ngayTraDuKien;
    }
    public void setNgayTraThucTe(LocalDateTime ngayTraThucTe){
        this.ngayTraThucTe = ngayTraThucTe;
    }
    public void setTrangThaiSach(String trangThaiSach){
        this.trangThaiSach = trangThaiSach;
    }
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    public void setTienPhat(long tienPhat){
        this.tienPhat = tienPhat;
    }
}


