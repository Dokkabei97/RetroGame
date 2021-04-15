package TeamProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class ChatGui extends JFrame implements Runnable {
	JButton btnSend;
	JTextField tfInput;
	JTextPane tpMsg;
	JPanel panel = new JPanel();

	public String userId, host = "localhost"; // host 는 발표할때 선생님 ip주소로 변경
	final int port = 9999;
	Socket sock;
	ObjectOutputStream out;
	ObjectInputStream in;
	boolean isStop = false;
	StyledDocument doc;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGui window = new ChatGui();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatGui() {
		setTitle("\uCC44\uD305");
		initialize();
		doc = this.tpMsg.getStyledDocument();
	}

	private void initialize() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setBounds(100, 100, 400, 475);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isStop = true;
                exitProcess();
            }
        });
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 384, 375);
		getContentPane().add(scrollPane);

		tpMsg = new JTextPane();
		tpMsg.setEditable(false);
		tpMsg.setBackground(Color.DARK_GRAY);
		tpMsg.setCaretColor(Color.WHITE);
		scrollPane.setViewportView(tpMsg);

		tfInput = new JTextField();
		tfInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatMsg();
			}
		});
		tfInput.setBounds(0, 376, 384, 60);
		getContentPane().add(tfInput);
		tfInput.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		tfInput.setColumns(10);

		JComponent gp = (JComponent) this.getGlassPane();
		gp.setLayout(null);
		gp.add(panel);
		panel.setOpaque(false);
		panel.setBounds(294, 376, 90, 60);
		panel.setLayout(null);

		btnSend = new JButton("\uC804\uC1A1");
		btnSend.setBounds(0, 10, 80, 40);
		panel.add(btnSend);
		btnSend.setBackground(Color.DARK_GRAY);
		btnSend.setForeground(Color.WHITE);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chatMsg();
			}
		});
		btnSend.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnSend.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		gp.setVisible(true);
	}

	protected void exitProcess() {
		int yn = showConfirm("종료 하시겠습니까?");
        if (yn == JOptionPane.YES_OPTION) {
            //1.채팅 서버 접속하고 종료하는 경우
            if (sock != null && !sock.isClosed()) {
                try {
                    out.writeUTF("900|" + this.userId);
                    out.flush();
                } catch (IOException e) {
                    System.out.println("시스템 종료 중 예외:" + e);
                }
            } else { //2.채팅 서버에 접속하지 않고 종료하는 경우
                dispose();
            }
        }
		
	}

	private int showConfirm(String message) {
		return JOptionPane.showConfirmDialog(null, message, "확인", JOptionPane.YES_NO_OPTION);
	}

	protected void chatMsg() {
		String msg = tfInput.getText();
		if (msg == null || msg.trim().isEmpty()) {
			msg = " ";
		}
		sendMessage(msg);
		tfInput.setText("");
		tfInput.requestFocus();

	}

	public void chatEnter() { // 접속 메소드
		try {
			sock = new Socket(host, port);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());

			isStop = false;
			Thread listener = new Thread(this);
			listener.start();

			out.writeUTF("100|" + this.userId);
			out.flush();

		} catch (Exception e) {
			System.out.println("main()에서 예외: " + e);
		}
	}

	@Override
	public void run() {
		try {
			while (!isStop) {
				String serMsg = in.readUTF();
				if (serMsg == null) {
					return;
				}
				parsing(serMsg);
			}
		} catch (IOException e) {
			System.out.println("run()에서 예외: " + e);
		}

	}

	private void sendMessage(String msg) {
		msg = msg.replace('|', 'ㅣ');

		try {
			String str = "200|" + msg;
			out.writeUTF(str);
			out.flush();
		} catch (IOException e) {
			System.out.println("sendMessage() 예외: " + e);
		}
	}

	/*
	 * 100: 클라이언트 입장 정보 (100|아이디) 
	 * 200: 대화 메시지 (200|아이디) 
	 * 300: 귓속말 (300|아이디)
	 * 900: 채팅종료 (900|아이디)
	 */

	private void parsing(String msg) {
		String[] tokens = msg.split("\\|");
		switch (tokens[0]) {
		case "100":
			Object[] data = { tokens[1] };
			tokens[1] = userId;
			break;
		case "200":
			String from200 = tokens[1];
			String fromMsg = tokens[2];
			showCacaoStyle(from200, fromMsg);
			break;
		case "900":
			String exitId = tokens[1];
			exit(exitId);
			break;
		}
	}

// 채팅 스타일
	private synchronized void showCacaoStyle(String from, String fromMsg) {
		String msg = " " + from + ": " + fromMsg + "\r\n";
		JLabel lb = new JLabel(msg);
		lb.setOpaque(true);
		lb.setPreferredSize(new Dimension(700, 50));
		lb.setForeground(Color.black);
		SimpleAttributeSet attr = null;
		if (from.equals(this.userId)) {
			attr = new SimpleAttributeSet();
			StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
			lb.setBackground(Color.white);
		} else {
			attr = new SimpleAttributeSet();
			StyleConstants.setAlignment(attr, StyleConstants.ALIGN_LEFT);
			lb.setBackground(Color.yellow);
		}
		setStyle(lb, msg, attr);
	}
//귓속말(미정)
//	private synchronized void showCacaoStyle(String who, Color bgCr, String msg) {
//		JLabel lb = new JLabel(msg);
//		lb.setOpaque(true);
//		lb.setPreferredSize(new Dimension(700, 50));
//		lb.setForeground(Color.black);
//		lb.setBackground(bgCr);
//		SimpleAttributeSet attr = null;
//		if (who.equals("me")) {
//			attr = new SimpleAttributeSet();
//			StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
//		} else if (who.equals("other")) {
//			attr = new SimpleAttributeSet();
//			StyleConstants.setAlignment(attr, StyleConstants.ALIGN_LEFT);
//		}
//		setStyle(lb, msg, attr);
//	}

	private void setStyle(JLabel lb2, String msg, SimpleAttributeSet attr) {
		int offset = doc.getEndPosition().getOffset() - 1;
		tpMsg.setCaretPosition(offset);
		tpMsg.insertComponent(lb2);
		String enter = "\r\n";
		offset = doc.getEndPosition().getOffset() - 1;
		try {
			doc.insertString(offset, enter, attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		doc.setParagraphAttributes(offset + 1, msg.length()+10, attr, true);
		tpMsg.setCaretPosition(offset + 1);
	}

	private void exit(String id) {
		isStop = true;
		try {
            if (out != null)
               out.close();
           if (in != null)
               in.close();
           if (sock != null) {
               sock.close();
               sock = null;
           }
           dispose();
		}catch(IOException e) {
			System.out.println("exit()에서 예외: " + e);
		}
	}
}
