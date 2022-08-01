package com.movies.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.movies.dao.MoviesDao;
import com.movies.model.Movies;
import com.movies.utilis.BaseDao;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
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
		setTitle("影院售票系统\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("影院售票系统");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setBounds(267, 10, 245, 47);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("添加信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddFrame addFrame=new AddFrame();
				addFrame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(97, 63, 164, 32);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("删除信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(471, 67, 164, 32);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 113, 770, 282);
		contentPane.add(scrollPane);
		
		List<Movies> list=new MoviesDao().selectMovies();
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
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"序号", "片名", "电影时长", "地区", "上映日期", "票价"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table.getEditingRow();
		if (row<0) {
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "你确认删除该信息吗？");
			if (index==JOptionPane.OK_OPTION) {
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				MoviesDao moviesDao=new MoviesDao();
				moviesDao.delete(id);
				this.dispose();
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
			}
		}
	}
}
