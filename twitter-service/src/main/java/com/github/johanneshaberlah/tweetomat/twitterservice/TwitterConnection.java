package com.github.johanneshaberlah.tweetomat.twitterservice;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class TwitterConnection {

  private TwitterConfiguration twitterConfiguration;

  private TwitterConnection(TwitterConfiguration twitterConfiguration) {
    this.twitterConfiguration = twitterConfiguration;
  }

  public Twitter establish() {
    return new TwitterFactory(this.twitterConfiguration.configuration()).getInstance();
  }

  public TwitterConfiguration twitterConfiguration() {
    return twitterConfiguration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TwitterConnection that = (TwitterConnection) o;
    return Objects.equal(twitterConfiguration, that.twitterConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(twitterConfiguration);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("twitterConfiguration", twitterConfiguration)
        .toString();
  }

  public static TwitterConnection of(TwitterConfiguration twitterConfiguration) {
    Preconditions.checkNotNull(twitterConfiguration);
    return new TwitterConnection(twitterConfiguration);
  }
}
