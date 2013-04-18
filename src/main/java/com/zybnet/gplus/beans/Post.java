package com.zybnet.gplus.beans;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
  
  private static final long serialVersionUID = 366669789362995031L;
  
  public String id, text, imageURL;
  public Date date;
  public Contact author;
  
}
