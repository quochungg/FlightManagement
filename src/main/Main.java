package main;

import control.*;
import static control.AirportFunction.loadList;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author nguye
 */
public class Main implements I_Menu {

    Validation valid = new Validation();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    @Override
    public void run() {
        OUTER:
        while (true) {
            AirportFunction a = new AirportFunction();
            ReservationManage r = new ReservationManage();
            CrewManagement c = new CrewManagement();
            System.out.println("\nWelcome to LAB211 Airport");
            System.out.println("You are:");
            System.out.println("1. Airport Staff");
            System.out.println("2. Passenger");
            System.out.println("3. Stop The Program");
            int choice = valid.getInt("Choose an option : ", 0, 3);
            switch (choice) {
                case 1:
                    while (true) {
                        if (a.checkLogin()) {
                            break;
                        }
                    }  
                    outerloop:
                    do {                        
                        Menu menu = new Menu("\nAdmin Function");
                        menu.addOption("1. Add Flight Schedule");
                        menu.addOption("2. Check-in ");
                        menu.addOption("3. Crew Management");
                        menu.addOption("4. Display All Flight");
                        menu.addOption("5. Display All Crew");   
                        menu.addOption("6. Display All Reservation");                          
                        menu.addOption("7. Back to login menu");
                        menu.printMenu();
                        int userChoice = menu.getChoice();
                        switch (userChoice) {
                            case 1:
                                a.addFlight();
                                break;
                            case 2:
                                r.checkIn();
                                break;
                            case 3:
                                c.manageCrewForFlight(null);
                                break;
                            case 4:
                                ArrayList<Flight> flights = loadList();
                                a.showInfo(flights,1);
                                break;
                            case 5:
                                c.showAllCrewInfo();
                                break;
                            case 6:
                                r.printReservation();
                            case 7:
                                break outerloop;
                                
                        }
                    } while (true);
                    break;
                case 2:
                    outerloop:
                    while (true) {
                        Menu menu = new Menu("\nPassenger Function");
                        menu.addOption("1. Search for flights");
                        menu.addOption("2. Booking flight");
                        menu.addOption("3. Back to login menu");
                        menu.printMenu();
                        int userChoice = menu.getChoice();
                        switch (userChoice) {
                            case 1:
                                Flight flight = r.search();
                                if (flight != null) {
                                    r.booking(flight);
                                }   break;
                            case 2:
                                r.directBooking();
                                break;
                            case 3:
                                break outerloop;
                            default:
                                break;
                        }                        
                    }   break;
                default:
                    break OUTER;
            }
        }
    }
}
