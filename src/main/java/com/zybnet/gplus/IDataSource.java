package com.zybnet.gplus;

import com.zybnet.gplus.beans.Contact;
import com.zybnet.gplus.beans.Post;

public interface IDataSource {
  Post findPostById(String id);
  Contact findContactById(String id);
}
