package cn.edu.pku.zx.spring.reactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 This class contains both the id and the quote text supplied from the website.
 * @JsonIgnoreProperties(ignoreUnknown=true) signals that any other attributes
 * are to be ignored.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	Long id;
	String quote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

}