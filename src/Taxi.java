/**
 * Represents a taxi as part of the dispatch center.
 */
public class Taxi {
    /**
     * The private fields of the taxi class.
     * A taxi has a plate number, an availability,
     * a destination, and an estimated time to destination.
     */
    private int         plateNumber;
    private boolean     available;
    private String      destination;
    private int         estimatedTimeToDest;

    /**
     * Gets the plate number of this Taxi.
     * @return this Taxi's plate number.
     */
    public int getPlateNumber() { return plateNumber; }

    /**
     * Gets the availability of this Taxi.
     * @return this Taxi's availability.
     */
    public boolean getAvailable() { return available; }

    /**
     * Gets the destination of this Taxi.
     * @return this Taxi's destination.
     */
    public String getDestination() { return destination; }

    /**
     * Gets the estimated time to destination of this Taxi.
     * @return this Taxi's estimated time to destination.
     */
    public int getEstimatedTimeToDest() { return estimatedTimeToDest; }

    /**
     * Changes the availability of this Taxi.
     * Availability is a private boolean field.
     * @param avail This Taxi's new availability.
     *              Should be either true or false.
     */
    public void setAvailable(boolean avail) { available = avail; }

    /**
     * Changes the destination of this Taxi.
     * @param d This Taxi's new destination.
     *          Should be one of "Downtown", "Airport", "North", "South", "East", "West".
     */
    public void setDestination(String d) { destination = d; }

    /**
     * Changes the estimated time to destination of this Taxi.
     * @param t This Taxi's new estimated time to destination.
     *          Should be a value in seconds.
     */
    public void setEstimatedTimeToDest(int t) { estimatedTimeToDest = t; }

    /**
     * Decreases the estimated time to destination of this Taxi by 1 second.
     */
    public void decreaseEstimatedTimeToDest() {
        estimatedTimeToDest--;
    }

    /**
     * Creates a new Taxi with the given plate.
     * By default, the new Taxi is available, has no destination,
     * and the estimated time to destination is 0 seconds.
     * @param plate This Taxi's new plate.
     */
    public Taxi (int plate) {
        plateNumber = plate;
        available = true;
        destination = "";
        estimatedTimeToDest = 0;
    }

    /**
     * Gets the string representation of this Taxi.
     * The string representation will either be (available) if the Taxi is available
     * or the estimated time to destination in seconds.
     * @return this Taxi's string representation.
     */
    public String toString() {
        if (available)
            return plateNumber + " (available)";
        return plateNumber + "(" + estimatedTimeToDest + ")";
    }

    // public equals method that checks whether two taxis are equal by comparing plateNumbers
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (this.getClass() != o.getClass())
            return false;
        Taxi taxi = (Taxi) o;
        // field comparison
        return this.getPlateNumber() == taxi.getPlateNumber();
    }
}