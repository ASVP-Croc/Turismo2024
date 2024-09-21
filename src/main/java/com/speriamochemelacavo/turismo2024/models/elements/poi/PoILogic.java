package com.speriamochemelacavo.turismo2024.models.elements.poi;

import com.speriamochemelacavo.turismo2024.models.elements.category.POILogicEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("Logic")
public class PoILogic extends PointOfInterest {

    private String historicInfo;
    private String area;

    @Enumerated(EnumType.STRING)
    private POILogicEnum typePoILogic;

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
    public PoIType getType() {
        return PoIType.POI_LOGIC;
    }
}
