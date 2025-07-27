package StudentRegister;
import java.util.*;
import java.io.*;

public class Main {
    static String tobeSearched;

    static List<StudentContructors> students = new ArrayList<StudentContructors>();
    static Scanner s = new Scanner(System.in);
    static String filePath = 
    "/home/aly/Beginner/MyLearningRepository/StudentInformationRegistrationAndManagement/src/StudentRegister/Students'Rec.txt";
    public static void main(String[] args){
        //students.add(new StudentContructors(
            //"0", "Aly", "DC", "2", "CpE", "A"));
        System.out.println();

       

        String userAnswered = "YES";
        while (userAnswered.equalsIgnoreCase("YES")){
        System.out.println("1. Student Registration");
        System.out.println("2. Update Student Information");
        System.out.println("3. Delete Student Information");
        System.out.println("4. View Students Information");
        System.out.println("5. Exit");
        try{
        System.out.print("Enter a number: ");
        int userInput = s.nextInt();
        
        s.nextLine();
        System.out.println();
        
        switch(userInput){
            case 1:
                studentRegister();
                break;
            case 2:
                String updateAnother = "YES";
                while(updateAnother.equalsIgnoreCase("YES")) {
                updateStudent();
                System.out.println("\nUpdate another student?");
                System.out.print("Enter Yes or No: ");
                updateAnother = s.nextLine();
                /*if(!s.nextLine().equalsIgnoreCase("YES")) {
                    updateAnother = false;
                }*/
            }
                break;
            case 3:
                deleteStudentRecF();
                break;
            case 4:
                viewStudentRec();
                break;
            case 5:
                userAnswered = "NO";
                break;
            default:
                System.out.println("Invalid Input!");
                break;
            }
        
        if(userAnswered == "YES"){
            System.out.println();
            System.out.println("Another Transaction?");
            System.out.print("Enter Yes or No: ");
            userAnswered = s.nextLine();
        }
        }
        catch(InputMismatchException e){
            s.nextLine();
            System.out.println("Only Input Integers\n");
        }
        }
        if(userAnswered != "YES"){
        System.out.println();
        System.out.println("Thank you for using the application!");
        }
        
        
    }
    static void studentRegister(){
        System.out.println("Student Registration");
        System.out.print("Enter Number of Students to Register: ");
        int numStudents = s.nextInt();
        s.nextLine();
        for(int i = 0; i < numStudents; i++){
        System.out.print("StudentID : ");
        String sId = s.nextLine();
        System.out.print("First Name: ");
        String fname = s.nextLine();
        System.out.print("Last Name : ");
        String lname = s.nextLine();
        System.out.print("Year      : ");
        String y = s.nextLine();
        System.out.print("Course    : ");
        String c = s.nextLine();
        System.out.print("Section   : ");
        String sec = s.nextLine();
        System.out.println();
        students.add(new StudentContructors(sId, fname, lname, y, c, sec));
        }
        System.out.println("Confirmation: Do you want to save the new student/s's record?");
        System.out.print("Enter Yes or No: ");
        String userConfirmation = s.nextLine();
        if(userConfirmation.equalsIgnoreCase("YES")){
            saveStudentData();
        }

        System.out.println();

    }
    static void updateStudent() {
        System.out.println("Update Student Information");
        boolean updateLoop = true;
        System.out.print("\nStudentID : ");
        String studentID = s.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6 && fields[0].trim().equalsIgnoreCase(studentID)) {
                    System.out.println("Name    : " + fields[1].trim() + " " + fields[2].trim());
                    System.out.println("Year    : " + fields[3].trim());
                    System.out.println("Course  : " + fields[4].trim());
                    System.out.println("Section : " + fields[5].trim());
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student not found...");
                return;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
        while(updateLoop = true){
        System.out.println("\nSelect field to update:");
        System.out.println("1. Year");
        System.out.println("2. Course");
        System.out.println("3. Section");
        System.out.println("4. Finish");
        try{
            System.out.print("Enter choice: ");
            int choice = s.nextInt();
            s.nextLine(); 

            if(choice == 4 ) break;

            System.out.print("Enter new value: ");
        String newValue = s.nextLine();

        System.out.println("Confirm update ");
        System.out.print("Enter Yes or No: ");
        if (!s.nextLine().equalsIgnoreCase("YES")) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    if (fields[0].trim().equalsIgnoreCase(studentID)) {
                        switch (choice) {        
                            case 1: fields[3] = newValue; break;
                            case 2: fields[4] = newValue; break;
                            case 3: fields[5] = newValue; break;
                            default: System.out.println("Invalid input..."); return;
                        }
                    }
                    writer.write(String.join(",", fields));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }

        File oldFile = new File(filePath);
        File newFile = new File("temp.csv");
        if (oldFile.delete() && newFile.renameTo(oldFile)) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Something went wrong...");
        }
            
        } catch(InputMismatchException e){
            s.nextLine();
            System.out.println("Only Input Integers\n");
        }
    }

    }


    static void updateField(Scanner s, String filePath, String studentID, int choice) {
        System.out.print("Enter new value: ");
        String newValue = s.nextLine();

        System.out.println("Confirm update ");
        System.out.print("Enter Yes or No: ");
        if (!s.nextLine().equalsIgnoreCase("YES")) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    if (fields[0].trim().equalsIgnoreCase(studentID)) {
                        switch (choice) {        
                            case 1: fields[3] = newValue; break;
                            case 2: fields[4] = newValue; break;
                            case 3: fields[5] = newValue; break;
                            default: System.out.println("Invalid input..."); return;
                        }
                    }
                    writer.write(String.join(",", fields));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }

        File oldFile = new File(filePath);
        File newFile = new File("temp.csv");
        if (oldFile.delete() && newFile.renameTo(oldFile)) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Something went wrong...");
        }
    }


    static void viewStudentRec(){
        System.out.println("Read Students Information");
        System.out.println("1. Show Specific Student's Information");
        System.out.println("2. Show All Students' Information");
        System.out.print("Enter a number: ");
        int userinput4 = s.nextInt();
        s.nextLine();

        switch (userinput4) {
            case 1:
                System.out.println("Show Specific Student's Information");
                while (true) {
                viewSpecificStudentData();
                System.out.println("View another student?");
                System.out.print("Enter Yes or No: ");
                    if(!s.nextLine().equalsIgnoreCase("YES")) {
                        break;
                    }
                }
                break;
            case 2:
                System.out.println("Show All Students' Information");
                readTextFile();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }
    static void saveStudentData(){
        try (FileWriter fw = new FileWriter(filePath, true); 
         BufferedWriter bw = new BufferedWriter(fw)) {
        for (StudentContructors student : students) {
            bw.write(student.getStudentId() + "," + 
            student.getFirstname() + "," + 
            student.getLastname() + "," + 
            student.getYear() + "," + 
            student.getCourse() + "," + 
            student.getSection());
            bw.newLine();
        }
            }catch(IOException e){
                System.err.println("Something went wrong...");
        }
    }

    static void viewSpecificStudentData(){
        String studentID;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.print("\nStudentID : ");
            studentID = s.nextLine().trim();

            String line;
            boolean studentFound = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6 && fields[0].trim().equalsIgnoreCase(studentID)) {
                    System.out.println("Name    : " + fields[1].trim() + " " + fields[2].trim());
                    System.out.println("Year    : " + fields[3].trim());
                    System.out.println("Course  : " + fields[4].trim());
                    System.out.println("Section : " + fields[5].trim());
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student not found...");
                return;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }

    static void readTextFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                if (fields.length == 6) {
                String studentID = fields[0].trim();
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String year = fields[3].trim();
                String course = fields[4].trim();
                String section = fields[5].trim();
                System.out.println("\nStudentID : " + studentID);
                System.out.println("Name      : " + firstName + " " + lastName);
                System.out.println("Year      : " + year);
                System.out.println("Course    : " + course);
                System.out.println("Section   : " + section);
            }
        }
    }
        catch(IOException e){
            System.out.println("Something went wrong...");
        }
    }

    static void deleteStudentRecF() {
    String studentIdToDelete;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
         BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {

        System.out.print("StudentID : ");
        studentIdToDelete = s.nextLine();

        String line;
        boolean studentFound = false;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length == 6) {
                String studentId = fields[0].trim();
                if (studentId.equalsIgnoreCase(studentIdToDelete)) {
                   
                    System.out.println("Name      : " + fields[1].trim() + " " + fields[2].trim());
                    System.out.println("Year      : " + fields[3].trim());
                    System.out.println("Course    : " + fields[4].trim());
                    System.out.println("Section   : " + fields[5].trim());
                    studentFound = true;

                   
                    System.out.println("\nDelete this student?");
                    System.out.print("Enter Yes or No: ");
                    String confirmation = s.nextLine();
                    if (!confirmation.equalsIgnoreCase("YES")) {
                        System.out.println("Cancelled...");
                       
                        writer.write(line);
                        writer.newLine();
                    }
                } else {
                    writer.write(line); 
                    writer.newLine();
                }
            }
        }

        if (!studentFound) {
            System.out.println("Student not found.");
        }
    } catch (IOException e) {
         System.out.println("Something went wrong...");
        return; 
    }

    File oldFile = new File(filePath);
    File newFile = new File("temp.txt");
    if (oldFile.delete() && newFile.renameTo(oldFile)) {
        System.out.println("Student record deleted successfully.");
    } else {
        System.out.println("Something went wrong...");
    }
    }
}
