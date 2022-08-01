package T9;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.server.ExportException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpList frame = new EmpList();
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
	public EmpList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 760, 414);
		contentPane.add(scrollPane);
		
		table = new JTable();
		ExecuteStatement es = new ExecuteStatement();
		List<Emp> emps = es.queryEmp();
		Object [][] empObjs = new Object[emps.size()][5];
		for(int i=0;i<emps.size();i++) {
			empObjs[i][0] = emps.get(i).getNo();
			empObjs[i][1] = emps.get(i).getName();
			empObjs[i][2] = emps.get(i).getBirthday();
			empObjs[i][3] = emps.get(i).getAddress();
			empObjs[i][4] = emps.get(i).getPost();
		}
		table.setModel(new DefaultTableModel(
				empObjs,
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u751F\u65E5", "\u5730\u5740", "\u804C\u52A1"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定要删除吗？", "删除提示", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					//选中的是哪一行
					int row = table.getSelectedRow();
					//选中行中的第一列数据
					String no = table.getValueAt(row, 0).toString();
					ExecuteStatement es = new ExecuteStatement();
					boolean isDelete = es.deleteByNo(no);
					if(isDelete) {
							dispose();
							EmpList el = new EmpList();
							el.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "删除员工信息失败");
					}
				}
			}
		});
		btnNewButton.setBounds(51, 23, 113, 27);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("编辑");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//选中的是哪一行
				int row = table.getSelectedRow();
				//选中行中的第一列数据
				String no = table.getValueAt(row, 0).toString();
				String name = table.getValueAt(row, 1).toString();
				String birthday = table.getValueAt(row, 2).toString();
				String address = table.getValueAt(row, 3).toString();
				String post = table.getValueAt(row, 4).toString();
				Emp emp = new Emp();
				emp.setNo(no);
				emp.setName(name);
				emp.setBirthday(birthday);
				emp.setAddress(address);
				emp.setPost(post);
				dispose();
				UpdateEmp ue = new UpdateEmp(emp);
				ue.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(301, 23, 113, 27);
		contentPane.add(btnNewButton_1);
		
		
		

	}
}
