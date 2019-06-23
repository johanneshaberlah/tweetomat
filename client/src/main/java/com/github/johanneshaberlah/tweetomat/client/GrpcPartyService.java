package com.github.johanneshaberlah.tweetomat.client;

import com.github.johanneshaberlah.tweetomat.proto.*;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.ManagedChannel;

import java.util.concurrent.Executors;

public class GrpcPartyService implements PartyService {
  private final ListeningExecutorService executorService =
      MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

  private ManagedChannel managedChannel;
  private PartyServiceGrpc.PartyServiceFutureStub futureStub;

  private GrpcPartyService(ManagedChannel managedChannel) {
    this.managedChannel = managedChannel;
    this.futureStub =
        PartyServiceGrpc.newFutureStub(this.managedChannel).withExecutor(this.executorService);
  }

  @Override
  public ListenableFuture<PartyWordResponse> persistPartyWord(
      PartyWordPersistRequest partyWordPersistRequest) {
    return this.futureStub.persistPartyWord(partyWordPersistRequest);
  }

  @Override
  public ListenableFuture<PartyResponse> findPartyBy(PartyRequest partyRequest) {
    return this.futureStub.findPartyBy(partyRequest);
  }

  @Override
  public ListenableFuture<PartyResponse> persistParty(PartyPersistRequest partyPersistRequest) {
    return this.futureStub.persistParty(partyPersistRequest);
  }

  @Override
  public ListenableFuture<PartyResponse> analyseWords(WordAnalyseRequest wordAnalyseRequest) {
    return this.futureStub.analyseWords(wordAnalyseRequest);
  }

  public static GrpcPartyService of(ManagedChannel managedChannel) {
    Preconditions.checkNotNull(managedChannel);
    return new GrpcPartyService(managedChannel);
  }
}
