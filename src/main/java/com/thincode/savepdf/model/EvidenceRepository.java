package com.thincode.savepdf.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author carpinteyror
 * @version 1.0
 * @date 15 oct. 2020
 */
@Repository
public interface EvidenceRepository extends CrudRepository<Evidence, Long> {

}
