package com.avenuecode.services;

import com.avenuecode.models.Graph;
import com.avenuecode.models.Route;
import com.avenuecode.models.dto.ResponseDistancePath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 05/08/18.
 */
@Service
public class DistanceService {

    public ResponseDistancePath findDistanceForPath(Graph graph, List<String> path) {
        List<Route> routes = graph.getData();
        List<Route> routesFound = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            Route routeToFind = new Route(path.get(i), path.get(i + 1));
            Route routeFound = routes.stream()
                    .filter(route -> route.getSource().equals(routeToFind.getSource())
                            && route.getTarget().equals(routeToFind.getTarget()))
                    .findFirst().orElse(null);

            if (routeFound == null) {
                return new ResponseDistancePath(-1);
            }

            routesFound.add(routeFound);
        }

        return new ResponseDistancePath(routesFound.stream()
                .map(Route::getDistance)
                .reduce(Integer::sum)
                .get());
    }

}
