import com.android.build.api.dsl.ApplicationExtension
import com.jparkbro.convention.addNavigationDependencies
import com.jparkbro.convention.addUiLayerDependencies
import com.jparkbro.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "myrecipy.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)

            dependencies {
                addUiLayerDependencies(target)
                addNavigationDependencies(target)
            }
        }
    }
}