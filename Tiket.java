public class Tiket {
    private Film film;
    private String jamTayang;
    private Kursi kursi;
    private double totalHarga;

    public Tiket(Film film, String jamTayang, Kursi kursi, double totalHarga) {
        this.film = film;
        this.jamTayang = jamTayang;
        this.kursi = kursi;
        this.totalHarga = totalHarga;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getJamTayang() {
        return jamTayang;
    }

    public void setJamTayang(String jamTayang) {
        this.jamTayang = jamTayang;
    }

    public Kursi getKursi() {
        return kursi;
    }

    public void setKursi(Kursi kursi) {
        this.kursi = kursi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    // === Method baru sesuai UML ===
    public void tampilkanDetail() {
        System.out.println("===== Detail Tiket =====");
        if (film != null) {
            System.out.println("Judul Film   : " + film.getJudul());
            System.out.println("Genre        : " + film.getGenre());
            System.out.println("Durasi       : " + film.getDurasi() + " menit");
            System.out.println("Harga Film   : Rp" + film.getHarga());
        }
        System.out.println("Jam Tayang   : " + this.getJamTayang());

        if (kursi != null) {
            System.out.println("Nomor Kursi  : " + kursi.getNomorKursi());
        }

        System.out.println("Total Harga  : Rp" + totalHarga);
        System.out.println("=========================");
    }

}
