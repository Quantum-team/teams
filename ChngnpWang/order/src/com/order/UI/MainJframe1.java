package com.order.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.order.dao.OrderDao;
import com.order.model.Order;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainJframe1 extends JFrame {

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
					MainJframe1 frame = new MainJframe1();
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
	public MainJframe1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6DFB\u52A0\u8BA2\u5355\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setBounds(258, 13, 210, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5546\u54C1\u540D\u79F0");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(101, 123, 140, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u5546\u54C1\u4EF7\u683C");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(101, 186, 140, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BA2\u5355\u7F16\u53F7");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(101, 252, 140, 41);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BA2\u5355\u63CF\u8FF0");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(101, 325, 140, 41);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(269, 123, 243, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(269, 176, 243, 38);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(269, 252, 243, 38);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(269, 325, 243, 38);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addOrderInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(170, 392, 160, 57);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				dispose();
				MainJFrame mainJFrame=new MainJFrame();
				mainJFrame.setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(450, 392, 160, 57);
		contentPane.add(button);
	}

	protected void addOrderInfo(ActionEvent e) throws SQLException {
		String name=textField.getText();
		String prices=textField_1.getText();
		String orderId=textField_2.getText();
		String descInfo=textField_3.getText();
		if ("".equals(name)||"".equals(prices)||"".equals(orderId)||"".equals(descInfo)) {
			JOptionPane.showConfirmDialog(null, "信息不完整，请填写完整！");
		}else {
			double price =Double.parseDouble(prices);
			Order order=new Order(0,name,price,orderId,descInfo);
			OrderDao dao=new OrderDao();
			dao.addOrder(order);
			JOptionPane.showConfirmDialog(null, "信息添加成功！");
			this.dispose();
			MainJFrame mainJFrame=new MainJFrame();
			mainJFrame.setVisible(true);
		}
	}
}
