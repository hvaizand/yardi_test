import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'checkFiles'
@Field Set STEP_CONFIG_KEYS = [
    'fileType',
    'environmentType'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            def counterFiles = 0
            def fileDetails = [:]
            for (commitFile in pullRequest.files) {
                debugMessage "SHA: ${commitFile.sha} File Name: ${commitFile.filename} Status: ${commitFile.status}", ''
                def file = commitFile.filename.toLowerCase()
                debugMessage "File name lowercase: ", file
                def fileFullPath = commitFile.filename
                fileFullPath = translatePath("${WORKSPACE}\\" + fileFullPath)
                debugMessage "File name full path: ", fileFullPath
                def ext = commitFile.filename.substring(commitFile.filename.lastIndexOf('.') + 1, commitFile.filename.length()).toLowerCase()
                debugMessage "File extension: ", ext
                if(fileFullPath.find(/${parameters.environmentType}/)){
                    switch(ext) {
                        case "pkg":
                            debugMessage "This is a package. The extension is ", ext
                            if(parameters.fileType=='Package'){
                                counterFiles += 1
                                fileDetails[counterFiles] = fileFullPath
                            }
                        break
                        case "txt":
                        case "inc":
                        case "doc":
                        case "docx":
                        case "xls":
                        case "xlsx":
                        case "xlsm":
                        case "rdlc":
                            debugMessage "This is a report file. The extension is ", ext
                            if(parameters.fileType=='Report'){
                                counterFiles += 1
                                fileDetails[counterFiles] = fileFullPath
                            }
                        break
                        default:
                            debugMessage "This is another file. The extension is ", ext
                            if(parameters.fileType=='Other'){
                                counterFiles += 1
                                fileDetails[counterFiles] = fileFullPath
                            }
                        break
                    }
                }
            }
            return fileDetails
        } else {
            return ""
        }
    }
}