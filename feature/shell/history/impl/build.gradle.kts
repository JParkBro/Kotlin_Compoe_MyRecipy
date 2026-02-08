plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.history.impl"
}

dependencies {
    implementation(projects.feature.shell.history.api)
}