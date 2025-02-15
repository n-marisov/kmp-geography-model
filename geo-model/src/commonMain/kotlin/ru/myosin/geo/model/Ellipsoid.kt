package ru.myosin.geo.model

/**
 * Эллипсоид.
 */
@ConsistentCopyVisibility
data class Ellipsoid private constructor(
    val equatorialRadius: Double,
    val polarRadius: Double,
    val firstFlattening: Double,
    val reverseFirstFlattening: Double,
    val secondFlattening: Double,
    val reverseSecondFlattening: Double,
    val thirdFlattening: Double,
    val reverseThirdFlattening: Double,
) {

    companion object Utils

    class Builder(var equatorialRadius: Double? = null, var polarRadius: Double? = null){

        constructor(block: Builder.() -> Unit):this(){ apply(block) }

        operator fun not(): Boolean{
            return listOf(equatorialRadius,polarRadius).any { it == null}
        }

        private fun calculate(block:()-> Double): Double{
            return if(polarRadius == equatorialRadius) 0.0 else run(block)
        }

        fun build(): Result<Ellipsoid>{
            if(not()) return Result.failure(IllegalArgumentException())

            val firstFlattening = calculate { (equatorialRadius!! - polarRadius!!) / equatorialRadius!! }
            val reverseFirstFlattening = calculate { 1 / firstFlattening }
            val secondFlattening = calculate { (equatorialRadius!! - polarRadius!!) / polarRadius!! }
            val reverseSecondFlattening = calculate { 1 / secondFlattening }
            val thirdFlattening = calculate { (equatorialRadius!! - polarRadius!!) / (equatorialRadius!! + polarRadius!!) }
            val reverseThirdFlattening = calculate { 1/ thirdFlattening }

            return  Result.success(Ellipsoid(
                equatorialRadius!!,
                polarRadius!!,
                firstFlattening,
                reverseFirstFlattening,
                secondFlattening,
                reverseSecondFlattening,
                thirdFlattening,
                reverseThirdFlattening
            ))
        }
    }
}