package net.roetem.tutorialmod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.roetem.tutorialmod.TutorialMod;
import net.roetem.tutorialmod.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EBONY_PLACED_KEY = createKey("ebony_placed");

    public static final ResourceKey<PlacedFeature> BLACK_OPAL_PLACED_KEY = createKey("black_opal_placed");
    public static final ResourceKey<PlacedFeature> END_BLACK_OPAL_PLACED_KEY = createKey("end_black_opal_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BLACK_OPAL_PLACED_KEY = createKey("nether_black_opal_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, EBONY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, .1f, 2),
                        ModBlocks.EBONY_SAPLING.get()));

        register(context, BLACK_OPAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAIN_BLACK_OPAL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(10,    // veins per chunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(30))));
        register(context, NETHER_BLACK_OPAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BLACK_OPAL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(10,    // veins per chunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, END_BLACK_OPAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_BLACK_OPAL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(10,    // veins per chunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));

    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TutorialMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
