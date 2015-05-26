package net.samongi.LoreEnchantments.Interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface OnEntityArrowHitPlayer
{
	public void onEntityArrowHitPlayer(EntityDamageByEntityEvent event, String[] data);
}
