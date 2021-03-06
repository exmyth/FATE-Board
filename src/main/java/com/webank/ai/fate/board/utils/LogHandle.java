package com.webank.ai.fate.board.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

public class LogHandle {

    public static List<Map> handleLog(List<Map> result) {
        ApplicationHome h = new ApplicationHome(LogHandle.class);
        File jarF = h.getSource();
        String boardPath = jarF.getParentFile().toString();
        int fateboard = boardPath.lastIndexOf("fateboard");
        String fatePath = boardPath.substring(0, fateboard);
        String tmpPath = "/tmp/";
        String ipRule="(127\\.0)|(10\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01])))|(192\\.168)";

        for (Map stringObjectMap : result) {
            Object o = stringObjectMap.get(Dict.LOG_CONTENT);
            String log = (String) o;
            String relativePathLog = log.replaceAll(fatePath, "./fate/");
            String tmpPathLog = relativePathLog.replaceAll(tmpPath, "./");

            String finalLog = tmpPathLog.replaceAll(ipRule, "xxx.xxx");
            stringObjectMap.put(Dict.LOG_CONTENT, finalLog);

        }
        return result;
    }
}
