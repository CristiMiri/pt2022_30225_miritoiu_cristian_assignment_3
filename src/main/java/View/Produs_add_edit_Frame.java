package View;

import Controler.Controller;
import Model.Produs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Produs_add_edit_Frame extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNume;
    private JTextField textFieldProdus;
    private JTextField textFieldPret;
    private JTextField textFieldStoc;
    private JButton btnNewButton;
    private Produs produs;
    /**
     * Create the frame.
     */
    public Produs_add_edit_Frame(Produs p) {
        produs=p;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 320, 260);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nume");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 11, 49, 14);
        contentPane.add(lblNewLabel);

        JLabel lblProducator = new JLabel("Producator");
        lblProducator.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProducator.setBounds(173, 12, 70, 14);
        contentPane.add(lblProducator);

        JLabel lblPret = new JLabel("Pret");
        lblPret.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblPret.setBounds(10, 90, 49, 14);
        contentPane.add(lblPret);

        JLabel lblStoc = new JLabel("Stoc");
        lblStoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblStoc.setBounds(173, 90, 49, 14);
        contentPane.add(lblStoc);

        btnNewButton = new JButton("Adauga Produs");
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBackground(Color.ORANGE);
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnNewButton.setBounds(78, 180, 150, 25);
        contentPane.add(btnNewButton);

        textFieldNume = new JTextField();
        textFieldNume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldNume.setBounds(10, 35, 96, 20);
        contentPane.add(textFieldNume);
        textFieldNume.setColumns(10);

        textFieldProdus = new JTextField();
        textFieldProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldProdus.setColumns(10);
        textFieldProdus.setBounds(173, 35, 96, 20);
        contentPane.add(textFieldProdus);

        textFieldPret = new JTextField();
        textFieldPret.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldPret.setColumns(10);
        textFieldPret.setBounds(10, 115, 96, 20);
        contentPane.add(textFieldPret);

        textFieldStoc = new JTextField();
        textFieldStoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textFieldStoc.setColumns(10);
        textFieldStoc.setBounds(173, 115, 96, 20);
        contentPane.add(textFieldStoc);
		if(produs==null) {
            setActinonInsert();
            btnNewButton.setText("Adauga Produs");
        }
        else
        {
            setActinonUpdate();
            btnNewButton.setText("Edit Produs");
            textFieldNume.setText(p.getNume());
            textFieldProdus.setText(p.getProducator());
            textFieldPret.setText(String.format("%.2f", p.getPret()));
            textFieldStoc.setText(String.valueOf(p.getStoc()));
        }
    }

    public void setActinonInsert() {
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = Controller.getSingeController();
                c.InsertProdus();
            }
        });
    }

    public void setActinonUpdate() {
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = Controller.getSingeController();
                c.EditProdus();
            }
        });
    }

    public JTextField getTextFieldNume() {
        return textFieldNume;
    }

    public JTextField getTextFieldProdus() {
        return textFieldProdus;
    }

    public JTextField getTextFieldPret() {
        return textFieldPret;
    }

    public JTextField getTextFieldStoc() {
        return textFieldStoc;
    }

    public Produs getProdus() {
        return produs;
    }
}
