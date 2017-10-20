package com.emoniph.witchery.worldgen;

import java.util.List;
import java.util.Random;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class WorldHandlerVillageWitchHut implements cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler
{
  public WorldHandlerVillageWitchHut() {}
  
  public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size)
  {
    return new StructureVillagePieces.PieceWeight(ComponentVillageWitchHut.class, 10, random.nextInt(2));
  }
  
  public Class getComponentClass()
  {
    return ComponentVillageWitchHut.class;
  }
  

  public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
  {
    return ComponentVillageWitchHut.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
  }
}
