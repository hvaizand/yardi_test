@NonCPS
def call(){
//    def causes = currentBuild.rawBuild.getCauses()
//    echo "Cause: ${causes}"
    def triggerCause = currentBuild.rawBuild.getCause(org.jenkinsci.plugins.pipeline.github.trigger.IssueCommentCause)
    def cause
    if (triggerCause) {
        cause = "Build was started by ${triggerCause.userLogin}, who wrote: " +
            "\"${triggerCause.comment}\", which matches the " +
            "\"${triggerCause.triggerPattern}\" trigger pattern.")
    } else {
        cause = 'Build was not started by a trigger'
    }
    return cause
}