@NonCPS
import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'fileToMap'
@Field Set STEP_CONFIG_KEYS = [
    'fileName'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            
            def file = readFile "${env.WORKSPACE}/${parameters.fileName}"
            //Split string on carriage return
            def fileContent = file.split("\n")

            return fileContent
        }
    }
}