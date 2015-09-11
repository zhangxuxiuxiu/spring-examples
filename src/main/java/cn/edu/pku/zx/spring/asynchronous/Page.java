package cn.edu.pku.zx.spring.asynchronous;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 Spring uses the Jackson JSON library to convert Facebook’s JSON response into
 * a Page object. The @JsonIgnoreProperties annotation signals Spring to ignore
 * any attributes not listed in the class. This makes it easy to make REST calls
 * and produce domain objects.
 * 
 * In this guide, we are only grabbing the id and the content for demonstration
 * purposes.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

	private String name;
	private String website;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Page [name=" + name + ", website=" + website + "]";
	}

}
