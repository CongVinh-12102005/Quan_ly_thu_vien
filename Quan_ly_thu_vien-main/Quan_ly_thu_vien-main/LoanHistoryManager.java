
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanHistoryManager {
   private static ArrayList <LoanRecord> loanRecordList = new ArrayList<LoanRecord>();
   BookManager BookManager= new BookManager();
   
   public void Management(){
      loadFromFile();
      BookManager.docSachTuFile(); // Gọi phương thức loadFromFile

      Scanner sc = new Scanner(System.in);
      boolean exit = false;

      while(!exit){
         System.out.println("============= QUAN LY LICH SU MUON TRA =============");
         System.out.println("1. Danh Sach Muon Tra");
         System.out.println("2. Tim Kiem");
         System.out.println("3. Muon");
         System.out.println("4. Tra");
         System.out.println("5. Thoat");
         System.out.print("Lua chon : ");
         int choice = sc.nextInt();
         sc.nextLine();
         switch (choice) {
            case 1:
                viewLoanRecords();
                break;
            case 2:
                searchLoanRecord(sc);
                break;
            case 3:
                borrowBook(sc);
                break;
            case 4:
                returnBook(sc);
                break;
            case 5:
                System.out.println("Tro ve");
                exit = true;
                break;
            default:
                System.out.println("Lua chon khong hop le. Vui long chon lai.");
         }
         sc.nextLine();
      }

   }


   public void viewLoanRecords() {
      System.out.println("=============== DANH SACH MUON TRA ===============");
  
      System.out.printf("%-10s %-20s %-10s %-10s %-20s %-20s %-10s %-20s%n",
              "MaSach", "TenSach","MaMuon", "SoLuong", "NgayMuon", "NgayTraDuKien", "TrangThai", "NgayTraThucTe");
  
      
      if (loanRecordList.isEmpty()) {
          System.out.println("Khong co ban ghi nao.");
      } else {
         for (LoanRecord record : loanRecordList) 
            System.out.println(record);
         
      }
  }


  /*          TIM KIEM                  */
   public void searchLoanRecord(Scanner scanner) {
      System.out.print("Nhap ID nguoi muon hoac ID sach: ");
      String id = scanner.nextLine();
      boolean found = false;

      for (LoanRecord record : loanRecordList) {
         if (record.getBook().getMaSach().equals(id) /* || record.getUserId().equals(id) */) {
            System.out.println(record); 
            found = true;
         }
      }

      if (!found) {
         System.out.println("Khong tim thay ket qua cho: " + id);
      }
   }
   /*        MUON SACH                */
   public void borrowBook(Scanner scanner) {
      String maSach;
      String tenSach;
      Book book;
      String maMuon = "M" + (loanRecordList.size()+1);

      while (true) {
         System.out.print("Nhap ID sach muon: ");
         maSach = scanner.nextLine();
         System.out.print("Nhap ten sach: ");
         tenSach = scanner.nextLine();
         book = BookManager.timMuonSach(maSach, tenSach);
         if (book == null) {
            System.out.println("khong ton tai ID va ten sach nay trong thu vien, vui long nhap lai");
         } else {
            break;
         }
      }
         int soLuong = 0;
         int hienCo = book.getSoLuong();
         
         if(hienCo <=0) {
            System.out.println("sach nay da het cho trong");
            return;
         }

      while (true) {
         System.out.print("Nhap so luong muon: ");
         if (scanner.hasNextInt()) {
              soLuong = scanner.nextInt();
              scanner.nextLine();

               if (soLuong > 0) 
                  if(soLuong <= hienCo){
                     book.setSoLuong((hienCo - soLuong));
                     break;
                  }
                  else System.out.println("so luong muon vuot qua so luong hien co");
               else 
                  System.out.println("So luong phai lon hon 0. Vui long nhap lai.");
              
         }else {
              System.out.println("Nhap so luong sai. Vui long nhap lai.");
              scanner.nextLine();
          }
      }

      //ngay muon
      LocalDateTime ngayMuon = LocalDateTime.now();

      //ngay tra 
      LocalDate ngayTraD = null;
      LocalDateTime ngayTra = null;
      while (true) {
        System.out.print("Nhap ngay tra (dd-MM-yyyy): ");
        String ngayTraStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        try {
            ngayTraD = LocalDate.parse(ngayTraStr, formatter);
            ngayTra = ngayTraD.atStartOfDay()
                .withHour(ngayMuon.getHour())
                .withMinute(ngayMuon.getMinute())
                .withSecond(ngayMuon.getSecond());
            
            if (ngayTraD.isBefore(ngayMuon.toLocalDate())) {
                System.out.println("Ngay tra khong hop le (phai sau ngay muon).");
            } else {
                break;
            }
         } catch (Exception e) {
            System.out.println("Vui long nhap theo dinh dang dd-MM-yyyy.");
         }
      }
      // System.out.print("Nhap ID nguoi muon: ");
      // String borrowerId = scanner.nextLine();
      // System.out.print("Nhap ten nguoi muon: ");
      // String borrowerName = scanner.nextLine();
      

      LoanRecord record = new LoanRecord(book, soLuong, maMuon, /* borrowerId, borrowerName, */ ngayMuon, ngayTra);
      loanRecordList.add(record);
      saveToFile(record);
      System.out.println("Da muon sach voi ID: " + maSach /* + " cho nguoi muon: " + borrowerName */);
   }
/*       TRA SACH              */
   public void returnBook(Scanner scanner) {
      System.out.print("Nhap ma muon sach muon tra: ");
      String maMuon = scanner.nextLine();
      boolean found = false;  

      for (LoanRecord record : loanRecordList) {
         if (record.getMaMuon().equals(maMuon) && 
            (record.getTrangThaiSach()=="Dang Muon" || record.getTrangThaiSach()=="Qua Han") ) {
               found = true;
               
               record.setNgayTraThucTe(LocalDateTime.now());
               record.tinhTienPhat();

               if(record.getTienPhat() > 0){
                  System.out.println("Sach tra qua han. Tien phat qua han: " + record.getTienPhat() + " VND");
               } 

               record.setTrangThaiSach("Da Tra");
               System.out.println("Da tra sach voi Ma: " + maMuon);

               Book book = record.getBook();
               book.setSoLuong(book.getSoLuong() + record.getSoLuong());

               saveUpdatedRecord(record);
               break;
         }
      }
      if (!found) {
         System.out.println("Khong tim thay ban ghi muon voi ma muon : " + maMuon + " hoac sach da duoc tra.");
      }
   }
/*      LOAD FILE loanrecord.txt     */
   public void loadFromFile() {
      loanRecordList.clear();
      try (Scanner scanner = new Scanner(new File("loanrecord.txt"))) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int length = parts.length;
            if (length >= 7) { 
                  String maSach = parts[0];
               // System.out.println("ghi ma sach");
                  String tenSach = parts[1];
               // System.out.println("ghi ten");
                  String maMuon = parts[2];
                  int soLuong = Integer.parseInt(parts[3]);
                  // System.out.println("ghi so");
                  // String borrowerId = parts[3];
                  // String borrowerName = parts[4];
                  LocalDateTime ngayMuon = LocalDateTime.parse(parts[4], formatter);
                  // System.out.println("nghi ngay tra");
                  LocalDateTime ngayTra = LocalDateTime.parse(parts[5], formatter);
                  // System.out.println("ghi ngay");
                  String trangThai = parts[6];

                  Book book = BookManager.timMuonSach(maSach,tenSach);
                  if(book==null) continue;
                  LoanRecord record = new LoanRecord(book, soLuong,maMuon, ngayMuon, ngayTra, trangThai);

                  if (length >= 8 && trangThai.equals("Da Tra") && !parts[7].equals("null")) {
                     LocalDateTime ngayTraThucTe = LocalDateTime.parse(parts[7], formatter);
                     record.setNgayTraThucTe(ngayTraThucTe);
                  }

                  loanRecordList.add(record);
            }
         }
      } catch (FileNotFoundException e) {
         System.out.println("Không tìm thấy tệp: " + e.getMessage());
      } catch (Exception e) {
         System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
      }
   }


   /*SAVE FILE  */
   public static void saveToFile(LoanRecord record) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("loanrecord.txt",true))) {
               bw.write(record.toFileFormatString()); 
               bw.newLine();
      } catch (IOException e) {
            System.out.println("Lỗi khi ghi tệp: " + e.getMessage());
      }
   }

   public static void saveAllToFile() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("loanrecord.txt",true))) {
            for (LoanRecord record : loanRecordList) {
               bw.write(record.toFileFormatString()); 
               bw.newLine();
            }
      } catch (IOException e) {
            System.out.println("Lỗi khi ghi tệp: " + e.getMessage());
      }
   }


   public static void saveUpdatedRecord(LoanRecord updatedRecord) {
    File inputFile = new File("loanrecord.txt");
    File tempFile = new File("loanrecord_temp.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            // Tách dòng hiện tại thành bản ghi
            String[] parts = currentLine.split(",");
            String maMuon = parts[2]; // Giả định mã mượn là trường đầu tiên

            // Nếu mã mượn khớp với bản ghi cập nhật, ghi lại dòng đã cập nhật
            if (maMuon.equals(updatedRecord.getMaMuon())) {
                writer.write(updatedRecord.toFileFormatString());
            } else {
                writer.write(currentLine); // Nếu không, ghi lại dòng cũ
            }
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Lỗi khi cập nhật tệp: " + e.getMessage());
    }

    // Xóa tệp gốc và đổi tên tệp tạm thành tệp gốc
    if (inputFile.delete()) {
        tempFile.renameTo(inputFile);
    }
}
}
