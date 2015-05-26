package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;
import net.samongi.LoreEnchantments.Interfaces.OnEntityShootBow;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    
  }
  
}
