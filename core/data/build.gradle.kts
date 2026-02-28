plugins {
    alias(libs.plugins.myrecipy.android.library)
}

android {
    namespace = "com.jparkbro.core.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(projects.core.database)
    implementation(projects.core.model)

    //	- Google Login
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}