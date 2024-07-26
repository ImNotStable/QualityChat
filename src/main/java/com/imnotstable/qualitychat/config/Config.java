package com.imnotstable.qualitychat.config;

import com.imnotstable.qualitychat.QualityChat;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Config extends BaseConfig {
  
  private String chatFormat;
  
  public Config(@NotNull QualityChat plugin) {
    super(plugin, "config.yml");
    load();
  }
  
  public void load() {
    super.load(true);
    chatFormat = config.getString("chat-format", "<player>: <message>");
  }
  
}
