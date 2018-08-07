package com.avenuecode.controllers;

import com.avenuecode.exceptions.distance.NoSuchRouteException;
import com.avenuecode.exceptions.graph.GraphNotFoundException;
import com.avenuecode.models.Graph;
import com.avenuecode.models.dto.DistanceBetweenTwoTowns;
import com.avenuecode.models.dto.RequestDistancePath;
import com.avenuecode.models.dto.ResponseDistancePath;
import com.avenuecode.repository.GraphRepository;
import com.avenuecode.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by alexandre on 05/08/18.
 */

@RestController
@RequestMapping("/distance")
public class DistanceController {

    private final GraphRepository graphRepository;

    private final DistanceService distanceService;

    @Autowired
    public DistanceController(GraphRepository graphRepository, DistanceService distanceService) {
        this.graphRepository = graphRepository;
        this.distanceService = distanceService;
    }


    @PostMapping("/{graphId}")
    ResponseEntity<ResponseDistancePath> findDistanceForPath(@PathVariable("graphId") Long graphId, @RequestBody Optional<RequestDistancePath> requestDistancePath) {
        Optional<Graph> graph = Optional.ofNullable(graphRepository.findOne(graphId));

        if (!graph.isPresent()) {
            throw new GraphNotFoundException();
        }

        if (!requestDistancePath.isPresent() || requestDistancePath.get().getPath().size() <= 1) {
            return new ResponseEntity<>(new ResponseDistancePath(0), HttpStatus.OK);
        }


        ResponseDistancePath responseDistancePath = null;
        try {
            responseDistancePath = distanceService.findDistanceForPath(graph.get(), requestDistancePath.get().getPath());
        } catch (NoSuchRouteException e) {
            responseDistancePath = new ResponseDistancePath(-1);
        }

        return new ResponseEntity<>(responseDistancePath, HttpStatus.OK);
    }


    @PostMapping("/{graphId}/from/{source}/to/{target}")
    ResponseEntity<DistanceBetweenTwoTowns> findDistanceBetweenTwoTowns(@PathVariable("graphId") Long graphId, @PathVariable("source") String source, @PathVariable("target") String target) {
        Optional<Graph> graph = Optional.ofNullable(graphRepository.findOne(graphId));

        if (!graph.isPresent()) {
            throw new GraphNotFoundException();
        }

        DistanceBetweenTwoTowns distanceBetweenTwoTowns = distanceService.findDistanceBetweenTwoTowns(graph.get(), source, target);
        return new ResponseEntity<>(distanceBetweenTwoTowns, HttpStatus.OK);
    }


}
