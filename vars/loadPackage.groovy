def call(fileList, dbo_credentials, db_server, db_name){
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            debugMessage "loadPackage - List of packages:", "${fileList}"
            utils.createFile "pldpkgload.pkglist"
            utils.mapToFile(fileList, "pldpkgload.pkglist")
            bat returnStdout: true, script: "C:\\Utils\\pldpkgload.exe -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i \"${WORKSPACE}\\pldpkgload.pkglist\""
    }
}