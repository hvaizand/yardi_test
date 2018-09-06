def call(fileList, dbo_credentials, db_server, db_name){
    def message = ''
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            debugMessage "loadPackage - List of packages:", "${fileList}"
            createFile "pldpkgload.pkglist"
            mapToFile(fileList, "pldpkgload.pkglist")
            def loadStatus = bat returnStdout: true, script: "C:\\Utils\\pldpkgload.exe -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i \"${WORKSPACE}\\pldpkgload.pkglist\""
            if(loadStatus!=0){
                currentBuild.result = 'FAILURE'
                message = "An issue occurred when trying to load packages. Please review the logs"
            } else {
                debugMessage "loadPackage - pldpkgload", loadStatus
                message = "Successful"
            }
    }
    return message
}