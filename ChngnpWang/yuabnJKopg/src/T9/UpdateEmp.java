package T9;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateEmp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateEmp frame = new UpdateEmp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UpdateEmp(Emp emp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 631, 555);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("编号：");
		lblNewLabel.setBounds(123, 77, 72, 18);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(326, 74, 86, 24);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(emp.getNo());
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setBounds(123, 127, 72, 18);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(326, 124, 86, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(emp.getName());
		
		JLabel lblNewLabel_2 = new JLabel("生日：");
		lblNewLabel_2.setBounds(123, 187, 72, 18);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(326, 184, 86, 24);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(emp.getBirthday());
		
		JLabel lblNewLabel_3 = new JLabel("地址：");
		lblNewLabel_3.setBounds(123, 247, 72, 18);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(326, 244, 86, 24);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(emp.getAddress());
		
		JLabel lblNewLabel_4 = new JLabel("职务：");
		lblNewLabel_4.setBounds(123, 302, 72, 18);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(326, 299, 86, 24);
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(emp.getPost());
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定要修改该员工信息吗？","修改确认",JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					String no = textField.getText();
					String name = textField_1.getText();
					String birthday = textField_2.getText();
					String address = textField_3.getText();
					String post = textField_4.getText();
					Emp emp = new Emp();
					emp.setNo(no);
					emp.setName(name);
					emp.setBirthday(birthday);
					emp.setAddress(address);
					emp.setPost(post);
					ExecuteStatement es = new ExecuteStatement();
					boolean isUpdate = es.updateData(emp);
					if(isUpdate) {
						dispose();
						EmpList el = new EmpList();
						el.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "修改员工信息失败");
					}
				}
			}
		});
		btnNewButton.setBounds(104, 420, 113, 27);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(326, 420, 113, 27);
		panel.add(btnNewButton_1);
	}

}
