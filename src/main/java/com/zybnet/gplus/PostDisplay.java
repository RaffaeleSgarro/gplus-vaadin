package com.zybnet.gplus;

import java.text.DateFormat;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
    
    setCompositionRoot(wrapper);
  }
  
  private Component getPostDisplay() {
    DateFormat df = DateFormat.getDateTimeInstance();
    HorizontalLayout root = new HorizontalLayout();
    root.setWidth("100%");
    
    VerticalLayout author = new VerticalLayout();
    author.setStyleName("post-author");
    Image authorPic = new Image("CAPTION", new ExternalResource(post.author.profilePictureURL));
    author.addComponent(authorPic);
    author.setWidth(AUTHOR_COLUMN_WIDTH);
    
    VerticalLayout contentWrapper = new VerticalLayout();
    contentWrapper.setStyleName("post-content");
    HorizontalLayout header = new HorizontalLayout();
    Label authorName = new Label(post.author.name);
    authorName.setStyleName("author-name");
    header.addComponent(authorName);
    header.setStyleName("post-header");
    header.setSpacing(true);
    Label date = new Label(df.format(post.date));
    date.setStyleName("post-date");
    header.addComponent(date);
    
    contentWrapper.addComponent(header);
    
    // How bad a name can be???
    HorizontalLayout contentContent = new HorizontalLayout();
    contentContent.setSpacing(true);
    contentContent.setStyleName("post-content-content");
    Image contentImg = new Image("CAPTION", new ExternalResource(post.imageURL));
    
    VerticalLayout contentDesc = new VerticalLayout();
    Label text = new Label(post.text);
    contentDesc.addComponent(text);
    
    contentContent.addComponents(contentImg, contentDesc);
    contentContent.setExpandRatio(contentDesc, 1);
    
    contentWrapper.addComponents(contentContent);
    
    root.addComponents(author, contentWrapper);
    root.setExpandRatio(contentWrapper, 1.0f);
    
    return root;
  }
  
}