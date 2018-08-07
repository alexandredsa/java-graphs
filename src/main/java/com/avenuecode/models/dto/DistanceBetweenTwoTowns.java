package com.avenuecode.models.dto;

import com.avenuecode.models.Route;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 05/08/18.
 */
public class DistanceBetweenTwoTowns implements RoutesProcessor{
    private List<String> path = new ArrayList<>();
    private int distance = -1;

    private transient List<Route> routes = new ArrayList<>();


    public DistanceBetweenTwoTowns(List<Route> routes) {
        this.routes = routes;
    }

    public DistanceBetweenTwoTowns(int distance) {
        this.distance = distance;
    }

    public DistanceBetweenTwoTowns() {
    }

    public static DistanceBetweenTwoTowns clone(DistanceBetweenTwoTowns that) {
        DistanceBetweenTwoTowns distanceBetweenTwoTowns = new DistanceBetweenTwoTowns();
        distanceBetweenTwoTowns.path = new ArrayList<>(that.getPath());
        distanceBetweenTwoTowns.distance = that.getDistance();
        distanceBetweenTwoTowns.routes = new ArrayList<>(that.getRoutes());
        return distanceBetweenTwoTowns;
    }


    public int getDistance() {
        return distance;
    }

    public List<String> getPath() {
        return path;
    }

    @JsonIgnore
    public List<Route> getRoutes() {
        return routes;
    }

    @JsonIgnore
    public DistanceBetweenTwoTowns addRoute(Route route) {
        if (routes.size() == 0) {
            path.add(route.getSource());
            this.distance = 0;
        }

        path.add(route.getTarget());

        distance += route.getDistance();
        this.routes.add(route);

        return this;
    }

    @JsonIgnore
    public boolean hasTown(String town){
        return this.path.contains(town);
    }

    @JsonIgnore
    public Route getLastRoute() {
        if (this.routes.size() == 0) {
            throw new UnsupportedOperationException();
        }

        return this.routes.get(this.routes.size() - 1);
    }

}
