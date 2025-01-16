package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.entity.Entity

class EntityArgument(name: String) :
    AbstractEntityArgument<Entity>(name) {

    override fun asBrigadier(): ArgumentType<out Any> {
        return EntityArgument.entity()
    }

    override fun parse(context: NeoCommandContext, t: Any): Entity {
        return (t as EntitySelector).findSingleEntity(context.context.source as CommandSourceStack).bukkitEntity
    }
}