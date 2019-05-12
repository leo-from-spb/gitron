package lb.hydra.idea

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.vcs.log.impl.VcsProjectLog

class HydraAction : AnAction() {

    override fun update(e: AnActionEvent) {
        super.update(e)
        val projectLog: VcsProjectLog? = e.project?.vcsLog
        e.presentation.isVisible = true
        e.presentation.isEnabled = projectLog != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val projectLog = e.project?.vcsLog ?: return
        projectLog.dataManager
    }


    override fun isDumbAware() = true

}