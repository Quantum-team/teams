package com.good.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.good.dao.GoodsDao;
import com.good.model.Goods;
import com.sun.xml.internal.ws.assembler.jaxws.AddressingTubeFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFrame frame = new AddFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddFrame() {
		setTitle("添加商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品编号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(68, 26, 151, 35);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("商品名称");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(68, 93, 151, 35);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("数量");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(68, 167, 151, 35);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("价格");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(68, 228, 151, 35);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(220, 26, 159, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(220, 93, 159, 35);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 167, 159, 35);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 228, 159, 35);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddGoodsInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(82, 309, 113, 43);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("取消");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				dispose();
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(330, 309, 113, 43);
		contentPane.add(button);
	}

	protected void AddGoodsInfo(ActionEvent e) throws SQLException {
		String goodId=textField.getText();
		String goodName=textField_1.getText();
		String nums=textField_2.getText();
		String prices=textField_3.getText();
		if ("".equals(goodId)||"".equals(goodName)||"".equals(nums)||"".equals(prices)) {
			JOptionPane.showMessageDialog(null, "请输入完整信息！");
		}else {
			int num=Integer.getInteger(nums);
			double price=Double.parseDouble(prices);
			Goods goods=new Goods(0, goodName, num, price);
			GoodsDao goodsDao=new GoodsDao();
			goodsDao.insert(goods);
			JOptionPane.showMessageDialog(null, "信息添加成功！");
			this.dispose();
			AddFrame addFrame=new AddFrame();
			addFrame.setVisible(true);
		}
	}

}
