
package model;

/**
 *
 * @author nguye
 */
public class Reservation {
    private String pName;
    private String flightId;
    private int seat;
    private String reserId;

    public Reservation() {
    }

    public Reservation(String pName, String flightId, int seat, String reserId) {
        this.pName = pName;
        this.flightId = flightId;
        this.seat = seat;
        this.reserId = reserId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getReserId() {
        return reserId;
    }

    public void setReserId(String reserId) {
        this.reserId = reserId;
    }




    @Override
    public String toString() {
        return pName + ", " + flightId + ", " + seat + ", " + reserId;
    }
    
    
}
