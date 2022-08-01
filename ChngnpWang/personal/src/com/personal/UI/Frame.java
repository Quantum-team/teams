package com.personal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.personal.dao.PersonalDao;
import com.personal.model.Persomal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {

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
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Frame() throws SQLException {
		setTitle("人事信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1126, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("员工信息管理");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(365, 26, 317, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("请输入员工姓名关键字");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(35, 92, 381, 36);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(412, 92, 293, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//查询数据
				try {
					selectInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(748, 76, 142, 52);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("删除员工");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除员工信息
				try {
					deletePersonalInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(858, 470, 202, 52);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 145, 1054, 312);
		contentPane.add(scrollPane);
		
		//创建dao对象，并调用查询方法
		PersonalDao personalDao=new PersonalDao();
		List<Persomal> list=personalDao.select(null);
		//创建二维数组
		Object[][] data=new Object[list.size()][5];
		//给二维数组赋值
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getEmpId();
			data[i][1]=list.get(i).getEmpName();
			data[i][2]=list.get(i).getEmpSex();
			data[i][3]=list.get(i).getEmpPhone();
			data[i][4]=list.get(i).getEmpAddress();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"员工序号", "员工性名", "员工性别", "联系电话", "居住地址"
			}
		));
		scrollPane.setViewportView(table);
	}

	//删除信息
	protected void deletePersonalInfo(ActionEvent e) throws SQLException {
		//获取行
		int row=table.getEditingRow();
		if (row<0) {
			JOptionPane.showMessageDialog(null, "选择你要删除的数据！");
		}else {
			int index = JOptionPane.showConfirmDialog(null, "您确认删除该员工？");
			if (index==JOptionPane.OK_OPTION) {
				//获取表格model对象
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				//创建dao对象，并调用删除方法
				PersonalDao personalDao=new PersonalDao();
				personalDao.deletePersonal(id);
				//友好提示
				JOptionPane.showMessageDialog(null, "删除成功");
				//页面刷新
				this.dispose();
				MainJFrame mainFrame=new MainJFrame();
				mainFrame.setVisible(true);
			}
		}
	}

	//模糊查询
	protected void selectInfo(ActionEvent e) throws SQLException {
		//获取输入框内容
		String name=textField.getText();
		//创建dao对象，并调用查询方法
		PersonalDao personalDao=new PersonalDao();
		List<Persomal> list=personalDao.select(name);
		        //创建二维数组
				Object[][] data=new Object[list.size()][5];
				//给二维数组赋值
				for (int i = 0; i < list.size(); i++) {
					data[i][0]=list.get(i).getEmpId();
					data[i][1]=list.get(i).getEmpName();
					data[i][2]=list.get(i).getEmpSex();
					data[i][3]=list.get(i).getEmpPhone();
					data[i][4]=list.get(i).getEmpAddress();
				}
				table.setModel(new DefaultTableModel(
						data,
						new String[] {
							"员工序号", "员工性名", "员工性别", "联系电话", "居住地址"
						}
					));
				this.dispose();
				Frame frame=new Frame();
				frame.setVisible(true);
	}
}
