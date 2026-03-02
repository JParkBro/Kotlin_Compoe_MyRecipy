package com.jparkbro.myreceipt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.core.navigation.rememberNavigationState
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.myreceipt.navigation.AppNavDisplay
import com.jparkbro.myreceipt.navigation.BottomNavigation
import com.jparkbro.myreceipt.navigation.TOP_LEVEL_NAV_ITEMS
import com.jparkbro.shell.history.api.HistoryNavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyReceiptTheme {
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
