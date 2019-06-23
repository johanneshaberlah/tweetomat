package com.github.johanneshaberlah.tweetomat.service.analysis;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class WordListComparator implements Comparator<PartyWordList> {

  @Override
  public int compare(PartyWordList wordList, PartyWordList targetWordList) {
    return Sets.union(wordList.partyWords(), targetWordList.partyWords()).size();
  }
}
