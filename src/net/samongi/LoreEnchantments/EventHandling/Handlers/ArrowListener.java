package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler.EnchantmentPackage;
import net.samongi.LoreEnchantments.Interfaces.OnBlockArrowHit;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnEntityArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnEntityShootBow;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerArrowHitPlayer;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerShootBow;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
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
  public void onArrowHitBlock(ProjectileHitEvent event)
  {
    // Check to see if arrow
    if(!event.getEntityType().equals(EntityType.ARROW)) return;
    LoreEnchantments.debugLog("[ArrowListener] Heard ProjectileHitEvent");
    
    Arrow arrow = (Arrow) event.getEntity();
    LoreEnchantments.debugLog("[ArrowListener] Does the arrow haveq the 'bow' metadata?: " + arrow.hasMetadata("bow"));
    
    // Check to see if arrow is in block (complexities time)
    // Location location = arrow.getLocation();
    // Vector direction = location.getDirection();
    // getting the direction and simply displacing it in the direction the arrow is facing.
    // Location loc = new Location(location.getWorld(), location.getX() + direction.getX(), location.getY() + direction.getY(), location.getZ() + direction.getZ());
    // if(loc.getBlock().getType().equals(Material.AIR)) return;
    
    List<MetadataValue> values = event.getEntity().getMetadata("bow");
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
    List<EnchantmentPackage> enchs;
    
    // Simple method just for shooting the bow.
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityShootBow'");
    enchantment_interface = OnBlockArrowHit.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityShootBow' for '" + e.getEnchantment() + "'");
      ((OnBlockArrowHit) e.getEnchantment()).onBlockArrowHit(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler
  public void onEntityShootBow(EntityShootBowEvent event)
  {
    if(event.isCancelled()) return;
    LoreEnchantments.debugLog("[ArrowListener] Heard EntityShootBowEvent");
    
    ItemStack item = event.getBow();
    if(item == null) return;
    
    ItemStack item_new = item.clone();
    ItemStack item_ref = item;
    LoreEnchantments.debugLog("[ArrowListener] ItemStack Hash: " + item.hashCode());
    LoreEnchantments.debugLog("[ArrowListener] ItemStack 'equals' clone: " + item.equals(item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack 'similar' clone: " + item.isSimilar(item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack '==' clone: " + (item == item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack '==' ref: " + (item == item_ref));
    item.setDurability((short) (item.getDurability() - 1));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack Hash: " + item.hashCode());
    LoreEnchantments.debugLog("[ArrowListener] ItemStack 'equals' clone: " + item.equals(item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack 'similar' clone: " + item.isSimilar(item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack '==' clone: " + (item == item_new));
    LoreEnchantments.debugLog("[ArrowListener] ItemStack '==' ref: " + (item == item_ref));
    item.setDurability((short) (item.getDurability() + 1));
    
    // time to copy the item stack for the bow and tag it to the arrow being launched.
    ItemStack bow = item.clone();
    // creating metadata for the bow used.
    LoreEnchantments.debugLog("[ArrowListener] Setting 'Bow' MetaData on Arrow - Bow Name: '" + bow.getItemMeta().getDisplayName() + "', Bow Durability: " + bow.getDurability());
    FixedMetadataValue metadata = new FixedMetadataValue(handler.getPlugin(),bow);
    // setting the metadata.  To be retrieved later by onArrowHitEntity
    event.getProjectile().setMetadata("bow", metadata);
    
    Class<?> enchantment_interface;
    List<EnchantmentPackage> enchs;
    
    // Simple method just for shooting the bow.
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityShootBow'");
    enchantment_interface = OnEntityShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityShootBow' for '" + e.getEnchantment() + "'");
      ((OnEntityShootBow) e.getEnchantment()).onEntityShootBow(event, e.getEnchantment(), e.getData());
    }
    
    // Case for player shooting bow
    if(!(event.getEntity() instanceof Player)) return;
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerShootBow'");
    enchantment_interface = OnPlayerShootBow.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerShootBow' for '" + e.getEnchantment() + "'");
      ((OnPlayerShootBow) e.getEnchantment()).onPlayerShootBow(event, e.getEnchantment(), e.getData());
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
    List<EnchantmentPackage> enchs;

    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityArrowHitEntity'");
  	enchantment_interface = OnEntityArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityArrowHitEntity' for '" + e.getEnchantment().getName() + "'");
      ((OnEntityArrowHitEntity) e.getEnchantment()).onEntityArrowHitEntity(event, e.getEnchantment(), e.getData());
    }
    
    if(event.getEntity() instanceof Player)
    {
      LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnEntityArrowHitPlayer'");
    	enchantment_interface = OnEntityArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
      {
        if(event.isCancelled()) break;
        LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onEntityArrowHitPlayer' for '" + e.getEnchantment().getName() + "'");
        ((OnEntityArrowHitPlayer) e.getEnchantment()).onEntityArrowHitPlayer(event, e.getEnchantment(), e.getData());
      }
    }
    
    // If the shooter is a player
    if(!(arrow.getShooter() instanceof Player)) return;
    
    LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerArrowHitEntity'");
    enchantment_interface = OnPlayerArrowHitEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, bow);
    if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
    {
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerArrowHitEntity' for '" + e.getEnchantment().getName() + "'");
      ((OnPlayerArrowHitEntity) e.getEnchantment()).onPlayerArrowHitEntity(event, e.getEnchantment(), e.getData());
    }
    
    // If the shooter is a player and the hit is a player
    if(event.getEntity() instanceof Player)
    {
      LoreEnchantments.debugLog("[ArrowListener] Getting Enchantments for 'OnPlayerArrowHitPlayer'");
    	enchantment_interface = OnPlayerArrowHitPlayer.class;
      enchs = handler.getEnchantments(enchantment_interface, bow);
      if(enchs.size() > 0) for(EnchantmentPackage e : enchs)
      {
        if(event.isCancelled()) break;
        LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onPlayerArrowHitPlayer' for '" + e.getEnchantment().getName() + "'");
        ((OnPlayerArrowHitPlayer) e.getEnchantment()).onPlayerArrowHitPlayer(event, e.getEnchantment(), e.getData());
      }
    }
  }
  
}
