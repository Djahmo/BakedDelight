package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.loot.AddItemModifier;
import net.djahmo.bakeddelight.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, BakedDelight.MODID);
    }

    @Override
    protected void start() {
        add("eggplant_seed_from_grass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, ModItems.EGGPLANT_SEED.get()));

        add("zucchini_seed_from_grass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, ModItems.ZUCCHINI_SEED.get()));
    }
}
