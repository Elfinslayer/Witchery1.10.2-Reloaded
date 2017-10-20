package com.emoniph.witchery.blocks;

import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStatueOfWorship extends BlockBaseContainer
{
  public static class ClassItemBlock extends ItemBlock
  {
    public ClassItemBlock(Block block)
    {
      super();
    }
    
    public String func_77653_i(ItemStack stack)
    {
      String s = super.func_77653_i(stack);
      String player = BlockStatueOfWorship.getBoundPlayerName(stack);
      if ((player != null) && (!player.isEmpty())) {
        return s + " (" + player + ")";
      }
      return s;
    }
  }
  
  public BlockStatueOfWorship()
  {
    super(Material.field_151576_e, TileEntityStatueOfWorship.class, ClassItemBlock.class);
    func_149711_c(3.5F);
    func_149752_b(20.0F);
    func_149672_a(field_149769_e);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public String func_149732_F()
  {
    return super.func_149732_F();
  }
  
  public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase player, ItemStack stack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    if (l == 0) {
      world.func_72921_c(posX, posY, posZ, 2, 2);
    } else if (l == 1) {
      world.func_72921_c(posX, posY, posZ, 5, 2);
    } else if (l == 2) {
      world.func_72921_c(posX, posY, posZ, 3, 2);
    } else if (l == 3) {
      world.func_72921_c(posX, posY, posZ, 4, 2);
    }
    
    if ((stack != null) && ((player instanceof EntityPlayer))) {
      TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityStatueOfWorship.class);
      if (tile != null) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if ((nbtRoot != null) && (nbtRoot.func_74764_b("WITCBoundPlayer"))) {
          String playerName = nbtRoot.func_74779_i("WITCBoundPlayer");
          if ((playerName != null) && (!playerName.isEmpty())) {
            tile.setOwner(playerName);
          }
        }
      }
    }
  }
  
  public void func_149726_b(World world, int posX, int posY, int posZ)
  {
    super.func_149726_b(world, posX, posY, posZ);
    BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
  }
  
  public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer)
  {
    if (field_71075_bZ.field_75098_d) {
      par5 |= 0x8;
      world.func_72921_c(posX, posY, posZ, par5, 4);
    }
    
    func_149697_b(world, posX, posY, posZ, par5, 0);
    
    super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> drops = new ArrayList();
    if ((metadata & 0x8) == 0) {
      TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueOfWorship.class);
      if (tile != null) {
        ItemStack stack = new ItemStack(tile.func_145838_q());
        NBTTagCompound nbtRoot = new NBTTagCompound();
        stack.func_77982_d(nbtRoot);
        nbtRoot.func_74778_a("WITCBoundPlayer", owner != null ? owner : "");
        drops.add(stack);
      }
    }
    return drops;
  }
  
  private static String getBoundPlayerName(ItemStack stack) {
    NBTTagCompound nbtRoot = stack.func_77978_p();
    if (nbtRoot != null) {
      return nbtRoot.func_74779_i("WITCBoundPlayer");
    }
    return "";
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hiyY, float hitZ)
  {
    if (!field_72995_K) {
      ItemStack item = player.func_70694_bm();
      if ((item != null) && (item.func_77973_b() == Items.field_151156_bN)) {
        TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueOfWorship.class);
        if ((tile != null) && (owner != null) && (owner.equals(player.func_70005_c_())))
        {
          int worshippers = tile.updateWorshippersAndGetLevel();
          if ((worshippers >= 5) && 
            (tile.summonGoblinGods(player, 16.0D, 8))) {
            if (!field_71075_bZ.field_75098_d) {
              if (--field_77994_a <= 0) {
                player.func_70062_b(0, null);
              }
              double R = 8.0D;
              AxisAlignedBB bb = AxisAlignedBB.func_72330_a(x + 0.5D - R, y + 0.5D - R, z + 0.5D - R, x + 0.5D + R, y + 0.5D + R, z + 0.5D + R);
              
              List entities = world.func_72872_a(EntityGoblin.class, bb);
              int i = 0; for (int iMax = Math.min(entities.size(), 5); i < iMax; i++) {
                if ((entities.get(i) instanceof EntityGoblin)) {
                  EntityGoblin goblin = (EntityGoblin)entities.get(i);
                  goblin.func_70097_a(DamageSource.field_76376_m, goblin.func_110138_aP());
                }
              }
            }
            
            ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.5D, 0.5D, 16);
            return true;
          }
        }
      }
      
      ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.5D, 0.5D, 16);
    }
    return false;
  }
  
  public static class TileEntityStatueOfWorship extends TileEntityBase {
    private String owner;
    
    public TileEntityStatueOfWorship() {}
    
    public void setOwner(EntityPlayer player) { owner = player.func_70005_c_();
      if (!field_145850_b.field_72995_K) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public void setOwner(String player) {
      owner = player;
      if (!field_145850_b.field_72995_K) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public boolean hasOwner() {
      return (owner != null) && (!owner.isEmpty());
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74778_a("Owner", owner != null ? owner : "");
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      owner = nbtRoot.func_74779_i("Owner");
    }
    
    public Packet func_145844_m()
    {
      NBTTagCompound nbtTag = new NBTTagCompound();
      func_145841_b(nbtTag);
      return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
    }
    
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
      super.onDataPacket(net, packet);
      func_145839_a(packet.func_148857_g());
      field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
    }
    

    @SideOnly(Side.CLIENT)
    private ThreadDownloadImageData downloadImageSkin;
    @SideOnly(Side.CLIENT)
    private ResourceLocation locationSkin;
    @SideOnly(Side.CLIENT)
    public ResourceLocation getLocationSkin()
    {
      if (locationSkin == null) {
        setupCustomSkin();
      }
      if (locationSkin != null) {
        return locationSkin;
      }
      return AbstractClientPlayer.field_110314_b;
    }
    
    @SideOnly(Side.CLIENT)
    private void setupCustomSkin()
    {
      if ((owner != null) && (!owner.isEmpty())) {
        locationSkin = AbstractClientPlayer.func_110311_f(owner);
        downloadImageSkin = AbstractClientPlayer.func_110304_a(locationSkin, owner);
      }
    }
    
    public int updateWorshippersAndGetLevel() {
      double R = 8.0D;
      AxisAlignedBB bb = AxisAlignedBB.func_72330_a(field_145851_c + 0.5D - 8.0D, field_145848_d + 0.5D - 8.0D, field_145849_e + 0.5D - 8.0D, field_145851_c + 0.5D + 8.0D, field_145848_d + 0.5D + 8.0D, field_145849_e + 0.5D + 8.0D);
      
      List entities = field_145850_b.func_72872_a(EntityGoblin.class, bb);
      int worshipCount = 0;
      for (Object entity : entities) {
        if ((entity instanceof EntityGoblin)) {
          EntityGoblin goblin = (EntityGoblin)entity;
          if (goblin.isWorshipping()) {
            worshipCount++;
          } else {
            goblin.beginWorship(this);
          }
        }
      }
      return worshipCount;
    }
    
    public void func_145845_h()
    {
      super.func_145845_h();
      if ((!field_145850_b.field_72995_K) && (hasOwner())) {
        int PULSE_INTERVAL_IN_SECS = 5;
        if (TimeUtil.secondsElapsed(5, ticks)) {
          int worshipCount = updateWorshippersAndGetLevel();
          
          EntityPlayer player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(owner);
          if (player != null) {
            NBTTagCompound nbtPlayer = Infusion.getNBT(player);
            int WORSHIP_LEVEL_1 = 5;
            int WORSHIP_LEVEL_2 = 10;
            int WORSHIP_LEVEL_3 = 15;
            
            if (worshipCount >= 5) {
              int RECHARGE_RADIUS = 64;
              int RECHARGE_RADIUS_SQ = 4096;
              if (player.func_70092_e(0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e) <= 4096.0D) {
                int currentEnergy = Infusion.getCurrentEnergy(player);
                int maxEnergy = Infusion.getMaxEnergy(player);
                if (currentEnergy < maxEnergy) {
                  int ENERGY_PER_PULSE = 30;
                  Infusion.setCurrentEnergy(player, Math.min(currentEnergy + 30, maxEnergy));
                  ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, player, 1.0D, 2.0D, 8);
                }
              }
            }
            
            if (worshipCount >= 10)
            {






              ExtendedPlayer playerEx = ExtendedPlayer.get(player);
              if (playerEx != null) {
                playerEx.addWorship(worshipCount >= 15 ? 1 : 0);
              }
            }
            
            double GODS_SUMMON_CHANCE = 0.01D * instancehobgoblinGodSpawnChance * 0.01D;
            

            if ((worshipCount >= 15) && (instancehobgoblinGodSpawnChance > 0) && (field_145850_b.field_73012_v.nextDouble() < GODS_SUMMON_CHANCE))
            {
              summonGoblinGods(player, 64.0D, 16);
            }
          }
        }
      }
    }
    
    public boolean summonGoblinGods(EntityPlayer player, double detectDistance, int spawnDistance) {
      double R2 = detectDistance;
      AxisAlignedBB bb2 = AxisAlignedBB.func_72330_a(field_145851_c + 0.5D - R2, field_145848_d + 0.5D - R2, field_145849_e + 0.5D - R2, field_145851_c + 0.5D + R2, field_145848_d + 0.5D + R2, field_145849_e + 0.5D + R2);
      
      if ((field_145850_b.func_72872_a(EntityGoblinMog.class, bb2).size() == 0) && (field_145850_b.func_72872_a(EntityGoblinGulg.class, bb2).size() == 0))
      {
        EntityCreature mog = InfusionInfernal.spawnCreature(field_145850_b, EntityGoblinMog.class, field_145851_c, field_145848_d, field_145849_e, player, 0, spawnDistance, ParticleEffect.FLAME, SoundEffect.MOB_WITHER_SPAWN);
        
        if (mog != null) {
          mog.func_110161_a(null);
          mog.func_70624_b(player);
          
          EntityCreature gulg = new EntityGoblinGulg(field_145850_b);
          gulg.func_70012_b(field_70165_t, field_70163_u, field_70161_v, 0.0F, 0.0F);
          field_145850_b.func_72838_d(gulg);
          gulg.func_110161_a(null);
          gulg.func_70624_b(player);
          
          return true;
        }
      }
      
      return false;
    }
  }
}
