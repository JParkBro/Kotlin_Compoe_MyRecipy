plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.report.impl"
}

dependencies {
    implementation(projects.feature.shell.report.api)
}