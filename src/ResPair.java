/*
 * Author : Jiyoung Hwang
 * Name   : ResPair.java
 * Date   : 10/22/2015
 * Description : having 2 resolvable clauses with heuristic function having smallest sum of the length of 2 clauses
 * 
 * */
public class ResPair implements Comparable<ResPair>{
	int i;
	int j;
	int heur;
	
	Clause ic;
	Clause jc;
	
	ResPair(){
		
	}
	
	ResPair(int i, int j, Clause ic, Clause jc){
		this.i = i;
		this.j = j;
		this.ic = ic;
		this.jc = jc;
		this.heur = setHeur();
		
	}
	public int setHeur(){
		return ic.getSize() + jc.getSize();
	}
	public String toString(){
		return "("+i+":"+ ic +","+j+":"+ jc+")\n";
	}
	public void print(){
		System.out.format("[%d:%s][%d:%s]\n",i,ic, j,jc);
	}
	
	@Override
	public int compareTo(ResPair rp) {
		// TODO Auto-generated method stub
		
		return this.heur - rp.heur;
	}
	public boolean equals(Object o){
		if(o instanceof ResPair){
			if(this.i == ((ResPair)o).i && 
			   this.j == ((ResPair)o).j)
				return true;
			else 
				return false;
		}else
			return false;
	}
	
}
