package lb.hydra.model.factual

/**
 *
 */
data class GitActor (
    
    @JvmField
    val mail: String,
    
    @JvmField
    val name: String
    
) {

    override fun toString() = "$name <$mail>" 
    
}