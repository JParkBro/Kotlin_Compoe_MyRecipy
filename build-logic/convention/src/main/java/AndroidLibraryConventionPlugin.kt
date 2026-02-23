import com.android.build.api.dsl.LibraryExtension
import com.jparkbro.convention.configureBuildTypes
import com.jparkbro.convention.configureKotlinAndroid
import com.jparkbro.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(this)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            dependencies {
                "implementation"(project(":core:model"))

                "implementation"(project.libs.findBundle("koin").get())
                "testImplementation"(kotlin("test"))
            }
        }
    }
}