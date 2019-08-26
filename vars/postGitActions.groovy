import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'postGitActions'
@Field Set STEP_CONFIG_KEYS = [
    'credentials',
    'labels',
    'comment',
    'result'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            withCredentials([usernamePassword(credentialsId: parameters.credentials, passwordVariable: 'password', usernameVariable: 'username')]) {
        //        pullRequest.addLabels(parameters.labels)
                pullRequest.labels = parameters.labels
                pullRequest.addLabels(parameters.labels)
                def comment = pullRequest.comment(parameters.comment)
            }
        } 
    }
}