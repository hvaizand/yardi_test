// def call(body){
//     bat script: body
// }
def call(fileList, dbo_credentials, db_server, db_name){
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
//        for (def e in mapToFile(fileList, env.WORKSPACE'\\pldpkgload.pkglist')){
//        for(int i = 0; i < fileList.size(); i++) {
            def e = mapToFile(fileList, env.WORKSPACE'\\pldpkgload.pkglist')
            echo "Load ${e.size()} package(s)"
            bat returnStdout: true, script: "C:\\Utils\\pldpkgload.exe -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i \"${WORKSPACE}\\pldpkgload.pkglist\""
//       }
    }
}