package com.avenuecode.services;

import com.avenuecode.models.Graph;
import com.avenuecode.models.Route;
import com.avenuecode.models.dto.RouteStopsSpecification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 05/08/18.
 */
@Service
public class RouteService {

    public List<RouteStopsSpecification> findAvailableRoutes(Graph graph, String source, String target, int maxStops) {
        List<RouteStopsSpecification> specifications = new ArrayList<>();
        for (int i = 1; i <= maxStops; i++) {

            if (i == 1) {
                specifications = graph.getData().stream()
                        .filter(route -> route.getSource().equals(source))
                        .map(route -> new RouteStopsSpecification().addRoute(route))
                        .collect(Collectors.toList());

                continue;
            }

            specifications
                    .stream()
                    .filter(specification -> !specification.getRoute().contains(target))
                    .forEach(specification -> {
                        Route route = graph.getData().stream()
                                .filter(r -> r.getSource().equals(specification.getLastRoute().getTarget()))
                                .filter(r -> !specification.getRoute().contains(r.getTarget()))
                                .findFirst()
                                .orElse(null);

                        if (route != null) {
                            specification.addRoute(route);
                        }
                    });

        }

        return specifications.stream()
                .filter(specification -> specification.getRoute().contains(target))
                .collect(Collectors.toList());

    }


}
