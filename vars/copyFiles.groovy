def call(fileName, config){
    for (def e in utils.mapToList(fileName)){
        echo "Copy report: ${e.value}"
        fileOperations([fileCopyOperation(excludes: '', flattenFiles: false, includes: "${e.value}", targetLocation: "${config.drive_letter}:\\")])
    }        
}