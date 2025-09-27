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

class MilkDecorator(private val coffee: Coffee) : Coffee by coffee {
    override fun cost(): Int {
        return coffee.cost() + 50
    }

    override fun description(): String {
        return coffee.description() + ", молоко"
    }
}

class SugarDecorator(private val coffee: Coffee) : Coffee by coffee {
    override fun cost(): Int {
        return coffee.cost() + 20
    }

    override fun description(): String {
        return coffee.description() + ", сахар"
    }
}

class VanillaDecorator(private val coffee: Coffee) : Coffee by coffee {
    override fun cost(): Int {
        return coffee.cost() + 70
    }

    override fun description(): String {
        return coffee.description() + ", ваниль"
    }
}