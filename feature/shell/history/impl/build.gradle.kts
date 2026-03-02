plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.history.impl"
}

dependencies {
    implementation(projects.feature.shell.history.api)
    implementation(projects.feature.shell.editor.api)
}