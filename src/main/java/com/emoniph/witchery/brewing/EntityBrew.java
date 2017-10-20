package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBrew
  extends EntityThrowableBase
{
  private ItemStack brewStack;
  private int color;
  private boolean isSpell;
  
  public EntityBrew(World world)
  {
    super(world);
  }
  
  public EntityBrew(World world, EntityLivingBase thrower, ItemStack brewStack, boolean isSpell) {
    super(world, thrower, isSpell ? 0.0F : -20.0F);
    this.brewStack = brewStack;
    setIsSpell(isSpell);
    setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
  }
  
  public EntityBrew(World world, double x, double y, double z, ItemStack brewStack, boolean isSpell) {
    super(world, x, y, z, isSpell ? 0.0F : -20.0F);
    this.brewStack = brewStack;
    setIsSpell(isSpell);
    setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(6, Integer.valueOf(0));
    field_70180_af.func_75682_a(12, Byte.valueOf((byte)0));
    super.func_70088_a();
  }
  
  protected void setColor(int color) {
    func_70096_w().func_75692_b(6, Integer.valueOf(color));
  }
  
  public int getColor() {
    return func_70096_w().func_75679_c(6);
  }
  
  protected void setIsSpell(boolean spell) {
    func_70096_w().func_75692_b(12, Byte.valueOf((byte)(spell ? 1 : 0)));
  }
  
  public boolean getIsSpell() {
    return func_70096_w().func_75683_a(12) == 1;
  }
  
  public ItemStack getBrew() {
    return brewStack;
  }
  
  protected float getGravityVelocity()
  {
    return getIsSpell() ? 0.0F : 0.05F;
  }
  
  protected float func_70182_d()
  {
    return getIsSpell() ? 4.0F : 0.75F;
  }
  
  protected float func_70183_g()
  {
    return getIsSpell() ? 0.0F : -20.0F;
  }
  
  protected void onImpact(MovingObjectPosition mop)
  {
    if ((!field_70170_p.field_72995_K) && 
      (mop != null) && 
      (WitcheryBrewRegistry.INSTANCE.impactSplashPotion(field_70170_p, brewStack, mop, new ModifiersImpact(new EntityPosition(this), false, 0, EntityUtil.playerOrFake(field_70170_p, getThrower())))))
    {



      field_70170_p.func_72926_e(2002, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), getColor());
    }
    




    func_70106_y();
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    
    if (nbtRoot.func_150297_b("Brew", 10)) {
      brewStack = ItemStack.func_77949_a(nbtRoot.func_74775_l("Brew"));
      setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
      if (nbtRoot.func_74764_b("Spell")) {
        setIsSpell(nbtRoot.func_74767_n("Spell"));
      }
    }
    
    if (brewStack == null) {
      func_70106_y();
    }
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    
    if (brewStack != null) {
      nbtRoot.func_74782_a("Brew", brewStack.func_77955_b(new NBTTagCompound()));
      nbtRoot.func_74757_a("Spell", getIsSpell());
    }
  }
}
