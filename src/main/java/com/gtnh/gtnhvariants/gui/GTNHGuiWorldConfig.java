package com.gtnh.gtnhvariants.gui;

import java.util.Arrays;
import java.util.List;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GTNHGuiWorldConfig {
    private static final List<String> validOptions = Arrays
        .asList("Normal", "Garden of Grind", "Hellish Grind", "Im no Rocket Man", "Resources o'plenty");
    private static final List<String> validOptionsDesc = Arrays.asList(
        "Just a normal world, like you know it",
        "You spawn in and are locked to an completely empty Overworld.",
        "You spawn in and are locked to the Nether",
        "GalaxyCraft Rockets are NOT available.",
        "You get early access to Void Miners.");


    private static GTNHWorldConfig worldConfig = GTNHWorldConfig.instance();

    public static String getCurrentOptionName() {
        return validOptions.get(worldConfig.worldGenVariant.ordinal());
    }

    public static String getCurrentOptionDescription() {
        return validOptionsDesc.get(worldConfig.worldGenVariant.ordinal());
    }

    public static void cycleWorldOptions() {
        int next_value = (worldConfig.worldGenVariant.ordinal() + 1) % GTNHWorldConfig.Options.values().length;
        worldConfig.worldGenVariant = GTNHWorldConfig.Options.values()[next_value];
    }

    public static void clear() {
        worldConfig.worldGenVariant  = GTNHWorldConfig.Options.NORMAL;
    }
}
