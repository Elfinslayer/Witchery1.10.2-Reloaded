package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.item.ItemGeneral;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PacketItemUpdate implements IMessage
{
  private int slot;
  private int damage;
  private int page;
  
  public PacketItemUpdate() {}
  
  public PacketItemUpdate(int slot, int page, ItemStack stack)
  {
    this.slot = slot;
    damage = stack.func_77960_j();
    this.page = page;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(slot);
    buffer.writeInt(damage);
    buffer.writeInt(page);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    slot = buffer.readInt();
    damage = buffer.readInt();
    page = buffer.readInt();
  }
  
  public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketItemUpdate, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketItemUpdate message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      if ((slot >= 0) && (slot < field_71071_by.func_70302_i_())) {
        ItemStack stack = field_71071_by.func_70301_a(slot);
        if ((stack != null) && (stack.func_77960_j() == damage) && (page >= 0) && (page < 1000))
        {
          if (ItemsGENERIC.isBook(stack)) {
            if (!stack.func_77942_o()) {
              stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74768_a("CurrentPage", page);
          } else if (stack.func_77973_b() == ItemsBIOME_BOOK) {
            ItemBook.setSelectedBiome(stack, page);
          }
        }
      }
      return null;
    }
  }
}
