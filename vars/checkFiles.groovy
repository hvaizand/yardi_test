def call(fileType){
    def counterFiles = 0
    def fileDetails
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
                if(fileType=='Package'){
                    counterFiles += 1
                    fileDetails[counterFiles] = fileFullPath
                }
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
                if(fileType=='Report'){
                    counterFiles += 1
                    fileDetails[counterFiles] = fileFullPath
                }
            break
            default:
                debugMessage "This is another file. The extension is ", ext
                if(fileType=='Other'){
                    counterFiles += 1
                    fileDetails[counterFiles] = fileFullPath
                }
            break
        }
    }
    return fileDetails
}