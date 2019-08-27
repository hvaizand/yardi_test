import groovy.transform.Field

@Field def STEP_NAME = 'unmapNetworkDrive'
@Field Set STEP_CONFIG_KEYS = [
    'driveLetter',
    'message'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            def mapStatus = bat returnStatus: true, script: "if exist ${parameters.driveLetter}:\\ (NET USE ${parameters.driveLetter}: /del /y)"
            if(mapStatus!=0){
                currentBuild.result = 'UNSTABLE'
                parameters.message = "An issue occurred when trying to unmap the network drive pointing to the default path. Contact your admin"
            } else {
                parameters.message = "Successful"
            }
            debugMessage "unmapNetworkDrive - Unmap network drive", parameters.message             
        }
    }
}