package net.samongi.LoreEnchantments.Util;

import java.util.List;
import java.util.Set;

import net.samongi.LoreEnchantments.LoreEnchantments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public interface Recharging
{
  public default boolean isRecharging(ItemStack item)
  {
    if(this.getRechargeTask(item) == null || this.getRechargeTask(item).getRemainingTicks() <= 0) 
    {
      this.getAllRechargingItems().remove(item);
      return false;
    }
    for(ItemStack i : this.getAllRechargingItems()) if(i.equals(item)) return true;
    return false;
  }
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
  /**Sets the item to be recharging for n ticks
   * 
   * @param item The item to set for recharging
   * @param ticks The amount of ticks to recharge for.
   */
  public default void setRecharging(ItemStack item, int ticks){this.setRecharging(item, ticks, false, 0);}
  public default void setRecharging(ItemStack item, int ticks, boolean do_durability, int durability_period)
  {
    this.getAllRechargingItems().add(item);
    RechargeLater task = new RechargeLater(this.getPlugin(), item, this, ticks, do_durability, durability_period);
    this.getAllRechargingTasks().add(task);
    task.runTaskLater(this.getPlugin(), ticks);
  }
  public default void removeRecharging(ItemStack item)
  {
    this.getAllRechargingTasks().remove(this.getRechargeTask(item));
    boolean success = this.getAllRechargingItems().remove(item);
    if(!success) LoreEnchantments.debugLog("Itemstack found to not recharge because it was not found.");
  }
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
    
    private RechargingItemUpdater item_updater;
    private boolean has_item_updater = false;
    
    public RechargeLater(JavaPlugin plugin, ItemStack to_recharge, Recharging recharging, int ticks, boolean do_durability, int durability_period)
    {
      this.to_recharge = to_recharge;
      this.recharging = recharging;
      this.ticks = ticks;
      this.start_tick = (int) (System.currentTimeMillis() / 50);
      
      // If we are doing durability, then do the durability stuff
      if(do_durability && to_recharge.getType().getMaxDurability() > 16)
      {
        LoreEnchantments.debugLog("Do_durability found true, starting item_updater task");
        this.item_updater = new RechargingItemUpdater(this);
        item_updater.runTaskTimer(plugin, 1, durability_period);
        this.has_item_updater = true;
      }
    }
    
    @Override
    public void run()
    {
      if(has_item_updater)
      {
        LoreEnchantments.debugLog("Recharge has item updater, canceling item updater");
        this.item_updater.complete();
        this.item_updater.cancel();
      }
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
    
    public int getRemainingTicks(){return (int) (this.ticks + this.start_tick - (System.currentTimeMillis() / 50));}
    public ItemStack getItem(){return this.to_recharge;}
    public int getTotalTicks(){return this.ticks;}
    
    /**Used for updating items
     */
    public class RechargingItemUpdater extends BukkitRunnable
    {
      private RechargeLater recharge_later;
      public RechargingItemUpdater(RechargeLater recharge_later)
      {
        this.recharge_later = recharge_later;
      }
      @Override
      public void run()
      {
        ItemStack item = this.recharge_later.getItem();
        short max_durability = item.getType().getMaxDurability();
        double filled_ratio = recharge_later.getRemainingTicks() / (double)recharge_later.getTotalTicks();
        short new_durability = (short) (max_durability * filled_ratio);
        if(new_durability <= 1) new_durability = 2;
        item.setDurability(new_durability);
      }
      
      public void complete()
      {
        this.recharge_later.getItem().setDurability((short) 0);;
        this.recharge_later = null;
      }
    }
  }
}
