package com.book.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.book.dao.BookDao;
import com.book.model.Book;

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
	private JTextField textField_4;

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
		setTitle("添加图书信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("添加图书信息");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 40));
		lblNewLabel.setBounds(179, 13, 330, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("书名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(91, 73, 91, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("作者");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(91, 132, 91, 35);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("性别");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(91, 189, 91, 35);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("价格");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(91, 237, 91, 35);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("简介");
		label_3.setFont(new Font("宋体", Font.PLAIN, 30));
		label_3.setBounds(91, 292, 91, 35);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(189, 79, 309, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(189, 143, 309, 29);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(189, 200, 309, 29);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(189, 248, 309, 29);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(189, 303, 309, 29);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.setBounds(66, 389, 192, 53);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回主页");
		button.setFont(new Font("黑体", Font.PLAIN, 30));
		button.setBounds(346, 389, 192, 53);
		contentPane.add(button);
	}

	protected void addInfo(ActionEvent e) throws SQLException {
		String bookName=textField.getText();
		String author=textField_1.getText();
		String sex=textField_2.getText();
		String prices=textField_3.getText();
		String bookDesc=textField_4.getText();
		if ("".equals(bookName)||"".equals(author)||"".equals(sex)||"".equals(prices)||"".equals(bookDesc)) {
			JOptionPane.showMessageDialog(null, "信息不完整，请填写完整！");
		}else {
			float price=Float.parseFloat(prices);
			Book book3=new Book(0, bookName, author, sex, price, bookDesc);
			BookDao bookDao=new BookDao();

		}
	}

}
