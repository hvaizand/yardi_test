import groovy.transform.Field

@Field def STEP_NAME = 'copyFiles'
@Field Set STEP_CONFIG_KEYS = [
    'fileType',
    'targetDrive',
    'targetFolder'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0){
            def fileName
            switch(parameters.fileType) {
                case "Report":
                    fileName = "pldreport.list"
                break
                case "Filter":
                    fileName = "filter.list"
                break
            }
            def fileToCopy = readFileInList(fileName: fileName)

            echo "Copying Report Files - ${fileName} - ${fileToCopy.size()} files"
            debugMessage "doCopy - Files to Copy", fileToCopy
            debugMessage "doCopy - Files to Copy - Count", fileToCopy.size()

            for (def e in fileToCopy){
                        
                echo "Copy file: ${e.value} to ${parameters.targetDrive}:${parameters.targetFolder}"
            
                if(bat(returnStatus: true, script: "COPY \"${e.value}\" ${parameters.targetDrive}:${parameters.targetFolder} /Y /Z") != 0) {
                    error "[${STEP_NAME}] An issue occurred when copying the file ${e.value}. Please review the logs - aborting ${STEP_NAME}"
                }
            } 
        } else {
            echo "Copying Files - No files to be copied"
        }
    }
}