package com.github.johanneshaberlah.tweetomat.service;

import com.github.johanneshaberlah.tweetomat.service.party.Party;
import com.github.johanneshaberlah.tweetomat.service.party.PartyRepository;
import com.github.johanneshaberlah.tweetomat.service.party.PartyWord;
import com.github.johanneshaberlah.tweetomat.service.party.PartyWordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Part;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

  @Autowired
  private PartyRepository partyRepository;

  @Autowired
  private PartyWordRepository partyWordRepository;

  @Test
  public void contextLoads() {

  }
}
