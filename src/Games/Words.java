package Games;

import java.util.*;
import java.io.*;

public class Words {
	private Random rand;
	private String[] words;
	static final int MAX = 500;
	private int len;

	public Words(String fname) {
		rand = new Random();
		try {
		BufferedReader br = new BufferedReader(new FileReader(fname));
		words = new String[MAX];
		int i;
		for (i = 0; i < MAX; i ++)
			if (((words[i]) = br.readLine()) == null){
				
				break;
			}
		len = i;
		br.close();
		} catch (IOException e) {
			System.out.println("¿À·ù");
			System.exit(0);
		}
	}
	
	String getWord() {
		return words[rand.nextInt(len)];
	}
}
