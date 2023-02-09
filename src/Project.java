import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
* Class for creating/making changes to projects
*
* @author Calum Gilchrist
 */
public class Project {
    // Attributes
    int jobNumber;
    String jobName;
    String buildType;
    String physicalAddress;
    int erfNumber;
    double totalFee;
    double totalPaid;
    LocalDate deadline;
    Person architect;
    Person contractor;
    Person customer;
    boolean completed;

    // Constructor
    /**
     * Create a project from the information listed
     *
     * @param jobNumber the jobs assigned number
     * @param jobName jobs name
     * @param buildType the type of building the project is
     * @param physicalAddress the address of where the project is being constructed
     * @param erfNumber ERF number (would be UPRN in UK)
     * @param totalFee total cost of the project
     * @param totalPaid how much has been paid
     * @param deadline completion date
     * @param architect architect of the project
     * @param contractor contractor building it
     * @param customer customer for the project
     * @param completed has the project been completed or not
     */
    public Project (int jobNumber, String jobName, String buildType, String physicalAddress,
                    int erfNumber, double totalFee, double totalPaid, LocalDate deadline,
                    Person architect, Person contractor, Person customer, boolean completed){
        this.jobNumber = jobNumber;
        this.buildType = buildType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.deadline = deadline;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
        this.completed = completed;
        this.setJobName(jobName);
        // required 'setJobName' to be placed after 'this.customer' or else the
        // setter will encounter an error when naming the project if none given by user
    }

    // Setters
    public void setJobNumber(int newJobNumber){
        jobNumber = newJobNumber;
    }
    public void setJobName(String newJobName){
        // If the job name is not given by the user it will generate a name by combining
        // the building type and the users name.
        if (newJobName.equals("")){
            this.jobName=this.buildType+ " " + this.customer.name;
        }
        else{
            this.jobName = newJobName;
        }
    }
    public void setBuildType(String newBuildType){
        this.buildType = newBuildType;
    }
    public void setPhysicalAddress(String newPhysicalAddress){
        physicalAddress = newPhysicalAddress;
    }
    public void setErfNumber(int newErfNumber) {
        erfNumber = newErfNumber;
    }
    public void setTotalFee(double newTotalFee) {
        totalFee = newTotalFee;
    }
    public void setTotalPaid(double newTotalPaid) {
        totalPaid = newTotalPaid;
    }
    public void setDeadline(LocalDate newDeadline) {
        deadline = newDeadline;
    }
    public void setArchitect(Person newArchitect) {
        architect = newArchitect;
    }
    public void setContractor(Person newContractor) {
        contractor = newContractor;
    }
    public void setCustomer(Person newCustomer) {
        customer = newCustomer;
    }
    public void setCompleted(boolean newCompleted) {
        completed = newCompleted;
    }

    // Getters
    public int getJobNumber(){
        return jobNumber;
    }
    public String getJobName() {
        return jobName;
    }
    public String getBuildType() {
        return buildType;
    }
    public String getPhysicalAddress() {
        return physicalAddress;
    }
    public int getErfNumber() {
        return erfNumber;
    }
    public double getTotalFee() {
        return totalFee;
    }
    public double getTotalPaid() {
        return totalPaid;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public Person getArchitect() {
        return architect;
    }
    public Person getContractor() {
        return contractor;
    }
    public Person getCustomer() {
        return customer;
    }
    public boolean getCompleted(){
        return completed;
    }

    /**
     * Information about the project formatted to an easily readable format.
     *
     * @return a string that will display all of the job information as well as
     * the architect, contractor and customer names.
     */
    // Methods
    public String toString() {
        // Default format that the projects will be displayed to the user
        return
                "\nJob Number:      " + jobNumber +
                "\nJob Name:        " + jobName +
                "\nBuild Type:      " + buildType +
                "\nProject Address: " + physicalAddress +
                "\nERF Number:      " + erfNumber +
                "\nProject Total:   " + totalFee +
                "\nAmount Paid:     " + totalPaid +
                "\nBuild Deadline:  " + dateOutput(deadline) +
                "\nArchitect:       " + architect.name +
                "\nContractor:      " + contractor.name +
                "\nCustomer:        " + customer.name;
    }

    /**
     * Creates a single line String containing all information about a job
     * and people involved. This is what will be saved to file so that jobs
     * can be saved and viewed/modified in future.
     *
     * @return string containing all information about job and people involved
     */
    public String toFile() {
        // Formats the project in a way that it can be written to the text file
        return
                jobNumber+"|"+jobName+"|"+buildType+"|"+physicalAddress+"|"+erfNumber+"|"+totalFee+"|"+
                totalPaid+"|"+dateOutput(deadline)+"|"+
                architect.role+"|"+architect.name+"|"+architect.phoneNumber+"|"+
                architect.emailAddress+"|"+architect.physicalAddress+"|"+
                contractor.role+"|"+contractor.name+"|"+contractor.phoneNumber+"|"+
                contractor.emailAddress+"|"+contractor.physicalAddress+"|"+
                customer.role+"|"+customer.name+"|"+customer.phoneNumber+"|"+
                customer.emailAddress+"|"+customer.physicalAddress+"|"+completed;
    }
    public String completedToFile(LocalDate date){
        return
                        "Job Number:      " + jobNumber +
                        "\nJob Name:        " + jobName +
                        "\nBuild Type:      " + buildType +
                        "\nProject Address: " + physicalAddress +
                        "\nERF Number:      " + erfNumber +
                        "\nProject Total:   " + totalFee +
                        "\nAmount Paid:     " + totalPaid +
                        "\nAmount Owed:     " + (totalFee-totalPaid) +
                        "\nBuild Deadline:  " + dateOutput(deadline) +
                        "\nCompletion Date: " + dateOutput(date) +
                        "\n\n-----Architect-----" + "\n" + architect +
                        "\n\n-----Contractor-----" + "\n" + contractor +
                        "\n\n-----Customer-----"+  "\n" + customer;
    }

    /**
     * Format todays date into an easily readable format.
     *
     * @param date the current date
     * @return the current date formatted to an easily readable format
     */
    private String dateOutput(LocalDate date){
        // This will take the date and convert it to a String in a format that is easily readable
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(format);


    }
}
