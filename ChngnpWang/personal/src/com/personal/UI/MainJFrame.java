package com.personal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.personal.dao.PersonalDao;
import com.personal.model.Persomal;

public class MainJFrame extends JFrame {

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
					MainJFrame frame = new MainJFrame();
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
	public MainJFrame() throws SQLException {
		setTitle("\u4EBA\u4E8B\u4FE1\u606F\u7BA1\u7406\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5458\u5DE5\u4FE1\u606F\u7BA1\u7406\r\n");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(321, 27, 342, 66);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u5458\u5DE5\u59D3\u540D\u5173\u952E\u5B57");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(47, 125, 259, 44);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(333, 125, 359, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//查询数据
				try {
					selectInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(765, 111, 136, 54);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5220\u9664\u5458\u5DE5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//删除员工信息
				try {
					deletePersonalInfo(arg0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(884, 535, 214, 54);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 167, 944, 368);
		contentPane.add(scrollPane);
		
		//创建dao对象，并调用查询方法
		PersonalDao personalDao=new PersonalDao();
		List<Persomal> list=personalDao.select(null);
		//创建二维数组
		Object[][] data=new Object[list.size()][5];
		//给二维数组赋值
		for (int i = 0; i < list.size(); i++) {
			data[i][0] =list.get(i).getEmpId();
			data[i][1] =list.get(i).getEmpName();
			data[i][2] =list.get(i).getEmpSex();
			data[i][3] =list.get(i).getEmpPhone();
			data[i][4] =list.get(i).getEmpAddress();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"员工序号", "员工性名", "员工性别", "联系电话", "居住地址"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton button_1 = new JButton("添加员工");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddFrame addFrame=new AddFrame();
				addFrame.setVisible(true);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 30));
		button_1.setBounds(139, 535, 214, 54);
		contentPane.add(button_1);
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
			data[i][0] =list.get(i).getEmpId();
			data[i][1] =list.get(i).getEmpName();
			data[i][2] =list.get(i).getEmpSex();
			data[i][3] =list.get(i).getEmpPhone();
			data[i][4] =list.get(i).getEmpAddress();
		}
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"员工序号", "员工性名", "员工性别", "联系电话", "居住地址"
			}
		));
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
				int result=new PersonalDao().deletePersonal(id);
				if (result>0) {
					JOptionPane.showMessageDialog(null, "删除成功");
				}else {
					JOptionPane.showMessageDialog(null, "删除不成功");
				}
				//创建dao对象，并调用删除方法
//				PersonalDao personalDao=new PersonalDao();
//				personalDao.deletePersonal(id);
				//友好提示
				//页面刷新
				this.dispose();
				MainJFrame mainFrame=new MainJFrame();
				mainFrame.setVisible(true);
			}
		}
	}
}
