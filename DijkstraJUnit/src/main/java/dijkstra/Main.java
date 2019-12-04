package dijkstra;

public class Main {


    public static void main(String[] args) throws Exception {

        Double inf = 0d;
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, 2d, 3d, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };

        Dijkstra dijkstra = new Dijkstra(grafo, 6);

        System.out.println(dijkstra.computeShortestPath(1, 5));
        System.out.println(dijkstra.getPath(1, 5));
        System.out.println(dijkstra.hasErrorHappened());



    }
}
