def call(fileName, targetPath){
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
       for(int i = 0; i < fileName.size(); i++) {
            echo "Copy report: ${fileName.[i]}"
//            echo "Creds: ${dbo_credentials} - DB Server: ${db_server} - DB Name: ${db_name}"
//            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i ${fileName}"
       }
    }        
}