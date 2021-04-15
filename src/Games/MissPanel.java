package Games;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MissPanel extends JPanel {
	
	private JLabel misses;
	private boolean[] flag;
	static final int NUM_ALPHA = 26;
	
	public MissPanel() {
		add(new JLabel("Missees: "));
		add(misses = new JLabel());
		flag = new boolean[NUM_ALPHA];
	}
	public void add(char c) {
		flag[c - 'a'] = true;
		misses.setText(scan());
	}
	public void reset() {
		misses.setText("");
		for (int i = 0; i < NUM_ALPHA; i ++)
			flag[i] = false;
	}
	private String scan() {
		int i, j, len = 0;
		for (i = 0; i < NUM_ALPHA; i ++) 
			if (flag[i] == true)
					len++;
		char[] buf = new char[len * 2]; // 각 단어마다 빈칸을 넣기위함
		for (i = j = 0; i < NUM_ALPHA; i ++)
			if (flag[i] == true) {
					buf[j++] = (char)(i + 'a');
					buf[j++] = ' ';
			}	
		return new String(buf);
	}	
}
