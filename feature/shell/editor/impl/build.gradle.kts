plugins {
    alias(libs.plugins.myreceipt.android.feature.impl)
}

android {
    namespace = "com.jparkbro.shell.editor.impl"
}

dependencies {
    implementation(projects.feature.shell.editor.api)
    implementation(projects.feature.shell.category.manage.api)
}