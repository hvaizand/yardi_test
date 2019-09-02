@NonCPS
import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'readFileInList'
@Field Set STEP_CONFIG_KEYS = [
    'fileName'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            
            File file = new File("${env.WORKSPACE}/${parameters.fileName}")

            def fileContent = file.readLines()

            return fileContent
        }
    }
}