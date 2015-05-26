package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.block.BlockBreakEvent;

public interface OnBlockBreak
{
  public void onBlockBreak(BlockBreakEvent event, String[] data);
}
