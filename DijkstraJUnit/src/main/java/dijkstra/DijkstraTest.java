package dijkstra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DijkstraTest {

    private final Double inf = 0d;


    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void test2() { //NO ES CUADRADA
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }

    @Test
    public void test3() { // 1 x1
        Double[][] grafo = {
                {inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }

    @Test
    public void test4() { // 1 x 6
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 6);
        assertNotNull(dijkstra);
    }

    @Test
    public void test5() { // 1 x1
        Double[][] grafo = {
                {inf, 1d, 2d, inf, inf, inf},
                {inf, inf, inf, 1d, inf, inf},
                {inf, inf, inf, inf, 2d, inf},
                {inf, inf, 2d, inf, inf, 3d},
                {inf, inf, inf}
        };
        Dijkstra dijkstra = new Dijkstra(grafo, 1);
        assertNotNull(dijkstra);
    }


    @Test
    public void computeShortestPath() {

    }

    @Test
    public void getPath() {
    }

    @Test
    public void hasErrorHappened() {
    }
}