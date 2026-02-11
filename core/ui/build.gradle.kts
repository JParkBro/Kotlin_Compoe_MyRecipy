plugins {
    alias(libs.plugins.myrecipy.android.library.compose)
}

android {
    namespace = "com.jparkbro.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
}