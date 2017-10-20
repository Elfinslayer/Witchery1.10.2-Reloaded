package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import org.lwjgl.opengl.GL11;

public class PotionDiseased extends PotionBase implements IHandleRenderLiving
{
  @SideOnly(Side.CLIENT)
  private static ResourceLocation TEXTURE;
  
  public PotionDiseased(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setIncurable();
    func_111184_a(SharedMonsterAttributes.field_111264_e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
  }
  
  public double func_111183_a(int amplifier, AttributeModifier p_111183_2_)
  {
    return -0.5F * (Math.min(amplifier, 1) + 1);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return duration % 40 == 4;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    if (!field_70170_p.field_72995_K)
    {





      if (field_70170_p.field_73012_v.nextInt(3) == 0) {
        Coord coord = new Coord(entity);
        if ((field_70170_p.func_147437_c(x, y, z)) && (field_70170_p.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a()))
        {
          if (BlockProtect.checkModsForBreakOK(field_70170_p, x, y, z, entity)) {
            coord.setBlock(field_70170_p, BlocksDISEASE);
          }
        }
      }
    }
  }
  




  @SideOnly(Side.CLIENT)
  public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
  {
    if (TEXTURE == null) {
      TEXTURE = new ResourceLocation("witchery", "textures/entities/disease_overlay.png");
    }
    GL11.glPushMatrix();
    Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
    ModelOverlayRenderer.render(entity, x, y, z, renderer);
    GL11.glPopMatrix();
  }
}
