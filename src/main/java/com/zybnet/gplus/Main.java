package com.zybnet.gplus;

import com.vaadin.annotations.Theme;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("gplus")
public class Main extends UI {
  
  private static final long serialVersionUID = -8321945541107800404L;
  
  public static final String DOCK_WIDTH = "100px";
  private static final String HEADER_HEIGHT = "60px";
  
  private Window lastOpenedWindow;
  
  @Override
  public void init(VaadinRequest req) {
    // TODO use DI
    IDataSource ds = new FakeDataSource();
    
    HorizontalLayout header = new HorizontalLayout();
    header.addStyleName("main-header");
    header.setSpacing(true);
    TextField searchBar = new TextField();
    searchBar.addStyleName("search");
    searchBar.setWidth("100%");
    Button searchBtn = new Button("Search!");
    searchBar.setSizeUndefined();
    header.addComponents(searchBar, searchBtn);
    header.setComponentAlignment(searchBar, Alignment.MIDDLE_LEFT);
    header.setComponentAlignment(searchBtn, Alignment.MIDDLE_LEFT);
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
  
  // TODO
  private ClickListener showPopup = new ClickListener() {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void buttonClick(ClickEvent event) {
      if (lastOpenedWindow != null) {
        lastOpenedWindow.close();
      }
      final Window w = new Window("Dock button clicked");
      lastOpenedWindow = w;
      
      w.addStyleName("dock-popup");
      w.setImmediate(true);
      w.setWidth("300px");
      w.setPositionX(95);
      w.setPositionY(event.getClientY() - event.getRelativeY());
      
      w.setClosable(false);
      w.setResizable(false);
      w.setDraggable(false);
      
      VerticalLayout content = new VerticalLayout();
      content.addComponent(new Image("CAPTION",
          new ExternalResource("http://placekitten.com/48/48")));
      w.setContent(content);
      
      getUI().addWindow(w);
      ((VerticalLayout) getUI().getContent()).addLayoutClickListener(new LayoutClickListener(){
        @Override
        public void layoutClick(LayoutClickEvent event) {
          w.close();
        }
        
      });
      
    }
    
  };
  
  private Button makeDockButton(String caption, String slug, AbstractOrderedLayout dock) {
    Button b = new Button(caption);
    b.addClickListener(showPopup);
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

