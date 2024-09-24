package com.speriamochemelacavo.turismo2024.models.elements;

import java.time.LocalDate;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.content.Content;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.Entity;

/**
 * È un {@link Element Elemento} che rappresenta l'idea di "evento competitivo".
 * Ha una durata prefissata, nella quale gli {@link User utenti} che partecipano caricano uno o più {@link Content contenuti},
 * inerenti all'argomento del {@link Contest}.
 *
 * @field Date starts: la data di inizio del {@link Contest}.
 * @field Date ends: la data di fine del {@link Contest}.
 * 
 */

@Component
@Entity
public class Contest extends ElementWithContents {

	private LocalDate starts;
	private LocalDate ends;
	private String theme;
	private boolean isClosed;
	private String linkInvite;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> participants;
	
	public Contest() {
		super();
		this.typology = ElementTypology.CONTEST;
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Contest(String name, String description, User author, String city, String postcode, List<Content> contents, String theme, boolean isOpen, String linkInvite, LocalDate starts, LocalDate ends) {
		super(name, description, author, contents, city, postcode);
		this.theme = theme;
		this.isClosed = isOpen;
		this.linkInvite = linkInvite;
		this.starts = starts;
		this.ends = ends;
		this.typology = ElementTypology.CONTEST;
	}

	public LocalDate getStarts() {
		return starts;
	}

	public void setStarts(LocalDate starts) {
		this.starts = starts;
	}

	public LocalDate getEnds() {
		return ends;
	}

	public void setEnds(LocalDate ends) {
		this.ends = ends;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public String getLinkInvite() {
		return linkInvite;
	}

	public void setLinkInvite(String linkInvite) {
		this.linkInvite = linkInvite;
	}

	public List<User> getParticipants() {
		return participants;
	}
	
}
