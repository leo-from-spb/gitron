package lb.hydra.idea

import com.intellij.openapi.project.Project
import com.intellij.vcs.log.impl.VcsProjectLog


val Project.vcsLog: VcsProjectLog?
    get() = VcsProjectLog.getInstance(this)


