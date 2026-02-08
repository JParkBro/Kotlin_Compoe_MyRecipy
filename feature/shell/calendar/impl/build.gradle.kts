plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.calendar.impl"
}

dependencies {
    implementation(projects.feature.shell.calendar.api)
}