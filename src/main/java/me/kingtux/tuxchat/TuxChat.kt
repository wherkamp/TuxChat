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

  @EventHandler
  fun chatEvent(event: AsyncPlayerChatEvent) {
    var format: String = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(event.player, config.getString("ChatFormat")))

    event.format = format.replace("{message}", "%2\$s", true);
    if (event.player.hasPermission("tuxchat.color")) {
      event.message = ChatColor.translateAlternateColorCodes('&', event.message);
    }
  }
}
