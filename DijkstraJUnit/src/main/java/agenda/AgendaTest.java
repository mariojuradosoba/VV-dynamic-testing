package agenda;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
        if(agenda.isEmpty()) {
            for (int i = 0; i< 10; i++){
                Entry e = new Entry();
                e.setName("Nombre" + i);
                e.setSurname("Apellidos" + i);
                e.setAddress("Direccion" + i);
                e.setCity("Poblacion" + i);
                e.setCounty("Provincia" + i);
                e.setZip("Codigo" + i);
                e.setTelephone("Telefono" + i);
                e.setBirthYear( i*1000);

                agenda.addEntry(e);
            }
        }
        boolean result = agenda.saveAgenda();
        Agenda agendaNueva = new Agenda();
        agendaNueva.loadAgenda();
        assertTrue(result);
        assert(!agendaNueva.isEmpty());
        assert (agendaNueva.nEntries() == 10);
    }

    @Test
    public void loadAgenda() throws IOException {

        Agenda a = new Agenda();
        a.saveAgenda();



        boolean result = agenda.loadAgenda();
        assertFalse(result);


        if(agenda.isEmpty()) {
            for (int i = 0; i< 10; i++){
                Entry e = new Entry();
                e.setName("Nombre" + i);
                e.setSurname("Apellidos" + i);
                e.setAddress("Direccion" + i);
                e.setCity("Poblacion" + i);
                e.setCounty("Provincia" + i);
                e.setZip("Codigo" + i);
                e.setTelephone("Telefono" + i);
                e.setBirthYear( i*1000);

                agenda.addEntry(e);
            }
        }
        agenda.saveAgenda();
        Agenda agendaNueva = new Agenda();
        result = agendaNueva.loadAgenda();
        assertTrue(result);
        assert(!agendaNueva.isEmpty());
        assert (agendaNueva.nEntries() == 10);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            //CLASES DE EQUIVALENCIA
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void addEntry1(){
        agenda.addEntry(new Entry());
        assert(agenda.nEntries()==1);
        assert(!agenda.isEmpty());
    }

    @Test
    public void addEntry2(){
        agenda.addEntry(new Entry());
        assert(agenda.nEntries()==1);
        assert(!agenda.isEmpty());
    }








}