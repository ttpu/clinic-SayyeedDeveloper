public class Person {
    private final String first, last, ssn;
    private Doctor doctor;
    public Person(String first, String last, String ssn) {
        this.first = first;
        this.last = last;
        this.ssn = ssn;
    }

    public String getSSN(){
        return this.ssn;
    }

    public String getFirst() {
        return this.first;
    }

    public String getLast() {
        return this.last;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public Doctor getDoctor() {
        return this.doctor;
    }
}
