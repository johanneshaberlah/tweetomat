package com.github.johanneshaberlah.tweetomat.service.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

  Optional<Party> findByNameEquals(String name);

}
