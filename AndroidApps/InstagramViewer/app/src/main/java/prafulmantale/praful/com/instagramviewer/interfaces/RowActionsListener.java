package prafulmantale.praful.com.instagramviewer.interfaces;

import prafulmantale.praful.com.instagramviewer.enums.RequesterTypes;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

/**
 * Created by prafulmantale on 9/17/14.
 */
public interface RowActionsListener {

  public void OnCommentsListRequested(MediaDetails mediaDetails, RequesterTypes requesterTypes);

  public void OnUserDetailsRequested(MediaDetails mediaDetails);

}
