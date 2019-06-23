package com.github.johbanneshaberlah.tweetomat.web;

import com.github.johanneshaberlah.tweetomat.client.PartyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PartyController {

  private PartyClient client;

  @Autowired
  private PartyController(PartyClient client) {
    this.client = client;
  }

  @RequestMapping(value = "/analyse/{name}", method = RequestMethod.GET)
  public String analyse(@PathVariable("name") String name, ModelMap model) {
    System.out.println("/analyse/" + name);
    model.put("name", name);
    return "index";
  }
}
