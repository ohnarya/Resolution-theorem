/*
 * Author : Jiyoung Hwang
 * Name   : SportShop.java
 * Date   : 10/22/2015
 * Description : prove propositional theorem using resolution refutation
 *               1) make a CNF form for each clause
 *               2) add negated desired query(-q)
 *               3) resolve until meeting empty clause or no more resolvable clauses
 *               4) if empty clause exits : success, otherwise : fail
 * 
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SportShop {
	
	ArrayList<Clause>      clauses    = new ArrayList<Clause>();
	PriorityQueue<ResPair> candidates = new PriorityQueue<ResPair>();
	int iter = 0;
	
	public void prooftree(Clause c, String i){

		System.out.print(i+ c.index + ": ");
		System.out.print( c.clause==null?"Empty":c );
		System.out.println( c.l == ""?" [input]":" ["+c.l+","+c.r+"]");
		
		if(c.left !=null)
			prooftree(c.left,i+" ");
		if(c.right !=null)
			prooftree(c.right,i+" ");
	}
	
	
	/*
	 * run resolution to proof the entailment
	 * */

	public  Clause resolution(){

		/*check resolvable with initial KB*/
		checkResolution();

		/*util not resolvable */
		while(!candidates.isEmpty()){
			System.out.format("iteration=%d, queue size=%d ",iter,candidates.size());
			
			/*pick one */
			ResPair rp = candidates.poll();
			System.out.format(", resolution on %d and %d\n",rp.i,rp.j);
			/*resolve one at a time*/
			Clause c = resolve(rp);
					
//			for(Clause c: resolvent){
				System.out.format("resolving (%s) and (%s)\n", rp.ic, rp.jc);
				System.out.format("%d: %s generated\n",iter,c.clause==null?"Empty clause":c);
				/*make a relation*/
				c.left  = rp.ic;
				c.right = rp.jc;
				c.l     = ""+rp.i;
				c.r     = ""+rp.j;
				c.index = iter;
				iter++;
				/*if it is empty clause -- success*/
				if(c.isEmpty()){
					return c;
				}
				else if(!clauses.contains(c)){
					clauses.add(c);
					/*check resolvable with clauses*/
					checkResolution(clauses.size()-1);
				}
//			}				
		}
		return null;
	}
	/*
	 * resolve - resolve 2 resolvable clauses and create a new clause 
	 * 
	 */
	public Clause resolve(ResPair rp){
		/*return value*/
		ArrayList<Clause> ret = new ArrayList<Clause>();

		
		Clause c1, c2, c=null;
		HashMap<String, Boolean> e1 = new HashMap<String, Boolean>();
		HashMap<String, Boolean> e2 = new HashMap<String, Boolean>(); 
		HashMap<String, Boolean> e  = new HashMap<String, Boolean>(); 
		
		/*get the clauses in respair*/
		c1 = rp.ic;
		c2 = rp.jc;
		
		e1.putAll(c1.elements);
		e2.putAll(c2.elements);
		
		for(String key : c1.elements.keySet()){
			/*if there are elements to be resolved*/
			if(c2.elements.containsKey(key)){
				c = new Clause();
				
				e1.remove(key);
				e2.remove(key);
				e.putAll(e1);
				/*check if there are the same keys*/
				for(String subkey : e2.keySet()){
					if(e.containsKey(subkey)){
						if(e.get(subkey)==e2.get(subkey))
							e.put(subkey, e2.get(subkey));
						else
							e.remove(subkey);
					}else{
						e.put(subkey, e2.get(subkey));
					}
				}
				c.elements = e;
				c.clause   = c.buildClause();
				
				ret.add(c);
				break;
			}
		}
		return c;
	}	
	/*
	 * check if there are resolvable clauses out of clauses newly added 
	 * 
	 * */	
	public void checkResolution(int j){
		for(int i = 0; i<clauses.size()-1;i++){
			if(clauses.get(i).isResolved(clauses.get(j))){

				ResPair rp = new ResPair(i,j, clauses.get(i), clauses.get(j));
				if(!candidates.contains(rp)){
					//System.out.format("resolvable : [%d: %s] [%d: %s]\n", i, clauses.get(i), j, clauses.get(j));
					candidates.add(rp);
				}
				
			}
		}
	}
	/*
	 * check if there are resolvable clauses 
	 * 
	 * */
	public void checkResolution(){
		for(int i = 0; i<clauses.size()-1;i++){
			for(int j = i+1; j<clauses.size();j++){
				if(clauses.get(i).isResolved(clauses.get(j))){

					ResPair rp = new ResPair(i,j, clauses.get(i), clauses.get(j));
					
					if(!candidates.contains(rp)){
						//System.out.format("resolvable : [%d: %s] [%d: %s]\n", i, clauses.get(i), j, clauses.get(j));
						candidates.add(rp);
					}
					
				}
			}
		}
	}
	/*
	 * initialize clauses with the given facts
	 * 
	 * */
	public  boolean initialize(String filename){
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String curline;

			while((curline = br.readLine()) != null){
				/*ignore empty lines*/
				if(curline.trim().length()<1)
					continue;
				/*ignore comments*/
				Pattern p = Pattern.compile("[#]");
				Matcher m = p.matcher(curline);
				
				if(!m.find()){
					Clause c = new Clause();
					c.clause = curline;
					c.insertElement();
					clauses.add(c);
					c.index = iter;
					System.out.println(""+ (iter++) +": " + c);
				}
					
			}
		}catch(IOException e){
			System.out.format("Cannot Open the input file(%s). \nput an input file in the same directory of jar file or /src/",filename);
			return false;
		}		
		return true;
	}
	

	/*
	 * validate inputs
	 * */
	public boolean validateInputs(String[] args){
		boolean ret = true;

		if(args.length!= 1){
			ret =  false;
		}
		
		Pattern p = Pattern.compile("\\w+.kb");
		Matcher m = p.matcher(args[0]);
		
		if(ret && !m.find()){		
			ret =  false;
		}
		if(!ret){
			System.out.println("example: java -jar SportShop.jar xxxxx.kb");
			return false;
		}else 
			return true;
	}

	/*
	 * main function
	 * */
	public static void main(String args[]){
		
		SportShop ss = new SportShop();
		
		if(!ss.validateInputs(args))
			return;
		
		ss.initialize(args[0]);
		Clause c = ss.resolution();
		
		if(c!= null){
			System.out.println("Success!! empty clause found");
			ss.prooftree(c,"");
		}
		
	}	
}
