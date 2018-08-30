// def call(body){
//     bat script: body
// }
def call(fileName, dbo_credentials, db_server, db_name){
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
       for(int i = 0; i < fileName.size(); i++) {
            echo "Load package: " + fileName.[i]
//            echo "Creds: ${dbo_credentials} - DB Server: ${db_server} - DB Name: ${db_name}"
//            bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i " + fileName.[i]
       }
    }
}