package ru.myosin.geo.model

@ConsistentCopyVisibility
data class Bounds private constructor(val north: Double, val west: Double, val south: Double, val east: Double){
    companion object Utils
    class Builder(var north: Double? = null, var west: Double? = null, var south: Double? = null, var east: Double? = null){
        constructor(block: Builder.() -> Unit):this(){ apply(block) }

        private fun isValidNorth(): Boolean{
            return north?.let { it in -90.0..90.0 } == true
        }

        private fun isValidWest(): Boolean{
            return west?.let { it in -180.0..180.0 } == true
        }

        private fun isValidSouth(): Boolean{
            return south?.let { it in -90.0..90.0 } == true
        }

        private fun isValidEast(): Boolean{
            return east?.let { it in -180.0..180.0 } == true
        }

        operator fun not(): Boolean = !isValidNorth() || !isValidWest() || !isValidSouth() || !isValidEast()

        fun build(): Result<Bounds> = if( not() ){
            Result.failure<Bounds>(IllegalArgumentException(when{
                listOf(north,west,south,east).all { it == null } -> "The builder is empty."
                north == null -> "North is not initialized."
                north!! !in -90.0..90.0 -> "The north value does not correspond to the range of -90..90."
                west == null -> "West is not initialized."
                west!! !in -180.0..180.0 -> "The west value does not correspond to the range of -180..180."
                south == null -> "South is not initialized."
                south!! !in -90.0..90.0 -> "The south value does not correspond to the range of -90..90."
                east == null -> "East is not initialized."
                east!! !in -180.0..180.0 -> "The east value does not correspond to the range of -180..180."
                else -> "Unknown error"
            }))
        }else{
            val northSouth = listOf(north!!,south!!)
            val westEast = listOf(west!!,east!!)
            Result.success<Bounds>(Bounds(
                north = northSouth.max(),
                west = westEast.min(),
                south = northSouth.min(),
                east = westEast.max()
            ))
        }
    }
}