
package model;

/**
 *
 * @author nguye
 */
public class Crew {
    private String flightId;
    private int pilots;
    private int flightAttendant;
    private int groundStaff;

    public Crew(String flightId, int pilots, int flightAttendant, int groundStaff) {
        this.flightId = flightId;
        this.pilots = pilots;
        this.flightAttendant = flightAttendant;
        this.groundStaff = groundStaff;
    }

    public Crew() {
    }

    public Crew(String flightId) {
        this.flightId = flightId;
    }
    
    public String getflightId() {
        return flightId;
    }

    public void setflightId(String flightId) {
        this.flightId = flightId;
    }

    public int getPilots() {
        return pilots;
    }

    public void setPilots(int pilots) {
        this.pilots = pilots;
    }

    public int getFlightAttendant() {
        return flightAttendant;
    }

    public void setFlightAttendant(int flightAttendant) {
        this.flightAttendant = flightAttendant;
    }

    public int getGroundStaff() {
        return groundStaff;
    }

    public void setGroundStaff(int groundStaff) {
        this.groundStaff = groundStaff;
    }

    @Override
    public String toString() {
        return flightId + ", " + pilots + ", " + flightAttendant + ", " + groundStaff;
    }
    
    
}
