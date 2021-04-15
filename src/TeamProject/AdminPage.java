package TeamProject;

import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame{

//	private JFrame frame;
	private JTable tbUserInfo;
	JTextField tfIDX, tfName, tfTel, tfID, tfEmail, tfRank, tfScore, tfAdmin;
	DefaultTableModel model;
	JButton btnUpdate, btnDelte, btnInquiry, btnSearch;
	UserDAO UDAO;
	Object[][] data;
	String[] col;
	ChatSettingPage csp;

	public enum state {
		none, update, delete, search
	}

	state mode = state.none;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage window = new AdminPage();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminPage() {
		initialize();
	}

	private void initialize() {
//		frame = new JFrame();
		setResizable(false);
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("\uAD00\uB9AC\uC790\uD328\uB110");
		setBounds(100, 100, 550, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		UDAO = new UserDAO();
		csp = new ChatSettingPage();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 510, 162);
		getContentPane().add(scrollPane);

		tbUserInfo = new JTable();
		tbUserInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o == tbUserInfo) {
					clickTable();
				}
			}
		});
		tbUserInfo.setForeground(Color.BLACK);
		tbUserInfo.setBackground(Color.WHITE);
		tbUserInfo.setModel(model = new DefaultTableModel(
				data = new Object[][] { { null, null, null, null, null, null, null, null, null }, },
				col = new String[] { "IDX", "ID", "NAME", "EMAIL", "TEL", "RANK", "SCORE", "INDATE", "ADMIN" }));
		scrollPane.setViewportView(tbUserInfo);

		tfIDX = new JTextField();
		tfIDX.setBounds(100, 186, 130, 30);
		getContentPane().add(tfIDX);
		tfIDX.setColumns(10);

		JLabel lbIDX = new JLabel("\uC544\uC774\uB514 \uBC88\uD638:");
		lbIDX.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbIDX.setForeground(Color.WHITE);
		lbIDX.setBackground(Color.WHITE);
		lbIDX.setHorizontalAlignment(SwingConstants.LEFT);
		lbIDX.setBounds(12, 185, 80, 30);
		getContentPane().add(lbIDX);

		btnUpdate = new JButton("\uC218 \uC815");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == state.none) {
					if (chkEmpty() == false)
						return;
					mode = state.update;
				} else {
					if (chkEmpty() == false)
						return;
					UDAO.updateUser(info());
					clearTf();
					mode = state.none;
				}
				setTfEditalbe(mode);
				getUserList();
			}
		});
		btnUpdate.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnUpdate.setBounds(12, 351, 85, 50);
		getContentPane().add(btnUpdate);

		btnSearch = new JButton("\uAC80 \uC0C9");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBtnListener();
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.DARK_GRAY);
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnSearch.setBounds(437, 351, 85, 50);
		getContentPane().add(btnSearch);

		btnDelte = new JButton("\uC0AD \uC81C");
		btnDelte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delBtnListener();
			}
		});
		btnDelte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDelte.setForeground(Color.WHITE);
		btnDelte.setBackground(Color.DARK_GRAY);
		btnDelte.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnDelte.setBounds(109, 351, 85, 50);
		getContentPane().add(btnDelte);

		btnInquiry = new JButton("\uC870 \uD68C");
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getUserList();
				clearTf();
			}
		});
		btnInquiry.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnInquiry.setBackground(Color.DARK_GRAY);
		btnInquiry.setForeground(Color.WHITE);
		btnInquiry.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnInquiry.setBounds(340, 351, 85, 50);
		getContentPane().add(btnInquiry);

		JLabel lbName = new JLabel("\uC774 \uB984:");
		lbName.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbName.setForeground(Color.WHITE);
		lbName.setHorizontalAlignment(SwingConstants.LEFT);
		lbName.setBounds(12, 221, 80, 30);
		getContentPane().add(lbName);

		JLabel lbID = new JLabel("\uC544 \uC774 \uB514:");
		lbID.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbID.setForeground(Color.WHITE);
		lbID.setHorizontalAlignment(SwingConstants.LEFT);
		lbID.setBounds(300, 182, 80, 30);
		getContentPane().add(lbID);

		JLabel lbEmail = new JLabel("\uC774 \uBA54 \uC77C:");
		lbEmail.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbEmail.setForeground(Color.WHITE);
		lbEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lbEmail.setBounds(300, 221, 80, 30);
		getContentPane().add(lbEmail);

		JLabel lbTel = new JLabel("\uC5F0 \uB77D \uCC98:");
		lbTel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbTel.setForeground(Color.WHITE);
		lbTel.setHorizontalAlignment(SwingConstants.LEFT);
		lbTel.setBounds(12, 257, 80, 30);
		getContentPane().add(lbTel);

		JLabel lnRank = new JLabel("\uB7AD \uD0B9:");
		lnRank.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lnRank.setForeground(Color.WHITE);
		lnRank.setHorizontalAlignment(SwingConstants.LEFT);
		lnRank.setBounds(300, 257, 80, 30);
		getContentPane().add(lnRank);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(100, 222, 130, 30);
		getContentPane().add(tfName);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(100, 258, 130, 30);
		getContentPane().add(tfTel);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(392, 186, 130, 30);
		getContentPane().add(tfID);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(392, 222, 130, 30);
		getContentPane().add(tfEmail);

		tfRank = new JTextField();
		tfRank.setColumns(10);
		tfRank.setBounds(392, 258, 130, 30);
		getContentPane().add(tfRank);

		tfScore = new JTextField();
		tfScore.setColumns(10);
		tfScore.setBounds(100, 294, 130, 30);
		getContentPane().add(tfScore);

		tfAdmin = new JTextField();
		tfAdmin.setColumns(10);
		tfAdmin.setBounds(392, 294, 130, 30);
		getContentPane().add(tfAdmin);

		JLabel lbScore = new JLabel("\uC810 \uC218:");
		lbScore.setHorizontalAlignment(SwingConstants.LEFT);
		lbScore.setForeground(Color.WHITE);
		lbScore.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbScore.setBounds(12, 293, 80, 30);
		getContentPane().add(lbScore);

		JLabel lbAdmin = new JLabel("\uAD00\uB9AC\uC790 \uAD8C\uD55C:");
		lbAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lbAdmin.setForeground(Color.WHITE);
		lbAdmin.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbAdmin.setBounds(300, 293, 80, 30);
		getContentPane().add(lbAdmin);
		
		JButton btnChatSet = new JButton("\uCC44\uD305 \uC124\uC815");
		btnChatSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csp.setVisible(true);
				csp.setLocationRelativeTo(null);
			}
		});
		btnChatSet.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnChatSet.setForeground(Color.WHITE);
		btnChatSet.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChatSet.setBackground(Color.DARK_GRAY);
		btnChatSet.setBounds(206, 351, 122, 50);
		getContentPane().add(btnChatSet);

		setTfInit();
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void getUserList() {
		ArrayList<UserDTO> arr = UDAO.inquiryUser();
		showUserTable(arr);
	}

	private void showUserTable(ArrayList<UserDTO> arr) {
		data = new Object[arr.size()][9];
		for (int i = 0; i < data.length; i++) {
			UserDTO dto = arr.get(i);
			data[i][0] = dto.getIdx();
			data[i][1] = dto.getId();
			data[i][2] = dto.getName();
			data[i][3] = dto.getEmail();
			data[i][4] = dto.getTel();
			data[i][5] = dto.getRank();
			data[i][6] = dto.getScore();
			data[i][7] = dto.getDate();
			data[i][8] = dto.getAdmin();
		}
		model = new DefaultTableModel(data, col);
		tbUserInfo.setModel(model);
	}

	public void setTfInit() {
		tfIDX.setEditable(false);
		tfID.setEditable(false);
		tfName.setEditable(false);
		tfEmail.setEditable(false);
		tfTel.setEditable(false);
		tfRank.setEditable(false);
		tfScore.setEditable(false);
		tfAdmin.setEditable(false);
	}

	public void setTfEditalbe(state mode) {
		this.setTfInit();

		switch (mode) {
		case update:
			tfName.setEditable(true);
			tfEmail.setEditable(true);
			tfTel.setEditable(true);
			tfRank.setEditable(true);
			tfScore.setEditable(true);
			tfAdmin.setEditable(true);
			break;
		case delete:
		case search:
			tfIDX.setEditable(true);
			break;
		}
	}

	public UserDTO info() {
		UserDTO dto = new UserDTO();

		int idx = Integer.parseInt(tfIDX.getText());
		String id = tfID.getText();
		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();
		int rank = Integer.parseInt(tfRank.getText());
		int score = Integer.parseInt(tfScore.getText());
		int admin = Integer.parseInt(tfAdmin.getText());

		dto.setIdx(idx);
		dto.setId(id);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);
		dto.setRank(rank);
		dto.setScore(score);
		dto.setAdmin(admin);

		return dto;
	}

	public void clickTable() {

		int row = tbUserInfo.getSelectedRow();
		Integer idx = (Integer) tbUserInfo.getValueAt(row, 0);
		if (idx == 0)
			return;
		UserDTO dto = UDAO.selectInfo(idx);
		if (dto == null) {
			showMsg(idx + "번 유저는 없습니다");
			return;
		}
		String id = dto.getId();
		String name = dto.getName();
		String email = dto.getEmail();
		String tel = dto.getTel();
		int rank = dto.getRank();
		int score = dto.getScore();
		int admin = dto.getAdmin();

		tfIDX.setText(idx.toString());
		tfID.setText(id);
		tfName.setText(name);
		tfEmail.setText(email);
		tfTel.setText(tel);
		tfRank.setText(String.valueOf(rank));
		tfScore.setText(String.valueOf(score));
		tfAdmin.setText(String.valueOf(admin));
	}

	public boolean chkEmpty() {

		String name = tfName.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();
		String rank = tfRank.getText();
		String score = tfScore.getText();
		String admin = tfAdmin.getText();

		if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty() || tel == null
				|| tel.trim().isEmpty() || rank == null || rank.trim().isEmpty() || score == null
				|| score.trim().isEmpty() || admin == null || admin.trim().isEmpty()) {
			showMsg("칸을 전부 입력해주세요");
			return false;
		}
		return true;
	}

	public boolean chkIdxEmpty() {
		String idx = tfIDX.getText();
		if (idx == null || idx.trim().isEmpty()) {
			showMsg("아이디 번호를 입력해주세요");
			return false;
		}
		return true;
	}

	public void searchBtnListener() {
		String idx = tfIDX.getText();

		if (mode == state.none) {
			mode = state.search;
		} else {
			if (idx == null || idx.trim().isEmpty()) {
				showMsg("검색할 유저 번호를 입력해주세요");
				return;
			} else {
				searchUser();
				mode = state.none;
			}
		}
		setTfEditalbe(mode);
	}

	public void searchUser() {
		UserDTO dto = new UserDTO();
		int idx = Integer.parseInt(tfIDX.getText());

		dto.setIdx(idx);
		
		ArrayList<UserDTO> arr = UDAO.searchUser(dto);
		showUserTable(arr);
		clearTf();
	}

	public void delBtnListener() {

		String idx = tfIDX.getText();

		if (mode == state.none) {
			if (idx == null || idx.trim().isEmpty()) {
				showMsg("삭제할 유저 정보를 유저 테이블에서 선택해주세요");
				return;
			}
			mode = state.delete;
		} else {
			int yn = JOptionPane.showConfirmDialog(null, idx + "번 유저를 삭제 하겠습니까?", null, JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_NO_OPTION) {
				deleteUser();
			}
			mode = state.none;
		}
		setTfEditalbe(mode);
	}

	public void deleteUser() {
		UserDTO dto = new UserDTO();
		int idx = Integer.parseInt(tfIDX.getText());

		dto.setIdx(idx);

		UDAO.deleteUser(dto);
		getUserList();
		clearTf();
	}
	
	public void clearTf() {
		tfIDX.setText("");
		tfID.setText("");
		tfName.setText("");
		tfEmail.setText("");
		tfTel.setText("");
		tfRank.setText("");
		tfScore.setText("");
		tfAdmin.setText("");
	}
}
