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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 610, 399);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setBounds(114, 77, 72, 18);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(271, 74, 86, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(114, 153, 72, 18);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(271, 150, 86, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				ExecuteStatement es = new ExecuteStatement();
				boolean isEmp = es.queryIsEmp(userName);
				String pwd = textField_1.getText();
				if(isEmp) {
					if("123456".equals(pwd)) {
						dispose();
						EmpList el = new EmpList();
						el.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "密码错误");
					}
				}else {
					JOptionPane.showMessageDialog(null, "没有查询到该员工信息");
				}
//				String pwd = textField_1.getText();
//				if("001".equals(userName) && "123456".equals(pwd)) {
//					JOptionPane.showMessageDialog(null, "登录成功");
//				}else {
//					JOptionPane.showMessageDialog(null, "用户名或密码错误");
//				}
			}
		});
		btnNewButton.setBounds(114, 279, 113, 27);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setBounds(297, 279, 113, 27);
		panel.add(btnNewButton_1);
	}

}
