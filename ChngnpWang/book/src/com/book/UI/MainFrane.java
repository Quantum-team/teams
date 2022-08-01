package com.book.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.book.dao.BookDao;
import com.book.model.Book;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainFrane extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrane frame = new MainFrane();
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
	public MainFrane() throws SQLException {
		setTitle("图书管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1165, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书管理系统");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 50));
		lblNewLabel.setBounds(391, 13, 328, 75);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 92, 1119, 332);
		contentPane.add(scrollPane);
		
		BookDao bookDao=new BookDao();
		List<Book> list=bookDao.selectAll();
		Object[][] data=new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getBookName();
			data[i][2]=list.get(i).getAuthor();
			data[i][3]=list.get(i).getSex();
			data[i][4]=list.get(i).getPrice();
			data[i][5]=list.get(i).getBookDesc();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"编号", "名称", "作者", "性别", "价格", "简介"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("添加信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(213, 437, 153, 44);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("删除信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(740, 437, 153, 44);
		contentPane.add(button);
	}

	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table.getEditingRow();
		if (row<0) {
			JOptionPane.showMessageDialog(null, "选择你要删除的数据！");
		}else {
			int index = JOptionPane.showConfirmDialog(null, "您确认删除该员工？");
			if (index==JOptionPane.OK_OPTION) {
				//获取表格model对象
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				BookDao bookDao=new BookDao();
				bookDao.delete(id);
				JOptionPane.showMessageDialog(null, "删除成功");
				this.dispose();
				MainFrane mainFrane=new MainFrane();
				mainFrane.setVisible(true);
			}
		}
	}

}
