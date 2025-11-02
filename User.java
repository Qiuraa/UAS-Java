import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String role;
    private ArrayList<Tiket> riwayatTiket;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.riwayatTiket = new ArrayList<>();
    }

    public boolean login() {
        System.out.println("Mencoba login sebagai " + this.username);
        return true; 
    }

    public void tambahTiket(Tiket tiket) {
        this.riwayatTiket.add(tiket);
        System.out.println("Tiket berhasil ditambahkan ke riwayat " + this.username);
    }

    public void tampilRiwayat() {
        System.out.println("--- Riwayat Tiket " + this.username + " ---");
        if (riwayatTiket.isEmpty()) {
            System.out.println("Belum ada riwayat pembelian tiket.");
        } else {
            for (Tiket tiket : riwayatTiket) {
                tiket.tampilkanDetail();
                System.out.println("--------------------");
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<Tiket> getRiwayatTiket() {
        return riwayatTiket;
    }
}
