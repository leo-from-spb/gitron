package lb.gitron.model.factual

/**
 * Git factual model (as is).
 */
class GitFactModel (
    
    val revs: Map<GitHash, GitRev>,
    val heads: Map<String, GitRev>,
    val tags: Map<GitHash, GitRev>

) 
