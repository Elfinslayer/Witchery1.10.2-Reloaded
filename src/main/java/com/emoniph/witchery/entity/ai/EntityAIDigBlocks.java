package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.util.BlockUtil;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.oredict.OreDictionary;

public class EntityAIDigBlocks extends net.minecraft.entity.ai.EntityAIBase
{
  protected final EntityGoblin entity;
  protected final double range;
  protected final double kobolditeChance;
  
  public EntityAIDigBlocks(EntityGoblin entity, double range, double kobolditeChance)
  {
    this.entity = entity;
    this.range = range;
    this.kobolditeChance = kobolditeChance;
    func_75248_a(7);
  }
  
  public static final GameProfile NORMAL_MINER_PROFILE = new GameProfile(UUID.fromString("AB06ACB0-0CDB-11E4-9191-0800200C9A66"), "[Minecraft]");
  
  public static final GameProfile KOBOLDITE_MINER_PROFILE = new GameProfile(UUID.fromString("24818AE0-0CDE-11E4-9191-0800200C9A66"), "[Minecraft]");
  

  MovingObjectPosition mop = null;
  int failedChecks = 0;
  
  public boolean func_75250_a()
  {
    if ((entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() != null) && ((entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe)) && (entity.func_110167_bD()) && (entity.field_70170_p.field_73012_v.nextInt(2) == 0))
    {

      MovingObjectPosition mop = raytraceBlocks(entity.field_70170_p, entity, true, failedChecks == 15 ? 1.0D : 4.0D, failedChecks == 15);
      if ((mop == null) || (field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) {
        failedChecks += 1;
        mop = null;
        return false;
      }
      
      Block block = BlockUtil.getBlock(entity.field_70170_p, mop);
      if (isMineable(block, entity.field_70170_p, field_72311_b, field_72312_c, field_72309_d)) {
        failedChecks = 0;
        this.mop = mop;
        return true;
      }
      this.mop = null;
      failedChecks += 1;
      return false;
    }
    
    return false;
  }
  
  private boolean isMineable(Block block, World world, int x, int y, int z)
  {
    if ((block.func_149688_o() != Material.field_151576_e) && (block.func_149688_o() != Material.field_151595_p) && (block.func_149688_o() != Material.field_151577_b) && (block.func_149688_o() != Material.field_151597_y) && (block.func_149688_o() != Material.field_151578_c))
    {
      return false;
    }
    
    if (block.func_149712_f(world, x, y, z) < 0.0F) {
      return false;
    }
    

    return true;
  }
  
  private static MovingObjectPosition raytraceBlocks(World world, EntityLiving player, boolean collisionFlag, double reachDistance, boolean down)
  {
    Vec3 playerPosition = Vec3.func_72443_a(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    
    float rotationYaw = field_73012_v.nextInt(360);
    field_70177_z = rotationYaw;
    float rotationPitch = down ? 90.0F : 0.0F;
    float f1 = MathHelper.func_76134_b(-rotationYaw * 0.017453292F - 3.1415927F);
    float f2 = MathHelper.func_76126_a(-rotationYaw * 0.017453292F - 3.1415927F);
    float f3 = -MathHelper.func_76134_b(-rotationPitch * 0.017453292F);
    float f4 = MathHelper.func_76126_a(-rotationPitch * 0.017453292F);
    Vec3 playerLook = Vec3.func_72443_a(f2 * f3, f4, f1 * f3);
    
    Vec3 playerViewOffset = Vec3.func_72443_a(field_72450_a + field_72450_a * reachDistance, field_72448_b + field_72448_b * reachDistance, field_72449_c + field_72449_c * reachDistance);
    

    return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
  }
  
  public void func_75249_e()
  {
    double SPEED = 0.6D;
    entity.func_70661_as().func_75492_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 0.6D);
  }
  
  public boolean func_75253_b()
  {
    return (entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() != null) && ((entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe)) && (entity.func_110167_bD()) && (mop != null);
  }
  

  public void func_75251_c()
  {
    if (entity.isWorking()) {
      entity.setWorking(false);
    }
  }
  
  private int waitTimer = 60;
  
  public void func_75246_d()
  {
    double DROP_RANGE = 2.5D;
    double DROP_RANGE_SQ = 6.25D;
    double dist = entity.func_70092_e(mopfield_72311_b + 0.5D, mopfield_72312_c + 0.5D, mopfield_72309_d + 0.5D);
    boolean retry = true;
    if (dist <= 6.25D) {
      if (!entity.isWorking()) {
        entity.setWorking(true);
      }
      if (--waitTimer == 0) {
        if (!tryHarvestBlock(entity.field_70170_p, mopfield_72311_b, mopfield_72312_c, mopfield_72309_d, entity)) {
          retry = false;
        }
        
        this.mop = null;
        waitTimer = getNextHarvestDelay();
      }
    } else if (entity.func_70661_as().func_75500_f()) {
      this.mop = null;
      waitTimer = getNextHarvestDelay();
      
      if (entity.isWorking()) {
        entity.setWorking(false);
      }
    }
    else if (!entity.isWorking()) {
      entity.setWorking(true);
    }
    

    if ((this.mop == null) && (retry) && (entity.field_70170_p.field_73012_v.nextInt(20) != 0)) {
      MovingObjectPosition mop = raytraceBlocks(entity.field_70170_p, entity, true, 4.0D, false);
      if ((mop != null) && (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK))
      {
        Block block = BlockUtil.getBlock(entity.field_70170_p, mop);
        if (isMineable(block, entity.field_70170_p, field_72311_b, field_72312_c, field_72309_d)) {
          this.mop = mop;
          waitTimer = getNextHarvestDelay();
        }
      }
    }
  }
  
  private int getNextHarvestDelay() {
    return isHoldingKobolditePick(entity) ? 4 : 60;
  }
  
  private static boolean isHoldingKobolditePick(EntityLivingBase entity) {
    return (entity.func_70694_bm() != null) && (entity.func_70694_bm().func_77973_b() == ItemsKOBOLDITE_PICKAXE);
  }
  
  public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester)
  {
    boolean kobolditePick = isHoldingKobolditePick(harvester);
    EntityPlayer minerPlayer = net.minecraftforge.common.util.FakePlayerFactory.get((WorldServer)world, kobolditePick ? KOBOLDITE_MINER_PROFILE : NORMAL_MINER_PROFILE);
    
    return tryHarvestBlock(world, par1, par2, par3, harvester, minerPlayer);
  }
  
