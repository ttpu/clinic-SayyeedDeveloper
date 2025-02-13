import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Clinic {

    HashMap<String, Person> patients = new HashMap<String, Person>();
    HashMap<Integer, Doctor> doctors = new HashMap<Integer, Doctor>();

    public void addPatient(String first, String last, String ssn) {
        Person patient = new Person(first, last, ssn);
        patients.put(ssn, patient);
    }

    public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
        Doctor doctor = new Doctor(first, last, ssn, docID, specialization);
        doctors.put(docID, doctor);
        addPatient(first, last, ssn);
    }

    public Person getPatient(String ssn) throws NoSuchPatient {
        Person temp_patient = patients.get(ssn);
        if (temp_patient == null){
            throw new NoSuchPatient();
        }
        return temp_patient;
    }

    public Doctor getDoctor(int docID) throws NoSuchDoctor {
        Doctor temp_doctor = doctors.get(docID);
        if (temp_doctor == null){
            throw new NoSuchDoctor();
        }
        return temp_doctor;
    }

    public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
        if (doctors.get(docID) == null){
            throw new NoSuchPatient();
        }else if(patients.get(ssn) == null){
            throw new NoSuchDoctor();
        }else {
            Person temp_patient = patients.get(ssn);
            Doctor temp_doctor = doctors.get(docID);
            doctors.get(docID).addPatients(ssn, temp_patient);
            patients.get(ssn).setDoctor(temp_doctor);
        }
    }

    /**
     * returns the collection of doctors that have no patient at all, sorted in alphabetic order.
     */
    Collection<Doctor> idleDoctors(){
        List<Doctor> idleDoctors = new ArrayList<>();
        for (Doctor i : doctors.values()) {
            if (i.getPatients().size() == 0){
                idleDoctors.add(i);
            }
        }
        idleDoctors.sort((d1, d2) -> d1.getFirst().compareTo(d2.getFirst()));
        return idleDoctors;
    }

    /**
     * returns the collection of doctors that a number of patients larger than the average.
     */
    Collection<Doctor> busyDoctors(){
        List<Doctor> busyDoctors = new ArrayList<>();
        int totalPatients = 0;
        int doctorCount = doctors.size();
        for (Doctor i: doctors.values()){
            totalPatients += i.getPatients().size();
        }
        double averagePatients = (double) totalPatients / doctorCount;

        for(Doctor j : doctors.values()){
            if(j.getPatients().size() > averagePatients){
                busyDoctors.add(j);
            }
        }
        busyDoctors.sort((d1, d2) -> d1.getFirst().compareTo(d2.getFirst()));
        return busyDoctors;
    }

    /**
     * returns list of strings
     * containing the name of the doctor and the relative number of patients
     * with the relative number of patients, sorted by decreasing number.<br>
     * The string must be formatted as "<i>### : ID SURNAME NAME</i>" where <i>###</i>
     * represent the number of patients (printed on three characters).
     */
    Collection<String> doctorsByNumPatients(){
        return null;
    }

    /**
     * computes the number of
     * patients per (their doctor's) specialization.
     * The elements are sorted first by decreasing count and then by alphabetic specialization.<br>
     * The strings are structured as "<i>### - SPECIALITY</i>" where <i>###</i>
     * represent the number of patients (printed on three characters).
     */
    public Collection<String> countPatientsPerSpecialization(){
        return null;
    }

    public void loadData(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            try {
                String[] parts = line.split(";");
                if(parts[0].equals("P")){
                    addPatient(parts[1], parts[2], parts[3]);
                }else if(parts[0].equals("M")){
                    int docId = Integer.parseInt(parts[1]);
                    addDoctor(parts[2], parts[3], parts[4], docId, parts[5]);
                }else {
                    throw new IOException();
                }
            }catch (Exception e){}
        }
    }
}
