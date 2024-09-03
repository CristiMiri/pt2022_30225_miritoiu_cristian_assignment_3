package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Select_Frame extends JFrame {

	private final JPanel contentPane;
	private Clienti_Frame clienti_frame;
	private Produse_Frame produse_frame;
	private Comenzi_Frame comenzi_frame;
	/**
	 * Create the frame.
	 */
	public Select_Frame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 160);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClienti = new JButton("Clienti");
		btnClienti.setFocusPainted(false);
		btnClienti.setBackground(Color.ORANGE);
		btnClienti.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClienti.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClienti.setBounds(10, 42, 80, 30);
		btnClienti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clienti_frame=new Clienti_Frame();
				clienti_frame.setVisible(true);
			}
		});
		contentPane.add(btnClienti);
		JButton btnProduse = new JButton("Produse");
		btnProduse.setFocusPainted(false);
		btnProduse.setBackground(Color.ORANGE);
		btnProduse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProduse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnProduse.setBounds(146, 45, 100, 30);
		btnProduse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				produse_frame =new Produse_Frame();
				produse_frame.setVisible(true);
			}
		});
		contentPane.add(btnProduse);
		
		JButton btnComenzi = new JButton("Comenzi");
		btnComenzi.setFocusPainted(false);
		btnComenzi.setBackground(Color.ORANGE);
		btnComenzi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComenzi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnComenzi.setBounds(304, 45, 100, 30);
		btnComenzi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comenzi_frame=new Comenzi_Frame();
				comenzi_frame.setVisible(true);
			}
		});
		contentPane.add(btnComenzi);
	}

	/***
	 * Getter pentru clienti_frame.
	 * @return Frameul necesar vizualizarii clientiilor.
	 */
	public Clienti_Frame getClienti_frame() {
		return clienti_frame;
	}

	/***
	 * Getter pentru produse_frame.
	 * @return Frameul necesar vizualizarii produselor.
	 */
	public Produse_Frame getProduse_frame() {
		return produse_frame;
	}

	public Comenzi_Frame getComenzi_frame() {
		return comenzi_frame;
	}
}
