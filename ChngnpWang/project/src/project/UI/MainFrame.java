package project.UI;

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

import project.dao.ProDao;
import project.model.Pro;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.util.Iterator;
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
		setTitle("项目管理窗口");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 906, 308);
		contentPane.add(scrollPane);
		
		ProDao dao=new ProDao();
		List<Pro> list=dao.select();
		Object[] [] data=new Object[list.size()][5];
		for (int i=0;i<list.size();i++) {
			Pro pro=new Pro();
			data[i][0]=list.get(i).getPrj_id();
			data[i][1]=list.get(i).getPrj_name();
			data[i][2]=list.get(i).getPrj_cycle();
			data[i][3]=list.get(i).getPrj_count();
			data[i][4]=list.get(i).getPrj_manager();
		}
		table = new JTable();
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
				try {
				dispose();
				new MainFrame().setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.setBounds(265, 347, 109, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(410, 347, 109, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("新增");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddFrame().setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_2.setBounds(542, 347, 109, 43);
		contentPane.add(btnNewButton_2);
	}

	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table.getEditingRow();
		if(row<0){
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "确认删除电商平台系统项目！");
			if (index==JOptionPane.OK_OPTION) {
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				ProDao baseDao=new ProDao();
				baseDao.delect(id);
				this.dispose();
				new MainFrame().setVisible(true);
			}
		}
	}
}
