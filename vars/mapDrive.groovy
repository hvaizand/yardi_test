def call(config){
    def message = ''
    withCredentials([usernamePassword(credentialsId: params.RdpUser, passwordVariable: 'RDPPASSWORD', usernameVariable: 'RDPUSERID')]) {
        echo "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        def mapStatus = bat returnStatus: true, script: "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        if(mapStatus!=0){
            currentBuild.result = 'UNSTABLE'
            notification.message = "An issue occurred when trying to map the network drive pointing to the default path. Please update your RDP password in Jenkins"
        } else {
            debugMessage "utils.mapDrive - Map network drive", mapStatus
            notification.message = "Successful"
        }
    }
    return mapStatus
}