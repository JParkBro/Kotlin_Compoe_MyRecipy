import com.jparkbro.convention.addNavigationDependencies
import com.jparkbro.convention.addUiLayerDependencies
import com.jparkbro.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "myrecipy.android.library.compose")

            dependencies {
                "implementation"(project(":core:ui"))

                addUiLayerDependencies(target)
                addNavigationDependencies(target)
            }
        }
    }
}