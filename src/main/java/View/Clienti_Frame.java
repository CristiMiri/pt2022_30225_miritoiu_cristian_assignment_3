package View;

import Controler.Controller;
import Model.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clienti_Frame extends JFrame {

    Client_add_edit_Frame client_add_frame;
    Client_add_edit_Frame client_edit_frame;
    private JPanel contentPane;
    private JTable table;

    /**
     * Create the frame.
     */
    public Clienti_Frame() {
        setTitle("Clienti");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 630, 418);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 34, 400, 336);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setDragEnabled(true);
        table.setAutoCreateRowSorter(true);
        table.setGridColor(Color.getHSBColor(184, 94, 98));
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        table.setModel(Controller.buildTable("Client"));
        table.removeColumn(table.getColumnModel().getColumn(0));
        JButton btnAddClient = new JButton("Add Client");
        btnAddClient.setBackground(Color.YELLOW);
        btnAddClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddClient.setFocusPainted(false);
        btnAddClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnAddClient.setBounds(420, 55, 150, 50);
        btnAddClient.addActionListener(e -> {
            client_add_frame = new Client_add_edit_Frame( null);
            client_add_frame.setVisible(true);
        });
        contentPane.add(btnAddClient);
        JButton btnDelClient = new JButton("Delete Client");
        btnDelClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnDelClient.setFocusPainted(false);
        btnDelClient.setBackground(Color.YELLOW);
        btnDelClient.setBounds(420, 134, 150, 50);
        btnDelClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getSingeController().DeleteObject(table, new Client(-1));
            }
        });
        contentPane.add(btnDelClient);

        JButton btnEditClient = new JButton("Edit Client");
        btnEditClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnEditClient.setFocusPainted(false);
        btnEditClient.setBackground(Color.YELLOW);
        btnEditClient.setBounds(420, 221, 150, 50);
        btnEditClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int id = (int) table.getModel().getValueAt(row, 0);
                String nume = (String) table.getModel().getValueAt(row, 1);
                String prenume = (String) table.getModel().getValueAt(row, 2);
                String email = (String) table.getModel().getValueAt(row, 3);
                String adresa = (String) table.getModel().getValueAt(row, 4);
                String data_nasterii = (String) table.getModel().getValueAt(row, 5).toString();
                Client c = new Client(id, nume, prenume, email, adresa, data_nasterii);
                client_edit_frame = new Client_add_edit_Frame(c);
                client_edit_frame.setVisible(true);
            }
        });
        contentPane.add(btnEditClient);
    }

    public Client_add_edit_Frame getClient_add_frame() {
        return client_add_frame;
    }

    public Client_add_edit_Frame getClient_edit_frame() {
        return client_edit_frame;
    }

    public JTable getTable() {
        return table;
    }
}
