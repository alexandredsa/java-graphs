package com.avenuecode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by alexandre on 05/08/18.
 */
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String source;

    @NotNull
    private String target;

    @Min(0)
    private int distance;

    public Route() {
    }

    public Route(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public Route(String source, String target, int distance) {
        this.source = source;
        this.target = target;
        this.distance = distance;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
