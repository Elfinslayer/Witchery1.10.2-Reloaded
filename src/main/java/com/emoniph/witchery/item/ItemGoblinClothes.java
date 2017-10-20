package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelGoblinClothes;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
import com.emoniph.witchery.network.PacketPipeline;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemGoblinClothes extends ItemArmor
{
  @SideOnly(Side.CLIENT)
  private ModelGoblinClothes modelClothesChest;
  @SideOnly(Side.CLIENT)
  private ModelGoblinClothes modelClothesLegs;
  
  public ItemGoblinClothes(int armorSlot)
  {
    super(armorSlot == 0 ? ItemArmor.ArmorMaterial.IRON : ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
    
    func_77656_e(armorSlot == 0 ? ItemArmor.ArmorMaterial.DIAMOND.func_78046_a(armorSlot) : ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
    
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  

  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 100L == 0L)) {
      double R = 8.0D;
      AxisAlignedBB bb = AxisAlignedBB.func_72330_a(field_70165_t - 8.0D, field_70163_u - 8.0D, field_70161_v - 8.0D, field_70165_t + 8.0D, field_70163_u + 8.0D, field_70161_v + 8.0D);
      List players = world.func_72872_a(EntityPlayer.class, bb);
      for (Object obj : players) {
        EntityPlayer otherPlayer = (EntityPlayer)obj;
        if ((player != otherPlayer) && (
          ((isQuiverWorn(player)) && (isBeltWorn(otherPlayer))) || ((isQuiverWorn(otherPlayer)) && (isBeltWorn(player)))))
        {
          if (player.func_70644_a(Potion.field_76429_m)) {
            player.func_82170_o(field_76429_mfield_76415_H);
          }
          
          player.func_70690_d(new PotionEffect(field_76429_mfield_76415_H, 200, 1));
          return;
        }
      }
    }
    

    if ((!field_72995_K) && (isHelmWorn(player)) && (world.func_82737_E() % 5L == 1L)) {
      double R2 = 16.0D;
      AxisAlignedBB bb2 = AxisAlignedBB.func_72330_a(field_70165_t - 16.0D, field_70163_u - 16.0D, field_70161_v - 16.0D, field_70165_t + 16.0D, field_70163_u + 16.0D, field_70161_v + 16.0D);
      
      List entities = world.func_72872_a(EntityLivingBase.class, bb2);
      for (Object obj : entities) {
        EntityLivingBase otherPlayer = (EntityLivingBase)obj;
        if ((player != otherPlayer) && 
          (shouldAffectTarget(player, otherPlayer))) {
          if ((otherPlayer instanceof EntityPlayer)) {
            double yawRadians = Math.atan2(field_70161_v - field_70161_v, field_70165_t - field_70161_v);
            double yaw = Math.toDegrees(yawRadians) + 180.0D;
            float rev = ((float)yaw + 90.0F) % 360.0F;
            











            if ((otherPlayer instanceof net.minecraft.entity.player.EntityPlayerMP)) {
              S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(field_70165_t, field_70163_u, field_70161_v, rev, field_70125_A, false);
              Witchery.packetPipeline.sendTo(packet, (net.minecraft.entity.player.EntityPlayerMP)otherPlayer);
            }
            

            if (!otherPlayer.func_70644_a(Potion.field_76438_s)) {
              otherPlayer.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, 100, 0));
            }
          }
          else if (!otherPlayer.func_70644_a(Potion.field_76437_t)) {
            otherPlayer.func_70690_d(new PotionEffect(field_76437_tfield_76415_H, 100, 0));
          }
        }
      }
    }
  }
  

  private boolean shouldAffectTarget(EntityLivingBase player, EntityLivingBase target)
  {
    ItemStack itemstack = target.func_71124_b(1);
    
    if ((itemstack != null) && (itemstack.func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150423_aK)))
    {
      return false;
    }
    

    Vec3 vec3 = target.func_70676_i(1.0F).func_72432_b();
    Vec3 vec31 = Vec3.func_72443_a(field_70165_t - field_70165_t, field_70121_D.field_72338_b + field_70131_O / 2.0F - (field_70163_u + target.func_70047_e()), field_70161_v - field_70161_v);
    double d0 = vec31.func_72433_c();
    vec31 = vec31.func_72432_b();
    double d1 = vec3.func_72430_b(vec31);
    return (d1 > 1.0D - 0.025D / d0) && (target.func_70685_l(player));
  }
  

  public boolean hasEffect(ItemStack stack, int pass)
  {
    return (super.hasEffect(stack, pass)) || (stack.func_77973_b() != ItemsKOBOLDITE_HELM);
  }
  
  public boolean func_82816_b_(ItemStack stack)
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int par2)
  {
    return super.func_82790_a(stack, par2);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v()
  {
    return false;
  }
  
  public int func_82814_b(ItemStack stack)
  {
    if (!func_82816_b_(stack)) {
      return 16777215;
    }
    
    return super.func_82814_b(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return field_77881_a != 0 ? EnumRarity.epic : EnumRarity.rare;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    if (stack != null) {
      String localText = Witchery.resource(func_77658_a() + ".tip");
      if (localText != null) {
        for (String s : localText.split("\n")) {
          if (!s.isEmpty()) {
            list.add(s);
          }
        }
      }
    }
  }
  






  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if (stack != null) {
      if (slot == 0)
        return "witchery:textures/entities/goblinclothes_head" + (type == null ? "" : "_overlay") + ".png";
      if (slot == 2) {
        return "witchery:textures/entities/goblinclothes_legs" + (type == null ? "" : "_overlay") + ".png";
      }
      return "witchery:textures/entities/goblinclothes" + (type == null ? "" : "_overlay") + ".png";
    }
    
    return null;
  }
  

  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelGoblinClothes(0.61F);
    }
    
    if (modelClothesLegs == null) {
      modelClothesLegs = new ModelGoblinClothes(0.0F);
    }
    
    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemArmor))) {
      int type = func_77973_bfield_77881_a;
      
      if (type != 2) {
        armorModel = modelClothesChest;
      } else {
        armorModel = modelClothesLegs;
      }
      
      if (armorModel != null) {
        boolean isVisible = true;
        
        field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
        field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        
        field_78117_n = entityLiving.func_70093_af();
        field_78093_q = entityLiving.func_70115_ae();
        field_78091_s = entityLiving.func_70631_g_();
        
        ItemStack heldStack = entityLiving.func_71124_b(0);
        field_78120_m = (heldStack != null ? 1 : 0);
        field_78118_o = false;
        
        if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
          EnumAction enumaction = heldStack.func_77975_n();
          
          if (enumaction == EnumAction.block) {
            field_78120_m = 3;
          }
          
          field_78118_o = (enumaction == EnumAction.bow);
        }
        return armorModel;
      }
    }
    return null;
  }
  
  private static boolean isQuiverWorn(EntityPlayer player) {
    ItemStack currentArmor = player.func_82169_q(2);
    return (currentArmor != null) && (currentArmor.func_77973_b() == ItemsMOGS_QUIVER);
  }
  
  private static boolean isHelmWorn(EntityPlayer player) {
    ItemStack currentArmor = player.func_82169_q(3);
    return (currentArmor != null) && (currentArmor.func_77973_b() == ItemsKOBOLDITE_HELM);
  }
  
  private static boolean isBeltWorn(EntityPlayer player) {
    ItemStack currentArmor = player.func_82169_q(1);
    return (currentArmor != null) && (currentArmor.func_77973_b() == ItemsGULGS_GURDLE);
  }
  
  public static class EventHooks { public EventHooks() {}
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) { if ((!entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
        if (source.func_76352_a()) {
          if (source.func_76364_f() != null) {
            boolean mogged = source.func_76364_f().getEntityData().func_74767_n("WITCMogged");
            if (mogged) {
              if (entityLiving.field_70160_al) {
                ammount *= 3.0F;
              }
              if (entityLiving.func_70644_a(Potion.field_76437_t)) {
                entityLiving.func_82170_o(field_76437_tfield_76415_H);
              }
              
              entityLiving.func_70690_d(new PotionEffect(field_76437_tfield_76415_H, 200, 0));
            }
          }
        } else if (source.func_76355_l().equals("player")) {
          Entity source = source.func_76346_g();
          if ((source != null) && ((source instanceof EntityPlayer))) {
            EntityPlayer player = (EntityPlayer)source;
            if ((ItemGoblinClothes.isBeltWorn(player)) && (player.func_70694_bm() == null)) {
              ammount = 5.0F;
              final EntityLivingBase entity = entityLiving;
              if ((entity instanceof EntityPlayer)) {
                Witchery.packetPipeline.sendTo(new com.emoniph.witchery.network.PacketPushTarget(field_70159_w, 1.0D, field_70179_y), (EntityPlayer)entity);
              } else {
                com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new ServerTickEvents.ServerTickTask(field_70170_p)
                {
                  public boolean process() {
                    if ((entity != null) && (!entityfield_70128_L)) {
                      entityfield_70181_x = 1.0D;
                    }
                    return true;
                  }
                });
              }
            }
          }
        }
      }
    }
    
    @SubscribeEvent
    public void onArrowLoose(ArrowLooseEvent event) {
      if ((!event.isCanceled()) && (ItemGoblinClothes.isQuiverWorn(entityPlayer))) {
        float f = charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        
        if (f < 0.1D) {
          return;
        }
        
        if (f > 1.0F) {
          f = 1.0F;
        }
        
        EntityArrow entityarrow = new EntityArrow(entityPlayer.field_70170_p, entityPlayer, f * 3.0F);
        entityarrow.getEntityData().func_74757_a("WITCMogged", true);
        
        if (f == 1.0F) {
          entityarrow.func_70243_d(true);
        }
        
        int k = EnchantmentHelper.func_77506_a(field_77345_tfield_77352_x, bow);
        
        if (k > 0) {
          entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
        }
        
        int l = EnchantmentHelper.func_77506_a(field_77344_ufield_77352_x, bow);
        
        if (l > 0) {
          entityarrow.func_70240_a(l);
        }
        
        if (EnchantmentHelper.func_77506_a(field_77343_vfield_77352_x, bow) > 0) {
          entityarrow.func_70015_d(100);
        }
        
        bow.func_77972_a(1, entityPlayer);
        entityPlayer.field_70170_p.func_72956_a(entityPlayer, "random.bow", 1.0F, 1.0F / (ItemGoblinClothes.field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        

        field_70251_a = 2;
        
        if (!entityPlayer.field_70170_p.field_72995_K) {
          entityPlayer.field_70170_p.func_72838_d(entityarrow);
        }
        event.setCanceled(true);
      }
    }
    
    @SubscribeEvent
    public void onArrowNock(ArrowNockEvent event) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(entityPlayer);
      if (playerEx.getCreatureType() != com.emoniph.witchery.util.TransformCreature.NONE) {
        event.setCanceled(true);
        return;
      }
      
      if ((!event.isCanceled()) && (ItemGoblinClothes.isQuiverWorn(entityPlayer))) {
        entityPlayer.func_71008_a(result, result.func_77973_b().func_77626_a(result));
      }
    }
  }
}
