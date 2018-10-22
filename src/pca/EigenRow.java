package pca;

import org.apache.commons.math3.linear.RealVector;

public class EigenRow implements Comparable<EigenRow>{
	double value;
	RealVector vector;
	public EigenRow(double value, RealVector vector)
	{
		this.value=value;
		this.vector=vector;
	}
	public double[] getVector()
	{
		return vector.toArray();
	}
	public int compareTo(EigenRow o) 
	{
		return this.value>o.value ? -1 : 1;
	}
}