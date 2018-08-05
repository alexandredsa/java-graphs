package com.avenuecode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 05/08/18.
 */
@Entity
public class Graph {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Route> data = new ArrayList<>();

    public Graph() {
    }

    public Graph(Long id, List<Route> data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Route> getData() {
        return data;
    }

    public void setData(List<Route> data) {
        this.data = data;
    }
}
