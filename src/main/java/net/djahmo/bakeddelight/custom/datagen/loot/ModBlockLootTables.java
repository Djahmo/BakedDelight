package net.djahmo.bakeddelight.custom.datagen.loot;

import net.djahmo.bakeddelight.custom.block.crop.EggplantCropBlock;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.djahmo.bakeddelight.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.BAKING_DISH.get());

        this.dropSelf(ModBlocks.UNCOOKED_LASAGNA_DISH.get());
        this.dropOther(ModBlocks.COOKED_LASAGNA_DISH.get(), ModBlocks.BAKING_DISH.get());

        this.dropSelf(ModBlocks.UNCOOKED_GRATIN_DISH.get());
        this.dropOther(ModBlocks.COOKED_GRATIN_DISH.get(), ModBlocks.BAKING_DISH.get());


        LootItemCondition.Builder dropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.EGGPLANT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EggplantCropBlock.AGE, 5));

        this.add(ModBlocks.EGGPLANT_CROP.get(), createCropDrops(ModBlocks.EGGPLANT_CROP.get(), ModItems.EGGPLANT.get(), ModItems.EGGPLANT_SEED.get(), dropCondition));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
