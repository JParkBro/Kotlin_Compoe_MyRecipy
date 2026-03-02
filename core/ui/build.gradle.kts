plugins {
    alias(libs.plugins.myreceipt.android.library.compose)
}

android {
    namespace = "com.jparkbro.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
}