package TeamProject;

import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.NumberFormat.Style;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;

public class UIHandler extends Thread implements ActionListener, MouseListener {

	OpenPage open;
	MainPage main;
	Login login;
	CreatAccout ca;
	FindIdPwd fip;
	UserDAO UDAO;
	AdminPage admin;
	UserInfo uif;
	ChatGui chat;
	ChatHandler chatH;

	boolean chkLogin = false;
	boolean chkAdmin = false;
	int userNum;

	public UIHandler(OpenPage open) {
		this.open = open;
		this.main = open.main;
		this.login = open.login;
		this.ca = open.ca;
		this.fip = open.fip;
		this.UDAO = open.UDAO;
		this.admin = open.admin;
		this.uif = open.uif;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o == open.btStart) {
			open.btStart.setFont(new Font("HY�߰��", Font.ITALIC, 33));
			open.btStart.setBounds(130, 600, 400, 80);
		} else if (o == open.btClose) {
			open.btClose.setFont(new Font("HY�߰��", Font.ITALIC, 35));
		} else if (o == open.btLogin) {
			open.btLogin.setFont(new Font("HY�߰��", Font.BOLD, 18));

		} else if (o == open.btCA) {
			open.btCA.setFont(new Font("HY�߰��", Font.BOLD, 18));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o == open.btStart) {
			open.btStart.setFont(new Font("HY�߰��", Font.ITALIC, 28));
			open.btStart.setBounds(180, 600, 300, 80);
		} else if (o == open.btClose) {
			open.btClose.setFont(new Font("HY�߰��", Font.ITALIC, 30));
		} else if (o == open.btLogin) {
			open.btLogin.setFont(new Font("HY�߰��", Font.PLAIN, 16));
		} else if (o == open.btCA) {
			open.btCA.setFont(new Font("HY�߰��", Font.PLAIN, 16));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		Object o = e.getSource();
		ca = new CreatAccout();
		UDAO = new UserDAO();
		admin = new AdminPage();
		uif = new UserInfo();
		chat = new ChatGui();
		chatH = new ChatHandler();
		if (o == open.btStart) { // ���������� ���۹�ư
			if (chkLogin == true) { // �α��� üũ ���� Ȯ�� true�� �۵�
				main.setVisible(true);
				main.setLocationRelativeTo(null);
				open.setVisible(false);
				open.dispose();
			} else if (chkLogin == false) {
				JOptionPane.showMessageDialog(null, "�α��� �������ּ���");
				return;
			}
		} else if (o == open.btClose) { // �ݱ� ��ư
			System.exit(0);
		} else if (o == open.btLogin) { // �α��ι�ư
			login.setVisible(true);
			login.setLocationRelativeTo(null);
		} else if (o == open.btCA) { // �������� ��ư
			ca.setVisible(true);
			ca.setLocationRelativeTo(null);
		} else if (o == login.btnLogin) { // �α���ȭ�� �α��ι�ư
			if (login.empty() == true) {
				if (UDAO.userLogin(login.info()) == true) { // �α��� Ȯ�ν�
					login.setVisible(false);
					login.dispose();
					chkLogin = true;
					open.btCA.setEnabled(false);
					open.btLogin.setEnabled(false);
				}
			} else if (login.empty() == false) {
				return;
			}
		} else if (o == main.btnInfo) { // ����ȭ�� info ��ư
			if (UDAO.chkAdmin(login.info()) == false) {
				UDAO.userInfo(login.info());
				uif.lbUserID.setText(UDAO.infoId + "��");
				uif.lbRank.setText(Integer.toString(UDAO.infoRank) + "��");
				uif.lbScore.setText(Integer.toString(UDAO.infoScore) + "��");
				uif.setVisible(true);
				uif.setLocationRelativeTo(null);
			} else if (UDAO.chkAdmin(login.info()) == true) { // ���� ���� Ȯ��
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
			}
		} else if (o == main.btnChat) { // ����ȭ�� ä�� ��ư
			UDAO.chat(login.info());
			chat.userId=UDAO.chatId;
			chatH.userId=UDAO.chatId;
			chat.chatEnter();
			chat.setVisible(true);
			chat.setLocationRelativeTo(null);
		}
	}

}/////////////////////////////
