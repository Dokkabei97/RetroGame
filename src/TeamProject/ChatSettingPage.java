package TeamProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.net.*;
import java.io.*;
import java.util.*;

/*
 * ���� ���� ��ư �۵��� ������ ���� ���� �ȵ�
 * ������ ���� ���α׷� ��ü ����¡ ����
 * ���� ��� ���۵�
 * */

public class ChatSettingPage extends JFrame {

	private JPanel contentPane;
	JTextField tfNotice;
	JPanel panel = new JPanel();
	JButton btnNotice, btnServerOn, btnServerOff;
	JTextArea taState;
	ChatGui chat;

	boolean chkServer = false;
	private ServerSocket server = null;
	private final int port = 9999;
	Vector<ChatHandler> userV = new Vector<>();

	class MyThread extends Thread {

		@Override
		public void run() {
			synchronized (ChatSettingPage.this) {
				while (true) {
					try {
						Socket sock = server.accept();
						System.out.println(sock.getInetAddress() + " Connect");
						ChatHandler chat = new ChatHandler(sock, userV);
						chat.start();
					} catch (IOException e) {
						System.out.println("ChatSever run()���� ����: " + e);
					}
				}
			}
		}
	}

	MyThread tr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatSettingPage frame = new ChatSettingPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatSettingPage() {
		setTitle("\uCC44\uD305\uC11C\uBC84\uD328\uB110");
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chat = new ChatGui();

		btnServerOn = new JButton("\uC11C\uBC84 \uC2E4\uD589");
		btnServerOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkServer == false || server == null) {
					serverOn();
					if (tr == null) {
						tr = new MyThread();
						if (!tr.isAlive())
							tr.start();
					}
				} else {
					taState.append("������ �̹� ����Ǿ����ϴ�\r\n");
					return;
				}
			}
		});
		btnServerOn.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		btnServerOn.setForeground(Color.WHITE);
		btnServerOn.setBackground(Color.DARK_GRAY);
		btnServerOn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnServerOn.setBounds(11, 221, 129, 50);
		contentPane.add(btnServerOn);

		tfNotice = new JTextField();
		tfNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendNotice();
			}
		});
		tfNotice.setFont(new Font("����", Font.PLAIN, 12));
		tfNotice.setBounds(12, 170, 410, 35);
		contentPane.add(tfNotice);
		tfNotice.setColumns(10);

		btnServerOff = new JButton("\uC11C\uBC84 \uC885\uB8CC");
		btnServerOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkServer == true || server != null) {
					serverOff();
//					if (tr != null) {
//						tr = new MyThread();
//						if (tr.isAlive()) {
//							try {
//								tr.sleep(1000);
//							} catch (InterruptedException e1) {
//								e1.printStackTrace();
//							}
//							tr.interrupt();
//							tr = null;
//						}
//					}
				} else {
					taState.append("������ �̹� ����Ǿ����ϴ�\r\n");
					return;
				}
			}
		});
		btnServerOff.setForeground(Color.WHITE);
		btnServerOff.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		btnServerOff.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnServerOff.setBackground(Color.DARK_GRAY);
		btnServerOff.setBounds(293, 221, 129, 50);
		contentPane.add(btnServerOff);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 10, 411, 143);
		contentPane.add(scrollPane);

		taState = new JTextArea();
		taState.setEditable(false);
		taState.setText("���� ���ุ ���� �۵��˴ϴ�\r\n");
		taState.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		taState.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null),
				"\uCC44\uD305 \uC11C\uBC84 \uC0C1\uD0DC", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		scrollPane.setViewportView(taState);

		JComponent gp = (JComponent) this.getGlassPane();
		gp.setLayout(null);
		gp.add(panel);

		panel.setOpaque(false);
		panel.setBounds(299, 172, 110, 30);
		panel.setLayout(null);

		btnNotice = new JButton("\uACF5\uC9C0 \uBCF4\uB0B4\uAE30");
		btnNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendNotice();
			}
		});
		btnNotice.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNotice.setBackground(Color.DARK_GRAY);
		btnNotice.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		btnNotice.setForeground(Color.WHITE);
		btnNotice.setBounds(0, 0, 111, 33);
		panel.add(btnNotice);

		gp.setVisible(true);
	}

	protected void sendNotice() {
		String str = tfNotice.getText();
		taState.append("������ ���½��ϴ�: " + str + "\r\n");
		tfNotice.setText("");
	}

	protected void serverOff() {
		chkServer = false;
		taState.append("ä�� ������ �����մϴ�\r\n");
		try {
			if (server != null) {
				server.close();
				server = null;
				System.out.println("Sever Off");
			}
		} catch (IOException e) {
			System.out.println("serverOff()���� ����: " + e);
		}
	}

	protected void serverOn() {
		chkServer = true;
		taState.append("ä�� ������ �����մϴ�\r\n");
		try {
			server = new ServerSocket(port);
			System.out.println("ChatServer Starting");
			System.out.println("Port: " + port + " waiting");
		} catch (IOException e) {
			System.out.println("serverOn()���� ����: " + e);
		}
	}

}
