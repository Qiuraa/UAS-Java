public class Kursi implements Reservasi {
    private String nomorKursi;
    private boolean tersedia;
    private Film film;

    public Kursi(String nomorKursi, Film film) {
        this.nomorKursi = nomorKursi;
        this.film = film;
        this.tersedia = true;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public Film getFilm() {
        return film;
    }

    @Override
    public void pesan(boolean tersedia) {
        // Use the object's field instead of the parameter to change availability
        if (this.tersedia) {
            this.tersedia = false;
            System.out.println("Kursi " + nomorKursi + " berhasil dipesan.");
        } else {
            System.out.println("Kursi " + nomorKursi + " sudah tidak tersedia.");
        }
    }

}
