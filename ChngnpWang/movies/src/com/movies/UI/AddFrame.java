package com.movies.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.movies.dao.MoviesDao;
import com.movies.model.Movies;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		setTitle("新增影片信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("新增影片信息");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(166, 10, 240, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("影片名称");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(79, 68, 120, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("影片时长");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(79, 123, 120, 35);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("归属地区");
		label_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label_1.setBounds(79, 168, 120, 35);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("上映日期");
		label_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label_2.setBounds(79, 213, 120, 35);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("实时票价");
		label_3.setFont(new Font("宋体", Font.PLAIN, 30));
		label_3.setBounds(79, 258, 120, 35);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(234, 73, 172, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(234, 123, 172, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(234, 168, 172, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(234, 213, 172, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(234, 258, 172, 26);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addInfo(arg0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(66, 332, 167, 35);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回主页");
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
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(285, 332, 167, 35);
		contentPane.add(button);
	}

	protected void addInfo(ActionEvent arg0) throws SQLException {
		String name=textField.getText();
		String durations=textField_1.getText();
		String area=textField_2.getText();
		String moviedate=textField_3.getText();
		String prices=textField_4.getText();
		if ("".equals(name)&&"".equals(durations)&&"".equals(area)&&"".equals(moviedate)&&"".equals(prices)) {
			JOptionPane.showMessageDialog(null, "信息不完整，请检查！");
		}else {
			int duration=Integer.parseInt(durations);
			double price=Double.parseDouble(prices);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
			Date date=null;
			try{
				date=simpleDateFormat.parse(moviedate);
			}catch (Exception e) {
				e.printStackTrace();
			}
			Movies movies=new Movies(0, name, duration, area,date, price);
			MoviesDao moviesDao=new MoviesDao();
			moviesDao.add(movies);
			JOptionPane.showMessageDialog(null, "数据添加成功！");
			this.dispose();
			AddFrame addFrame=new AddFrame();
			addFrame.setVisible(true);
		}
	}
}
