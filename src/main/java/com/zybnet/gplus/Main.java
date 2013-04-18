package com.zybnet.gplus;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("gplus")
public class Main extends UI {
  
  private static final long serialVersionUID = -8321945541107800404L;
  
  public static final String DOCK_WIDTH = "100px";
  private static final String HEADER_HEIGHT = "60px";
  
  @Override
  public void init(VaadinRequest req) {
    // TODO use DI
    IDataSource ds = new FakeDataSource();
    
    HorizontalLayout header = new HorizontalLayout();
    Label headerLabel = new Label("Hello World!");
    headerLabel.setSizeUndefined();
    header.addComponent(headerLabel);
    header.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
    header.setWidth("100%");
    header.setHeight(HEADER_HEIGHT);
    
    // Setup content
    HorizontalLayout content = new HorizontalLayout();
    Component dock = dock(), posts = posts(ds);
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
    dock.setSpacing(true);
    dock.setWidth(DOCK_WIDTH);
    dock.setStyleName("dock");
    
    makeDockButton("Calendar", "dock-calendar", dock);
    makeDockButton("Palette", "dock-palette", dock);
    makeDockButton("Update", "dock-update", dock);
    
    return dock;
  }
  
  private Button makeDockButton(String caption, String slug, AbstractOrderedLayout dock) {
    Button b = new Button(caption);
    b.setPrimaryStyleName(slug);
    dock.addComponent(b);
    dock.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
    return b;
  }
  
  private Component posts(IDataSource ds) {
    Table posts = new Table();
    posts.setStyleName("posts");
    posts.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
    posts.setSizeFull();
    posts.addContainerProperty("Post", PostDisplay.class, null);
    for (int i = 0; i < 10; i++) {
      posts.addItem(new Object[] { new PostDisplay(ds.findPostById(Integer.toString(i))) }, i);
    }
    return posts;
  }
}

