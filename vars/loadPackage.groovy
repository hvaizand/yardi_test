def call(fileName){
    withCredentials([usernamePassword(credentialsId: yardiconfig.dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${yardiconfig.db_server} -d ${yardiconfig.db_name} -r1 -b -f 65001 -i ${fileName}"
        }
}