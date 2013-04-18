package com.zybnet.gplus;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Table.ColumnHeaderMode;

public class Main extends UI {
  
  private static final long serialVersionUID = -8321945541107800404L;
  
  public static final String DOCK_WIDTH = "200px";
  private static final String HEADER_HEIGHT = "60px";
  
  @Override
  public void init(VaadinRequest req) {
    
    HorizontalLayout header = new HorizontalLayout();
    Label headerLabel = new Label("Hello World!");
    headerLabel.setSizeUndefined();
    header.addComponent(headerLabel);
    header.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
    header.setWidth("100%");
    header.setHeight(HEADER_HEIGHT);
    
    // Setup content
    HorizontalLayout content = new HorizontalLayout();
    Component dock = dock(), posts = posts();
    content.setSizeFull();
    content.addComponents(dock, posts);
    content.setExpandRatio(posts, 1);
    
    VerticalLayout rootLayout = new VerticalLayout();
    rootLayout.setSizeFull();
    rootLayout.setMargin(new MarginInfo(false, true, false, false));
    rootLayout.addComponents(header, content);
    rootLayout.setExpandRatio(content, 1);
    
    setContent(rootLayout);
    
    getPage().setTitle("GPlus");
  }
  
  private Component dock() {
    VerticalLayout dock = new VerticalLayout();
    Component[] buttons = {new Button("Hola chicos"), new Button("Hello world")};
    dock.setWidth(DOCK_WIDTH);
    for (Component c : buttons) {
      dock.addComponent(c);
      dock.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
    }
    return dock;
  }
  
  private Component posts() {
    Table posts = new Table();
    posts.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
    posts.setSizeFull();
    posts.addContainerProperty("Name", String.class, null);
    for (int i = 0; i < 1000; i++) {
      posts.addItem(new Object[] {"Hello Moto"}, i);
    }
    return posts;
  }
}

