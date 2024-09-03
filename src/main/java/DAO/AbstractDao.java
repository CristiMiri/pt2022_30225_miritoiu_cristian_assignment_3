package DAO;

import Model.Comanda;
import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDao {
    /**
     * Metoda table_get_all este utilizata pentru a retuna toate randurile din cadrul tabelei ce este data ca paramentru string .
     * Acesta utilizeaza conectionfactory pentru facilizarea operatiei de select asupra bazei de date
     * @param  table string tabel din care se selecteaza
     * @return result set continand toate randurile din tabel.
     */
    public static ResultSet table_get_all(String table) {
        String query = "Select * from orders_management." + table + " ;";
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Metoda insertQuery construieste string de inserarea o unui obiect pe baza paramentrului table.
     * @param table tabela de inseray
     * @return string constind queryul de inserare
     */
    private String insertQuery(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into orders_management.");
        sb.append(table);
        switch (table) {
            case "Client" -> {
                sb.append("(client.Nume,client.Prenume,client.Adresa,client.Email,client.Data_nasterii) ");
                sb.append("values(?,?,?,?,?);");
            }
            case "Produs" -> {
                sb.append("(produs.Nume,produs.Stoc,produs.Pret,produs.Producator) ");
                sb.append("values(?,?,?,?);");
            }
            case "Comanda" -> {
                sb.append("(comanda.IdComanda,comanda.Produs_Id,comanda.Nume, comanda.Client_Id,comanda.Cantitate,comanda.Pret) ");
                sb.append("values(?,?,?,?,?,?);");
            }
        }
        return sb.toString();
    }

    /**Metoda deleteQuery construieste string de stergere o unui obiect pe baza paramentrului table si al unui Id care se va alege ulterior.
     * @param table tabela din care se sterge
     * @return string constind queryul
     */
    private String deleteQuery(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("Delete from orders_management.");
        sb.append(table);
        sb.append(" WHERE Id" + table + " =?");
        return sb.toString();
    }

    /**
     * Metoda updateQuery construieste string de update o unui obiect pe baza paramentrului table si al unui Id care se va alege ulterior.
     * @param table tabel pentru operatie
     * @return tring constind queryul de inserare
     */
    private String updateQuery(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("update orders_management.");
        sb.append(table);
        switch (table) {
            case "Client" -> {
                sb.append(" set Nume= ? \n, ");
                sb.append("Prenume= ? \n, ");
                sb.append("Adresa= ? \n, ");
                sb.append("Email= ? \n, ");
                sb.append("orders_management.client.Data_nasterii= ? \n");
            }
            case "Produs" -> {
                sb.append(" set Nume=? ,\n");
                sb.append("Stoc=? ,\n");
                sb.append("Pret=? ,\n");
                sb.append("Producator=? \n");
            }
        }
        sb.append(" WHERE id" + table + " = ? ;");
        return sb.toString();

    }

    /**
     * Metoda CosClient construieste string pentru a selecta toate comenzile unui client pe baza unui id.
     * @return string query
     */
    private String CosClient() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("Comanda");
        sb.append(" WHERE orders_management.comanda.Client_Id =?");
        return sb.toString();
    }

    public ResultSet tableCos(int Clientid) {
        String query = CosClient();
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Clientid);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     *Metoda deleteObject foloseste reflexie pentru a vizualiza toate campul de id al unui obiect si in folosete pentru a sterge instanta respectiva din tabel folosind stringul din deleteQuery.
     * @param o obiect de sters
     */
    public void delete(Object o) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = deleteQuery(o.getClass().getSimpleName());
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            fields[0].setAccessible(true);
            Object value = fields[0].get(o);
            statement.setInt(1, (int) value);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    /**Metoda insert foloseste reflexie pentru a insera obiecte in baza de date .
     * Aceaste metoda se uita la campurile din cadrul obiectului si in functie de tipul acestora
     * le pune in statement pentru indexul respectiv in stringul query din insertQuery ..
     * @param o obiectul de inserat.
     */
    public void insert(Object o) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = insertQuery(o.getClass().getSimpleName());
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int index = 1;
            Object value;
            for (Field f : fields) {
                f.setAccessible(true);
                value = f.get(o);
                System.out.println(value.toString());
                if (!(o instanceof Comanda))
                    if (f.getName().contains("id"))
                        continue;

                if (value instanceof Integer)
                    statement.setInt(index, (int) value);
                else if (value instanceof String)
                    statement.setString(index, (String) value);
                else if (value instanceof Double)
                    statement.setDouble(index, (Double) value);
                index++;
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     *Metoda update foloseste reflexie fiind asemanatoarea cu metoda de insert
     * insa folosind campul id pentru a identifica instanta de update in baza de date.
     * @param o obiect de updatat
     */
    public void update(Object o) {
        Connection connection;
        PreparedStatement statement;
        String query = updateQuery(o.getClass().getSimpleName());
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(query);
            int index = 1;
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(o);
                if (f.getName().contains("id"))
                    continue;
                if (value instanceof Integer)
                    statement.setInt(index, (int) value);
                else if (value instanceof String)
                    statement.setString(index, (String) value);
                else if (value instanceof Double)
                    statement.setDouble(index, (Double) value);
                index++;
            }
            statement.setInt(index, (int) fields[0].get(o));
            statement.execute();
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
}


