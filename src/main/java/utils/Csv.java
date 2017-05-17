package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Csv {
	
	
	public static Object[][] readCsv(String filePath) throws Exception{
		File scr = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(scr));
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((line = br.readLine())!= null){
			String[] a = line.split(",");
			list.add(a);
		}
		Object[][] object = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			object[i] = list.get(i);
		}
		br.close();
		return object;
	}

}
