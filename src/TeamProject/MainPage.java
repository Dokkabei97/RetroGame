package TeamProject;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.TitledBorder;

import Games.*;

import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class MainPage extends JFrame {

	// Variables declaration - do not modify
	JButton btExit, btnInfo, btnChat;
	JSplitPane jSplitPane;
	JLabel lbLogo, lbMainName;
	JList<String> ltGameList;
	JPanel p;
	JPanel pLeft;
	JScrollPane scrollPaneList;
	JTextField tfSearch;
	// End of variables declaration
	DefaultListModel listModel;
	PistolGame1 pistol;
	Hangman hangman;
	SnakeGame snake;

	private JPanel pRight;
	private JLabel lbGameName;
	private JButton btnStart;
	private JLabel lbImg;

	public MainPage() {
		setUndecorated(true);
		setResizable(false);

		p = new JPanel();
		p.setBackground(Color.DARK_GRAY);
		jSplitPane = new JSplitPane();
		jSplitPane.setBackground(Color.DARK_GRAY);
		pLeft = new JPanel();
		pLeft.setBackground(Color.DARK_GRAY);
		tfSearch = new JTextField();
		tfSearch.setForeground(Color.WHITE);
		tfSearch.setCaretColor(Color.WHITE);
		tfSearch.setBackground(Color.DARK_GRAY);
		tfSearch.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		scrollPaneList = new JScrollPane();
		scrollPaneList.setBackground(Color.DARK_GRAY);
		ltGameList = new JList<>();
		ltGameList.setForeground(Color.WHITE);
		ltGameList.setBackground(Color.DARK_GRAY);
		ltGameList.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbLogo = new JLabel();
		lbMainName = new JLabel();
		lbMainName.setForeground(Color.WHITE);
		btExit = new JButton();
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btExit.setForeground(Color.WHITE);
		btExit.setBackground(Color.DARK_GRAY);
		btExit.setFont(new Font("HY견고딕", Font.PLAIN, 18));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(new java.awt.Dimension(500, 700));
		setLocationRelativeTo(null);

		tfSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "SEARCH",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));

		tfSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tfSearchActionPerformed(evt);
			}
		});

		ltGameList.setSelectionBackground(Color.black);
		ltGameList.setSelectionForeground(Color.white);

		ltGameList.setModel(new javax.swing.AbstractListModel<String>() {
			String[] strings = { "가위바위보", "행맨", "뱀", "콜오브듀티", "사이버펑크2077", "배틀필드", "어몽어스", "하스스톤", "롤" };

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		ltGameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				ltGameListValueChanged(evt);
			}
		});
		scrollPaneList.setViewportView(ltGameList);

		javax.swing.GroupLayout pLeftLayout = new javax.swing.GroupLayout(pLeft);
		pLeftLayout.setHorizontalGroup(pLeftLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tfSearch, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
				.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE));
		pLeftLayout.setVerticalGroup(pLeftLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(pLeftLayout.createSequentialGroup()
						.addComponent(tfSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)));
		pLeft.setLayout(pLeftLayout);

		jSplitPane.setLeftComponent(pLeft);

		lbLogo.setIcon(new javax.swing.ImageIcon(("src/Img/레트로로고.png"))); // NOI18N

		lbMainName.setFont(new Font("HY견고딕", Font.PLAIN, 45)); // NOI18N
		lbMainName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbMainName.setText("MINI GAME");

		btExit.setText("X");
		btExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		//btnInfo, btnChat ActionListener는 OpenPage에 있음
		btnInfo = new JButton("\uC815 \uBCF4");
		btnInfo.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnInfo.setBackground(Color.DARK_GRAY);

		btnChat = new JButton("\uCC44 \uD305");
		btnChat.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		btnChat.setForeground(Color.WHITE);
		btnChat.setBackground(Color.DARK_GRAY);
		btnChat.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
		pLayout.setHorizontalGroup(pLayout.createParallelGroup(Alignment.TRAILING).addGroup(pLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(pLayout.createParallelGroup(Alignment.LEADING, false).addGroup(pLayout.createSequentialGroup()
						.addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE).addGap(78)
						.addComponent(lbMainName).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnInfo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(btnChat, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btExit, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(jSplitPane, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		pLayout.setVerticalGroup(pLayout.createParallelGroup(Alignment.LEADING).addGroup(pLayout.createSequentialGroup()
				.addGroup(pLayout.createParallelGroup(Alignment.LEADING).addGroup(pLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbMainName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(btExit, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(18))
						.addGroup(pLayout.createSequentialGroup().addGap(22)
								.addGroup(pLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnInfo, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChat, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addComponent(jSplitPane, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		pRight = new JPanel();
		pRight.setBackground(Color.DARK_GRAY);
		jSplitPane.setRightComponent(pRight);
		pRight.setLayout(null);

		lbGameName = new JLabel();
		lbGameName.setForeground(Color.WHITE);
		lbGameName.setBounds(110, 10, 215, 50);
		lbGameName.setBackground(Color.DARK_GRAY);
		lbGameName.setFont(new Font("HY견고딕", Font.BOLD, 18)); // NOI18N
		lbGameName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbGameName.setText("게임이름");
		lbGameName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
		pRight.add(lbGameName);

		btnStart = new JButton("");
		btnStart.setBorderPainted(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				int n = ltGameList.getSelectedIndex();
				if (o == btnStart) {
					if (n == 0) {
						pistol = new PistolGame1();
						pistol.setVisible(true);
						pistol.setLocationRelativeTo(null);
					} else if (n == 1) {
						hangman = new Hangman();
						hangman.setVisible(true);
						hangman.setLocationRelativeTo(null);
					} else if (n == 2) {
						snake = new SnakeGame();
						snake.setVisible(true);
						snake.setLocationRelativeTo(null);
					} else if (n == 3) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					} else if (n == 4) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					} else if (n == 5) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					} else if (n == 6) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					} else if (n == 7) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					} else if (n == 8) {
						showMsg("해당 게임은 프리미엄서비스 입니다");
					}
				}
			}
		});
		btnStart.setContentAreaFilled(false);
		btnStart.setIcon(new ImageIcon(MainPage.class.getResource("/Img/StartBtn.png")));
		btnStart.setBounds(101, 438, 341, 107);
		pRight.add(btnStart);

		lbImg = new JLabel("");
		lbImg.setBounds(0, 0, 454, 555);
		pRight.add(lbImg);
		p.setLayout(pLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(p,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(p,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// --생성자-----------------------------------------

	protected void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private void tfSearchActionPerformed(ActionEvent e) {
		Object o = e.getSource();

		String str = tfSearch.getText();
		if (o == tfSearch) {
			if (str == null || str.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "없는 게임입니다.");
				return;
			}

			if (str.equals("가위바위보")) {
				ltGameList.setSelectedIndex(0);
			} else if (str.equals("행맨")) {
				ltGameList.setSelectedIndex(1);
			} else if (str.equals("뱀")) {
				ltGameList.setSelectedIndex(2);
			} else if (str.equals("콜오브듀티")) {
				ltGameList.setSelectedIndex(3);
			} else if (str.equals("사이버펑크2077")) {
				ltGameList.setSelectedIndex(4);
			}else if (str.equals("배틀필드")) {
					ltGameList.setSelectedIndex(5);
			}else if (str.equals("어몽어스")) {
				ltGameList.setSelectedIndex(6);
			}else if (str.equals("하스스톤")) {
				ltGameList.setSelectedIndex(7);
			}else if (str.equals("롤")) {
				ltGameList.setSelectedIndex(8);
			} else {
				JOptionPane.showMessageDialog(this, "없는 게임입니다.");
			}

		}
	}

	private void ltGameListValueChanged(javax.swing.event.ListSelectionEvent evt) {
//      boolean bool = evt.getValueIsAdjusting();
//      if(bool == false) return;
		// String gamaName = ltGameList.getSelectedValue();
		Object o = evt.getSource();
//	   String gameSearch = tfSearch.getText();
		if (o == ltGameList) {
			int n = ltGameList.getSelectedIndex();

			if (n == 0) {
				lbImg.setIcon(new ImageIcon("src/Img/가위바위보.png"));
				lbGameName.setText("가위바위보");
			} else if (n == 1) {
				lbImg.setIcon(new ImageIcon("src/Img/행맨.jpg"));
				lbGameName.setText("행맨");
			} else if (n == 2) {
				lbImg.setIcon(new ImageIcon("src/Img/snake.jpg"));
				lbGameName.setText("뱀");
			} else if (n == 3) {
				lbGameName.setText("콜오브듀티");
			} else if (n == 4) {
				lbGameName.setText("사이버펑크2077");
			} else if (n == 5) {
				lbGameName.setText("배틀필드");
			} else if (n == 6) {
				lbGameName.setText("어멍어스");
			} else if (n == 7) {
				lbGameName.setText("하스스톤");
			} else if (n == 8) {
				lbGameName.setText("롤");
			}

		} // --

	}// ---------리스트()

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainPage().setVisible(true);
			}
		});
	}
}
