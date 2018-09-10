def call(config){
    def mapStatus = bat returnStatus: true, script: "if exist ${config.drive_letter}:\\ (NET USE ${config.drive_letter}: /del /y)"
    if(mapStatus!=0){
        currentBuild.result = 'UNSTABLE'
        notification.message = "An issue occurred when trying to unmap the network drive pointing to the default path. Contact your admin"
    } else {
        debugMessage "utils.unmapDrive - Unmap network drive", mapStatus
        notification.message = "Successful"
    }
    return mapStatus
}