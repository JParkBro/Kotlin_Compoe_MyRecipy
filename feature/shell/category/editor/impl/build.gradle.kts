plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.category.editor.impl"
}

dependencies {
    implementation(projects.feature.shell.category.editor.api)
}