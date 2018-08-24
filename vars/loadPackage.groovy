def call(fileName){
    withCredentials([usernamePassword(credentialsId: yardiConfig.dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${yardiConfig.db_server} -d ${yardiConfig.db_name} -r1 -b -f 65001 -i ${fileName}"
        }
}