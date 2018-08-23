import javafx.scene.control.TextField;

import java.util.*;

public class DispatchCenter {
    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"};

    private int[][]  stats; // You'll need this for the last part of the assignment
    private HashMap<Integer, Taxi> taxis;
    private HashMap<String, ArrayList<Taxi>> areas;
    private ArrayList<Taxi> areasArray;



    // Constructor
    public DispatchCenter() {
        // You'll need this for the last part of the assignment
        areas = new HashMap<String, ArrayList<Taxi>>();
        stats = new int[AREA_NAMES.length][AREA_NAMES.length];
        for (int i = 0; i < AREA_NAMES.length; i++) {
            areas.put(AREA_NAMES[i], new ArrayList<Taxi>());
        }
        //areasArray = new ArrayList<Taxi>();
        taxis = new HashMap<Integer, Taxi>();
        int counter = 0;
        while (counter < 50) {
            double n = Math.random() * 999 + 100;
            double a = Math.random() * 6;
            Taxi t = new Taxi((int )n);
            if (counter == 0) {
                addTaxi(t, AREA_NAMES[(int )a]);
                counter++;
            } else if (!taxis.containsKey(n)) {
                //taxis.put(n, t);
                addTaxi(t, AREA_NAMES[(int )a]);
                counter++;
            }
            //areasArray.add(a, t);
        }



    }


    // You'll need this for the last part of the assignment
    public int[][]   getStats() { return stats; }


    // Update the statistics for a taxi going from the pickup location to the dropoff location
    public void updateStats(String pickup, String dropOff) {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            if (DispatchCenter.AREA_NAMES[i].equals(pickup)) {
                fromIndex = i;
            }
        }
        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            if (DispatchCenter.AREA_NAMES[i].equals(dropOff)) {
                toIndex = i;
            }
        }
        /*if ((fromIndex != -1) && (toIndex != -1)) {
            int total = Integer.parseInt(DispatchStatsDialog.getStatsFields()[fromIndex][toIndex].getText());
            total++;
            DispatchStatsDialog.getStatsFields()[fromIndex][toIndex].setText(Integer.toString(total));
        }*/
        if ((fromIndex != -1) && (toIndex != -1)) {
            stats[fromIndex][toIndex]++;
        }
    }

    // Determine the travel times from one area to another
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
        int pickUpIndex = -1;
        int dropOffIndex = -1;
        int[][] distanceMatrix = {
                {10, 40, 20, 20, 20, 20},
                {40, 10, 40, 40, 20, 60},
                {20, 40, 10, 40, 20, 20},
                {20, 40, 40, 10, 20, 20},
                {20, 20, 20, 20, 10, 40},
                {20, 60, 20, 20, 40, 10}
        };

        for (int i=0; i < AREA_NAMES.length; i++) {
            if (AREA_NAMES[i].equals(pickup)) {
                pickUpIndex = i;
            }
            if (AREA_NAMES[i].equals(dropOff)) {
                dropOffIndex = i;
            }
        }
        if ((pickUpIndex != -1) && (dropOffIndex != -1)) {
            return distanceMatrix[pickUpIndex][dropOffIndex];
        }
        return 0;
    }

    // Add a taxi to the hashmaps
    public void addTaxi(Taxi aTaxi, String area) {
        taxis.put(aTaxi.getPlateNumber(), aTaxi);
        areasArray = areas.get(area);
        areasArray.add(aTaxi);
        areas.put(area, areasArray);
    }

    // Return a list of all available taxis within a certain area
    private ArrayList<Taxi> availableTaxisInArea(String s) {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        result = areas.get(s);
        for (Taxi t: result) {
            if (!t.getAvailable()) {
                result.remove(t);
            }
        }
        return result;
    }

    // Return a list of all busy taxis
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for (int i = 0; i < AREA_NAMES.length; i++) {
            for (Taxi t: areas.get(AREA_NAMES[i])) {
                if (!t.getAvailable()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    // Find a taxi to satisfy the given request
    public Taxi sendTaxiForRequest(ClientRequest request) {
        for (Taxi t: areas.get(request.getPickupLocation())) {
            if (t.getAvailable()) {
                areas.get(request.getPickupLocation()).remove(t);
                areas.get(request.getDropoffLocation()).add(t);
                t.setAvailable(false);
                t.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(), request.getDropoffLocation()));
                updateStats(request.getPickupLocation(), request.getDropoffLocation());
                return t;
            }
        }
        for (int i = 0; i < AREA_NAMES.length; i++) {
            for (Taxi t: areas.get(AREA_NAMES[i])) {
                if (t.getAvailable()) {
                    areas.get(AREA_NAMES[i]).remove(t);
                    areas.get(request.getDropoffLocation()).add(t);
                    t.setAvailable(false);
                    t.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(), request.getDropoffLocation()));
                    updateStats(request.getPickupLocation(), request.getDropoffLocation());
                    return t;
                }
            }
        }
        return null;
    }

    // get method for taxis HashMap
    public HashMap<Integer, Taxi> getTaxis() {
        return taxis;
    }

    public HashMap<String, ArrayList<Taxi>> getAreas() {
        return areas;
    }

}