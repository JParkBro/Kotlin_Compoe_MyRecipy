plugins {
    alias(libs.plugins.myrecipy.android.library.compose)
}

android {
    namespace = "com.jparkbro.core.navigation"
}

dependencies {
    implementation(libs.bundles.navigation3)
}