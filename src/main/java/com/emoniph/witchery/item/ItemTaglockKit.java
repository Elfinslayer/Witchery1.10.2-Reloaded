package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockBloodRose.TileEntityBloodRose;
import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
import com.emoniph.witchery.entity.EntityEye;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.network.PacketCamPos;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ItemTaglockKit extends ItemBase
{
  @SideOnly(Side.CLIENT)
  private IIcon emptyIcon;
  @SideOnly(Side.CLIENT)
  private IIcon fullIcon;
  static final String KEY_PLAYER_NAME = "WITCPlayer";
  static final String KEY_DISPLAY_NAME = "WITCDisplayName";
  static final String KEY_ENTITY_ID_MOST = "WITCEntityIDm";
  static final String KEY_ENTITY_ID_LEAST = "WITCEntityIDl";
  
  public ItemTaglockKit()
  {
    func_77625_d(16);
    func_77656_e(0);
  }
  

  public String func_77653_i(ItemStack itemStack)
  {
    String entityID = getBoundEntityDisplayName(itemStack, Integer.valueOf(1));
    String localizedName = super.func_77653_i(itemStack);
    return !entityID.isEmpty() ? String.format("%s (%s)", new Object[] { localizedName, entityID }) : localizedName;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
  {
    super.func_77624_a(stack, player, list, advTooltips);
    String entityID = getBoundEntityDisplayName(stack, Integer.valueOf(1));
    if ((entityID != null) && (!entityID.isEmpty())) {
      list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
    } else {
      list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
    }
  }
  
  public void func_94581_a(IIconRegister par1IconRegister)
  {
    emptyIcon = par1IconRegister.func_94245_a(func_111208_A());
    fullIcon = par1IconRegister.func_94245_a(func_111208_A() + ".full");
  }
  
  public int func_77626_a(ItemStack stack)
  {
    return 1200;
  }
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
  {
    World world = field_70170_p;
    int elapsedTicks = func_77626_a(stack) - countdown;
    if ((!field_72995_K) && 
      (elapsedTicks % 20 == 0)) {
      EntityLivingBase entity = getBoundEntity(world, player, stack, Integer.valueOf(1));
      if ((entity != null) && (field_71093_bK == field_71093_bK)) {
        if (entity == player) {
          if (elapsedTicks == 0) {
            EntityEye eye = new EntityEye(world);
            eye.func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70177_z, 90.0F);
            world.func_72838_d(eye);
            
            Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, eye), player);
          }
          else {
            Witchery.packetPipeline.sendTo(new PacketCamPos(true, false, null), player);
          }
        } else {
          Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, entity), player);
        }
      } else {
        Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
      }
    }
  }
  

  public EnumAction func_77661_b(ItemStack stack)
  {
    return EnumAction.none;
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
  {
    if (!field_72995_K) {
      Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
    }
    return stack;
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
  {
    if (!field_72995_K) {
      Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damageValue)
  {
    return damageValue == 1 ? fullIcon : emptyIcon;
  }
  
  public static class PlayerComparitor implements java.util.Comparator<EntityPlayer> {
    public PlayerComparitor() {}
    
    public int compare(EntityPlayer p1, EntityPlayer p2) { return p1.func_70005_c_().compareTo(p2.func_70005_c_()); }
  }
  

  public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
  {
    Block block = world.func_147439_a(x, y, z);
    if ((block == Blocks.field_150324_C) || (block == BlocksCOFFIN) || (block.func_149739_a().equals("tile.blockCarpentersBed")) || (block.isBed(world, x, y, z, player))) {
      int i1 = world.func_72805_g(x, y, z);
      Log.instance().debug(String.format("Using taglock on bed [%s] meta %d", new Object[] { block.func_149739_a(), Integer.valueOf(i1) }));
      
      if ((block == Blocks.field_150324_C) && (!BlockBed.func_149975_b(i1))) {
        int j1 = BlockBed.func_149895_l(i1);
        x += BlockBed.field_149981_a[j1][0];
        z += BlockBed.field_149981_a[j1][1];
        
        if (world.func_147439_a(x, y, z) != Blocks.field_150324_C) {
          SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
          
          return !field_72995_K;
        }
      }
      
      ChunkCoordinates clickedBedLocation = new ChunkCoordinates(x, y, z);
      
      if (player.func_70093_af()) {
        if (!field_72995_K) {
          setTaglockForEntity(world, player, itemstack, player);
        }
        return !field_72995_K;
      }
      if (!field_72995_K) {
        boolean taglockSaved = tryBindTaglockFromBed(itemstack, player, world, clickedBedLocation);
        if ((!taglockSaved) && (block != Blocks.field_150324_C)) {
          if (world.func_147439_a(x + 1, y, z) == block) {
            taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x + 1, y, z));
          }
          if ((!taglockSaved) && (world.func_147439_a(x, y, z + 1) == block)) {
            taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z + 1));
          }
          if ((!taglockSaved) && (world.func_147439_a(x - 1, y, z) == block)) {
            taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x - 1, y, z));
          }
          if ((!taglockSaved) && (world.func_147439_a(x, y, z - 1) == block)) {
            taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z - 1));
          }
        }
        
        if (taglockSaved) {
          return !field_72995_K;
        }
      }
      

      if (!field_72995_K) {
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      
      return !field_72995_K; }
    if (block == BlocksLEECH_CHEST) {
      if (!field_72995_K) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if ((tile != null) && ((tile instanceof BlockLeechChest.TileEntityLeechChest))) {
          BlockLeechChest.TileEntityLeechChest chest = (BlockLeechChest.TileEntityLeechChest)tile;
          String username = chest.popUserExcept(player);
          if ((username != null) && (!username.isEmpty())) {
            setTaglockForEntity(world, player, itemstack, username);
            return !field_72995_K;
          }
        }
        
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      
      return !field_72995_K; }
    if (block == BlocksBLOOD_ROSE) {
      if (!field_72995_K) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if ((tile != null) && ((tile instanceof BlockBloodRose.TileEntityBloodRose))) {
          BlockBloodRose.TileEntityBloodRose chest = (BlockBloodRose.TileEntityBloodRose)tile;
          String username = chest.popUserExcept(player, false);
          if ((username != null) && (!username.isEmpty())) {
            setTaglockForEntity(world, player, itemstack, username);
            return !field_72995_K;
          }
        }
        
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      
      return !field_72995_K; }
    if (block == BlocksCRYSTAL_BALL) {
      if (itemstack.func_77960_j() > 0) {
        if ((!field_72995_K) && (com.emoniph.witchery.blocks.BlockCrystalBall.tryConsumePower(world, player, x, y, z))) {
          player.func_71008_a(itemstack, func_77626_a(itemstack));
        } else if (field_72995_K) {
          player.func_71008_a(itemstack, func_77626_a(itemstack));
        }
      } else {
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      return !field_72995_K;
    }
    
    return super.onItemUseFirst(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
  }
  

  private boolean tryBindTaglockFromBed(ItemStack itemstack, EntityPlayer player, World world, ChunkCoordinates clickedBedLocation)
  {
    String boundName = "";
    EntityLivingBase boundEntity = getBoundEntity(world, player, itemstack, Integer.valueOf(1));
    if ((boundEntity != null) && ((boundEntity instanceof EntityPlayer))) {
      boundName = ((EntityPlayer)boundEntity).func_70005_c_();
    }
    

    ArrayList<EntityPlayer> players = new ArrayList();
    
    for (Object obj : field_73010_i) {
      EntityPlayer worldPlayer = (EntityPlayer)obj;
      ChunkCoordinates playerBedLocation = worldPlayer.getBedLocation(field_71093_bK);
      if ((playerBedLocation != null) && (playerBedLocation.equals(clickedBedLocation))) {
        players.add(worldPlayer);
      }
    }
    

    java.util.Collections.sort(players, new PlayerComparitor());
    
    boolean taglockSaved = false;
    

    if (players.size() > 0) {
      if (boundName.isEmpty()) {
        taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
      } else {
        boolean found = false;
        for (int i = 0; (i < players.size()) && (!found); i++) {
          if (((EntityPlayer)players.get(i)).func_70005_c_().equals(boundName)) {
            if (i == players.size() - 1) {
              taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
            } else {
              taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(i + 1));
            }
            found = true;
          }
        }
        if (!found) {
          taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
        }
      }
    }
    return taglockSaved;
  }
  
  public static boolean isTaglockRestricted(EntityPlayer collector, EntityLivingBase target) {
    if ((!(target instanceof EntityPlayer)) || (collector.equals(target))) {
      return false;
    }
    if ((instancerestrictTaglockCollectionOnNonPVPServers) && (!MinecraftServer.func_71276_C().func_71219_W())) {
      return true;
    }
    EntityPlayer targetPlayer = (EntityPlayer)target;
    if ((instancerestrictTaglockCollectionForStaffMembers) && (MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(targetPlayer.func_146103_bH()))) {
      return true;
    }
    return false;
  }
  
  private boolean setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, EntityPlayer victim) {
    if (!isTaglockRestricted(player, victim)) {
      setTaglockForEntity(world, player, itemstack, victim.func_70005_c_());
      return true;
    }
    return false;
  }
  
  private void setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, String victimUsername)
  {
    if (!field_72995_K) {
      if (field_77994_a > 1) {
        ItemStack newStack = new ItemStack(this, 1, 1);
        setTaglockForEntity(newStack, player, victimUsername, true, Integer.valueOf(1));
        
        field_77994_a -= 1;
        if (field_77994_a <= 0) {
          field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
        }
        if (!field_71071_by.func_70441_a(newStack)) {
          world.func_72838_d(new EntityItem(world, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
        } else if ((player instanceof EntityPlayerMP)) {
          ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
        }
      } else {
        setTaglockForEntity(itemstack, player, victimUsername, true, Integer.valueOf(1));
        itemstack.func_77964_b(1);
        if ((player instanceof EntityPlayerMP)) {
          ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
        }
      }
    }
  }
  
  public void onEntityInteract(World world, EntityPlayer player, ItemStack stack, EntityInteractEvent event) {
    if ((!field_72995_K) && 
      (stack != null) && (stack.func_77973_b() == ItemsTAGLOCK_KIT) && (target != null) && ((target instanceof EntityLivingBase)))
    {
      EntityLivingBase target = (EntityLivingBase)target;
      if ((!(target instanceof EntityPlayer)) || (isSneakSuccessful(player, target))) {
        if ((!(target instanceof EntityTreefyd)) && (!(target instanceof com.emoniph.witchery.entity.EntityImp)) && ((!(target instanceof EntityWingedMonkey)) || (player.func_70093_af()))) {
          if (field_77994_a > 1) {
            ItemStack newStack = new ItemStack(ItemsTAGLOCK_KIT, 1, 1);
            ItemsTAGLOCK_KIT.setTaglockForEntity(newStack, player, target, true, Integer.valueOf(1));
            field_77994_a -= 1;
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
            }
            if (!field_71071_by.func_70441_a(newStack)) {
              world.func_72838_d(new EntityItem(world, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
            }
            else if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          } else {
            ItemsTAGLOCK_KIT.setTaglockForEntity(stack, player, target, true, Integer.valueOf(1));
            stack.func_77964_b(1);
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
        }
      }
      else
      {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, entityPlayer, "witchery.taglockkit.taglockfailed", new Object[0]);
        
        if ((target instanceof EntityPlayer)) {
          ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (EntityPlayer)target, "witchery.taglockkit.taglockdiscovered", new Object[0]);
        }
      }
      

      event.setCanceled(true);
    }
  }
  

  private boolean isSneakSuccessful(EntityPlayer sneaker, EntityLivingBase target)
  {
    if (isTaglockRestricted(sneaker, target)) {
      return false;
    }
    
    double sneakerFacing = (field_70759_as + 90.0F) % 360.0F;
    if (sneakerFacing < 0.0D) {
      sneakerFacing += 360.0D;
    }
    
    double targetFacing = (field_70759_as + 90.0F) % 360.0F;
    if (targetFacing < 0.0D) {
      targetFacing += 360.0D;
    }
    
    double ARC = 45.0D;
    double diff = Math.abs(targetFacing - sneakerFacing);
    


    double chance = 0.0D;
    
    if ((360.0D - diff % 360.0D < 45.0D) || (diff % 360.0D < 45.0D)) {
      chance = sneaker.func_70093_af() ? 0.6D : 0.3D;
    } else {
      chance = sneaker.func_70093_af() ? 0.1D : 0.01D;
      if (sneaker.func_82150_aj()) {
        chance += 0.1D;
      }
    }
    return field_70170_p.field_73012_v.nextDouble() < chance;
  }
  





  public void setTaglockForEntity(ItemStack stack, EntityPlayer player, Entity entity, boolean playSoundAtPlayer, Integer index)
  {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer hitPlayer = (EntityPlayer)entity;
      stack.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), hitPlayer.func_70005_c_());
      stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
      stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
    } else if ((entity instanceof EntityLiving)) {
      stack.func_77978_p().func_82580_o("WITCPlayer" + index.toString());
      UUID id = entity.getPersistentID();
      ((EntityLiving)entity).func_110163_bv();
      

      stack.func_77978_p().func_74772_a("WITCEntityIDm" + index.toString(), id.getMostSignificantBits());
      stack.func_77978_p().func_74772_a("WITCEntityIDl" + index.toString(), id.getLeastSignificantBits());
      stack.func_77978_p().func_74778_a("WITCDisplayName" + index.toString(), entity.func_70005_c_());
    } else {
      return;
    }
    
    if (playSoundAtPlayer) {
      field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
    }
  }
  
  public void clearTaglock(ItemStack stack, Integer index) {
    if (stack != null) {
      stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
      stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
      stack.func_77978_p().func_82580_o("WITCPlayer" + index.toString());
      stack.func_77978_p().func_82580_o("WITCDisplayName" + index.toString());
    }
  }
  
  public void setTaglockForEntity(ItemStack stack, EntityPlayer player, String username, boolean playSoundAtPlayer, Integer index)
  {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    
    if ((username != null) && (!username.isEmpty())) {
      stack.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), username);
      stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
      stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
    } else {
      return;
    }
    
    if (playSoundAtPlayer) {
      field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
    }
  }
  
  public boolean isTaglockPresent(ItemStack itemStack, Integer index) {
    if (itemStack.func_77942_o()) {
      if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
        String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
        if ((playerName != null) && (!playerName.isEmpty())) {
          return true;
        }
      }
      if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())))
      {
        return true;
      }
    }
    return false;
  }
  
  public String getBoundEntityDisplayName(ItemStack itemStack, Integer index) {
    if (itemStack.func_77942_o()) {
      if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
        String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
        return playerName; }
      if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
      {

        String displayName = itemStack.func_77978_p().func_74779_i("WITCDisplayName" + index.toString());
        return displayName;
      }
    }
    return "";
  }
  
  public static enum BoundType {
    NONE,  PLAYER,  CREATURE;
    
    private BoundType() {} }
  
  public BoundType getBoundEntityType(ItemStack itemStack, Integer index) { if (itemStack.func_77942_o()) {
      if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString()))
        return BoundType.PLAYER;
      if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
      {

        return BoundType.CREATURE;
      }
    }
    return BoundType.NONE;
  }
  
  public String getBoundUsername(ItemStack itemStack, Integer index) {
    if ((itemStack.func_77942_o()) && 
      (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString()))) {
      String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
      return playerName;
    }
    
    return "";
  }
  
  public UUID getBoundCreatureID(ItemStack itemStack, Integer index) {
    if ((itemStack.func_77942_o()) && 
      (itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
    {


      String displayName = itemStack.func_77978_p().func_74779_i("WITCDisplayName" + index.toString());
      UUID entityID = new UUID(itemStack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), itemStack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
      
      return entityID;
    }
    
    return new UUID(0L, 0L);
  }
  
  public void addTagLockToPoppet(ItemStack stackTaglockKit, ItemStack stackPoppet, Integer index) {
    assert (stackTaglockKit != null) : "stack of taglock kit cannot be null";
    assert (stackPoppet != null) : "stack poppet cannot be null";
    
    Integer tagLockIndex = Integer.valueOf(1);
    
    if (!stackPoppet.func_77942_o()) {
      stackPoppet.func_77982_d(new NBTTagCompound());
    }
    
    if (stackTaglockKit.func_77942_o()) {
      if (stackTaglockKit.func_77978_p().func_74764_b("WITCPlayer" + tagLockIndex.toString())) {
        String playerName = stackTaglockKit.func_77978_p().func_74779_i("WITCPlayer" + tagLockIndex.toString());
        if (playerName != null) {
          stackPoppet.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), playerName);
        }
      } else if ((stackTaglockKit.func_77978_p().func_74764_b("WITCEntityIDm" + tagLockIndex.toString())) && (stackTaglockKit.func_77978_p().func_74764_b("WITCEntityIDl" + tagLockIndex.toString())) && (stackTaglockKit.func_77978_p().func_74764_b("WITCDisplayName" + tagLockIndex.toString())))
      {

        stackPoppet.func_77978_p().func_74772_a("WITCEntityIDm" + index.toString(), stackTaglockKit.func_77978_p().func_74763_f("WITCEntityIDm" + tagLockIndex.toString()));
        
        stackPoppet.func_77978_p().func_74772_a("WITCEntityIDl" + index.toString(), stackTaglockKit.func_77978_p().func_74763_f("WITCEntityIDl" + tagLockIndex.toString()));
        
        stackPoppet.func_77978_p().func_74778_a("WITCDisplayName" + index.toString(), stackTaglockKit.func_77978_p().func_74779_i("WITCDisplayName" + tagLockIndex.toString()));
      }
    }
  }
  

  public boolean containsTaglockForEntity(ItemStack itemStack, Entity entity, Integer index)
  {
    if (itemStack.func_77942_o()) {
      if ((entity instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entity;
        if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
          String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
          if ((playerName != null) && (playerName.equals(player.func_70005_c_()))) {
            return true;
          }
        }
      } else if (((entity instanceof EntityLiving)) && 
        (itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())))
      {
        UUID entityID = new UUID(itemStack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), itemStack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
        
        if (entityID.equals(entity.getPersistentID())) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  public EntityLivingBase getBoundEntity(World world, Entity entity, ItemStack stack, Integer index) {
    if (stack.func_77942_o()) {
      if (stack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
        String playerName = stack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
        if ((playerName != null) && (!playerName.isEmpty())) {
          if (!field_72995_K) {
            MinecraftServer server = MinecraftServer.func_71276_C();
            for (WorldServer worldServer : field_71305_c) {
              for (Object obj : field_73010_i) {
                EntityPlayer player = (EntityPlayer)obj;
                if (player.func_70005_c_().equalsIgnoreCase(playerName)) {
                  return player;
                }
              }
            }
          } else {
            for (Object obj : field_73010_i) {
              EntityPlayer player = (EntityPlayer)obj;
              if (player.func_70005_c_().equalsIgnoreCase(playerName)) {
                return player;
              }
            }
          }
          return null;
        }
      }
      if ((stack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (stack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString()))) {
        UUID entityID = new UUID(stack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), stack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
        
        if (!field_72995_K) {
          MinecraftServer server = MinecraftServer.func_71276_C();
          for (WorldServer worldServer : field_71305_c) {
            for (Object obj : field_72996_f) {
              if ((obj instanceof EntityLivingBase)) {
                EntityLivingBase living = (EntityLivingBase)obj;
                UUID livingID = living.getPersistentID();
                if ((entityID.equals(livingID)) && (living.func_70089_S())) {
                  return living;
                }
              }
            }
          }
        } else {
          for (Object obj : field_72996_f) {
            if ((obj instanceof EntityLivingBase)) {
              EntityLivingBase living = (EntityLivingBase)obj;
              UUID livingID = living.getPersistentID();
              if ((entityID.equals(livingID)) && (living.func_70089_S())) {
                return living;
              }
            }
          }
        }
        return null;
      }
    }
    
    return null;
  }
}
