package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

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

	private Date starts;
	private Date ends;
	
	public Contest() {
		super();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Contest(String name, String description, User author, String city, String postcode, List<Content> contents, Date starts, Date ends) {
		super(name, description, author, city, postcode, contents);
		this.starts = starts;
		this.ends = ends;
		this.typology = ElementTypology.CONTEST;
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
