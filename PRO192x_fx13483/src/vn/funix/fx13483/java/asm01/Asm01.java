package vn.funix.fx13483.java.asm01;

import java.util.Random;
import java.util.Scanner;

public class Asm01 {
    public static final String AUTHOR = "FX13483";
    public static final String VERSION = "v1.0.0";
    public static final String CHU_CAI = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHU_CAI_HOA = CHU_CAI.toUpperCase();
    public static final String CHU_SO = "0123456789";
    public static final String CHU_VA_SO = CHU_CAI + CHU_CAI_HOA + CHU_SO;
    public static Random generator = new Random();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        hienThiGiaoDien();
        int num1 = chonChucNangBanDau();
        kiemTraChonChucNangBanDau(num1);
        String text = nhapCCCD();
        kiemTraChonChucNangChinh(text);
    }

    public static void hienThiGiaoDien() {
        System.out.println("+--------+-------------------+--------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "       |");
        System.out.println("+--------+-------------------+--------+");
        System.out.println("| 1. Nhap CCCD                        |");
        System.out.println("| 0. Thoat                            |");
        System.out.println("+--------+-------------------+--------+");
    }

    public static int chonChucNangBanDau() {
        int num = 2;
        do {
            //try catch de nhap 0 hoac 1 tranh nhap cac chu cai
            try {
                System.out.print("Chuc nang: ");
                String text = sc.nextLine();
                num = Integer.parseInt(text);
                if (!(num == 0) && !(num == 1)) {
                    System.out.println("Vui long chi nhap so 1 hoac 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long chi nhap so");
            }
        } while (!(num == 0) && !(num == 1));
        return num;
    }

    //Neu nguoi dung nhap 0 thi thoat, nhap 1 thi tiep tuc chuong trinh
    public static void kiemTraChonChucNangBanDau(int num) {
        if (num == 0) {
            System.out.println("Cam on quy khach da su dung dich vu");
            System.exit(0);
        } else {
            int chose = chonCheDo();
            kiemTraUserNhapMa(chose);
        }
    }

    //Chon che do 3 ky tu so hoac 6 ky tu chu va so
    public static int chonCheDo() {
        System.out.println("Chon che do: ");
        int num = 3;
        do {
            try {
                System.out.println("EASY 3 ky tu so (nhan so 1)");
                System.out.println("HARD 6 chuoi so (nhan so 2)");
                String text = sc.nextLine();
                num = Integer.parseInt(text);
                if (!(num == 1) && !(num == 2)) {
                    System.out.println("Vui long chi nhap so 1 hoac 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long chi nhap so 1 hoac 2");
            }
        } while (!(num == 1) && !(num == 2));
        return num;
    }

    //Kiem tra xem nguoi dung chon che do nao
    public static void kiemTraUserNhapMa(int num) {
        if (num == 1) {
            int easy = taoMaNgauNhienEasy();
            nhapMaXacThucEasy(easy);
        } else {
            String hard = taoMaNgauNhienHard();
            nhapMaXacThucHard(hard);
        }
    }

    //Tao so ngau nhien trong khoang tu min den max
    public static int taoSoNgauNhien(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    //Tao so co gia tri tu 100->999
    public static int taoMaNgauNhienEasy() {
        return generator.nextInt((999 - 100) + 1) + 100;
    }

    //Tao 6 ky tu gom ca so va chu
    public static String taoMaNgauNhienHard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int num = taoSoNgauNhien(0, CHU_VA_SO.length() - 1);
            char ch = CHU_VA_SO.charAt(num);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void nhapMaXacThucEasy(int easy) {
        System.out.println("Nhap ma xac thuc: " + easy);
        int num = -1;
        do {
            try {
                String text = sc.nextLine();
                num = Integer.parseInt(text);
                if (num != easy) {
                    System.out.println("Ma xac thuc khong dung. Vui long thu lai,");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long chi nhap so");
            }
        } while (num != easy);
    }

    public static void nhapMaXacThucHard(String hard) {
        System.out.println("Nhap ma xac thuc: " + hard);
        String text;
        do {
            text = sc.nextLine();
            if (!(text.equals(hard))) {
                System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            }
        } while (!(text.equals(hard)));
    }

    public static String nhapCCCD() {
        System.out.println("Vui long nhap so CCCD: ");
        long num = -1;
        String text = "a";
        boolean success = true;
        do {
            try {
                text = sc.nextLine();
                if (text.equalsIgnoreCase("no")) {
                    System.exit(0);
                }
                num = Long.parseLong(text);
                if (text.length() != 12) {
                    System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
                    continue;
                }
                success = false;
            } catch (NumberFormatException e) {
                System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
            }
        } while (success);
        return text;
    }

    public static void hienThiChonChucNangChinh() {
        System.out.println("   | 1. Kiem tra noi sinh");
        System.out.println("   | 2. Kiem tra tuoi, gioi tinh");
        System.out.println("   | 3. Kiem tra so ngau nhien");
        System.out.println("   | 0. Thoat");
    }

    public static int chonChucNangChinh() {
        int num = 4;
        do {
            //try catch de nhap 0 hoac 1 tranh nhap cac chu cai
            try {
                System.out.print("Chuc nang: ");
                String text = sc.nextLine();
                num = Integer.parseInt(text);
                if (!(num == 0) && !(num == 1) && !(num == 2) && !(num == 3)) {
                    System.out.println("Vui long chi nhap so 0, 1, 2, 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long chi nhap so");
            }
        } while (!(num == 0) && !(num == 1) && !(num == 2) && !(num == 3));
        return num;
    }

    public static void kiemTraChonChucNangChinh(String text) {
        int num;
        do {
            hienThiChonChucNangChinh();
            num = chonChucNangChinh();
            switch (num) {
                case 0 -> System.exit(0);
                case 1 -> kiemTraNoiSinh(text);
                case 2 -> kiemTraTuoiGioiTinh(text);
                case 3 -> kiemTraDaySoNgauNhien(text);
            }
        } while (true);

    }

    public static void kiemTraNoiSinh(String text) {

        switch (text.substring(0, 3)) {
            case "001" -> System.out.println("Noi sinh: Ha Noi");
            case "002" -> System.out.println("Noi sinh: Ha Giang");
            case "004" -> System.out.println("Noi sinh: Cao Bang");
            case "006" -> System.out.println("Noi sinh: Bac Kan");
            case "008" -> System.out.println("Noi sinh: Tuyen Quang");
            case "010" -> System.out.println("Noi sinh: Lao Cai");
            case "011" -> System.out.println("Noi sinh: Dien Bien");
            case "012" -> System.out.println("Noi sinh: Lai Chau");
            case "014" -> System.out.println("Noi sinh: Son La");
            case "015" -> System.out.println("Noi sinh: Yen Bai");
            case "017" -> System.out.println("Noi sinh: Hoa Binh");
            case "019" -> System.out.println("Noi sinh: Thai Nguyen");
            case "020" -> System.out.println("Noi sinh: Lang Son");
            case "022" -> System.out.println("Noi sinh: Quang Ninh");
            case "024" -> System.out.println("Noi sinh: Bac Giang");
            case "025" -> System.out.println("Noi sinh: Phu Tho");
            case "026" -> System.out.println("Noi sinh: Vinh Phuc");
            case "027" -> System.out.println("Noi sinh: Bac Ninh");
            case "030" -> System.out.println("Noi sinh: Hai Duong");
            case "031" -> System.out.println("Noi sinh: Hai Phong");
            case "033" -> System.out.println("Noi sinh: Hung Yen");
            case "034" -> System.out.println("Noi sinh: Thai Binh");
            case "035" -> System.out.println("Noi sinh: Ha Nam");
            case "036" -> System.out.println("Noi sinh: Nam Dinh");
            case "037" -> System.out.println("Noi sinh: Ninh Binh");
            case "038" -> System.out.println("Noi sinh: Thanh Hoa");
            case "040" -> System.out.println("Noi sinh: Nghe An");
            case "042" -> System.out.println("Noi sinh: Ha Tinh");
            case "044" -> System.out.println("Noi sinh: Quang Binh");
            case "045" -> System.out.println("Noi sinh: Quang Tri");
            case "046" -> System.out.println("Noi sinh: Thua Thien Hue");
            case "048" -> System.out.println("Noi sinh: Da Nang");
            case "049" -> System.out.println("Noi sinh: Quang Nam");
            case "051" -> System.out.println("Noi sinh: Quang Ngai");
            case "052" -> System.out.println("Noi sinh: Binh Dinh");
            case "054" -> System.out.println("Noi sinh: Phu Yen");
            case "056" -> System.out.println("Noi sinh: Khanh Hoa");
            case "058" -> System.out.println("Noi sinh: Ninh Thuan");
            case "060" -> System.out.println("Noi sinh: Binh Thuan");
            case "062" -> System.out.println("Noi sinh: Kon Tum");
            case "064" -> System.out.println("Noi sinh: Gia Lai");
            case "066" -> System.out.println("Noi sinh: Dak Lak");
            case "067" -> System.out.println("Noi sinh: Dak Nong");
            case "068" -> System.out.println("Noi sinh: Lam Dong");
            case "070" -> System.out.println("Noi sinh: Binh Phuoc");
            case "072" -> System.out.println("Noi sinh: Tay Ninh");
            case "074" -> System.out.println("Noi sinh: Binh Duong");
            case "075" -> System.out.println("Noi sinh: Dong Nai");
            case "077" -> System.out.println("Noi sinh: Ba Ria - Vung Tau");
            case "079" -> System.out.println("Noi sinh: Ho Chi Minh");
            case "080" -> System.out.println("Noi sinh: Long An");
            case "082" -> System.out.println("Noi sinh: Tien Giang");
            case "083" -> System.out.println("Noi sinh: Ben Tre");
            case "084" -> System.out.println("Noi sinh: Tra Vinh");
            case "086" -> System.out.println("Noi sinh: Vinh Long");
            case "087" -> System.out.println("Noi sinh: Dong Thap");
            case "089" -> System.out.println("Noi sinh: An Giang");
            case "091" -> System.out.println("Noi sinh: Kien Giang");
            case "092" -> System.out.println("Noi sinh: Can Tho");
            case "093" -> System.out.println("Noi sinh: Hau Giang");
            case "094" -> System.out.println("Noi sinh: Soc Trang");
            case "095" -> System.out.println("Noi sinh: Bac Lieu");
            case "096" -> System.out.println("Noi sinh: Ca Mau");
            default -> System.out.println("Khong xac dinh duoc noi sinh");
        }
    }

    public static void kiemTraTuoiGioiTinh(String text) {
        switch (text.substring(3, 4)) {
            case "0" -> System.out.println("Gioi tinh: Nam | 19" + text.substring(4, 6));
            case "1" -> System.out.println("Gioi tinh: Nu | 19" + text.substring(4, 6));
            case "2" -> System.out.println("Gioi tinh: Nam | 20" + text.substring(4, 6));
            case "3" -> System.out.println("Gioi tinh: Nu | 20" + text.substring(4, 6));
            case "4" -> System.out.println("Gioi tinh: Nam | 21" + text.substring(4, 6));
            case "5" -> System.out.println("Gioi tinh: Nu | 21" + text.substring(4, 6));
            case "6" -> System.out.println("Gioi tinh: Nam | 22" + text.substring(4, 6));
            case "7" -> System.out.println("Gioi tinh: Nu | 22" + text.substring(4, 6));
            case "8" -> System.out.println("Gioi tinh: Nam | 23" + text.substring(4, 6));
            case "9" -> System.out.println("Gioi tinh: Nu | 23" + text.substring(4, 6));
        }
    }

    public static void kiemTraDaySoNgauNhien(String text) {
        System.out.println("So ngau nhien: " + text.substring(6));
    }

}
//+ Các logic chính chạy đúng. + Nên code sạch đẹp đúng chuẩn hơn. + Nên comment đầy đủ để giúp quá trình bảo trì thuận lợi hơn. + Khuyến khích sử dụng Tiếng Anh khi đặt tên. + Các giá trị không đổi như Tỉnh Thành thì nên lưu trữ ở một nơi nào đó để tiện sử dụng ở nhiều nơi khác. Ví dụ chúng ta có thể sử dụng HashMap để mapping giữa mã và tên Tỉnh Thành. Có thể lưu tỉnh thành vào file CSV, khi chương trình khởi chạy thì load dữ liệu từ file CSV vào HashMap. + Input của một hàm nên được giới hạn phạm vi trước khi được sử dụng. Ví dụ hàm kiemTraNoiSinh(String text), đoạn code text.substring(0, 3) nên được chạy ở ngoài hàm trước đó. + Nên sử dụng hàm random.nextInt(origin, bound) để đạt được kết quả tương tự, nhưng dễ hiểu hơn, ngắn gọn hơn.