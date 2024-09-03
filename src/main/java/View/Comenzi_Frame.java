package View;

import Controler.Controller;
import Model.Comanda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class Comenzi_Frame extends JFrame {

    private JPanel contentPane;
    private JTable tableProduse;
    private JTable tableComanda;
    private JTextField textFieldAddCantitate;
    private JTextField textFieldTakeCantitate;
    private JComboBox comboBoxClienti;
    private int Comanda_id = new Random().nextInt() % 1000;
    private JButton btntake;
    private JButton btnAdd;
    private JButton btnComanda;


    /**
     * Create the frame.
     */
    public Comenzi_Frame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 728, 432);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 11, 242, 352);
        contentPane.add(scrollPane_1);


        tableProduse = new JTable();
        tableProduse.getTableHeader().setReorderingAllowed(false);
        tableProduse.setModel(Controller.buildTable("Produs"));
        tableProduse.removeColumn(tableProduse.getColumnModel().getColumn(0));
        tableProduse.setAutoCreateRowSorter(true);
        scrollPane_1.setViewportView(tableProduse);


        comboBoxClienti = new JComboBox();
        comboBoxClienti.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        comboBoxClienti.setBounds(272, 307, 169, 56);
        comboBoxClienti.setModel(Controller.buildList("Client"));
        contentPane.add(comboBoxClienti);


        JScrollPane scrollPane_1_1 = new JScrollPane();
        scrollPane_1_1.setBounds(472, 11, 232, 352);
        contentPane.add(scrollPane_1_1);


        tableComanda = new JTable();
        tableComanda.getTableHeader().setReorderingAllowed(false);
        String client = (String) comboBoxClienti.getModel().getElementAt(0);
        String[] v = client.split(":");
        int idClient = Integer.valueOf(v[0]);
        tableComanda.setModel(Controller.buildCos(idClient));
        tableComanda.removeColumn(tableComanda.getColumnModel().getColumn(0));
        tableComanda.removeColumn(tableComanda.getColumnModel().getColumn(0));
        tableComanda.setAutoCreateRowSorter(true);
        scrollPane_1_1.setViewportView(tableComanda);


        textFieldAddCantitate = new JTextField();
        textFieldAddCantitate.setBounds(272, 11, 169, 20);
        contentPane.add(textFieldAddCantitate);
        textFieldAddCantitate.setColumns(10);


        btnAdd = new JButton("Adauga Produs");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAdd.setBounds(272, 38, 169, 56);
        contentPane.add(btnAdd);



        btntake = new JButton("Scoate Produs");
        btntake.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btntake.setBounds(272, 141, 169, 56);
        contentPane.add(btntake);



        JLabel lblNewLabel = new JLabel("Client");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel.setBounds(272, 278, 37, 18);
        contentPane.add(lblNewLabel);



        btnComanda = new JButton("Comanda  Produse");
        btnComanda.setBounds(272, 208, 169, 40);
        contentPane.add(btnComanda);


        select_client_functionality();
        delete_functionality();
        add_functionality();
        order_functionality();
    }

    public void select_client_functionality() {
        comboBoxClienti.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    e.getItem();
                    String client = (String) comboBoxClienti.getSelectedItem();
                    String[] v = client.split(":");
                    int idClient = Integer.valueOf(v[0]);
                    tableComanda.setModel(Controller.buildCos(idClient));
                    tableComanda.removeColumn(tableComanda.getColumnModel().getColumn(0));
                    tableComanda.removeColumn(tableComanda.getColumnModel().getColumn(0));

                }
            }
        });
    }

    public void delete_functionality() {
        btntake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableComanda.getSelectedRow();
                int id = (int) tableComanda.getModel().getValueAt(row, 0);
                Comanda com = new Comanda(id);
                Controller.getSingeController().DeleteObject(tableComanda, com);
            }
        });
    }

    public void add_functionality() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getSingeController().InsertComanda();
            }
        });
    }

    public void order_functionality() {
        btnComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getSingeController().bon();
            }
        });
    }




    public int getComanda_id() {
        return Comanda_id;
    }

    public void setComanda_id(int comanda_id) {
        Comanda_id = comanda_id;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JTable getTableProduse() {
        return tableProduse;
    }

    public JTextField getTextFieldAddCantitate() {
        return textFieldAddCantitate;
    }

    public JTextField getTextFieldTakeCantitate() {
        return textFieldTakeCantitate;
    }

    public JComboBox getComboBoxClienti() {
        return comboBoxClienti;
    }

    public JTable getTableComanda() {
        return tableComanda;
    }
}
