package com.builtbroken.fleamob;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

public class EntityRegistry {

	private static int modEntities = 0;
	
	public static EntityEntry getEntry(boolean shouldSpawn, Class<? extends Entity> EntityClass, String entityNameIn, int primaryColor, int secondaryColor, EnumCreatureType type, int minSpawn, int maxSpawn, int spawnWeight, Biome[] biomes) {
		return shouldSpawn ? buildEntrySpawn(EntityClass, entityNameIn, primaryColor, secondaryColor, type, minSpawn, maxSpawn, spawnWeight, biomes) : buildEntryNoSpawn(EntityClass, entityNameIn, primaryColor, secondaryColor);
	}
	
	public static EntityEntry buildEntrySpawn(Class<? extends Entity> EntityClass, String entityNameIn, int primaryColor, int secondaryColor, EnumCreatureType type, int minSpawn, int maxSpawn, int spawnWeight, Biome[] biomes){
		return EntityEntryBuilder.create()
				.entity(EntityClass)
				.id(new ResourceLocation(FleaMobMod.MODID, entityNameIn), modEntities++)
				.name(FleaMobMod.MODID + "." + entityNameIn)
				.tracker(64, 1, true)
				.egg(primaryColor, secondaryColor)
				.spawn(type, spawnWeight, minSpawn, maxSpawn, biomes)
				.build();
	}
	
	public static EntityEntry buildEntryNoSpawn(Class<? extends Entity> EntityClass, String entityNameIn, int primaryColor, int secondaryColor){
		return EntityEntryBuilder.create()
				.entity(EntityClass)
				.id(new ResourceLocation(FleaMobMod.MODID, entityNameIn), modEntities++)
				.name(FleaMobMod.MODID + "." + entityNameIn)
				.tracker(64, 1, true)
				.egg(primaryColor, secondaryColor)
				.build();
	}


	@EventBusSubscriber(modid = FleaMobMod.MODID)
	public static class RegistrationHandler
	{
		/**
		 * Register this mod's {@link EntityEntry}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<EntityEntry> event)
		{
			final IForgeRegistry<EntityEntry> registry = event.getRegistry();
			
            List<Biome> biomesList = new ArrayList();
			
			if(FleaConfig.SHOULD_SPAWN && FleaConfig.BIOMES.length > 0) {
				
	            //Build supported biome list
	            for (String id : FleaConfig.BIOMES)
	            {
	                Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(id));
	                if (biome != null)
	                {
	                    biomesList.add(biome);
	                }
	                else
	                {
	                    FleaMobMod.logger.error("EntityRegistry#onEvent() -> Failed to find a biome with id [" + id + "] while loading config data for entity registry");
	                }
	            }

	        }
			
            //Convert to array
            Biome[] biomes = new Biome[biomesList.size()];
            for (int i = 0; i < biomesList.size(); i++)
            {
                biomes[i] = biomesList.get(i);
            }
			
			EntityEntry flea = getEntry(FleaConfig.SHOULD_SPAWN, EntityFlea.class, "flea", 0xc16719, 0xbf5513, EnumCreatureType.MONSTER, FleaConfig.SPAWN_MIN, FleaConfig.SPAWN_MAX, FleaConfig.SPAWN_WEIGHT, biomes);
			registry.register(flea);
		}
	}

}