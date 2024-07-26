package com.imnotstable.qualitychat;

import com.imnotstable.qualitychat.hooks.LuckPermsHook;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ChatListener implements Listener {
  
  @EventHandler (priority = EventPriority.LOWEST)
  public void onChat(AsyncChatEvent event) {
    event.renderer(this::formatChat);
  }
  
  private @NotNull Component formatChat(@NotNull Player source, @NotNull Component sourceDisplayName, @NotNull Component message, @NotNull Audience viewer) {
    String rawChatFormat = QualityChat.getQualityConfig().getChatFormat();
    rawChatFormat = PlaceholderAPI.setPlaceholders(source, rawChatFormat);
    
    User user = LuckPermsHook.getLuckPerms().getUserManager().getUser(source.getUniqueId());
    
    return MiniMessage.miniMessage().deserialize(
      rawChatFormat,
      Placeholder.unparsed("group", user != null ? user.getPrimaryGroup() : ""),
      Placeholder.unparsed("prefix", user != null ? Optional.ofNullable(user.getCachedData().getMetaData().getPrefix()).orElse("") : ""),
      Placeholder.unparsed("suffix", user != null ? Optional.ofNullable(user.getCachedData().getMetaData().getSuffix()).orElse("") : ""),
      Placeholder.unparsed("player", source.getName()),
      Placeholder.component("displayname", sourceDisplayName),
      Placeholder.component("message", message)
    );
  }
  
}
