package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityIllusion;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.infusion.infusions.spirit.IFetishTile;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.item.ItemTaglockKit.BoundType;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class BlockFetish extends BlockBaseContainer
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
      String effect = InfusedSpiritEffect.getEffectDisplayName(stack);
      if (effect != null) {
        return s + " (" + effect + ")";
      }
      return s;
    }
  }
  
  public BlockFetish()
  {
    super(Material.field_151575_d, TileEntityFetish.class, ClassItemBlock.class);
    func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
    func_149752_b(100000.0F);
    func_149711_c(3.5F);
    func_149672_a(field_149766_f);
  }
  
  public void func_149666_a(Item item, CreativeTabs tabs, List list)
  {
    super.func_149666_a(item, tabs, list);
    if (Item.func_150898_a(BlocksFETISH_SCARECROW) == item) {
      list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.POPPET_ENHANCEMENT));
    }
    
    if ((Item.func_150898_a(BlocksFETISH_SCARECROW) == item) || (Item.func_150898_a(BlocksFETISH_WITCHS_LADDER) == item)) {
      list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SCREAMER));
    }
    
    if (Item.func_150898_a(BlocksFETISH_SCARECROW) == item) {
      list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SENTINAL));
    }
    
    if (Item.func_150898_a(BlocksFETISH_SCARECROW) == item) {
      list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.TWISTER));
    }
    
    if (Item.func_150898_a(BlocksFETISH_SCARECROW) == item) {
      list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.GHOST_WALKER));
    }
  }
  
  public void func_149726_b(World world, int posX, int posY, int posZ)
  {
    super.func_149726_b(world, posX, posY, posZ);
    BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
  }
  
  public AxisAlignedBB func_149668_a(World world, int posX, int posY, int posZ)
  {
    TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
    if ((this == BlocksFETISH_WITCHS_LADDER) || ((tile != null) && (tile.isSpectral()))) {
      return null;
    }
    return super.func_149668_a(world, posX, posY, posZ);
  }
  

  public float func_149712_f(World world, int posX, int posY, int posZ)
  {
    TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
    if ((tile == null) || (!tile.isSpectral())) {
      return super.func_149712_f(world, posX, posY, posZ);
    }
    return -1.0F;
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
    
    if (stack != null) {
      TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
      if (tile != null) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
        if ((nbtRoot != null) && (nbtRoot.func_74764_b("TileData"))) {
          NBTTagCompound nbtTileData = nbtRoot.func_74775_l("TileData");
          tile.readSubDataFromNBT(nbtTileData);
          if ((tile.getEffectType() == 0) && (InfusedSpiritEffect.getEffectID(stack) != 0)) {
            tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
          }
        }
      }
    }
    

    if ((!field_72995_K) && (field_73011_w.field_76574_g == instancedimensionDreamID)) {
      World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
      if ((overworld != null) && (overworld.func_147437_c(posX, posY, posZ))) {
        BlockUtil.setBlock(overworld, posX, posY, posZ, this);
        func_149689_a(overworld, posX, posY, posZ, player, stack);
        TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(overworld, posX, posY, posZ, TileEntityFetish.class);
        if (tile != null) {
          tile.setSpectral(true);
        }
      }
    }
  }
  
  public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer)
  {
    if (field_71075_bZ.field_75098_d) {
      par5 |= 0x8;
      world.func_72921_c(posX, posY, posZ, par5, 4);
    }
    
    func_149697_b(world, posX, posY, posZ, par5, 0);
    
    super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
    
    if ((!field_72995_K) && (field_73011_w.field_76574_g == instancedimensionDreamID)) {
      World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
      if ((overworld != null) && (BlockUtil.getBlock(overworld, posX, posY, posZ) == this)) {
        overworld.func_147468_f(posX, posY, posZ);
      }
    }
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> drops = new ArrayList();
    if ((metadata & 0x8) == 0) {
      TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, x, y, z, TileEntityFetish.class);
      if (tile != null) {
        ItemStack stack = new ItemStack(tile.func_145838_q());
        NBTTagCompound nbtRoot = new NBTTagCompound();
        stack.func_77982_d(nbtRoot);
        nbtRoot.func_74774_a("BlockColor", (byte)tile.getColor());
        InfusedSpiritEffect.setEffectID(stack, tile.getEffectType());
        
        NBTTagCompound nbtTileData = new NBTTagCompound();
        tile.writeSubDataToNBT(nbtTileData);
        nbtRoot.func_74782_a("TileData", nbtTileData);
        
        drops.add(stack);
      }
    }
    return drops;
  }
  
  public int func_149645_b()
  {
    return this == BlocksFETISH_WITCHS_LADDER ? 1 : super.func_149645_b();
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return this == BlocksFETISH_WITCHS_LADDER;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return null;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, x, y, z, TileEntityFetish.class);
    if ((tile != null) && (player != null)) {
      ItemStack stack = player.func_70694_bm();
      if (stack != null) {
        if (!tile.isSpectral()) {
          if (stack.func_77973_b() == Items.field_151100_aR) {
            int color = BlockColored.func_150032_b(stack.func_77960_j());
            tile.setColor(color);
            if (!field_71075_bZ.field_75098_d) {
              if (--field_77994_a == 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            }
            return true; }
          if (stack.func_77973_b() == Items.field_151131_as) {
            tile.clearBoundEntities(stack, player);
            SoundEffect.WATER_SWIM.playAtPlayer(world, player);
            return true; }
          if (stack.func_77973_b() == ItemsBOLINE) {
            tile.cycleBoundMode(player);
            return true;
          }
        }
        
        if (stack.func_77973_b() == ItemsTAGLOCK_KIT) {
          tile.setBoundEntity(stack, player, tile.isSpectral());
          return true;
        }
      }
    }
    return false;
  }
  
  public int func_149709_b(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side)
  {
    TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(par1IBlockAccess, posX, posY, posZ, TileEntityFetish.class);
    if (tile != null) {
      return tile.getPowerLevel();
    }
    return super.func_149709_b(par1IBlockAccess, posX, posY, posZ, side);
  }
  

  public int func_149748_c(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side)
  {
    return side == 1 ? func_149709_b(par1IBlockAccess, posX, posY, posZ, side) : 0;
  }
  
  public boolean func_149744_f()
  {
    return true;
  }
  
  public static class TileEntityFetish extends TileEntityBase implements IFetishTile
  {
    private BlockFetish.CreatureID testID = new BlockFetish.CreatureID(new UUID(0L, 0L), "");
    
    boolean lastRaiseAlarm;
    
    long lastActivationTime;
    final int TRIGGER_WHEN_PLAYER_NOT_IN_WHITELIST = 0;
    final int TRIGGER_WHEN_PLAYER_IN_BLACKLIST = 1;
    final int TRIGGER_WHEN_CREATURE_NOT_IN_WHITELIST = 2;
    final int TRIGGER_WHEN_NOT_ALL_CREATURES_FOUND = 3;
    final int TRIGGER_WHEN_SOME_CREATURES_NOT_FOUND = 4;
    final int TRIGGER_OFF = 5;
    
    int alarmMode = 5;
    private Block expectedBlock;
    
    public TileEntityFetish() {}
    
    public TileEntityFetish setExpectedBlock(Block block) { expectedBlock = block;
      return this;
    }
    
    public Block getExpectedBlock() {
      return expectedBlock;
    }
    
    public void func_145845_h()
    {
      super.func_145845_h();
      
      if ((!field_145850_b.field_72995_K) && (TimeUtil.secondsElapsed(1, ticks))) {
        InfusedSpiritEffect effect = InfusedSpiritEffect.getEffect(this);
        if ((effect != null) && (effect.getRadius() > 0.0D)) {
          boolean someFound = false;
          int found = 0;
          int someLeft = 0;
          HashSet<String> foundTypes = new HashSet();
          List entities = null;
          ArrayList<EntityLivingBase> foundEntities = new ArrayList();
          if (alarmMode != 5) {
            double RADIUS = effect.getRadius();
            double RADIUS_SQ = RADIUS * RADIUS;
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a(0.5D + field_145851_c - RADIUS, 0.5D + field_145848_d - RADIUS, 0.5D + field_145849_e - RADIUS, 0.5D + field_145851_c + RADIUS, 0.5D + field_145848_d + RADIUS, 0.5D + field_145849_e + RADIUS);
            
            if ((alarmMode == 0) || (alarmMode == 1)) {
              entities = field_145850_b.func_72872_a(EntityPlayer.class, bb);
            } else {
              entities = field_145850_b.func_72872_a(EntityLivingBase.class, bb);
            }
            someLeft = entities.size();
            for (Object obj : entities) {
              if ((obj instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer)obj;
                if ((knownPlayers != null) && (knownPlayers.contains(player.func_70005_c_()))) {
                  someFound = true;
                  found++;
                  someLeft--;
                  if (alarmMode == 1) {
                    foundEntities.add(player);
                  }
                } else if ((alarmMode == 2) || (alarmMode == 0)) {
                  foundEntities.add(player);
                }
              } else if (((obj instanceof EntityLiving)) && (!isIgnorableEntity((EntityLiving)obj))) {
                EntityLiving creature = (EntityLiving)obj;
                if ((knownCreatureTypes != null) && (knownCreatureTypes.contains(creature.func_70005_c_()))) {
                  someFound = true;
                  foundTypes.add(creature.func_70005_c_());
                  someLeft--;
                } else {
                  testID.id = creature.func_110124_au();
                  if ((knownCreatures != null) && (knownCreatures.contains(testID))) {
                    someFound = true;
                    found++;
                    someLeft--;
                  } else if (alarmMode == 2) {
                    foundEntities.add(creature);
                  }
                }
              }
            }
          }
          
          boolean raiseAlarm = false;
          switch (alarmMode) {
          case 0: 
          case 2: 
            raiseAlarm = someLeft > 0;
            break;
          case 1: 
            raiseAlarm = someFound;
            break;
          case 3: 
            raiseAlarm = (found != knownCreatures.size() + knownPlayers.size()) || (knownCreatureTypes.size() != foundTypes.size());
            break;
          case 4: 
            raiseAlarm = !someFound;
          }
          
          
          int cooldown = effect.getCooldownTicks();
          long currentTime = field_145850_b.func_82737_E();
          if (((cooldown == -1) || (currentTime > lastActivationTime + cooldown)) && 
            (effect.doUpdateEffect(this, raiseAlarm, foundEntities))) {
            lastActivationTime = currentTime;
          }
          

          if (lastRaiseAlarm != raiseAlarm) {
            lastRaiseAlarm = raiseAlarm;
            if (effect.isRedstoneSignaller()) {
              BlockUtil.notifyNeighborsOfBlockChange(field_145850_b, field_145851_c, field_145848_d, field_145849_e, func_145838_q());
            }
          }
        }
      }
    }
    
    private boolean isFamiliar(Entity entity) {
      if ((entity instanceof IFamiliar)) {
        IFamiliar familiar = (IFamiliar)entity;
        return familiar.isFamiliar();
      }
      return false;
    }
    
    private boolean isIgnorableEntity(EntityLiving entity) {
      return ((entity instanceof EntityCorpse)) || ((entity instanceof EntityIllusion)) || ((entity instanceof com.emoniph.witchery.entity.EntitySpirit)) || (isFamiliar(entity));
    }
    
    private boolean spectral;
    public boolean isSpectral()
    {
      return spectral;
    }
    
    public void setSpectral(boolean spectral) {
      this.spectral = spectral;
      if ((field_145850_b != null) && (!field_145850_b.field_72995_K)) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public int getPowerLevel() {
      InfusedSpiritEffect effect = InfusedSpiritEffect.getEffect(this);
      if ((effect != null) && (effect.isRedstoneSignaller())) {
        return lastRaiseAlarm ? 15 : 0;
      }
      return 0;
    }
    

    private int color = 0;
    
    public void setColor(int dyeColor) {
      color = dyeColor;
      if ((field_145850_b != null) && (!field_145850_b.field_72995_K)) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        syncSpectralEntities();
      }
    }
    
    public int getColor() {
      return color;
    }
    
    private int effectType = 0;
    
    public int getEffectType() {
      return effectType;
    }
    
    public void setEffectType(int effectID) {
      effectType = effectID;
      if ((field_145850_b != null) && (!field_145850_b.field_72995_K)) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public int getX() {
      return field_145851_c;
    }
    
    public int getY() {
      return field_145848_d;
    }
    
    public int getZ() {
      return field_145849_e;
    }
    
    public void syncSpectralEntities() {
      if ((field_145850_b != null) && (!field_145850_b.field_72995_K) && (field_145850_b.field_73011_w.field_76574_g == instancedimensionDreamID)) {
        World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
        if ((overworld != null) && (BlockUtil.getBlock(overworld, field_145851_c, field_145848_d, field_145849_e) == func_145838_q())) {
          TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(overworld, field_145851_c, field_145848_d, field_145849_e, TileEntityFetish.class);
          if (tile != null) {
            NBTTagCompound nbtOurData = new NBTTagCompound();
            writeSubDataToNBT(nbtOurData);
            tile.readSubDataFromNBT(nbtOurData);
          }
        }
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
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      lastActivationTime = nbtRoot.func_74763_f("LastActivation");
      if (nbtRoot.func_74764_b("Spectral")) {
        spectral = nbtRoot.func_74767_n("Spectral");
      }
      readSubDataFromNBT(nbtRoot);
    }
    
    public void readSubDataFromNBT(NBTTagCompound nbtRoot) {
      if (nbtRoot.func_74764_b("BlockColor")) {
        color = nbtRoot.func_74771_c("BlockColor");
      }
      
      if (nbtRoot.func_74764_b("EffectTypeID")) {
        effectType = nbtRoot.func_74762_e("EffectTypeID");
      }
      
      if (nbtRoot.func_74764_b("AlarmMode")) {
        alarmMode = nbtRoot.func_74762_e("AlarmMode");
      } else {
        alarmMode = 5;
      }
      
      if (nbtRoot.func_74764_b("KnownPlayers")) {
        NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
        knownPlayers = new ArrayList();
        for (int i = 0; i < nbtPlayers.func_74745_c(); i++) {
          NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
          String playerName = nbtKnownPlayer.func_74779_i("PlayerName");
          if ((playerName != null) && (!playerName.isEmpty())) {
            knownPlayers.add(playerName);
          }
        }
      }
      
      if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
        NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
        knownCreatureTypes = new ArrayList();
        for (int i = 0; i < nbtCreatureTypes.func_74745_c(); i++) {
          NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
          String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
          if ((typeName != null) && (!typeName.isEmpty())) {
            knownCreatureTypes.add(typeName);
          }
        }
      }
      
      if (nbtRoot.func_74764_b("KnownCreatures")) {
        NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
        knownCreatures = new ArrayList();
        for (int i = 0; i < nbtCreatures.func_74745_c(); i++) {
          NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
          String playerName = nbtKnownCreature.func_74779_i("PlayerName");
          long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
          long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
          String cname = nbtKnownCreature.func_74779_i("CreatureName");
          if ((uuidMost != 0L) || (uuidLeast != 0L)) {
            UUID creatureID = new UUID(uuidMost, uuidLeast);
            knownCreatures.add(new BlockFetish.CreatureID(creatureID, cname));
          }
        }
      }
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74772_a("LastActivation", lastActivationTime);
      nbtRoot.func_74757_a("Spectral", spectral);
      writeSubDataToNBT(nbtRoot);
    }
    
    public void writeSubDataToNBT(NBTTagCompound nbtRoot) {
      nbtRoot.func_74774_a("BlockColor", (byte)color);
      nbtRoot.func_74768_a("EffectTypeID", effectType);
      nbtRoot.func_74768_a("AlarmMode", alarmMode);
      if (knownPlayers.size() > 0) {
        NBTTagList nbtPlayers = new NBTTagList();
        for (String playerName : knownPlayers) {
          NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
          nbtKnownPlayer.func_74778_a("PlayerName", playerName);
          nbtPlayers.func_74742_a(nbtKnownPlayer);
        }
        nbtRoot.func_74782_a("KnownPlayers", nbtPlayers);
      }
      
      if (knownCreatureTypes.size() > 0) {
        NBTTagList nbtCreatureTypes = new NBTTagList();
        for (String typeName : knownCreatureTypes) {
          NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
          nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
          nbtCreatureTypes.func_74742_a(nbtKnownCreatureType);
        }
        nbtRoot.func_74782_a("KnownCreatureTypes", nbtCreatureTypes);
      }
      
      if (knownCreatures.size() > 0) {
        NBTTagList nbtCreatures = new NBTTagList();
        for (BlockFetish.CreatureID creatureID : knownCreatures) {
          NBTTagCompound nbtKnownCreature = new NBTTagCompound();
          nbtKnownCreature.func_74772_a("CreatureMost", id.getMostSignificantBits());
          nbtKnownCreature.func_74772_a("CreatureLeast", id.getLeastSignificantBits());
          nbtKnownCreature.func_74778_a("CreatureName", name);
          nbtCreatures.func_74742_a(nbtKnownCreature);
        }
        nbtRoot.func_74782_a("KnownCreatures", nbtCreatures);
      }
    }
    
    private static ArrayList<String> groupables = null;
    private ArrayList<String> knownPlayers = new ArrayList();
    private ArrayList<String> knownCreatureTypes = new ArrayList();
    private ArrayList<BlockFetish.CreatureID> knownCreatures = new ArrayList();
    
    private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
      if (groupables == null) {
        groupables = new ArrayList();
        addGroupableType(EntityVillager.class);
        addGroupableType(EntityGoblin.class);
        addGroupableType(net.minecraft.entity.passive.EntitySheep.class);
        addGroupableType(EntityCow.class);
        addGroupableType(net.minecraft.entity.passive.EntityMooshroom.class);
        addGroupableType(EntityChicken.class);
        addGroupableType(EntityPig.class);
        addGroupableType(EntityHorse.class);
        addGroupableType(EntityBat.class);
        addGroupableType(EntitySquid.class);
        addGroupableType(com.emoniph.witchery.entity.EntityCovenWitch.class);
      }
      return groupables.contains(creatureName);
    }
    
    public void setBoundEntity(ItemStack stack, EntityPlayer player, boolean readonly) {
      if ((!field_145850_b.field_72995_K) && 
        (stack != null)) {
        ItemTaglockKit.BoundType boundEntityType = ItemsTAGLOCK_KIT.getBoundEntityType(stack, Integer.valueOf(1));
        switch (BlockFetish.1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[boundEntityType.ordinal()]) {
        case 1: 
          if (!readonly) {
            String otherUsername = ItemsTAGLOCK_KIT.getBoundUsername(stack, Integer.valueOf(1));
            
            if (!knownPlayers.contains(otherUsername)) {
              knownPlayers.add(otherUsername);
            } else {
              knownPlayers.remove(otherUsername);
            }
            if (!field_71075_bZ.field_75098_d) if (--field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
            
            syncSpectralEntities();
          }
          showCurrentKnownEntities(player);
          break;
        case 2: 
          if (!readonly) {
            UUID otherCreature = ItemsTAGLOCK_KIT.getBoundCreatureID(stack, Integer.valueOf(1));
            String creatureName = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
            
            if (isGroupableCreature(otherCreature, creatureName)) {
              if (!knownCreatureTypes.contains(creatureName)) {
                knownCreatureTypes.add(creatureName);
              } else {
                knownCreatureTypes.remove(creatureName);
              }
            } else {
              BlockFetish.CreatureID creatureID = new BlockFetish.CreatureID(otherCreature, creatureName);
              if (!knownCreatures.contains(creatureID)) {
                knownCreatures.add(creatureID);
              } else {
                knownCreatures.remove(creatureID);
              }
            }
            
            if (!field_71075_bZ.field_75098_d) if (--field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
            
            syncSpectralEntities();
          }
          showCurrentKnownEntities(player);
          break;
        case 3: 
          showCurrentKnownEntities(player);
        }
        
      }
    }
    
    public void clearBoundEntities(ItemStack stack, EntityPlayer player)
    {
      if ((player != null) && (!field_70170_p.field_72995_K) && (stack != null)) {
        knownCreatureTypes.clear();
        knownCreatures.clear();
        knownPlayers.clear();
        
        if (!field_71075_bZ.field_75098_d) {
          field_71071_by.func_70299_a(field_71071_by.field_70461_c, new ItemStack(Items.field_151133_ar));
        }
        
        if ((player instanceof EntityPlayerMP)) {
          ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
        }
        
        syncSpectralEntities();
        
        showCurrentKnownEntities(player);
      }
    }
    
    public void cycleBoundMode(EntityPlayer player) {
      if (!field_145850_b.field_72995_K) {
        if (++alarmMode > 5) {
          alarmMode = 0;
        }
        
        syncSpectralEntities();
        
        showCurrentKnownEntities(player);
      }
    }
    
    private void addGroupableType(Class<? extends EntityLiving> className) {
      String name = (String)EntityList.field_75626_c.get(className);
      if (name != null) {
        String localName = net.minecraft.util.StatCollector.func_74838_a("entity." + name + ".name");
        groupables.add(localName);
      }
    }
    
    private void showCurrentKnownEntities(EntityPlayer player) {
      StringBuffer sb = new StringBuffer();
      
      for (String s : knownPlayers) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(s);
      }
      
      for (String s : knownCreatureTypes) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append("#");
        sb.append(s);
      }
      
      for (BlockFetish.CreatureID cid : knownCreatures) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(cid.toString());
      }
      
      String message = sb.toString();
      String key = "";
      switch (alarmMode) {
      case 0: 
        key = "tile.witchery.scarecrow.operation.playerwhitelist";
        break;
      case 1: 
        key = "tile.witchery.scarecrow.operation.playerblacklist";
        break;
      case 2: 
        key = "tile.witchery.scarecrow.operation.creaturewhitelist";
        break;
      case 3: 
        key = "tile.witchery.scarecrow.operation.allnotfound";
        break;
      case 4: 
        key = "tile.witchery.scarecrow.operation.onenotfound";
        break;
      case 5: 
        key = "tile.witchery.scarecrow.operation.off";
      }
      
      
      ChatUtil.sendTranslated(player, key, new Object[] { message });
    }
  }
  
  private static class CreatureID {
    UUID id;
    String name;
    
    public CreatureID(UUID id, String name) {
      this.id = id;
      this.name = name;
    }
    
    public boolean equals(Object obj)
    {
      if (obj == null) {
        return false;
      }
      
      if (obj == this) {
        return true;
      }
      
      if ((obj instanceof UUID)) {
        return id.equals((UUID)obj);
      }
      
      if (obj.getClass() == getClass()) {
        return id.equals(id);
      }
      
      return false;
    }
    
    public String toString()
    {
      return name;
    }
  }
}
