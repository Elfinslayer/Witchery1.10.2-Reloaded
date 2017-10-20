package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class RiteRegistry
{
  private static final RiteRegistry INSTANCE = new RiteRegistry();
  

  public static RiteRegistry instance() { return INSTANCE; }
  
  private RiteRegistry() {}
  
  public static class Ritual {
    private String unlocalizedName;
    public final Rite rite;
    
    public String getUnlocalizedName() { return unlocalizedName; }
    

    final Sacrifice initialSacrifice;
    
    final EnumSet<RitualTraits> traits;
    final Circle[] circles;
    final byte ritualID;
    final int bookIndex;
    boolean visibleInBook;
    public Ritual setUnlocalizedName(String unlocalizedName)
    {
      this.unlocalizedName = unlocalizedName;
      return this;
    }
    
    public String getLocalizedName() {
      if (unlocalizedName != null) {
        return Witchery.resource(getUnlocalizedName());
      }
      
      return toString();
    }
    








    Ritual(byte ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle[] circles)
    {
      this.ritualID = ritualID;
      this.bookIndex = bookIndex;
      this.rite = rite;
      this.initialSacrifice = initialSacrifice;
      this.traits = traits;
      this.circles = circles;
      visibleInBook = true;
    }
    
    public String getDescription() {
      StringBuffer sb = new StringBuffer();
      sb.append("§n");
      sb.append(getLocalizedName());
      sb.append("§r");
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Const.BOOK_NEWLINE);
      initialSacrifice.addDescription(sb);
      return sb.toString();
    }
    
    public boolean isMatch(World world, int posX, int posY, int posZ, Circle[] nearbyCircles, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks, boolean isDaytime, boolean isRaining, boolean isThundering)
    {
      if (((traits.contains(RitualTraits.ONLY_AT_NIGHT)) && (isDaytime)) || ((traits.contains(RitualTraits.ONLY_AT_DAY)) && (!isDaytime)) || ((traits.contains(RitualTraits.ONLY_IN_RAIN)) && (!isRaining)) || ((traits.contains(RitualTraits.ONLY_IN_STROM)) && (!isThundering)) || ((traits.contains(RitualTraits.ONLY_OVERWORLD)) && (field_73011_w.field_76574_g != 0)))
      {

        return false;
      }
      
      if (circles.length > 0) {
        ArrayList<Circle> circlesToFind = new ArrayList(Arrays.asList(circles));
        for (Circle circle : nearbyCircles) {
          circle.removeIfRequired(circlesToFind);
          if (circlesToFind.size() == 0) {
            break;
          }
        }
        
        if (circlesToFind.size() > 0) {
          return false;
        }
      }
      
      return initialSacrifice.isMatch(world, posX, posY, posZ, getMaxDistance(), entities, grassperStacks);
    }
    
    public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds) {
      int maxDistance = getMaxDistance();
      initialSacrifice.addSteps(steps, bounds, maxDistance);
      addRiteSteps(steps, 0);
    }
    
    private int getMaxDistance() {
      int maxDistance = circles.length > 0 ? 0 : 4;
      for (Circle circle : circles) {
        maxDistance = Math.max(circle.getRadius(), maxDistance);
      }
      return maxDistance;
    }
    
    public byte getRitualID() {
      return ritualID;
    }
    
    public void addRiteSteps(ArrayList<RitualStep> ritualSteps, int stage) {
      rite.addSteps(ritualSteps, stage);
    }
    
    public byte[] getCircles() {
      byte[] result = new byte[circles.length];
      int index = 0;
      for (Circle circle : circles) {
        result[(index++)] = ((byte)circle.getTextureIndex());
      }
      return result;
    }
    
    public Ritual setShowInBook(boolean show) {
      visibleInBook = show;
      return this;
    }
    
    public boolean showInBook() {
      return visibleInBook;
    }
    
    private boolean consumeAttunedStoneCharged = false;
    
    public boolean isConsumeAttunedStoneCharged() { return consumeAttunedStoneCharged; }
    
    public void setConsumeAttunedStoneCharged()
    {
      consumeAttunedStoneCharged = true;
    }
    
    private boolean consumeNecroStone = false;
    
    public boolean isConsumeNecroStone() { return consumeNecroStone; }
    
    public void setConsumeNecroStone()
    {
      consumeNecroStone = true;
    }
  }
  
  final ArrayList<Ritual> rituals = new ArrayList();
  



  public final List<Ritual> getRituals()
  {
    return rituals;
  }
  
  public static Ritual addRecipe(int ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle... circles) {
    Ritual ritual = new Ritual((byte)ritualID, bookIndex, rite, initialSacrifice, traits, circles);
    instancerituals.add(ritual);
    return ritual;
  }
  
  public Ritual getRitual(byte ritualID) {
    return (Ritual)rituals.get(ritualID - 1);
  }
  
  public static class IndexComparitor implements Comparator<RiteRegistry.Ritual> {
    public IndexComparitor() {}
    
    public int compare(RiteRegistry.Ritual o1, RiteRegistry.Ritual o2) { return Integer.valueOf(bookIndex).compareTo(Integer.valueOf(bookIndex)); }
  }
  
  public List<Ritual> getSortedRituals()
  {
    ArrayList<Ritual> sortedRituals = new ArrayList();
    sortedRituals.addAll(rituals);
    Collections.sort(sortedRituals, new IndexComparitor());
    return sortedRituals;
  }
  
  public static void RiteError(String translationID, String username, World world) {
    if ((world != null) && (!field_72995_K) && (username != null)) {
      for (Object obj : field_73010_i) {
        if ((obj instanceof EntityPlayer)) {
          EntityPlayer worldPlayer = (EntityPlayer)obj;
          if (worldPlayer.func_70005_c_().equals(username)) {
            RiteError(translationID, worldPlayer, world);
            return;
          }
        }
      }
    }
  }
  
  public static void RiteError(String translationID, EntityPlayer player, World world) {
    if ((world != null) && (!field_72995_K) && (player != null)) {
      ChatUtil.sendTranslated(EnumChatFormatting.RED, player, translationID, new Object[0]);
    }
  }
}
