package recommenderSYS.driver;

import java.io.File;
import java.io.FileNotFoundException;

import recommenderSYS.pearsonCoeff.SimilarityBasedCheck;
import recommenderSYS.util.FileProcessor;

public class Driver {

	/**
	 * @param args
	 */
	public static String INPUTFILE;
	public static String modifiedFILE = "modifiedFile.txt";
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		
		
		FileProcessor fp = null;
		try{
			fp = new FileProcessor(new File("train_all_txt.txt"),modifiedFILE);
			fp.ConvertFromFile();
		}
		catch(FileNotFoundException fnf){
			System.out.println("File Not Found");
			fnf.printStackTrace();
			System.exit(1);
		}
		finally{
			
		}
		
		
		SimilarityBasedCheck simiCheck = new SimilarityBasedCheck(modifiedFILE);
		
			try {
				simiCheck.ItemBasedSimilarity();
//				simiCheck.itembasedSimilarity();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
