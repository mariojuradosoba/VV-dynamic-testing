package agenda;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        Agenda agenda = new Agenda();
/*

        if(agenda.isEmpty()) {
            for (int i = 0; i< 10; i++){
                Entry e = new Entry();
                e.setName("nombre" + i);
                e.setSurname("apellidos" + i);
                e.setAddress("drieccion" + i);
                e.setCity("Pobla" + i);
                e.setCounty("Provincia" + i);
                e.setZip("Codigo" + i);
                e.setTelephone("Telefono" + i);
                e.setBirthYear( i*1000);

                agenda.addEntry(e);
            }
        }
        agenda.removeEntry("5");
        agenda.removeEntry("8");
*/

        try {
            agenda.loadAgenda();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Es empty = " + agenda.isEmpty());
        System.out.println("Num entries =" + agenda.nEntries());
        Entry entry =  new Entry();
        try {
            agenda.saveAgenda();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
