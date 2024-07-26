package com.imnotstable.qualitychat;

import com.imnotstable.qualitychat.config.Config;
import com.imnotstable.qualitychat.hooks.LuckPermsHook;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class QualityChat extends JavaPlugin {
  
  @Getter
  private static Config qualityConfig;
  
  @Override
  public void onLoad() {
    CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
  }
  
  @Override
  public void onEnable() {
    CommandAPI.onEnable();
    LuckPermsHook.load();
    qualityConfig = new Config(this);
  }
  
  @Override
  public void onDisable() {
    CommandAPI.onDisable();
  }
  
}
