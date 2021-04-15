package Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PistolGame1 extends JFrame {

	private JPanel contentPane;
	JPanel p;
	JButton btScissors, btRock, btPaper;
	JLabel lbUserSelect, lbVS, lbComputerSelect;
	JLabel lbUserName, lbComName, lbGameName, lbRecored;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PistolGame1 frame = new PistolGame1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});// 스레드
	}

	public PistolGame1() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		p = new JPanel();
		p.setBackground(Color.DARK_GRAY);
		contentPane.add(p, BorderLayout.CENTER);
		p.setLayout(null);

		btScissors = new JButton(new ImageIcon("src/img/가위.jpg"));
		btScissors.setBounds(43, 317, 100, 100);
		p.add(btScissors);

		btRock = new JButton(new ImageIcon("src/img/바위.jpg"));
		btRock.setBounds(188, 317, 100, 100);
		p.add(btRock);

		btPaper = new JButton(new ImageIcon("src/img/보.jpg"));
		btPaper.setBounds(331, 317, 100, 100);
		p.add(btPaper);

		lbUserSelect = new JLabel("");
		lbUserSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserSelect.setBounds(43, 177, 100, 100);
		p.add(lbUserSelect);

		lbVS = new JLabel("VS");
		lbVS.setForeground(Color.WHITE);
		lbVS.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		lbVS.setHorizontalAlignment(SwingConstants.CENTER);
		lbVS.setBounds(188, 177, 100, 100);
		p.add(lbVS);

		lbComputerSelect = new JLabel("");
		lbComputerSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lbComputerSelect.setBounds(331, 177, 100, 100);
		p.add(lbComputerSelect);

		lbUserName = new JLabel("\uC720\uC800\uC544\uC774\uB514");
		lbUserName.setForeground(Color.WHITE);
		lbUserName.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserName.setBounds(43, 113, 100, 30);
		p.add(lbUserName);

		lbComName = new JLabel("COMPUTER");
		lbComName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbComName.setForeground(Color.WHITE);
		lbComName.setHorizontalAlignment(SwingConstants.CENTER);
		lbComName.setBounds(331, 113, 100, 30);
		p.add(lbComName);

		lbGameName = new JLabel("\uAC00\uC704\uBC14\uC704\uBCF4");
		lbGameName.setForeground(Color.WHITE);
		lbGameName.setFont(new Font("HY견고딕", Font.BOLD, 30));
		lbGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lbGameName.setBounds(137, 19, 193, 45);
		p.add(lbGameName);

		lbRecored = new JLabel("연승횟수: 0");
		lbRecored.setForeground(Color.WHITE);
		lbRecored.setFont(new Font("HY견고딕", Font.BOLD, 12));
		lbRecored.setHorizontalAlignment(SwingConstants.CENTER);
		lbRecored.setBounds(352, 19, 79, 30);
		p.add(lbRecored);

		btScissors.addMouseListener(new MyMouseActionListener());
		btRock.addMouseListener(new MyMouseActionListener());
		btPaper.addMouseListener(new MyMouseActionListener());

		// 랜덤한 정수를 얻는다. 1~3까지 얻고 1=가위, 2= 바위 , 3=보를 지정한다.
		// 그리고 if문으로 1~3까지 그림넣는다.

	}

	/** 가위바위보 결과 */
	int count = 0;

	private void pistolResult(int user) {

		int com = (int) (Math.random() * 3 + 1);

		System.out.println(com);
		if (com == 1) {
			lbComputerSelect.setIcon(new ImageIcon("src/img/가위.jpg"));
		} else if (com == 2) {
			lbComputerSelect.setIcon(new ImageIcon("src/img/바위.jpg"));
		} else if (com == 3) {
			lbComputerSelect.setIcon(new ImageIcon("src/img/보.jpg"));
		}

		if (user == com) {
			JOptionPane.showMessageDialog(this, "비겼다.");
			System.out.println("비겼다.");

		} else if ((user == 1 && com == 2) || (user == 2 && com == 3) || (user == 3 && com == 1)) {
			// JOptionPane.showMessageDialog(this, "졌다."+"이긴횟수:"+count);
			int result = JOptionPane.showConfirmDialog(this, "졌다. 다시하시겠습니까? 이긴횟수:" + count, "PistolGame",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.CLOSED_OPTION) {
				super.setVisible(false);
			} else if (result == JOptionPane.YES_OPTION) {
				count = 0;
				lbRecored.setText("연승횟수: " + count);
			} else {
				super.setVisible(false);
			}

		} else if ((user == 1 && com == 3) || (user == 2 && com == 1) || (user == 3 && com == 2)) {
			JOptionPane.showMessageDialog(this, "이겼다..");
			System.out.println("유져가 이겼다.");
			count++;
			lbRecored.setText("연승횟수: " + count);

		}

	}

	/** 마우스리스너 (핸들러) */
	class MyMouseActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			if (o == btScissors) {
				lbUserSelect.setIcon(new ImageIcon("src/img/가위.jpg"));
				pistolResult(1);
			} else if (o == btRock) {
				lbUserSelect.setIcon(new ImageIcon("src/img/바위.jpg"));
				pistolResult(2);
			} else if (o == btPaper) {
				lbUserSelect.setIcon(new ImageIcon("src/img/보.jpg"));
				pistolResult(3);
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}// 핸들러

}// 매인클레스
