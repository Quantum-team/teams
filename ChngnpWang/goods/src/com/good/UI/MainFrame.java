package com.good.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.good.dao.GoodsDao;
import com.good.model.Goods;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 */
	public MainFrame() throws SQLException {
		setTitle("商品管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1187, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入商品名称");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setBounds(159, 45, 337, 53);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(444, 45, 246, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 35));
		btnNewButton.setBounds(761, 45, 131, 48);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("添加");
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		button.setBounds(1002, 170, 131, 48);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 109, 963, 412);
		contentPane.add(scrollPane);
		
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> list=goodsDao.select(null);
		Object[][] data=new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getGoodId();
			data[i][1]=list.get(i).getGoodName();
			data[i][2]=list.get(i).getNum();
			data[i][3]=list.get(i).getPrice();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"商品编号", "商品名称", "数量", "添加"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void selectInfo(ActionEvent e) throws SQLException {
		String goodName=textField.getText();
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> list=goodsDao.select(goodName);
		Object[][] data=new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getGoodId();
			data[i][1]=list.get(i).getGoodName();
			data[i][2]=list.get(i).getNum();
			data[i][3]=list.get(i).getPrice();
		}
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"商品编号", "商品名称", "数量", "添加"
			}
		));
	}
}
