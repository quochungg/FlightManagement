package control;

import model.Flight;

/**
 *
 * @author nguye
 */
public interface I_Reservation {
    public Flight search();
    public boolean booking(Flight flight);
}
