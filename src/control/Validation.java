package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author nguye
 */
public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String mess, int min, int max) {
        int output;
        try {
            System.out.println(mess);
            output = Integer.parseInt(sc.nextLine());
            if (output >= min && output < max) {
                return output;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static String getString(String mess, int opt) { //opt = 1 check valid flight code
        String output = "";
        while (true) {
            if (opt == 1) {
                try {
                    System.out.println(mess);
                    output = sc.nextLine();
                    if (output.matches("^F\\d{4}$")) {
                        return output;
                    }
                    System.out.println("Flight Code Format must be Fxxxx!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (opt == 2) {
                try {
                    System.out.println(mess);
                    output = sc.nextLine();
                    if (!output.isEmpty()) {
                        return output;
                    }else{
                        System.out.println("Input can not be empty!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    //opt 1 để User tìm kiếm (không hỏi về thời gian)
    //opt 2 để admin nhập dữ liệu chuyến bay (phải có rõ thời gian đi và đến)
    public static String getDate(String mess, int opt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate now = LocalDate.now();
        LocalDate check = null;
        String output = "";
        do {
            if (opt == 1) {
                System.out.println(mess);
                output = sc.nextLine().trim();
                try {
                    check = LocalDate.parse(output, formatter1);
                    if (check.isAfter(now)){
                        return output;
                    }else {
                        System.out.println("Please enter valid day!");
                    }
                    return output;
                } catch (Exception e) {
                    System.out.println("Day must follow (yyyy/MM/dd) format!");
                }
            } else if (opt == 2) {
                System.out.println(mess);
                output = sc.nextLine().trim();
                try {
                    check = LocalDate.parse(output, formatter);
                    if (check.isAfter(now)) {
                        return output;
                    } else {
                        System.out.println("Please enter valid day !");
                    }
                } catch (Exception e) {
                    System.out.println("Day must follow (yyyy/MM/dd HH:mm) format!");
                }
            }

        } while (true);
    }
}
