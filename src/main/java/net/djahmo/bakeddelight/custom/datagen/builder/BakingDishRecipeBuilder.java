package net.djahmo.bakeddelight.custom.datagen.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.djahmo.bakeddelight.custom.datagen.serializer.ModRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class BakingDishRecipeBuilder {

    private final ItemLike result;
    private final ItemLike firstIngredient;
    private final ItemLike secondIngredient;
    private final ItemLike thirdIngredient;
    private final ItemLike fourthIngredient;
    private final ItemLike fifthIngredient;
    private final ItemLike sixthIngredient;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private BakingDishRecipeBuilder(ItemLike mainResult, ItemLike firstIngredient, ItemLike secondIngredient, ItemLike thirdIngredient, ItemLike fourthIngredient, ItemLike fifthIngredient, ItemLike sixthIngredient) {
        this.result = mainResult;
        this.firstIngredient = firstIngredient;
        this.secondIngredient = secondIngredient;
        this.thirdIngredient = thirdIngredient;
        this.fourthIngredient = fourthIngredient;
        this.fifthIngredient = fifthIngredient;
        this.sixthIngredient = sixthIngredient;
    }


    public static BakingDishRecipeBuilder bakindDishRecipe(ItemLike mainResult, ItemLike firstIngredient, ItemLike secondIngredient, ItemLike thirdIngredient, ItemLike fourthIngredient, ItemLike fifthIngredient, ItemLike sixthIngredient) {
        return new BakingDishRecipeBuilder(mainResult, firstIngredient, secondIngredient, thirdIngredient, fourthIngredient, fifthIngredient, sixthIngredient);
    }

    public BakingDishRecipeBuilder unlockedBy(String criterionName, ItemLike... items) {
        this.advancement.addCriterion(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        if (!this.advancement.getCriteria().isEmpty()) {
            this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
            ResourceLocation advancementId = new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
            consumerIn.accept(new BakingDishRecipeBuilder.Result(id, this.result, this.firstIngredient, this.secondIngredient, this.thirdIngredient, this.fourthIngredient, this.fifthIngredient, this.sixthIngredient, this.advancement, advancementId));
        } else {
            consumerIn.accept(new BakingDishRecipeBuilder.Result(id, this.result, this.firstIngredient, this.secondIngredient, this.thirdIngredient, this.fourthIngredient, this.fifthIngredient, this.sixthIngredient));
        }

    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemLike result;
        private final ItemLike firstIngredient;
        private final ItemLike secondIngredient;
        private final ItemLike thirdIngredient;
        private final ItemLike fourthIngredient;
        private final ItemLike fifthIngredient;
        private final ItemLike sixthIngredient;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, ItemLike mainResult, ItemLike firstIngredient, ItemLike secondIngredient, ItemLike thirdIngredient, ItemLike fourthIngredient, ItemLike fifthIngredient, ItemLike sixthIngredient, @Nullable Advancement.Builder advancement, @Nullable ResourceLocation advancementId) {
            this.id = idIn;
            this.result = mainResult;
            this.firstIngredient = firstIngredient;
            this.secondIngredient = secondIngredient;
            this.thirdIngredient = thirdIngredient;
            this.fourthIngredient = fourthIngredient;
            this.fifthIngredient = fifthIngredient;
            this.sixthIngredient = sixthIngredient;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public Result(ResourceLocation idIn, ItemLike mainResult, ItemLike firstIngredient, ItemLike secondIngredient, ItemLike thirdIngredient, ItemLike fourthIngredient, ItemLike fifthIngredient, ItemLike sixthIngredient) {
            this(idIn, mainResult, firstIngredient, secondIngredient, thirdIngredient, fourthIngredient, fifthIngredient, sixthIngredient, null,null);
        }

        public void serializeRecipeData(JsonObject json) {

            JsonArray arrayIngredients = new JsonArray();

            arrayIngredients.add(Ingredient.of(this.firstIngredient).toJson());
            arrayIngredients.add(Ingredient.of(this.secondIngredient).toJson());
            arrayIngredients.add(Ingredient.of(this.thirdIngredient).toJson());
            arrayIngredients.add(Ingredient.of(this.fourthIngredient).toJson());
            arrayIngredients.add(Ingredient.of(this.fifthIngredient).toJson());
            arrayIngredients.add(Ingredient.of(this.sixthIngredient).toJson());

            json.add("ingredients", arrayIngredients);

            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result.asItem())).toString());
            json.add("result", objectResult);

        }

        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.BAKING_DISH.get();
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement != null ? this.advancement.serializeToJson() : null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
