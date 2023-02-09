import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * <H1>Poised Program</H1>
 * The Poised Program is a project management system that the
 * Poised Structural Engineering Firm can use to manage their projects.
 * <p>
 * The program will be used to:
 * · Add new projects to their folio of work
 * · Make modifications to existing projects/contractors
 * · View overdue and unfinished projects
 * · Finalise projects
 * <P>
 * It will read existing projects from a text file and save any changes
 * to the projects text file. It also creates an Array to store projects in.
 *
 * @author Calum Gilchrist
 * @version 1.0
 */
public class PoisedProgram {
    // List to store Project objects that is dynamically extended as new objects are added
    static LinkedList<Project> projectList = new LinkedList<>();
    // scanner to get user input
    public static final Scanner sc = new Scanner(System.in);
    /**
     * Will run the methods to read the projects from file into the program
     * before running the main menu method
     */
    public static void main(String[] args) {
        /*
         * This will run the display menu method and then whatever other
         * method is required based on the users choice
         */
        // reads existing projects from the text file into the program
        readFromFile();
        mainMenu(sc);
    }
    /**
     * Gets the users choice from the displayed menu method and then triggers
     * the appropriate action. It will write projects to file when the
     * user chooses to 'exit'.
     */
    public static void mainMenu(Scanner sc){
        // Menu loop
        while(true){
            // Runs the method to display the menu on screen
            displayMenu();
            // Get user choice
            String menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "1" -> {
                    System.out.println("----- Add a project -----");
                    addProject();
                }
                case "2" -> {
                    System.out.println("----- Amend project due date -----");
                    amendDueDate();
                }
                case "3" -> {
                    System.out.println("----- Update paid amount -----");
                    updateAmountPaid();
                }
                case "4" -> {
                    System.out.println("----- Amend contractor details -----");
                    updateContractorDetails();
                }
                case "5" -> {
                    System.out.println("----- Update project -----");
                    updateProject();
                }
                case "6" -> {
                    System.out.println("----- Overdue projects -----");
                    viewOverdueProjects();
                }
                case "7" -> {
                    System.out.println("----- Unfinished projects -----");
                    viewUnfinishedProjects();
                }
                case "8" -> {
                    System.out.println("----- Finalise project -----");
                    finaliseProject();
                }
                case "9" -> {
                    System.out.println("exiting program, goodbye");
                    writeToFile();
                    System.exit(0);
                }
                default -> System.out.println("Menu choice not recognised." +
                        "\nEnter your choice again");
            }
        }
    }
    /**
     * Displays the main menu.
     */
    private static void displayMenu(){
        // Displays the menu
        System.out.println("\n      M E N U");
        System.out.println("===================");
        System.out.println("1. Create new project");
        System.out.println("2. Amend due date of a project");
        System.out.println("3. Update amount paid for a project");
        System.out.println("4. Amend Contractors details");
        System.out.println("5. Update a project");
        System.out.println("6. View overdue Projects");
        System.out.println("7. View unfinished Projects");
        System.out.println("8. Finalise a project");
        System.out.println("9. Exit");
        System.out.println("====================");
        System.out.print("Enter choice: ");
    }
    /**
     * Creates a new 'project' object by first gathering all required
     * information from the user, including triggering the 'person' class to
     * gather information about these.
     * It will also display the project on the screen when it has been created.
     */
    public static void addProject() {
        /*
        This will gather all the required data to create a new project
        and then add the Project to the Project list
         */
        try{
            // Project input
            System.out.print("Project number: ");
            int jobNumber = sc.nextInt();
            sc.nextLine();
            System.out.print("Project name: ");
            String jobName = sc.nextLine();
            System.out.print("Building type: ");
            String buildType = sc.nextLine();
            System.out.print("Project address: ");
            String physicalAddress = sc.nextLine();
            System.out.print("ERF number: ");
            int erfNumber = sc.nextInt();
            System.out.print("Total fee: ");
            double totalFee = sc.nextDouble();
            System.out.print("Amount paid: ");
            double totalPaid = sc.nextDouble();
            sc.nextLine();
            System.out.print("Project deadline (dd/mm/yyyy): ");
            String date = sc.nextLine();
            LocalDate deadline = getDate(date);
            // Call other methods to add people to project
            Person architect = addPerson("Architect");
            Person contractor = addPerson("Contractor");
            Person customer = addPerson("Customer");
            boolean completed = false;

            // Create the project object
            Project newJob = new Project(jobNumber,jobName,buildType,physicalAddress,erfNumber,totalFee,
                    totalPaid,deadline,architect,contractor,customer,completed);
            // Adds project to the list
            // currently adding the toString() method of the project object, want all information added.
            projectList.add(newJob);
            System.out.print(newJob);
            System.out.print(newJob.toFile());
        }
        catch (InputMismatchException ime){
            System.out.println("Incorrect input type, please enter the project information again.");
            sc.next();
            addProject();
        }

    }
    /**
     * Changes the date from a String to a Date so that calculations can be done on it
     * @param date the date in String format
     * @return the date in LocalDate format
     */
    public static LocalDate getDate(String date){
        LocalDate dateFormatted = null;
        try {
            //Create the DateTimeFormatter matching the input string format
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //Using parse method to convert the string to LocalDate object
            dateFormatted = LocalDate.parse(date, format);
        }
        // If the String pattern is invalid or unable to be parsed
        catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Exception: " + e);
        }
        return dateFormatted;
    }
    /**
     *  Creates a person.
     *  This will gather the information about the person from the user
     * @param role passes whether it is Architect, Contractor or Customer
     * @return the Person object
     */
    public static Person addPerson(String role) {
        /*
        The role will be passed from the Project method.
        This will then gather the required data to create the particular person and then return this information.
         */
        System.out.println("\n----- " + role + " Information -----");
        System.out.print("Name: ");
        String personName = sc.nextLine();
        System.out.print("Phone number: ");
        String personNumber = sc.nextLine();
        System.out.print("Email: ");
        String personEmailAddress = sc.nextLine();
        System.out.print("Address: ");
        String personPhysicalAddress = sc.nextLine();

        return new Person(role,personName,personNumber,personEmailAddress,personPhysicalAddress);
    }
    /**
     * Modifies the due date of a selected project
     */
    public static void amendDueDate(){
        /*
        Modify the due date of a particular project from its Project number
         */
        try {
            System.out.print("Project number you wish to amend or 0 to return to main menu: ");
            int jobNumberToAmend = sc.nextInt();
            sc.nextLine();
            // "If" will run as long as number entered isn't 0, otherwise "else" will return to main menu
            if (jobNumberToAmend == 0){
                System.out.println("Returning to main menu.");
                mainMenu(sc);
            }
            else {
                // Iterates through the Project list till the Project numbers match and then
                // gets the new due date from the user and amends the Project
                for (Project newJob : projectList) {
                    if (newJob.getJobNumber() == jobNumberToAmend) {
                        System.out.print("New due date (dd/mm/yyyy): ");
                        String date = sc.nextLine();
                        LocalDate newDueDate = getDate(date);
                        newJob.setDeadline(newDueDate);
                        // saves the changes to file
                        writeToFile();
                        System.out.println("Due date updated. Returning to main menu.");
                        mainMenu(sc);
                    }
                }
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("Project number not recognised. Returning to main menu.");
            mainMenu(sc);
        }
    }
    /**
     * Add an amount paid by the customer for a job.
     * Displays amount still owed.
     */
    public static void updateAmountPaid() {
        /*
        Update the amount paid for a particular project from its Project number
         */
        System.out.print("Project number you wish to update payment for: ");
        int jobNumberToAmend = sc.nextInt();
        sc.nextLine();
        // Iterates through the Project list till the Project numbers match then
        // gets the amount from the user that has to be added to the paid amount
        // before updating this for the project.
        for (Project newJob : projectList) {
            if (newJob.getJobNumber() == jobNumberToAmend) {
                System.out.print("Amount to add to paid amount: ");
                double newPayment = sc.nextDouble();
                double newAmountPaid = newJob.getTotalPaid() + newPayment;
                newJob.setTotalPaid(newAmountPaid);
                // Displays amount paid and still owed
                System.out.println("Total amount Paid: £" + newJob.getTotalPaid());
                System.out.println("Total still owed:  £" + (newJob.getTotalFee()-newJob.getTotalPaid()));
                // saves the changes to file
                writeToFile();
                // prompt to return to main menu
                promptEnterKey(sc);
            }
        }
    }
    /**
     * Make amendments to the details stored for a particular Contractor.
     */
    public static void updateContractorDetails() {
        /*
        Update a contractors details from their name
         */
        System.out.println("----Current Contractors----\n");
        for (Project newJob : projectList){
            System.out.println(newJob.getContractor().getName());
        }
        System.out.print("Type the name of the contractor you wish to update the contact details for" +
                " as it appears above: ");
        String contractorName = sc.nextLine();
        // Iterates through the Project list till the Contractor name matches
        // then gets the new contact information from the user before
        // writing this to the Contractor object.
        for (Project newJob : projectList) {
            if (newJob.getContractor().getName().equals(contractorName)) {
                System.out.print("Phone number: ");
                String newNumber = sc.nextLine();
                System.out.print("Email: ");
                String newEmailAddress = sc.nextLine();
                System.out.print("Address: ");
                String newPhysicalAddress = sc.nextLine();

                newJob.getContractor().setPhoneNumber(newNumber);
                newJob.getContractor().setEmailAddress(newEmailAddress);
                newJob.getContractor().setPhysicalAddress(newPhysicalAddress);
            }
        }
        // saves the changes to file
        writeToFile();
        mainMenu(sc);
    }
    /**
     * Make amendments to elements of the project
     * Change either the name, type, address, erf no. or the total fee.
     */
    public static void updateProject(){
        /*
        Modify the properties of a particular project from its Project number
         */
        try {
            System.out.print("Project number you wish to amend or 0 to return to main menu: ");
            int jobNumberToAmend = sc.nextInt();
            sc.nextLine();
            // "If" will run as long as number entered isn't 0, otherwise "else" will return to main menu
            if (jobNumberToAmend!=0){
                // Iterates through the Project list till the Project numbers match and then
                // gets the new due date from the user and amends the Project
                for (Project newJob : projectList) {
                    if (newJob.getJobNumber() == jobNumberToAmend) {
                        System.out.print("What do you wish to amend?: ");
                        System.out.println("1. Project Name");
                        System.out.println("2. Build Type");
                        System.out.println("3. Address");
                        System.out.println("4. ERF Number");
                        System.out.println("5. Total Fee");
                        System.out.println("6. Return to main menu");
                        // message that is used after changes made to project
                        String main = "returning to main menu";
                        // Menu loop
                        while(true){
                            // get users choice
                            String menuChoice = sc.nextLine();
                            switch (menuChoice) {
                                case "1" -> {
                                    System.out.println("Enter new name for the project:");
                                    String jobName = sc.nextLine();
                                    newJob.setJobName(jobName);
                                    writeToFile();
                                    System.out.println("Name updated.");
                                    System.out.println(main);
                                    mainMenu(sc);
                                }
                                case "2" -> {
                                    System.out.println("Enter the new build type:");
                                    String buildType = sc.nextLine();
                                    newJob.setBuildType(buildType);
                                    writeToFile();
                                    System.out.println("Type updated.");
                                    System.out.println(main);
                                    mainMenu(sc);
                                }
                                case "3" -> {
                                    System.out.println("Enter the new address:");
                                    String jobAddress = sc.nextLine();
                                    newJob.setPhysicalAddress(jobAddress);
                                    writeToFile();
                                    System.out.println("Address updated.");
                                    System.out.println(main);
                                    mainMenu(sc);
                                }
                                case "4" -> {
                                    System.out.println("Enter the new ERF number:");
                                    int erfNumber = sc.nextInt();
                                    newJob.setErfNumber(erfNumber);
                                    writeToFile();
                                    System.out.println("ERF updated.");
                                    System.out.println(main);
                                    mainMenu(sc);
                                }
                                case "5" -> {
                                    System.out.println("Enter the new total fee:");
                                    double jobFee = sc.nextDouble();
                                    newJob.setTotalFee(jobFee);
                                    writeToFile();
                                    System.out.println("Total Fee updated.");
                                    System.out.println(main);
                                    mainMenu(sc);
                                } case "6" ->{
                                    System.out.println(main);
                                    mainMenu(sc);
                                }
                                default -> System.out.println("Menu choice not recognised." +
                                        "\nEnter your choice again");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Returning to main menu.");
                mainMenu(sc);
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("Project number not recognised. Returning to main menu.");
            sc.next();
        }
        finally{
            mainMenu(sc);
        }
    }
    /**
     * Displays all the projects that their due dates have passed.
     */
    public static void viewOverdueProjects(){
        // creates today's date as a variable
        LocalDate todayDate = LocalDate.now();
        // iterates through the projects list and if the due date has passed the project will be displayed
        for (Project newJob : projectList) {
            if (todayDate.isAfter(newJob.getDeadline())){
                System.out.println(newJob);
                System.out.println("------------------------");
            }
        }
        promptEnterKey(sc);
    }
    /**
     * Displays the projects which are marked unfinished.
     */
    public static void viewUnfinishedProjects(){
        // iterates through the projects list and if the due date has passed the project will be displayed
        for (Project newJob : projectList) {
            if (!newJob.completed){
                System.out.println(newJob);
                System.out.println("------------------------");
            }
        }
        promptEnterKey(sc);
    }
    /**
     * Marks a project as complete. Will also generate amount still owed by the customer.
     */
    public static void finaliseProject() {
        /*
        Finalise the project, outputting information: customer info, amount owed, completion date
         */
        System.out.print("Project number you wish to finalise: ");
        int jobNumberToFinalise = sc.nextInt();
        sc.nextLine();
        // Iterates through the Project list till the Project numbers match
        // then displays all required information
        for (Project newJob : projectList) {
            if (newJob.getJobNumber() == jobNumberToFinalise) {
                System.out.println("----- Finalised Project -----");
                System.out.println(newJob.getCustomer());
                newJob.setCompleted(true);
                // calculates the amount still owed
                double amountOwed = newJob.getTotalFee() - newJob.getTotalPaid();
                if (amountOwed > 0) {
                    // if total owed is more than zero it will show amount owed
                    System.out.println("Amount still to be paid: £" + amountOwed);
                }
                else{
                    System.out.println("Fully Paid");
                }
                // Gets and displays the completion date (today's date)
                LocalDate date = LocalDate.now();
                // sets the format of how the date will display
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println("Completion date: " + date.format(format));
                // write to file
                finalisedToFile(newJob,date);
                promptEnterKey(sc);
                mainMenu(sc);
            }
        }
    }
    /**
     * Reads existing projects from the text file where they are stored and
     * creates object for them adding to the Project List.
     */
    private static void readFromFile(){
        /*
        This method will read through the text file and add each project that exists in it to the Project List
         */
        try {
            BufferedReader reader = new BufferedReader(new FileReader("poisedProjects.txt"));
            String project;
            // reads each line of the text file, splits it and then uses each array to create project objects
            while((project = reader.readLine()) != null) {
                // Splits each line into an Array so Project objects can then be created from it
                String[] pj = project.split("\\|");
                // create each "Person" so they can be added to the project object in the next step
                Person architect = new Person(pj[8],pj[9],pj[10],pj[11],pj[12]);
                Person contractor = new Person(pj[13],pj[14],pj[15],pj[16],pj[17]);
                Person customer = new Person(pj[18],pj[19],pj[20],pj[21],pj[22]);
                // converts the string date to a date format so calculations can be done on it
                LocalDate dueDate = getDate(pj[7]);
                // checks if project is completed and sets true or false
                boolean completed;
                if(pj[23].equals("true")){
                    completed = true;
                }else{
                    completed=false;
                }

                // Create the project object, some array elements are converted to Integer or Double from a String
                Project existingJob = new Project(Integer.parseInt(pj[0]),pj[1],pj[2],pj[3],Integer.parseInt(pj[4]),
                        Double.parseDouble(pj[5]),Double.parseDouble(pj[6]),dueDate,
                        architect,contractor,customer,completed);
                // Adds project to the list
                // currently adding the toString() method of the project object, want all information added.
                projectList.add(existingJob);
            }
            reader.close();
        } catch (IOException e) {
            // In case no file exists this will display.
            System.err.println("No existing projects file found.");
        }
    }
    /**
     * Writes the projects stored in the Project List to file.
     */
    private static void writeToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("poisedProjects.txt"));
            for (Project project : projectList) {
                writer.write(project.toFile() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            // In case of error writing to the text file
            System.err.println("Error saving projects to file");
        }
    }
    /**
     * Saves completed job information to a text file
     *
     * @param newJob project object
     */
    private static void finalisedToFile(Project newJob,LocalDate date){
        try {
            // Creates a 'Completed project (x)' file to save the information to. Where x=job number so that
            // multiple can exist, one for each job.
            BufferedWriter writer = new BufferedWriter(new FileWriter
                    (String.format("Completed project %s.txt",newJob.getJobNumber())));
            writer.write(newJob.completedToFile(date));
            writer.close();
        } catch (IOException e) {
            // In case of error writing to the text file
            System.err.println("Error writing to file");
        }
    }
    /**
     * Has the  user hit 'Enter' before returning to main menu.
     */
    public static void promptEnterKey(Scanner sc){
        System.out.println("Press \"ENTER\" to return to main menu...");
        sc.nextLine();
        mainMenu(sc);
    }
}