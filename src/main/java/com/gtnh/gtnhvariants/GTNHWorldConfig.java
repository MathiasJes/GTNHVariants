package com.gtnh.gtnhvariants;

import net.minecraft.nbt.NBTTagCompound;

public class GTNHWorldConfig {

    private static final int CURRENT_CONFIG_VERSION = 1;

    public Options worldGenVariant = Options.NORMAL;

    private boolean netherOnly = false;
    private boolean portalsDisabled = false;
    private boolean rocketsDisabled = false;
    private int primaryDimension = 0;

    public enum Options {
        NORMAL,
        GOG,
        NETHERONLY,
        NOROCKET,
        // VOIDMINER
    }

    private static final GTNHWorldConfig INSTANCE = new GTNHWorldConfig();

    private static String GTNHWorldOptionsNBTTag = "GTNH";
    private String NBT_VERSION_TAG = "version";
    private String NBT_OPTION_TAG = "option";

    public static final GTNHWorldConfig instance() {
        return INSTANCE;
    }

    public boolean IsNetherOnly() {
        return netherOnly;
    }

    public boolean IsPortalsDisabled() {
        return portalsDisabled;
    }

    public boolean IsRocketsDisabled() {
        return rocketsDisabled;
    }

    public int GetPrimaryDimension() {
        return primaryDimension;
    }

    public boolean IsVoidWorld() {
        return worldGenVariant == Options.GOG;
    }

    private GTNHWorldConfig() {}

    public void SaveToNBT(NBTTagCompound nbtTagCompound) {
        NBTTagCompound gtnhTagCompound = new NBTTagCompound();
        gtnhTagCompound.setInteger(NBT_VERSION_TAG, CURRENT_CONFIG_VERSION);
        gtnhTagCompound.setInteger(NBT_OPTION_TAG, worldGenVariant.ordinal());

        nbtTagCompound.setTag(GTNHWorldOptionsNBTTag, gtnhTagCompound);
    }

    public void LoadFromNBT(NBTTagCompound nbtTagCompound) {
        NBTTagCompound gtnhTagCompound = nbtTagCompound.getCompoundTag(GTNHWorldOptionsNBTTag);

        int configVersion = gtnhTagCompound.getInteger(NBT_VERSION_TAG);
        switch (configVersion) {
            case 1:
                ParseConfigVersionOne(gtnhTagCompound);
                return;
            default:
                GTNHVariants.LOG.error("Attempted to load config with unknown version: " + configVersion);
                return;
        }
    }

    private void ParseConfigVersionOne(NBTTagCompound nbtTagCompound) {
        worldGenVariant = Options.values()[nbtTagCompound.getInteger(NBT_OPTION_TAG)];

        GTNHVariants.LOG.info("Loaded world config(v1). Current option: " + worldGenVariant.name());
        netherOnly = worldGenVariant == Options.NETHERONLY;
        portalsDisabled = worldGenVariant == Options.NETHERONLY || worldGenVariant == Options.GOG;
        rocketsDisabled = worldGenVariant == Options.NOROCKET || worldGenVariant == Options.NETHERONLY
            || worldGenVariant == Options.GOG;
        primaryDimension = (worldGenVariant == Options.NETHERONLY) ? -1 : 0;
    }
}
