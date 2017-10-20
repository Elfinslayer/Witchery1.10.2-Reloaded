package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class WorldHandlerVillageApothecary implements VillagerRegistry.IVillageCreationHandler, cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler
{
  public WorldHandlerVillageApothecary() {}
  
  public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size)
  {
    return new StructureVillagePieces.PieceWeight(ComponentVillageApothecary.class, 15, 1 + (size > 2 ? random.nextInt(2) : 0));
  }
  
  public Class getComponentClass()
  {
    return ComponentVillageApothecary.class;
  }
  

  public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
  {
    return ComponentVillageApothecary.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
  }
  
  public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
  {
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 2), ItemsGENERIC.itemDogTongue.createStack(2)));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 1), ItemsGENERIC.itemBatWool.createStack(3)));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 4), ItemsGENERIC.itemSpectralDust.createStack()));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5), new ItemStack(ItemsSEEDS_GARLIC)));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 6), ItemsGENERIC.itemArtichoke.createStack()));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 7), new ItemStack(net.minecraft.init.Blocks.field_150328_O, 5)));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 8), new ItemStack(Items.field_151123_aH)));
    recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 3), new ItemStack(Items.field_151119_aD, 5)));
  }
}
