/**
 * This class stores the data that each entry of HashDictionary will contain
 * @author Zhiqi Bei (Student#: 250916374)
 */
public class Configuration {
	/*
	 * the config that need to stored into the configuration object
	 */
	String config;
	/*
	 * the score that need to stored into the configuration object
	 */
	int score;
/**
 * A constructor which returns a new Configuration object with the specified configuration string and score	
 * @param config the specific config that need to stored into the configuration object
 * @param score the specific score that need to stored into the configuration object
 */
	public Configuration(String config, int score) {
		this.config = config;
		this.score = score;
	}
/**
 * this method is to get the configuration string stored in a Configuration object.	
 * @return the configuration string stored in a Configuration object
 */
	public String getStringConfiguration() {
		return this.config;
	}
/**
 * this method is to get the score stored in a Configuration object	
 * @return the score stored in a Configuration object
 */
	public int getScore() {
		return this.score;
	}

}
