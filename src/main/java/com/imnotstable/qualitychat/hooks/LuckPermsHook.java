package com.imnotstable.qualitychat.hooks;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsHook {
  
  @Getter
  private static LuckPerms luckPerms;
  
  public static void load() {
    RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    if (provider == null)
      throw new IllegalStateException("LuckPerms is not installed on the server.");
    luckPerms = provider.getProvider();
  }
  
}
