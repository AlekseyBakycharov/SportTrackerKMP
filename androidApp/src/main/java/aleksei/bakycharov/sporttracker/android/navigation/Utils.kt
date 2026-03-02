package aleksei.bakycharov.sporttracker.android.navigation

import androidx.navigation.NavDestination

fun NavDestination?.isCurrentRoute(screen: Screen): Boolean {
    return this?.route?.contains(screen::class.qualifiedName.orEmpty()) == true
}