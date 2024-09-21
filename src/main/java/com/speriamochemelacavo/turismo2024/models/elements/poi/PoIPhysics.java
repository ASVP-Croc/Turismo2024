package com.speriamochemelacavo.turismo2024.models.elements.poi;

import com.speriamochemelacavo.turismo2024.models.elements.category.POIPhysicsEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalTime;

@Entity
@DiscriminatorValue("Physic")
public class PoIPhysics extends PointOfInterest {

    private LocalTime openTime;
    private LocalTime closingTime;
    private String availableService;
    private String webSite;
    private String contact;

    @Enumerated(EnumType.STRING)
    private POIPhysicsEnum typePoIPhysics;

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

    public POIPhysicsEnum getTypePoIPhysics() {
        return typePoIPhysics;
    }

    public void setTypePoIPhysics(POIPhysicsEnum typePoIPhysics) {
        this.typePoIPhysics = typePoIPhysics;
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
    public PoIType getType() {
        return PoIType.POI_PHYSICS;
    }
}
