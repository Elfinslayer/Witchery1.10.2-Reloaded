package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBeartrap extends BlockBaseContainer
{
  private final boolean silvered;
  
  public BlockBeartrap(boolean silvered)
  {
    super(Material.field_151573_f, TileEntityBeartrap.class);
    this.silvered = silvered;
    func_149711_c(5.0F);
    func_149752_b(10.0F);
    func_149672_a(field_149777_j);
    float w = 0.3F;
    func_149676_a(0.19999999F, 0.01F, 0.19999999F, 0.8F, 0.1F, 0.8F);
  }
  
  public TileEntity func_149915_a(World world, int metadata)
  {
    TileEntityBeartrap tile = new TileEntityBeartrap(silvered);
    return tile;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    return null;
  }
  
  public int func_149645_b()
  {
    return -1;
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
    
    
    if ((!field_72995_K) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
      if (tile != null) {
        owner = player.func_146103_bH();
        sprung = true;
        tile.markBlockForUpdate(false);
      }
    }
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    if (silvered) {
      return new ArrayList();
    }
    
    return super.getDrops(world, x, y, z, metadata, fortune);
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity)
  {
    if ((!field_72995_K) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)entity;
      TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
      if ((tile != null) && (!sprung) && (world.func_82737_E() > setTime + 20L) && ((!silvered) || (CreatureUtil.isWerewolf(entity, false)))) {
        AxisAlignedBB trapBounds = AxisAlignedBB.func_72330_a(x + field_149759_B, y + field_149760_C, z + field_149754_D, x + field_149755_E, y + field_149756_F, z + field_149757_G);
        

        if ((trapBounds.func_72326_a(field_70121_D)) && (
          (!silvered) || (tile.tryTrapWolf(living)))) {
          boolean isCreative = ((entity instanceof EntityPlayer)) && (field_71075_bZ.field_75098_d);
          
          if (!isCreative) {
            living.func_70690_d(new PotionEffect(PotionsPARALYSED.field_76415_H, TimeUtil.secsToTicks(30), 2, true));
          }
          
          living.func_70097_a(DamageSource.field_82728_o, 4.0F);
          ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_MANTRAP, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.25D, 0.5D, 16);
          
          sprung = true;
          tile.markBlockForUpdate(true);
        }
      }
    }
  }
  


  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (!field_72995_K) {
      TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
      if (tile != null) {
        SoundEffect.WITCHERY_RANDOM_CLICK.playAtPlayer(world, player);
        sprung = (!sprung);
        if (!sprung) {
          setTime = world.func_82737_E();
        }
        tile.markBlockForUpdate(false);
      }
    }
    
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public static boolean checkForHiddenTrap(EntityPlayer player, MovingObjectPosition mop) {
    if ((mop != null) && (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && 
      (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == BlocksBEARTRAP)) {
      TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(field_70170_p, field_72311_b, field_72312_c, field_72309_d, TileEntityBeartrap.class);
      
      if (tile != null) {
        return !tile.isVisibleTo(player);
      }
    }
    
    return false;
  }
  
  public static class TileEntityBeartrap extends TileEntityBase {
    private final boolean silvered;
    private GameProfile owner;
    private boolean sprung = true;
    private long setTime = 0L;
    private long startTime = 0L;
    private UUID spawnedWolfID = null;
    
    public TileEntityBeartrap() {
      silvered = false;
    }
    
    public TileEntityBeartrap(boolean silvered) {
      this.silvered = silvered;
    }
    
    public boolean tryTrapWolf(EntityLivingBase living) {
      if ((silvered) && 
        ((living instanceof EntityWolfman))) {
        EntityWolfman wolf = (EntityWolfman)living;
        if ((spawnedWolfID != null) && (wolf != null) && (wolf.getPersistentID().equals(spawnedWolfID))) {
          SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playAt(this, 1.0F);
          wolf.setInfectious();
          return true;
        }
      }
      
      return false;
    }
    
    public boolean isSprung() {
      return sprung;
    }
    
    public boolean canUpdate()
    {
      return silvered;
    }
    
    private static final int MIN_LURE_TIME = TimeUtil.minsToTicks(1);
    private static final int LURE_EXTRA = TimeUtil.minsToTicks(1);
    
    public void func_145845_h()
    {
      super.func_145845_h();
      
      if ((!field_145850_b.field_72995_K) && (silvered) && (!sprung) && (spawnedWolfID == null) && (TimeUtil.secondsElapsed(10, ticks)))
      {
        if ((baitFound()) && (CreatureUtil.isFullMoon(field_145850_b))) {
          long time = field_145850_b.func_82737_E();
          if (startTime > 0L) {
            long activateTime = startTime;
            if ((time > activateTime) && (CreatureUtil.isFullMoon(field_145850_b))) {
              EntityCreature creature = InfusionInfernal.spawnCreature(field_145850_b, EntityWolfman.class, field_145851_c, field_145848_d, field_145849_e, null, 16, 32, ParticleEffect.SMOKE, SoundEffect.WITCHERY_MOB_WOLFMAN_TALK);
              

              if (creature != null) {
                creature.func_110163_bv();
                spawnedWolfID = creature.getPersistentID();
              }
            }
          } else {
            startTime = time;
          }
        } else {
          startTime = 0L;
        }
      }
    }
    
    private boolean baitFound() {
      double R = 8.0D;
      double RSQ = 64.0D;
      boolean foundSheep = false;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(0.5D + field_145851_c - 8.0D, 0.5D + field_145848_d - 8.0D, 0.5D + field_145849_e - 8.0D, 0.5D + field_145851_c + 8.0D, 0.5D + field_145848_d + 8.0D, 0.5D + field_145849_e + 8.0D);
      
      List<EntitySheep> sheep = field_145850_b.func_72872_a(EntitySheep.class, bounds);
      for (EntitySheep aSheep : sheep) {
        if ((aSheep.func_70092_e(0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e) <= 64.0D) && 
          (aSheep.func_110167_bD())) {
          foundSheep = true;
          break;
        }
      }
      

      boolean wolfaltar = (field_145850_b.func_147439_a(field_145851_c + 1, field_145848_d, field_145849_e) == BlocksWOLF_ALTAR) || (field_145850_b.func_147439_a(field_145851_c - 1, field_145848_d, field_145849_e) == BlocksWOLF_ALTAR) || (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e + 1) == BlocksWOLF_ALTAR) || (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e - 1) == BlocksWOLF_ALTAR);
      


      return (wolfaltar) && (foundSheep);
    }
    
    public boolean isVisibleTo(EntityPlayer player) {
      if ((sprung) || (owner == null) || (silvered))
        return true;
      if (player == null)
        return false;
      if (player.func_146103_bH().equals(owner)) {
        return true;
      }
      return false;
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74757_a("Sprung", sprung);
      nbtRoot.func_74772_a("WolftrapStart", startTime);
      if (spawnedWolfID != null) {
        nbtRoot.func_74772_a("WolfLeast", spawnedWolfID.getLeastSignificantBits());
        nbtRoot.func_74772_a("WolfMost", spawnedWolfID.getMostSignificantBits());
      }
      
      if (owner != null) {
        NBTTagCompound nbtPlayer = new NBTTagCompound();
        NBTUtil.func_152460_a(nbtPlayer, owner);
        nbtRoot.func_74782_a("Owner", nbtPlayer);
      }
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot) {
      super.func_145839_a(nbtRoot);
      sprung = nbtRoot.func_74767_n("Sprung");
      startTime = nbtRoot.func_74763_f("WolftrapStart");
      if (nbtRoot.func_150297_b("Owner", 10)) {
        owner = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Owner"));
      } else {
        owner = null;
      }
      
      if ((nbtRoot.func_74764_b("WolfLeast")) && (nbtRoot.func_74764_b("WolfMost"))) {
        spawnedWolfID = new UUID(nbtRoot.func_74763_f("WolfMost"), nbtRoot.func_74763_f("WolfLeast"));
      } else {
        spawnedWolfID = null;
      }
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
  }
}
