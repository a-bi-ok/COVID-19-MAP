package edu.bu.met.cs665.domain;

/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */

public abstract class Table implements MapFeature {

  private String country;

  private long lon;

  private long lat;

  private Integer cases;

  private Integer deaths;


  /**
   * Constructor takes no parameters.
   * @constructor 
   *
   */

  public String getCountry() {
    return country;
  }

  /**
   * Getter for Longitudes.
   *
   */

  public long getLon() {
    return lon;
  }

  /**
   * Getter for Latitudes.
   *
   */

  public long getLat() {
    return lat;
  }


  /**
   * Getter for Cases Variable.
   *
   */
  public Integer getCases() {
    return cases;
  }


  /**
   * Getter for Deaths Variable.
   *
   */
  public Integer getDeaths() {
    return deaths;
  }


  /**
   * Setter for Country Variable.
   *
   */

  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Setter for Longitude Variable.
   *
   */

  public void setLon(long lon) {
    this.lon = lon;
  }

  /**
   * Setter for Latitude Variable.
   *
   */

  public void setLat(long lat) {
    this.lat = lat;
  }


  /**
   * Setter for Cases Variable.
   *
   */
  public void setCases(Integer cases) {
    this.cases = cases;
  }


  /**
   * Setter for Deaths Variable.
   *
   */
  public void setDeaths(Integer deaths) {
    this.deaths = deaths;
  }



}
