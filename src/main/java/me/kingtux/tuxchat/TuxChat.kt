package me.kingtux.tuxchat

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

class TuxChat : JavaPlugin(), Listener {

  override fun onEnable() {
    saveDefaultConfig()
    server.pluginManager.registerEvents(this, this)

  }

  override fun onDisable() {
    // Plugin shutdown logic
  }

  @EventHandler
  fun chatEvent(event: AsyncPlayerChatEvent) {
    event.isCancelled = true;
    var format: String = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(event.player, config.getString("ChatFormat")))

    var message = format.replace("{message}", event.message, true);
    if (event.player.hasPermission("tuxchat.color")) {
      message = ChatColor.translateAlternateColorCodes('&', message);
    }
    Bukkit.getServer().broadcastMessage(message);
  }
}
