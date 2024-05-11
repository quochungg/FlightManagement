
package model;

/**
 *
 * @author nguye
 */
public class Passenger {
    private String pName;
    private String pPhoneNumber;

    public Passenger(String pName, String pPhoneNumber) {
        this.pName = pName;
        this.pPhoneNumber = pPhoneNumber;
    }

    public Passenger() {
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPhoneNumber() {
        return pPhoneNumber;
    }

    public void setpPhoneNumber(String pPhoneNumber) {
        this.pPhoneNumber = pPhoneNumber;
    }
    
    
    
    
    
}
