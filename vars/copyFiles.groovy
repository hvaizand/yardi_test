def call(fileName, targetPath, rdp_credentials){
    withCredentials([usernamePassword(credentialsId: rdp_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
    //    for(int i = 0; i < fileName.size(); i++) {
    //         echo "Copy report: ${fileName.[i]}"
        for (def e in mapToList(fileName)){
            echo "Copy report: + ${e.value}"
//            echo "Creds: ${dbo_credentials} - DB Server: ${db_server} - DB Name: ${db_name}"
//            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i ${fileName}"
       }
    }        
}