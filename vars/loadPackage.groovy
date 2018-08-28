@nonCPS
def call(fileName, LinkedHashMap config){
    withCredentials([usernamePassword(credentialsId: config.dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
        echo "file name: ${fileName}"
//        bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${config.db_server} -d ${config.db_name} -r1 -b -f 65001 -i ${fileName}"
    }
}