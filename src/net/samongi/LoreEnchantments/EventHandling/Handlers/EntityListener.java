package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;
import net.samongi.LoreEnchantments.Interfaces.OnEntityDamageEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDamageEntity;
import net.samongi.LoreEnchantments.Interfaces.OnPlayerDamagePlayer;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public EntityListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler
  public void onLivingEntityDamageEntity(EntityDamageByEntityEvent event)
  {
    if(event.isCancelled()) return;
    // Only Living Entities can have items.
    //   Arrows will be handled in another method.
    if(!(event.getDamager() instanceof LivingEntity)) return;
    LivingEntity damager = (LivingEntity) event.getDamager();
    
    ItemStack item = damager.getEquipment().getItemInHand();
    if(item == null) return;
    
    // Setting up reused variables
    Class<?> enchantment_interface;
    List<LoreEnchantment> enchs;
    
    // Generic case of entity vs entity
    enchantment_interface = OnEntityDamageEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnEntityDamageEntity) e).onEntityDamageEntity(event, e.seperateData(item));
    }
    
    // Case of player vs entity
    if(!(damager instanceof Player)) return;
    enchantment_interface = OnPlayerDamageEntity.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnEntityDamageEntity) e).onEntityDamageEntity(event, e.seperateData(item));
    }
    
    // Case of player vs player
    if(!(event.getEntity() instanceof Player)) return;
    enchantment_interface = OnPlayerDamagePlayer.class;
    enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnEntityDamageEntity) e).onEntityDamageEntity(event, e.seperateData(item));
    }
  }
}
