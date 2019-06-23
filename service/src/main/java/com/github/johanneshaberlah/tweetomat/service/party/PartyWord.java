package com.github.johanneshaberlah.tweetomat.service.party;

import com.github.johanneshaberlah.tweetomat.proto.PartyWordResponse;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class, which represents a word used by a deputy of a party. The class is generally immutable and
 * should not be modified in the course of the program. The Spring Boot @Entity annotation allows
 * this class to be stored directly in MySQL by Spring Boot JPA. Here I used the Table Naming
 * Convention Snake Case, because this is the most useful one. To all who think about contributing:
 * Please also use Snake Case for other tables to ensure consistency.
 *
 */
@Entity
@Table(name = "party_words")
public class PartyWord {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private long id;

  @JoinColumn @ManyToOne private Party party;

  @Column private String text;

  private PartyWord() {}

  private PartyWord(Party party, String text) {
    this.party = party;
    this.text = text;
  }

  public long id() {
    return id;
  }

  public Party party() {
    return party;
  }

  public String text() {
    return text;
  }

  public PartyWordResponse buildResponse() {
    return PartyWordResponse.newBuilder()
        .setId(this.id)
        .setText(text)
        .setPartyResponse(this.party.buildResponse())
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PartyWord partyWord = (PartyWord) o;
    return id == partyWord.id
        && Objects.equal(party, partyWord.party)
        && Objects.equal(text, partyWord.text);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, party, text);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("party", party)
        .add("text", text)
        .toString();
  }

  public static PartyWord of(String text) {
    Preconditions.checkNotNull(text);
    return new PartyWord(null, text);
  }

  public static PartyWord of(Party party, String text) {
    Preconditions.checkNotNull(party);
    Preconditions.checkNotNull(text);
    return new PartyWord(party, text);
  }
}
