package com.avenuecode.services;

import com.avenuecode.models.Graph;
import com.avenuecode.models.Route;
import com.avenuecode.models.dto.PossibleRoutes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 06/08/18.
 */
public abstract class BasePathService {

    public List<PossibleRoutes> findPossibleRoutes(Graph graph, String source, String target) {
        List<Route> routes = graph.getData();
        List<PossibleRoutes> possibleRoutes = new ArrayList<>();
        List<PossibleRoutes> newRoutes;

        newRoutes = routes.stream()
                .filter(route -> route.getSource().equals(source))
                .map(r -> new PossibleRoutes().addRoute(r)).
                        collect(Collectors.toList());

        while (newRoutes.size() > 0) {
            possibleRoutes.addAll(newRoutes.stream()
                    .filter(possibleRoute -> possibleRoute.reachedTarget(target))
                    .collect(Collectors.toList()));

            newRoutes = newRoutes
                    .stream()
                    .filter(possibleRoute -> !possibleRoute.reachedTarget(target))
                    .map(possibleRoute -> {
                        List<Route> filteredRoutes = graph.getData().stream()
                                .filter(r -> !possibleRoute.alreadyPassedThisTown(r.getTarget()))
                                .filter(r -> possibleRoute.getLastRoute().getTarget().equals(r.getSource()))
                                .collect(Collectors.toList());

                        return filteredRoutes.stream()
                                .map(r -> {
                                    PossibleRoutes pr = PossibleRoutes.clone(possibleRoute);
                                    pr.addRoute(r);
                                    return pr;
                                })
                                .collect(Collectors.toList());
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

        }

        return possibleRoutes;
    }
}
