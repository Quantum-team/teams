package com.personal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.personal.Dao.BaseDao;
import com.personal.moule.Per;
import com.personal.moule.Personal;
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
		setTitle("人口普查登记系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("城市：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel.setBounds(104, 28, 117, 34);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(211, 28, 390, 36);
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
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 25));
		btnNewButton.setBounds(643, 28, 93, 33);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("录入");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new AddFrame().setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 25));
		button.setBounds(753, 28, 93, 33);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 97, 882, 360);
		contentPane.add(scrollPane);
		
		BaseDao dao=new BaseDao();
		List<Per> list=dao.selectAll(null);
		Object[][] data=new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getCity();
			data[i][2]=list.get(i).getCommunity();
			data[i][3]=list.get(i).getStreet();
			data[i][4]=list.get(i).getName();
			data[i][5]=list.get(i).getSex();
			data[i][6]=list.get(i).getCard_no();
			data[i][7]=list.get(i).getRegister_data();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"序号", "城市", "社区", "街道", "姓名", "性别", "身份证号码", "登记时间"
			}
		));
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
	}

	protected void selectInfo(ActionEvent e) throws SQLException {
		String city=textField.getText();
		BaseDao dao=new BaseDao();
		List<Per> list=dao.selectAll(city);
		Object[][] data=new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getCity();
			data[i][2]=list.get(i).getCommunity();
			data[i][3]=list.get(i).getStreet();
			data[i][4]=list.get(i).getName();
			data[i][5]=list.get(i).getSex();
			data[i][6]=list.get(i).getCard_no();
			data[i][7]=list.get(i).getRegister_data();
		}
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"序号", "城市", "社区", "街道", "姓名", "性别", "身份证号码", "登记时间"
				}
			));
		this.dispose();
		new MainFrame().setVisible(true);
	}
}
