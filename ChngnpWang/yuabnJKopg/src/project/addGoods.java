package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addGoods extends JFrame {

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
					addGoods frame = new addGoods(null);
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
	public addGoods(goodsList gl) {
		setTitle("添加商品信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品名称");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(63, 62, 74, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("单价");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(92, 129, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("库存");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(92, 189, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("出厂日期");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(63, 253, 64, 15);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(154, 62, 136, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 128, 138, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 188, 140, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(154, 252, 136, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("添加商品");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定要添加商品信息吗？","添加确认",JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					String id = textField.getText();
					String name = textField.getText();
					String price = textField_1.getText();
					String num = textField_2.getText();
					String date = textField_3.getText();
					goodsList gl = new goodsList();
					gl.setStoreId(id);
					gl.setStoreName(name);
					gl.setUnitPrice(price);
					gl.setStoreNum(num);
					gl.setCreateDate(date);
					ExecuteStatement es = new ExecuteStatement();
					boolean insert = es.insertData(gl);
					if(insert) {
						dispose();
						Mian main = new Mian();
						main.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "添加商品信息失败");
					}
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.setBounds(41, 364, 96, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消添加");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton_1.setBounds(234, 364, 104, 31);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("商品  ID");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(63, 24, 74, 15);
		contentPane.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(154, 23, 136, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
