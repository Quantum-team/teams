package com.movies.UI;

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

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.movies.dao.MoviesDao;
import com.movies.model.Movies;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
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
		setTitle("影院售票系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1281, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("影院售票系统");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 40));
		lblNewLabel.setBounds(479, 10, 536, 85);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("添加信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new AddFrame().setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 35));
		btnNewButton.setBounds(192, 74, 236, 57);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除信息");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 35));
		btnNewButton_1.setBounds(793, 74, 236, 57);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 1247, 379);
		contentPane.add(scrollPane);
		
		List<Movies> list=new MoviesDao().select();
		Object[][] data=new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getName();
			data[i][2]=list.get(i).getDuration();
			data[i][3]=list.get(i).getArea();
			data[i][4]=list.get(i).getMoviedate();
			data[i][5]=list.get(i).getPrice();
		}
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"序号", "片名", "电影时长", "地区", "上映日期", "票价"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table.getSelectedRow();
		if (row<0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的行！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "你确认删除该信息吗？");
			if (index==JOptionPane.OK_OPTION) {
				TableModel tableModel=table.getModel();
				int id=(int)tableModel.getValueAt(row, 0);
				new MoviesDao().delete(id);
				this.dispose();
				new MainFrame().setVisible(true);
			}
		}
	}
}
