package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;


public class ItemPolynesiaCharm
  extends ItemBase
{
  private final boolean charmDemons;
  
  public ItemPolynesiaCharm(boolean charmDemons)
  {
    this.charmDemons = charmDemons;
    func_77656_e(50);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
  {
    if (!field_72995_K) {
      boolean success = false;
      double MAX_TARGET_RANGE = 5.0D;
      MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 5.0D);
      if ((mop != null) && (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((field_72308_g instanceof EntityLiving))) {
        EntityLiving living = (EntityLiving)field_72308_g;
        if ((((living instanceof EntityAnimal)) || ((living instanceof EntityAmbientCreature)) || ((living instanceof EntitySpider)) || ((living instanceof EntityWaterMob)) || (((living instanceof EntityCreeper)) && (ItemsWITCH_ROBES.isRobeWorn(player))) || ((living.func_70662_br()) && (ItemsNECROMANCERS_ROBES.isRobeWorn(player)))) && (!(living instanceof EntityFamiliar)) && (!(living instanceof EntityCovenWitch)) && (!(living instanceof EntityImp)))
        {




          if ((living.func_70089_S()) && (!living.func_70631_g_()) && (living.func_70638_az() == null) && (
            (!(living instanceof EntityBat)) || (canBatDrop(living)))) {
            AnimalMerchant merchant = new AnimalMerchant(living);
            merchant.playIntro(player);
            merchant.func_70932_a_(player);
            String animalName = living.func_70005_c_();
            player.func_71030_a(merchant, animalName.substring(0, Math.min(30, animalName.length())));
            success = true;
          }
        }
      }
      

      if ((!success) || ((mop != null) && ((field_72308_g instanceof EntityDemon)))) {
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      } else {
        itemstack.func_77972_a(1, player);
        if (field_77994_a <= 0) {
          player.func_71028_bD();
        }
      }
    }
    return super.func_77659_a(itemstack, world, player);
  }
  
  private boolean canBatDrop(EntityLiving living) {
    NBTTagCompound nbtBat = living.getEntityData();
    return (nbtBat == null) || (!nbtBat.func_74764_b("WITCNoDrops")) || (!nbtBat.func_74767_n("WITCNoDrops"));
  }
  
  public boolean canCharmDemons() {
    return charmDemons;
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return EnumRarity.uncommon;
  }
  
  public void func_77624_a(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
  {
    String localText = Witchery.resource(func_77658_a() + ".tip");
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
  }
  
  public static boolean hasStockInventory(EntityLiving entity) {
    if (entity == null) {
      return false;
    }
    NBTTagCompound nbtTag = entity.getEntityData();
    boolean hasKey = (nbtTag != null) && (nbtTag.func_74764_b("WitcheryShopStock"));
    return hasKey;
  }
  
  public static void setEmptyStockInventory(World world, EntityLiving entity) {
    if ((entity != null) && (!field_72995_K)) {
      NBTTagCompound nbtTag = entity.getEntityData();
      nbtTag.func_74782_a("WitcheryShopStock", new NBTTagCompound());
    }
  }
  
  private static class AnimalMerchant implements IMerchant {
    private final EntityLiving animal;
    private EntityPlayer customer;
    static final String STOCKS_KEY = "WitcheryShopStock";
    
    public AnimalMerchant(EntityLiving animal) {
      this.animal = animal;
    }
    
    public void playIntro(EntityPlayer player) {
      playGreeting(animal, player);
    }
    
    public void func_70932_a_(EntityPlayer player)
    {
      customer = player;
    }
    
    public EntityPlayer func_70931_l_()
    {
      return customer;
    }
    


    private MerchantRecipeList currentList = null;
    
    public MerchantRecipeList func_70934_b(EntityPlayer player)
    {
      NBTTagCompound nbtTag = animal.getEntityData();
      if (currentList != null)
        return currentList;
      if (nbtTag.func_74764_b("WitcheryShopStock")) {
        NBTTagCompound nbtTagStocks = nbtTag.func_74775_l("WitcheryShopStock");
        if (nbtTagStocks.func_82582_d()) {
          currentList = new MerchantRecipeList();
        } else {
          currentList = new MerchantRecipeList(nbtTagStocks);
        }
        return currentList;
      }
      currentList = new MerchantRecipeList();
      populateList(animal, currentList);
      nbtTag.func_74782_a("WitcheryShopStock", currentList.func_77202_a());
      return currentList;
    }
    

    public void func_70933_a(MerchantRecipe recipe)
    {
      if ((animal != null) && (animal.func_70089_S()) && (!animal.field_70170_p.field_72995_K)) {
        recipe.func_77399_f();
        if (currentList != null) {
          NBTTagCompound nbtTag = animal.getEntityData();
          nbtTag.func_74782_a("WitcheryShopStock", currentList.func_77202_a());
        }
      }
      animal.func_70642_aH();
    }
    
    public void func_110297_a_(ItemStack itemstack)
    {
      animal.func_70642_aH();
    }
    

    @SideOnly(Side.CLIENT)
    public void func_70930_a(MerchantRecipeList list) {}
    

    private static void populateList(EntityLiving animal, MerchantRecipeList finalList)
    {
      Random r = field_70170_p.field_73012_v;
      
      MerchantRecipeList list = new MerchantRecipeList();
      
      ItemStack[] stacks = { ItemsGENERIC.itemMandrakeRoot.createStack(3), ItemsGENERIC.itemBelladonnaFlower.createStack(3), ItemsGENERIC.itemArtichoke.createStack(3), new ItemStack(Blocks.field_150345_g, 4, 0), new ItemStack(Blocks.field_150345_g, 4, 1), new ItemStack(Blocks.field_150345_g, 4, 2), new ItemStack(Blocks.field_150345_g, 4, 3), new ItemStack(Blocks.field_150436_aH, 2), new ItemStack(Blocks.field_150434_aF, 2), new ItemStack(Items.field_151074_bl, 5), new ItemStack(Items.field_151042_j, 2), new ItemStack(Items.field_151103_aS, 4), new ItemStack(Items.field_151145_ak, 5), ItemsGENERIC.itemDogTongue.createStack(2), new ItemStack(Items.field_151174_bG, 5), new ItemStack(Items.field_151170_bI, 2), new ItemStack(Items.field_151172_bF, 5), new ItemStack(Items.field_151119_aD, 10) };
      





      ArrayList<ItemStack> currencies = new ArrayList();
      ArrayList<ItemStack> items = new ArrayList();
      
      items.add(stacks[r.nextInt(stacks.length)]);
      if (field_70170_p.field_73012_v.nextDouble() < 0.03D) {
        items.add(ItemsGENERIC.itemSeedsTreefyd.createStack());
      }
      
      if ((animal instanceof EntityPig)) {
        currencies.add(new ItemStack(Items.field_151172_bF));
        currencies.add(new ItemStack(Items.field_151034_e));
        currencies.add(new ItemStack(Items.field_151174_bG));
        
        items.add(new ItemStack(Blocks.field_150337_Q, 5));
        items.add(new ItemStack(Blocks.field_150338_P, 5));
        if (r.nextDouble() < 0.02D) {
          items.add(new ItemStack(Items.field_151166_bC, 1));
        }
        if (r.nextDouble() < 0.01D) {
          items.add(new ItemStack(Items.field_151045_i, 1));
        }
      }
      else if ((animal instanceof EntityHorse)) {
        currencies.add(new ItemStack(Items.field_151172_bF));
        currencies.add(new ItemStack(Items.field_151034_e));
        currencies.add(new ItemStack(Items.field_151015_O));
        
        if (r.nextDouble() < 0.01D) {
          items.add(new ItemStack(Items.field_151141_av, 1));
        }
      } else if ((animal instanceof EntityWolf)) {
        currencies.add(new ItemStack(Items.field_151082_bd));
        currencies.add(new ItemStack(Items.field_151147_al));
        currencies.add(new ItemStack(Items.field_151076_bf));
        
        items.add(new ItemStack(Items.field_151103_aS, 5));
        
        if (r.nextDouble() < 0.02D) {
          items.add(new ItemStack(Items.field_151166_bC, 1));
        }
        if (r.nextDouble() < 0.01D) {
          items.add(new ItemStack(Items.field_151045_i, 1));
        }
      } else if ((animal instanceof EntityOcelot)) {
        currencies.add(new ItemStack(Items.field_151117_aB));
        currencies.add(new ItemStack(Items.field_151115_aP));
      } else if ((animal instanceof EntityCow)) {
        currencies.add(new ItemStack(Items.field_151015_O));
      } else if ((animal instanceof EntityChicken)) {
        currencies.add(new ItemStack(Items.field_151014_N));
        
        items.add(new ItemStack(Items.field_151008_G, 10));
        items.add(new ItemStack(Items.field_151110_aK, 5));
      }
      else if ((animal instanceof EntityMooshroom)) {
        currencies.add(new ItemStack(Blocks.field_150337_Q));
        currencies.add(new ItemStack(Blocks.field_150338_P));
      } else if ((animal instanceof EntitySheep)) {
        currencies.add(new ItemStack(Items.field_151015_O));
      } else if ((animal instanceof EntitySquid)) {
        currencies.add(new ItemStack(Items.field_151115_aP));
        items.add(Dye.INK_SAC.createStack(10));
      } else if ((animal instanceof EntityBat)) {
        currencies.add(new ItemStack(Items.field_151014_N));
        currencies.add(new ItemStack(Items.field_151015_O));
        currencies.add(new ItemStack(Items.field_151082_bd));
        currencies.add(new ItemStack(Items.field_151147_al));
        
        items.add(ItemsGENERIC.itemBatWool.createStack(5));
      }
      else if ((animal instanceof EntitySpider)) {
        currencies.add(new ItemStack(Items.field_151082_bd));
        currencies.add(new ItemStack(Items.field_151147_al));
        currencies.add(new ItemStack(Items.field_151076_bf));
        currencies.add(new ItemStack(Items.field_151115_aP));
        
        items.add(new ItemStack(Items.field_151007_F, 8));
        items.add(ItemsGENERIC.itemWeb.createStack(4));
      } else if ((animal instanceof EntityCreeper)) {
        currencies.add(new ItemStack(Items.field_151016_H));
        currencies.add(new ItemStack(Items.field_151115_aP));
        
        if (r.nextDouble() < 0.05D) {
          items.add(ItemsGENERIC.itemSpectralDust.createStack(2));
        }
        
        if (field_70170_p.field_73012_v.nextDouble() < 0.1D) {
          items.add(ItemsGENERIC.itemSeedsTreefyd.createStack());
        }
        
        if (r.nextDouble() < 0.02D) {
          items.add(ItemsGENERIC.itemCreeperHeart.createStack(1));
        }
      } else if (animal.func_70662_br()) {
        currencies.add(new ItemStack(Items.field_151103_aS));
        
        items.add(ItemsGENERIC.itemSpectralDust.createStack(1));
      } else {
        currencies.add(new ItemStack(Items.field_151082_bd));
        currencies.add(new ItemStack(Items.field_151147_al));
        currencies.add(new ItemStack(Items.field_151076_bf));
        currencies.add(new ItemStack(Items.field_151115_aP));
        currencies.add(new ItemStack(Items.field_151015_O));
        currencies.add(new ItemStack(Items.field_151014_N));
        currencies.add(new ItemStack(Items.field_151172_bF));
        currencies.add(new ItemStack(Items.field_151034_e));
        currencies.add(new ItemStack(Items.field_151174_bG));
      }
      
      for (ItemStack itemstack : items) {
        if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
          ItemStack goods = itemstack.func_77946_l();
          field_77994_a = Math.min(r.nextInt(field_77994_a) + (field_77994_a > 4 ? 3 : 1), goods.func_77976_d());
          ItemStack currency = (ItemStack)currencies.get(r.nextInt(currencies.size()));
          ItemStack cost = currency.func_77946_l();
          int multiplier = 1;
          if ((goods.func_77973_b() == Items.field_151045_i) || (goods.func_77973_b() == Items.field_151166_bC) || (goods.func_77973_b() == Items.field_151141_av) || (ItemsGENERIC.itemSeedsTreefyd.isMatch(goods)) || (animal.func_70662_br()))
          {
            multiplier = 2;
          }
          int factor = field_77994_a > 4 ? 1 : 2;
          field_77994_a = Math.min(r.nextInt(2) + field_77994_a * multiplier * (r.nextInt(2) + factor), currency.func_77976_d());
          MerchantRecipe recipe = new MerchantRecipe(cost, goods);
          recipe.func_82783_a(0 - (6 - r.nextInt(2)));
          list.add(recipe);
        }
      }
      
      Collections.shuffle(list);
      
      int MAX_ITEMS = r.nextInt(2) + 1;
      
      for (int i = 0; (i < MAX_ITEMS) && (i < list.size()); i++) {
        finalList.add(list.get(i));
      }
    }
    
    private void playGreeting(EntityLiving animal, EntityPlayer player) {
      animal.func_70642_aH();
      animal.func_70642_aH();
      animal.func_70642_aH();
    }
  }
}
