package agenda;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.*;


public class AgendaTest {

    Agenda agenda;
    Entry entry1, entry2, entry3;

    @Before
    public void setUp() throws Exception {
        agenda = new Agenda();
        entry1 = new Entry();
        entry2 = new Entry();
        entry3 = new Entry();

        entry1.setName("NAME1");
        entry1.setSurname("SURNAME1");

        entry2.setName("NAME2");
        entry2.setSurname("SURNAME2");

        entry3.setName("NAME3");
        entry3.setSurname("SURNAME3");
    }

    @Test
    public void Agenda() {
        Agenda a = new Agenda();
        assert (a != null);
    }

    @Test
    public void addEntry() {
        agenda.addEntry(entry1);

        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 1);

        agenda.addEntry(entry2);

        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 2);

        agenda.addEntry(entry2);

        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 2);
    }


    @Test
    public void removeEntry() {

        agenda.removeEntry("AGENDA VACIA");
        assert (agenda.isEmpty());
        assert (agenda.nEntries() == 0);

        agenda.addEntry(entry1);
        agenda.addEntry(entry2);
        agenda.addEntry(entry3);

        agenda.removeEntry("NAME2");
        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 2);

        agenda.removeEntry("NAME INVALID");
        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 2);

        agenda.removeEntry("NAME1");
        assert (!agenda.isEmpty());
        assert (agenda.nEntries() == 1);

        agenda.removeEntry("NAME3");
        assert (agenda.isEmpty());
        assert (agenda.nEntries() == 0);

    }

    @Test
    public void removeFirst() {
        agenda.addEntry(entry1);
        agenda.addEntry(entry2);

        Entry firstRemoved = agenda.removeFirst();

        assert (firstRemoved.getName().equals("NAME2"));
        assert (firstRemoved.getSurname().equals("SURNAME2"));
    }

    @Test
    public void nEntries() {

        assert (agenda.nEntries() == 0);
        agenda.addEntry(entry1);
        assert (agenda.nEntries() == 1);

    }

    @Test
    public void isEmpty() {
        assert (agenda.isEmpty());
        agenda.addEntry(entry1);
        assert (!agenda.isEmpty());
    }


    @Test
    public void saveAgenda() throws IOException {
        if (agenda.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                Entry e = new Entry();
                e.setName("Nombre" + i);
                e.setSurname("Apellidos" + i);
                e.setAddress("Direccion" + i);
                e.setCity("Poblacion" + i);
                e.setCounty("Provincia" + i);
                e.setZip("Codigo" + i);
                e.setTelephone("Telefono" + i);
                e.setBirthYear(i * 1000);

                agenda.addEntry(e);
            }
        }
        boolean result = agenda.saveAgenda();
        Agenda agendaNueva = new Agenda();
        agendaNueva.loadAgenda();
        assertTrue(result);
        assert (!agendaNueva.isEmpty());
        assert (agendaNueva.nEntries() == 10);
    }

    @Test
    public void loadAgenda() throws IOException {

        Agenda a = new Agenda();
        a.saveAgenda();


        boolean result = agenda.loadAgenda();
        assertFalse(result);


        if (agenda.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                Entry e = new Entry();
                e.setName("Nombre" + i);
                e.setSurname("Apellidos" + i);
                e.setAddress("Direccion" + i);
                e.setCity("Poblacion" + i);
                e.setCounty("Provincia" + i);
                e.setZip("Codigo" + i);
                e.setTelephone("Telefono" + i);
                e.setBirthYear(i * 1000);

                agenda.addEntry(e);
            }
        }
        agenda.saveAgenda();
        Agenda agendaNueva = new Agenda();
        result = agendaNueva.loadAgenda();
        assertTrue(result);
        assert (!agendaNueva.isEmpty());
        assert (agendaNueva.nEntries() == 10);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CLASES DE EQUIVALENCIA
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void addEntry1() {
        boolean result = agenda.addEntry(new Entry());
        assert (agenda.nEntries() == 1);
        assert (!agenda.isEmpty());
        assert (result);
    }

    @Test
    public void addEntry2() {
        Entry entry1 = new Entry();
        entry1.setName("entry1");
        agenda.addEntry(entry1);
        boolean result = agenda.addEntry(entry1);

        assert (agenda.nEntries() == 1);
        assert (!agenda.isEmpty());
        assert (!result);
    }

    @Test
    public void addEntry3() {
        Entry entry1 = new Entry();
        entry1.setName("entry1");
        agenda.addEntry(entry1);

        Entry entry2 = new Entry();
        entry2.setName("entry2");
        boolean result = agenda.addEntry(entry2);

        assert (agenda.nEntries() == 2);
        assert (!agenda.isEmpty());
        assert (result);
    }

    @Test
    public void removeFirst1() {
        Entry entry = agenda.removeFirst();
        assertNull(entry);
    }

    @Test
    public void removeFirst2() {
        Entry entry = new Entry();
        entry.setName("entry");
        agenda.addEntry(entry);
        Entry result = agenda.removeFirst();
        assertNotNull(result);
        assert (result.getName().equals("entry"));
    }

    @Test
    public void nEntries1() {
        int result = agenda.nEntries();
        assert (result == 0);
    }

    @Test
    public void isEmpty1() {
        boolean result = agenda.isEmpty();
        assert (result);
    }

    @Test
    public void isEmpty2() {
        agenda.addEntry(new Entry());
        boolean result = agenda.isEmpty();
        assert (!result);
    }

    @Test
    public void removeEntry1() {
        boolean result = agenda.removeEntry("entry");
        assert (!result);

    }

    @Test
    public void removeEntry2() {
        agenda.addEntry(entry1);
        boolean result = agenda.removeEntry("NAME1");
        assert (result);
        assert (agenda.nEntries() == 0);
        assert (agenda.isEmpty());

    }

    @Test
    public void removeEntry3() {
        agenda.addEntry(entry1);
        boolean result = agenda.removeEntry("entry");
        assert (!result);
        assert (agenda.nEntries() == 1);
        assert (!agenda.isEmpty());

    }

    @Test
    public void removeEntry4() {
        agenda.addEntry(entry1);
        agenda.addEntry(entry2);
        boolean result = agenda.removeEntry("NAME1");
        assert (result);
        assert (agenda.nEntries() == 1);
        assert (!agenda.isEmpty());

    }

    @Test
    public void removeEntry5() {
        agenda.addEntry(entry1);
        agenda.addEntry(entry2);
        boolean result = agenda.removeEntry("entry");
        assert (!result);
        assert (agenda.nEntries() == 2);
        assert (!agenda.isEmpty());

    }

    @Test
    public void saveAgenda1() throws IOException {
        boolean result = agenda.saveAgenda();
        assert (!result);
    }

    @Test
    public void saveAgenda2() throws IOException {
        agenda.addEntry(entry1);
        boolean result = agenda.saveAgenda();
        assert (result);
    }

    @Test
    public void loadAgenda1() throws IOException {

        new PrintWriter("agendafile.txt").close(); // This is to clean the file
        boolean result = agenda.loadAgenda();
        assert (!result);
        assert (agenda.isEmpty());
        assert (agenda.nEntries() == 0);
    }

    @Test
    public void loadAgenda2() throws IOException {
        agenda.addEntry(entry1);
        agenda.saveAgenda();

        Agenda newAgenda = new Agenda();
        boolean result = newAgenda.loadAgenda();
        assert (result);
        assert (!newAgenda.isEmpty());
        assert (newAgenda.nEntries() == 1);
    }

    @Test
    public void loadAgenda3() throws IOException {
        agenda.addEntry(entry1);
        agenda.addEntry(entry2);
        agenda.saveAgenda();

        Agenda newAgenda = new Agenda();
        boolean result = newAgenda.loadAgenda();
        assert (result);
        assert (!newAgenda.isEmpty());
        assert (newAgenda.nEntries() == 2);
    }

}