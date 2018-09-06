#!/usr/bin/groovy
package com.prologis.yardi

//Convert groovy map to list
@NonCPS
def mapToList(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        debugMessage "mapToList", entry2.value
        //dlist.add(new java.util.AbstractMap.SimpleImmutableEntry(entry2.key, entry2.value))
        dlist.add(entry2.value)
    }
    dlist
}

//Create file with content of groovy list
@NonCPS
def mapToFile(listFiles, fileName) {
    debugMessage "mapToFile - List of files", "${listFiles}"
    def listFilesString = ''
    def filePath
    for(def entry2 in listFiles){
        debugMessage "mapToFile - entry2", "${entry2}"
        filePath = "${entry2}".substring("${entry2}".indexOf('=') + 1, "${entry2}".length())
        debugMessage "mapToFile - File path:", "${filePath}"
        listFilesString += "${filePath}\n"
        debugMessage "mapToFile - List file string:", "${listFilesString}"
    }
    writeFile encoding: 'UTF-8', file: "${fileName}", text: "${listFilesString}"
}

// Map network drive
def mapDrive(config){
    withCredentials([usernamePassword(credentialsId: params.RdpUser, passwordVariable: 'RDPPASSWORD', usernameVariable: 'RDPUSERID')]) {
        echo "NET USE ${config.drive_letter} \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        bat returnStdout: true, script: "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
    }
}

// Unmap netwrok drive
def unmapDrive(){
    bat returnStdout: true, script: "NET USE * /del /y"
}