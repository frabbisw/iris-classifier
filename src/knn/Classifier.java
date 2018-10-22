package knn;

import java.util.ArrayList;
import java.util.Collections;

public class Classifier 
{
	ArrayList<Row>rows;
	int k, numberOfClasses=3;	
	
	public Classifier(int k)
	{
		this.k=k;
		rows = new ArrayList<>();
	}
	public void addRow(Row r)
	{
		rows.add(r);
	}
	public double getOutput(Row newRow)
	{
		int [] cnt = new int [numberOfClasses];
		for(Row r : rows)
			r.calculateDistance(newRow);
		Collections.sort(rows);
		
		for(int i=0; i<k; i++)
			cnt[(int)rows.get(i).getOutput()-1]++;
		
		ArrayList<Vote>votes=new ArrayList<>();
		for(int i=0; i<numberOfClasses; i++)
			votes.add(new Vote(cnt[i], i+1));
			
		Vote v = java.util.Collections.max(votes);
		return v.classIndex;
	}
	public int size()
	{
		return rows.size();
	}
}