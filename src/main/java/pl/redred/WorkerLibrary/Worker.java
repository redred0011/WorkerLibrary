package pl.redred.WorkerLibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int Id;
    private String firstName;
    private String lastName;
    private String position;
    private double salary;

    public Worker(String firstName, String lastName, String position, double salary) {
        if(firstName != null && !firstName.isEmpty()){
            boolean allLetters = true;
            for (char c : firstName.toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.err.println("Wrong first name: " + firstName);;
                    allLetters = false;
                    break;
                }
            }
            if (allLetters) {
                this.firstName = firstName;
            }
        }
        if(lastName != null && !lastName.isEmpty()){
            boolean allLetters = true;
            for (char c : firstName.toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.err.println("Wrong last name: " + lastName);;
                    allLetters = false;
                    break;
                }
            }
            if (allLetters) {
                this.lastName = lastName;
            }
        }
        if(position != null && !position.isEmpty()){
            boolean allLetters = true;
            for (char c : position.toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.err.println("Wrong position: " + position);;
                    allLetters = false;
                    break;
                }
            }
            if (allLetters) {
                this.position = position;
            }
        }
        if(salary > 0 && salary < 1000000){
            this.salary = salary;
        }else{
            System.err.println("Wrong salary: "+ salary);
        }

    }
    @Override
    public String toString(){
        return getId() + ". "+getFirstName()+" "+getLastName()+" "+getPosition()+" "+getSalary();
    }
}
