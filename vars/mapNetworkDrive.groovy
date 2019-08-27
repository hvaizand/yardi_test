import groovy.transform.Field

@Field def STEP_NAME = 'mapNetworkDrive'
@Field Set STEP_CONFIG_KEYS = [
    'driveLetter',
    'defPath',
    'rdpUser',
    'message'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            withCredentials([usernamePassword(credentialsId: parameters.rdpUser, passwordVariable: 'RDPPASSWORD', usernameVariable: 'RDPUSERID')]) {
                echo "NET USE ${parameters.driveLetter}: \"${parameters.defPath}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
                def mapStatus = bat returnStatus: true, script: "NET USE ${parameters.driveLetter}: \"${parameters.defPath}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
                if(mapStatus!=0){
                    currentBuild.result = 'UNSTABLE'
                    parameters.message = "An issue occurred when trying to map the network drive pointing to the default path. Please update your RDP password in Jenkins"
                } else {
                    parameters.message = "Successful"
                }
            }
            debugMessage "Map Network Drive", parameters.message
        }
    }
}