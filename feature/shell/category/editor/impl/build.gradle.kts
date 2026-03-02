plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.category.editor.impl"
}

dependencies {
    implementation(projects.feature.shell.category.editor.api)
}