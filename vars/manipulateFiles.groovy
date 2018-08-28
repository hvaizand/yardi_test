@NonCPS
def call(counterFiles, deploy, config){
    def dbServer = config.db_server
    def dbName = config.db_name
    withCredentials([usernamePassword(credentialsId: config.dbo_credentials, passwordVariable: 'dbPassword', usernameVariable: 'dbUserName')]) {
    //    for(int i = 0; i < pullRequest.files.size(); i++) {
        for (commitFile in pullRequest.files) {
            debugMessage "SHA: ${commitFile.sha} File Name: ${commitFile.filename} Status: ${commitFile.status}", ''
            def file = commitFile.filename.toLowerCase()
            debugMessage "File name lowercase: ", file
            def fileFullPath = commitFile.filename
            fileFullPath = translatePath("${WORKSPACE}\\" + fileFullPath)
            debugMessage "File name full path: ", fileFullPath
            def ext = commitFile.filename.substring(commitFile.filename.lastIndexOf('.') + 1, commitFile.filename.length()).toLowerCase()
            debugMessage "File extension: ", ext
            switch(ext) {
                case "pkg":
                    debugMessage "This is a package. The extension is ", ext
                    if(deploy){
    //                    loadPackage fileFullPath, config.dbo_credentials, config.db_server, config.db_name
    //                    echo "Creds: ${config.dbo_credentials} - DB Server: ${config.db_server} - DB Name: ${config.db_name}"
    //                         echo "file name: ${fileFullPath}"
    //                         echo "Creds: ${config.dbo_credentials} - DB Server: ${config.db_server} - DB Name: ${config.db_name}"
                             bat script: "sqlcmd -U ${dbUserName} -P ${dbPassword} -S ${dbServer} -d ${dbName} -r1 -b -f 65001 -i ${fileFullPath}"
    //                     }
                    }
                    counterFiles.countpkg += 1
                break
                case "txt":
                case "inc":
                case "doc":
                case "docx":
                case "xls":
                case "xlsx":
                case "xlsm":
                case "rdlc":
                    debugMessage "This is a report file. The extension is ", ext
                    counterFiles.countrpt += 1
                break
                default:
                    debugMessage "This is another file. The extension is ", ext
                    counterFiles.countother += 1
                break
            }
        }
    }
    debugMessage "Number of pkg: ", counterFiles.countpkg
    debugMessage "Number of report files: ", counterFiles.countrpt
    debugMessage "Number of others: ", counterFiles.countother
    return counterFiles
}