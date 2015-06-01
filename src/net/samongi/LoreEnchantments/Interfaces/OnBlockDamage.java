package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.block.BlockDamageEvent;

public interface OnBlockDamage
{
  public void onBlockDamage(BlockDamageEvent event, LoreEnchantment ench,  String[] data);
}
