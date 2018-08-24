def call(fileName){
    withCredentials([usernamePassword(credentialsId: config.dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i ${fileName}"
        }
}