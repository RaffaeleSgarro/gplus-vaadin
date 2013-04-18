package com.zybnet.gplus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.zybnet.gplus.beans.Contact;
import com.zybnet.gplus.beans.Post;

public class FakeDataSource implements IDataSource {

  DateFormat df = new SimpleDateFormat("YYYY-mm-dd");
  
  @Override
  public Post findPostById(String id) {
    Post post = new Post();
    post.id = id;
    // use same id. Silly, but hey it's a fake data source...
    post.author = findContactById(id);
    try {
      post.date = df.parse("2013-01-01");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    post.text = loremIpsum(id);
    post.imageURL = postPic(id);
    return post;
  }

  private String postPic(String id) {
    return "http://placekitten.com/g/300/150";
  }

  private String loremIpsum(String id) {
    return "Lorem ipsum dolor sit amen";
  }

  @Override
  public Contact findContactById(String id) {
    Contact contact = new Contact();
    contact.id = id;
    contact.name = "Foo B. Baz";
    contact.profilePictureURL = profilePic(id);
    return contact;
  }

  private String profilePic(String id) {
    return "http://placekitten.com/48/48";
  }

}
