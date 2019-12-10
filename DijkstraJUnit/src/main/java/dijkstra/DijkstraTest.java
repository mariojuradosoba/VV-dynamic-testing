package dijkstra;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DijkstraTest {

    private final Double inf = Double.MAX_VALUE;
    private int MIN = 0;
    private int MAX = 5;

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

    @Test(expected = Exception.class) //Checks that when not squared matrix is introduced, an exception rises.
    public void test2() throws Exception {
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
    }

    @Test
    public void test3() throws Exception { // 1 x1
        Double[][] grafo = {
                {inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }

    @Test(expected = Exception.class)
    public void test4() throws Exception {
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
    }

    @Test
    public void computerPathLauncher() { //49 tests Robust worst case boundary testing

        int cont = 0;
        Double[] results = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, 0.0d, 1.0d, 2.0d, 3.0d,
                5.0d, 8.0d, -1.0d, -1.0d, 1.7976931348623157E308d, 0.0d, 2.0d, 3.0d, 5.0d, 8.0d, -1.0d, -1.0d,
                1.7976931348623157E308d, 1.7976931348623157E308d, 0.0d, 1.0d, 3.0d, 6.0d, -1.0d, -1.0d, 1.7976931348623157E308d,
                1.7976931348623157E308d, 4.0d, 0.0d, 2.0d, 5.0d, -1.0d, -1.0d, 1.7976931348623157E308d, 1.7976931348623157E308d,
                2.0d, 3.0d, 0.0d, 3.0d, -1.0d, -1.0d, 1.7976931348623157E308d, 1.7976931348623157E308d, 1.7976931348623157E308d,
                1.7976931348623157E308d, 1.7976931348623157E308d, 0.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d,};


        for (int i = (MIN - 1); i <= (MAX + 1); ++i) {
            for (int j = (MIN - 1); j <= (MAX + 1); ++j) {
                Double dist = dijkstraOk.computeShortestPath(i, j);
                System.out.print(dist + "d, ");
                    assertEquals(results[cont], dist);
                    System.out.println("ini = " + i + " y  end = " + j + " result = " + dist);
                    cont ++;
            }
        }
    }


    private void computeShortestPathFail(int min, int max) {
        dijkstraOk.computeShortestPath(min, max);
    }

    private Double computeShortestPathOK(int min, int max) {
        return dijkstraOk.computeShortestPath(min, max);
    }


    @Test
    public void getPath() {

        ArrayList<Integer> path = dijkstraOk.getPath(1, 5);
        assertNull(path);
        assertTrue(dijkstraOk.hasErrorHappened());

        dijkstraOk.computeShortestPath(1, 5);
        path = dijkstraOk.getPath(1, 5);

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
        Boolean error = dijkstraOk.hasErrorHappened();
        assertFalse(error);

        dijkstraOk.computeShortestPath(5, -1);
        error = dijkstraOk.hasErrorHappened();
        assertTrue(error);

    }
}