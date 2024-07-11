package elements;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Element {
	private static Integer generalID = 0;
	private final String description;
	private final Map<Integer, Content> myContents;
	private final Integer id ;
	private boolean published;
	
	public Element(String text) {
		this.description = text;
		this.myContents = new HashMap<>();
		this.id = generalID++;
		this.published=false;
	}
	
	public Content addContent(Content content) {
		return myContents.put(content.getId(), content);
	}
	
	public Stream<Content>getContents() {
		return myContents.values().stream();
	}
	
	protected String getDescritpion() {
		return description;
	}
	
	public Integer getId() {
		return id;
	}
	
	protected Content getContent(Integer id) {
		return myContents.get(id);
	}
	
	public void setVisibility() {
		published=true;
	}

	public boolean getVisibility() {
		return published;
	}

}
