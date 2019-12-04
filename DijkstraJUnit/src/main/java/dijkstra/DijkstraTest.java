package dijkstra;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DijkstraTest {

    private final Double inf = 0d;
    private Dijkstra dijkstraOk;

    @Before
    public void setUp() throws Exception {


        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, 2d, 3d, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };

        dijkstraOk = new Dijkstra(grafo, 6);

    }

    @Test
    public void test2() throws Exception { //NO ES CUADRADA
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNull(dijkstra);
    }

    @Test
    public void test3() throws Exception { // 1 x1
        Double[][] grafo = {
                {inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }

    @Test
    public void test4() throws Exception { // 1 x 6
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }


    @Test
    public void computeShortestPath() {

        Double dist = dijkstraOk.computeShortestPath(1, 5);
        assert (dist == 8);
    }

    @Test
    public void getPath() {

        dijkstraOk.computeShortestPath(1, 5);
        ArrayList<Integer> path = dijkstraOk.getPath(1, 5);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        expected.add(4);
        expected.add(3);
        expected.add(1);

        for (int i = 0; i < expected.size(); i++) {
            assert (expected.get(i).equals(path.get(i)));
        }
    }

    @Test
    public void hasErrorHappened() {
        dijkstraOk.computeShortestPath(1, 5);
        Boolean error =dijkstraOk.hasErrorHappened();
        assertFalse(error);

        dijkstraOk.computeShortestPath(5, 1);
        error =dijkstraOk.hasErrorHappened();
        assertFalse(error);

    }
}