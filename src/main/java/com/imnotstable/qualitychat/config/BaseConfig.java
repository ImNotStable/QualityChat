package com.imnotstable.qualitychat.config;

import com.imnotstable.qualitychat.QualityChat;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BaseConfig {
  
  @Getter
  private final File file;
  private final QualityChat plugin;
  protected YamlConfiguration config;
  
  protected BaseConfig(@NotNull QualityChat plugin, @NotNull String fileName) {
    this.plugin = plugin;
    this.file = new File(plugin.getDataFolder(), fileName);
  }
  
  protected void load(boolean update) {
    if (!file.exists())
      plugin.saveResource(file.getName(), false);
    else if (update)
      update();
    config = YamlConfiguration.loadConfiguration(file);
  }
  
  protected void update() {
    boolean save = false;
    YamlConfiguration internalMessages;
    YamlConfiguration messages;
    try (InputStream inputStream = plugin.getResource(file.getName())) {
      assert inputStream != null;
      try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
        internalMessages = YamlConfiguration.loadConfiguration(inputStreamReader);
      }
    } catch (IOException exception) {
      plugin.getLogger().severe("Failed to load internal " + file.getName());
      exception.printStackTrace();
      return;
    }
    messages = YamlConfiguration.loadConfiguration(file);
    
    for (String key : internalMessages.getKeys(true))
      if (!messages.contains(key)) {
        messages.set(key, internalMessages.get(key));
        save = true;
      }
    
    for (String key : messages.getKeys(true)) {
      if (!internalMessages.contains(key)) {
        messages.set(key, null);
        save = true;
      }
      
      if (save)
        try {
          messages.save(file);
        } catch (IOException exception) {
          plugin.getLogger().severe("Failed to update " + file.getName());
          exception.printStackTrace();
        }
    }
  }
  
}
