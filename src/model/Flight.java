
package model;

/**
 *
 * @author nguye
 */

public class Flight {
    private String flightCode;  //Fxxxx
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;
    private int availableSeat;
       
    //CONSTRUCTOR

    public Flight() {
    }

    public Flight(String flightCode, String departureLocation, String arrivalLocation, String departureTime, String arrivalTime, int availableSeat) {
        this.flightCode = flightCode;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeat = availableSeat;
    }

    public Flight(String departureLocation, String arrivalLocation, String departureTime, String arrivalTime) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    //GETTER SETTER
    
    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    @Override
    public String toString() {
        return flightCode + ", " + departureLocation + ", " + arrivalLocation + ", " + departureTime + ", " + arrivalTime + ", " + availableSeat;
    }
    
    
}
