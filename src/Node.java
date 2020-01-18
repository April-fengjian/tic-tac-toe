/**
 * This class implement a node
 * @author Zhiqi Bei
 *
 */
public class Node {
	String config;
	int position;
	Configuration[] list;
	private Node next;
	private Configuration data;
	private Node previous;

/**
 * This class is to make a node using the data	
 * @param data data that need to store in the node
 */
	public Node(Configuration data) {
		next = null;
		previous = null;
		this.data = data;
	}
/**
 * This method is to get the data from the node	
 * @return the data store in the node
 */
	public Configuration getData() {
		return this.data;
	}
/**
 * This method is to set the next node	
 * @param node the node need put in to the linear node
 */
	public void setNext(Node node) {
		next = node;
		
	}
/**
 * This method is to link the previous node with the current one
 * @param node the node need to be set as the previous
 */
	public void setPrevious(Node node) {
		previous = node;
	}
/**
 * This method is to get the next code after the current one
 * @return the next node
 */
	public Node getNext() {
		return this.next;
	}
/**
 * This method is to get the previous node before the current one	
 * @return the previous node
 */
	public Node getPrevious() {
		return this.previous;
	}

}
