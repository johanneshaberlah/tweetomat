package com.github.johanneshaberlah.tweetomat.service.analysis;

import com.github.johanneshaberlah.tweetomat.service.party.PartyWord;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.Set;

public class PartyWordList {
  private Set<PartyWord> partyWords;

  private PartyWordList(Set<PartyWord> partyWords) {
    this.partyWords = partyWords;
  }

  public Set<PartyWord> partyWords() {
    return Collections.unmodifiableSet(this.partyWords);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PartyWordList that = (PartyWordList) o;
    return Objects.equal(partyWords, that.partyWords);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(partyWords);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("partyWords", partyWords).toString();
  }

  public static PartyWordList of(Set<PartyWord> partyWords) {
    Preconditions.checkNotNull(partyWords);
    return new PartyWordList(partyWords);
  }
}
