package Model;

public class Client {
    private int idClient;
    private String Nume;
    private String Prenume;
    private String Adresa;
    private String Email;
    private String Data_nasterii;

    public Client(int idClient) {
        this.idClient = idClient;
    }

    public Client(String nume, String prenume, String adresa, String email, String data_nasterii) {
        Nume = nume;
        Prenume = prenume;
        Adresa = adresa;
        Email = email;
        Data_nasterii = data_nasterii;
    }

    public Client(int idClient, String nume, String prenume, String adresa, String email, String data_nasterii) {
        this.idClient = idClient;
        Nume = nume;
        Prenume = prenume;
        Adresa = adresa;
        Email = email;
        Data_nasterii = data_nasterii;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getData_nasterii() {
        return Data_nasterii;
    }

    public void setData_nasterii(String data_nasterii) {
        Data_nasterii = data_nasterii;
    }
}
