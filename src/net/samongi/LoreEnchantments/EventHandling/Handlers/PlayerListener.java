package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler.EnchantmentPackage;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteract;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerInteractEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerOpenInventory;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerToggleFlight;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerToggleSneak;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerToggleSprint;

public class PlayerListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public PlayerListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerOpenInventory(InventoryOpenEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard InventoryOpenEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerOpenInventory.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerOpenInventory' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerOpenInventory) e.getEnchantment()).onPlayerOpenInventory(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerInteract(PlayerInteractEvent event)
  {
    if(event.isCancelled() && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR))) event.setCancelled(false);;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerInteractEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerInteract.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerInteract' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerInteract) e.getEnchantment()).onPlayerInteract(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerInteractEntityEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerInteractEntity.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerInteractEntity' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerInteractEntity) e.getEnchantment()).onPlayerInteractEntity(event, e.getEnchantment(), e.getData());
    }
  }
  
  /*
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerMovement(PlayerMoveEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerMoveEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerMove.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerMove' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerMove) e.getEnchantment()).onPlayerMove(event, e.getEnchantment(), e.getData());
    }
  }
  */
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerSprint(PlayerToggleSprintEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerToggleSprintEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerToggleSprint.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerToggleSprint' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerToggleSprint) e.getEnchantment()).onPlayerToggleSprint(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerSneak(PlayerToggleSneakEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerToggleSneakEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerToggleSneak.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerToggleSneak' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerToggleSneak) e.getEnchantment()).onPlayerToggleSneak(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler(priority=EventPriority.LOW)
  public void onPlayerFlight(PlayerToggleFlightEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[PlayerListener] Heard PlayerToggleFlightEvent");
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnPlayerToggleFlight.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[PlayerListener] Calling Method 'onPlayerToggleFlight' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerToggleFlight) e.getEnchantment()).onPlayerToggleFlight(event, e.getEnchantment(), e.getData());
    }
  }
}
