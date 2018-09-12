import groovy.transform.Field

@Field def STEP_NAME = 'copyFiles'

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        def message = ''
        for (def e in mapToList(parameters.fileNames)){
            echo "Copy report: ${e.value} to ${parameters.targetDrive}:\\"
            def returnStatus = bat returnStatus: true, script: "COPY \"${e.value}\" ${parameters.targetDrive}:\\ /Y /Z"
            if(returnStatus!=0){
                currentBuild.result = 'FAILURE'
                message = "An issue occurred when copying the report files. Please review the logs"
            } else {
                debugMessage "copyFile - copy status", returnStatus
                message = "Successful"
            }
        }      
        return message  
    }
}