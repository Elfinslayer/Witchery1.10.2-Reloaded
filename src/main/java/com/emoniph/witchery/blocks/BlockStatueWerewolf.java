package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.QuestState;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStatueWerewolf extends BlockBaseContainer
{
  public BlockStatueWerewolf()
  {
    super(Material.field_151576_e, TileEntityStatueWerewolf.class);
    func_149752_b(1000.0F);
    func_149711_c(2.5F);
    func_149672_a(field_149769_e);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
  {
    switch (MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) {
    case 0: 
      world.func_72921_c(x, y, z, 2, 2);
      break;
    case 1: 
      world.func_72921_c(x, y, z, 5, 2);
      break;
    case 2: 
      world.func_72921_c(x, y, z, 3, 2);
      break;
    case 3: 
      world.func_72921_c(x, y, z, 4, 2);
    }
    
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (!field_72995_K) {
      TileEntityStatueWerewolf statue = (TileEntityStatueWerewolf)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueWerewolf.class);
      
      if (statue != null) {
        int meta = world.func_72805_g(x, y, z);
        ForgeDirection direction = ForgeDirection.getOrientation(meta);
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        ItemStack heldStack = player.func_70694_bm();
        SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0F, 1.0F);
        int level = playerEx.getWerewolfLevel();
        int GOLD_REQUIRED = 3;
        if ((level >= 2) && (heldStack != null) && (heldStack.func_77973_b() == Items.field_151043_k) && (field_77994_a >= 3))
        {
          ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.mooncharmcrafted", new Object[0]);
          
          heldStack.func_77979_a(3);
          EntityItem itemEntity = new EntityItem(world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, new ItemStack(ItemsMOON_CHARM));
          

          field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
          world.func_72838_d(itemEntity);
          ParticleEffect.REDDUST.send(SoundEffect.RANDOM_ORB, itemEntity, 0.2D, 0.2D, 16);
        } else {
          switch (level) {
          case 0: 
            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, player, 1.0D, 1.0D, 16);
            player.func_70690_d(new PotionEffect(field_76419_ffield_76415_H, TimeUtil.secsToTicks(60), 0));
            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.notworthy", new Object[0]);
            break;
          
          case 1: 
            if ((heldStack != null) && (heldStack.func_77973_b() == Items.field_151043_k)) {
              if (field_77994_a >= 3) {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2complete", new Object[0]);
                
                heldStack.func_77979_a(3);
                EntityItem itemEntity = new EntityItem(world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, new ItemStack(ItemsMOON_CHARM));
                

                field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
                world.func_72838_d(itemEntity);
                ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, itemEntity, 0.2D, 0.2D, 16);
                playerEx.increaseWerewolfLevel();
              } else {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2progress", new Object[] { Integer.valueOf(3).toString(), Integer.valueOf(3 - field_77994_a).toString() });
              }
              

            }
            else {
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2begin", new Object[] { Integer.valueOf(3).toString() });
            }
            
            break;
          case 2: 
            int MUTTON_REQUIRED = 30;
            if ((heldStack != null) && (ItemsGENERIC.itemMuttonRaw.isMatch(heldStack))) {
              if (field_77994_a >= 30) {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3complete", new Object[0]);
                
                heldStack.func_77979_a(30);
                ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
                
                playerEx.increaseWerewolfLevel();
              } else {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3progress", new Object[] { Integer.valueOf(30).toString(), Integer.valueOf(30 - field_77994_a).toString() });
              }
              

            }
            else {
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3begin", new Object[] { Integer.valueOf(30).toString() });
            }
            
            break;
          case 3: 
            int TONGUES_REQUIRED = 10;
            if ((heldStack != null) && (ItemsGENERIC.itemDogTongue.isMatch(heldStack))) {
              if (field_77994_a >= 10) {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4complete", new Object[0]);
                
                heldStack.func_77979_a(10);
                ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
                
                playerEx.increaseWerewolfLevel();
              } else {
                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4progress", new Object[] { Integer.valueOf(10).toString(), Integer.valueOf(10 - field_77994_a).toString() });
              }
              

            }
            else {
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4begin", new Object[] { Integer.valueOf(10).toString() });
            }
            
            break;
          case 4: 
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5begin", new Object[0]);
              
              EntityItem itemEntity = new EntityItem(world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, new ItemStack(ItemsHORN_OF_THE_HUNT));
              

              field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
              world.func_72838_d(itemEntity);
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, itemEntity, 0.2D, 0.2D, 16);
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5progress", new Object[0]);
              
              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 5: 
            int KILLS_REQUIRED = 10;
            if (playerEx.getWolfmanQuestCounter() >= 10) {
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
            }
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6begin", new Object[] { Integer.valueOf(10).toString() });
              
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6progress", new Object[] { Integer.valueOf(10).toString(), Integer.valueOf(10 - playerEx.getWolfmanQuestCounter()).toString() });
              


              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 6: 
            int PLACES_HOWLED_AT = 16;
            if (playerEx.getWolfmanQuestCounter() >= 16) {
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
            }
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7begin", new Object[] { Integer.valueOf(16).toString() });
              
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7progress", new Object[] { Integer.valueOf(16).toString(), Integer.valueOf(16 - playerEx.getWolfmanQuestCounter()).toString() });
              



              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 7: 
            int WOLVES_TAMED = 6;
            if (playerEx.getWolfmanQuestCounter() >= 6) {
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
            }
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8begin", new Object[] { Integer.valueOf(6).toString() });
              
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8progress", new Object[] { Integer.valueOf(6).toString(), Integer.valueOf(6 - playerEx.getWolfmanQuestCounter()).toString() });
              

              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 8: 
            int PIGMEN_KILLED = 30;
            if (playerEx.getWolfmanQuestCounter() >= 30) {
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
            }
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9begin", new Object[] { Integer.valueOf(30).toString() });
              
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9progress", new Object[] { Integer.valueOf(30).toString(), Integer.valueOf(30 - playerEx.getWolfmanQuestCounter()).toString() });
              

              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 9: 
            int PEOPLE_KILLED = 1;
            if (playerEx.getWolfmanQuestCounter() >= 1) {
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
            }
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$QuestState[playerEx.getWolfmanQuestState().ordinal()]) {
            case 1: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10begin", new Object[] { Integer.valueOf(1).toString() });
              
              playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
              break;
            case 2: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10progress", new Object[] { Integer.valueOf(1).toString(), Integer.valueOf(1 - playerEx.getWolfmanQuestCounter()).toString() });
              


              break;
            case 3: 
              ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10complete", new Object[0]);
              
              ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, offsetX + 0.5D + x, 1.1D + y, 0.5D + z + offsetZ, 0.2D, 0.2D, 16);
              
              playerEx.increaseWerewolfLevel();
            }
            
            break;
          case 10: 
            SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0F, 1.0F);
            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10complete", new Object[0]);
          }
          
        }
      }
      

      return true;
    }
    return false;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
  {
    return false;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  
  public static class TileEntityStatueWerewolf extends TileEntity
  {
    public TileEntityStatueWerewolf() {}
    
    public boolean canUpdate() {
      return false;
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
    }
  }
}
