package com.jparkbro.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import java.util.Properties

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

internal fun Project.configureApplicationBuildTypes(
    applicationExtension: ApplicationExtension,
    keystoreProperties: Properties,
) {
    applicationExtension.apply {
        buildFeatures.apply {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY") ?: ""
        val appVersion = libs.findVersion("projectVersionName").get().toString()

        signingConfigs {
            create("release") {
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }

        buildTypes {
            getByName("debug") {
                configureDebugBuildType(appVersion, apiKey)
            }
            getByName("release") {
                configureReleaseBuildType(this@apply, appVersion, apiKey)
                isMinifyEnabled = true
                isShrinkResources = true
                signingConfig = signingConfigs.getByName("release")
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

    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}