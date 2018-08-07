package com.avenuecode.models.dto;

import com.avenuecode.models.Route;

import java.util.LinkedHashSet;

/**
 * Created by alexandre on 06/08/18.
 */
public class PossibleRoutes {
    private LinkedHashSet<Route> routes = new LinkedHashSet<>();
    private Route lastRoute;

    public PossibleRoutes addRoute(Route route) {
        this.routes.add(route);
        this.lastRoute = route;
        return this;
    }

    public LinkedHashSet<Route> getRoutes() {
        return routes;
    }

    public Route getLastRoute() {
        return lastRoute;
    }

    public boolean reachedTarget(String target) {
        return this.lastRoute.getTarget().equals(target);
    }

    public boolean hasTown(String town){
       return this.routes.stream()
               .anyMatch(route -> route.getTarget().equals(town) || route.getSource().equals(town));
    }

    public static PossibleRoutes clone(PossibleRoutes that) {
        PossibleRoutes possibleRoutes = new PossibleRoutes();
        possibleRoutes.lastRoute = that.lastRoute;
        possibleRoutes.routes = new LinkedHashSet<>(that.routes);
        return possibleRoutes;
    }

    public RoutesProcessor fillRouteProcessor(RoutesProcessor routesProcessor) {
        this.routes.forEach(routesProcessor::addRoute);
        return routesProcessor;
    }
}
