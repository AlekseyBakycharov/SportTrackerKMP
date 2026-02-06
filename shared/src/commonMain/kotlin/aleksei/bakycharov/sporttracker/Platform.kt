package aleksei.bakycharov.sporttracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform