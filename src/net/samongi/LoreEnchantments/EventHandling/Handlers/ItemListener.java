package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler.EnchantmentPackage;
import net.samongi.LoreEnchantments.Interfaces.OnItemBreak;
import net.samongi.LoreEnchantments.Interfaces.OnItemDamage;
import net.samongi.LoreEnchantments.Interfaces.OnItemInventoryClick;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDropItem;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerItemConsume;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerItemHeld;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerPickupItem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public ItemListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler(priority=EventPriority.LOW)
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
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerDropItem(PlayerDropItemEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerDropItemEvent");
    
    ItemStack item = event.getItemDrop().getItemStack();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerDropItem.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerInteractEntity' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerDropItem) e.getEnchantment()).onPlayerDropItem(event, e.getEnchantment(), e.getData());
    }
  }

  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerPickupItem(PlayerPickupItemEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerPickupItemEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerPickupItem.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerPickupItem' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerPickupItem) e.getEnchantment()).onPlayerPickupItem(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerItemHeld(PlayerItemHeldEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerItemHeldEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerItemHeld.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onPlayerItemHeld' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerItemHeld) e.getEnchantment()).onPlayerItemHeld(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onItemDamage(PlayerItemDamageEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerItemDamageEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnItemDamage.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onItemDamage' for '" + e.getEnchantment().getName() + "'");
      ((OnItemDamage) e.getEnchantment()).onItemDamage(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onItemBreak(PlayerItemBreakEvent event)
  {
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerItemBreakEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnItemBreak.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onItemBreak' for '" + e.getEnchantment().getName() + "'");
      ((OnItemBreak) e.getEnchantment()).onItemBreak(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onItemInventoryClick(InventoryClickEvent event)
  {
    LoreEnchantments.debugLog("[ItemListener] Heard PlayerItemBreakEvent");

    ItemStack item = event.getCurrentItem();
    if(item == null) return;
    Class<?> enchantment_interface = OnItemInventoryClick.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      LoreEnchantments.debugLog("[ItemListener] Calling Method 'onItemBreak' for '" + e.getEnchantment().getName() + "'");
      ((OnItemInventoryClick) e.getEnchantment()).onItemInventoryClick(event, e.getEnchantment(), e.getData());
    }
    Player player = (Player)event.getWhoClicked();
    player.updateInventory();
  }
  
}
