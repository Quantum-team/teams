package com.order.UI;

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
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.order.dao.OrderDao;
import com.order.model.Order;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7269\u6D41\u8DDF\u8E2A\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 45));
		lblNewLabel.setBounds(349, 13, 404, 126);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 115, 987, 337);
		contentPane.add(scrollPane);
		
		//查询
		List<Order> list=new OrderDao().seleteAll();
		Object[][] data=new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getName();
			data[i][2]=list.get(i).getPrice();
			data[i][3]=list.get(i).getOrderId();
			data[i][4]=list.get(i).getDescinfo();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C", "\u8BA2\u5355\u7F16\u53F7", "\u8BA2\u5355\u63CF\u8FF0"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainJframe1 mainJframe1=new MainJframe1();
				mainJframe1.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(124, 465, 122, 56);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteOrderInfo(arg0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(853, 465, 122, 56);
		contentPane.add(button);
	}
	protected void deleteOrderInfo(ActionEvent e) throws SQLException{
		int row=table.getEditingRow();
		if (row<0) {
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "您真的真的要删除吗？");
			if (index==JOptionPane.OK_OPTION) {
				TableModel obj=table.getModel();
				int id=(int)obj.getValueAt(row, 0);
				OrderDao orderDao=new OrderDao();
				orderDao.deleteOrder(id);
//				JOptionPane.showConfirmDialog(null, "删除成功！");
				
				this.dispose();
				MainJFrame mainJFrame=new MainJFrame();
				mainJFrame.setVisible(true);
			}
		}
	}
}
