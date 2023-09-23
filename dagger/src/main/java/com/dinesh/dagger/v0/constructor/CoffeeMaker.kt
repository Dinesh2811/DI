package com.dinesh.dagger.v0.constructor

import android.util.Log
import dagger.Component
import javax.inject.Inject

private val TAG = "log_Dagger_Constructor"

@Component
interface CoffeeComponent {
    fun getCoffeeMaker(): CoffeeMaker
}

class DI {
    fun main() {
        // Create Dagger component
        val coffeeShop = DaggerCoffeeComponent.create()

        // Get the CoffeeMaker instance
        val coffeeMaker = coffeeShop.getCoffeeMaker()

        // Make coffee
        coffeeMaker.makeCoffee()
    }
}

class CoffeeMaker @Inject constructor(private val heater: Heater, private val coffee: Coffee) {
    fun makeCoffee() {
        heater.heat()
        coffee.brew()
        println("Coffee is ready!")
        Log.e(TAG, "makeCoffee: Coffee is ready!")
    }
}

class Coffee @Inject constructor() {
    fun brew() {
        println("Brewing coffee...")
        Log.d(TAG, "brew: Brewing coffee...")
    }
}

class Heater @Inject constructor() {
    fun heat() {
        println("Heating the heater...")
        Log.d(TAG, "heat: Heating the heater...")
    }
}

