package elements;

import users.AbstractUser;
import users.Role;

public class Content {
	private final String text;
	private static Integer generalId = 0;
	private final Integer id;
	private final Role creator;
	private boolean published;

	public Content(String text, Role role) {
		this.text = text;
		this.id= generalId++;
		this.creator = role;
		this.published=false;
	}

	public String getText() {
		return text;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Role getCreator() {
		return creator;
	}
	
	public void setVisibility() {
		published=true;
	}
	
	public boolean getVisibility() {
		return published;
	}
	
}
	
