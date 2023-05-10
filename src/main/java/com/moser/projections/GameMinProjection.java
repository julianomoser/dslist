package com.moser.projections;

/**
 * @author Juliano Moser
 */
public interface GameMinProjection {

    Long getId();
    String getTitle();
    Integer getYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}
