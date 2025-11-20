import java.util.ArrayList;
import java.util.Scanner;

public class BioskopSystem {
    private ArrayList<User> daftarUsers;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Tiket> daftarTiket;
    private User currentUser;
    
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public BioskopSystem() {
        this.daftarUsers = new ArrayList<>();
        this.daftarFilm = new ArrayList<>();
        this.daftarTiket = new ArrayList<>();
        this.currentUser = null;
    }

    public ArrayList<User> getDaftarUsers() {
        return daftarUsers;
    }
    public ArrayList<Film> getDaftarFilm() {
        return daftarFilm;
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
        System.out.println("------------------------------");
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

    public void tambahUserBaru(Scanner scanner) {
        System.out.println("\n=== Tambah User Baru ===");
        
        String username;
        while (true) {
            System.out.print("Masukkan Username Baru: ");
            username = scanner.nextLine();
            if (username.trim().isEmpty()) {
                System.out.println("Username tidak boleh kosong.");
                continue;
            }
            
            boolean exists = false;
            for (User u : daftarUsers) {
                if (u.getUsername().equals(username)) {
                    exists = true;
                    break;
                }
            }
            
            if (exists) {
                System.out.println("Username sudah terpakai! Pilih yang lain.");
            } else {
                break;
            }
        }

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        String role = "customer";

        User newUser = new User(username, password, role);
        daftarUsers.add(newUser);
        System.out.println("Berhasil membuat user baru: " + username + " sebagai " + role);
    }

    public void tambahFilm(Scanner scanner) {
        System.out.print("Masukkan judul film :");
        String judul;
        do {
            judul = scanner.nextLine();
            if (judul.trim().isEmpty()) System.out.print("Judul tidak boleh kosong: ");
        } while (judul.trim().isEmpty());

        System.out.print("Masukkan genre film :");
        String genre = scanner.nextLine();
        
        System.out.print("Masukkan durasi film (menit) :");
        int durasi = 0;
        try {
            durasi = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) { durasi = 0; } 
        
        System.out.print("Masukkan harga tiket film :");
        double harga = 0;
        try {
            harga = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) { harga = 0; }

        Film filmBaru = new Film(judul, genre, durasi, harga, 5, 5); 
        daftarFilm.add(filmBaru);
        System.out.println("Film " + judul + " berhasil ditambahkan.");
    }

    public void hapusFilm(Scanner scanner) {
    if (daftarFilm.isEmpty()) {
        System.out.println("Tidak ada film yang dapat dihapus.");
        return;
    }

    System.out.println("=== Hapus Film ===");
    for (int i = 0; i < daftarFilm.size(); i++) {
        System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul());
    }

    System.out.print("Pilih nomor film yang akan dihapus: ");
    int pilihan = -1;
    try {
        pilihan = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Input harus berupa angka.");
        return;
    }

    if (pilihan < 1 || pilihan > daftarFilm.size()) {
        System.out.println("Pilihan tidak valid.");
        return;
    }

    Film filmDihapus = daftarFilm.remove(pilihan - 1);
    System.out.println("Film \"" + filmDihapus.getJudul() + "\" berhasil dihapus!");
    }


    public void lihatDaftarFilm() {
        System.out.println("=== Daftar Film ===");
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang tayang.");
        } else {
            for (Film film : daftarFilm) {
                film.tampilkanDetail();
            }
        }
    }

    public void menuAdmin(Scanner scanner) {
        while (true) { 
            System.out.println("\n=== Menu Admin (" + currentUser.getUsername() + ") ===");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Daftar Film");
            System.out.println("3. Tambah User/Admin Baru");
            System.out.println("4. Logout");

            System.out.print("Pilih menu : ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    tambahFilm(scanner);
                    break;
                case "2":
                    lihatDaftarFilm();
                    break;
                case "3":
                    tambahUserBaru(scanner);
                    break;
                case "4":
                    System.out.println("Logout berhasil!");
                    setCurrentUser(null);
                    return; 
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void lihatRiwayatPembelian() {
        currentUser.tampilRiwayat();
    }

    public void beliTiket(Scanner scanner) {
        if (daftarFilm.isEmpty()) {
            System.out.println("Maaf, tidak ada film yang tersedia saat ini.");
            return;
        }
        System.out.println("=== Pilih Film ===");
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul());
        }
        System.out.print("Pilih nomor film: ");
        
        int pilihan = -1;
        try {
            pilihan = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input angka dong.");
            return;
        }
        
        if (pilihan < 1 || pilihan > daftarFilm.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        
        Film filmTerpilih = daftarFilm.get(pilihan - 1);
        filmTerpilih.tampilkanDetail();

        tampilkanLayoutKursi(filmTerpilih);

        System.out.print("Masukkan nomor kursi (misal 1-2): ");
        String nomorKursi = scanner.nextLine();

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
            System.out.println("Kursi sudah dipesan. Telat lu bang.");
            return;
        }

        // Proses beli
        kursiTerpilih.setTersedia(false);
        Tiket tiketBaru = new Tiket(filmTerpilih, "20:00", kursiTerpilih, filmTerpilih.getHarga());
        daftarTiket.add(tiketBaru);
        currentUser.tambahTiket(tiketBaru);
        System.out.println(GREEN + "Sukses! Tiket " + filmTerpilih.getJudul() + " kursi " + nomorKursi + " berhasil dibeli." + RESET);
    }

    public void tampilkanLayoutKursi(Film film) {
        Kursi[][] layout = film.getKursiBioskop();
        System.out.println("===== Layar Bioskop =====");
        for (int i = 0; i < layout.length; i++) { 
            for (int j = 0; j < layout[i].length; j++) { 
                Kursi k = layout[i][j];
                if (k.isTersedia()) {
                    System.out.print(GREEN + "[" + k.getNomorKursi() + "] " + RESET);
                } else {
                    System.out.print(RED + "[X] " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("=========================");
    }

    public void menuUser(Scanner scanner) {
        while(true) { 
            System.out.println("\n=== Selamat Datang " + currentUser.getUsername() + " ===");
            System.out.println("1. Lihat Daftar Film");
            System.out.println("2. Beli Tiket");
            System.out.println("3. Riwayat Pembelian");
            System.out.println("4. Logout");
    
            System.out.print("Pilih menu : ");
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
                    System.out.println("Logout berhasil!");
                    setCurrentUser(null);
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
