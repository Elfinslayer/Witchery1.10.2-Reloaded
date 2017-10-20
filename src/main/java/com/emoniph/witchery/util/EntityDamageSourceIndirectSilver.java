package com.emoniph.witchery.util;

import net.minecraft.entity.Entity;

public class EntityDamageSourceIndirectSilver extends net.minecraft.util.EntityDamageSourceIndirect
{
  public EntityDamageSourceIndirectSilver(Entity directSource, Entity indirectSource) {
    super("indirectMagic", directSource, indirectSource);
  }
}
