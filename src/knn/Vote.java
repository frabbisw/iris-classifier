package knn;

public class Vote implements Comparable<Vote>{
	int instances;
	int classIndex;
	public Vote(int instances, int classIndex)
	{
		this.instances=instances;
		this.classIndex=classIndex;
	}
	@Override
	public int compareTo(Vote o) {
		// TODO Auto-generated method stub
		return this.instances-o.instances;
	}	
}