package elements;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public abstract class Element {
	private static Integer generalID = 1;
	private final String description;
	private final Map<Integer, Content> myContents;
	private final Integer id ;
	private boolean published;
	private Integer author;
	
	public Element(String text, Integer id) {
		this.description = text;
		this.myContents = new HashMap<>();
		this.id = generalID++;
		this.published=false;
		this.author = id;
	}
	
	public Content addContent(Content content) {
		return myContents.put(content.getId(), content);
	}
	
	public Stream<Content>getContents() {
		return myContents.values().stream();
	}
	
	public String getDescritpion() {
		return description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Content getContent(Integer id) {
		return myContents.get(id);
	}
	
	public void setVisibility() {
		published=true;
	}

	public boolean getVisibility() {
		return published;
	}

	public Integer getAuthorId() {
		return this.author;
	}
	
	public Content deleteContent(Integer id) {
		return myContents.remove(id);
	}

}
