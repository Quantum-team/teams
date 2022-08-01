package com.personal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.personal.dao.PersonalDao;
import com.personal.model.Persomal;

import java.awt.FlowLayout;
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
		setTitle("新增员工");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("员工姓名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setBounds(45, 44, 176, 41);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("员工性别");
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		label.setBounds(45, 128, 176, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("联系电话");
		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
		label_1.setBounds(45, 213, 176, 41);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("居住地址");
		label_2.setFont(new Font("宋体", Font.PLAIN, 35));
		label_2.setBounds(45, 277, 176, 41);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(235, 44, 199, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(235, 128, 199, 41);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(235, 213, 199, 41);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(235, 277, 199, 41);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加数据
				try {
					addPersonalInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(45, 407, 176, 54);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回主页");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//返回主页
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
		button.setBounds(268, 407, 176, 54);
		contentPane.add(button);
	}

	//添加数据
	protected void addPersonalInfo(ActionEvent e) throws SQLException {
		//获取输入框内容
		String empName=textField.getText();
		String empSex=textField_1.getText();
		String empPhone=textField_2.getText();
		String empAddress=textField_3.getText();
		//判断是否完整
		if ("".equals(empName)||"".equals(empSex)||"".equals(empPhone)||"".equals(empAddress)) {
			JOptionPane.showMessageDialog(null, "信息不完整，请填写完整！");
		}else {
			//将数据封装到实体对象里面
			Persomal persomal=new Persomal(0, empName, empSex, empPhone, empAddress);
			//创建dao对象，并调用方法
			PersonalDao personalDao=new PersonalDao();
			personalDao.addPersonal(persomal);
			//友好提示
			JOptionPane.showMessageDialog(null, "信息添加成功！");
			//页面跳转
			this.dispose();
			AddFrame addFrame=new AddFrame();
			addFrame.setVisible(true);
		}
	}
}
