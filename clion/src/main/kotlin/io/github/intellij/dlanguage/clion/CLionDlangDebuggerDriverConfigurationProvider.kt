package io.github.intellij.dlanguage.project

import com.intellij.openapi.project.Project
import com.jetbrains.cidr.cpp.toolchains.CPPToolchains
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration
import io.github.intellij.dlanguage.project.DlangDebuggerDriverConfigurationProvider

class CLionDlangDebuggerDriverConfigurationProvider : DlangDebuggerDriverConfigurationProvider {
    override fun getDebuggerDriverConfiguration(project: Project): DebuggerDriverConfiguration? {
        return CPPToolchains.getInstance().defaultToolchain?.createDriverConfiguration(project)
    }
}
