package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.Date;

import org.springframework.stereotype.Component;

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

	public Contest(String name, String description) {
		super(name, description);
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
