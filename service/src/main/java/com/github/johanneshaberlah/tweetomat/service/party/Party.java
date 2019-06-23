package com.github.johanneshaberlah.tweetomat.service.party;

import com.github.johanneshaberlah.tweetomat.proto.ErrorCode;
import com.github.johanneshaberlah.tweetomat.proto.PartyResponse;
import com.github.johanneshaberlah.tweetomat.proto.PartyWordResponse;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Class, which represents a party. It currently contains only an id to which it joins and a naming
 * key that points to a property resource bundle.
 */
@Entity
@Table(name = "parties")
public class Party {

  /**
   * This field displays the auto incrementing Id of the party. It is also the primary key of the
   * table and is essential for later use of join statements.
   */
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private long id;

  @Column(name = "name")
  @NotBlank
  @NotNull
  private String name;

  @OneToMany(mappedBy = "party")
  private Collection<PartyWord> partyWords;

  private Party() {}

  private Party(String name, Collection<PartyWord> partyWords) {
    this.name = name;
    this.partyWords = partyWords;
  }

  public long id() {
    return id;
  }

  public String name() {
    return name;
  }

  public Set<PartyWord> partyWords() {
    return Sets.newHashSet(partyWords);
  }

  public PartyResponse buildResponse() {
    return PartyResponse.newBuilder()
      .setErrorCode(ErrorCode.SUCCESS)
      .setId(this.id())
      .setName(this.name())
      .addAllPartyWords(Collections.emptyList())
      .setErrorCode(ErrorCode.SUCCESS)
      .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Party party = (Party) o;
    return id == party.id
        && Objects.equal(name, party.name)
        && Objects.equal(partyWords, party.partyWords);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, partyWords);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("namingKey", name)
        .add("partyWords", partyWords)
        .toString();
  }

  public static Party create(String namingKey, Collection<PartyWord> partyWords) {
    Preconditions.checkNotNull(namingKey);
    return new Party(namingKey, partyWords);
  }

  public static Party of(String namingKey) {
    Preconditions.checkNotNull(namingKey);
    return create(namingKey, Lists.newArrayList());
  }
}
