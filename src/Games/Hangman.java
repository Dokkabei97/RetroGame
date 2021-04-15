package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hangman extends JFrame implements ActionListener{
	Words wrd;
	
	public static void main(String[] args) {
		new Hangman();
		
	}
	public Hangman(){
		setSize(400, 170);
		setTitle("::Hangman::");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		makeUI();
		setVisible(true);
	}
	static final int COUNT = 6;
	private WordPanel wp;
	private CountPanel cp;
	private JTextField tf;
	private DrawPanel dp;
	private MissPanel mp;

	
	private void makeUI() {
		wrd = new Words("src/Games/Words.txt");
		wp = new WordPanel();
		wp.setWord(wrd.getWord());
		cp = new CountPanel();
		cp.setCount(COUNT);
		
		JPanel gp = new JPanel();
		gp.add(new JLabel("Guess: "));
		//gp.add(new JTextField(1));
		gp.add(tf = new JTextField(1));
		tf.addActionListener(this);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(4, 1));
		right.add(wp);
		right.add(gp);
		right.add(mp = new MissPanel());
		right.add(cp);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		add(dp = new DrawPanel());
		add(right);
	}
	public void actionPerformed(ActionEvent e) {
		char c = tf.getText().charAt(0);
		tf.setText("");
		if (wp.match(c) == false) {
			mp.add(c);
			dp.drawNext();
			if (cp.decrement() == 0) {
				JOptionPane.showMessageDialog(this, "Game Over\n" + "정답은: " + wp.getWord());
			reset();
		}
			
	} else if (wp.isAllMatched()) {
		JOptionPane.showMessageDialog(this, "축하합니다!! \n" + "정답은: " + wp.getWord());
		reset();
	}
	
}
	private void reset() {
		wp.setWord(wrd.getWord());
		cp.setCount(6);
		dp.reset();
		mp.reset();
		
	}
}