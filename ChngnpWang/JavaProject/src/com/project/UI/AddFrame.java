package com.project.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.project.dao.BaseDao;
import com.project.model.Pro;

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
		setTitle("新增项目信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("项目名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(43, 49, 156, 56);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("项目周期：");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(43, 134, 156, 56);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("参与人数：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(43, 202, 156, 56);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("项目负责人：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(14, 282, 198, 56);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(214, 59, 236, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(210, 144, 86, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(210, 210, 86, 36);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(210, 299, 156, 35);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("月");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(321, 144, 72, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("人");
		label_3.setFont(new Font("宋体", Font.PLAIN, 30));
		label_3.setBounds(321, 213, 72, 33);
		contentPane.add(label_3);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					ListFrame listFrame=new ListFrame();
					listFrame.setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(422, 398, 113, 56);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addInfo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(280, 398, 113, 56);
		contentPane.add(button);
	}
	protected void addInfo() throws SQLException {
		String prj_name=textField.getText();
		String prj_cycles=textField_1.getText();
		String prj_counts=textField_2.getText();
		String prj_manager=textField_3.getText();
		if ("".equals(prj_name)||"".equals(prj_cycles)||"".equals(prj_counts)||"".equals(prj_manager)){
			JOptionPane.showConfirmDialog(null, "信息不完整，请填写完整！");
		}else {
			int prj_cycle=Integer.parseInt(prj_cycles);
			int prj_count=Integer.parseInt(prj_counts);
			Pro pro =new Pro(0,prj_name,prj_cycle,prj_count,prj_manager);
			BaseDao baseDao=new BaseDao();
			baseDao.addProject(pro);
			JOptionPane.showConfirmDialog(null, "添加成功！");
			this.dispose();
			AddFrame addFrame=new AddFrame();
			addFrame.setVisible(true);
		}
	}	
}
