package agenda;

import java.io.IOException;

/**
 * Generic interface that must be implemented
 * by an Agenda123.java.
 */
public interface AgendaInterface {
    boolean addEntry(Entry p);

    boolean removeEntry(String nombre);

    Entry removeFirst();

    boolean isEmpty();

    int nEntries();
//	boolean saveAgenda() throws IOException;
//	boolean loadAgenda() throws IOException;
}
