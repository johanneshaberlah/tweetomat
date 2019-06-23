package com.github.johanneshaberlah.tweetomat.service.party;

import com.github.johanneshaberlah.tweetomat.proto.*;
import com.github.johanneshaberlah.tweetomat.service.analysis.PartyWordList;
import com.github.johanneshaberlah.tweetomat.service.analysis.WordListAnalyser;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Collectors;

@GRpcService
public final class PartyGrpcService extends PartyServiceGrpc.PartyServiceImplBase {

  private PartyRepository partyRepository;
  private PartyWordRepository partyWordRepository;
  private WordListAnalyser wordListAnalyser;

  @Autowired
  private PartyGrpcService(
      PartyRepository partyRepository,
      PartyWordRepository partyWordRepository,
      WordListAnalyser wordListAnalyser) {
    this.partyRepository = partyRepository;
    this.partyWordRepository = partyWordRepository;
    this.wordListAnalyser = wordListAnalyser;
  }

  @Override
  public void persistPartyWord(
      PartyWordPersistRequest request, StreamObserver<PartyWordResponse> responseObserver) {
    if (!request.hasPartyResponse() || request.getText().isEmpty()) {
      responseObserver.onNext(PartyWordResponse.newBuilder().setErrorCode(ErrorCode.ERROR).build());
      responseObserver.onCompleted();
      return;
    }
    Optional<Party> existingParty =
        this.partyRepository.findById(request.getPartyResponse().getId());
    if (!existingParty.isPresent()) {
      responseObserver.onNext(PartyWordResponse.newBuilder().setErrorCode(ErrorCode.ERROR).build());
      responseObserver.onCompleted();
      return;
    }
    Party party = existingParty.get();
    PartyWord partyWord = this.partyWordRepository.save(PartyWord.of(party, request.getText()));
    responseObserver.onNext(partyWord.buildResponse());
    responseObserver.onCompleted();
  }

  @Override
  public void findPartyBy(PartyRequest request, StreamObserver<PartyResponse> responseObserver) {
    Optional<Party> existingParty;
    if (request.getName().isEmpty()) {
      existingParty = this.partyRepository.findById(request.getId());
    } else {
      existingParty = this.partyRepository.findByNameEquals(request.getName());
    }
    if (!existingParty.isPresent()) {
      responseObserver.onNext(PartyResponse.newBuilder().setErrorCode(ErrorCode.ERROR).build());
      responseObserver.onCompleted();
    } else {
      responseObserver.onNext(existingParty.get().buildResponse());
      responseObserver.onCompleted();
    }
  }

  @Override
  public void analyseWords(
      WordAnalyseRequest request, StreamObserver<PartyResponse> responseObserver) {
    PartyWordList partyWordList =
        PartyWordList.of(
            request.getWordsList().stream().map(PartyWord::of).collect(Collectors.toSet()));
    Optional<Party> optionalParty = this.wordListAnalyser.findBestMatchingParty(partyWordList);
    if (!optionalParty.isPresent()) {
      responseObserver.onNext(
          PartyResponse.newBuilder()
              .setErrorCode(ErrorCode.ERROR)
              .setName("No party found!")
              .build());
      responseObserver.onCompleted();
    } else {
      Party party = optionalParty.get();
      responseObserver.onNext(party.buildResponse());
      responseObserver.onCompleted();
    }
  }

  @Override
  public void persistParty(
      PartyPersistRequest request, StreamObserver<PartyResponse> responseObserver) {
    if (request.getName().isEmpty()) {
      responseObserver.onNext(PartyResponse.newBuilder().setErrorCode(ErrorCode.ERROR).build());
      responseObserver.onCompleted();
      return;
    }
    Party party = this.partyRepository.save(Party.of(request.getName()));
    responseObserver.onNext(party.buildResponse());
    responseObserver.onCompleted();
  }
}
