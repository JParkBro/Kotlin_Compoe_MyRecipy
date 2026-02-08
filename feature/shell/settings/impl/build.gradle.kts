plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.settings.impl"
}

dependencies {
    implementation(projects.feature.shell.settings.api)
}