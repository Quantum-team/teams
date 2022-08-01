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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		setBounds(100, 100, 520, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("新增影片信息");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel.setBounds(116, 21, 376, 63);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("影片名称");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(64, 109, 143, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("影片时长");
		lblNewLabel_1_1.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(64, 178, 143, 38);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("归属地区");
		lblNewLabel_1_2.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(64, 241, 143, 38);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("上映日期");
		lblNewLabel_1_3.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1_3.setBounds(64, 299, 143, 38);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("票价");
		lblNewLabel_1_4.setFont(new Font("黑体", Font.PLAIN, 25));
		lblNewLabel_1_4.setBounds(64, 362, 143, 38);
		contentPane.add(lblNewLabel_1_4);
		
		textField = new JTextField();
		textField.setBounds(214, 109, 205, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(214, 178, 205, 34);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(214, 241, 205, 34);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(214, 303, 205, 34);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(214, 362, 205, 34);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 25));
		btnNewButton.setBounds(46, 444, 143, 63);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回主页");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				dispose();
				new MainFrame().setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 25));
		btnNewButton_1.setBounds(294, 444, 143, 63);
		contentPane.add(btnNewButton_1);
	}

	protected void AddInfo(ActionEvent e) throws SQLException {
		String name=textField.getText();
		String durations=textField_1.getText();
		String area=textField_2.getText();
		String moviedates=textField_3.getText();
		String prices=textField_4.getText();
		if ("".equals(name)||"".equals(durations)||"".equals(area)||"".equals(moviedates)||"".equals(prices)) {
			JOptionPane.showMessageDialog(null, "请检查信息完整！");
		}else {
			int duration=Integer.parseInt(durations);
			double price=Double.parseDouble(prices);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
			Date time=null;
			try {
				time=simpleDateFormat.parse(moviedates);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			Movies movies=new Movies(0, name, duration, area, time, price);
			new MoviesDao().add(movies);
			dispose();
			new AddFrame().setVisible(true);
		}
	}
}
