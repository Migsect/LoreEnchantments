package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnEntityShootBow;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerShootBow;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ArrowListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public ArrowListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler
  public void onEntityShootBow(EntityShootBowEvent event)
  {
    if(event.isCancelled()) return;
    
    ItemStack item = event.getBow();
    if(item == null) return;
    
    // time to copy the item stack for the bow and tag it to the arrow being launched.
    ItemStack item_copy = item.clone();
    // creating metadata for the bow used.
    FixedMetadataValue metadata = new FixedMetadataValue(handler.getPlugin(),item_copy);
    // setting the metadata.  To be retrieved later by onArrowHitEntity
    event.getProjectile().setMetadata("Bow", metadata);
    
    Class<?> enchantment_interface;
    List<LoreEnchantment> enchs;
    
    // Simple method just for shooting the bow.
    enchantment_interface = OnEntityShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnEntityShootBow) e).onEntityShootBow(event, e.seperateData(item));
    }
    
    // Case for playe shooting bow
    if(!(event.getEntity() instanceof Player)) return;
    enchantment_interface = OnPlayerShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnPlayerShootBow) e).onPlayerShootBow(event, e.seperateData(item));
    }
  }
  
  @EventHandler
  public void onEntityDamageByArrow(EntityDamageByEntityEvent event)
  {
  	if(event.isCancelled()) return;
  	
  	if(!(event.getDamager() instanceof Arrow)) return;
  	Arrow arrow = (Arrow) event.getDamager();
  	ItemStack bow = (ItemStack)event.getDamager().getMetadata("Bow").get(0).value();
  	if(bow == null) return;
  	
  	Class<?> enchantment_interface;
    List<LoreEnchantment> enchs;
  	
  	enchantment_interface = OnEntityArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnEntityArrowHitEntity) e).onEntityArrowHitEntity(event, e.seperateData(bow));
    }
    
    if(event.getEntity() instanceof Player)
    {
    	enchantment_interface = OnEntityArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() == 0) return;
      for(LoreEnchantment e : enchs)
      {
        ((OnEntityArrowHitPlayer) e).onEntityArrowHitPlayer(event, e.seperateData(bow));
      }
    }
    
    // If the shooter is a player
    if(!(arrow.getShooter() instanceof Player)) return;
    
    enchantment_interface = OnPlayerArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnPlayerArrowHitEntity) e).onPlayerArrowHitEntity(event, e.seperateData(bow));
    }
    
    // If the shooter is a player and the hit is a player
    if(event.getEntity() instanceof Player)
    {
    	enchantment_interface = OnPlayerArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() == 0) return;
      for(LoreEnchantment e : enchs)
      {
        ((OnPlayerArrowHitPlayer) e).onPlayerArrowHitPlayer(event, e.seperateData(bow));
      }
    }
  }
  
}
