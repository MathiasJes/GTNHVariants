package com.gtnh.gtnhvariants.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum Mixins implements IMixins {

    FML(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("FMLCommonHandlerMixin")),

    INTEGRATEDSERVER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("IntegratedServerMixin")),

    SERVERCONFIGURATIONMANAGER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("ServerConfigurationManagerMixin")),

    HELLPROVIDER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("WorldProviderHellMixin")),

    WORLDPROVIDER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("WorldProviderMixin")),

    CHUNKPROVIDERSERVER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("ChunkProviderServerMixin")),

    BLOCKPORTAL(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("BlockPortalMixin")),

    DIMENSIONMANAGER(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("DimensionManagerMixin")),

    TWILIGHTFOREST(new MixinBuilder().addRequiredMod(TargetedMod.TWILIGHTFOREST)
        .setPhase(Phase.LATE)
        .addClientMixins("TwilightForestMixin")),

    GALACTICRAFTCORE(new MixinBuilder().addRequiredMod(TargetedMod.GALACTICRAFTCORE)
        .setPhase(Phase.LATE)
        .addClientMixins("GalacticraftCoreMixin"));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
