package lab_3;

import java.io.*;

public class main {

	public static void main(String[] args) throws Exception {
		Lexema lex = new Lexema("/home/hryhorchuk117/Documents/labs/lab3/code.txt");
		FileWriter fw = new FileWriter("result.txt");
		//fw.write("Counter Type Lexema\n");
		System.out.println("Counter Type Lexema");
		//
		int cnt = 1;
		while (!lex.flag) {
			lex.Read();
			System.out.printf("%s %s %s\n", cnt, lex.type, lex.lex);
			//fw.write(cnt);
			//fw.write(lex.type.toString());
			//fw.write(lex.lex.toString());
			cnt++;
		}
		fw.close();
		if (lex.Error.isEmpty()) {
			System.out.println("Accepted");
		} else {
			System.out.println(lex.Error);
		}

	}

}
