package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.block.BlockBreakEvent;

public interface OnBlockBreak
{
  public void onBlockBreak(BlockBreakEvent event, LoreEnchantment ench, String[] data);
}
