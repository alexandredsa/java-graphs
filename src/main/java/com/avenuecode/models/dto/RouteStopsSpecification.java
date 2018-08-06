package com.avenuecode.models.dto;

import com.avenuecode.models.Route;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 05/08/18.
 */
public class RouteStopsSpecification {
    private String route;
    private int stops;

    private transient List<Route> routes = new ArrayList<>();

    public RouteStopsSpecification(String route, int stops) {
        this.route = route;
        this.stops = stops;
    }

    public RouteStopsSpecification(List<Route> routes) {
        this.routes = routes;
    }

    public RouteStopsSpecification() {
    }

    public String getRoute() {
        return route;
    }


    public int getStops() {
        return stops;
    }


    public RouteStopsSpecification addRoute(Route route) {
        this.routes.add(route);
        this.stops++;

        if (this.stops == 1) {
            this.route = String.format("%s%s", route.getSource(), route.getTarget());
            return this;
        }

        this.route = String.format("%s%s", this.route, route.getTarget());
        return this;
    }

    @JsonIgnore
    public Route getLastRoute() {
        if (this.routes.size() == 0) {
            throw new UnsupportedOperationException();
        }

        return this.routes.get(this.routes.size() - 1);
    }


    /*public RouteStopsSpecification buildFromRoutes() {
        if (routes.size() == 0) {
            throw new UnsupportedOperationException();
        }

        this.route = routes.get(0).getSource();
        this.stops = routes.size();

        this.route += routes.stream()
                .filter(route -> !route.getTarget().equals(this.route))
                .map(Route::getTarget)
                .collect(Collectors.joining());

        return this;
    }*/
}
