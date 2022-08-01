package xitongxx;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Lamborghini.ManagementDepartment;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class xtxx extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xtxx frame = new xtxx();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
	public xtxx() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 511);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagementDepartment a=new ManagementDepartment();
				a.main(null);
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 15));
		button.setBounds(70, 467, 93, 23);
		panel.add(button);
		
		JLabel label = new JLabel(" 关 于 系 统 ");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 33));
		label.setBounds(301, 37, 185, 52);
		panel.add(label);
		
		JLabel label_1 = new JLabel("系统名称：");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 21));
		label_1.setBounds(563, 108, 110, 24);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("跑车系统");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 21));
		label_3.setBounds(750, 108, 110, 24);
		panel.add(label_3);
		
		JLabel label_2 = new JLabel("版本：");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.BOLD, 21));
		label_2.setBounds(563, 179, 110, 24);
		panel.add(label_2);
		
		JLabel label_4 = new JLabel("2021-6  1.0");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Dialog", Font.BOLD, 21));
		label_4.setBounds(750, 179, 110, 24);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("开发团队：");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Dialog", Font.BOLD, 21));
		label_5.setBounds(563, 258, 110, 24);
		panel.add(label_5);
		
		JLabel lblHonor = new JLabel("HONOR");
		lblHonor.setForeground(Color.WHITE);
		lblHonor.setFont(new Font("Dialog", Font.BOLD, 21));
		lblHonor.setBounds(750, 258, 110, 24);
		panel.add(lblHonor);
		
		JLabel label_6 = new JLabel("开发者：");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Dialog", Font.BOLD, 21));
		label_6.setBounds(563, 339, 110, 24);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("田正荣");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Dialog", Font.BOLD, 21));
		label_7.setBounds(750, 339, 110, 24);
		panel.add(label_7);
		
		JLabel lblqq = new JLabel("合作联系：QQ：3136675549   手机号：18087385039");
		lblqq.setForeground(Color.WHITE);
		lblqq.setFont(new Font("Dialog", Font.BOLD, 21));
		lblqq.setBounds(206, 410, 534, 24);
		panel.add(lblqq);
		
		JLabel lbla = new JLabel("地址：云南省昆明市五华区龙翔街道128号云南农业职业技术学院A1栋413");
		lbla.setForeground(Color.WHITE);
		lbla.setFont(new Font("Dialog", Font.BOLD, 19));
		lbla.setBounds(206, 464, 668, 24);
		panel.add(lbla);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 884, 511);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(xtxx.class.getResource("/image/背景.png")));
	}
}
