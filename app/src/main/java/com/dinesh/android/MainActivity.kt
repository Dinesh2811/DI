package com.dinesh.android

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        com.dinesh.android.v0.DI().main()
        com.dinesh.dagger.v0.constructor.DI().main()
//        com.dinesh.dagger.v0.field.DI().main()

    }
}
