/**
 * Represents a client request for a taxi.
 */
public class ClientRequest {
    /**
     * The pickup and drop off locations of this client request.
     */
    private String pickupLocation;
    private String dropOffLocation;

    /**
     * Gets the pickup location of this ClientRequest.
     * @return this ClientRequest's pickup location.
     */
    public String getPickupLocation() { return pickupLocation; }

    /**
     * Gets the drop off location of this ClientRequest.
     * @return this ClientRequest's drop off location.
     */
    public String getDropoffLocation() { return dropOffLocation; }

    /**
     * Creates a new ClientRequest with the given pickup and drop off location.
     * @param p This ClientRequest's pickup location.
     * @param d This ClientRequest's drop off location.
     */
    public ClientRequest (String p, String d) {
        pickupLocation = p;
        dropOffLocation = d;
    }

    /**
     * Gets the string representation of this ClientRequest.
     * @return this ClientRequest's string representation.
     */
    public String toString() {
        return pickupLocation + " ==> " + dropOffLocation;
    }
}