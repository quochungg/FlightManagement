package control;

import static control.AirportFunction.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author nguye
 */
public class ReservationManage implements I_Reservation {

    ArrayList<Reservation> reservationList = new ArrayList<>();
    Validation valid = new Validation();
    Scanner sc = new Scanner(System.in);

    @Override
    public Flight search() {
        ArrayList<Flight> flightList = new ArrayList<>();
        ArrayList<Flight> searchList = new ArrayList<>();
        flightList = loadList();
        System.out.println("\nSEARCH FLIGHT");
        while (true) {
            String departTime = valid.getDate("Enter departure time (yyyy/MM/dd): ", 1);
            String departLoca = valid.getString("Enter departure location: ", 2);
            String arrivLoca = valid.getString("Enter arrival location: ", 2);
            for (Flight flight : flightList) {
                String temp[] = flight.getDepartureTime().split(" ");
                if (departLoca.equalsIgnoreCase(flight.getDepartureLocation().trim()) && arrivLoca.equalsIgnoreCase(flight.getArrivalLocation().trim()) && departTime.equalsIgnoreCase(temp[0].trim())) {
                    searchList.add(flight);
                } else if (departLoca.equalsIgnoreCase(flight.getDepartureLocation().trim()) && arrivLoca.equalsIgnoreCase(flight.getArrivalLocation().trim()) || departTime.equalsIgnoreCase(temp[0].trim())) {
                    searchList.add(flight);
                }
            }
            System.out.println("\n-------------Appropriate Flight-------------");
            showInfo(searchList, 0);
            int userChoice;
            userChoice = valid.getInt("Choose a flight (Back to main menu : 0): ", 0, searchList.size() + 1);
            if (userChoice != 0) {
                return searchList.get(userChoice - 1);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean booking(Flight flight) {
        Reservation newReservation = new Reservation();
        Passenger newPassenger = new Passenger();
        System.out.println("\n------------------Booking-------------------");
        while (true) {
            try {
                String name = valid.getString("Enter your name: ", 2);
                String phoneNumber = valid.getString("Enter your phone number:", 2);
                newPassenger.setpName(name);
                newPassenger.setpPhoneNumber(phoneNumber);
                newReservation.setpName(newPassenger.getpName());
                newReservation.setFlightId(flight.getFlightCode());
                newReservation.setSeat(0);
                newReservation.setReserId(generateReserId());
                System.out.println("Successful!!!");
                System.out.println("Your reservation ID is " + newReservation.getReserId());
                reservationList = loadReserFile();
                reservationList.add(newReservation);
                saveReserFile(reservationList);
                int userChoice = valid.getInt("Do you want to make a new reservation (Yes : 1/ No : 0):", 0, 2);
                if (userChoice == 0) {
                    return true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ReservationManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Reservation> loadReserFile() {
        ArrayList<Reservation> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/output/reservation.txt"));
            try {
                //return pName + ", " + flightId + ", " + seat + ", " + reserId;
                String line;
                while ((line = br.readLine()) != null) {
                    String[] part = line.split(",");
                    String name = part[0].trim();
                    String id = part[1].trim();
                    int seat = Integer.parseInt(part[2].trim());
                    String reserId = part[3].trim();
                    Reservation reser = new Reservation(name, id, seat, reserId);
                    list.add(reser);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReservationManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void saveReserFile(ArrayList<Reservation> list) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/output/reservation.txt"));
            String line;
            for (Reservation reservation : list) {
                line = (reservation.toString() + "\n");
                bw.write(line);
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String generateReserId() {

        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        String output = "P" + String.valueOf(randomNumber);
        return output;
    }

    private int chooseSeat(Flight flight) {
        ArrayList<Reservation> reserList = loadReserFile();
        int seatAvailable = flight.getAvailableSeat();
        boolean[] checkList = new boolean[seatAvailable];
        int[] seat = new int[seatAvailable];
        ArrayList<Integer> seatNotAvailable = new ArrayList<>();
        for (Reservation r : reserList) {
            if (r.getFlightId().matches(flight.getFlightCode())) {
                seatNotAvailable.add(r.getSeat());
            }
        }
        for (int i = 0; i < checkList.length; i++) {
            checkList[i] = !seatNotAvailable.contains(i);
        }
        for (int i = 1; i <= seat.length; i++) {
            seat[i - 1] = i;
            if (checkList[i - 1] == false) {
                System.out.print("X  ");
            } else {
                if (i < 10) {
                    System.out.print(seat[i - 1] + "  ");
                } else {
                    System.out.print(seat[i - 1] + " ");
                }
            }
            if ((i) % 5 == 0 && i != 1) {
                System.out.println("");
            }

        }
        while (true) {
            int userChoice = valid.getInt("\nSelect your seat:", 1, seatAvailable + 1);
            if (!checkList[userChoice - 1]) {
                System.out.println("Your chosen seat is not available. Please choose another seat!");
                continue;
            } else {
                return userChoice - 1;
            }
        }

    }

    private boolean checkValidReserId(String id) {
        ArrayList<Reservation> reserList = loadReserFile();
        while (true) {
            for (Reservation r : reserList) {
                if (r.getReserId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean checkIn() {
        try {
            System.out.println("-----------------Check-in---------------------");
            String reserId;
            do {
                reserId = valid.getString("Please provide your reservation ID: ", 2);
                if (checkValidReserId(reserId)) {
                    break;
                } else {
                    System.out.println("Your reservation ID is incorrect. Please try again!");
                }
            } while (!checkValidReserId(reserId));
            ArrayList<Reservation> reserList = loadReserFile();
            ArrayList<Flight> flightList = loadList();
            Flight choosenFlight = new Flight();
            Reservation userReser = new Reservation();
            for (Reservation r : reserList) {
                if (r.getReserId().matches(reserId)) {
                    userReser = r;
                    for (Flight f : flightList) {
                        if (r.getFlightId().matches(f.getFlightCode())) {
                            choosenFlight = f;
                        }
                    }
                    userReser.setSeat(chooseSeat(choosenFlight));
                }
            }
            saveReserFile(reserList);
            displayBoardingPass(userReser, choosenFlight);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ReservationManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void displayBoardingPass(Reservation r, Flight f) {
        System.out.println("--------------------------------------------------------");
        System.out.println("                   LAB211 AIRPORT                       ");
        System.out.format("Flight ID:                      %s\n", f.getFlightCode());
        System.out.format("Passenger Name:                 %s\n", r.getpName());
        System.out.format("Dep Loc:                        %s\n", f.getDepartureLocation());
        System.out.format("Dep Time:                       %s\n", f.getDepartureTime());
        System.out.format("Arri Loc:                       %s\n", f.getArrivalLocation());
        System.out.format("Arri Time:                      %s\n", f.getArrivalTime());
        String seatString = String.format("Seat:                           %d\n", r.getSeat() + 1);
        System.out.print(seatString);
        System.out.println("--------------------------------------------------------");
    }

    public void directBooking() {
        System.out.println("\nBooking");
        String flightCode = valid.getString("Enter flight code: ", 1);
        ArrayList<Flight> flightList = loadList();
        for (Flight flight : flightList) {
            if (flight.getFlightCode().matches(flightCode)) {
                booking(flight);
                return;
            }

        }
    }
    
    public void printReservation(){
        ArrayList<Reservation> reserList = loadReserFile();
        for (Reservation reservation : reserList) {
            System.out.println(reservation.toString());
        }
    }
}
