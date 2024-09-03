package View;

import Controler.Controller;
import Model.Produs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class Produse_Frame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Produs_add_edit_Frame produs_add_frame;
	private Produs_add_edit_Frame produs_edit_frame;
	/**
	 * Create the frame.
	 */
	public Produse_Frame() {
		setTitle("Produse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 276, 336);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setDragEnabled(true);
		table.setAutoCreateRowSorter(true);
		table.setGridColor(Color.getHSBColor(184,94,98));
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		table.setModel(Controller.buildTable("Produs"));
		table.removeColumn(table.getColumnModel().getColumn(0));
		JButton btnAddProdus = new JButton("Add Produs");
		btnAddProdus.setBackground(Color.YELLOW);
		btnAddProdus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddProdus.setFocusPainted(false);
		btnAddProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAddProdus.setBounds(366, 55, 150, 50);
		btnAddProdus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				produs_add_frame = new Produs_add_edit_Frame(null);
				produs_add_frame.setVisible(true);
			}
		});
		contentPane.add(btnAddProdus);
		
		JButton btnDelProdus = new JButton("Delete Produs\r\n");
		btnDelProdus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDelProdus.setFocusPainted(false);
		btnDelProdus.setBackground(Color.YELLOW);
		btnDelProdus.setBounds(366, 134, 150, 50);
		contentPane.add(btnDelProdus);
		btnDelProdus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getSingeController().DeleteObject(table,new Produs(-1));
			}
		});
		JButton btnEditProdus = new JButton("Edit Produs");
		btnEditProdus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnEditProdus.setFocusPainted(false);
		btnEditProdus.setBackground(Color.YELLOW);
		btnEditProdus.setBounds(366, 221, 150, 50);
		btnEditProdus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getModel().getValueAt(row, 0);
				String nume = (String) table.getModel().getValueAt(row, 1);
				double pret = (float) table.getModel().getValueAt(row, 3);
				long stoc = (long) table.getModel().getValueAt(row, 2);
				String producator= (String) table.getModel().getValueAt(row, 4);
				Produs p=new Produs(id,nume,(int) stoc,pret,producator);
				produs_edit_frame=new Produs_add_edit_Frame(p);
				System.out.println(p.getIdProdus());
				produs_edit_frame.setVisible(true);
			}
		});
		contentPane.add(btnEditProdus);

	}

	public Produs_add_edit_Frame getProdus_add_frame() {
		return produs_add_frame;
	}

	public JTable getTable() {
		return table;
	}

	public Produs_add_edit_Frame getProdus_edit_frame() {
		return produs_edit_frame;
	}
}
