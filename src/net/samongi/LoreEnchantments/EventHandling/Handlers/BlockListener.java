package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;
import net.samongi.LoreEnchantments.Interfaces.OnBlockBreak;
import net.samongi.LoreEnchantments.Interfaces.OnBlockDamage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener
{
  private final EnchantmentHandler handler;
  
  public BlockListener(EnchantmentHandler handler)
  {
    this.handler = handler;
  }
  
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event)
  {
    if(event.isCancelled()) return;
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnBlockBreak.class;
    List<LoreEnchantment> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnBlockBreak) e).onBlockBreak(event, e.seperateData(item));
    }
  }
  
  @EventHandler
  public void onBlockDamage(BlockDamageEvent event)
  {
    if(event.isCancelled()) return;
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnBlockDamage.class;
    List<LoreEnchantment> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(LoreEnchantment e : enchs)
    {
      ((OnBlockDamage) e).onBlockDamage(event, e.seperateData(item));
    }
  }
}