package com.github.johanneshaberlah.tweetomat.service.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that allows CRUD operations on the PartyWord entity. It is generally only used to
 * store PartyWords, but also has all CRUD functions. It uses the JpaRepository class from Spring
 * Boot, so Superclass, which in combination with the @Repository Annotation makes sure that later
 * via ByteCodeManipulation the CRUD functions are manipulated here. A detailed documentation can be
 * found here: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 *
 */
@Repository
public interface PartyWordRepository extends JpaRepository<PartyWord, Long> {}
