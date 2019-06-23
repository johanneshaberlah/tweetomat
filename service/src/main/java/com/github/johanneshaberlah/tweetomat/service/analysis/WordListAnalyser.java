package com.github.johanneshaberlah.tweetomat.service.analysis;

import com.github.johanneshaberlah.tweetomat.service.party.Party;
import com.github.johanneshaberlah.tweetomat.service.party.PartyLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WordListAnalyser {
  private PartyLister party;
  private WordListComparator wordListComparator;

  @Autowired
  private WordListAnalyser(PartyLister party, WordListComparator wordListComparator) {
    this.party = party;
    this.wordListComparator = wordListComparator;
  }

  private PartyEntry analyseParty(Party party, PartyWordList wordList) {
    int fitness = wordListComparator.compare(wordList, PartyWordList.of(party.partyWords()));
    return PartyEntry.of(party, fitness);
  }

  public Optional<Party> findBestMatchingParty(PartyWordList wordList) {
    return party
        .asStream()
        .map(party -> analyseParty(party, wordList))
        .sorted()
        .findFirst()
        .map(PartyEntry::party);
  }
}
