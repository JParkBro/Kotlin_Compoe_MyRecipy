plugins {
    alias(libs.plugins.myrecipy.android.library)
}

android {
    namespace = "com.jparkbro.core.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    //	- Google Login
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}