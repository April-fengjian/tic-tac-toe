/**
 * Represents the situation in which the exception need to throw
 * @author Zhiqi Bei
 *
 */
public class DictionaryException extends RuntimeException{
	public DictionaryException(String collection) {
		super(collection);
	}

}
