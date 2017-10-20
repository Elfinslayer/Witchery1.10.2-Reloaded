package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
import com.emoniph.witchery.blocks.BlockAltarGUI;
import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaCurseProtect;
import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaTeleportPullProtect;
import com.emoniph.witchery.blocks.BlockBeartrap.TileEntityBeartrap;
import com.emoniph.witchery.blocks.BlockBloodCrucible.TileEntityBloodCrucible;
import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
import com.emoniph.witchery.blocks.BlockCandelabra.TileEntityCandelabra;
import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
import com.emoniph.witchery.blocks.BlockCoffin.TileEntityCoffin;
import com.emoniph.witchery.blocks.BlockCrystalBall.TileEntityCrystalBall;
import com.emoniph.witchery.blocks.BlockDemonHeart.TileEntityDemonHeart;
import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
import com.emoniph.witchery.blocks.BlockFumeFunnel.TileEntityFumeFunnel;
import com.emoniph.witchery.blocks.BlockGarlicGarland.TileEntityGarlicGarland;
import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
import com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle;
import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
import com.emoniph.witchery.blocks.BlockMirror.TileEntityMirror;
import com.emoniph.witchery.blocks.BlockPlacedItem.TileEntityPlacedItem;
import com.emoniph.witchery.blocks.BlockSilverVat.TileEntitySilverVat;
import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
import com.emoniph.witchery.blocks.BlockSpinningWheelGUI;
import com.emoniph.witchery.blocks.BlockStatueGoddess.TileEntityStatueGoddess;
import com.emoniph.witchery.blocks.BlockStatueOfWorship.TileEntityStatueOfWorship;
import com.emoniph.witchery.blocks.BlockStatueWerewolf.TileEntityStatueWerewolf;
import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
import com.emoniph.witchery.blocks.BlockWitchesOvenGUI;
import com.emoniph.witchery.blocks.BlockWolfHead.TileEntityWolfHead;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.EntityDroplet;
import com.emoniph.witchery.brewing.EntitySplatter;
import com.emoniph.witchery.brewing.RenderBrew;
import com.emoniph.witchery.brewing.RenderBrewGas;
import com.emoniph.witchery.brewing.RenderBrewLiquid;
import com.emoniph.witchery.brewing.RenderCauldron;
import com.emoniph.witchery.brewing.RenderDroplet;
import com.emoniph.witchery.brewing.RenderSplatter;
import com.emoniph.witchery.brewing.RenderWitchVine;
import com.emoniph.witchery.brewing.TileEntityCauldron;
import com.emoniph.witchery.brewing.potions.WitcheryPotions.ClientEventHooks;
import com.emoniph.witchery.client.gui.GuiScreenBiomeBook;
import com.emoniph.witchery.client.gui.GuiScreenMarkupBook;
import com.emoniph.witchery.client.gui.GuiScreenWitchcraftBook;
import com.emoniph.witchery.client.model.ModelDemon;
import com.emoniph.witchery.client.model.ModelEnt;
import com.emoniph.witchery.client.model.ModelFamiliarPig;
import com.emoniph.witchery.client.model.ModelGoblin;
import com.emoniph.witchery.client.model.ModelGoblinGulg;
import com.emoniph.witchery.client.model.ModelGoblinMog;
import com.emoniph.witchery.client.model.ModelHellhound;
import com.emoniph.witchery.client.model.ModelHornedAvatar;
import com.emoniph.witchery.client.model.ModelLeonard;
import com.emoniph.witchery.client.model.ModelLilith;
import com.emoniph.witchery.client.model.ModelMandrake;
import com.emoniph.witchery.client.model.ModelMonkey;
import com.emoniph.witchery.client.model.ModelOwl;
import com.emoniph.witchery.client.model.ModelToad;
import com.emoniph.witchery.client.model.ModelTreefyd;
import com.emoniph.witchery.client.model.ModelWolfman;
import com.emoniph.witchery.client.particle.NaturePowerFX;
import com.emoniph.witchery.client.renderer.RenderAlluringSkull;
import com.emoniph.witchery.client.renderer.RenderBabaYaga;
import com.emoniph.witchery.client.renderer.RenderBanshee;
import com.emoniph.witchery.client.renderer.RenderBeartrap;
import com.emoniph.witchery.client.renderer.RenderBlockItem;
import com.emoniph.witchery.client.renderer.RenderBloodCrucible;
import com.emoniph.witchery.client.renderer.RenderBolt;
import com.emoniph.witchery.client.renderer.RenderBrazier;
import com.emoniph.witchery.client.renderer.RenderBrewBottle;
import com.emoniph.witchery.client.renderer.RenderBroom;
import com.emoniph.witchery.client.renderer.RenderCandelabra;
import com.emoniph.witchery.client.renderer.RenderCaneSword;
import com.emoniph.witchery.client.renderer.RenderChalice;
import com.emoniph.witchery.client.renderer.RenderCoffin;
import com.emoniph.witchery.client.renderer.RenderCorpse;
import com.emoniph.witchery.client.renderer.RenderCovenWitch;
import com.emoniph.witchery.client.renderer.RenderCrystalBall;
import com.emoniph.witchery.client.renderer.RenderDarkMark;
import com.emoniph.witchery.client.renderer.RenderDeath;
import com.emoniph.witchery.client.renderer.RenderDeathsHand;
import com.emoniph.witchery.client.renderer.RenderDemon;
import com.emoniph.witchery.client.renderer.RenderDemonHeart;
import com.emoniph.witchery.client.renderer.RenderDreamCatcher;
import com.emoniph.witchery.client.renderer.RenderEnt;
import com.emoniph.witchery.client.renderer.RenderFamiliar;
import com.emoniph.witchery.client.renderer.RenderFetish;
import com.emoniph.witchery.client.renderer.RenderFetish.RenderFetishBlockItem;
import com.emoniph.witchery.client.renderer.RenderFollower;
import com.emoniph.witchery.client.renderer.RenderFumeFunnel;
import com.emoniph.witchery.client.renderer.RenderGarlicGarland;
import com.emoniph.witchery.client.renderer.RenderGoblin;
import com.emoniph.witchery.client.renderer.RenderGoblinGulg;
import com.emoniph.witchery.client.renderer.RenderGoblinMog;
import com.emoniph.witchery.client.renderer.RenderGoddess;
import com.emoniph.witchery.client.renderer.RenderGrassper;
import com.emoniph.witchery.client.renderer.RenderGrenade;
import com.emoniph.witchery.client.renderer.RenderHandBow;
import com.emoniph.witchery.client.renderer.RenderHellhound;
import com.emoniph.witchery.client.renderer.RenderHornedAvatar;
import com.emoniph.witchery.client.renderer.RenderHuntsmanSpear;
import com.emoniph.witchery.client.renderer.RenderIllusion;
import com.emoniph.witchery.client.renderer.RenderImp;
import com.emoniph.witchery.client.renderer.RenderKettle;
import com.emoniph.witchery.client.renderer.RenderLeechChest;
import com.emoniph.witchery.client.renderer.RenderLeonard;
import com.emoniph.witchery.client.renderer.RenderLilith;
import com.emoniph.witchery.client.renderer.RenderLordOfTorment;
import com.emoniph.witchery.client.renderer.RenderMandrake;
import com.emoniph.witchery.client.renderer.RenderMindrake;
import com.emoniph.witchery.client.renderer.RenderMirror;
import com.emoniph.witchery.client.renderer.RenderMirrorFace;
import com.emoniph.witchery.client.renderer.RenderMysticBranch;
import com.emoniph.witchery.client.renderer.RenderNightmare;
import com.emoniph.witchery.client.renderer.RenderOwl;
import com.emoniph.witchery.client.renderer.RenderParasyticLouse;
import com.emoniph.witchery.client.renderer.RenderPitGrass;
import com.emoniph.witchery.client.renderer.RenderPlacedItem;
import com.emoniph.witchery.client.renderer.RenderPoltergeist;
import com.emoniph.witchery.client.renderer.RenderPoppetChest;
import com.emoniph.witchery.client.renderer.RenderSilverVat;
import com.emoniph.witchery.client.renderer.RenderSpectre;
import com.emoniph.witchery.client.renderer.RenderSpellEffect;
import com.emoniph.witchery.client.renderer.RenderSpinningWheel;
import com.emoniph.witchery.client.renderer.RenderSpirit;
import com.emoniph.witchery.client.renderer.RenderStatueMandrake;
import com.emoniph.witchery.client.renderer.RenderStatueOfWorship;
import com.emoniph.witchery.client.renderer.RenderStatueWerewolf;
import com.emoniph.witchery.client.renderer.RenderStatueWolf;
import com.emoniph.witchery.client.renderer.RenderStockade;
import com.emoniph.witchery.client.renderer.RenderToad;
import com.emoniph.witchery.client.renderer.RenderTreefyd;
import com.emoniph.witchery.client.renderer.RenderVampire;
import com.emoniph.witchery.client.renderer.RenderWingedMonkey;
import com.emoniph.witchery.client.renderer.RenderWitchCat;
import com.emoniph.witchery.client.renderer.RenderWitchHand;
import com.emoniph.witchery.client.renderer.RenderWitchHunter;
import com.emoniph.witchery.client.renderer.RenderWitchProjectile;
import com.emoniph.witchery.client.renderer.RenderWitchesOven;
import com.emoniph.witchery.client.renderer.RenderWolfHead;
import com.emoniph.witchery.client.renderer.RenderWolfman;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.entity.EntityAttackBat;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDarkMark;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.entity.EntityHellhound;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntityMandrake;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityMirrorFace;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityParasyticLouse;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityVampire;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.entity.EntityWitchCat;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag;
import com.emoniph.witchery.item.ItemBrewBagGUI;
import com.emoniph.witchery.item.ItemEarmuffs.ClientEventHooks;
import com.emoniph.witchery.item.ItemLeonardsUrn.InventoryLeonardsUrn;
import com.emoniph.witchery.item.ItemLeonardsUrnGUI;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
  public static int RENDER_ID;
  
  public ClientProxy() {}
  
  public void registerRenderers()
  {
    RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
    
    MinecraftForgeClient.registerItemRenderer(ItemsWITCH_HAND, new RenderWitchHand());
    MinecraftForgeClient.registerItemRenderer(ItemsDEATH_HAND, new RenderDeathsHand());
    MinecraftForgeClient.registerItemRenderer(ItemsBREW_BAG, new RenderBrewBottle());
    MinecraftForgeClient.registerItemRenderer(ItemsHUNTSMANS_SPEAR, new RenderHuntsmanSpear());
    MinecraftForgeClient.registerItemRenderer(ItemsMYSTIC_BRANCH, new RenderMysticBranch());
    MinecraftForgeClient.registerItemRenderer(ItemsCROSSBOW_PISTOL, new RenderHandBow());
    MinecraftForgeClient.registerItemRenderer(ItemsCANE_SWORD, new RenderCaneSword());
    
    RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new RenderDemon(new ModelDemon(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderBroom());
    RenderingRegistry.registerEntityRenderingHandler(EntityWitchProjectile.class, new RenderWitchProjectile(ItemsGENERIC));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityFamiliar.class, new RenderFamiliar(new ModelFamiliarPig(), 0.8F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityMandrake.class, new RenderMandrake(new ModelMandrake(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityTreefyd.class, new RenderTreefyd(new ModelTreefyd(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityHornedHuntsman.class, new RenderHornedAvatar(new ModelHornedAvatar(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntitySpellEffect.class, new RenderSpellEffect(0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityEnt.class, new RenderEnt(new ModelEnt(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityIllusionCreeper.class, new RenderIllusion(new ModelCreeper(), new ResourceLocation("textures/entity/creeper/creeper.png")));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityIllusionSpider.class, new RenderIllusion(new ModelSpider(), new ResourceLocation("textures/entity/spider/spider.png")));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityIllusionZombie.class, new RenderIllusion(new ModelZombie(), new ResourceLocation("textures/entity/zombie/zombie.png")));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, new RenderOwl(new ModelOwl(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, new RenderToad(new ModelToad(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityWitchCat.class, new RenderWitchCat(new ModelOcelot(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityParasyticLouse.class, new RenderParasyticLouse());
    RenderingRegistry.registerEntityRenderingHandler(EntityBabaYaga.class, new RenderBabaYaga());
    RenderingRegistry.registerEntityRenderingHandler(EntityCovenWitch.class, new RenderCovenWitch());
    RenderingRegistry.registerEntityRenderingHandler(EntityCorpse.class, new RenderCorpse());
    RenderingRegistry.registerEntityRenderingHandler(EntityNightmare.class, new RenderNightmare());
    RenderingRegistry.registerEntityRenderingHandler(EntitySpectre.class, new RenderSpectre());
    RenderingRegistry.registerEntityRenderingHandler(EntityPoltergeist.class, new RenderPoltergeist());
    RenderingRegistry.registerEntityRenderingHandler(EntityBanshee.class, new RenderBanshee());
    RenderingRegistry.registerEntityRenderingHandler(EntitySpirit.class, new RenderSpirit());
    RenderingRegistry.registerEntityRenderingHandler(EntityDeath.class, new RenderDeath());
    RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderBolt());
    RenderingRegistry.registerEntityRenderingHandler(EntityWitchHunter.class, new RenderWitchHunter());
    RenderingRegistry.registerEntityRenderingHandler(EntityLordOfTorment.class, new RenderLordOfTorment());
    RenderingRegistry.registerEntityRenderingHandler(EntityImp.class, new RenderImp());
    RenderingRegistry.registerEntityRenderingHandler(EntityDarkMark.class, new RenderDarkMark());
    RenderingRegistry.registerEntityRenderingHandler(EntityMindrake.class, new RenderMindrake());
    RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, new RenderGoblin(new ModelGoblin(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityGoblinMog.class, new RenderGoblinMog(new ModelGoblinMog(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityGoblinGulg.class, new RenderGoblinGulg(new ModelGoblinGulg(), 0.5F));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityBrew.class, new RenderBrew(ItemsBREW));
    RenderingRegistry.registerEntityRenderingHandler(EntityDroplet.class, new RenderDroplet(ItemsBREW));
    

    RenderingRegistry.registerEntityRenderingHandler(EntitySplatter.class, new RenderSplatter(ItemsBREW));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityLeonard.class, new RenderLeonard(new ModelLeonard(), 0.5F));
    
    RenderingRegistry.registerEntityRenderingHandler(EntityLostSoul.class, new RenderSpirit());
    RenderingRegistry.registerEntityRenderingHandler(EntityWolfman.class, new RenderWolfman(new ModelWolfman(), 0.5F));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityHellhound.class, new RenderHellhound(new ModelHellhound(), 0.5F));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityVillagerWere.class, new RenderVillager());
    
    RenderingRegistry.registerEntityRenderingHandler(EntityVillageGuard.class, new com.emoniph.witchery.client.renderer.RenderVillageGuard());
    RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire());
    
    RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(ItemsSUN_GRENADE));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityLilith.class, new RenderLilith(new ModelLilith(), 0.5F));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityFollower.class, new RenderFollower(new ModelBiped()));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityWingedMonkey.class, new RenderWingedMonkey(new ModelMonkey(), 0.5F));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityAttackBat.class, new net.minecraft.client.renderer.entity.RenderBat());
    
    RenderingRegistry.registerEntityRenderingHandler(EntityMirrorFace.class, new RenderMirrorFace());
    RenderingRegistry.registerEntityRenderingHandler(EntityReflection.class, new com.emoniph.witchery.client.renderer.RenderReflection());
    
    bindRenderer(com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf.class, new RenderPoppetChest(), new Item[] { Item.func_150898_a(BlocksPOPPET_SHELF) });
    
    bindRenderer(BlockGrassper.TileEntityGrassper.class, new RenderGrassper(), new Item[] { Item.func_150898_a(BlocksGRASSPER) });
    
    bindRenderer(BlockDistillery.TileEntityDistillery.class, new com.emoniph.witchery.client.renderer.RenderDistillery(), new Item[] { Item.func_150898_a(BlocksDISTILLERY_IDLE) });
    
    bindRenderer(BlockWitchesOven.TileEntityWitchesOven.class, new RenderWitchesOven(), new Item[] { Item.func_150898_a(BlocksOVEN_IDLE) });
    
    bindRenderer(BlockDreamCatcher.TileEntityDreamCatcher.class, new RenderDreamCatcher(), new Item[] { Item.func_150898_a(BlocksDREAM_CATCHER) });
    
    bindRenderer(BlockChalice.TileEntityChalice.class, new RenderChalice(), new Item[] { Item.func_150898_a(BlocksCHALICE) });
    bindRenderer(BlockCandelabra.TileEntityCandelabra.class, new RenderCandelabra(), new Item[] { Item.func_150898_a(BlocksCANDELABRA) });
    
    bindRenderer(BlockCrystalBall.TileEntityCrystalBall.class, new RenderCrystalBall(), new Item[] { Item.func_150898_a(BlocksCRYSTAL_BALL) });
    
    bindRenderer(BlockKettle.TileEntityKettle.class, new RenderKettle(), new Item[] { Item.func_150898_a(BlocksKETTLE) });
    bindRenderer(BlockLeechChest.TileEntityLeechChest.class, new RenderLeechChest(), new Item[] { Item.func_150898_a(BlocksLEECH_CHEST) });
    
    bindRenderer(BlockStatueGoddess.TileEntityStatueGoddess.class, new RenderGoddess(), new Item[] { Item.func_150898_a(BlocksSTATUE_GODDESS) });
    
    bindRenderer(BlockSpinningWheel.TileEntitySpinningWheel.class, new RenderSpinningWheel(), new Item[] { Item.func_150898_a(BlocksSPINNING_WHEEL) });
    
    bindRenderer(BlockBrazier.TileEntityBrazier.class, new RenderBrazier(), new Item[] { Item.func_150898_a(BlocksBRAZIER) });
    bindRenderer(BlockAreaMarker.TileEntityAreaCurseProtect.class, new RenderStatueWolf(), new Item[] { Item.func_150898_a(BlocksDECURSE_DIRECTED) });
    
    bindRenderer(BlockAreaMarker.TileEntityAreaTeleportPullProtect.class, new RenderStatueMandrake(), new Item[] { Item.func_150898_a(BlocksDECURSE_TELEPORT) });
    

    bindRenderer(BlockStatueOfWorship.TileEntityStatueOfWorship.class, new RenderStatueOfWorship(), new Item[] { Item.func_150898_a(BlocksSTATUE_OF_WORSHIP) });
    

    bindRenderer(BlockPlacedItem.TileEntityPlacedItem.class, new RenderPlacedItem(), new Item[0]);
    bindRenderer(com.emoniph.witchery.blocks.BlockAlluringSkull.TileEntityAlluringSkull.class, new RenderAlluringSkull(), new Item[0]);
    bindRenderer(BlockDemonHeart.TileEntityDemonHeart.class, new RenderDemonHeart(), new Item[0]);
    
    bindRenderer(TileEntityCauldron.class, new RenderCauldron(), new Item[] { Item.func_150898_a(BlocksCAULDRON) });
    

    bindRenderer(BlockStatueWerewolf.TileEntityStatueWerewolf.class, new RenderStatueWerewolf(), new Item[] { Item.func_150898_a(BlocksWOLF_ALTAR) });
    

    bindRenderer(BlockSilverVat.TileEntitySilverVat.class, new RenderSilverVat(), new Item[] { Item.func_150898_a(BlocksSILVER_VAT) });
    

    bindRenderer(BlockBeartrap.TileEntityBeartrap.class, new RenderBeartrap(), new Item[] { Item.func_150898_a(BlocksBEARTRAP), Item.func_150898_a(BlocksWOLFTRAP) });
    

    bindRenderer(BlockWolfHead.TileEntityWolfHead.class, new RenderWolfHead(), new Item[0]);
    bindRenderer(BlockCoffin.TileEntityCoffin.class, new RenderCoffin(), new Item[0]);
    bindRenderer(BlockGarlicGarland.TileEntityGarlicGarland.class, new RenderGarlicGarland(), new Item[0]);
    
    bindRenderer(BlockBloodCrucible.TileEntityBloodCrucible.class, new RenderBloodCrucible(), new Item[] { Item.func_150898_a(BlocksBLOOD_CRUCIBLE), Item.func_150898_a(BlocksBLOOD_CRUCIBLE) });
    


    bindRenderer(BlockMirror.TileEntityMirror.class, new RenderMirror(), new Item[0]);
    
    RenderFumeFunnel funnelRenderer = new RenderFumeFunnel(false);
    bindRenderer(BlockFumeFunnel.TileEntityFumeFunnel.class, funnelRenderer, new Item[0]);
    BlockFumeFunnel.TileEntityFumeFunnel dummyFunnelTile = new BlockFumeFunnel.TileEntityFumeFunnel();
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksOVEN_FUMEFUNNEL), new RenderBlockItem(funnelRenderer, dummyFunnelTile));
    
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksOVEN_FUMEFUNNEL_FILTERED), new RenderBlockItem(funnelRenderer, new BlockFumeFunnel.TileEntityFumeFunnel()));
    

    RenderFetish fetishRenderer = new RenderFetish();
    bindRenderer(BlockFetish.TileEntityFetish.class, fetishRenderer, new Item[0]);
    BlockFetish.TileEntityFetish dummyFetishTile = new BlockFetish.TileEntityFetish();
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksFETISH_SCARECROW), new RenderFetish.RenderFetishBlockItem(BlocksFETISH_SCARECROW, fetishRenderer, dummyFetishTile));
    
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksFETISH_TREANT_IDOL), new RenderFetish.RenderFetishBlockItem(BlocksFETISH_TREANT_IDOL, fetishRenderer, dummyFetishTile));
    

    RenderingRegistry.registerBlockHandler(STOCKADE_RENDER_ID, new RenderStockade());
    RenderingRegistry.registerBlockHandler(GAS_RENDER_ID, new RenderBrewGas());
    RenderingRegistry.registerBlockHandler(BREW_LIQUID_RENDER_ID, new RenderBrewLiquid());
    RenderingRegistry.registerBlockHandler(VINE_RENDER_ID, new RenderWitchVine());
    
    RenderingRegistry.registerBlockHandler(PITGRASS_RENDER_ID, new RenderPitGrass());
  }
  
  private static final int STOCKADE_RENDER_ID = ;
  private static final int GAS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  private static final int BREW_LIQUID_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  private static final int VINE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  private static final int PITGRASS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  
  public int getStockageRenderId()
  {
    return STOCKADE_RENDER_ID;
  }
  
  public int getPitGrassRenderId()
  {
    return PITGRASS_RENDER_ID;
  }
  
  public int getGasRenderId()
  {
    return GAS_RENDER_ID;
  }
  
  public int getBrewLiquidRenderId() {
    return BREW_LIQUID_RENDER_ID;
  }
  
  public int getVineRenderId() {
    return VINE_RENDER_ID;
  }
  




  private void bindRenderer(Class<? extends TileEntity> clazz, TileEntitySpecialRenderer render, Item... items)
  {
    ClientRegistry.bindTileEntitySpecialRenderer(clazz, render);
    for (Item item : items) {
      if (item != null) {
        try {
          MinecraftForgeClient.registerItemRenderer(item, new RenderBlockItem(render, (TileEntity)clazz.newInstance()));
        }
        catch (IllegalAccessException ex) {}catch (InstantiationException ex) {}
      }
    }
  }
  


  public void registerHandlers()
  {
    super.registerHandlers();
  }
  
  public void registerEvents()
  {
    super.registerEvents();
    MinecraftForge.EVENT_BUS.register(new ClientEvents());
    MinecraftForge.EVENT_BUS.register(new WitcheryPotions.ClientEventHooks());
    MinecraftForge.EVENT_BUS.register(new ItemEarmuffs.ClientEventHooks());
  }
  

  public void postInit() {}
  

  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch (ID) {
    case 0: 
      return new BlockAltarGUI((BlockAltar.TileEntityAltar)world.func_147438_o(x, y, z));
    case 1: 
      return new GuiScreenWitchcraftBook(player, player.func_70694_bm());
    case 2: 
      return new BlockWitchesOvenGUI(field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
    case 3: 
      return new com.emoniph.witchery.blocks.BlockDistilleryGUI(field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
    case 4: 
      return new BlockSpinningWheelGUI(field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
    
    case 5: 
      return new ItemBrewBagGUI(field_71071_by, new ItemBrewBag.InventoryBrewBag(player));
    case 6: 
      return new GuiScreenBiomeBook(player, player.func_70694_bm());
    case 7: 
      return new GuiScreenMarkupBook(player, player.func_70694_bm());
    case 8: 
      return new ItemLeonardsUrnGUI(field_71071_by, new ItemLeonardsUrn.InventoryLeonardsUrn(player));
    }
    return null;
  }
  

  public boolean getGraphicsLevel()
  {
    return func_71410_xfield_71474_y.field_74347_j;
  }
  
  public static final ResourceLocation APOTHECARY_TEXTURE = new ResourceLocation("witchery:textures/entities/apothecary.png");
  

  public void registerVillagers()
  {
    super.registerVillagers();
    if (instancegenerateApothecaries) {
      VillagerRegistry.instance().registerVillagerSkin(instanceapothecaryID, APOTHECARY_TEXTURE);
    }
  }
  

  public void generateParticle(World worldObj, double posX, double posY, double posZ, float r, float g, float b, int ttl, float gravity)
  {
    if (field_72995_K) {
      NaturePowerFX sparkle = new NaturePowerFX(worldObj, posX, posY, posZ);
      sparkle.setMaxAge(ttl);
      field_70145_X = true;
      sparkle.func_70538_b(r, g, b);
      sparkle.setGravity(gravity);
      func_71410_xfield_71452_i.func_78873_a(sparkle);
    }
  }
  
  public EntityPlayer getPlayer(MessageContext ctx)
  {
    if (side == Side.SERVER) {
      return getServerHandlerfield_147369_b;
    }
    return func_71410_xfield_71439_g;
  }
  

  public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle)
  {
    if (sound != SoundEffect.NONE) {
      world.func_72980_b(x, y, z, sound.toString(), 0.5F, 0.4F / ((float)field_73012_v.nextDouble() * 0.4F + 0.8F), false);
    }
    

    int effectCount = Math.min(MathHelper.func_76143_f(Math.max(width, 1.0D) * 20.0D), 300);
    for (int i = 0; i < effectCount; i++) {
      double d0 = field_73012_v.nextGaussian() * 0.02D;
      double d1 = field_73012_v.nextGaussian() * 0.02D;
      double d2 = field_73012_v.nextGaussian() * 0.02D;
      if (particle == ParticleEffect.SPELL_COLORED) {
        EntitySmokeFX sparkle = new EntitySmokeFX(world, x + field_73012_v.nextDouble() * width * 2.0D - width, y + field_73012_v.nextDouble() * height, z + field_73012_v.nextFloat() * width * 2.0D - width, 0.0D, 0.0D, 0.0D);
        

        field_70145_X = true;
        float red = (color >>> 16 & 0xFF) / 256.0F;
        float green = (color >>> 8 & 0xFF) / 256.0F;
        float blue = (color & 0xFF) / 256.0F;
        sparkle.func_70538_b(red, green, blue);
        func_71410_xfield_71452_i.func_78873_a(sparkle);
      } else {
        world.func_72869_a(particle.toString(), x + field_73012_v.nextDouble() * width * 2.0D - width, y + field_73012_v.nextDouble() * height, z + field_73012_v.nextFloat() * width * 2.0D - width, 0.0D, 0.0D, 0.0D);
      }
    }
  }
}
