package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author nguye
 */
public class AirportFunction implements I_AirportFunction {
    
    ArrayList<Flight> flights = new ArrayList<>();
    Validation valid = new Validation();

    @Override
    public boolean addFlight() {
        int userChoice = -1;
        do {
            try {
                System.out.println("      Add New Flight     ");
                String flightCode = "";
                do {
                    flightCode = valid.getString("Enter new flight code: ", 1);
                    if (checkDuplicate(flightCode)) {
                        System.out.println("Flight Code is duplicated. Enter another code!");
                    }
                } while (checkDuplicate(flightCode));
                String departureLocation = valid.getString("Enter departure location: ", 2);
                String arrivalLocation = valid.getString("Enter arrival location: ", 2);
                String departureTime = valid.getDate("Enter departure time: ", 2);
                String arrivalTime = valid.getDate("Enter arrival time: ", 2);
                int availableSeat = valid.getInt("Enter available seat ( Min:50 / Max:500 ): ", 50, 500);
                Flight newFlight = new Flight(flightCode, departureLocation, arrivalLocation, departureTime, arrivalTime, availableSeat);
                flights = loadList();
                flights.add(newFlight);
                saveList(flights);
                CrewManagement cr = new CrewManagement();
                cr.manageCrewForFlight(newFlight);
                System.out.println("Add sucessfully !!!");
                System.out.println();
                userChoice = valid.getInt("Do you want to add more flights ? (Yes:1, No:0): ", 0, 2);
                if (userChoice == 0) {
                    System.out.println("Back to main menu !");
                }
            } catch (Exception ex) {
                Logger.getLogger(AirportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (userChoice == 1);
        return true;
    }

    public boolean checkDuplicate(String id) {
        ArrayList<Flight> flightList = new ArrayList<>();
        flightList = loadList();
        for (Flight flight : flightList) {
            if (flight.getFlightCode().matches(id)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Flight> loadList() {
        ArrayList<Flight> flightList = new ArrayList<>();
        try {
            try ( BufferedReader br = new BufferedReader(new FileReader("src/output/flights.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String parts[] = line.split(",");
                    String id = parts[0].trim();
                    String departLoca = parts[1].trim();
                    String arrivLoca = parts[2].trim();
                    String departTime = parts[3].trim();
                    String arrivTime = parts[4].trim();
                    int avaiSeat = Integer.parseInt(parts[5].trim());                   
                    Flight flight = new Flight(id, departLoca, arrivLoca, departTime, arrivTime, avaiSeat);
                    flightList.add(flight);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return flightList;
    }

    public static void saveList(ArrayList<Flight> list) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/output/flights.txt"));
            String line;
            for (Flight flight : list) {
                line = flight.toString();
                bw.write(line + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showInfo(ArrayList<Flight> list, int opt) {
        if (opt == 1) {
            Collections.sort(list, new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o2.getDepartureTime().compareTo(o1.getDepartureTime());
                }
            });
        }
        int i = 0;
        for (Flight flight : list) {
            System.out.print(++i + ". ");
            System.out.println(flight.toString());
        }

    }

    public static boolean isAvailable(Flight flight) {
        return flight.getAvailableSeat() != 0;
    }

    public boolean checkLogin() {
        while (true) {
            String user = valid.getString("Enter your user name: ", 2);
            String password = valid.getString("Enter your password: ", 2);
            if (user.matches("admin") && password.matches("admin")) {
                return true;
            }
        }
    }

}
