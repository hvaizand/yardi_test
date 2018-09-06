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