package recommenderSYS.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

	private BufferedReader bufferRead;
	private static int lineNum = 0;
	private File INPUTFILE;
	private String modifiedFILE;


	public FileProcessor(File fileIN, String modifiedFILEIN) throws FileNotFoundException{
		// TODO Auto-generated constructor stub
		INPUTFILE = fileIN;
		modifiedFILE = modifiedFILEIN;
	}

	public void ConvertFromFile(){
		try {

			File fmod = new File(modifiedFILE);
			FileWriter fw = new FileWriter(fmod);
			Scanner scan = new Scanner(INPUTFILE);
			while(scan.hasNext()){
				fw.write(scan.nextLine().replaceAll(" ", ",")+ "\n");
				lineNum++;
			}
			fw.flush();
			fw.close();
		}
		catch(FileNotFoundException fnf){
			System.out.println("File Not Found");
			fnf.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Error while reading from file at line: " + lineNum);
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
}
