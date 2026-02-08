plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.editor.impl"
}

dependencies {
    implementation(projects.feature.shell.editor.api)
}