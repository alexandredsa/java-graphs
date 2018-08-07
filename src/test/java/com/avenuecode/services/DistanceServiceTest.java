package com.avenuecode.services;

import com.avenuecode.exceptions.distance.NoSuchRouteException;
import com.avenuecode.models.Graph;
import com.avenuecode.models.Route;
import com.avenuecode.models.dto.DistanceBetweenTwoTowns;
import com.avenuecode.models.dto.ResponseDistancePath;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 06/08/18.
 */
public class DistanceServiceTest {

    Graph graphMock;

    DistanceService distanceService;

    @Before
    public void buildDistanceService() {
        distanceService = new DistanceService();
    }

    @Before
    public void buildGraphMock() {
        graphMock = new Graph();
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("A", "B", 5));
        routes.add(new Route("B", "C", 4));
        routes.add(new Route("C", "D", 8));
        routes.add(new Route("D", "C", 8));
        routes.add(new Route("D", "E", 6));
        routes.add(new Route("A", "D", 5));
        routes.add(new Route("C", "E", 2));
        routes.add(new Route("E", "B", 3));
        routes.add(new Route("A", "E", 7));
        graphMock.setData(routes);
    }

    @Test
    public void shouldABCPathReturn9() throws NoSuchRouteException {
        ResponseDistancePath responseDistancePath = distanceService.findDistanceForPath(graphMock, Arrays.asList("A", "B", "C"));
        assertEquals(responseDistancePath.getDistance(), 9);
    }

    @Test
    public void shouldADPathReturn5() throws NoSuchRouteException {
        ResponseDistancePath responseDistancePath = distanceService.findDistanceForPath(graphMock, Arrays.asList("A", "D"));
        assertEquals(responseDistancePath.getDistance(), 5);
    }

    @Test
    public void shouldADCPathReturn13() throws NoSuchRouteException {
        ResponseDistancePath responseDistancePath = distanceService.findDistanceForPath(graphMock, Arrays.asList("A", "D", "C"));
        assertEquals(responseDistancePath.getDistance(), 13);
    }

    @Test
    public void shouldAEBCDathReturn22() throws NoSuchRouteException {
        ResponseDistancePath responseDistancePath = distanceService.findDistanceForPath(graphMock, Arrays.asList("A", "E", "B", "C", "D"));
        assertEquals(responseDistancePath.getDistance(), 22);
    }

    @Test(expected = NoSuchRouteException.class)
    public void shouldAEDathReturnNoSuchRoute() throws NoSuchRouteException {
        distanceService.findDistanceForPath(graphMock, Arrays.asList("A", "E", "D"));
    }

    @Test
    public void shouldReturnShortestPathAtoC(){
       DistanceBetweenTwoTowns distanceBetweenTwoTowns = distanceService.findDistanceBetweenTwoTowns(graphMock, "A", "C");
       assertEquals(distanceBetweenTwoTowns.getDistance(), 9);
    }

    @Test
    public void shouldReturnShortestPathBtoB(){
        DistanceBetweenTwoTowns distanceBetweenTwoTowns = distanceService.findDistanceBetweenTwoTowns(graphMock, "B", "B");
        assertEquals(distanceBetweenTwoTowns.getDistance(), 0);
    }
}