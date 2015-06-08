package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler.EnchantmentPackage;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteract;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteractEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerItemConsume;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public ItemListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler
  public void onItemConsume(PlayerItemConsumeEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerItemConsumeEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerItemConsume.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerItemConsume' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerItemConsume) e.getEnchantment()).onPlayerItemConsume(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event)
  {
    if(event.isCancelled() && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR))) event.setCancelled(false);;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerInteractEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerInteract.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerInteract' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerInteract) e.getEnchantment()).onPlayerInteract(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerInteractEntityEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerInteractEntity.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerInteractEntity' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerInteractEntity) e.getEnchantment()).onPlayerInteractEntity(event, e.getEnchantment(), e.getData());
    }
  }
}
