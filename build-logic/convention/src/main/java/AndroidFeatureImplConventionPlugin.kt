import com.jparkbro.convention.addNavigationDependencies
import com.jparkbro.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "myrecipy.android.library.compose")

            dependencies {
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:model"))
                "implementation"(project(":core:ui"))

                addUiLayerDependencies(target)
                addNavigationDependencies(target)
            }
        }
    }
}