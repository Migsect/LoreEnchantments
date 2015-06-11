package net.samongi.LoreEnchantments.Util;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ActionUtil
{
  public enum ActionType
  {
    LEFT_CLICK_BLOCK         (Action.LEFT_CLICK_BLOCK,  false,  false,  "Left Click Block",         "LCB"),
    LEFT_CLICK_BLOCK_SHIFT   (Action.LEFT_CLICK_BLOCK,  true,   false,  "Shift Left Click Block",   "SLCB"),
    RIGHT_CLICK_BLOCK        (Action.RIGHT_CLICK_BLOCK, false,  false,  "Right Click Block",        "RCB"),
    RIGHT_CLICK_BLOCK_SHIFT  (Action.RIGHT_CLICK_BLOCK, true,   false,  "Shift Right Click Block",  "SRCB"),
    LEFT_CLICK_AIR           (Action.LEFT_CLICK_AIR,    false,  true,   "Left Click Air",           "LCA"),
    LEFT_CLICK_AIR_SHIFT     (Action.LEFT_CLICK_AIR,    true,   true,   "Shift Left Click Air",     "SLCA"),
    RIGHT_CLICK_AIR          (Action.RIGHT_CLICK_AIR,   false,  true,   "Right Click Air",          "RCA"),
    RIGHT_CLICK_AIR_SHIFT    (Action.RIGHT_CLICK_AIR,   true,   true,   "Shift Right Click Air",    "SRCA"),
    PHYSICAL                 (Action.PHYSICAL,          false,  false,  "Physical",                 "P"),
    PHYSICAL_SHIFT           (Action.PHYSICAL,          true,   false,  "Shift Physical",           "SP"),
    OPEN_INVENTORY           (null,                     false,  false,  "Open Inventory",           "OI"),
    OPEN_INVENTORY_SHIFT     (null,                     true,   false,  "Shift Open Inventory",     "SOI"),
    DROP_ITEM                (null,                     false,  false,  "Drop Item",                "DI"),
    DROP_ITEM_SHIFT          (null,                     true,   false,  "Shift Drop Item",          "SDI");
    
    private final Action action;
    private final boolean shift;
    private final boolean air_click;
    private final String friendly_name;
    private final String short_hand;
    
    ActionType(Action action, boolean shift, boolean air_click, String friendly_name, String short_hand)
    {
      this.action = action;
      this.shift = shift;
      this.air_click = air_click;
      this.friendly_name = friendly_name;
      this.short_hand = short_hand;
    }
    public Action getBukkitAction(){return this.action;}
    public boolean isShiftAction(){return this.shift;}
    public boolean isAirClick(){return this.air_click;}
    public boolean isBlockClick(){return !this.air_click;}
    public boolean isLeftClick(){return this.action == Action.LEFT_CLICK_AIR || this.action == Action.LEFT_CLICK_BLOCK;}
    public boolean isRightClick(){return this.action == Action.RIGHT_CLICK_AIR || this.action == Action.RIGHT_CLICK_BLOCK;}
    public String getFriendlyName(){return this.friendly_name;}
    public String getShortHandName(){return this.short_hand;}
    
    public static ActionType getActionType(String short_hand)
    {
      switch(short_hand)
      {
        case "LCB":   return ActionType.LEFT_CLICK_BLOCK;
        case "SLCB":   return ActionType.LEFT_CLICK_BLOCK_SHIFT;
        case "RCB":   return ActionType.RIGHT_CLICK_BLOCK;
        case "SRCB":   return ActionType.RIGHT_CLICK_BLOCK_SHIFT;
        case "LCA":   return ActionType.LEFT_CLICK_AIR;
        case "SLCA":   return ActionType.LEFT_CLICK_AIR_SHIFT;
        case "RCA":   return ActionType.RIGHT_CLICK_AIR;
        case "SRCA":   return ActionType.RIGHT_CLICK_AIR_SHIFT;
        case "P":   return ActionType.PHYSICAL;
        case "SP":   return ActionType.PHYSICAL_SHIFT;
        case "OI":   return ActionType.OPEN_INVENTORY;
        case "SOI":   return ActionType.OPEN_INVENTORY_SHIFT;
        case "DI":   return ActionType.DROP_ITEM;
        case "SDI":   return ActionType.DROP_ITEM_SHIFT;
      }
      return null;
    }
    
    public static ActionType getActionType(PlayerInteractEvent event)
    {
      Action action = event.getAction();
      boolean shift = event.getPlayer().isSneaking();
      switch(action)
      {
        case LEFT_CLICK_BLOCK:
          if(shift) return ActionType.LEFT_CLICK_BLOCK_SHIFT;
          else return ActionType.LEFT_CLICK_BLOCK;
        case RIGHT_CLICK_BLOCK:
          if(shift) return ActionType.RIGHT_CLICK_BLOCK_SHIFT;
          else return ActionType.RIGHT_CLICK_BLOCK;
        case LEFT_CLICK_AIR:
          if(shift) return ActionType.LEFT_CLICK_AIR_SHIFT;
          else return ActionType.LEFT_CLICK_AIR;
        case RIGHT_CLICK_AIR:
          if(shift) return ActionType.RIGHT_CLICK_AIR_SHIFT;
          else return ActionType.RIGHT_CLICK_AIR;
        case PHYSICAL:
          if(shift) return ActionType.PHYSICAL_SHIFT;
          else return ActionType.PHYSICAL;
      }
      return null;
    }
    public static ActionType getActionType(Action action)
    {
      switch(action)
      {
        case LEFT_CLICK_BLOCK:
          return ActionType.LEFT_CLICK_BLOCK;
        case RIGHT_CLICK_BLOCK:
          return ActionType.RIGHT_CLICK_BLOCK;
        case LEFT_CLICK_AIR:
          return ActionType.LEFT_CLICK_AIR;
        case RIGHT_CLICK_AIR:
          return ActionType.RIGHT_CLICK_AIR;
        case PHYSICAL:
          return ActionType.PHYSICAL;
      }
      return null;
    }
  }
}
