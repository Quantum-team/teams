package com.personal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.personal.Dao.BaseDao;
import com.personal.moule.Per;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.jar.JarOutputStream;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBox;

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
		setBounds(100, 100, 479, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("人口普查信息录入");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(131, 20, 316, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("城市");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(36, 96, 103, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("社区");
		lblNewLabel_1_1.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(36, 162, 103, 35);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("街道");
		lblNewLabel_1_2.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(36, 221, 103, 35);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("姓名");
		lblNewLabel_1_3.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(36, 285, 103, 35);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("性别");
		lblNewLabel_1_4.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_4.setBounds(36, 349, 103, 35);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("身份证号");
		lblNewLabel_1_4_1.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_1_4_1.setBounds(10, 416, 129, 35);
		contentPane.add(lblNewLabel_1_4_1);
		
		textField = new JTextField();
		textField.setBounds(141, 88, 255, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 156, 255, 41);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(141, 221, 255, 41);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(141, 285, 255, 41);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(141, 418, 255, 41);
		contentPane.add(textField_4);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("黑体", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		comboBox.setBounds(151, 349, 245, 34);
		contentPane.add(comboBox);
		
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
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.setBounds(73, 476, 97, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new MainFrame().setVisible(true);
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(321, 476, 97, 54);
		contentPane.add(btnNewButton_1);
	}

	protected void addInfo(ActionEvent e) throws SQLException {
		String city=textField.getText();
		String community=textField_1.getText();
		String street=textField_2.getText();
		String name=textField_3.getText();
		String sex=(comboBox).getActionCommand();
		String card_no=textField_4.getText();
		if ("".equals(city)&&"".equals(community)&&"".equals(street)&&"".equals(name)&&"".equals(sex)&&"".equals(card_no)){
			JOptionPane.showMessageDialog(null, "信息不完整，请检查！");
		}else {
			Date time=new Date();
			Per per=new Per(0, city, community, street, name, sex, card_no, time);
			new BaseDao().add(per);
			JOptionPane.showMessageDialog(null, "添加成功！");
			this.dispose();
			new AddFrame().setVisible(true);
		}
	}
}
