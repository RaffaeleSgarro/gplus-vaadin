package com.zybnet.gplus;

import java.text.DateFormat;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.zybnet.gplus.beans.Post;

public class PostDisplay extends CustomComponent {
  
  private Post post;
  
  private static final long serialVersionUID = 8225188178933848055L;
  private static final String AUTHOR_COLUMN_WIDTH = "100px";
  
  public PostDisplay(Post post) {
    this.post = post;
    
    // Nasty trick to have right padding. Hope there's a better way
    VerticalLayout wrapper = new VerticalLayout();
    wrapper.setStyleName("post-wrapper");
    wrapper.addComponent(getPostDisplay());
    
    //setSizeFull();
    //wrapper.setSizeFull();
    
    setCompositionRoot(wrapper);
  }
  
  private Component getPostDisplay() {
    DateFormat df = DateFormat.getDateTimeInstance();
    HorizontalLayout root = new HorizontalLayout();
    root.setWidth("100%");
    
    VerticalLayout author = new VerticalLayout();
    author.addStyleName("post-author");
    Image authorPic = new Image("CAPTION", new ExternalResource(post.author.profilePictureURL));
    author.addComponent(authorPic);
    author.setWidth(AUTHOR_COLUMN_WIDTH);
    
    VerticalLayout contentWrapper = new VerticalLayout();
    contentWrapper.addStyleName("post-content");
    HorizontalLayout header = new HorizontalLayout();
    Label authorName = new Label(post.author.name);
    authorName.addStyleName("author-name");
    header.addComponent(authorName);
    header.addStyleName("post-header");
    header.setSpacing(true);
    Label date = new Label(df.format(post.date));
    date.addStyleName("post-date");
    header.addComponent(date);
    
    contentWrapper.addComponent(header);
    
    Panel p = new Panel();
    p.setWidth("100%");
    // How bad a name can be???
    HorizontalLayout contentContent = new HorizontalLayout();
    contentContent.setWidth("100%");
    p.setContent(contentContent);
    contentContent.setSpacing(true);
    contentContent.addStyleName("post-content-content");
    Image contentImg = new Image("CAPTION", new ExternalResource(post.imageURL));
    
    // BUG TODO TextArea should not be used here, I suppose...
    TextArea text = new TextArea();
    //Label text = new Label();
    text.setValue(post.text);
    text.addStyleName("post-content-text");
    text.setReadOnly(true);
    text.setSizeFull();
    
    contentContent.addComponents(contentImg, text);
    contentContent.setExpandRatio(text, 1);
    
    contentWrapper.addComponent(p);
    
    root.addComponents(author, contentWrapper);
    root.setExpandRatio(contentWrapper, 1.0f);
    
    return root;
  }
  
}
