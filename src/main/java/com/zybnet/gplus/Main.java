package com.zybnet.gplus;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class Main extends UI {
  @Override
  public void init(VaadinRequest req) {
    setContent(new Label("GPlus in Vaadin!"));
    getPage().setTitle("GPlus");
  }
}

