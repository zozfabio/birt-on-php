<?php

require_once("BirtEngine.inc");

$reportDesignName = $_SERVER["PATH_INFO"];

try {
    $birtEngine = BirtEngine::getBirtEngine();

    $reportDesign = $birtEngine->openReportDesign(__DIR__."$reportDesignName.rptdesign");

    $task = $birtEngine->createRunTask($reportDesign);
    $task->run(__DIR__."$reportDesignName.rptdocument");

    $task->close();

    echo "OK";
} catch (JavaException $e) {
    echo "<pre>$e</pre>";
}