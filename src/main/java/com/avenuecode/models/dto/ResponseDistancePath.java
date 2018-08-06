package com.avenuecode.models.dto;

import java.util.List;

/**
 * Created by alexandre on 06/08/18.
 */
public class ResponseDistancePath {
    private int distance;

    public ResponseDistancePath(int distance) {
        this.distance = distance;
    }

    public ResponseDistancePath() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
