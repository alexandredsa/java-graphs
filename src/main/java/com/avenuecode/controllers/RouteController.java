package com.avenuecode.controllers;

import com.avenuecode.exceptions.graph.GraphNotFoundException;
import com.avenuecode.models.Graph;
import com.avenuecode.models.dto.RouteStopsSpecification;
import com.avenuecode.repository.GraphRepository;
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
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private RouteService routeService;


    @GetMapping("/{graphId}/from/{source}/to/{target}")
    ResponseEntity<List<RouteStopsSpecification>> findAvailableRoutes(@PathVariable("graphId") Long graphId, @PathVariable("source") String source, @PathVariable("target") String target, @RequestParam("maxStops") Optional<Integer> maxStops) {
        Optional<Graph> graph = Optional.ofNullable(graphRepository.findOne(graphId));

        if (!graph.isPresent()) {
            throw new GraphNotFoundException();
        }

        List<RouteStopsSpecification> specifications = routeService.findAvailableRoutes(graph.get(), source, target, maxStops.orElseGet(() -> graph.get().getData().size()));
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }
}
