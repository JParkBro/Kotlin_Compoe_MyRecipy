plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.calendar.impl"
}

dependencies {
    implementation(projects.feature.shell.calendar.api)
}