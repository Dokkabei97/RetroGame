package TeamProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	JLabel lbUserID, lbRank, lbScore;
	UIHandler uih;
	UserDAO UDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
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
	public UserInfo() {
		setTitle("\uC720\uC800 \uC815\uBCF4");
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 285, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbUserID = new JLabel("");
		lbUserID.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		lbUserID.setForeground(Color.WHITE);
		lbUserID.setBounds(88, 38, 169, 53);
		contentPane.add(lbUserID);

		JLabel lblNewLabel_1 = new JLabel("\uC21C \uC704:");
		lblNewLabel_1.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(12, 101, 64, 53);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uC810 \uC218:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(12, 164, 64, 53);
		contentPane.add(lblNewLabel_1_1);

		lbRank = new JLabel("");
		lbRank.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		lbRank.setForeground(Color.WHITE);
		lbRank.setBounds(88, 101, 169, 53);
		contentPane.add(lbRank);

		lbScore = new JLabel("");
		lbScore.setForeground(Color.WHITE);
		lbScore.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
		lbScore.setBounds(88, 164, 169, 53);
		contentPane.add(lbScore);

		JLabel lblNewLabel = new JLabel(
				"\uC815\uBCF4 \uAC31\uC2E0\uC740 00\uC2DC\uC5D0 \uB429\uB2C8\uB2E4");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(12, 227, 245, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uC544 \uC774 \uB514:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(12, 38, 64, 53);
		contentPane.add(lblNewLabel_1_2);
	}
}
