package com.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.Dao;
import com.model.Persional;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		setTitle("人口普查信息录入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("人口普查信息录入");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 40));
		lblNewLabel.setBounds(105, 20, 331, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("城市");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(34, 84, 109, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("社区");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(34, 141, 109, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("街道");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(34, 192, 109, 30);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("姓名");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(34, 246, 109, 30);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("性别");
		label_3.setFont(new Font("宋体", Font.PLAIN, 30));
		label_3.setBounds(34, 291, 109, 30);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("身份证号");
		label_4.setFont(new Font("宋体", Font.PLAIN, 30));
		label_4.setBounds(34, 338, 142, 30);
		contentPane.add(label_4);
		
		textField = new JTextField();
		textField.setBounds(140, 91, 277, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 141, 277, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(140, 203, 277, 30);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(140, 246, 277, 30);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(140, 286, 277, 30);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(166, 338, 277, 30);
		contentPane.add(textField_5);
		
		JButton btnNewButton = new JButton("保存");
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
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton.setBounds(98, 448, 93, 40);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("关闭");
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
		button.setFont(new Font("宋体", Font.PLAIN, 25));
		button.setBounds(324, 448, 93, 40);
		contentPane.add(button);
		
		JLabel label_5 = new JLabel("登录时间");
		label_5.setFont(new Font("宋体", Font.PLAIN, 30));
		label_5.setBounds(34, 391, 142, 30);
		contentPane.add(label_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(166, 391, 277, 30);
		contentPane.add(textField_6);
	}

	protected void addInfo(ActionEvent e) throws SQLException {
		String city=textField.getText();
		String community=textField_1.getText();
		String street=textField_2.getText();
		String name=textField_3.getText();
		String sex=textField_4.getText();
		String card_no=textField_5.getText();
		String register_data=textField_6.getText();
		System.out.println(register_data);
		if ("".equals(city)||"".equals(community)||"".equals(street)||"".equals(name)||"".equals(sex)||"".equals(card_no)||"".equals(register_data)) {
			JOptionPane.showMessageDialog(null, "信息不完整，请填写完整！");
		}else {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
//			Date data=null;
//			try{
//				data=simpleDateFormat.parse(register_data);
//				System.out.println(data);
//			}catch (Exception e1) {
//				e1.printStackTrace();
//			}
			Persional persional=new Persional(0, city, community, street, name, sex, card_no,register_data);
			Dao dao=new Dao();
			dao.add(persional);
			JOptionPane.showMessageDialog(null, "信息添加成功！");
			this.dispose();
			AddFrame addFrame=new AddFrame();
			addFrame.setVisible(true);
		}
	}
}
