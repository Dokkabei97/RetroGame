package TeamProject;

import java.awt.*;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class OpenPage extends JFrame {

	ImageIcon icon = new ImageIcon("src/Img/πŸ≈¡»≠∏È.png");
	JPanel p = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	JPanel pC, pN, pS, pW, pE;
	JPanel pnW, pnC;
	public JButton btLogin, btCA, btClose, btStart;

	JLabel mainLabel;
	JLabel lbCompany, lbVerson;

	EtchedBorder eborder;
	
	MainPage main;
	UIHandler handler;
	Login login;
	CreatAccout ca;
	FindIdPwd fip;
	UserDAO UDAO;
	AdminPage admin;
	UserInfo uif;
	ChatGui chat;

	public OpenPage() {
		super("::MiniGame::");

		Container cp = this.getContentPane();

		cp.add(p, "Center");

		JComponent glassPane = (JComponent) this.getGlassPane();
		glassPane.setLayout(new BorderLayout());

		pC = new JPanel();
		pC.setLayout(null);
		pC.setBounds(300, 300, 300, 300);

		pN = new JPanel(new BorderLayout(10, 10));
		pnW = new JPanel();
		pnC = new JPanel();
		pS = new JPanel(new BorderLayout(10, 10));
		pW = new JPanel();
		pE = new JPanel();

		main = new MainPage();
		login = new Login();
		handler = new UIHandler(this);

		glassPane.add(pC, "Center");
		glassPane.add(pN, "North");
		glassPane.add(pS, "South");
		glassPane.add(pW, "West");
		glassPane.add(pE, "East");

		pC.setOpaque(false);
		pN.setOpaque(false);
		pS.setOpaque(false);
		pW.setOpaque(false);
		pE.setOpaque(false);
		pnW.setOpaque(false);
		pnC.setOpaque(false);

		pN.add(pnW, BorderLayout.EAST);
		pN.add(pnC, BorderLayout.CENTER);

		btClose = new JButton("Exit");
		pnW.add(btClose);
		btClose.setContentAreaFilled(false);
		btClose.setBorderPainted(false);
		btClose.setForeground(Color.white);
		btClose.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.ITALIC, 30));
		btClose.setPreferredSize(new Dimension(120, 80));

		btStart = new JButton("Touch   to   Start");

		btStart.setBounds(180, 600, 300, 80);
		pC.add(btStart);
		btStart.setContentAreaFilled(false);
		btStart.setBorderPainted(false);
		btStart.setForeground(Color.white);
		btStart.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.ITALIC, 28));

		btStart.addMouseListener(handler);
		btStart.addActionListener(handler);
		btClose.addMouseListener(handler);
		btClose.addActionListener(handler);

		btLogin = new JButton("∑Œ±◊¿Œ");
		pC.add(btLogin);
		btLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btLogin.setBackground(Color.DARK_GRAY);
		btLogin.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 16));
		btLogin.setForeground(Color.WHITE);
		btLogin.setBounds(150, 550, 150, 30);
		btLogin.addMouseListener(handler);
		btLogin.addActionListener(handler);

		btCA = new JButton("»∏ø¯∞°¿‘");
		pC.add(btCA);
		btCA.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btCA.setBackground(Color.DARK_GRAY);
		btCA.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 16));
		btCA.setForeground(Color.WHITE);
		btCA.setBounds(350, 550, 150, 30);
		btCA.addMouseListener(handler);
		btCA.addActionListener(handler);
		
		login.btnLogin.addActionListener(handler);
		main.btnInfo.addActionListener(handler);
		main.btnChat.addActionListener(handler);

		/** ----------------∂Û∫ß------------------------ */
		mainLabel = new JLabel("Retro Game");
		pC.add(mainLabel);
		mainLabel.setBounds(150, 20, 400, 400);
		mainLabel.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 55));
		mainLabel.setForeground(Color.yellow);

		lbCompany = new JLabel("®œ TIS Team2");
		pC.add(lbCompany);
		lbCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lbCompany.setBounds(230, 800, 200, 100);
		lbCompany.setForeground(Color.white);
		lbCompany.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.ITALIC, 13));

		lbVerson = new JLabel(" Version 3.115");
		pnC.setLayout(null);
		pnC.add(lbVerson);
		lbVerson.setBounds(20, 30, 140, 30);
		lbVerson.setForeground(Color.white);
		lbVerson.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 15));
		eborder = new EtchedBorder(EtchedBorder.RAISED);
		lbVerson.setBorder(eborder);

		glassPane.setVisible(true);
		setUndecorated(true);
		setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		OpenPage mainFrame = new OpenPage();
		mainFrame.setVisible(true);
		mainFrame.setSize(700, 1000);
		mainFrame.setLocationRelativeTo(null);
	}
}
