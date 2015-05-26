package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.block.BlockDamageEvent;

public interface OnBlockDamage
{
  public void onBlockDamage(BlockDamageEvent event, String[] data);
}
