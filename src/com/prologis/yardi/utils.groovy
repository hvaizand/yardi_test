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
    def message = ''
    withCredentials([usernamePassword(credentialsId: params.RdpUser, passwordVariable: 'RDPPASSWORD', usernameVariable: 'RDPUSERID')]) {
        echo "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        def mapStatus = bat returnStatus: true, script: "NET USE ${config.drive_letter}: \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        if(mapStatus!=0){
            currentBuild.result = 'UNSTABLE'
            message = "An issue occurred when trying to map the network drive pointing to the default path. Please update your RDP password in Jenkins"
        } else {
            debugMessage "mapDrive - Map network drive", mapStatus
            message = "Successful"
        }
    }
    return message
}

// Unmap netwrok drive
def unmapDrive(config){
    def message = ''
    def mapStatus = bat returnStatus: true, script: "if exist ${config.drive_letter}:\\ (NET USE ${config.drive_letter}: /del /y)"
    if(mapStatus!=0){
        currentBuild.result = 'UNSTABLE'
        message = "An issue occurred when trying to unmap the network drive pointing to the default path. Contact your admin"
    } else {
        debugMessage "unmapDrive - Unmap network drive", mapStatus
        message = "Successful"
    }
    return message
}


def loadPackage(fileList, dbo_credentials, db_server, db_name){
    def message = ''
    withCredentials([usernamePassword(credentialsId: dbo_credentials, passwordVariable: 'DBPASSWORD', usernameVariable: 'DBUSERNAME')]) {
            debugMessage "loadPackage - List of packages:", "${fileList}"
            createFile "pldpkgload.pkglist"
            mapToFile(fileList, "pldpkgload.pkglist")
            def loadStatus = bat returnStatus: true, script: "C:\\Utils\\pldpkgload.exe -U ${DBUSERNAME} -P ${DBPASSWORD} -S ${db_server} -d ${db_name} -r1 -b -f 65001 -i \"${WORKSPACE}\\pldpkgload.pkglist\""
            if(loadStatus!=0){
                currentBuild.result = 'FAILURE'
                message = "An issue occurred when trying to load packages. Please review the logs"
            } else {
                debugMessage "loadPackage - pldpkgload", loadStatus
                message = "Successful"
            }
    }
    return message
}