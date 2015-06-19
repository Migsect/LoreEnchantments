package net.samongi.LoreEnchantments.Util;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ActionUtil
{
  public enum ActionType
  {
    LEFT_CLICK              (null,                     false,  "Left Click",               "LC"),
    SHIFT_LEFT_CLICK        (null,                     true,   "Shift Left Click",         "SLC"),
    RIGHT_CLICK             (null,                     false,  "Right Click",              "RC"),
    SHIFT_RIGHT_CLICK       (null,                     true,   "Shift Right Click",        "SRC"),
    LEFT_CLICK_BLOCK        (Action.LEFT_CLICK_BLOCK,  false,  "Left Click Block",         "LCB"),
    SHIFT_LEFT_CLICK_BLOCK  (Action.LEFT_CLICK_BLOCK,  true,   "Shift Left Click Block",   "SLCB"),
    RIGHT_CLICK_BLOCK       (Action.RIGHT_CLICK_BLOCK, false,  "Right Click Block",        "RCB"),
    SHIFT_RIGHT_CLICK_BLOCK (Action.RIGHT_CLICK_BLOCK, true,   "Shift Right Click Block",  "SRCB"),
    LEFT_CLICK_AIR          (Action.LEFT_CLICK_AIR,    false,  "Left Click Air",           "LCA"),
    SHIFT_LEFT_CLICK_AIR    (Action.LEFT_CLICK_AIR,    true,   "Shift Left Click Air",     "SLCA"),
    RIGHT_CLICK_AIR         (Action.RIGHT_CLICK_AIR,   false,  "Right Click Air",          "RCA"),
    SHIFT_RIGHT_CLICK_AIR   (Action.RIGHT_CLICK_AIR,   true,   "Shift Right Click Air",    "SRCA"),
    PHYSICAL                (Action.PHYSICAL,          false,  "Physical",                 "P"),
    SHIFT_PHYSICAL          (Action.PHYSICAL,          true,   "Shift Physical",           "SP"),
    OPEN_INVENTORY          (null,                     false,  "Open Inventory",           "OI"),
    SHIFT_OPEN_INVENTORY    (null,                     true,   "Shift Open Inventory",     "SOI"),
    DROP_ITEM               (null,                     false,  "Drop Item",                "DI"),
    SHIFT_DROP_ITEM         (null,                     true,   "Shift Drop Item",          "SDI"),
    SHOOT_BOW               (null,                     false,  "Shoot Bow",                "SB"),
    SHOOT_BOW_ALL           (null,                     false,  "Shoot Bow All",            "SBA"),
    SHIFT_SHOOT_BOW         (null,                     true,   "Shift Shoot Bow",          "SSB"),
    ATTACK                  (null,                     false,  "Attack",                   "A"),
    ATTACK_ALL              (null,                     true,   "Attack All",               "AA"),
    SHIFT_ATTACK            (null,                     true,   "Shift Attack",             "SA"),
    CONSUME                 (null,                     false,  "Consume",                  "C"),
    CONSUME_ALL             (null,                     true,   "Consume All",              "CA"),
    SHIFT_CONSUME           (null,                     true,   "Shift Consume",            "SC"),
    BLOCK_BREAK             (null,                     false,  "Block Break",              "BB"),
    BLOCK_BREAK_ALL         (null,                     false,  "Block Break All",          "BBA"),
    SHIFT_BLOCK_BREAK       (null,                     true,   "Shift Block Break",        "SBB");
    
    
    private final Action action;
    private final boolean shift;
    private final String friendly_name;
    private final String short_hand;
    
    ActionType(Action action, boolean shift, String friendly_name, String short_hand)
    {
      this.action = action;
      this.shift = shift;
      this.friendly_name = friendly_name;
      this.short_hand = short_hand;
    }
    public Action getBukkitAction(){return this.action;}
    public boolean isShiftAction(){return this.shift;}
    public boolean isAirClick(){return this.action.equals(Action.LEFT_CLICK_AIR) || this.action.equals(Action.RIGHT_CLICK_AIR);}
    public boolean isBlockClick(){return !this.isAirClick();}
    public boolean isLeftClick(){return this.action == Action.LEFT_CLICK_AIR || this.action == Action.LEFT_CLICK_BLOCK  || this.equals(ActionType.LEFT_CLICK) || this.equals(ActionType.SHIFT_LEFT_CLICK);}
    public boolean isRightClick(){return this.action == Action.RIGHT_CLICK_AIR || this.action == Action.RIGHT_CLICK_BLOCK || this.equals(ActionType.RIGHT_CLICK) || this.equals(ActionType.SHIFT_RIGHT_CLICK);}
    public String getFriendlyName(){return this.friendly_name;}
    public String getShortHandName(){return this.short_hand;}
    
    public boolean isSimilar(ActionType type)
    {
      if(this.equals(ActionType.LEFT_CLICK))
      {
        if(type.equals(LEFT_CLICK_BLOCK)) return true;
        if(type.equals(LEFT_CLICK_AIR)) return true;
      }
      if(this.equals(ActionType.RIGHT_CLICK))
      {
        if(type.equals(RIGHT_CLICK_BLOCK)) return true;
        if(type.equals(RIGHT_CLICK_AIR)) return true;
      }
      if(this.equals(ActionType.SHIFT_LEFT_CLICK))
      {
        if(type.equals(SHIFT_LEFT_CLICK_BLOCK)) return true;
        if(type.equals(SHIFT_LEFT_CLICK_AIR)) return true;
      }
      if(this.equals(ActionType.SHIFT_RIGHT_CLICK))
      {
        if(type.equals(SHIFT_RIGHT_CLICK_BLOCK)) return true;
        if(type.equals(SHIFT_RIGHT_CLICK_AIR)) return true;
      }
      if(this.equals(ActionType.ATTACK_ALL))
      {
        if(type.equals(ATTACK)) return true;
        if(type.equals(SHIFT_ATTACK)) return true;
      }
      if(this.equals(ActionType.CONSUME_ALL))
      {
        if(type.equals(CONSUME)) return true;
        if(type.equals(SHIFT_CONSUME)) return true;
      }
      if(this.equals(ActionType.SHOOT_BOW_ALL))
      {
        if(type.equals(SHOOT_BOW)) return true;
        if(type.equals(SHIFT_SHOOT_BOW)) return true;
      }
      if(this.equals(ActionType.BLOCK_BREAK_ALL))
      {
        if(type.equals(BLOCK_BREAK)) return true;
        if(type.equals(SHIFT_BLOCK_BREAK)) return true;
      }
      return type.equals(this);
    }
    
    public static ActionType getActionType(String short_hand)
    {
      switch(short_hand)
      {
        case "LC":    return ActionType.LEFT_CLICK;
        case "SLC":   return ActionType.SHIFT_LEFT_CLICK;
        case "RC":    return ActionType.RIGHT_CLICK;
        case "SRC":   return ActionType.SHIFT_RIGHT_CLICK;
        case "LCB":   return ActionType.LEFT_CLICK_BLOCK;
        case "SLCB":  return ActionType.SHIFT_LEFT_CLICK_BLOCK;
        case "RCB":   return ActionType.RIGHT_CLICK_BLOCK;
        case "SRCB":  return ActionType.SHIFT_RIGHT_CLICK_BLOCK;
        case "LCA":   return ActionType.LEFT_CLICK_AIR;
        case "SLCA":  return ActionType.SHIFT_LEFT_CLICK_AIR;
        case "RCA":   return ActionType.RIGHT_CLICK_AIR;
        case "SRCA":  return ActionType.SHIFT_RIGHT_CLICK_AIR;
        case "P":     return ActionType.PHYSICAL;
        case "SP":    return ActionType.SHIFT_PHYSICAL;
        case "OI":    return ActionType.OPEN_INVENTORY;
        case "SOI":   return ActionType.SHIFT_OPEN_INVENTORY;
        case "DI":    return ActionType.DROP_ITEM;
        case "SDI":   return ActionType.SHIFT_DROP_ITEM;
        case "SB":    return ActionType.SHOOT_BOW;
        case "SBA":   return ActionType.SHOOT_BOW;
        case "SSB":   return ActionType.SHIFT_SHOOT_BOW;
        case "A":     return ActionType.ATTACK;
        case "AA":    return ActionType.ATTACK_ALL;
        case "SA":    return ActionType.SHIFT_ATTACK;
        case "C":     return ActionType.CONSUME;
        case "CA":    return ActionType.CONSUME_ALL;
        case "SC":    return ActionType.SHIFT_CONSUME;
        case "BB":    return ActionType.BLOCK_BREAK;
        case "BBA":   return ActionType.BLOCK_BREAK_ALL;
        case "SBB":   return ActionType.SHIFT_BLOCK_BREAK;
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
          if(shift) return ActionType.SHIFT_LEFT_CLICK_BLOCK;
          else return ActionType.LEFT_CLICK_BLOCK;
        case RIGHT_CLICK_BLOCK:
          if(shift) return ActionType.SHIFT_RIGHT_CLICK_BLOCK;
          else return ActionType.RIGHT_CLICK_BLOCK;
        case LEFT_CLICK_AIR:
          if(shift) return ActionType.SHIFT_LEFT_CLICK_AIR;
          else return ActionType.LEFT_CLICK_AIR;
        case RIGHT_CLICK_AIR:
          if(shift) return ActionType.SHIFT_RIGHT_CLICK_AIR;
          else return ActionType.RIGHT_CLICK_AIR;
        case PHYSICAL:
          if(shift) return ActionType.SHIFT_PHYSICAL;
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
