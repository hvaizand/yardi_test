@NonCPS
def call(listFiles, fileName) {
    fileOperations([fileCreateOperation(fileContent: '', fileName: fileName)])
    echo "List of files: ${listFiles}"
    for(def item in listFiles){
        echo "File name: ${filName} - Value: ${item}\n"
        writeFile encoding: 'UTF-8', file: "${fileName}", text: "${item}\n"
    }
    return listFiles
}