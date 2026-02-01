package com.jparkbro.convention

import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        buildFeatures.apply {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY") ?: ""
        val appVersion = libs.findVersion("projectVersionName").get().toString()

        buildTypes {
            getByName("debug") {
                configureDebugBuildType(appVersion, apiKey)
            }
            getByName("release") {
                configureReleaseBuildType(commonExtension, appVersion, apiKey)
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(
    appVersion: String,
    apiKey: String,
) {
    buildConfigField("String", "APP_VERSION", "\"$appVersion\"")
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension,
    appVersion: String,
    apiKey: String
) {
    buildConfigField("String", "APP_VERSION", "\"$appVersion\"")
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://\"")

    isMinifyEnabled = false
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}