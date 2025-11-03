import java.util.ArrayList;

public class Film {
    private int idFilm;
    private String judul;
    private String genre;
    private int durasi; // dalam menit
    private double harga;
    private ArrayList<Kursi> daftarKursi; // relasi 1 film memiliki banyak kursi

    public Film(int idFilm, String judul, String genre, int durasi, double harga) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.harga = harga;
        this.daftarKursi = new ArrayList<>();
    }

    // === Getter & Setter ===
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public ArrayList<Kursi> getDaftarKursi() {
        return daftarKursi;
    }

    // === Method tambahan sesuai relasi UML ===
    public void tambahKursi(Kursi kursi) {
        daftarKursi.add(kursi);
        System.out.println("Kursi " + kursi.getNomorKursi() + " ditambahkan ke film " + this.judul);
    }

    // === Method tampilkan detail ===
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
