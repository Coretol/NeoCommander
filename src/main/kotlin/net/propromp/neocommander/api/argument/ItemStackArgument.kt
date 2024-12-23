package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import io.papermc.paper.command.brigadier.argument.ArgumentTypes
import net.minecraft.commands.arguments.item.ItemArgument
import net.minecraft.commands.arguments.item.ItemInput
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

@Suppress("UnstableApiUsage")
class ItemStackArgument(name: String) : NeoArgument<Any, ItemStack>(name) {
    override fun asBrigadier(): ArgumentType<ItemStack> = ArgumentTypes.itemStack()

    override fun parse(context: NeoCommandContext, t: Any): ItemStack {
        val itemStack = (t as ItemInput).createItemStack(1, false)

        return CraftItemStack.asBukkitCopy(itemStack)
    }
}