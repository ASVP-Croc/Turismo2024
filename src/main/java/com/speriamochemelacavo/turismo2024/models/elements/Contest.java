package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.Date;
import java.util.List;

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

	private String theme;
	private boolean isOpen;

	private String linkInvite;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> participants;
	private Date starts;
	private Date ends;
	
	public Contest() {
		super();
		this.typology = ElementTypology.CONTEST.toString();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Contest(String name, String description, User author, String city, String postcode, List<Content> contents, String theme, boolean isOpen, String linkInvite, Date starts, Date ends) {
		super(name, description, author, contents, city, postcode);
		this.theme = theme;
		this.isOpen = isOpen;
		this.linkInvite = linkInvite;
		this.starts = starts;
		this.ends = ends;
		this.typology = ElementTypology.CONTEST.toString();
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}
	
}
