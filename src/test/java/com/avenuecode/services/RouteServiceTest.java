package com.avenuecode.services;

import com.avenuecode.exceptions.distance.NoSuchRouteException;
import com.avenuecode.models.Graph;
import com.avenuecode.models.Route;
import com.avenuecode.models.dto.RouteStopsSpecification;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 06/08/18.
 */
public class RouteServiceTest {

    Graph graphMock;

    RouteService routeService;

    @Before
    public void buildRouteService() {
        routeService = new RouteService();
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
    public void shouldEvaluateSourceCTargetCWith3MaxStops() throws NoSuchRouteException {
        List<RouteStopsSpecification> specifications = routeService.findAvailableRoutes(graphMock, "C", "C", 3);
        List<RouteStopsSpecification> specificationsExpected = Arrays.asList(new RouteStopsSpecification("CDC", 2),
                new RouteStopsSpecification("CEBC", 3));

        assertEquals(specificationsExpected.size(), specifications.size());
        assertTrue(specificationsExpected.stream().allMatch(specifications::contains));
    }


    @Test
    public void shouldEvaluateSourceATargetBWith4MaxStops() throws NoSuchRouteException {
        List<RouteStopsSpecification> specifications = routeService.findAvailableRoutes(graphMock, "A", "C", 4);
        List<RouteStopsSpecification> specificationsExpected = Arrays.asList(new RouteStopsSpecification("ABC", 2),
                                                                             new RouteStopsSpecification("ADC", 2),
                                                                            new RouteStopsSpecification("AEBC", 3),
                                                                            new RouteStopsSpecification("ADEBC", 4));
        assertEquals(specificationsExpected.size(), specifications.size());
        assertTrue(specificationsExpected.stream().allMatch(specifications::contains));
    }



}