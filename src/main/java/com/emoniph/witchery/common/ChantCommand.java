package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.util.ChatUtil;
import java.util.ArrayList;
import java.util.List;
import joptsimple.internal.Strings;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ChantCommand implements net.minecraft.command.ICommand
{
  private final List aliases;
  
  public ChantCommand()
  {
    aliases = new ArrayList();
    aliases.add(func_71517_b());
  }
  
  public String func_71517_b()
  {
    return "chant";
  }
  
  public String func_71518_a(ICommandSender icommandsender)
  {
    return func_71517_b() + " <message>";
  }
  
  public List func_71514_a()
  {
    return aliases;
  }
  
  public void func_71515_b(ICommandSender sender, String[] args)
  {
    if (args.length == 0) {
      ChatUtil.sendTranslated(EnumChatFormatting.RED, sender, "witchery.rite.unknownchant", new Object[0]);
      return;
    }
    
    World world = sender.func_130014_f_();
    if (world != null) {
      String strings = Strings.join(args, " ");
      EntityPlayer player = world.func_72924_a(sender.func_70005_c_());
      if (player != null) {
        if (ItemsRUBY_SLIPPERS.trySayTheresNoPlaceLikeHome(player, strings)) {
          return;
        }
        
        if (BlocksMIRROR.trySayMirrorMirrorSendMeHome(player, strings)) {
          return;
        }
      }
    }
    
    ChatUtil.sendTranslated(EnumChatFormatting.RED, sender, "witchery.rite.unknownchant", new Object[0]);
  }
  
  public boolean func_71519_b(ICommandSender sender)
  {
    return true;
  }
  
  public List func_71516_a(ICommandSender icommandsender, String[] astring)
  {
    return null;
  }
  
  public boolean func_82358_a(String[] astring, int i)
  {
    return false;
  }
  
  public int compareTo(Object arg0)
  {
    return 0;
  }
}
