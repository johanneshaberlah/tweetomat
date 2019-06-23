package com.github.johanneshaberlah.tweetomat.twitterservice;

import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsRequest;
import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsResponse;
import com.github.johanneshaberlah.tweetomat.twitter.TwitterServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Collection;
import java.util.stream.Collectors;

@GRpcService
public class TwitterGrpcService extends TwitterServiceGrpc.TwitterServiceImplBase {
  private final int TWEETS_TO_SCAN = 100;

  private Twitter twitter;

  @Autowired
  private TwitterGrpcService(Twitter twitter) {
    this.twitter = twitter;
  }

  @Override
  public void findTweets(
      TwitterListTweetsRequest request,
      StreamObserver<TwitterListTweetsResponse> responseObserver) {
    try {
      Collection<String> tweets =
          this.twitter.getUserTimeline(request.getName(), new Paging(1, TWEETS_TO_SCAN)).stream()
              .map(Status::getText)
              .collect(Collectors.toSet());
      responseObserver.onNext(TwitterListTweetsResponse.newBuilder().addAllTweets(tweets).build());
      responseObserver.onCompleted();
    } catch (TwitterException e) {
      responseObserver.onError(e);
      responseObserver.onCompleted();
    }
  }
}
