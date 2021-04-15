package TeamProject;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	public JTextField tfUserID;
	public JPasswordField pfPwd;
	public JButton btnLogin;
	
	JLabel lbChk;

	OpenPage open;
	FindIdPwd fip;
	UserDAO UDAO;

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

	public Login() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLACK, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfUserID = new JTextField();
		tfUserID.setBounds(152, 147, 251, 32);
		contentPane.add(tfUserID);
		tfUserID.setColumns(10);

		pfPwd = new JPasswordField();
		pfPwd.setBounds(152, 212, 251, 32);
		contentPane.add(pfPwd);

		JLabel lbUserID = new JLabel("ID:");
		lbUserID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUserID.setForeground(Color.WHITE);
		lbUserID.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
		lbUserID.setBounds(49, 145, 91, 40);
		contentPane.add(lbUserID);

		JLabel lbPwd = new JLabel("Password :");
		lbPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPwd.setForeground(Color.WHITE);
		lbPwd.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
		lbPwd.setBounds(12, 210, 128, 40);
		contentPane.add(lbPwd);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/Img/login.png"));
		lblNewLabel.setBounds(92, 10, 278, 127);
		contentPane.add(lblNewLabel);
		
		// btnLogin ActionListener´Â OpenPage¿¡ ÀÖÀ½ 
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
			}
		});
		btnLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(152, 273, 163, 54);
		contentPane.add(btnLogin);

		lbChk = new JLabel("");
		lbChk.setForeground(Color.WHITE);
		lbChk.setHorizontalAlignment(SwingConstants.LEFT);
		lbChk.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 14));
		lbChk.setBounds(152, 187, 251, 20);
		contentPane.add(lbChk);

		JButton btnExit = new JButton("X");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnExit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 18));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(428, 10, 20, 20);
		contentPane.add(btnExit);

		JButton btnFindInfo = new JButton("Click here to find your ID or Password");
		btnFindInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFindInfo.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 17));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFindInfo.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
			}
		});
		btnFindInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fip = new FindIdPwd();
				fip.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnFindInfo.setBackground(Color.DARK_GRAY);
		btnFindInfo.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		btnFindInfo.setForeground(Color.WHITE);
		btnFindInfo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFindInfo.setBounds(49, 344, 354, 32);
		contentPane.add(btnFindInfo);
		setLocationRelativeTo(null);
	}

	public boolean empty() {

		String id = tfUserID.getText();
		String pwd = pfPwd.getText();

		if (id == null || id.trim().isEmpty() || pwd == null || pwd.trim().isEmpty()) {
			lbChk.setText("Ä­À» ÀüºÎ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
			tfUserID.requestFocus();
			return false;
		}
		return true;
	}

	public UserDTO info() {
		UserDTO dto = new UserDTO();

		String id = tfUserID.getText();
		String pwd = pfPwd.getText();

		dto.setId(id);
		dto.setPwd(pwd);

		return dto;
	}
}
