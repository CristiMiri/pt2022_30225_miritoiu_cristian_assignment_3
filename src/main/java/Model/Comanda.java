package Model;

public class Comanda {
    private int idComanda;
    private int Produs_Id;
    private String nume;
    private int Client_Id;
    private int Cantitate;
    private double Pret;

    public Comanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public Comanda(int idComanda, int produs_Id, String nume, int client_Id, int cantitate, double pret) {
        this.idComanda = idComanda;
        Produs_Id = produs_Id;
        this.nume = nume;
        Client_Id = client_Id;
        Cantitate = cantitate;
        Pret = pret;
    }

    public int getId_Comanda() {
        return idComanda;
    }

    public void setId_Comanda(int id_Comanda) {
        id_Comanda = id_Comanda;
    }

    public int getId_Produs() {
        return idComanda;
    }

    public void setId_Produs(int id_Produs) {
        id_Produs = id_Produs;
    }

    public int getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(int client_Id) {
        Client_Id = client_Id;
    }

    public double getPret() {
        return Pret;
    }

    public void setPret(double pret) {
        Pret = pret;
    }

    public int getCantitate() {
        return Cantitate;
    }

    public void setCantitate(int cantitate) {
        Cantitate = cantitate;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getProdus_Id() {
        return Produs_Id;
    }

    public void setProdus_Id(int produs_Id) {
        Produs_Id = produs_Id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


}
