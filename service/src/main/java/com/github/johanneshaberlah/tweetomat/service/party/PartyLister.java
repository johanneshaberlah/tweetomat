package com.github.johanneshaberlah.tweetomat.service.party;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

public interface PartyLister {

  Collection<Party> list();

  default Stream<Party> asStream() {
    return list().stream();
  }
}
