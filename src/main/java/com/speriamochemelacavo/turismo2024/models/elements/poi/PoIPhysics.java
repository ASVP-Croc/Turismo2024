package com.speriamochemelacavo.turismo2024.models.elements.poi;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalTime;

@Entity
@DiscriminatorValue("Physic")
public class PoIPhysics extends PointOfInterest {

    private LocalTime openTime;
    private LocalTime closingTime;
    private String availableService;
    private String webSite;
    private String contact;

    public String getAvailableService() {
        return availableService;
    }

    public void setAvailableService(String availableService) {
        this.availableService = availableService;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public PoIType getPoiType() {
    	super.setPoiType(PoIType.PHYSICS);
        return super.getPoiType();
    }
}
