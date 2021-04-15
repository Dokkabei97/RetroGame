package Games;

import java.awt.*;
import javax.swing.*;

public class WordPanel extends JPanel {

	private JTextField tf;
	private String word;
	private int len;
	private char[] buf;

	public WordPanel() {
		add(new JLabel("Word: "));
		add(tf = new JTextField(12));
		tf.setEditable(false);
		tf.setHorizontalAlignment(JTextField.CENTER);
	}

	public void setWord(String word) {
		this.word = word;
		len = word.length();
		buf = new char[len];
		for (int i = 0; i < len; i++)
			buf[i] = '_';
		tf.setText(new String(buf));
	}

	String getWord() {
		return word;
	}

	int getWordLength() {
		return len;
	}

	boolean match(char c) {
		boolean found = false;
		for (int i = 0; i < len; i++)
			if (word.charAt(i) == c) {
				buf[i] = c;
				found = true;
			}
		tf.setText(new String(buf));
		return found;
	}

	boolean isAllMatched() {
		for (int i = 0; i < len; i++)
			if (buf[i] == '_')
				return false;
		return true;
	}
}
