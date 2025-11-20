public class TiketReguler extends Tiket {
    public TiketReguler(Film film, String jamTayang, Kursi kursi, double totalHarga) {
        super(film, jamTayang, kursi, 0);
    }

    @Override
    public double hitungTotalHarga(double hargaFilm) {
        this.totalHarga = hargaFilm; // Tidak ada tambahan biaya untuk tiket reguler
        return this.totalHarga;
    }
    
}
