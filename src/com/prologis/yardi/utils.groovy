#!/usr/bin/groovy
package com.prologis.yardi

import com.cloudbees.groovy.cps.NonCPS
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException

//Check if mandatory parameter is set
@NonCPS
def getMandatoryParameter(Map map, paramName, defaultValue = null) {

    def paramValue = map[paramName]

    if (paramValue == null)
        paramValue = defaultValue

    if (paramValue == null)
        throw new Exception("ERROR - NO VALUE AVAILABLE FOR ${paramName}")
    return paramValue

}

//Convert groovy map to list
@NonCPS
def mapToList(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        debugMessage "utils.mapToList", entry2.value
        //dlist.add(new java.util.AbstractMap.SimpleImmutableEntry(entry2.key, entry2.value))
        dlist.add(entry2.value)
    }
    dlist
}

//Create file with content of groovy list
@NonCPS
def mapToFileUtils(listFiles, fileName) {
    debugMessage "utils.mapToFile - List of files", "${listFiles}"
    def listFilesString = ''
    def filePath
    for(def entry2 in listFiles){
        filePath = "${entry2}".substring("${entry2}".indexOf('=') + 1, "${entry2}".length())
        listFilesString += "${filePath}\n"
//        debugMessage "utils.mapToFile - entry2", "${entry2}"
//        debugMessage "utils.mapToFile - File path:", "${filePath}"
//        debugMessage "utils.mapToFile - List file string:", "${listFilesString}"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFilesString}"
}