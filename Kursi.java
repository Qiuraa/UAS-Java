public class Kursi {
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
}
