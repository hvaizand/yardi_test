@NonCPS
def call(listFiles, fileName) {
    echo "mapToFile - List of files: ${listFiles}"
    echo "mapToFile - Number of files: ${listFiles.size()}"
    def listFilesString = ''
    def filePath
    for(def entry2 in listFiles){
        filePath = "${entry2}".substring("${entry2}".indexOf('=') + 1, "${entry2}".length())
        listFilesString += "${filePath}\n"
        debugMessage "mapToFile - entry2", "${entry2}"
        debugMessage "mapToFile - File path:", "${filePath}"
        debugMessage "mapToFile - List file string:", "${listFilesString}"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFilesString}"
}