/*
 * Author : Jiyoung Hwang
 * Name   : Clause.java
 * Date   : 10/22/2015
 * Description : parsing a CNF clause into propositions and check if 2 clauses are resolvable
 * 
 * */
import java.util.HashMap;


public class Clause{
	String clause;    /*each CNF*/
	int index;
	/*elements of each CNF*/
	HashMap<String, Boolean> elements = new HashMap<String, Boolean>();
	/*for tracking down*/
	Clause left=null, right=null;
	
	String l="",r="";
	
	/*get the size of element*/
	public int getSize(){
		return elements.size();
	}
	/*check if element is empty*/
	public boolean isEmpty(){
		return this.elements.size() == 0 ? true : false;
	}
	/*build a new clause after resolution*/
	public String buildClause(){
		String ret = "";
		
		if(elements.size()<1)
			return null;
		
		for(String key:elements.keySet()){
			if(!elements.get(key))
				ret +="-";
			ret += key+ " ";
		}
		
		/*get rid of white space at the end*/
		ret = ret.substring(0,ret.length()-1);
		return ret;
	}
	/*
	 * check if this clause is resolved with c
	 * */
	public boolean isResolved(Clause c){
		
		HashMap<String, Boolean> target = c.elements;
		boolean ret = false;
		
		for(String key:this.elements.keySet()){
			if(target.containsKey(key)){			
				if(target.get(key) != elements.get(key)){
					ret = true;
					break;
				}	
			}
		}
		return ret;
	}
	/*
	 * parse a line in the file and create an clause
	 * if an element start with '-', put false otherwise true
	 * 
	 * */
	public void insertElement(){
		if(clause.isEmpty())
			return;
		
		String[] token = clause.split(" ");
		
		for(String t:token){
			if(t.substring(0,1).equals("-"))
				elements.put(t.substring(1), false);
			else
				elements.put(t, true);
		}
	}
	/*
	 * override equals
	 * */
	public boolean equals(Object o){
		if(o instanceof Clause){
			if(this.clause.equals(((Clause)o).clause)){
				return true;
			}else
				return false;
			
		}else{
			return false;
		}	
	}
	/*
	 * override hashCode
	 * */
	public int hashCode(){
		return clause.hashCode();
	}
	/*
	 * override toString()
	 * */
	public String toString(){
		return this.clause;
	}
	
	/*
	 * print a clause
	 * */
	public void print(){
		System.out.println(this.clause);
	}
	
	/*
	 * print elements of a clause
	 * 
	 * */
	public void printelement(){
		for(String key : elements.keySet()){
			System.out.format("[%s%-3s] " , elements.get(key)? "":"-", key);
		}
		System.out.println();
	}
		
}
