import java.util.ArrayList;
import java.util.Scanner;

public class BioskopSystem {
    private ArrayList<User> daftarUsers;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Tiket> daftarTiket;
    private User currentUser;
    private Kursi[][] kursiBioskop;
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    public BioskopSystem(ArrayList<User> daftarUsers, ArrayList<Film> daftarFilm, ArrayList<Tiket> daftarTiket, Kursi[][] kursiBioskop,
            User currentUser) {
        this.daftarUsers = daftarUsers;
        this.daftarFilm = daftarFilm;
        this.daftarTiket = daftarTiket;
        this.currentUser = currentUser;
        this.kursiBioskop = kursiBioskop;
    }

    public ArrayList<User> getDaftarUsers() {
        return daftarUsers;
    }
    public ArrayList<Film> getDaftarFilm() {
        return daftarFilm;
    }
    public void setDaftarFilm(ArrayList<Film> daftarFilm) {
        this.daftarFilm = daftarFilm;
    }
    public ArrayList<Tiket> getDaftarTiket() {
        return daftarTiket;
    }
    public void setDaftarTiket(ArrayList<Tiket> daftarTiket) {
        this.daftarTiket = daftarTiket;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void mulai() {
        System.out.println("Selamat datang di Sistem Bioskop!");
        System.out.println("Silakan login untuk melanjutkan.");
    }

    public boolean login(String inputUsername, String inputPassword) {

    for (User user : daftarUsers) {
        if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
            currentUser = user;
            System.out.println("Login berhasil! Selamat datang, " + currentUser.getUsername() + ".");
            return true; 
        }
    }

    System.out.println("Login gagal! Username atau password salah.");
    return false; 
}
    public void tambahFilm(Scanner scanner) {
        System.out.println("Masukkan judul film:");
        String judul = scanner.nextLine();
        System.out.println("Masukkan genre film:");
        String genre = scanner.nextLine();
        System.out.println("Masukkan durasi film (dalam menit):");
        int durasi = Integer.parseInt(scanner.nextLine());
        System.out.println("Masukkan harga tiket film:");
        double harga = Double.parseDouble(scanner.nextLine());

        Film filmBaru = new Film(judul, genre, durasi, harga);
        daftarFilm.add(filmBaru);
        System.out.println("Film " + judul + " berhasil ditambahkan.");
    }

    public void lihatDaftarFilm() {
        System.out.println("=== Daftar Film ===");
        for (Film film : daftarFilm) {
            film.tampilkanDetail();
        }
    }

    public void menuAdmin(Scanner scanner) {
        System.out.println("=== Menu Admin ===");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat Daftar Film");
        System.out.println("3. Logout");

        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                tambahFilm(scanner);
                break;
            
            case "2":
                lihatDaftarFilm();
                break;
            
            case "3":
                return;
        
        }
    }
    
    
    public void beliTiket(Scanner scanner) {
        
        if (daftarFilm.isEmpty()) {
            System.out.println("Maaf, tidak ada film yang tersedia saat ini.");
            return;
        }
        System.out.println("=== Daftar Film ===");
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul());
        }

        System.out.println("Pilih nomor film:");
        
        int pilihan;

        try {
        pilihan = Integer.parseInt(scanner.nextLine());
    }   catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Silakan masukkan nomor film.");
            return;
        }
        
        if (pilihan < 1 || pilihan > daftarFilm.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        
        Film filmTerpilih = daftarFilm.get(pilihan - 1);
        filmTerpilih.tampilkanDetail();

        System.out.println("===== Layout Kursi =====");
        
        tampilkanLayoutKursi(daftarFilm.get(pilihan - 1));

        System.out.println("Masukkan nomor kursi yang ingin dipilih (format: baris-kolom, misal 1-2):");
        
        String nomorKursi;

        try {
        nomorKursi = scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Input tidak valid. Silakan masukkan nomor kursi dengan format yang benar.");
            return;
        }

        Kursi kursiTerpilih = null;
        for (Kursi kursi : filmTerpilih.getDaftarKursi()) {
            if (kursi.getNomorKursi().equals(nomorKursi)) {
                kursiTerpilih = kursi;
                break;
            }
        }

        if (kursiTerpilih == null) {
            System.out.println("Nomor kursi tidak valid.");
            return;
        }
        if (!kursiTerpilih.isTersedia()) {
            System.out.println("Kursi sudah dipesan. Silakan pilih kursi lain.");
            return;
        }

    }

    public void tampilkanLayoutKursi(Film film) {
    Kursi[][] layout = film.getKursiBioskop();

    System.out.println("===== Layout Kursi " + film.getJudul() + " =====");
    for (int i = 0; i < layout.length; i++) { // baris
        for (int j = 0; j < layout[i].length; j++) { // kolom
            Kursi k = layout[i][j];
            if (k.isTersedia()) {
                System.out.print(GREEN + "[" + k.getNomorKursi() + "] " + RESET);
            } else {
                System.out.print(RED + "[" + k.getNomorKursi() + "] " + RESET);
            }
        }
        System.out.println();
    }
    System.out.println("==============================");
    }

    public void menuUser(Scanner scanner) {
        System.out.println("=== Menu User ===");
        System.out.println("1. Lihat Daftar Film");
        System.out.println("2. Beli Tiket");
        System.out.println("3. Riwayat Pembelian");
        System.out.println("4. Logout");

        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                lihatDaftarFilm();
                break;
            
            case "2":
                beliTiket(scanner);
                break;
            
            case "3":
                lihatRiwayatPembelian();
                break;
            
            case "4":
                return;
        
        }
    }
}
