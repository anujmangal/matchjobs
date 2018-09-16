package com.matchjobs.utils;

public class DistanceCalculator {

    private static double distance(double lat1, double lat2, double lon1,
                                  double lon2, String unit) {
    	
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c ; // earth radius in km
        distance = Math.pow(distance, 2);
        distance = Math.sqrt(distance);
        if ("M".equalsIgnoreCase(unit) || "Miles".equalsIgnoreCase(unit)) {
            // Converting distance to miles
            distance = distance * 0.62137119;
        }
        return distance;
    }

    public static double distance(String lat1, String lat2, String lon1,
                                  String lon2, String unit) {

        return distance(Double.parseDouble(lat1),Double.parseDouble(lat2),Double.parseDouble(lon1),Double.parseDouble(lon2), unit.toUpperCase());
    }
}