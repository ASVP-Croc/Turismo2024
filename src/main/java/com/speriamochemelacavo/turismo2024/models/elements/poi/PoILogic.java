package com.speriamochemelacavo.turismo2024.models.elements.poi;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Logic")
public class PoILogic extends PointOfInterest {

    private String historicInfo;
    private String area;

    public String getHistoricInfo() {
        return historicInfo;
    }

    public void setHistoricInfo(String historicInfo) {
        this.historicInfo = historicInfo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public PoIType getPoiType() {
    	super.setPoiType(PoIType.LOGIC);
        return super.getPoiType();
    }
}
