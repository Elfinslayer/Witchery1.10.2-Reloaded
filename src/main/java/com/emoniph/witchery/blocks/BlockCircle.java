package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.ritual.Circle;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockCircle extends BlockBaseContainer
{
  public BlockCircle()
  {
    super(Material.field_151582_l, TileEntityCircle.class);
    registerWithCreateTab = false;
    
    func_149711_c(3.0F);
    func_149752_b(1000.0F);
    float f1 = 0.015625F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2)
  {
    return field_149761_L;
  }
  
  public void func_149699_a(World world, int posX, int posY, int posZ, EntityPlayer player)
  {
    if (!field_72995_K) {
      ItemStack itemstack = player.func_70694_bm();
      if ((itemstack != null) && ((ItemsGENERIC.itemBroom.isMatch(itemstack)) || (ItemsGENERIC.itemBroomEnchanted.isMatch(itemstack)))) {
        world.func_147480_a(posX, posY, posZ, false);
      }
    }
  }
  
  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
  {
    return null;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149745_a(Random rand)
  {
    return 0;
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return new ItemStack(ItemsCHALK_GOLDEN);
  }
  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    if (func_111046_k(par1World, par2, par3, par4)) {
      boolean flag = par1World.func_72864_z(par2, par3, par4);
      TileEntityCircle tileCircle = (TileEntityCircle)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityCircle.class);
      
      if ((tileCircle != null) && (previousRedstoneState != flag)) {
        if (flag) {
          activateBlock(par1World, par2, par3, par4, null, false);
        }
        
        previousRedstoneState = flag;
      }
    }
  }
  
  private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
    if (!func_149718_j(par1World, par2, par3, par4)) {
      par1World.func_147468_f(par2, par3, par4);
      return false;
    }
    return true;
  }
  

  public boolean func_149718_j(World world, int x, int y, int z)
  {
    Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
    return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return par5 == 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    int metadata = world.func_72805_g(x, y, z);
    if (metadata == 1) {
      double d0 = x + 0.4F + rand.nextFloat() * 0.2F;
      double d1 = y + 0.1F + rand.nextFloat() * 0.3F;
      double d2 = z + 0.4F + rand.nextFloat() * 0.2F;
      world.func_72869_a(ParticleEffect.REDDUST.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    ItemStack stack = player.func_70694_bm();
    activateBlock(world, x, y, z, player, (stack != null) && (ItemsGENERIC.itemSeerStone.isMatch(stack)));
    return true;
  }
  
  private void activateBlock(World world, int posX, int posY, int posZ, EntityPlayer player, boolean summonCoven) {
    TileEntityCircle tileEntity = (TileEntityCircle)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityCircle.class);
    if (tileEntity == null) {
      return;
    }
    
    if (tileEntity.isRitualActive()) {
      tileEntity.deactivate();
      return;
    }
    
    if (field_72995_K) {
      return;
    }
    
    if ((PowerSources.instance().isAreaNulled(world, posX, posY, posZ)) || (field_73011_w.field_76574_g == instancedimensionDreamID)) {
      ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.nullfield", new Object[0]);
      SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      return;
    }
    
    Circle a = new Circle(16);
    Circle b = new Circle(28);
    Circle c = new Circle(40);
    Circle _ = new Circle(0);
    
    Circle[][] PATTERN = { { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ } };
    

















    int offsetZ = (PATTERN.length - 1) / 2;
    for (int z = 0; z < PATTERN.length - 1; z++) {
      int worldZ = posZ - offsetZ + z;
      int offsetX = (PATTERN[z].length - 1) / 2;
      for (int x = 0; x < PATTERN[z].length; x++) {
        int worldX = posX - offsetX + x;
        PATTERN[(PATTERN.length - 1 - z)][x].addGlyph(world, worldX, posY, worldZ);
      }
    }
    
    boolean isDaytime = world.func_72935_r();
    boolean isRainPossible = world.func_72807_a(posX, posZ).func_76738_d();
    boolean isRaining = (world.func_72896_J()) && (isRainPossible);
    boolean isThundering = world.func_72911_I();
    
    int maxRadius = PATTERN.length / 2;
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - maxRadius, posY, posZ - maxRadius, posX + maxRadius, posY + 1, posZ + maxRadius);
    ArrayList<Entity> entities = new ArrayList();
    for (Object obj : world.func_72872_a(Entity.class, bounds)) {
      Entity item = (Entity)obj;
      entities.add(item);
    }
    
    ArrayList<ItemStack> grassperStacks = new ArrayList();
    
    int radius = 5;
    for (int x = posX - 5; x <= posX + 5; x++) {
      for (int z = posZ - 5; z <= posZ + 5; z++) {
        Block block = world.func_147439_a(x, posY, z);
        if (block == BlocksGRASSPER) {
          TileEntity tile = world.func_147438_o(x, posY, z);
          if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
            BlockGrassper.TileEntityGrassper grassper = (BlockGrassper.TileEntityGrassper)tile;
            
            ItemStack stack = grassper.func_70301_a(0);
            if (stack != null) {
              grassperStacks.add(stack);
            }
          }
        }
      }
    }
    
    Circle[] circles = { a, b, c };
    boolean ritualFound = false;
    int covenSize = summonCoven ? EntityCovenWitch.getCovenSize(player) : 0;
    for (RiteRegistry.Ritual ritual : RiteRegistry.instance().getRituals()) {
      if (ritual.isMatch(world, posX, posY, posZ, circles, entities, grassperStacks, isDaytime, isRaining, isThundering))
      {
        tileEntity.queueRitual(ritual, bounds, player, covenSize, summonCoven);
        summonCoven = false;
        ritualFound = true;
      }
    }
    
    if ((!ritualFound) && (!field_72995_K)) {
      RiteRegistry.RiteError("witchery.rite.unknownritual", player, world);
      SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
    }
  }
  
  public static class TileEntityCircle extends TileEntityBase
  {
    public boolean previousRedstoneState;
    private final ArrayList<ActivatedRitual> activeRituals;
    private final ArrayList<ActivatedRitual> upkeepRituals;
    private boolean abortNext;
    
    public void func_145841_b(NBTTagCompound nbtTag) {
      super.func_145841_b(nbtTag);
      byte[] ritualIDs = new byte[upkeepRituals.size()];
      byte[] stages = new byte[upkeepRituals.size()];
      byte[] covenSizes = new byte[upkeepRituals.size()];
      NBTTagList nbtList = new NBTTagList();
      NBTTagList nbtLocationList = new NBTTagList();
      for (int i = 0; i < upkeepRituals.size(); i++) {
        ritualIDs[i] = upkeepRituals.get(i)).ritual.getRitualID();
        stages[i] = ((byte)((ActivatedRitual)upkeepRituals.get(i)).getCurrentStage());
        covenSizes[i] = ((byte)upkeepRituals.get(i)).covenSize);
        nbtList.func_74742_a(new NBTTagString(((ActivatedRitual)upkeepRituals.get(i)).getInitiatingPlayerName()));
        nbtLocationList.func_74742_a(((ActivatedRitual)upkeepRituals.get(i)).getLocationTag());
      }
      nbtTag.func_74773_a("Rituals", ritualIDs);
      nbtTag.func_74773_a("RitualStages", stages);
      nbtTag.func_74782_a("Initiators", nbtList);
      nbtTag.func_74782_a("Locations", nbtLocationList);
      nbtTag.func_74773_a("RitualCovens", covenSizes);
    }
    

    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      if ((nbtTag.func_74764_b("Rituals")) && (nbtTag.func_74764_b("RitualStages"))) {
        byte[] stages = nbtTag.func_74770_j("RitualStages");
        byte[] ritualIDs = nbtTag.func_74770_j("Rituals");
        
        Coord[] locations = new Coord[stages.length];
        if (nbtTag.func_74764_b("Locations")) {
          NBTTagList list = nbtTag.func_150295_c("Locations", 10);
          for (int i = 0; i < Math.min(list.func_74745_c(), locations.length); i++) {
            NBTTagCompound nbtListItem = list.func_150305_b(i);
            locations[i] = Coord.fromTagNBT(nbtListItem);
          }
        }
        
        String[] initators = new String[stages.length];
        if (nbtTag.func_74764_b("Initiators")) {
          NBTTagList list = nbtTag.func_150295_c("Initiators", 8);
          for (int i = 0; i < Math.min(list.func_74745_c(), initators.length); i++) {
            String nbtListItem = list.func_150307_f(i);
            initators[i] = ((nbtListItem != null) && (!nbtListItem.isEmpty()) ? nbtListItem : null);
          }
        }
        
        byte[] covenSizes = nbtTag.func_74764_b("RitualCovens") ? nbtTag.func_74770_j("RitualCovens") : null;
        
        for (int i = 0; i < ritualIDs.length; i++) {
          RiteRegistry.Ritual ritual = RiteRegistry.instance().getRitual(ritualIDs[i]);
          if (ritual != null) {
            ArrayList<RitualStep> ritualSteps = new ArrayList();
            ritual.addRiteSteps(ritualSteps, stages[i]);
            if (!ritualSteps.isEmpty()) {
              ActivatedRitual activatedRitual = new ActivatedRitual(ritual, ritualSteps, initators[i], covenSizes != null ? covenSizes[i] : 0, null);
              activatedRitual.setLocation(locations[i]);
              upkeepRituals.add(activatedRitual);
            }
          }
        }
      }
    }
    
    public void func_145845_h()
    {
      super.func_145845_h();
      
      if (!field_145850_b.field_72995_K) {
        if (!upkeepRituals.isEmpty()) {
          for (ActivatedRitual upkeepRitual : upkeepRituals) {
            RitualStep.Result result = ((RitualStep)steps.get(0)).run(field_145850_b, field_145851_c, field_145848_d, field_145849_e, ticks, upkeepRitual);
            
            if ((result != RitualStep.Result.UPKEEP) && (Config.instance().traceRites())) {
              Log.instance().traceRite(String.format(" - Upkeep ritual=%s, step=%s, result=%s", new Object[] { ritual.getUnlocalizedName(), ((RitualStep)steps.get(0)).toString(), result.toString() }));
            }
            
            switch (BlockCircle.1.$SwitchMap$com$emoniph$witchery$ritual$RitualStep$Result[result.ordinal()]) {
            case 1: 
              steps.clear();
              break;
            case 2: 
            case 3: 
              steps.clear();
              SoundEffect.NOTE_SNARE.playAt(this);
            }
            
          }
          


          for (int i = upkeepRituals.size() - 1; i >= 0; i--) {
            if (upkeepRituals.get(i)).steps.isEmpty()) {
              upkeepRituals.remove(i);
            }
          }
        }
        
        if (!activeRituals.isEmpty()) {
          ActivatedRitual ritual = (ActivatedRitual)activeRituals.get(0);
          RitualStep.Result result = ((RitualStep)steps.get(0)).run(field_145850_b, field_145851_c, field_145848_d, field_145849_e, ticks, ritual);
          ritual.postProcess(field_145850_b);
          if (abortNext) {
            abortNext = false;
            result = RitualStep.Result.ABORTED_REFUND;
            activeRituals.clear();
          }
          
          if ((result != RitualStep.Result.STARTING) && (Config.instance().traceRites())) {
            Log.instance().traceRite(String.format("Active ritual=%s, step=%s, result=%s", new Object[] { ritual.getUnlocalizedName(), ((RitualStep)steps.get(0)).toString(), result.toString() }));
          }
          
          switch (BlockCircle.1.$SwitchMap$com$emoniph$witchery$ritual$RitualStep$Result[result.ordinal()]) {
          case 4: 
            if (activeRituals.size() > 0) {
              activeRituals.remove(0);
            }
            upkeepRituals.add(ritual);
            break;
          case 1: 
            if (steps.size() > 0) {
              steps.remove(0);
            }
            if (steps.isEmpty()) {
              activeRituals.remove(0);
            }
            break;
          case 2: 
          case 3: 
            if (activeRituals.size() > 0) {
              activeRituals.remove(0);
            }
            if (!field_145850_b.field_72995_K) {
              SoundEffect.NOTE_SNARE.playAt(this);
              if (result == RitualStep.Result.ABORTED_REFUND)
                for (RitualStep.SacrificedItem sacrificedItem : sacrificedItems)
                  field_145850_b.func_72838_d(new EntityItem(field_145850_b, 0.5D + location.x, 0.5D + location.y, 0.5D + location.z, itemstack));
            }
            break;
          }
          
        }
        




        if ((!isRitualActive()) && (func_145832_p() != 0)) {
          field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, 0, 3);
        }
      }
    }
    
    public TileEntityCircle()
    {
      activeRituals = new ArrayList();
      upkeepRituals = new ArrayList();
      


























































































































































      abortNext = false; }
    
    public void deactivate() { if (!field_145850_b.field_72995_K)
      {
        if (activeRituals.size() > 0) {
          abortNext = true;
        }
        upkeepRituals.clear();
        field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, 0, 3);
        SoundEffect.NOTE_SNARE.playAt(this);
      }
    }
    
    public boolean isRitualActive() {
      return (!activeRituals.isEmpty()) || (!upkeepRituals.isEmpty());
    }
    
    public static class ActivatedRitual {
      private final RiteRegistry.Ritual ritual;
      private final ArrayList<RitualStep> steps;
      public final String playerName;
      public final ArrayList<RitualStep.SacrificedItem> sacrificedItems = new ArrayList();
      public final int covenSize;
      private Coord coord;
      
      private ActivatedRitual(RiteRegistry.Ritual ritual, ArrayList<RitualStep> steps, String playerName, int covenSize) {
        this.ritual = ritual;
        this.steps = steps;
        this.playerName = playerName;
        this.covenSize = covenSize;
      }
      
      public Coord getLocation() {
        return coord;
      }
      
      public NBTTagCompound getLocationTag() {
        return coord != null ? coord.toTagNBT() : new NBTTagCompound();
      }
      
      public void setLocation(Coord coord) {
        this.coord = coord;
      }
      
      public String getInitiatingPlayerName() {
        return playerName != null ? playerName : "";
      }
      
      public EntityPlayer getInitiatingPlayer(World world) {
        return world.func_72924_a(getInitiatingPlayerName());
      }
      
      public void postProcess(World world)
      {
        for (int i = 0; i < sacrificedItems.size(); i++) {
          RitualStep.SacrificedItem sacrificedItem = (RitualStep.SacrificedItem)sacrificedItems.get(i);
          if ((sacrificedItem != null) && (itemstack != null)) {
            if ((!ritual.isConsumeAttunedStoneCharged()) && (ItemsGENERIC.itemAttunedStoneCharged.isMatch(itemstack))) {
              world.func_72838_d(new EntityItem(world, 0.5D + location.x, 0.5D + location.y, 0.5D + location.z, ItemsGENERIC.itemAttunedStone.createStack()));
              
              sacrificedItems.remove(i);
              break; }
            if (itemstack.func_77973_b() == ItemsARTHANA) {
              world.func_72838_d(new EntityItem(world, 0.5D + location.x, 0.5D + location.y, 0.5D + location.z, itemstack));
              
              sacrificedItems.remove(i);
              break; }
            if (itemstack.func_77973_b() == ItemsBOLINE) {
              world.func_72838_d(new EntityItem(world, 0.5D + location.x, 0.5D + location.y, 0.5D + location.z, itemstack));
              
              sacrificedItems.remove(i);
              break; }
            if ((!ritual.isConsumeNecroStone()) && (ItemsGENERIC.itemNecroStone.isMatch(itemstack))) {
              world.func_72838_d(new EntityItem(world, 0.5D + location.x, 0.5D + location.y, 0.5D + location.z, itemstack));
              
              sacrificedItems.remove(i);
              break;
            }
          }
        }
      }
      
      public int getCurrentStage()
      {
        if (!steps.isEmpty()) {
          return ((RitualStep)steps.get(0)).getCurrentStage();
        }
        return 0;
      }
    }
    
    public void queueRitual(RiteRegistry.Ritual ritual, AxisAlignedBB bounds, EntityPlayer player, int covenSize, boolean summonCoven)
    {
      ArrayList<RitualStep> ritualSteps = new ArrayList();
      if (summonCoven) {
        EntityCovenWitch.summonCoven(ritualSteps, field_70170_p, player, new int[][] { { field_145851_c - 2, field_145848_d, field_145849_e - 2 }, { field_145851_c + 2, field_145848_d, field_145849_e - 2 }, { field_145851_c - 2, field_145848_d, field_145849_e + 2 }, { field_145851_c + 2, field_145848_d, field_145849_e + 2 }, { field_145851_c, field_145848_d, field_145849_e + 3 }, { field_145851_c, field_145848_d, field_145849_e - 3 } });
      }
      





      ritual.addSteps(ritualSteps, bounds);
      if ((ritualSteps.size() > 0) && (!field_145850_b.field_72995_K)) {
        activeRituals.add(new ActivatedRitual(ritual, ritualSteps, player != null ? player.func_70005_c_() : null, covenSize, null));
        field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, 1, 3);
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
