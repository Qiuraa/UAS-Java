import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BioskopSystem bioskopSystem = new BioskopSystem();

        bioskopSystem.initDummyData();

        while (true) {
            bioskopSystem.mulai();
            
            boolean buatAkun = false;
            while (!buatAkun) {
                System.out.print("Apakah Anda ingin membuat akun baru? (ya/tidak): ");
                String response = scanner.nextLine().toLowerCase();
                if (response.equals("ya")) {
                    bioskopSystem.tambahUserBaru(scanner);
                    buatAkun = true;
                } else if (response.equals("tidak")) {
                    buatAkun = true;
                } else {
                    System.out.println("Input tidak valid. Silakan jawab dengan 'ya' atau 'tidak'.");
                }
            }

            System.out.print("Masukkan username (ketik 'exit' untuk keluar) : ");
            String username = scanner.nextLine();

            if (username.equals("exit")) {
                System.out.println("Keluar dari sistem. Terima kasih!");
                break;
            }

            System.out.print("Masukkan password (ketik 'exit' untuk keluar) : ");
            String password = scanner.nextLine();

            if (password.equals("exit")) {
                System.out.println("Keluar dari sistem. Terima kasih!");
                break;
            }
            bioskopSystem.login(username, password);

            boolean flag = true;
            while (flag == true) {
                if (bioskopSystem.getCurrentUser() == null) {
                    break;
                }
                if (bioskopSystem.getCurrentUser().getRole().equals("admin")) {
                    System.out.println("Anda masuk sebagai admin.");

                    bioskopSystem.menuAdmin(scanner);

                    if (bioskopSystem.getCurrentUser() == null) {
                        flag = false;

                    }

                }

                else if (bioskopSystem.getCurrentUser().getRole().equals("customer")) {
                    System.out.println("Selamat datang, " + bioskopSystem.getCurrentUser().getUsername() + "!");

                    bioskopSystem.menuUser(scanner);

                    if (bioskopSystem.getCurrentUser() == null) {
                        flag = false;
                    }

                }

                else {
                    System.out.println("Gagal masuk. Silakan coba lagi.");
                    flag = false;
                }
            }
        }
        
        

        scanner.close();
    }
}
