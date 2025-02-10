import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Doctor extends Person{

    private final Integer docID;
    private final String specialization;
    private HashMap<String, Person> patients = new HashMap <>();

    public Doctor(String first, String last, String ssn, int docID, String specialization){
        super(first, last, ssn);
        this.docID = docID;
        this.specialization = specialization;
    }
    public int getId(){
        return this.docID;
    }

    public String getSpecialization(){
        return this.specialization;
    }

    public void addPatients(String ssn, Person patient){
        patients.put(ssn, patient);
    }

    public Collection<Person> getPatients() {
        return patients.values();
    }
}
