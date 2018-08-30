def call(fileName, targetPath, rdp_credentials){
    withCredentials([usernamePassword(credentialsId: rdp_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
        for (def e in utils.mapToList(fileName)){
            echo "Copy report: + ${e.value}"

       }
    }        
}