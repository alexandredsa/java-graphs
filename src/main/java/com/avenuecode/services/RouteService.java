package com.avenuecode.services;

import com.avenuecode.models.Graph;
import com.avenuecode.models.dto.PossibleRoutes;
import com.avenuecode.models.dto.RouteStopsSpecification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 05/08/18.
 */
@Service
public class RouteService extends BasePathService {

    public List<RouteStopsSpecification> findAvailableRoutes(Graph graph, String source, String target, int maxStops) {
        List<PossibleRoutes> possibleRoutes = this.findPossibleRoutes(graph, source, target);
        List<RouteStopsSpecification> routeStopsSpecifications = possibleRoutes.stream()
                .map(possibleRoute -> (RouteStopsSpecification) possibleRoute.fillRouteProcessor(new RouteStopsSpecification()))
                .filter(possibleRoute -> possibleRoute.getStops() <= maxStops)
                .collect(Collectors.toList());

        return routeStopsSpecifications;
    }
}
