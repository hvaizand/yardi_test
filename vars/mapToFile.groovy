@NonCPS
def call(listFiles, fileName) {
    echo "List of files: ${listFiles}"
    def listFilesString
    for(def item in listFiles){
        echo "File name: ${fileName} - Value: ${item}\n"
//        item.substring(commitFile.filename.lastIndexOf('=') + 1, item.length())
        listFilesString += "${item.substring(commitFile.filename.lastIndexOf('=') + 1, item.length())}\n"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFileString}"
//    return listFiles
}