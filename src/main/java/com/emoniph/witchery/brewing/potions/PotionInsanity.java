package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PotionInsanity extends PotionBase
{
  public PotionInsanity(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setIncurable();
    setPermenant();
    hideInventoryText();
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return duration % 20 == 13;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      World world = field_70170_p;
      int level = Math.max(0, amplifier) + 1;
      
      int x = MathHelper.func_76128_c(field_70165_t);
      int y = MathHelper.func_76128_c(field_70163_u);
      int z = MathHelper.func_76128_c(field_70161_v);
      if (field_73012_v.nextInt(level > 1 ? 30 : level > 2 ? 25 : 35) == 0) {
        Class<? extends EntityCreature> creatureType = null;
        switch (field_73012_v.nextInt(3)) {
        case 0: 
        default: 
          creatureType = com.emoniph.witchery.entity.EntityIllusionCreeper.class;
          break;
        case 1: 
          creatureType = EntityIllusionSpider.class;
          break;
        case 2: 
          creatureType = EntityIllusionZombie.class;
        }
        
        int MAX_DISTANCE = 9;
        int MIN_DISTANCE = 4;
        com.emoniph.witchery.infusion.Infusion.spawnCreature(world, creatureType, x, y, z, player, 4, 9);
      } else if ((level >= 4) && (field_73012_v.nextInt(20) == 0)) {
        SoundEffect sound = SoundEffect.NONE;
        switch (field_73012_v.nextInt(3)) {
        case 0: case 2: 
        case 3: 
        default: 
          sound = SoundEffect.RANDOM_EXPLODE;
          break;
        case 1: 
          sound = SoundEffect.MOB_ENDERMAN_IDLE;
        }
        
        
        sound.playOnlyTo((EntityPlayer)entity, 1.0F, 1.0F);
      }
    }
  }
  
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
  {
    int factor = effect.func_76459_b() / 60 % 7;
    String s1 = I18n.func_135052_a(Witchery.resource("witchery:potion.insanity." + factor), new Object[0]);
    








    field_71466_p.func_78261_a(s1, x + 10 + 18, y + 6, 16777215);
    String s = net.minecraft.potion.Potion.func_76389_a(effect);
    field_71466_p.func_78261_a(s, x + 10 + 18, y + 6 + 10, 8355711);
  }
}
