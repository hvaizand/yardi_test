@NonCPS
def call(listFiles, fileName) {
    fileOperations([fileCreateOperation(fileContent: '', fileName: fileName)])
    for(def e in listFiles){
        echo "File name: ${filName} - Value: ${e.value}\n"
        writeFile encoding: 'UTF-8', file: "${fileName}", text: "${e.value}\n"
    }
}