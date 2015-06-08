package net.samongi.LoreEnchantments.Util;

import java.util.ArrayList;
import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class EntityUtil
{
  public static LivingEntity getLookedAtEntity(LivingEntity entity, double max_distance, double arc_length)
  {
    List<Entity> nearby_entities = entity.getNearbyEntities(max_distance, max_distance, max_distance);
    if(nearby_entities.contains(entity)) nearby_entities.remove(entity);
    while(nearby_entities.size() > 0)
    {
      LivingEntity e = getNearestEntity(entity, new ArrayList<>(nearby_entities));
      nearby_entities.remove(e);
      if(e == null) return null;
      LoreEnchantments.debugLog("Found Nearest entity: '" + e.getType() + "' with distance: " + e.getLocation().distance(entity.getLocation()));
      
      double degree_sqr = Math.pow(arc_length,2)/e.getLocation().distanceSquared(entity.getLocation());
      double x = e.getLocation().getX() - entity.getLocation().getX();
      double y = e.getLocation().getY() - entity.getLocation().getY();
      double z = e.getLocation().getZ() - entity.getLocation().getZ();
      
      Vector v = new Vector(x, y, z);
      double degree_comp = Math.pow(entity.getLocation().getDirection().angle(v),2);
      if(degree_comp <= degree_sqr) return e;
    }
    return null;
  }
  private static LivingEntity getNearestEntity(LivingEntity entity, List<Entity> entities)
  {
    LivingEntity closest = null;
    double closest_distance_sq = Double.MAX_VALUE;
    if(entities == null || entities.size() == 0) return null;
    while(entities.size() > 0)
    {
      Entity e = entities.get(0);
      entities.remove(e);
      
      if(!(e instanceof LivingEntity)) continue;
      
      double distance_sq = e.getLocation().distanceSquared(entity.getLocation());
      LoreEnchantments.debugLog("  Found Closest Distance Squared to be: " + distance_sq + " for '" + e.getType() + "'");
      if(distance_sq >= closest_distance_sq) continue;
      
      closest = (LivingEntity)e;
      closest_distance_sq = distance_sq;
      entities.remove(e);
    }
    return closest;
  }
}
