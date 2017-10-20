package com.emoniph.witchery.item;

import net.minecraft.item.ItemStack;

public class ItemCircleTalisman extends ItemBase
{
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item0;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item1;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item2;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item3;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item4;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item5;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item6;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item7;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item8;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  net.minecraft.util.IIcon item9;
  
  public ItemCircleTalisman() {
    func_77625_d(16);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
  {
    return net.minecraft.item.EnumRarity.uncommon;
  }
  
  public String func_77667_c(ItemStack itemStack)
  {
    Integer damage = Integer.valueOf(itemStack.func_77960_j());
    return func_77658_a() + "." + damage.toString();
  }
  
  public String func_77657_g(ItemStack par1ItemStack)
  {
    return super.func_77667_c(par1ItemStack);
  }
  
  public String func_77653_i(ItemStack itemStack)
  {
    String localizedName = super.func_77653_i(itemStack);
    
    int damage = itemStack.func_77960_j();
    return damage > 0 ? String.format("%s (%s)", new Object[] { localizedName, getChalkDisplayName(damage) }) : localizedName;
  }
  
  private String getChalkDisplayName(int damage) {
    int small = damage & 0x7;
    int medium = damage >>> 3 & 0x7;
    int large = damage >>> 6 & 0x7;
    
    StringBuilder result = new StringBuilder();
    
    if (small > 0) {
      result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.small." + Integer.valueOf(small).toString()));
      result.append(", ");
    }
    
    if (medium > 0) {
      result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.medium." + Integer.valueOf(medium).toString()));
      result.append(", ");
    }
    
    if (large > 0) {
      result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.large." + Integer.valueOf(large).toString()));
      result.append(", ");
    }
    if (result.length() > 0) {
      result.setLength(result.length() - 2);
    }
    return result.toString();
  }
  





















  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_94581_a(net.minecraft.client.renderer.texture.IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    item0 = field_77791_bV;
    item1 = iconRegister.func_94245_a(func_111208_A() + ".1");
    item2 = iconRegister.func_94245_a(func_111208_A() + ".2");
    item3 = iconRegister.func_94245_a(func_111208_A() + ".3");
    item4 = iconRegister.func_94245_a(func_111208_A() + ".4");
    item5 = iconRegister.func_94245_a(func_111208_A() + ".5");
    item6 = iconRegister.func_94245_a(func_111208_A() + ".6");
    item7 = iconRegister.func_94245_a(func_111208_A() + ".7");
    item8 = iconRegister.func_94245_a(func_111208_A() + ".8");
    item9 = iconRegister.func_94245_a(func_111208_A() + ".9");
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public net.minecraft.util.IIcon func_77617_a(int damage)
  {
    int small = damage & 0x7;
    int medium = damage >>> 3 & 0x7;
    int large = damage >>> 6 & 0x7;
    
    switch (medium > 0 ? medium + 3 : large > 0 ? large + 6 : small) {
    case 0: 
    default: 
      return item0;
    case 1: 
      return item1;
    case 2: 
      return item2;
    case 3: 
      return item3;
    case 4: 
      return item4;
    case 5: 
      return item5;
    case 6: 
      return item6;
    case 7: 
      return item7;
    case 8: 
      return item8;
    }
    return item9;
  }
  

  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_150895_a(net.minecraft.item.Item itemID, net.minecraft.creativetab.CreativeTabs tab, java.util.List itemList)
  {
    itemList.add(new ItemStack(itemID, 1, 0));
    itemList.add(new ItemStack(itemID, 1, 1));
    itemList.add(new ItemStack(itemID, 1, 2));
    itemList.add(new ItemStack(itemID, 1, 3));
    itemList.add(new ItemStack(itemID, 1, 8));
    itemList.add(new ItemStack(itemID, 1, 16));
    itemList.add(new ItemStack(itemID, 1, 24));
    itemList.add(new ItemStack(itemID, 1, 64));
    itemList.add(new ItemStack(itemID, 1, 128));
    itemList.add(new ItemStack(itemID, 1, 192));
  }
  
  public boolean func_77648_a(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, net.minecraft.world.World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
  {
    if (((com.emoniph.witchery.util.BlockSide.TOP.isEqual(side)) && (world.func_147439_a(posX, posY, posZ) == BlocksCIRCLE)) || (BlocksCIRCLE.func_149718_j(world, posX, posY + 1, posZ))) {
      int damage = itemstack.func_77960_j();
      if (damage > 0) {
        if (!field_72995_K) {
          int a = damage & 0x7;
          int b = damage >>> 3 & 0x7;
          int c = damage >>> 6 & 0x7;
          int _ = 0;
          
          int[][] PATTERN = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0 }, { 0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0 }, { 0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0 }, { 0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0 }, { 0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 4, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0 }, { 0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0 }, { 0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0 }, { 0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0 }, { 0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
          

















          int y = world.func_147439_a(posX, posY, posZ) == BlocksCIRCLE ? posY : posY + 1;
          
          int pass = 0;
          boolean fail = false;
          while ((pass < 2) && (!fail)) {
            int offsetZ = (PATTERN.length - 1) / 2;
            for (int z = 0; z < PATTERN.length - 1; z++) {
              int worldZ = posZ - offsetZ + z;
              int offsetX = (PATTERN[z].length - 1) / 2;
              for (int x = 0; x < PATTERN[z].length; x++) {
                int worldX = posX - offsetX + x;
                int item = PATTERN[(PATTERN.length - 1 - z)][x];
                net.minecraft.block.material.Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
                boolean solidBlock = (material != null) && ((material.func_76218_k()) || (material.func_76220_a()));
                switch (item) {
                case 1: 
                  if ((!solidBlock) && (BlocksGLYPH_RITUAL.func_149718_j(world, worldX, y, worldZ))) {
                    if (pass == 1) {
                      world.func_147465_d(worldX, y, worldZ, BlocksGLYPH_RITUAL, field_73012_v.nextInt(12), 3);
                    }
                  } else {
                    fail = true;
                  }
                  break;
                case 2: 
                  if ((!solidBlock) && (BlocksGLYPH_OTHERWHERE.func_149718_j(world, worldX, y, worldZ))) {
                    if (pass == 1) {
                      world.func_147465_d(worldX, y, worldZ, BlocksGLYPH_OTHERWHERE, field_73012_v.nextInt(12), 3);
                    }
                  } else {
                    fail = true;
                  }
                  break;
                case 3: 
                  if ((!solidBlock) && (BlocksGLYPH_INFERNAL.func_149718_j(world, worldX, y, worldZ))) {
                    if (pass == 1) {
                      world.func_147465_d(worldX, y, worldZ, BlocksGLYPH_INFERNAL, field_73012_v.nextInt(12), 3);
                    }
                  } else {
                    fail = true;
                  }
                  break;
                case 4: 
                  if (y != posY) {
                    if (BlocksCIRCLE.func_149718_j(world, worldX, y, worldZ)) {
                      if (pass == 1) {
                        world.func_147449_b(worldX, y, worldZ, BlocksCIRCLE);
                      }
                    } else {
                      fail = true;
                    }
                  }
                  
                  break;
                }
                
                
                if (pass == 1) {
                  com.emoniph.witchery.util.ParticleEffect.SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, world, worldX, posY + 1, worldZ, 0.5D, 1.0D, 16);
                }
              }
            }
            


            pass++;
          }
          if (fail) {
            world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)field_73012_v.nextDouble() * 0.4F + 0.8F));
          }
          else if (field_77994_a > 1) {
            ItemStack newStack = new ItemStack(this);
            if (!field_71071_by.func_70441_a(newStack)) {
              world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
            } else if ((player instanceof net.minecraft.entity.player.EntityPlayerMP)) {
              ((net.minecraft.entity.player.EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
            field_77994_a -= 1;
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
            }
          } else {
            itemstack.func_77964_b(0);
          }
        }
        else {
          world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)field_73012_v.nextDouble() * 0.4F + 0.8F));
        }
      }
      
      return true;
    }
    com.emoniph.witchery.util.SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
    return true;
  }
}
