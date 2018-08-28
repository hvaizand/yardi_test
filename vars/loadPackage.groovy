def call(fileName, dbo_credentials, db_server, db_name){
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
        echo "file name: ${fileName}"
        echo "Creds: ${dbo_credentials} - DB Server: ${db_server} - DB Name: ${db_name}"
        bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i ${fileName}"
    }
}