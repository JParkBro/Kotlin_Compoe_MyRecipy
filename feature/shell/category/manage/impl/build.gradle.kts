plugins {
    alias(libs.plugins.myrecipy.android.feature.impl)
}

android {
    namespace = "com.jparkbro.category.manage.impl"
}

dependencies {
    implementation(projects.feature.shell.category.manage.api)
    implementation(projects.feature.shell.category.editor.api)
}