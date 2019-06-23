package com.github.johanneshaberlah.tweetomat.service.analysis;

import com.github.johanneshaberlah.tweetomat.service.party.Party;
import com.google.common.base.Preconditions;

public class PartyEntry implements Comparable<PartyEntry> {
  private Party party;
  private int fitness;

  private PartyEntry(Party party, int fitness) {
    this.party = party;
    this.fitness = fitness;
  }

  public int fitness() {
    return fitness;
  }

  public Party party() {
    return party;
  }

  @Override
  public int compareTo(PartyEntry entry) {
    return Integer.compare(fitness, entry.fitness);
  }

  public static PartyEntry of(Party party, int fitness) {
    Preconditions.checkNotNull(party);
    return new PartyEntry(party, fitness);
  }
}
