package cn.edu.pku.zx.spring.reactor;

/**
 * 
 The wrapper class has the type attribute along with a Quote. This makes it
 * easy later to use Spring’s RestTemplate and convert JSON to a POJO with the
 * Jackson binding library.
 * 
 */
public class QuoteResource {

	String type;
	Quote value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Quote getValue() {
		return value;
	}

	public void setValue(Quote value) {
		this.value = value;
	}

}
