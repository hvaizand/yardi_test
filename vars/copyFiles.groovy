def call(fileName, config){
    for (def e in mapToList(fileName)){
        echo "Copy report: ${e.value} to ${config.drive_letter}:\\"
        fileOperations([fileCopyOperation(excludes: '', flattenFiles: false, includes: "${e.value}", targetLocation: "${config.drive_letter}:\\")])
    }        
}