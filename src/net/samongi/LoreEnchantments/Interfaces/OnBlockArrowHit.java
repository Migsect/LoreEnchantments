package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.ProjectileHitEvent;

public interface OnBlockArrowHit
{
	public void onBlockArrowHit(ProjectileHitEvent event, String[] data);
}
