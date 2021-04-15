package TeamProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class FindIdPwd extends JFrame {

	private JPanel contentPane;
	public JTextField tfName, tfEmail, tfTel, tfUserID, tfName_pwd, tfEmail_pwd, tfTel_pwd;
	public JLabel lbFindID, lbFindPwd;

	Login login;
	UserDAO UDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindIdPwd frame = new FindIdPwd();
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
	public FindIdPwd() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		login = new Login();
		UDAO = new UserDAO();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setAutoscrolls(true);
		tabbedPane.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(12, 42, 476, 448);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("\uC544\uC774\uB514 \uCC3E\uAE30", null, panel, null);
		tabbedPane.setBackgroundAt(0, Color.DARK_GRAY);
		tabbedPane.setForegroundAt(0, Color.WHITE);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\uB4F1\uB85D\uD55C \uC815\uBCF4\uB85C \uCC3E\uAE30");
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(12, 29, 201, 32);
		panel.add(lblNewLabel_1);

		tfName = new JTextField();
		tfName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfName.getText();
				if (str.equals("이름"))
					tfName.setText("");
			}
		});
		tfName.setFont(new Font("굴림", Font.PLAIN, 12));
		tfName.setText("\uC774\uB984");
		tfName.setBounds(12, 64, 362, 32);
		panel.add(tfName);
		tfName.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfEmail.getText();
				if (str.equals("이메일"))
					tfEmail.setText("");
			}
		});
		tfEmail.setText("\uC774\uBA54\uC77C");
		tfEmail.setColumns(10);
		tfEmail.setBounds(12, 106, 362, 32);
		panel.add(tfEmail);

		tfTel = new JTextField();
		tfTel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfTel.getText();
				if (str.equals("연락처"))
					tfTel.setText("");
			}

		});
		tfTel.setText("\uC5F0\uB77D\uCC98");
		tfTel.setColumns(10);
		tfTel.setBounds(12, 148, 362, 32);
		panel.add(tfTel);

		JButton btnFindID = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		btnFindID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFindID.setFont(new Font("HY견고딕", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFindID.setFont(new Font("HY견고딕", Font.BOLD, 16));
			}
		});
		btnFindID.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFindID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (emptyID() == true) {
					if (UDAO.findID(idInfo()) == true) {
						setVisible(false);
						dispose();
						login.setVisible(true);
					} else if (UDAO.findID(idInfo()) == false)
						showMsg("회원정보가 틀렸습니다");
						resetIdTf();
				} else if (emptyID() == false) {
					return;
				}
			}
		});
		btnFindID.setForeground(Color.WHITE);
		btnFindID.setBackground(Color.DARK_GRAY);
		btnFindID.setFont(new Font("HY견고딕", Font.BOLD, 16));
		btnFindID.setBounds(12, 190, 128, 37);
		panel.add(btnFindID);

		lbFindID = new JLabel("");
		lbFindID.setForeground(Color.WHITE);
		lbFindID.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		lbFindID.setBounds(12, 255, 362, 40);
		panel.add(lbFindID);

		JButton btnRetrun = new JButton("\uB85C\uADF8\uC778 \uB3CC\uC544\uAC00\uAE30");
		btnRetrun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRetrun.setFont(new Font("HY견고딕", Font.PLAIN, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRetrun.setFont(new Font("HY견고딕", Font.PLAIN, 16));
			}
		});
		btnRetrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnLogin();
			}
		});
		btnRetrun.setBackground(Color.DARK_GRAY);
		btnRetrun.setForeground(Color.WHITE);
		btnRetrun.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnRetrun.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRetrun.setBounds(307, 377, 152, 32);
		panel.add(btnRetrun);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLACK, 2));
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("\uB4F1\uB85D\uD55C \uC815\uBCF4\uB85C \uCC3E\uAE30");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(12, 29, 198, 32);
		panel_1.add(lblNewLabel_1_2);

		tfUserID = new JTextField();
		tfUserID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfUserID.getText();
				if (str.equals("아이디"))
					tfUserID.setText("");
			}
		});
		tfUserID.setText("아이디");
		tfUserID.setColumns(10);
		tfUserID.setBounds(12, 64, 362, 32);
		panel_1.add(tfUserID);

		tfName_pwd = new JTextField();
		tfName_pwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfName_pwd.getText();
				if (str.equals("이름"))
					tfName_pwd.setText("");
			}
		});
		tfName_pwd.setText("\uC774\uB984");
		tfName_pwd.setColumns(10);
		tfName_pwd.setBounds(12, 106, 362, 32);
		panel_1.add(tfName_pwd);

		tfEmail_pwd = new JTextField();
		tfEmail_pwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfEmail_pwd.getText();
				if (str.equals("이메일"))
					tfEmail_pwd.setText("");
			}
		});
		tfEmail_pwd.setText("\uC774\uBA54\uC77C");
		tfEmail_pwd.setColumns(10);
		tfEmail_pwd.setBounds(12, 148, 362, 32);
		panel_1.add(tfEmail_pwd);

		tfTel_pwd = new JTextField();
		tfTel_pwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = tfTel_pwd.getText();
				if (str.equals("연락처"))
					tfTel_pwd.setText("");
			}
		});
		tfTel_pwd.setText("\uC5F0\uB77D\uCC98");
		tfTel_pwd.setColumns(10);
		tfTel_pwd.setBounds(12, 190, 362, 32);
		panel_1.add(tfTel_pwd);

		JButton btnFindPwd = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnFindPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFindPwd.setFont(new Font("HY견고딕", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFindPwd.setFont(new Font("HY견고딕", Font.BOLD, 16));
			}
		});
		btnFindPwd.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFindPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (emptyPwd() == true) {
					if (UDAO.findPWD(pwdInfo()) == true) {
						setVisible(false);
						dispose();
						login.setVisible(true);
					} else if (UDAO.findPWD(pwdInfo()) == false)
						showMsg("회원정보가 틀렸습니다");
						resetPwdTf();
				} else if (emptyPwd() == false) {
					return;
				}
			}
		});
		btnFindPwd.setForeground(Color.WHITE);
		btnFindPwd.setFont(new Font("HY견고딕", Font.BOLD, 16));
		btnFindPwd.setBackground(Color.DARK_GRAY);
		btnFindPwd.setBounds(12, 232, 145, 37);
		panel_1.add(btnFindPwd);

		lbFindPwd = new JLabel("");
		lbFindPwd.setForeground(Color.WHITE);
		lbFindPwd.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		lbFindPwd.setBounds(12, 279, 362, 40);
		panel_1.add(lbFindPwd);

		JButton btnRetrun_pwd = new JButton("\uB85C\uADF8\uC778 \uB3CC\uC544\uAC00\uAE30");
		btnRetrun_pwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRetrun_pwd.setFont(new Font("HY견고딕", Font.PLAIN, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRetrun_pwd.setFont(new Font("HY견고딕", Font.PLAIN, 16));
			}
		});
		btnRetrun_pwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnLogin();
			}
		});
		btnRetrun_pwd.setForeground(Color.WHITE);
		btnRetrun_pwd.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnRetrun_pwd.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRetrun_pwd.setBackground(Color.DARK_GRAY);
		btnRetrun_pwd.setBounds(307, 377, 152, 32);
		panel_1.add(btnRetrun_pwd);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.DARK_GRAY);

		JButton btnExit = new JButton("X");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setFont(new Font("HY견고딕", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setFont(new Font("HY견고딕", Font.PLAIN, 18));
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnExit.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setBounds(474, 11, 20, 20);
		contentPane.add(btnExit);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	protected void showMsg(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg);
	}

	protected void close() {
		// TODO Auto-generated method stub
		setVisible(false);
		dispose();
	}

	protected void returnLogin() {
		// TODO Auto-generated method stub
		login = new Login();
		login.setVisible(true);
		setVisible(false);
		dispose();
	}

	public boolean emptyID() {

		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();

		if (name == null || name.trim().isEmpty() || name.equals("이름") || email == null || email.trim().isEmpty()
				|| email.equals("이메일") || tel == null || tel.trim().isEmpty() || tel.equals("연락처")) {
			lbFindID.setText("칸을 전부 입력해주세요");
			tfName.requestFocus();
			return false;
		}
		return true;
	}

	public boolean emptyPwd() {

		String id = tfUserID.getText();
		String name = tfName_pwd.getText();
		String email = tfEmail_pwd.getText();
		String tel = tfTel_pwd.getText();

		if (id == null || id.trim().isEmpty() || id.equals("아이디") || name == null || name.trim().isEmpty()
				|| name.equals("이름") || email == null || email.trim().isEmpty() || email.equals("이메일") || tel == null
				|| tel.trim().isEmpty() || tel.equals("연락처")) {
			lbFindPwd.setText("칸을 전부 입력해주세요");
			tfUserID.requestFocus();
			return false;
		}
		return true;
	}

	public void resetIdTf() {
		tfName.setText("");
		tfEmail.setText("");
		tfTel.setText("");
	}

	public void resetPwdTf() {
		tfUserID.setText("");
		tfName_pwd.setText("");
		tfEmail_pwd.setText("");
		tfTel_pwd.setText("");
	}

	public UserDTO idInfo() {
		UserDTO dto = new UserDTO();

		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();

		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);

		return dto;
	}

	public UserDTO pwdInfo() {
		UserDTO dto = new UserDTO();

		String id = tfUserID.getText();
		String name = tfName_pwd.getText();
		String email = tfEmail_pwd.getText();
		String tel = tfTel_pwd.getText();

		dto.setId(id);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);

		return dto;
	}
}
