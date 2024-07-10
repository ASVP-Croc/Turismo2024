package elements;

import java.util.stream.Stream;

public class Contest extends Element {
	private final Element element;
	
	public Contest(String description, Element element) {
		super(description);
		this.element = element;
	}
	
	public Element getElement() {
		return element;
	}
	
	public Stream<Content> getContents(){
		return super.getContents();
	}
	
	public String getDescription() {
		return super.getDescritpion();
	}

	public Content addContent(Content content){
		return super.addContent(content);
	}
	
	public Integer getId() {
		return super.getId();
	}
	
	public Content getContent(Integer id) {
		return super.getContent(id);
	}

	public void deleteContent(Integer id) {
		getContents();//ricontrolla
		
	}


}
