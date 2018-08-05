package com.avenuecode.controllers;

import com.avenuecode.exceptions.graph.GraphNotFoundException;
import com.avenuecode.models.Graph;
import com.avenuecode.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by alexandre on 05/08/18.
 */

@RestController
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    private GraphRepository graphRepository;

    @PostMapping
    Graph save(@RequestBody Graph graph) {
        return graphRepository.save(graph);
    }

    @GetMapping("/{id}")
    Graph findById(@PathVariable("id") Long id) {
        Optional<Graph> graph = Optional.ofNullable(graphRepository.findOne(id));

        if (!graph.isPresent()) {
            throw new GraphNotFoundException();
        }

        return graph.get();
    }

}
