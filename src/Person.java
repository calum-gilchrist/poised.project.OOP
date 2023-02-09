/**
 * Used to create/make changes to people involved in a project.
 *
 * @author Calum Gilchrist
 */
public class Person {
    // Attributes
    String role;
    String name;
    String phoneNumber;
    String emailAddress;
    String physicalAddress;

    // Constructor
    /**
     * Create a person from the information listed
     *
     * @param role the persons role e.g.Architect, Constructor, Customer
     * @param name their name
     * @param phoneNumber their phone number
     * @param emailAddress their email address
     * @param physicalAddress their address
     */
    public Person (String role, String name, String phoneNumber, String emailAddress, String physicalAddress){
        this.role = role;
        this.name = name;
        this. phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    // Setters
    public void setRole(String newRole) {
        role = newRole;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }
    public void setEmailAddress(String newEmailAddress) {
        emailAddress = newEmailAddress;
    }
    public void setPhysicalAddress(String newPhysicalAddress) {
        physicalAddress = newPhysicalAddress;
    }

    // Getters
    public String getRole() {
        return role;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * * Information about the person formatted to an easily readable format.
     *
     * @return a string that contains the information about the person
     */
    // Methods
    public String toString() {
        return role +
                "\nName:            " + name +
                "\nPhone number:    " + phoneNumber +
                "\nEmail address:   " + emailAddress +
                "\nAddress:         " + physicalAddress;

    }
}
