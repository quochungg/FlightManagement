package control;

import static control.AirportFunction.loadList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author nguye
 */
public class CrewManagement {

    ArrayList<Crew> crewList = loadCrewList();
    Validation valid = new Validation();
    
    
    public void manageCrewForFlight(Flight flight) {
        if (flight == null) {
            ArrayList<Crew> cr = loadCrewList();
            AirportFunction a = new AirportFunction();
            ArrayList<Flight> listFlight = loadList();
            a.showInfo(listFlight, 0);
            int choice = valid.getInt("Choose Flight to manage crew: ", 1, listFlight.size());
            Flight flight1 = listFlight.get(choice - 1);
            System.out.println(flight1.toString());
            Crew crew = new Crew();
            for (Crew c : cr) {
                if (c.getflightId().trim().equalsIgnoreCase(flight1.getFlightCode().trim())) {
                    crew = c;
                    crew.setPilots(valid.getInt("Pilots: (Current value " + crew.getPilots() + ")", 2, 5));
                    crew.setFlightAttendant(valid.getInt("Flight Attendant: (Current value " + crew.getFlightAttendant() + ")", 10, 15));
                    crew.setGroundStaff(valid.getInt("Ground Staff: (Current value " + crew.getGroundStaff() + ")", 20, 50));
                    cr.set(cr.indexOf(c), crew);
                    break;
                }
            }
            saveCrewList(cr);
        } else {
            ArrayList<Crew> cr = loadCrewList();
            Crew crew = new Crew(flight.getFlightCode());
            crew.setPilots(valid.getInt("Enter Pilots: ", 2, 5));
            crew.setFlightAttendant(valid.getInt("Enter Flight Attendant: ", 10, 15));
            crew.setGroundStaff(valid.getInt("Enter Ground Staff: ", 20, 50));
            cr.add(crew);
            saveCrewList(cr);
        }
    }

    public ArrayList<Crew> loadCrewList() {
        ArrayList<Crew> cr = new ArrayList<>();
        try {
            try ( BufferedReader br = new BufferedReader(new FileReader("src/output/crew.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String parts[] = line.split(",");
                    String flightId = parts[0].trim();
                    int pilots = Integer.parseInt(parts[1].trim());
                    int flightAttendants = Integer.parseInt(parts[2].trim());
                    int groundStaff = Integer.parseInt(parts[3].trim());
                    Crew crew = new Crew(flightId, pilots, flightAttendants, groundStaff);
                    cr.add(crew);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return cr;
    }

    public void saveCrewList(ArrayList<Crew> crList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/output/crew.txt"));
            String line;
            for (Crew crew : crList) {
                line = crew.toString();
                bw.write(line + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void showAllCrewInfo(){
        int i = 0;
        for (Crew crew : crewList) {
            System.out.println(++i + ". " + crew.toString());
        }
    }
}
