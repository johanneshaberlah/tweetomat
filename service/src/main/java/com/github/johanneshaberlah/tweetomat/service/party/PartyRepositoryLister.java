package com.github.johanneshaberlah.tweetomat.service.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PartyRepositoryLister implements PartyLister {

  private PartyRepository partyRepository;

  @Autowired
  public PartyRepositoryLister(PartyRepository partyRepository) {
    this.partyRepository = partyRepository;
  }

  @Override
  public Collection<Party> list() {
    return this.partyRepository.findAll();
  }
}
