package View;

import Controler.Controller;
import Model.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client_add_edit_Frame extends JFrame {

    JButton btnCreate;
    private JPanel contentPane;
    private JTextField textFieldNume;
    private JTextField textFieldPrenume;
    private JTextField textFieldAdresa;
    private JComboBox comboBoxZi;
    private JComboBox comboBoxAn;
    private JComboBox comboBoxLuna;
    private JTextField textFieldEmail;
    private String btn;
    private Client client;

    public Client_add_edit_Frame( Client c) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 354, 363);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNume = new JLabel("Nume\r\n");
        lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNume.setBounds(10, 34, 60, 15);
        contentPane.add(lblNume);

        JLabel lblPrenume = new JLabel("Prenume\r\n");
        lblPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblPrenume.setBounds(180, 35, 60, 15);
        contentPane.add(lblPrenume);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblEmail.setBounds(10, 107, 50, 15);
        contentPane.add(lblEmail);

        JLabel lblAdresa = new JLabel("Adresa");
        lblAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblAdresa.setBounds(180, 107, 50, 15);
        contentPane.add(lblAdresa);

        JLabel lblData = new JLabel("Data nasterii\r\n");
        lblData.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblData.setBounds(10, 169, 80, 15);
        contentPane.add(lblData);

        textFieldNume = new JTextField();
        textFieldNume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldNume.setBounds(10, 60, 126, 24);
        contentPane.add(textFieldNume);
        textFieldNume.setColumns(10);

        textFieldPrenume = new JTextField();
        textFieldPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldPrenume.setColumns(10);
        textFieldPrenume.setBounds(180, 61, 126, 24);
        contentPane.add(textFieldPrenume);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(10, 133, 126, 24);
        contentPane.add(textFieldEmail);

        textFieldAdresa = new JTextField();
        textFieldAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldAdresa.setColumns(10);
        textFieldAdresa.setBounds(180, 133, 126, 24);
        contentPane.add(textFieldAdresa);

        comboBoxZi = new JComboBox();
        comboBoxZi.setBackground(Color.LIGHT_GRAY);
        comboBoxZi.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        comboBoxZi.setToolTipText("Zi");
        comboBoxZi.setBounds(10, 205, 40, 24);
        contentPane.add(comboBoxZi);

        comboBoxAn = new JComboBox();
        comboBoxAn.setBackground(Color.LIGHT_GRAY);
        comboBoxAn.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
        comboBoxAn.setToolTipText("Luna");
        comboBoxAn.setBounds(80, 205, 85, 24);
        contentPane.add(comboBoxAn);

        comboBoxLuna = new JComboBox();
        comboBoxLuna.setBackground(Color.LIGHT_GRAY);
        comboBoxLuna.setModel(new DefaultComboBoxModel(new String[]{"1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"}));
        comboBoxLuna.setToolTipText("An");
        comboBoxLuna.setBounds(200, 206, 60, 24);
        contentPane.add(comboBoxLuna);

        btnCreate = new JButton(btn);
        btnCreate.setBackground(Color.ORANGE);
        btnCreate.setFocusPainted(false);
        btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCreate.setBounds(76, 288, 164, 30);
        contentPane.add(btnCreate);
        if (c == null) {
            btn = "Create Client";
            setActinonInsert();
        } else {
            client = c;
            btn = "Edit Client";
            textFieldAdresa.setText(c.getAdresa());
            textFieldNume.setText(c.getNume());
            textFieldPrenume.setText(c.getPrenume());
            textFieldEmail.setText(c.getEmail());
            String[] date = c.getData_nasterii().split("-");
            comboBoxLuna.getModel().setSelectedItem(date[0]);
            comboBoxAn.getModel().setSelectedItem(date[1]);
            comboBoxZi.getModel().setSelectedItem(date[2]);
            setActionUpdate();
        }
        btnCreate.setText(btn);

    }

    public void setActinonInsert() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = Controller.getSingeController();
                c.InsertClient();
            }
        });
    }

    public void setActionUpdate() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = Controller.getSingeController();
                c.EditClient();
            }
        });
    }

    public JTextField getTextFieldNume() {
        return textFieldNume;
    }

    public JTextField getTextFieldPrenume() {
        return textFieldPrenume;
    }

    public JTextField getTextFieldAdresa() {
        return textFieldAdresa;
    }

    public JComboBox getComboBoxZi() {
        return comboBoxZi;
    }

    public JComboBox getComboBoxAn() {
        return comboBoxAn;
    }

    public JComboBox getComboBoxLuna() {
        return comboBoxLuna;
    }

    public JTextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public Client getClient() {
        return client;
    }
}

