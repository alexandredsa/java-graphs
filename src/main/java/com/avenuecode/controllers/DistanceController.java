package com.avenuecode.controllers;

import com.avenuecode.exceptions.graph.GraphNotFoundException;
import com.avenuecode.models.Graph;
import com.avenuecode.models.dto.RequestDistancePath;
import com.avenuecode.models.dto.ResponseDistancePath;
import com.avenuecode.models.dto.RouteStopsSpecification;
import com.avenuecode.repository.GraphRepository;
import com.avenuecode.services.DistanceService;
import com.avenuecode.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by alexandre on 05/08/18.
 */

@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private DistanceService distanceService;


    @PostMapping("/{graphId}")
    ResponseEntity<ResponseDistancePath> findDistanceForPath(@PathVariable("graphId") Long graphId, @RequestBody Optional<RequestDistancePath> requestDistancePath) {
        Optional<Graph> graph = Optional.ofNullable(graphRepository.findOne(graphId));

        if (!graph.isPresent()) {
            throw new GraphNotFoundException();
        }

        if(!requestDistancePath.isPresent() || requestDistancePath.get().getPath().size() == 0){
            return new ResponseEntity<>(new ResponseDistancePath(0), HttpStatus.OK);
        }

        ResponseDistancePath responseDistancePath = distanceService.findDistanceForPath(graph.get(), requestDistancePath.get().getPath());

        return new ResponseEntity<>(responseDistancePath, HttpStatus.OK);
    }
}
