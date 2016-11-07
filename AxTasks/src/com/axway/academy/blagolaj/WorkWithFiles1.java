package com.axway.academy.blagolaj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;



public class WorkWithFiles1 {
	
	private static void makeLiverpoolPerfect(String fileName) {
		
		Path filePath = Paths.get(fileName);
		Path forbiddenWordsFilePath = Paths.get("forbiddenWords");
		Charset charset = StandardCharsets.UTF_8;
		Properties replacement = new Properties();
		Set forbidden = new HashSet();
		String str;
		try {
			if (!Files.exists(filePath)) {
				Files.createFile(filePath);
				String contentFirst = "Liverpool first 10 games: Won Lost Drawn Won Won Won Won Drawn Won Won";
				System.out.println(contentFirst);
				Files.write(filePath, contentFirst.getBytes(charset));
			}
			if (Files.isDirectory(filePath)) {
				System.out.println("The 'file' wanted to modify is a directory" );
				return;
			}
				
			String content = new String(Files.readAllBytes(filePath), charset);
			
			if (content.isEmpty()) {
				System.out.println("The file wanted to modify is empty! ");
				return;
			}

				replacement.put("Drawn", "Won");
				replacement.put("Lost", "Won");
				
				forbidden = replacement.keySet();
				
				Files.write(forbiddenWordsFilePath, forbidden);
				Iterator it = forbidden.iterator();
				
					while(it.hasNext()) {
						str = (String)it.next();
						if (content.contains(str)) { 
							content = content.replace(str, replacement.getProperty(str));
						} 
					}
					
					System.out.println("After modifying: " + "\n" + content);
					String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
					Path renameFilePath = Paths.get(fileName + "_modified_" + timestamp);
					Files.write(filePath, content.getBytes(charset));
					Files.move(filePath, renameFilePath, StandardCopyOption.REPLACE_EXISTING);
				
		}  catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
			String form = "LiverpoolCurrentForm";
			//reading(form);
			makeLiverpoolPerfect(form);
	}

}
