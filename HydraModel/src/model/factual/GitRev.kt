@file:JvmName("GitRevs")

package lb.hydra.model.factual

import java.util.*

/**
 * Raw revision node, contains persistent information. 
 */
sealed class GitRev (
    
    @JvmField
    val hash: GitHash,
    
    @JvmField
    val message: String,
    
    @JvmField
    val author: GitActor,
    
    @JvmField
    val authorTime: Long,
    
    @JvmField
    val committer: GitActor,
    
    @JvmField
    val committerTime: Long

) {
    
    abstract val parentCount: Int
    
    abstract fun parents(): List<GitRev>
    
}


class GitCradleRev : GitRev {

    constructor(hash: GitHash, message: String, author: GitActor, authorTime: Long, committer: GitActor, committerTime: Long)
        : super(hash, message, author, authorTime, committer, committerTime)

    override val parentCount get() = 0
    override fun parents() = noGitRevs
}


class GitPlainRev : GitRev {

    @JvmField
    val parent: GitRev
    
    constructor(parent: GitRev, 
                hash: GitHash, message: String, author: GitActor, authorTime: Long, committer: GitActor, committerTime: Long)
            : super(hash, message, author, authorTime, committer, committerTime) {
        this.parent = parent
    }

    override val parentCount get() = 1
    override fun parents(): List<GitRev> = Collections.singletonList(parent)
    
}


class GitMergeRev : GitRev {

    @JvmField
    val parent0: GitRev
    
    @JvmField
    val parent1: GitRev

    constructor(parent0: GitRev, parent1: GitRev, 
                hash: GitHash, message: String, author: GitActor, authorTime: Long, committer: GitActor, committerTime: Long)
        : super(hash, message, author, authorTime, committer, committerTime) {
        this.parent0 = parent0
        this.parent1 = parent1
    }

    override val parentCount get() = 2
    override fun parents(): List<GitRev> = listOf(parent0, parent1)

}


class GitMegaMergeRev : GitRev {

    @JvmField
    val parents: List<GitRev>

    constructor(parents: List<GitRev>,
                hash: GitHash, message: String, author: GitActor, authorTime: Long, committer: GitActor, committerTime: Long)
        : super(hash, message, author, authorTime, committer, committerTime) {
        this.parents = Collections.unmodifiableList(ArrayList(parents)) // TODO use simpler list
    }

    override val parentCount get() = this.parents.size
    override fun parents(): List<GitRev> = this.parents
}


@JvmField
val noGitRevs: List<GitRev> = emptyList()
