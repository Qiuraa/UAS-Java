import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BioskopSystem bioskopSystem = new BioskopSystem();
        while (true) {
            bioskopSystem.mulai();
            bioskopSystem.getDaftarUsers().add(new User("user1", "pass1", "customer"));
            bioskopSystem.getDaftarUsers().add(new User("admin", "adminpass", "admin"));
            
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
                if (bioskopSystem.getCurrentUser().getRole().equals("admin")) {
                    System.out.println("Anda masuk sebagai admin.");

                    bioskopSystem.menuAdmin(scanner);

                if (bioskopSystem.getCurrentUser() == null) {
                    flag = false;

                }

                }

                else {
                    System.out.println("Selamat datang, " + bioskopSystem.getCurrentUser().getUsername() + "!");

                    bioskopSystem.menuUser(scanner);

                    if (bioskopSystem.getCurrentUser() == null) {
                        flag = false;
                    }

                }
            }
        }
        
        

        scanner.close();
    }
}
