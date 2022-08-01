package UI;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.Dao;
import model.Order;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1227, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入名字\r\n");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel.setBounds(37, 10, 313, 49);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(214, 10, 394, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.setBounds(678, 10, 138, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("录入");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new AddFrame().setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(900, 10, 138, 49);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("删除");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteInfo(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton_1_1.setBounds(1049, 125, 138, 49);
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1007, 410);
		contentPane.add(scrollPane);
		
		List<Order> list = new Dao().select(null);
		Object[][] data = new Object[list.size()][9];
		for(int i=0;i<list.size();i++){
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getOdd();
			data[i][2] = list.get(i).getName();
			data[i][3] = list.get(i).getOrder();
			data[i][4] = list.get(i).getQuantity();
			data[i][5] = list.get(i).getTelephone();
			data[i][6] = list.get(i).getAddress();
			data[i][7] = list.get(i).getNote();
			data[i][8] = list.get(i).getDate();
		}
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"序号", "单号", "名字", "订单", "数量", "电话", "地址", "备注", "日期"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void deleteInfo(ActionEvent e) throws SQLException {
		int row=table .getSelectedRow();
		if (row<0) {
			JOptionPane.showConfirmDialog(null, "选择你要删除的数据！");
		}else {
			int index=JOptionPane.showConfirmDialog(null, "你确实要删除此数据吗？");
			if (index==JOptionPane.OK_OPTION) {
				TableModel obj = table.getModel();
				int id = (int)obj.getValueAt(row, 0);
				Dao dao=new Dao();
				dao.delete(id);
				JOptionPane.showConfirmDialog(null, "数据删除成功！");
			}
			this.dispose();
			MainFrame mainFrame=new MainFrame();
			mainFrame.setVisible(true);
		}
	}

	protected void selectInfo(ActionEvent e) throws SQLException{
		String nameString=textField.getText();
		List<Order> list = new Dao().select(nameString);
		Object[][] data = new Object[list.size()][9];
		for(int i=0;i<list.size();i++){
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getOdd();
			data[i][2] = list.get(i).getName();
			data[i][3] = list.get(i).getOrder();
			data[i][4] = list.get(i).getQuantity();
			data[i][5] = list.get(i).getTelephone();
			data[i][6] = list.get(i).getAddress();
			data[i][7] = list.get(i).getNote();
			data[i][8] = list.get(i).getDate();
		}
		System.out.println(data[0][0]);
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"序号", "单号", "名字", "订单", "数量", "电话", "地址", "备注", "日期"
				}
			));
	}
}
