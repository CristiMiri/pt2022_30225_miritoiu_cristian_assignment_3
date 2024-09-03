package Controler;

import DAO.AbstractDao;
import Model.Client;
import Model.Comanda;
import Model.Produs;
import View.Client_add_edit_Frame;
import View.Comenzi_Frame;
import View.Produs_add_edit_Frame;
import View.Select_Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    private static Controller singeController = new Controller();
    private Select_Frame select_frame;
    private static AbstractDao a;

    /**
     * Metoda buildCos construieste un model de tabela pe baza datelor oferite din result se in cadrul metodei respective din AbstractDao .
     * Pentru fiecare coloana din result set acesta este adaugata in tabela .Pentru fiecare rand din resultset se creaza o lista de obiecte de tipuri generice ce sunt introduse in tabela.
     * @param Clientid client pentru care se genereaza tabela de comenzi
     * @return modelul de date pentru tabela.
     */
    public static DefaultTableModel buildCos(int Clientid)
    {
        DefaultTableModel model = new DefaultTableModel();

        try {
            ResultSet resultSet = a.tableCos(Clientid);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                model.addColumn(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                List<Object> objects = new ArrayList<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++)
                    objects.add(resultSet.getObject(i));
                model.addRow(objects.toArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Metoda buildTable este metoda principala in cadrul contruirii tabelor din cadrul interfetelor . Se creaza un result set prin apelarea metodei build_all din abstractDao ce returnezaa toate datele din tabela .
     * Pe baza coloanelor returnate se adauga coloanele respectiva in tabela . Pentru fiecare rand din result set se creaza o lista de obiecte generice nesesare pentru a crea un rand in tabela din interfata .
     * Metoda returneaza modelul de date al tablei ce a fost selectata .
     * @param Table tabela pentru care se geneaza randurile
     * @return modelul de date al tabelei
     */
    public static DefaultTableModel buildTable(String Table) {
        DefaultTableModel model = new DefaultTableModel();

        try {
            ResultSet resultSet = AbstractDao.table_get_all(Table);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                model.addColumn(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                List<Object> objects = new ArrayList<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++)
                    objects.add(resultSet.getObject(i));
                model.addRow(objects.toArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Metoda buildList construieste un model pentru combolistul folosit pentru a selecta clienti la care se adauga comenzi . Fiecare instanta din lista este construita din Id , Nume , Prenume pentru fiecare client din cadrul unui result set ce selecteaza toate datele despre clienti din baza de date .
     * @param Table tabela de client
     * @return modelul de date al unei liste
     */
    public static DefaultComboBoxModel buildList(String Table) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            ResultSet resultSet = AbstractDao.table_get_all(Table);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nume = resultSet.getString("Nume");
                String prenume = resultSet.getString("Prenume");
                String client = id + ": " + nume + " " + prenume;
                Client c = new Client(id);
                c.setNume(nume);
                c.setPrenume(prenume);
                model.addElement(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Metoda getSingeController returneaza singura instanta a obiectului Controller.
     * @return uinca instrantiere a obiectului controller
     */
    public static Controller getSingeController() {
        return singeController;
    }

    /**
     * Porneste programul
     */
    public void start() {
        select_frame = new Select_Frame();
        select_frame.setVisible(true);
        a = new AbstractDao();
    }

    /**
     * Metoda insertClient creaza un obiect Client cu datele din cadrul interfetei si apeleaza insert din AbstractDao .
     * Se updateaza tabela de clienti si disparea fereastra de inserare .
     */
    public void InsertClient() {
        Client_add_edit_Frame f = select_frame.getClienti_frame().getClient_add_frame();
        StringBuilder sb = new StringBuilder();
        sb.append(f.getComboBoxLuna().getSelectedItem() + "-");
        sb.append(f.getComboBoxAn().getSelectedItem() + "-");
        sb.append(f.getComboBoxZi().getSelectedItem());
        Client c = new Client(0, null, null, null, null, null);
        c.setNume(f.getTextFieldNume().getText());
        c.setPrenume(f.getTextFieldPrenume().getText());
        c.setEmail(f.getTextFieldEmail().getText());
        c.setAdresa(f.getTextFieldAdresa().getText());
        c.setData_nasterii(sb.toString());
        a.insert(c);
        JTable jTable = select_frame.getClienti_frame().getTable();
        jTable.setModel(Controller.buildTable("Client"));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        f.dispose();
    }

    /**\
     * Metoda insertProdus creaza un obiect produs cu datele din cadrul interfetei si apeleaza insert din AbstractDao .
     * Se updateaza tabela de clienti si disparea fereastra de inserare
     */
    public void InsertProdus() {
        Produs_add_edit_Frame f = select_frame.getProduse_frame().getProdus_add_frame();
        String nume = f.getTextFieldNume().getText();
        String producator = f.getTextFieldProdus().getText();
        float pret = Float.valueOf(f.getTextFieldPret().getText());
        int stoc = Integer.parseInt(f.getTextFieldStoc().getText());
        Produs p = new Produs(-1, nume, stoc, pret, producator);
        a.insert(p);
        JTable jTable = select_frame.getProduse_frame().getTable();
        jTable.setModel(Controller.buildTable("Produs"));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        f.dispose();
    }

    /**
     * Metoda InsertComanda creaza un obiect comanda pe baza clientului selectat si produsului selectat din tabela acestea avand un id generat automat pentru fiecare client si apeleaza insert din AbstractDao .
     * Tabela de comenzi se va actualiza dupa inserare .
     */
    public void InsertComanda()
    {
        Comenzi_Frame f=select_frame.getComenzi_frame();
        String client= (String) f.getComboBoxClienti().getSelectedItem();
        String[] v=client.split(":");
        int idClient=Integer.valueOf(v[0]);
        int row=f.getTableProduse().getSelectedRow();
        int idProdus= (int) f.getTableProduse().getModel().getValueAt(row,0);
        String nume=(String) f.getTableProduse().getModel().getValueAt(row,1);
        int idComanda=f.getComanda_id();
        if(idComanda<0)
            idComanda=-idComanda;
        System.out.println(idComanda);
        float pretunitar=(float)f.getTableProduse().getModel().getValueAt(row,3);
        int cantitate=Integer.valueOf(f.getTextFieldAddCantitate().getText());
        float pret=pretunitar*cantitate;
        Comanda com=new Comanda(idComanda,idProdus,nume,idClient,cantitate,pret);
        a.insert(com);
        f.setComanda_id(new Random().nextInt() % 1000);
        JTable jTable = f.getTableComanda();
        jTable.setModel(Controller.buildCos(com.getClient_Id()));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
    }

    /**
     * Metoda bon genereaza un fisier text pe baza clientului selecta in cadrul interfetei .
     * Aceasta metoda preia din tabela de comendi informatiile relevante si le scrie in fisier .
     * La final se calculeaza totalul comenziilor si se scrie in fisier.
     */
    public void bon()
    {
        Comenzi_Frame f=select_frame.getComenzi_frame();
        String client= (String) f.getComboBoxClienti().getSelectedItem();
        String[] v=client.split(":");
        String idClient=v[0];
        JTable jTable = f.getTableComanda();
        float total=0;
        try {
            FileWriter writer=new FileWriter("bon.txt");
            writer.append("Client: "+idClient+'\n');
            writer.append("Produse : \n");
            for(int i=0;i<jTable.getRowCount();i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(jTable.getModel().getValueAt(i, 2)+": ");
                sb.append(jTable.getModel().getValueAt(i,5)+" x");
                sb.append(jTable.getModel().getValueAt(i,4)+"\n");
                total+=(float) jTable.getModel().getValueAt(i,5);
                writer.append(sb);
            }
            writer.append(String.format("Total %.2f",total));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda DeleteObject primeste tabela din care se sterge si un obiect dummy utilizat pentru stergere .
     * Pe baza clasei obiectului se crea un dummy object utilizand date din cadrul tabelei si apeleaza functia de delete a lui abstractDao .
     * Dupa stergere se updateaza tabela .
     * @param jTable tabela din care se sterge
     * @param o dummy object de sters.
     */
    public void DeleteObject(JTable jTable, Object o) {
        if (o instanceof Client) {
            Client c = (Client) o;
            int row = jTable.getSelectedRow();
            c.setIdClient(Integer.parseInt(jTable.getModel().getValueAt(row, 0).toString()));
            a.delete(c);
        } else if (o instanceof Produs) {
            Produs p = (Produs) o;
            int row = jTable.getSelectedRow();
            p.setIdProdus(Integer.parseInt(jTable.getModel().getValueAt(row, 0).toString()));
            a.delete(p);
        } else {
            Comanda com = (Comanda) o;
            int row = jTable.getSelectedRow();
            com.setId_Comanda(Integer.parseInt(jTable.getModel().getValueAt(row, 0).toString()));
            a.delete(com);
        }
        jTable.setModel(Controller.buildTable(o.getClass().getSimpleName()));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
    }


    /**
     * Metoda EditClient creaza un dummyobject  cu campurile introduse in cadrul interfetei si apeleaza metoda update din AbstractDao .
     * Apoi updateaza tabela de clienti .
     */
    public void EditClient() {
        Client_add_edit_Frame f = select_frame.getClienti_frame().getClient_edit_frame();
        StringBuilder sb = new StringBuilder();
        sb.append(f.getComboBoxLuna().getSelectedItem() + "-");
        sb.append(f.getComboBoxAn().getSelectedItem() + "-");
        sb.append(f.getComboBoxZi().getSelectedItem());
        Client c = new Client(f.getClient().getIdClient());
        c.setNume(f.getTextFieldNume().getText());
        c.setPrenume(f.getTextFieldPrenume().getText());
        c.setEmail(f.getTextFieldEmail().getText());
        c.setAdresa(f.getTextFieldAdresa().getText());
        c.setData_nasterii(sb.toString());
        a.update(c);
        JTable jTable = select_frame.getClienti_frame().getTable();
        jTable.setModel(Controller.buildTable("Client"));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        f.dispose();
    }

    /**
     * Metoda EditProdus creaza un dummyobject  cu campurile introduse in cadrul interfetei si apeleaza metoda update din AbstractDao .
     * Apoi updateaza tabela de produse .
     */
    public void EditProdus() {
        Produs_add_edit_Frame f = select_frame.getProduse_frame().getProdus_edit_frame();
        String nume = f.getTextFieldNume().getText();
        String producator = f.getTextFieldProdus().getText();
        double pret = Double.valueOf(f.getTextFieldPret().getText());
        int stoc = Integer.parseInt(f.getTextFieldStoc().getText());
        Produs p = new Produs(f.getProdus().getIdProdus(), nume, stoc, pret, producator);
        a.update(p);
        JTable jTable = select_frame.getProduse_frame().getTable();
        jTable.setModel(Controller.buildTable("Produs"));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        f.dispose();

    }
}

