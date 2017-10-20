package com.emoniph.witchery.infusion.infusions.symbols;

import com.emoniph.witchery.entity.EntitySpellEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class SymbolEffectProjectile
  extends SymbolEffect
{
  private float size = 1.0F;
  private int color = 16711680;
  private int timetolive = -1;
  
  public SymbolEffectProjectile(int effectID, String unlocalisedName) {
    super(effectID, unlocalisedName);
  }
  
  public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown) {
    super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown);
  }
  
  public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown, boolean isVisible) {
    super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown, isVisible);
  }
  
  public SymbolEffectProjectile setColor(int color) {
    this.color = color;
    return this;
  }
  
  public SymbolEffectProjectile setSize(float size) {
    this.size = size;
    return this;
  }
  
  public SymbolEffectProjectile setTimeToLive(int ticks) {
    timetolive = ticks;
    return this;
  }
  
  public int getColor() {
    return color;
  }
  
  public float getSize() {
    return size;
  }
  
  public void perform(World world, EntityPlayer player, int effectLevel)
  {
    world.func_72889_a((EntityPlayer)null, 1008, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    float f = 1.0F;
    double motionX = -MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f;
    double motionZ = MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f;
    double motionY = -MathHelper.func_76126_a(field_70125_A / 180.0F * 3.1415927F) * f;
    EntitySpellEffect entity = new EntitySpellEffect(world, player, motionX, motionY, motionZ, this, effectLevel);
    if (timetolive > 0) {
      entity.setLifeTime(timetolive);
    }
    entity.func_70012_b(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v, field_70177_z, field_70125_A);
    entity.func_70107_b(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    double d6 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
    accelerationX = (motionX / d6 * 0.3D);
    accelerationY = (motionY / d6 * 0.3D);
    accelerationZ = (motionZ / d6 * 0.3D);
    double d8 = 1.5D;
    Vec3 vec3 = player.func_70676_i(1.0F);
    field_70165_t = (field_70165_t + field_72450_a * 1.5D);
    field_70163_u = (field_70163_u + eyeHeight - 0.10000000149011612D + field_72448_b * 1.5D);
    field_70161_v = (field_70161_v + field_72449_c * 1.5D);
    world.func_72838_d(entity);
  }
  
  public abstract void onCollision(World paramWorld, EntityLivingBase paramEntityLivingBase, MovingObjectPosition paramMovingObjectPosition, EntitySpellEffect paramEntitySpellEffect);
}
