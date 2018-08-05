package com.avenuecode.repository;

import com.avenuecode.models.Graph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alexandre on 05/08/18.
 */
@Repository
public interface GraphRepository extends CrudRepository<Graph, Long> {
}
