package com.lexwilliam.basic_chat_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.lexwilliam.basic_chat_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

    override fun onStart() {
        super.onStart()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        auth = Firebase.auth
        analytics = Firebase.analytics

        if (auth.currentUser == null) {
            navController.navigate(R.id.signInFragment)
        } else {
            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            Firebase.database.useEmulator("10.0.2.2", 9000)
            Firebase.auth.useEmulator("10.0.2.2", 9099)
            Firebase.storage.useEmulator("10.0.2.2", 9199)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        auth = Firebase.auth
        if (auth.currentUser == null) {
            navController.navigate(R.id.signInFragment)
        }

        analytics = Firebase.analytics
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
//            param(FirebaseAnalytics.Param.ITEM_ID, id)
//            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        }
    }
}