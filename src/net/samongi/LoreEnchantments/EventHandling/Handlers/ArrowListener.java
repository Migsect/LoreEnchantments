package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnEntityShootBow;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerShootBow;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**ArrowListener, handles catching all arrow related events.
 * 
 * @author Migsect
 *
 * Classes called:
 *  - OnEntityShootBow
 *  - OnPlayerShootBow
 *  - OnEntityArrowHitEntity
 *  - OnEntityArrowHitPlayer
 *  - OnPlayerArrowHitEntity
 *  - OnPlayerArrowHitPlayer
 */
public class ArrowListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public ArrowListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler
  public void onPlayerPickupArrow(PlayerPickupItemEvent event)
  {
    if(event.isCancelled()) return;
    
    if(!event.getItem().getItemStack().getType().equals(Material.ARROW)) return;
    // ghost is used to 
    for(MetadataValue m :event.getItem().getMetadata("ghost")) if(m.asBoolean()) event.setCancelled(true);
  }
  
  @EventHandler
  public void onEntityShootBow(EntityShootBowEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ArrowListener] Heard EntityShootBowEvent");
    
    ItemStack item = event.getBow();
    if(item == null) return;
    
    // time to copy the item stack for the bow and tag it to the arrow being launched.
    ItemStack bow = item.clone();
    // creating metadata for the bow used.
    LoreEnchantments.debugLog("[ArrowListener] Setting 'Bow' MetaData on Arrow - Bow Name: '" + bow.getItemMeta().getDisplayName() + "', Bow Durability: " + bow.getDurability());
    FixedMetadataValue metadata = new FixedMetadataValue(handler.getPlugin(),bow);
    // setting the metadata.  To be retrieved later by onArrowHitEntity
    event.getProjectile().setMetadata("bow", metadata);
    
    Class<?> enchantment_interface;
    List<LoreEnchantment> enchs;
    
    // Simple method just for shooting the bow.
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityShootBow'");
    enchantment_interface = OnEntityShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() > 0) for(LoreEnchantment e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityShootBow' for '" + e.getName() + "'");
      String[] data = e.seperateData(item);
      ((OnEntityShootBow) e).onEntityShootBow(event, data);
    }
    
    // Case for player shooting bow
    if(!(event.getEntity() instanceof Player)) return;
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerShootBow'");
    enchantment_interface = OnPlayerShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() > 0) for(LoreEnchantment e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerShootBow' for '" + e.getName() + "'");
      String[] data = e.seperateData(item);
      ((OnPlayerShootBow) e).onPlayerShootBow(event, data);
    }
  }
  
  @EventHandler
  public void onEntityDamageByArrow(EntityDamageByEntityEvent event)
  {
  	if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ArrowListener] Heard EntityDamageByEntityEvent");
  	
  	if(!(event.getDamager() instanceof Arrow)) return;
  	Arrow arrow = (Arrow) event.getDamager();
  	LoreEnchantments.debugLog("[ArrowListener] Does the arrow have the 'bow' metadata?: " + arrow.hasMetadata("bow"));
  	
  	List<MetadataValue> values = event.getDamager().getMetadata("bow");
  	LoreEnchantments.debugLog("[ArrowListener] Found " + values.size() + " MetadataValues");
  	ItemStack bow = null;
  	for(MetadataValue v : values)
  	{
  	  if(!(v.value() instanceof ItemStack)) continue;
  	  bow = (ItemStack)v.value();
  	  break;
  	}
  	if(bow == null) LoreEnchantments.debugLog("[ArrowListener] Found no bow MetaTag, null returning.");
  	if(bow == null) return;
    LoreEnchantments.debugLog("[ArrowListener] Found 'Bow' MetaData on Arrow - Bow Name: '" + bow.getItemMeta().getDisplayName() + "', Bow Durability: " + bow.getDurability());
  	
  	Class<?> enchantment_interface;
    List<LoreEnchantment> enchs;

    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityArrowHitEntity'");
  	enchantment_interface = OnEntityArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() > 0) for(LoreEnchantment e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityArrowHitEntity' for '" + e.getName() + "'");
      String[] data = e.seperateData(bow);
      ((OnEntityArrowHitEntity) e).onEntityArrowHitEntity(event, data);
    }
    
    if(event.getEntity() instanceof Player)
    {
      LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityArrowHitPlayer'");
    	enchantment_interface = OnEntityArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() > 0) for(LoreEnchantment e : enchs)
      {
        LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityArrowHitPlayer' for '" + e.getName() + "'");
        String[] data = e.seperateData(bow);
        ((OnEntityArrowHitPlayer) e).onEntityArrowHitPlayer(event, data);
      }
    }
    
    // If the shooter is a player
    if(!(arrow.getShooter() instanceof Player)) return;
    
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerArrowHitEntity'");
    enchantment_interface = OnPlayerArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() > 0) for(LoreEnchantment e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerArrowHitEntity' for '" + e.getName() + "'");
      String[] data = e.seperateData(bow);
      ((OnPlayerArrowHitEntity) e).onPlayerArrowHitEntity(event, data);
    }
    
    // If the shooter is a player and the hit is a player
    if(event.getEntity() instanceof Player)
    {
      LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerArrowHitPlayer'");
    	enchantment_interface = OnPlayerArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() > 0) for(LoreEnchantment e : enchs)
      {
        LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerArrowHitPlayer' for '" + e.getName() + "'");
        String[] data = e.seperateData(bow);
        ((OnPlayerArrowHitPlayer) e).onPlayerArrowHitPlayer(event, data);
      }
    }
  }
  
}
