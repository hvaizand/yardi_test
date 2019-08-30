import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'createFile'
@Field Set STEP_CONFIG_KEYS = [
    'fileType',
    'fileList'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {

            if(parameters.fileType!="Other") {
                echo "Create ${parameters.fileType} file"

                debugMessage "createFile - List of files", "${parameters.fileList}"
                
                def fileName = ""

                switch(parameters.fileType) {
                    case "Package":
                        fileName = "pldpkgload.pkglist"
                    break
                    case "Report":
                        fileName = "pldreport.list"
                    break
                }

                FileUtils.createFile(this, "${fileName}")
                
                mapToFile(parameters.fileList, "${fileName}")
            }
            
        } else {
            debugMessage "createFile - List of files", "Error creating the list of files to load/copy"
        }
    }
}