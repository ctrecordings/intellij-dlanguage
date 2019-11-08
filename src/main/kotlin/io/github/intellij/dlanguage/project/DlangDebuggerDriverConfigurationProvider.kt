package io.github.intellij.dlanguage.project

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.Project
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration

interface DlangDebuggerDriverConfigurationProvider {

    fun getDebuggerDriverConfiguration(project: Project): DebuggerDriverConfiguration?

    companion object {
        @JvmField
        val EP_NAME: ExtensionPointName<RsDebuggerDriverConfigurationProvider> =
            ExtensionPointName.create("io.github.intellij.dlanguage.project.driverConfigurationProvider")
    }
}

