import java.util.ArrayList;

public class Film {
    private int idFilm;
    private String judul;
    private String genre;
    private int durasi;
    private double harga;
    private ArrayList<Kursi> daftarKursi;
    private Kursi[][] kursiBioskop;

    // Konstruktor: input jumlah baris & kolom sebagai int
    public Film(int idFilm, String judul, String genre, int durasi, double harga, int baris, int kolom) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.harga = harga;

        this.daftarKursi = new ArrayList<>();
        this.kursiBioskop = new Kursi[baris][kolom];

        // Generate kursi otomatis
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                String nomor = (i+1) + "-" + (j+1);
                Kursi kursi = new Kursi(nomor, this);
                kursiBioskop[i][j] = kursi;
                daftarKursi.add(kursi);
            }
        }
    }
    public String getJudul() {
        return judul;
    }

    public double getHarga() {
        return harga;
    }
    public ArrayList<Kursi> getDaftarKursi() {
        return daftarKursi;
    }

    public Kursi[][] getKursiBioskop() {
        return kursiBioskop;
    }

    public void tampilkanDetail() {
        System.out.println("===== Detail Film =====");
        System.out.println("ID Film     : " + idFilm);
        System.out.println("Judul       : " + judul);
        System.out.println("Genre       : " + genre);
        System.out.println("Durasi      : " + durasi + " menit");
        System.out.println("Harga Tiket : Rp" + harga);
        System.out.println("Jumlah Kursi: " + daftarKursi.size());
        System.out.println("=========================");
    }
}
