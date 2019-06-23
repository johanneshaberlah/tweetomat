package com.github.johanneshaberlah.tweetomat.client;

import com.github.johanneshaberlah.tweetomat.proto.*;
import com.google.common.util.concurrent.ListenableFuture;

public interface PartyService {

  ListenableFuture<PartyWordResponse> persistPartyWord(
      PartyWordPersistRequest partyWordPersistRequest);

  ListenableFuture<PartyResponse> findPartyBy(PartyRequest partyRequest);

  ListenableFuture<PartyResponse> persistParty(PartyPersistRequest partyPersistRequest);

  ListenableFuture<PartyResponse> analyseWords(WordAnalyseRequest wordAnalyseRequest);
}
