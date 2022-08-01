package com.library.ul;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import com.library.dao.BookDao;
import com.library.model.Book;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	BookDao bookDao=new BookDao();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private  JComboBox comboBox;
	private JTable table;
	private JTable table_1;

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
		setTitle("\u56FE\u4E66\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(30, 22, 109, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4F5C\u8005\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(29, 91, 87, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(30, 150, 109, 53);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u4EF7\u683C\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(30, 236, 109, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(29, 300, 137, 47);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(138, 22, 143, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 91, 143, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 168, 143, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(138, 236, 143, 35);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 24));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5C0F\u8BF4", "\u6587\u5B66", "\u513F\u7AE5", "\u8BA1\u7B97\u673A", "\u5DE5\u7A0B"}));
		comboBox.setBounds(138, 300, 143, 35);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addBookInfo(arg0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(69, 357, 97, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6E05\u7A7A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//清空
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(188, 357, 93, 53);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteBookInfo(arg0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(679, 357, 93, 53);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 22, 537, 324);
		contentPane.add(scrollPane);
		
		//查询所有
		List<Book> list=new BookDao().selectAll();
		//创建二维数组
		Object[][] data=new Object[list.size()][6];
		//将查询数组存储到二维数组
		for (int i = 0; i <list.size(); i++) {
			data[i][0] =list.get(i).getBookId();
			data[i][1] =list.get(i).getBookName();
			data[i][2] =list.get(i).getBookAuthor();
			data[i][3] =list.get(i).getBookPublish();
			data[i][4] =list.get(i).getBookPrice();
			data[i][5] =list.get(i).getBookType();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"编号", "名称", "作者", "出版社", "价格", "类型"
			}
		));
		scrollPane.setViewportView(table);
		
		table_1 = new JTable();
		table_1.setBounds(115, 378, 1, 1);
		contentPane.add(table_1);
//		table.setModel(null);
	}
	//删除
	protected void deleteBookInfo(ActionEvent e) throws SQLException {
		int row=table .getSelectedRow();
		//kjrtyhetthrgSystem.out.println(row);
		if (row<0) {
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "你确实要删除此数据吗？");
			if (index==JOptionPane.OK_OPTION) {
//				new BookDao().deleteBook(row);
				TableModel obj = table.getModel();
				int id = (int)obj.getValueAt(row, 0);
				BookDao bookDao=new BookDao();
				bookDao.deleteBook(id);
				JOptionPane.showConfirmDialog(null, "数据删除成功！");
//				this.repaint();
			}
			this.dispose();
			MainFrame mainFrame=new MainFrame();
			mainFrame.setVisible(true);
		}
	}
	//添加Book信息
	protected void addBookInfo(ActionEvent e) throws SQLException{
		//获取信息
		String name=textField.getText();
		String author=textField_1.getText();
		String publish=textField_2.getText();
		String bookPrice=textField_3.getText();
		double price=Double.parseDouble(bookPrice);
		String type =(String)comboBox.getSelectedItem();//接收下拉值
		//判断数据是否为空
		if (name==null||author==null||publish==null||price==0) {
			JOptionPane.showConfirmDialog(null, "填写信息不完整，请检查！");
		}else {
			//将数据封装给Book对象
			Book book=new Book(1, name, author, publish, price, type);
			//调用添加方法
			new BookDao().addBook(book);
			JOptionPane.showConfirmDialog(null, "图书信息添加成功！");
			this.dispose();
			MainFrame mainFrame=new MainFrame();
			mainFrame.setVisible(true);
		}
	}
}
