package com.jparkbro.myrecipy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.core.navigation.rememberNavigationState
import com.jparkbro.core.ui.theme.MyRecipyTheme
import com.jparkbro.myrecipy.navigation.AppNavDisplay
import com.jparkbro.myrecipy.navigation.BottomNavigation
import com.jparkbro.myrecipy.navigation.TOP_LEVEL_NAV_ITEMS
import com.jparkbro.shell.history.api.HistoryNavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipyTheme {
                val navigationState = rememberNavigationState(
                    startKey = HistoryNavKey,
                    topLevelKeys = TOP_LEVEL_NAV_ITEMS.keys,
                )
                val navigator = remember(navigationState) { Navigator(navigationState) }

                AppNavDisplay(
                    bottomNavigation = {
                        BottomNavigation(
                            currentKey = navigationState.currentTopLevelKey,
                            onNavigate = { navigator.navigate(it) },
                        )
                    },
                    navigationState = navigationState,
                    navigator = navigator,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }
    }
}
