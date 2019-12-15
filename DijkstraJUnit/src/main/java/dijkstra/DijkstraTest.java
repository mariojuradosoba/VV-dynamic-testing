package dijkstra;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DijkstraTest {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //VALORES LÍMITE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final Double inf = Double.MAX_VALUE;
    private final int MIN = 0;
    private final int MAX = 5;

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

        dijkstraOk = new Dijkstra(grafo, grafo.length);

    }

    @Test(expected = Exception.class) //Checks that when not squared matrix is introduced, an exception rises.
    public void test1() throws Exception {
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, grafo.length);
    }

    @Test
    public void test2() throws Exception { // 1 x1
        Double[][] grafo = {
                {inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, grafo.length);
        assertNotNull(dijkstra);
    }

    @Test(expected = Exception.class)
    public void test3() throws Exception {
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, grafo.length);
    }

    @Test(expected = Exception.class)
    public void test4() throws Exception {
        Double[][] grafo = {
                {inf},
                {1d},
                {inf},
                {inf},
                {3d}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 5);
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
                cont++;
            }
        }
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CLASES DE EQUIVALENCIA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void getPathTest1() {

        ArrayList<Integer> result = dijkstraOk.getPath(1, 3);
        assertNull(result);
    }

    @Test
    public void getPathTest2() {
        dijkstraOk.computeShortestPath(1, 5);
        ArrayList<Integer> result = dijkstraOk.getPath(null, null);
        assertEquals(result.size(), 1);
        assertNull(result.get(0));

    }

    @Test
    public void getPathTest3() {

        ArrayList<Integer> estimatedValues = new ArrayList<Integer>(Arrays.asList(3, 1));
        dijkstraOk.computeShortestPath(1, 5);
        ArrayList<Integer> result = dijkstraOk.getPath(1, 3);
        assertNotNull(result);
        assertArrayEquals(result.toArray(), estimatedValues.toArray());

    }


    //////////////////////?????????????????????????????
    @Test
    public void nextCur1() throws Exception {
        Double[][] grafo = {
                {inf}
        };

        Dijkstra dijkstra = new Dijkstra(grafo, 1);
        dijkstra.computeShortestPath(0, 0);


    }
    /////////////////??????????????????????????????????


    @Test
    public void computeShortestPath1() {

        Double result = dijkstraOk.computeShortestPath(-1, 5);
        assert (result == -1d);
        assert (dijkstraOk.hasErrorHappened());
    }

    @Test
    public void computeShortestPath2() {

        Double result = dijkstraOk.computeShortestPath(0, -1);
        assert (result == -1d);
        assert (dijkstraOk.hasErrorHappened());
    }

    @Test
    public void computeShortestPath3() {

        Double result = dijkstraOk.computeShortestPath(8, 0);
        assert (result == -1d);
        assert (dijkstraOk.hasErrorHappened());
    }

    @Test
    public void computeShortestPath4() {

        Double result = dijkstraOk.computeShortestPath(0, 8);
        assert (result == -1d);
        assert (dijkstraOk.hasErrorHappened());
    }

    @Test
    public void computeShortestPath5() throws Exception {

        Double[][] grafo = {
                {0d}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 1);
        Double result = dijkstra.computeShortestPath(0, 0);
        assert (result == 0);
        assert (!dijkstraOk.hasErrorHappened());
    }

    @Test
    public void computeShortestPath6() {

        ArrayList<Integer> estimatedValues = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 1));

        Double dist = dijkstraOk.computeShortestPath(1, 5);
        ArrayList<Integer> result = dijkstraOk.getPath(1, 5);
        assert (dist == 8);
        assertArrayEquals(estimatedValues.toArray(), result.toArray());
    }
}