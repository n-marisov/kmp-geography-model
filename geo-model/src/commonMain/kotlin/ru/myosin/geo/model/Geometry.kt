package ru.myosin.geo.model

/**
 * Географическая фигура.
 */
sealed class Geometry {
    /**
     * Объект границ в которую входит географическая фигура.
     */
    abstract val bounds: Bounds 
}