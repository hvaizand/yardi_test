@NonCPS
def call(listFiles, fileName) {
    echo "List of files: ${listFiles}"
    for(def item in listFiles){
        echo "File name: ${fileName} - Value: ${item}\n"
        writeFile encoding: 'UTF-8', file: "${fileName}", text: "${item}\n"
    }
//    return listFiles
}