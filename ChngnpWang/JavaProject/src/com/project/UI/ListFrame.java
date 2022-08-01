package com.project.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.project.dao.BaseDao;
import com.project.model.Pro;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFrame frame = new ListFrame();
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
	public ListFrame() throws SQLException {
		setTitle("项目管理窗口");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 23, 1089, 305);
		contentPane.add(scrollPane);
		
		List<Pro> list=new BaseDao().seleteAll();
		Object[][] data=new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getPrj_id();
			data[i][1]=list.get(i).getPrj_name();
			data[i][2]=list.get(i).getPrj_cycle();
			data[i][3]=list.get(i).getPrj_count();
			data[i][4]=list.get(i).getPrj_manager();
		}
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"序号", "项目名称", "项目周期", "参与人数", "项目负责人"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				dispose();
				ListFrame listFrame=new ListFrame();
				listFrame.setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(371, 341, 113, 56);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteInfo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(498, 341, 113, 56);
		contentPane.add(button);
		
		JButton button_1 = new JButton("新增");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddFrame addFrame=new AddFrame();
				addFrame.setVisible(true);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 30));
		button_1.setBounds(625, 341, 113, 56);
		contentPane.add(button_1);
	}

	protected void deleteInfo() throws SQLException {
		int row=table.getEditingRow();
		if(row<0){
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "确认删除电商平台系统项目！");
			if (index==JOptionPane.OK_OPTION) {
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				BaseDao baseDao=new BaseDao();
				baseDao.deleteProject(id);
				this.dispose();
				ListFrame listFrame=new ListFrame();
				listFrame.setVisible(true);
			}
		}
	}
}
