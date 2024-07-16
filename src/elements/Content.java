package elements;

public class Content {
	private final String text;
	private static Integer generalId = 0;
	private final Integer id;
	private boolean published;

	public Content(String text) {
		this.text = text;
		this.id= generalId++;
		this.published=false;
	}

	public String getText() {
		return text;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setVisibility() {
		published=true;
	}
	
	public boolean getVisibility() {
		return published;
	}
	
}
	
