package com.speriamochemelacavo.turismo2024.models.elements.poi;

public class PointOfInterestFactory {
    public static PointOfInterest createPOI(String type) {
        switch (type.toUpperCase()) {
            case "physic":
                return new PoIPhysics();
            case "logic":
                    return new PoILogic();
            default:
                throw new IllegalArgumentException("Tipo POI sconosciuto");
        }
    }
}
