import java.util.HashMap;



public class Clause{
	String clause;    /*each CNF*/
	int index;
	/*elements of each CNF*/
	HashMap<String, Boolean> elements = new HashMap<String, Boolean>();
	Clause left=null, right=null;
	String l="",r="";
	
	public int getSize(){
		return elements.size();
	}
	
	public boolean isEmpty(){
		return this.elements.size() == 0 ? true : false;
	}
	
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
	public boolean isResolved(Clause c){
		
		HashMap<String, Boolean> target = c.elements;
		boolean ret = false;
		
		for(String key:elements.keySet()){
			if(target.containsKey(key)){			
				if(target.get(key) != elements.get(key)){
					ret = true;
					break;
				}	
			}
		}
		return ret;
	}
	public void insertElement(){
		if(clause.isEmpty())
			return;
		
		String[] token = clause.split(" ");
		
		for(String t:token){
			if(t.length()>1)
				elements.put(t.substring(1), false);
			else
				elements.put(t, true);
		}
	}
	
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
	
	public int hashCode(){
		return clause.hashCode();
	}
	public String toString(){
		return this.clause;
	}
	public void print(){
		System.out.println(this.clause);
	}
	public void printelement(){
		for(String key : elements.keySet()){
			System.out.format("[%s%-3s] " , elements.get(key)? "":"-", key);
		}
		System.out.println();
	}
		
}