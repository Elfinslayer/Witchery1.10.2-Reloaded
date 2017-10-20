package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityVampire;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ItemGlassGoblet extends ItemBase
{
  @SideOnly(Side.CLIENT)
  private IIcon iconFull;
  
  public ItemGlassGoblet()
  {
    func_77625_d(1);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
  {
    if (!field_72995_K) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if (!hasBlood(stack)) {
        if (playerEx.getVampireLevel() >= 9) {
          if (playerEx.decreaseBloodPower(125, true)) {
            setBloodOwner(stack, player);
            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, field_70165_t, field_70163_u + field_70131_O * 0.85F, field_70161_v, 0.8D, 0.3D, 16);
          }
          else {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.notenoughblood", new Object[0]);
            
            SoundEffect.NOTE_SNARE.playOnlyTo(player);
          }
        } else if (playerEx.isVampire()) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nothighenoughlevel", new Object[0]);
          
          SoundEffect.NOTE_SNARE.playOnlyTo(player);
        } else {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nothinghappens", new Object[0]);
          
          SoundEffect.NOTE_SNARE.playOnlyTo(player);
        }
      } else {
        world.func_72956_a(player, "random.burp", 0.5F, field_73012_v.nextFloat() * 0.1F + 0.9F);
        if ((!playerEx.isVampire()) && (!hasBloodType(stack, BloodSource.CHICKEN))) {
          if ((!instanceallowVampireWolfHybrids) && (playerEx.getWerewolfLevel() > 0)) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.wolfcurse.hybridsnotallow", new Object[0]);
            
            return stack;
          }
          if (playerEx.getBloodPower() == 0) {
            playerEx.setVampireLevel(1);
            ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.8D, 1.5D, 16);
          } else if (CreatureUtil.isInSunlight(player)) {
            player.func_70015_d(5);
          } else {
            player.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, TimeUtil.secsToTicks(30)));
            player.func_70690_d(new PotionEffect(field_76420_gfield_76415_H, TimeUtil.secsToTicks(30), 1));
          }
        }
        setBloodOwner(stack, BloodSource.EMPTY);
      }
    }
    return stack;
  }
  
  public String func_77653_i(ItemStack stack)
  {
    if (hasBlood(stack)) {
      return ("" + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append(func_77657_g(stack)).append(".full").toString())).trim();
    }
    return super.func_77653_i(stack);
  }
  

  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
  {
    super.func_77624_a(stack, player, list, advTooltips);
    if (hasBlood(stack)) {
      list.add(String.format(Witchery.resource(func_77657_g(stack) + ".tip"), new Object[] { getBloodName(stack) }));
    }
  }
  

  public int func_77626_a(ItemStack stack)
  {
    return 32;
  }
  
  public EnumAction func_77661_b(ItemStack stack)
  {
    return hasBlood(stack) ? EnumAction.drink : EnumAction.block;
  }
  

  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceBlocks(world, player, true, 2.0D);
    if ((mop != null) && (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && 
      (world.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == Blocks.field_150465_bP)) {
      TileEntitySkull skull = (TileEntitySkull)BlockUtil.getTileEntity(world, field_72311_b, field_72312_c, field_72309_d, TileEntitySkull.class);
      
      if ((!field_72995_K) && 
        (skull != null) && (skull.func_145904_a() == 0)) {
        if ((hasBloodType(stack, BloodSource.CHICKEN)) && (field_73011_w.field_76574_g == 0) && (isRitual(world, field_72311_b, field_72312_c, field_72309_d)) && (world.func_72937_j(field_72311_b, field_72312_c, field_72309_d)) && (!world.func_72935_r()) && (instanceallowVampireRitual) && (!isElleNear(world, field_72311_b, field_72312_c - 1, field_72309_d, 32.0D)))
        {






          setBloodOwner(stack, BloodSource.EMPTY);
          EntityLightningBolt bolt = new EntityLightningBolt(world, 0.5D + field_72311_b, field_72312_c + 0.05D, 0.5D + field_72309_d);
          
          world.func_147468_f(field_72311_b, field_72312_c, field_72309_d);
          world.func_72942_c(bolt);
          EntityFollower follower = new EntityFollower(world);
          follower.setFollowerType(0);
          follower.func_110163_bv();
          follower.func_70080_a(0.5D + field_72311_b, field_72312_c + 1.05D, 0.5D + field_72309_d, 0.0F, 0.0F);
          
          follower.setOwner(player);
          ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_MOB_LILITH_TALK, world, 0.5D + field_72311_b, field_72312_c + 1.05D, 0.5D + field_72309_d, 1.0D, 2.0D, 16);
          
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.lilithquest", new Object[0]);
          
          world.func_72838_d(follower);

        }
        else if (!field_72995_K) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.seemswrong", new Object[0]);
          
          SoundEffect.NOTE_SNARE.playOnlyTo(player);
        }
      }
      

      return stack;
    }
    

    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  private boolean isElleNear(World world, double x, double y, double z, double range) {
    double r = range;
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(0.5D + x - r, y - r, 0.5D + z - r, 0.5D + x + r, y + r, 0.5D + z + r);
    
    List<EntityFollower> followers = world.func_72872_a(EntityFollower.class, bb);
    if ((followers != null) && (followers.size() > 0)) {
      return true;
    }
    return false;
  }
  
  private boolean isRitual(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) != Blocks.field_150465_bP) {
      return false;
    }
    TileEntitySkull skull = (TileEntitySkull)BlockUtil.getTileEntity(world, x, y, z, TileEntitySkull.class);
    if ((skull == null) || (skull.func_145904_a() != 0)) {
      return false;
    }
    
    Block string = Blocks.field_150473_bD;
    boolean b = true;
    b &= world.func_147439_a(x, y, z - 3) == string;
    b &= world.func_147439_a(x + 1, y, z - 3) == string;
    b &= world.func_147439_a(x + 2, y, z - 3) == string;
    b &= world.func_147439_a(x + 2, y, z - 2) == string;
    b &= world.func_147439_a(x + 3, y, z - 2) == string;
    b &= world.func_147439_a(x + 3, y, z - 1) == string;
    b &= world.func_147439_a(x + 3, y, z) == string;
    b &= world.func_147439_a(x + 3, y, z + 1) == string;
    b &= world.func_147439_a(x + 3, y, z + 2) == string;
    b &= world.func_147439_a(x + 2, y, z + 2) == string;
    
    if (!b) {
      return false;
    }
    
    b &= world.func_147439_a(x + 2, y, z + 3) == string;
    b &= world.func_147439_a(x + 1, y, z + 3) == string;
    b &= world.func_147439_a(x, y, z + 3) == string;
    b &= world.func_147439_a(x - 1, y, z + 3) == string;
    b &= world.func_147439_a(x - 2, y, z + 3) == string;
    b &= world.func_147439_a(x - 2, y, z + 2) == string;
    b &= world.func_147439_a(x - 3, y, z + 2) == string;
    b &= world.func_147439_a(x - 3, y, z + 1) == string;
    b &= world.func_147439_a(x - 3, y, z) == string;
    b &= world.func_147439_a(x - 3, y, z + 1) == string;
    b &= world.func_147439_a(x - 3, y, z + 2) == string;
    b &= world.func_147439_a(x - 2, y, z + 2) == string;
    b &= world.func_147439_a(x - 2, y, z + 3) == string;
    b &= world.func_147439_a(x - 1, y, z + 3) == string;
    
    if (!b) {
      return false;
    }
    
    Block candle = Blocks.field_150478_aa;
    b &= world.func_147439_a(x - 3, y, z + 3) == candle;
    b &= world.func_147439_a(x - 3, y, z - 3) == candle;
    b &= world.func_147439_a(x + 3, y, z + 3) == candle;
    b &= world.func_147439_a(x + 3, y, z - 3) == candle;
    
    Block redstone = Blocks.field_150488_af;
    b &= world.func_147439_a(x - 1, y, z) == redstone;
    b &= world.func_147439_a(x + 1, y, z) == redstone;
    b &= world.func_147439_a(x, y, z + 1) == redstone;
    b &= world.func_147439_a(x, y, z - 1) == redstone;
    b &= world.func_147439_a(x - 1, y, z - 1) == redstone;
    b &= world.func_147439_a(x - 1, y, z + 1) == redstone;
    b &= world.func_147439_a(x + 1, y, z - 1) == redstone;
    b &= world.func_147439_a(x + 1, y, z + 1) == redstone;
    
    for (int dx = x - 3; dx <= x + 3; dx++) {
      for (int dz = z - 3; dz <= z + 3; dz++) {
        if (!world.func_147439_a(dx, y - 1, dz).func_149721_r()) {
          return false;
        }
        
        if (!world.func_147437_c(dx, y + 1, dz)) {
          return false;
        }
        
        if (!world.func_147437_c(dx, y + 2, dz)) {
          return false;
        }
      }
    }
    
    return b;
  }
  
  public void handleCreatureDeath(World world, EntityPlayer player, EntityLivingBase victim) {
    if (((victim instanceof net.minecraft.entity.passive.EntityChicken)) && 
      (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsBOLINE)) {
      for (int i = 0; i < 9; i++) {
        ItemStack stack = field_71071_by.func_70301_a(i);
        if ((stack != null) && (stack.func_77973_b() == this)) {
          int x = MathHelper.func_76128_c(field_70165_t);
          int y = MathHelper.func_76128_c(field_70163_u);
          int z = MathHelper.func_76128_c(field_70161_v);
          for (int dx = x - 1; dx <= x + 1; dx++) {
            for (int dz = z - 1; dz <= z + 1; dz++) {
              for (int dy = y - 1; dy <= y + 1; dy++) {
                if (isRitual(world, dx, dy, dz)) {
                  setBloodOwner(stack, BloodSource.CHICKEN);
                  ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, field_70165_t, field_70163_u + field_70131_O * 0.85F, field_70161_v, 0.5D, 0.5D, 16);
                  

                  return;
                }
              }
            }
          }
          break;
        }
      }
    }
  }
  
  public void onEntityInteract(World world, EntityPlayer player, ItemStack stack, EntityInteractEvent event)
  {
    if ((!entityPlayer.field_70170_p.field_72995_K) && (hasBlood(stack)) && (!CreatureUtil.isWerewolf(target, true)) && (!CreatureUtil.isVampire(target)))
    {
      boolean success = false;
      if ((target instanceof EntityVillager)) {
        EntityVillager entity = (EntityVillager)target;
        if (tryConvertToVampire(entity, ExtendedVillager.get(entity).getBlood(), player, stack)) {
          success = true;
        }
      } else if ((target instanceof EntityVillageGuard)) {
        EntityVillageGuard entity = (EntityVillageGuard)target;
        if (tryConvertToVampire(entity, entity.getBlood(), player, stack)) {
          success = true;
        }
      }
      
      if (success) {
        ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, target.field_70165_t, target.field_70163_u, target.field_70161_v, target.field_70130_N, target.field_70131_O, 16);
        
        setBloodOwner(stack, BloodSource.EMPTY);
      } else {
        SoundEffect.NOTE_SNARE.playOnlyTo(player);
      }
      event.setCanceled(true);
    }
  }
  
  private boolean tryConvertToVampire(EntityLiving target, int blood, EntityPlayer player, ItemStack stack) {
    PotionEffect effect = target.func_70660_b(PotionsPARALYSED);
    if ((effect != null) && (effect.func_76458_c() >= 5)) {
      if (blood == 0) {
        if (isCoffinNear(field_70170_p, target, 4)) {
          convertToVampire(target);
          ExtendedPlayer playerEx = ExtendedPlayer.get(player);
          if ((playerEx.getVampireLevel() == 9) && (playerEx.canIncreaseVampireLevel()) && 
            (getBloodOwner(stack, field_70170_p) == player)) {
            playerEx.increaseVampireLevel();
          }
          
          return true;
        }
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nocoffinnear", new Object[0]);
      }
      else
      {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.targetnotdrained", new Object[0]);
      }
    }
    else {
      ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.targetnottransfixed", new Object[0]);
    }
    
    return false;
  }
  
  private boolean isCoffinNear(World world, Entity entity, int radius) {
    int x = MathHelper.func_76128_c(field_70165_t);
    int y = MathHelper.func_76128_c(field_70163_u);
    int z = MathHelper.func_76128_c(field_70161_v);
    for (int dx = x - radius; dx <= x + radius; dx++) {
      for (int dz = z - radius; dz <= z + radius; dz++) {
        for (int dy = y - radius; dy <= y + radius; dy++) {
          if (world.func_147439_a(dx, dy, dz) == BlocksCOFFIN) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private void convertToVampire(EntityLiving entity) {
    EntityVampire vampire = new EntityVampire(field_70170_p);
    vampire.func_110163_bv();
    vampire.func_82149_j(entity);
    vampire.func_110161_a(null);
    field_70170_p.func_72900_e(entity);
    field_70170_p.func_72838_d(vampire);
    field_70170_p.func_72889_a(null, 1017, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
  }
  

  public IIcon func_77617_a(int meta)
  {
    if (meta == 0) {
      return super.func_77617_a(meta);
    }
    return iconFull;
  }
  




  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    iconFull = iconRegister.func_94245_a(func_111208_A() + "full");
  }
  
  private boolean hasBlood(ItemStack stack) {
    return stack.func_77960_j() == 1;
  }
  
  private boolean hasBloodType(ItemStack stack, BloodSource source) {
    return (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCBloodUUID")) && (stack.func_77978_p().func_74779_i("WITCBloodUUID").equals(KEY));
  }
  
  private EntityPlayer getBloodOwner(ItemStack stack, World world)
  {
    if (stack.func_77942_o()) {
      String s = stack.func_77978_p().func_74779_i("WITCBloodUUID");
      if ((s != null) && (!s.isEmpty())) {
        if (BloodSource.isOneOf(s)) {
          return null;
        }
        UUID uuid = UUID.fromString(s);
        return uuid != null ? world.func_152378_a(uuid) : null;
      }
    }
    
    return null;
  }
  
  private String getBloodName(ItemStack stack) {
    if (stack.func_77942_o()) {
      return stack.func_77978_p().func_74779_i("WITCBloodName");
    }
    return "";
  }
  
  public void setBloodOwner(ItemStack stack, EntityPlayer player) {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    if (player != null) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_74778_a("WITCBloodUUID", player.func_146103_bH().getId().toString());
      nbtRoot.func_74778_a("WITCBloodName", player.func_146103_bH().getName());
      stack.func_77964_b(1);
    } else {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_82580_o("WITCBloodUUID");
      nbtRoot.func_82580_o("WITCBloodName");
      stack.func_77964_b(0);
    }
  }
  
  public void setBloodOwner(ItemStack stack, BloodSource source) {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    if (source == BloodSource.EMPTY) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_82580_o("WITCBloodUUID");
      nbtRoot.func_82580_o("WITCBloodName");
      stack.func_77964_b(0);
    } else {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_74778_a("WITCBloodUUID", KEY);
      nbtRoot.func_74778_a("WITCBloodName", DISPLAY_NAME);
      stack.func_77964_b(1);
    }
  }
  
  public static enum BloodSource {
    EMPTY("", ""),  CHICKEN("__chicken", "item.witchery:glassgoblet.chicken"),  LILITH("__lilith", "item.witchery:glassgoblet.lilith");
    
    public final String KEY;
    public final String DISPLAY_NAME;
    
    private BloodSource(String nbtKey, String resourceKey)
    {
      KEY = nbtKey;
      DISPLAY_NAME = Witchery.resource(resourceKey);
    }
    
    public static boolean isOneOf(String key) {
      return (CHICKENKEY.equals(key)) || (LILITHKEY.equals(key));
    }
  }
}
