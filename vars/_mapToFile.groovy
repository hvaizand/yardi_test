@NonCPS
def call(listFiles, fileName) {
    echo "List of files: ${listFiles}"
    def listFilesString = ''
    def filePath
    for(def entry2 in listFiles){
        debugMessage "mapToFile - entry2", "${entry2}"
        filePath = "${entry2}".substring("${entry2}".indexOf('=') + 1, "${entry2}".length())
        debugMessage "File path:", "${filePath}"
        listFilesString += "${filePath}\n"
        debugMessage "List file string:", "${listFilesString}"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFilesString}"
}