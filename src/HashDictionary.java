/**
 * This class implements a dictionary using a hash table with separate chaining
 * @author Zhiqi Bei
 *
 */
public class HashDictionary implements DictionaryADT{
	/*
	 * A dictionary
	 */
	Node[] dictionary;
	/*
	 * The head of the linear node
	 */
	Node head = null;
	/**
	 *  initializes a dictionary with an empty hash table of the specified size
	 * @param size a specified size
	 */
	public HashDictionary(int size) {
		dictionary = new Node[size];
	}
	/**
	 *  This method is using to find a position for the given data
	 * @param data the string that stored in the configuration object
	 * @return the position that the data can store
	 */
	private static int Hashfunction(String data) {
        int hash = 0;
        int i;
        for(i = 0; i< data.length(); i++) {
        		hash = 23*hash + data.charAt(i);
        		hash = hash%7023;
        }
        return hash;
} 
	/**
	 * This method inserts the given Configuration object referenced by data in the dictionary
	 * @param data the Configuration object that need to be stored
	 * @return 1 if the insertion of the object produces a collision，0 otherwise.
	 * @throws DictionaryException if the configuration string stored in data, 
	 * is already in the dictionary
	 */
	public int put(Configuration data) throws DictionaryException {
		String key = data.getStringConfiguration();
		int position = Hashfunction(key);
		//create an node object that store the data
		Node node = new Node(data);
		Node current;
		//if there is already has at least one node, set the fist one as current,
		//and put the data at the end of the linear node.		
		if(dictionary[position]!=null) {
			current = head;
			while(current.getNext()!=null)
				current = current.getNext();
				if(current.getData().getStringConfiguration()==key) {
					throw new DictionaryException("There already has one");
				}
			current.setNext(node);
			node.setPrevious(current);
			
			return 1;
		}		
		else {
		dictionary[position] = node;
		head = node;
		return 0;
		}
	}	
/**
 * Removes the entry with configuration string config from the dictionary	
 * @param config the config need to remove
 * @throws DictionaryException  if the configuration is not in the dictionary.
 */
	public void remove(String config) throws DictionaryException{
		int position = Hashfunction(config);
		Node current = dictionary[position];
		if(current==null)
			throw new DictionaryException("There is no such data");
		//if there has at least two node					
		while(current.getNext() != null) {
			if(current.getData().getStringConfiguration() == config) {
				if(current.getPrevious()==null) {					
					Node next = current.getNext();
					next.setPrevious(null);
				}else {
					Node next = current.getNext();
					Node previous = current.getPrevious();
					previous.setNext(next);
					next.setPrevious(previous);
				}
			}
			current = current.getNext();
		}
		//if there only one node
		if(current.getNext() == null) {
			if(current.getData().getStringConfiguration() == config) {
				dictionary[position] = null;
			}	
		}		
	}
/**
 * This method is  to get the score stored in the dictionary for the given configuration string
 * @param config the given config
 * @return 	the score,or -1 if the configuration string is not in the dictionary.
 */
	public int getScore(String config) {
		int score = -1;
		int position = Hashfunction(config);
		if(dictionary[position]!=null) {
			Node current = dictionary[position];			
			while(current.getData().getStringConfiguration()!=config&&current.getNext()!=null) {
				current = current.getNext();
			}			
			if(current != null) {
					score = current.getData().getScore();			
			}			
		}
		return score;		
	}
}
