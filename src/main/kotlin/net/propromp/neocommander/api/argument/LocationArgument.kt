package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.coordinates.BlockPosArgument
import net.minecraft.commands.arguments.coordinates.Coordinates
import net.minecraft.commands.arguments.coordinates.Vec3Argument
import net.propromp.neocommander.api.NeoCommandContext
import net.propromp.neocommander.api.argument.annotation.LocationArgument
import org.bukkit.Location

class LocationArgument(name: String, val type: LocationType = LocationType.ENTITY) : NeoArgument<Any, Location>(name) {
    constructor(name: String, annotation: LocationArgument) : this(name, annotation.type)

    enum class LocationType {
        BLOCK, ENTITY
    }

    override fun asBrigadier() = when (type) {
        LocationType.BLOCK -> BlockPosArgument.blockPos()
        LocationType.ENTITY -> Vec3Argument.vec3()
    } as ArgumentType<out Any>

    override fun parse(context: NeoCommandContext, t: Any): Location {

        val vec3D = (t as Coordinates).getPosition(context.context.source as CommandSourceStack)

        val x = vec3D.x()
        val y = vec3D.y()
        val z = vec3D.z()
        return Location(context.source.world, x, y, z)
    }
}