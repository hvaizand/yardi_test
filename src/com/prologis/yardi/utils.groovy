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
        def mapStatus = bat returnStatus: true, script: "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        if(mapStatus!=0){
            currentBuild.result = 'UNSTABLE'
            def message = "An issue occurred when trying to map the network drive pointing to the default path. Please update your RDP password in Jenkins"
        } else {
            debugMessage "mapDrive - Map network drive", mapStatus
            def message = "Successful"
        }
    }
    return message
}

// Unmap netwrok drive
def unmapDrive(){
    def mapStatus = bat returnStatus: true, script: "NET USE * /del /y"
    if(mapStatus!=0){
        currentBuild.result = 'UNSTABLE'
        def message = "An issue occurred when trying to unmap the network drive pointing to the default path. Contact your admin"
    } else {
        debugMessage "unmapDrive - Unmap network drive", mapStatus
            def message = "Successful"
    }
    return message
}