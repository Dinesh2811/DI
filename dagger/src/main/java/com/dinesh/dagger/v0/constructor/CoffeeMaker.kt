package com.dinesh.dagger.v0.constructor

import android.util.Log
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

private val TAG = "log_Dagger_Constructor"

class DI {
    fun main() {
        // Create Dagger component - DaggerCoffeeComponent is generated by Dagger based on CoffeeComponent interface.
        val coffeeShop = DaggerCoffeeComponent.create()

        // Get the CoffeeMaker instance from the Dagger component. DaggerCoffeeComponent implements CoffeeComponent interface.
        val coffeeMaker = coffeeShop.getCoffeeMaker()

        // Make coffee using the CoffeeMaker instance.
        coffeeMaker.makeCoffee()
    }
}

// Define a CoffeeMaker class that will be injected with Heater and Coffee
class CoffeeMaker @Inject constructor(private val heater: Heater, private val coffee: Coffee) {
    fun makeCoffee() {
        heater.heat()
        coffee.brew()
        println("Coffee is ready!")
        Log.e(TAG, "makeCoffee: Coffee is ready!")
    }
}

// Define a Dagger Component interface to define the injection functions.
@Component
interface CoffeeComponent {
    // Define a function to provide a CoffeeMaker instance
    fun getCoffeeMaker(): CoffeeMaker
}

// Define a Coffee class that will be injected
class Coffee @Inject constructor() {
    fun brew() {
        println("Brewing coffee...")
        Log.d(TAG, "brew: Brewing coffee...")
    }
}

// Define a Heater class that will be injected
class Heater @Inject constructor() {
    fun heat() {
        println("Heating the heater...")
        Log.d(TAG, "heat: Heating the heater...")
    }
}

/*

class DI {
    fun main() {
        // Create a Dagger component by using DaggerCoffeeComponent.create().
        val coffeeComponent = DaggerCoffeeComponent.create()

        // Get the CoffeeMaker instance from the component.
        val coffeeMaker = coffeeComponent.getCoffeeMaker()

        // Make coffee using the CoffeeMaker.
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

@Component
interface CoffeeComponent {
    fun getCoffeeMaker(): CoffeeMaker
}

@Module
class CoffeeModule {
    @Provides
    fun provideHeater(): Heater {
        return Heater()
    }

    @Provides
    fun provideCoffee(): Coffee {
        return Coffee()
    }
}

class Heater @Inject constructor() {
    fun heat() {
        println("Heating the heater...")
        Log.d(TAG, "heat: Heating the heater...")
    }
}

class Coffee @Inject constructor() {
    fun brew() {
        println("Brewing coffee...")
        Log.d(TAG, "brew: Brewing coffee...")
    }
}

*/


/*

// Main function
class DI {
    fun main() {
        val coffeeComponent = DaggerCoffeeComponent.create()
        val coffeeMaker = coffeeComponent.getCoffeeMaker()
        coffeeMaker.makeCoffee()
    }
}

// Heater Interface and its implementations
interface Heater {
    fun heat()
}

class ElectricHeater : Heater {
    override fun heat() {
        println("Heating with an electric heater...")
        Log.d(TAG, "heat: Heating with an electric heater...")
    }
}

class GasHeater : Heater {
    override fun heat() {
        println("Heating with a gas heater...")
        Log.d(TAG, "heat: Heating with a gas heater...")
    }
}

// Coffee Interface and its implementations
interface Coffee {
    fun brew()
}

class Espresso : Coffee {
    override fun brew() {
        println("Brewing espresso...")
        Log.d(TAG, "brew: Brewing espresso...")
    }
}

class Latte : Coffee {
    override fun brew() {
        println("Brewing latte...")
        Log.d(TAG, "brew: Brewing latte...")
    }
}

// CoffeeMaker with constructor injection
class CoffeeMaker @Inject constructor(private val heater: Heater, private val coffee: Coffee) {
    fun makeCoffee() {
        heater.heat()
        coffee.brew()
        println("Coffee is ready!")
        Log.e(TAG, "makeCoffee: Coffee is ready!")
    }
}

// Dagger components and modules
@Component(modules = [CoffeeModule::class])
interface CoffeeComponent {
    fun getCoffeeMaker(): CoffeeMaker
}

@Module
class CoffeeModule {
    @Provides
    fun provideHeater(): Heater {
        return ElectricHeater() // We changed this to ElectricHeater for this example.
    }

    @Provides
    fun provideCoffee(): Coffee {
        return Espresso() // We changed this to Espresso for this example.
    }
}

 */