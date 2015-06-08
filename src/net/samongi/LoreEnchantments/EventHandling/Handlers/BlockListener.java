package net.samongi.LoreEnchantments.EventHandling.Handlers;

import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler;
import net.samongi.LoreEnchantments.EventHandling.EnchantmentHandler.EnchantmentPackage;
import net.samongi.LoreEnchantments.Interfaces.OnBlockBreak;
import net.samongi.LoreEnchantments.Interfaces.OnBlockDamage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

/**BlockListener, handles catching all block related events.
 * 
 * @author Migsect
 *
 * Classes called:
 *  - OnBlockBreak
 *  - OnBlockDamage
 */
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
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onBlockBreak' for '" + e.getEnchantment().getName() + "'");
      ((OnBlockBreak) e.getEnchantment()).onBlockBreak(event, e.getEnchantment(), e.getData());
    }
  }
  
  @EventHandler
  public void onBlockDamage(BlockDamageEvent event)
  {
    if(event.isCancelled()) return;
    
    ItemStack item = event.getPlayer().getItemInHand();
    if(item == null) return;
    Class<?> enchantment_interface = OnBlockDamage.class;
    List<EnchantmentPackage> enchs = handler.getEnchantments(enchantment_interface, item);
    if(enchs.size() == 0) return;
    for(EnchantmentPackage e : enchs)
    {
      if(event.isCancelled()) break;
      LoreEnchantments.debugLog("[ArrowListener] Calling Method 'onBlockDamage' for '" + e.getEnchantment().getName() + "'");
      ((OnBlockDamage) e.getEnchantment()).onBlockDamage(event, e.getEnchantment(), e.getData());
    }
  }
}
