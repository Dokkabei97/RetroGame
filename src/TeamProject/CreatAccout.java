package TeamProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreatAccout extends JFrame {

	private JPanel contentPane;
	public JTextField tfID, tfName, tfEmail, tfTel;
	public JPasswordField pfPwd;
	public JLabel lbChkID;
	
	UserDAO UDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatAccout frame = new CreatAccout();
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
	public CreatAccout() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 315);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);

		UDAO = new UserDAO();

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(12, 47, 110, 30);
		getContentPane().add(lblNewLabel_1);

		tfID = new JTextField();
		tfID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				UDAO.chkUserID(info());
				if (UDAO.chkUserID(info()) == true) {
					lbChkID.setForeground(Color.red);
					lbChkID.setText("이미 있는 아이디 입니다");
					return;
				} else if (UDAO.chkUserID(info()) == false) {
					lbChkID.setForeground(Color.yellow);
					lbChkID.setText("사용 가능한 아이디 입니다");
				}
			}
		});
		tfID.setBounds(148, 48, 170, 30);
		getContentPane().add(tfID);
		tfID.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(12, 87, 129, 30);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("NAME:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(12, 127, 110, 30);
		getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("Email:");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		lblNewLabel_1_3_1.setBounds(12, 167, 110, 30);
		getContentPane().add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Tel:");
		lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_2.setForeground(Color.WHITE);
		lblNewLabel_1_3_2.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		lblNewLabel_1_3_2.setBounds(12, 207, 110, 30);
		getContentPane().add(lblNewLabel_1_3_2);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(148, 128, 170, 30);
		getContentPane().add(tfName);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(148, 168, 170, 30);
		getContentPane().add(tfEmail);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(148, 208, 170, 30);
		getContentPane().add(tfTel);

		pfPwd = new JPasswordField();
		pfPwd.setBounds(148, 88, 170, 30);
		getContentPane().add(pfPwd);

		JButton btnCA = new JButton("CreatAccount");
		btnCA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCA.setFont(new Font("HY견고딕", Font.PLAIN, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCA.setFont(new Font("HY견고딕", Font.PLAIN, 16));
			}
		});
		btnCA.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCA.setBackground(Color.DARK_GRAY);
		btnCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (empty() == true && UDAO.chkUserID(info()) == false) {
					UDAO.createAccount(info());
					setVisible(false);
					dispose();
				}
				else if (UDAO.chkUserID(info())) {
					JOptionPane.showMessageDialog(null, "사용중인 아이디입니다");
					return;
				} else if (empty() == false) {
					return;
				}
			}
		});
		btnCA.setForeground(Color.WHITE);
		btnCA.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnCA.setBounds(70, 265, 200, 30);
		getContentPane().add(btnCA);

		lbChkID = new JLabel("");
		lbChkID.setHorizontalAlignment(SwingConstants.CENTER);
		lbChkID.setForeground(Color.WHITE);
		lbChkID.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbChkID.setBounds(126, 28, 192, 20);
		contentPane.add(lbChkID);
		
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
				setVisible(false);
				dispose();
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setBounds(298, 11, 20, 20);
		contentPane.add(btnExit);

	}

	public boolean empty() {

		String id = tfID.getText();
		String pwd = pfPwd.getText();
		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();

		if (id == null || id.trim().isEmpty() || pwd == null || pwd.trim().isEmpty() || name == null
				|| name.trim().isEmpty() || email == null || email.trim().isEmpty() || tel == null
				|| tel.trim().isEmpty()) {
			lbChkID.setText("칸을 전부 입력해주세요!");
			tfID.requestFocus();
			return false;
		}
		lbChkID.setText("");
		return true;
	}

	public UserDTO info() {
		UserDTO dto = new UserDTO();

		String id = tfID.getText();
		String pwd = pfPwd.getText();
		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();

		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);

		return dto;
	}
	
	// 아이디에 admin 들어가면 회원가입 불가능
	// 아이디 4글자 이상 비밀번호 4글자 이상이어야 회원가입 가능
}
