plugins {
    alias(libs.plugins.myrecipy.android.application.compose)
}

android {
    namespace = "com.jparkbro.myrecipy"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.model)
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.feature.shell.calendar.api)
    implementation(projects.feature.shell.calendar.impl)
    implementation(projects.feature.shell.editor.api)
    implementation(projects.feature.shell.editor.impl)
    implementation(projects.feature.shell.history.api)
    implementation(projects.feature.shell.history.impl)
    implementation(projects.feature.shell.report.api)
    implementation(projects.feature.shell.report.impl)
    implementation(projects.feature.shell.settings.api)
    implementation(projects.feature.shell.settings.impl)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //	- Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //	- Oss Licenses
    implementation(libs.ui.compose.material3)

    //	- In App Update
    implementation(libs.app.update)
    implementation(libs.app.update.ktx)
}