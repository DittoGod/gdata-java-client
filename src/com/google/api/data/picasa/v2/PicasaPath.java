package com.google.api.data.picasa.v2;

import com.google.api.data.client.v2.escape.CharEscapers;

/** Builder for the path part of Picasa Web Albums URI's. */
public final class PicasaPath {

  /** Default Picasa Data API server to use. Used primarily for testing. */
  public static final String DEFAULT_SERVER = "picasaweb.google.com";

  /** Type of URI: "feed", "entry", or "media". */
  public String type;

  /** Album ID. */
  public String albumId;

  /** Photo ID. */
  public String photoId;

  /** User. */
  public String user;

  /** Tag name. */
  public String tag;

  /** Server to use. By default, this is {@link #DEFAULT_SERVER}. */
  public String server = DEFAULT_SERVER;

  PicasaPath() {
  }

  /** Returns a new Picasa path for a feed. */
  public static PicasaPath feed() {
    return forType("feed");
  }

  /** Returns a new Picasa path for an entry. */
  public static PicasaPath entry() {
    return forType("entry");
  }

  /** Returns a new Picasa path for media. */
  public static PicasaPath media() {
    return forType("media");
  }

  /** Builds a path string from this path. */
  public final String build() {
    StringBuilder buf = new StringBuilder();
    buf.append("http://").append(server).append("/data/").append(type);
    buf.append("/api");
    String user = this.user;
    if (user == null) {
      buf.append("/all");
    } else {
      buf.append("/user/").append(CharEscapers.escapeUriPath(user));
      String albumId = this.albumId;
      if (albumId != null) {
        buf.append("/albumid/").append(CharEscapers.escapeUriPath(albumId));
        String photoId = this.photoId;
        if (photoId != null) {
          buf.append("/photoid/").append(CharEscapers.escapeUriPath(photoId));
          String tag = this.tag;
          if (tag != null) {
            buf.append("/tag/").append(CharEscapers.escapeUriPath(tag));
          }
        }
      }
    }
    return buf.toString();
  }

  private static PicasaPath forType(String type) {
    PicasaPath result = new PicasaPath();
    result.type = type;
    return result;
  }
}