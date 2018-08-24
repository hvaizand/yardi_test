def call(countFiles){
    for (commitFile in pullRequest.files) {
        echo "SHA: ${commitFile.sha} File Name: ${commitFile.filename} Status: ${commitFile.status}"
        def file = commitFile.filename.toLowerCase()
        def ext = commitFile.filename.substring(commitFile.filename.lastIndexOf('.') + 1, commitFile.filename.length()).toLowerCase()
        switch(ext) {
            case "pkg":
                echo "This is a package. The extension is ${ext}"
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
                echo "This is a report file. The extension is ${ext}"
                countFiles.countrpt += 1
            break
            default:
                echo "This is another file. The extension is ${ext}"
                countFiles.countother += 1
            break
        }
    }
}