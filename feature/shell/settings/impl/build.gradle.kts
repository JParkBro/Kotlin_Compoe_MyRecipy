plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.settings.impl"
}

dependencies {
    implementation(projects.feature.shell.settings.api)
}