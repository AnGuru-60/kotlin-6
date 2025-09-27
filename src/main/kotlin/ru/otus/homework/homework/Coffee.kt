package ru.otus.homework.homework

/**
 * Базовый интерфейс кофейного напитка
 */
interface Coffee {
    /**
     * Цена кофейного напитка в копейках
     */
    fun cost(): Int

    /**
     * Описание кофейного напитка
     */
    fun description(): String
}

class SimpleCoffee : Coffee {
    override fun cost() = 200
    override fun description() = "Простой кофе"
}

abstract class Decorator(private val coffee: Coffee): Coffee {
    abstract val extCost: Int
    abstract val extDescription: String

    override fun cost() = coffee.cost() + extCost
    override fun description() = coffee.description() + ", " + extDescription
}

class MilkDecorator(coffee: Coffee) : Decorator(coffee) {
    override val extCost: Int = 50
    override val extDescription: String = "молоко"
}

class SugarDecorator(coffee: Coffee) : Decorator(coffee) {
    override val extCost: Int = 20
    override val extDescription: String = "сахар"
}

class VanillaDecorator(coffee: Coffee) : Decorator(coffee) {
    override val extCost: Int = 70
    override val extDescription: String = "ваниль"
}