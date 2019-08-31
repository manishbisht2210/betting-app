package com.bettingservice.model.betting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by manish.bisht on 3/8/2019.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BettingItem implements Serializable {

  private double amount;
  //Can be horse-racing, car-racing etc.
  private String sport;
  //Track,Horse,Car,Player etc.
  private String playerType;
  //Id of that player
  private String playerId;

}
