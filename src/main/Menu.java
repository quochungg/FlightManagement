
package main;

import control.Validation;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Menu {
    private String title;
    private ArrayList<String> optionList = new ArrayList();

    
    public Menu(String title) {
        this.title = title;
    }
      
    public void addOption(String option){
        optionList.add(option);
    }
    
    public void printMenu(){
        if (optionList.isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        System.out.println("\t"+this.title+"\n");
        for (String string : optionList) {
            System.out.println(string);
        }
    }
    
    public int getChoice() {
        int max = optionList.size();
        return Validation.getInt(("Choose [1..." + max + "]: "), 0, max + 1);
    }
    
}
