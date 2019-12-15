package agenda;

import java.io.*;

class AgendaNode {
    Entry info;
    AgendaNode sig;

    AgendaNode(Entry p, AgendaNode siguiente) {
        info = p;
        sig = siguiente;
    }
}

public class Agenda implements AgendaInterface {
    private AgendaNode first;
    private int numEntries;

    public Agenda() {
        first = null;
        numEntries = 0;
    }

    /**
     * STATIC ANALYSIS
     * The function addEntry must add a new contact to the agenda. This function will
     * receive the values to each field of the contact person. The new person will be stored into a
     * list sorted by name and surname. If the agenda is empty, then the person added will be
     * the first contact in the list. This function must return true if the
     * contact has been stored successfully and false in the case of duplicate values of name and
     * surname fields.
     */
    public boolean addEntry(Entry p) {
        AgendaNode cur = null;
        AgendaNode prev = null;
        boolean found = false;

        if (first != null) {
            cur = first;

            while (cur != null && cur.info.getName().compareToIgnoreCase(p.getName()) <= 0 && !found) {
                if (cur.info.getName().equals(p.getName()) && cur.info.getSurname().equals(p.getSurname())) {
                    found = true;
                } else {
                    prev = cur;
                    cur = cur.sig;
                }
            }

            if (found) {
                return false;
            } else {
                AgendaNode newNode = new AgendaNode(p, cur);
                prev.sig= newNode;
                this.numEntries++;
                return true;
            }
        } else {
            first = new AgendaNode(p, null);
            this.numEntries++;
            return true;
        }
    }


    /**
     * STATIC ANALYSIS
     * The function removeEntry must remove a contact from the agenda. This function will
     * receive the name of the contact person that must be removed. If the agenda is empty the
     * function does not have any impact in the list. If the agenda has only one contact and this
     * contact is the one to be removed, the resulted list must be empty. This function must return
     * true if the contact has been removed successfully and false in the opposite.
     */
    public boolean removeEntry(String name) {
        if (first == null) {
            return false;
        }

        if (first.info.getName().equals(name)) {
            first = first.sig;
            numEntries--;
            return true;
        }

        AgendaNode prev = first;
        while (prev.sig != null) {
            if (prev.sig.info.getName().equals(name)) {
                prev.sig = prev.sig.sig;
                numEntries--;
                return true;
            }

            prev = prev.sig;
        }
        return false;

    }

    /**
     * STATIC ANALYSIS
     * The function removeFirst must remove the first contact of the agenda. If the agenda is
     * empty the function does not have any impact in the list. If the agenda has only one contact,
     * this contact must be removed and the resulted list must be empty. This function must return
     * a Entry object if the contact has been removed successfully and null in the opposite.
     */
    public Entry removeFirst() {
        Entry p = null;

        if (first != null) {
            p = first.info;
            first = first.sig;
            numEntries--;
        }

        return p;
    }

    public int nEntries() {
        return numEntries;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * STATIC ANALYSIS
     * The function saveAgenda must store the agenda in a text file named agendafile.txt.
     * If the agenda is empty, the function does not create the file. The agenda must remain
     * unchanged after the saving. The function returns true if the agenda was successfully
     * saved and false otherwise.
     */
    public boolean saveAgenda() throws IOException {
        AgendaNode cur = first;
        boolean success = false;

        if (cur != null) {
            String line;
            Parser p = new Parser();

            FileWriter fichero = new FileWriter("agendafile.txt");
            BufferedWriter bufferescritura = new BufferedWriter(fichero);
            PrintWriter output = new PrintWriter(bufferescritura);

            while (cur != null) {
                if (!success) {
                    success = true;
                }
                p.insertEntry(cur.info);
                line = p.getLine();
                output.println(line);
                cur = cur.sig;
            }

            output.close();
        }
        return success;
    }

    /**
     * STATIC ANALYSIS
     * The function loadAgenda must load the agenda from the file agendafile.txt,
     * adding all the entries in that file. If the agenda is not empty, all the
     * pre-existing contacts are removed and the final agenda only contain
     * the entries of the file. If the file agendafile.txt is empty or
     * does not exists, the agenda remain unchanged.
     * The function returns true is the file exists and false otherwise.
     */
    public boolean loadAgenda() throws IOException {
        FileReader filein = new FileReader("agendafile.txt");
        BufferedReader bufferin = new BufferedReader(filein);

        Parser p = new Parser();
        String cad;
        if ((cad = bufferin.readLine()) == null) {
            bufferin.close();
            return false;
        }

        do {
            System.out.println(cad);
            p.insertLine(cad);
            Entry auxEntry = p.getEntry();
            if (auxEntry.hasData()) {
                addEntry(auxEntry);
            }
        } while ((cad = bufferin.readLine()) != null);
        filein.close();

        return true;
    }
}
