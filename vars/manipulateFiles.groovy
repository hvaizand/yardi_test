def call(countFiles, deploy, config){
    for (commitFile in pullRequest.files) {
        debugMessage "SHA: ${commitFile.sha} File Name: ${commitFile.filename} Status: ${commitFile.status}", ''
        def file = commitFile.filename.toLowerCase()
        def fileFullPath = commitFile.filename
        fileFullPath = translatePath(fileFullPath)
        fileFullPath = "${WORKSPACE}\\" + fileFullPath
        def ext = commitFile.filename.substring(commitFile.filename.lastIndexOf('.') + 1, commitFile.filename.length()).toLowerCase()
        switch(ext) {
            case "pkg":
                debugMessage "This is a package. The extension is ", ext
                if(deploy){
//                    loadPackage fileFullPath, config.dbo_credentials, config.db_server, config.db_name
//                    echo "Creds: ${config.dbo_credentials} - DB Server: ${config.db_server} - DB Name: ${config.db_name}"
                    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
                        echo "file name: ${fileFullPath}"
                        echo "Creds: ${config.dbo_credentials} - DB Server: ${config.db_server} - DB Name: ${config.db_name}"
                        bat script: "sqlcmd -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${config.db_server} -d ${config.db_name} -r1 -b -f 65001 -i ${fileFullPath}"
                    }
                }
                countFiles.countpkg += 1
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
                countFiles.countrpt += 1
            break
            default:
                debugMessage "This is another file. The extension is ", ext
                countFiles.countother += 1
            break
        }
    }
    debugMessage "Number of pkg: ", countFiles.countpkg
    debugMessage "Number of report files: ", countFiles.countrpt
    debugMessage "Number of others: ", countFiles.countother
}