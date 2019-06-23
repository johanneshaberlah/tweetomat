package com.github.johanneshaberlah.tweetomat.twitterservice;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
  private String oAuthConsumerKey;
  private String oAuthConsumerSecret;
  private String oAuthAccessToken;
  private String oAuthAccessTokenSecret;

  private TwitterConfiguration(
      String oAuthConsumerKey,
      String oAuthConsumerSecret,
      String oAuthAccessToken,
      String oAuthAccessTokenSecret) {
    this.oAuthConsumerKey = oAuthConsumerKey;
    this.oAuthConsumerSecret = oAuthConsumerSecret;
    this.oAuthAccessToken = oAuthAccessToken;
    this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
  }

  public Configuration configuration() {
    return new ConfigurationBuilder()
        .setDebugEnabled(true)
        .setOAuthConsumerKey(this.oAuthConsumerKey)
        .setOAuthConsumerSecret(this.oAuthConsumerSecret)
        .setOAuthAccessToken(this.oAuthAccessToken)
        .setOAuthAccessTokenSecret(this.oAuthAccessTokenSecret)
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TwitterConfiguration that = (TwitterConfiguration) o;
    return Objects.equal(oAuthConsumerKey, that.oAuthConsumerKey)
        && Objects.equal(oAuthConsumerSecret, that.oAuthConsumerSecret)
        && Objects.equal(oAuthAccessToken, that.oAuthAccessToken)
        && Objects.equal(oAuthAccessTokenSecret, that.oAuthAccessTokenSecret);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        oAuthConsumerKey, oAuthConsumerSecret, oAuthAccessToken, oAuthAccessTokenSecret);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("oAuthConsumerKey", oAuthConsumerKey)
        .add("oAuthConsumerSecret", oAuthConsumerSecret)
        .add("oAuthAccessToken", oAuthAccessToken)
        .add("oAuthAccessTokenSecret", oAuthAccessTokenSecret)
        .toString();
  }

  public static TwitterConfiguration create(
      String oAuthConsumerKey,
      String oAuthConsumerSecret,
      String oAuthAccessToken,
      String oAuthAccessTokenSecret) {
    Preconditions.checkNotNull(oAuthConsumerKey);
    Preconditions.checkNotNull(oAuthConsumerSecret);
    Preconditions.checkNotNull(oAuthAccessToken);
    Preconditions.checkNotNull(oAuthAccessTokenSecret);
    return new TwitterConfiguration(
        oAuthConsumerKey, oAuthConsumerSecret, oAuthAccessToken, oAuthAccessTokenSecret);
  }
}
