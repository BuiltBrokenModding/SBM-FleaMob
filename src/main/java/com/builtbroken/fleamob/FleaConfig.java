package com.builtbroken.fleamob;

import net.minecraftforge.common.config.Config;

@Config(modid = FleaMobMod.MODID, name = "sbmfleamob")
public class FleaConfig {
	
    @Config.Name("should_spawn")
    @Config.Comment("Should the flea spawn in the world")
    @Config.RequiresMcRestart
    public static boolean SHOULD_SPAWN = true;

    @Config.Name("spawn_weight")
    @Config.Comment("How likely the entity is to spawn. Higher the value the higher the spawn chance")
    @Config.RequiresMcRestart
    public static int SPAWN_WEIGHT = 15;

    @Config.Name("spawn_group_min")
    @Config.Comment("Smallest number to spawn in a group.")
    @Config.RequiresMcRestart
    public static int SPAWN_MIN = 1;

    @Config.Name("spawn_group_max")
    @Config.Comment("Largest number to spawn in a group.")
    @Config.RequiresMcRestart
    public static int SPAWN_MAX = 2;

    @Config.Name("spawn_biomes")
    @Config.Comment("Biomes to spawn entities inside")
    @Config.RequiresMcRestart
    public static String[] BIOMES = new String[]{"minecraft:desert_hills", "minecraft:mutated_desert", "minecraft:mesa", "minecraft:desert", "minecraft:mesa_rock", "minecraft:mesa_clear_rock"};
}