package net.samongi.LoreEnchantments.Util;

import java.util.ArrayList;
import java.util.List;

import net.samongi.LoreEnchantments.LoreEnchantments;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class EntityUtil
{
  public static LivingEntity getLookedAtEntity(LivingEntity entity, double max_distance, double arc_length){return EntityUtil.getLookedAtEntity( entity,  0,  max_distance,  arc_length);}
  public static LivingEntity getLookedAtEntity(LivingEntity entity, double min_distance, double max_distance, double arc_length)
  {
    List<LivingEntity> nearby_entities = EntityUtil.getNearbyLivingEntities( entity, min_distance, max_distance);
    if(nearby_entities.contains(entity)) nearby_entities.remove(entity);
    while(nearby_entities.size() > 0)
    {
      LivingEntity e = getNearestEntity(entity, new ArrayList<>(nearby_entities));
      nearby_entities.remove(e);
      if(e == null) return null;
      LoreEnchantments.debugLog("Found Nearest entity: '" + e.getType() + "' with distance: " + e.getLocation().distance(entity.getLocation()));
      
      Location e_loc = e.getLocation();
      Location e_adj = new Location(e_loc.getWorld(), e_loc.getX(), e_loc.getY(), e_loc.getZ());
      double degree_sqr = Math.pow(arc_length,2) / (e.getLocation().distanceSquared(entity.getLocation()));
      double x = e_adj.getX() - entity.getLocation().getX();
      double y = e_adj.getY() - entity.getLocation().getY();
      double z = e_adj.getZ() - entity.getLocation().getZ();
      
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
  
  public static List<LivingEntity> getNearbyLivingEntities(Entity entity, double radius)
  {
    List<LivingEntity> list = new ArrayList<>();
    for(Entity e : entity.getWorld().getEntities())
    {
      if(!(e instanceof LivingEntity)) continue;
      if(e.getLocation().distanceSquared(entity.getLocation()) > radius*radius) continue;
      if(e.equals(entity)) continue;
      list.add((LivingEntity) e);
    }
    return list;
  }
  public static List<LivingEntity> getNearbyLivingEntities(Entity entity, double min_radius, double max_radius)
  {
    List<LivingEntity> list = new ArrayList<>();
    for(Entity e : entity.getWorld().getEntities())
    {
      if(!(e instanceof LivingEntity)) continue;
      if(e.getLocation().distanceSquared(entity.getLocation()) > max_radius*max_radius) continue;
      if(e.getLocation().distanceSquared(entity.getLocation()) < min_radius*min_radius) continue;
      if(e.equals(entity)) continue;
      list.add((LivingEntity) e);
    }
    return list;
  }
}
