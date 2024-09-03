package Model;

public class Produs {
    private int idProdus;
    private String Nume;
    private int Stoc;
    private double Pret;
    private String Producator;

    public Produs(int idProdus) {
        this.idProdus = idProdus;
    }

    public Produs(int idProdus, String nume, int stoc, double pret, String producator) {
        this.idProdus = idProdus;
        Nume = nume;
        Stoc = stoc;
        Pret = pret;
        Producator = producator;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public int getStoc() {
        return Stoc;
    }

    public void setStoc(int stoc) {
        Stoc = stoc;
    }

    public double getPret() {
        return Pret;
    }

    public void setPret(double pret) {
        Pret = pret;
    }

    public String getProducator() {
        return Producator;
    }

    public void setProducator(String producator) {
        Producator = producator;
    }
}
