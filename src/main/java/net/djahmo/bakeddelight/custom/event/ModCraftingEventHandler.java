package net.djahmo.bakeddelight.custom.event;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.block.BackingDishTypeCollection;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.tag.ForgeTags;

@Mod.EventBusSubscriber(modid = BakedDelight.MODID)
public class ModCraftingEventHandler {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        BackingDishTypeCollection.initialiseCookedDishItems();
        ItemStack foundItem = findDynamicItemInInventory(event);
        if (!foundItem.isEmpty()) {
            handleFoundItem(event, foundItem);
        }
    }

    private static ItemStack findDynamicItemInInventory(PlayerEvent.ItemCraftedEvent event) {
        for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
            ItemStack stack = event.getInventory().getItem(i);
            if (BackingDishTypeCollection
                    .containsItem(stack.getItem())) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private static void handleFoundItem(PlayerEvent.ItemCraftedEvent event, ItemStack foundItem) {
        Player player = (Player) event.getEntity();
        ItemStack dish = ModBlocks.BAKING_DISH.get().asItem().getDefaultInstance();
        if (!player.getInventory().add(dish.copy())) {
            player.drop(dish, false);
        }

        applyToolDurability(event.getInventory(), event.getEntity().getInventory());

        foundItem.shrink(1);
        if (foundItem.isEmpty()) {
            int slotIndex = findSlotContainingItem(event.getInventory(), foundItem);
            if (slotIndex != -1) {
                event.getInventory().setItem(slotIndex, ItemStack.EMPTY);
            }
        }
    }

    private static void applyToolDurability(Container inventory, Container playerInventory) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.is(ForgeTags.TOOLS_KNIVES)) {
                ItemStack damagedTool = stack.copy();
                int newDamage = damagedTool.getDamageValue() + 1;
                if (newDamage >= damagedTool.getMaxDamage()) {
                    damagedTool.setCount(0);
                } else {
                    damagedTool.setDamageValue(newDamage);
                }
                playerInventory.setItem(i, damagedTool);
                break;
            }
        }
    }

    private static int findSlotContainingItem(Container inventory, ItemStack searchStack) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == searchStack.getItem()) {
                return i;
            }
        }
        return -1;
    }
}
