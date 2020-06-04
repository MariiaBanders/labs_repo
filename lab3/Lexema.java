package lab_3;

import java.io.IOException;
import java.nio.file.*;

import java.util.stream.Stream;
import java.util.*;


public class Lexema {
	
	private StringBuilder file = new StringBuilder();
	public C_plus_plus.Type type;
	public String lex;
	public boolean flag = false;
	public String Error = "";
	private Set<Character> st = new HashSet<Character>();
	
	public Lexema(String filePath) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))){
			stream.forEach(file::append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Error = "Fail...";
			flag = true;
			return;
		}
		
	}
	
	public void Read() {
		if (flag) {
			return;
		}
		
		if (file.length() == 0) {
			flag = true;
			return;
		}
		
		int k = 0;
		while (st.contains(file.charAt(k))) {
			k++;
		}
		if (k > 0) {
			file.delete(0, k);
		}
		
		if (lexem_find()) {
			if (file.length() == 0) {
				flag = true; return;
			}
			return;
		} else {
			type = C_plus_plus.Type.Type_trash;
			lex = file.substring(0, 1);
			file.delete(0, 1);
			while (true) {
				int stop = 0;
				for (C_plus_plus.Type T : C_plus_plus.Type.values()) {
					stop = T.Match(file.toString());
					if (stop != -1) {
						break;
					}
				}
				if (stop != -1) {
					break;
				}
				lex += file.substring(0, 1);
				file.delete(0, 1);
			}
			flag = false;
			return;
		}
	}
	
	private boolean lexem_find() {
		for (C_plus_plus.Type T : C_plus_plus.Type.values()) {
			int stop = T.Match(file.toString());
			if (stop != -1) {
				type = T;
				lex = file.substring(0, stop);
				file.delete(0, stop);
				return true;
			}
		}
		return false;
	}
	
}
