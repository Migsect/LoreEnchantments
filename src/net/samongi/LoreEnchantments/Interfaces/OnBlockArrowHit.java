package net.samongi.LoreEnchantments.Interfaces;

import net.samongi.LoreEnchantments.EventHandling.LoreEnchantment;

import org.bukkit.event.entity.ProjectileHitEvent;

public interface OnBlockArrowHit
{
	public void onBlockArrowHit(ProjectileHitEvent event, LoreEnchantment ench, String[] data);
}
