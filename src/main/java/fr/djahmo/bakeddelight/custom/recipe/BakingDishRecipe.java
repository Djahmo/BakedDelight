package fr.djahmo.bakeddelight.custom.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.registry.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BakingDishRecipe implements Recipe<RecipeWrapper> {

    private final ResourceLocation id;
    private final NonNullList<Ingredient> recipeItems;
    private final ItemStack bakedDish;

    public BakingDishRecipe(ResourceLocation id, NonNullList<Ingredient> recipeItems, ItemStack result) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.bakedDish = result;
    }

    @Override
    public boolean matches(RecipeWrapper pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper pContainer) {
        return bakedDish;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return bakedDish.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public  RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BakingDishRecipe> {
        public Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "baking_dish";
    }

    public static class Serializer implements RecipeSerializer<BakingDishRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(BakedDelight.MODID,Type.ID);

        @Override
        public @NotNull BakingDishRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack bakeddish = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "bakeddish"));

            NonNullList<Ingredient> ingredients = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            return new BakingDishRecipe(id, ingredients, bakeddish);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty())
                    nonnulllist.add(ingredient);
            }
            return nonnulllist;
        }

        @Override
        public @Nullable BakingDishRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> ingredients = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack bakeddish = buf.readItem();
            return new BakingDishRecipe(id, ingredients, bakeddish);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BakingDishRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients())
                ing.toNetwork(buf);
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