  public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester, EntityPlayer minerPlayer) {
    Block block = world.func_147439_a(par1, par2, par3);
    int blockMeta = world.func_72805_g(par1, par2, par3);
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(par1, par2, par3, world, block, blockMeta, minerPlayer);
    event.setCanceled(false);
    MinecraftForge.EVENT_BUS.post(event);
    
    if (event.isCanceled()) {
      return false;
    }
    ItemStack stack = harvester.func_70694_bm();
    if ((stack != null) && (stack.func_77973_b().onBlockStartBreak(stack, par1, par2, par3, minerPlayer))) {
      return false;
    }
    
    world.func_72926_e(2001, par1, par2, par3, Block.func_149682_b(block) + (blockMeta << 12));
    boolean canHarvest = false;
    
    if (block.func_149712_f(world, par1, par2, par3) >= 0.0F) {
      if (block.func_149688_o().func_76229_l()) {
        canHarvest = true;
      }
      
      String tool = block.getHarvestTool(blockMeta);
      
      int toolLevel = stack != null ? stack.func_77973_b().getHarvestLevel(stack, tool) : 0;
      if (toolLevel < 0) {
        canHarvest = true;
      }
      
      if (toolLevel >= block.getHarvestLevel(blockMeta)) {
        canHarvest = true;
      }
    }
    


    if (canHarvest) {
      canHarvest = removeBlock(world, par1, par2, par3, minerPlayer);
      if (canHarvest) {
        block.func_149636_a(world, minerPlayer, par1, par2, par3, blockMeta);
      }
    }
    
    return canHarvest;
  }
  
  private static boolean removeBlock(World world, int x, int y, int z, EntityPlayer player)
  {
    Block block = world.func_147439_a(x, y, z);
    int metadata = world.func_72805_g(x, y, z);
    block.func_149681_a(world, x, y, z, metadata, player);
    boolean flag = block.removedByPlayer(world, player, x, y, z, true);
    
    if (flag) {
      block.func_149664_b(world, x, y, z, metadata);
    }
    
    return flag;
  }
  
  public static void onHarvestDrops(EntityPlayer harvester, BlockEvent.HarvestDropsEvent event) {
    if ((harvester != null) && (!field_70170_p.field_72995_K) && (!event.isCanceled()) && (
      (isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE)) || (isEqual(harvester.func_146103_bH(), NORMAL_MINER_PROFILE)))) {
      boolean hasKobolditePick = isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE);
      ArrayList<ItemStack> newDrops = new ArrayList();
      double kobolditeChance = hasKobolditePick ? 0.02D : 0.01D;
      for (ItemStack drop : drops) {
        int[] oreIDs = OreDictionary.getOreIDs(drop);
        boolean addOriginal = true;
        if (oreIDs.length > 0) {
          String oreName = OreDictionary.getOreName(oreIDs[0]);
          if ((oreName != null) && (oreName.startsWith("ore"))) {
            ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
            
            if ((smeltedDrop != null) && (hasKobolditePick) && (field_70170_p.field_73012_v.nextDouble() < 0.5D)) {
              addOriginal = false;
              newDrops.add(smeltedDrop.func_77946_l());
              newDrops.add(smeltedDrop.func_77946_l());
              if (field_70170_p.field_73012_v.nextDouble() < 0.25D) {
                newDrops.add(smeltedDrop.func_77946_l());
              }
            }
            
            kobolditeChance = hasKobolditePick ? 0.08D : 0.05D;
          }
        }
        if (addOriginal) {
          newDrops.add(drop);
        }
      }
      drops.clear();
      for (ItemStack newDrop : newDrops) {
        drops.add(newDrop);
      }
      if ((kobolditeChance > 0.0D) && (field_70170_p.field_73012_v.nextDouble() < kobolditeChance)) {
        drops.add(ItemsGENERIC.itemKobolditeDust.createStack());
      }
    }
  }
  
  private static boolean isEqual(GameProfile a, GameProfile b)
  {
    if ((a == null) || (b == null) || (a.getId() == null) || (b.getId() == null)) {
      return false;
    }
    
    return a.getId().equals(b.getId());
  }
}
