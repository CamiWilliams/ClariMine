package com.cami.ClariMine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cami.ClariMine.client.ScreenshotHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(
    modid = ClariMine.ID,
    name = ClariMine.NAME,
    version = ClariMine.VERSION,
    useMetadata = true
)
public class ClariMine {
    
    public static final String NAME = "ClariMine";
    public static final String ID = NAME;
    public static final String VERSION = "cami is the best";
    
    @Instance(ID)
    public static ClariMine instance;
    
    private ModMetadata metadata;
    public int mobid;
    public ModMetadata getMetadata() {
        return metadata;
    }

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent evt) {
        metadata = evt.getModMetadata();
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		mobid = entityID;
		EntityRegistry.registerGlobalEntityID(ClariMine.EntityClarifaiMonster.class, "clarifaimonster", entityID);
		EntityRegistry.registerModEntity(ClariMine.EntityClarifaiMonster.class, "clarifaimonster", entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, (255 << 16) + (255 << 8) + 255, (255 << 16) + (255 << 8) + 255));
		EntityRegistry.addSpawn(ClariMine.EntityClarifaiMonster.class, 45, 10, 65, EnumCreatureType.MONSTER , clean(BiomeGenBase.getBiomeGenArray()));
    }
    
	public static BiomeGenBase[] clean(final BiomeGenBase[] v) {
		List<BiomeGenBase> list = new ArrayList<BiomeGenBase>(Arrays.asList(v));
		list.removeAll(Collections.singleton(null));
		return list.toArray(new BiomeGenBase[list.size()]);
	}
    
	public Entity spawnEntity(int var1, World var2, double var3, double var5, double var7) {
        if(var1==mobid) return new ClariMine.EntityClarifaiMonster(var2);
        else return null;
    }
	
    @EventHandler
    public void onInit(FMLInitializationEvent evt) {
        ScreenshotHandler sch = new ScreenshotHandler();
        FMLCommonHandler.instance().bus().register(sch);
        FMLCommonHandler.instance().bus().register(this);
		RenderingRegistry.registerEntityRenderingHandler(ClariMine.EntityClarifaiMonster.class, new RenderBiped(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 0, 0){protected ResourceLocation getEntityTexture(Entity par1Entity){return new ResourceLocation("cm:textures/skin.png");}});
    }
    
    
    
    public static class EntityClarifaiMonster extends EntityCreeper {
    	
    	World world = null;
 	    public EntityClarifaiMonster(World var1) {
 	        super(var1);
 	        world = var1;
 	        experienceValue = 5;
 	        this.isImmuneToFire = true;
 	        addRandomArmor();
 			setNoAI(!false);
         	
 	    }
 	    
 	    protected void addRandomArmor(){
 	    	this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
 	    	this.setCurrentItemOrArmor(4, new ItemStack(Items.iron_helmet));
 	    	this.setCurrentItemOrArmor(3, new ItemStack(Items.golden_chestplate));
 	    	this.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_leggings));
 	    	this.setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_boots));
 	    }
 	    
 	    protected void dropRareDrop(int par1){
 	    	this.dropItem(Items.melon, 1);
 	    }
 		
 		@Override
 		protected Item getDropItem() {
 			return new ItemStack(Items.apple).getItem();
 		}

 	    @Override
 	    protected String getLivingSound() {
 	        return "mob.enderdragon.growl";
 	    }

 	    @Override
 	    protected String getHurtSound() {
 	        return "mob.enderdragon.hit";
 	    }
 		
 		@Override
 	    protected String getDeathSound() {
 	        return "mob.enderdragon.end";
 	    }

 		@Override
 	    public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
 			super.onStruckByLightning(entityLightningBolt);
 			int i = (int)this.posX;
 			int j = (int)this.posY;
 			int k = (int)this.posZ;
 			Entity entity = this;	
 		}

 		@Override
 		public void fall(float l, float d) {
 			super.fall(l,d);
 			int i = (int)this.posX;
 			int j = (int)this.posY;
 			int k = (int)this.posZ;
 			super.fall(l,d);
 			Entity entity = this;	
 		}

 		@Override
 		public void onDeath(DamageSource source) {
 			super.onDeath(source);
 			int i = (int)this.posX;
 			int j = (int)this.posY;
 			int k = (int)this.posZ;
 			Entity entity = this;
 			
 			if(true) {
 				System.out.println("killed");
 	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("Download Forevery!");
 			}		
 		}

 		@Override
 		public boolean interact(EntityPlayer entity) {
 			super.interact(entity);
 			int i = (int)this.posX;
 			int j = (int)this.posY;
 			int k = (int)this.posZ;
 			return true;
 		}
 		
 		@Override
 		protected float getSoundVolume() {
 			return 1.0F;
 		}
 	}
}

