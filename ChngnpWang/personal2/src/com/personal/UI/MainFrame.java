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
import com.personal.model.Personal;
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
	 * @throws SQLException 
	 */
	public MainFrame() throws SQLException {
		setTitle("人事信息管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("员工信息管理");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel.setBounds(333, 10, 332, 61);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("请输入员工姓名关键字");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(80, 80, 368, 42);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(355, 81, 259, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//模糊查询
				try {
					selectInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.setBounds(693, 66, 115, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除员工");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(731, 433, 159, 42);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 878, 294);
		contentPane.add(scrollPane);
		
		//全部查询
		List<Personal> list=new PersonalDao().select(null);
		Object[][] data=new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getEmpId();
			data[i][1]=list.get(i).getEmpName();
			data[i][2]=list.get(i).getEmpSex();
			data[i][3]=list.get(i).getEmpPhone();
			data[i][4]=list.get(i).getEmpAddress();
		}
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"员工序号", "员工名", "性别", "手机", "地址"
			}
		));
		scrollPane.setViewportView(table);
	}

	//删除
	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table.getEditingRow();
		if (row<0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的行！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "您确认删除该员工？");
			if (index==JOptionPane.OK_OPTION) {
				TableModel objModel=table.getModel();
				int id=(int)objModel.getValueAt(row, 0);
				new PersonalDao().delete(id);
				JOptionPane.showMessageDialog(null, "删除成功！");
				this.dispose();
				new MainFrame().setVisible(true);
			}
		}
	}

	//模糊查询
	protected void selectInfo(ActionEvent e) throws SQLException {
		//获取输入框
		String empName=textField.getText();
		List<Personal> list=new PersonalDao().select(empName);
		Object[][] data=new Object[list.size()][5];
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
					"员工序号", "员工名", "性别", "手机", "地址"
				}
			));
	}
}
