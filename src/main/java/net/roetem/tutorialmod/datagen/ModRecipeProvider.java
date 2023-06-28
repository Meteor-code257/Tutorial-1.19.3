package net.roetem.tutorialmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.roetem.tutorialmod.block.ModBlocks;
import net.roetem.tutorialmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()), RecipeCategory.MISC,
                ModItems.BLACK_OPAL.get(), 0.7f, 200, "black_opal");
        oreBlasting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()), RecipeCategory.MISC,
                ModItems.BLACK_OPAL.get(), 0.7f, 100, "black_opal");


        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BLACK_OPAL.get(),
                RecipeCategory.MISC, ModBlocks.BLACK_OPAL_BLOCK.get());
    }
}
