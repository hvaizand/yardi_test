@NonCPS
def call(listFiles, fileName) {
    fileOperations([fileCreateOperation(fileContent: '', fileName: fileName)])
    for(def e in listFiles)){
        writeFile encoding: 'UTF-8', file: fileName, text: "${e.value}\n"
    }
}