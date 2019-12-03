package agenda;

public class Main {


    public static void main(String[] args) {

        Agenda agenda = new Agenda();
       /*
        if(agenda.isEmpty()) {
            for (int i = 0; i< 10; i++){
                Entry e = new Entry();
                e.setName("" + i);
                e.setSurname("" + i + 10);
                agenda.addEntry(e);
            }
        }
        agenda.removeEntry("5");
        agenda.removeEntry("8");
        */
//        try {
//            agenda.loadAgenda();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Es empty = " + agenda.isEmpty());
        System.out.println("Num entries =" + agenda.nEntries());


    }
}
