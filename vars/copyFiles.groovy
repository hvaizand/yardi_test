import groovy.transform.Field

@Field def STEP_NAME = 'copyFiles'
@Field Set STEP_CONFIG_KEYS = [
    'fileNames',
    'targetDrive',
    'targetFolder',
    'count'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0){

            echo "Copying Report Files - ${parameters.count} files"

            for (def e in mapToList(parameters.fileNames)){
            
                echo "Copy report: ${e.value} to ${parameters.targetDrive}:${parameters.targetFolder}"
            
                if(bat(returnStatus: true, script: "COPY \"${e.value}\" ${parameters.targetDrive}:${parameters.targetFolder} /Y /Z") != 0) {
                    error "[${STEP_NAME}] An issue occurred when copying the report files. Please review the logs - aborting ${STEP_NAME}"
                }
            } 
        } else {
            echo "Copying Files - No files to be copied"
        }
    }
}