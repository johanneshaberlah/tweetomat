package com.github.johanneshaberlah.tweetomat.twitter.client;

import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsRequest;
import com.github.johanneshaberlah.tweetomat.twitter.TwitterListTweetsResponse;
import com.google.common.util.concurrent.ListenableFuture;

public interface TwitterService {

  ListenableFuture<TwitterListTweetsResponse> findTweets(
      TwitterListTweetsRequest twitterListTweetsRequest);
}
