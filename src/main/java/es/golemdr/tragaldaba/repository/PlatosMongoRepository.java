package es.golemdr.tragaldaba.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.PlatoMongo;

@Repository
public interface PlatosMongoRepository extends MongoRepository<PlatoMongo, String> {
//	public PlatoMongo findByNombre(String nombrePlato, Pageable pageable);
	public PlatoMongo findByIdPlato(Long idPlato);
}

/* 
 * Spring Data MongoDB uses the MongoTemplate to execute the queries behind your findXXX methods. 
 * You can use the template yourself for more complex queries, but this guide does not cover that. 
 * Note: see the Spring Data MongoDB Reference Guide: 
 * 		 https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference
 * 
 */
