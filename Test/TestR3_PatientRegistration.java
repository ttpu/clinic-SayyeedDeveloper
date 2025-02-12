import junit.framework.TestCase;

import java.util.Collection;

public class TestR3_PatientRegistration extends TestCase {

    public void testPatientDoctor() throws NoSuchPatient, NoSuchDoctor {

        Clinic c = new Clinic();

        c.addPatient("Gianni", "Bianchi", "BNCGNN34T30A854T");
        c.addDoctor("Mario", "Rossi", "MRARSS56T12L435F",2,"Heart");

        c.assignPatientToDoctor("BNCGNN34T30A854T", 2);

        Person p = c.getPatient("BNCGNN34T30A854T");
        Doctor d = p.getDoctor();

        assertEquals("Mario", d.getFirst());
        assertEquals("Rossi", d.getLast());
        assertEquals("MRARSS56T12L435F", d.getSSN());
        assertEquals(2, d.getId());
        assertEquals("Heart", d.getSpecialization());

    }

    public void testListOfPatients() throws NoSuchPatient, NoSuchDoctor {

        Clinic c = new Clinic();

        c.addPatient("Gianni", "Bianchi", "BNCGNN34T30A854T");
        c.addPatient("Marco", "Verdi", "VRDMRC54D21S345F");
        c.addDoctor("Mario", "Rossi", "MRARSS56T12L435F",2,"Heart");

        c.assignPatientToDoctor("BNCGNN34T30A854T", 2);
        c.assignPatientToDoctor("MRARSS56T12L435F", 2);

        Doctor d = c.getDoctor(2);

        Collection<Person> listOfPatients = d.getPatients();

        assertEquals(2,listOfPatients.size());

    }

}
