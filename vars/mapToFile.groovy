@NonCPS
def call(listFiles, fileName) {
    echo "List of files: ${listFiles}"
    def listFilesString
//    def filePath
    for(def item in listFiles){
        echo "File name: ${fileName} - Value: ${item}\n"
//        item.substring(commitFile.filename.lastIndexOf('=') + 1, item.length())
//        filePath = item.substring(item.lastIndexOf('=') + 1, item.length())
//        echo "File path: ${filePath}"
//        listFilesString += "${filePath}\n"
        listFilesString += "${item}\n"
        echo "List file string: ${listFilesString}"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFilesString}"
//    return listFiles
}