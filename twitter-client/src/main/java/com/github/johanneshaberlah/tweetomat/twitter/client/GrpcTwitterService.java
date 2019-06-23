package com.github.johanneshaberlah.tweetomat.twitter.client;

import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsRequest;
import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsResponse;
import com.github.johanneshaberlah.tweetomat.twitter.TwitterServiceGrpc;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.ManagedChannel;

import java.util.concurrent.Executors;

public class GrpcTwitterService implements TwitterService {
  private final ListeningExecutorService executorService =
      MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

  private ManagedChannel managedChannel;
  private TwitterServiceGrpc.TwitterServiceFutureStub futureStub;

  private GrpcTwitterService(ManagedChannel managedChannel) {
    this.managedChannel = managedChannel;
    this.futureStub =
        TwitterServiceGrpc.newFutureStub(this.managedChannel).withExecutor(this.executorService);
  }

  @Override
  public ListenableFuture<TwitterListTweetsResponse> findTweets(
      TwitterListTweetsRequest twitterListTweetsRequest) {
    return this.futureStub.findTweets(twitterListTweetsRequest);
  }

  public static TwitterService of(ManagedChannel managedChannel) {
    Preconditions.checkNotNull(managedChannel);
    return new GrpcTwitterService(managedChannel);
  }
}
