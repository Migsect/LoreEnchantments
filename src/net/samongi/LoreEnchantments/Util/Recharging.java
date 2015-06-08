package net.samongi.LoreEnchantments.Util;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public interface Recharging
{
  public default boolean isRecharging(ItemStack item){return this.getAllRechargingItems().contains(item);}
  public default int getRemainingTicks(ItemStack item)
  {
    if(this.isRecharging(item)) return 0;
    RechargeLater task = this.getRechargeTask(item);
    
    if(task != null) return task.getRemainingTicks();
    else return 0;
  }
  public default RechargeLater getRechargeTask(ItemStack item)
  {
    for(RechargeLater t : this.getAllRechargingTasks())
    {
      if(t.getItem().equals(item)) return t;
    }
    return null;
  }
  public default void setRecharging(ItemStack item, int ticks)
  {
    this.getAllRechargingItems().add(item);
    RechargeLater task = new RechargeLater(item, this, ticks);
    this.getAllRechargingTasks().add(task);
    task.runTaskLater(this.getPlugin(), ticks);
  }
  public default void removeRecharging(ItemStack item){this.getAllRechargingItems().remove(item);}
  public default void rechargeAll()
  {
    Set<RechargeLater> tasks = this.getAllRechargingTasks();
    for(RechargeLater t : tasks) t.recharge();
  }
  public Set<RechargeLater> getAllRechargingTasks();
  public Set<ItemStack> getAllRechargingItems();
  public JavaPlugin getPlugin();
  public void onItemRecharge(Player player);

  public class RechargeLater extends BukkitRunnable
  {
    private ItemStack to_recharge;
    private Recharging recharging;
    private int start_tick;
    private int ticks;
    
    public RechargeLater(ItemStack to_recharge, Recharging recharging, int ticks)
    {
      this.to_recharge = to_recharge;
      this.recharging = recharging;
      this.ticks = ticks;
      this.start_tick = (int) (System.currentTimeMillis() / 20);
    }
    
    @Override
    public void run()
    {
      if(recharging.isRecharging(to_recharge)) recharging.removeRecharging(to_recharge);
      Player player = null;
      @SuppressWarnings("unchecked")
      List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
      for(Player p : players)
      {
        if(p.getInventory().contains(this.to_recharge)) player =p;
      }
      if(player != null) recharging.onItemRecharge(player);
    }
    
    public void recharge()
    {
      this.run();
      this.cancel();
    }
    
    public int getRemainingTicks(){return (int) (this.ticks + this.start_tick - (System.currentTimeMillis() / 20));}
    public ItemStack getItem(){return this.to_recharge;}
    public int getTotalTicks(){return this.ticks;}
  }
}
