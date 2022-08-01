package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import model.Order;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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
		setTitle("添加信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 593);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("单号");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel.setBounds(43, 29, 93, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("订单");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(43, 151, 93, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("数量");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(43, 213, 93, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("电话");
		lblNewLabel_3.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(43, 278, 93, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("地址");
		lblNewLabel_4.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(43, 341, 93, 35);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("备注");
		lblNewLabel_5.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(43, 413, 93, 35);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1_1 = new JLabel("名字");
		lblNewLabel_1_1.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(43, 84, 93, 35);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(158, 29, 118, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(158, 96, 118, 33);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(158, 151, 118, 33);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(158, 213, 118, 33);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(158, 280, 118, 33);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(158, 341, 118, 33);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(158, 413, 118, 33);
		contentPane.add(textField_6);
		
		JButton btnNewButton = new JButton("确认");
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
		btnNewButton.setBounds(39, 490, 109, 56);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				dispose();
				new MainFrame().setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(205, 490, 109, 56);
		contentPane.add(btnNewButton_1);
	}

	protected void addInfo(ActionEvent e) throws SQLException {
		String odds=textField.getText();
		String name=textField_1.getText();
		String order=textField_2.getText();
		String quantitys=textField_3.getText();
		String telephones=textField_4.getText();
		String address=textField_5.getText();
		String note=textField_6.getText();
		if ("".equals(odds)||"".equals(name)||"".equals(order)||
				"".equals(quantitys)||"".equals(telephones)||"".equals(address)||"".equals(note)) {
			JOptionPane.showConfirmDialog(null, "填写信息不完整，请检查！");
		}else {
			int odd=Integer.parseInt(odds);
			int quantity=Integer.parseInt(quantitys);
			int telephone=Integer.parseInt(telephones);
			Order order2=new Order(0, odd, name, order, quantity, telephone, address, note,new java.util.Date());
			Dao dao=new Dao();
			dao.add(order2);
			JOptionPane.showMessageDialog(null, "信息添加成功！");
		}
	}

}
