package recommenderSYS.pearsonCoeff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class SimilarityBasedCheck {
	private String InputFile;
	public SimilarityBasedCheck(String modifiedFILEIn) {
		// TODO Auto-generated constructor stub
		InputFile = modifiedFILEIn;
	}
	
	public void ItemBasedSimilarity(){
		try {
			DataModel model = new FileDataModel(new File(InputFile));
			ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
			GenericItemBasedRecommender recommenders = new GenericItemBasedRecommender(model,itemSimilarity);
			Recommender cachingRecommender = new CachingRecommender(recommenders);
			StringBuffer sb = new StringBuffer();
			FileWriter writer = new FileWriter("output.txt");
			BufferedWriter bwriter = new BufferedWriter(writer);
			
			int totalitem = model.getNumItems();
			int numUser = model.getNumUsers();
			/*
			 * sorting the itemlist
			 */
			LongPrimitiveIterator iteratorItem = model.getItemIDs();
			ArrayList<Long> itemList = new ArrayList<Long>();
			while(iteratorItem.hasNext()){
				itemList.add(iteratorItem.next());
			}
			Collections.sort(itemList);
			
			/*
			 * sorting the userlist
			 */
			LongPrimitiveIterator iteratorUser = model.getUserIDs();
			ArrayList<Long> userList = new ArrayList<Long>();
			while(iteratorUser.hasNext()){
				userList.add(iteratorUser.next());
			}
			Collections.sort(userList);
			
			/*
			 * estimating the preference
			 * and appending in the list
			 */
			int x =1;
//			ArrayList<String> line = new ArrayList<String>();
			for(long userId :userList){
				for(long itemId : itemList){
					float estimate = cachingRecommender.estimatePreference(userId, itemId);
						if(Double.isNaN(estimate)){
							estimate = (float) 1.0;
						}
						else
							sb.append(userId+" "+itemId+" "+Math.round(estimate)+"\n");
					
				}
			}
			bwriter.write(sb.toString());
			bwriter.close();
			bwriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
