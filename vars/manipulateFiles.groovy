def call(countFiles, deploy, config){
    for (commitFile in pullRequest.files) {
        debugMessage "SHA: ${commitFile.sha} File Name: ${commitFile.filename} Status: ${commitFile.status}", ''
        def file = commitFile.filename.toLowerCase()
        def ext = commitFile.filename.substring(commitFile.filename.lastIndexOf('.') + 1, commitFile.filename.length()).toLowerCase()
        switch(ext) {
            case "pkg":
                debugMessage "This is a package. The extension is ", ext
                if(deploy){
                    loadPackage commitFile.filename, config
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