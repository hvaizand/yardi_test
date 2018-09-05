@NonCPS
def call(){
    def causes = currentBuild.rawBuild.getCauses(hudson.model.Cause$UserIdCause)
    echo "Cause: ${causes}"
//    def triggerCause = currentBuild.rawBuild.getCause(org.jenkinsci.plugins.pipeline.github.trigger.IssueCommentCause)
//    echo "${triggerCause.userLogin} - ${triggerCause.comment} - ${triggerCause.triggerPattern}"
    return causes
}