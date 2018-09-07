def call(fileName, config){
    def message = ''
    for (def e in mapToList(fileName)){
        echo "Copy report: ${e.value} to ${config.drive_letter}:\\"
//        fileOperations([fileCopyOperation(excludes: '', flattenFiles: false, includes: "${e.value}", targetLocation: "${config.drive_letter}:\\")])
        def copyStatus = bat returnStatus: true, script: "COPY \"${e.value}\" ${config.drive_letter}:\\ /Y /Z"
        if(copyStatus!=0){
            currentBuild.result = 'FAILURE'
            message = "An issue occurred when copying the report files. Please review the logs"
        } else {
            debugMessage "copyFile - copy status", copyStatus
            message = "Successful"
        }
    }        
}