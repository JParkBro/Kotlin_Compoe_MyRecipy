import com.android.build.api.dsl.ApplicationExtension
import com.jparkbro.convention.configureApplicationBuildTypes
import com.jparkbro.convention.configureKotlinAndroid
import com.jparkbro.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")

            val keystoreProperties = Properties().apply {
                val keystoreFile = rootProject.file("keystore.properties")
                if (keystoreFile.exists()) load(keystoreFile.inputStream())
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(this)

                configureApplicationBuildTypes(this, keystoreProperties)
            }
        }
    }
}