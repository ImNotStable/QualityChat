package com.imnotstable.qualitychat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ChatPlayer {
  
  public ChatPlayer of(UUID uniqueId) {
    return new ChatPlayer(uniqueId);
  }
  
  private final UUID uniqueId;
  
  private ChatPlayer(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
  
}
