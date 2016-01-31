<?php

require_once("java/BirtEngine.inc");

$reportDocumentName = $_SERVER["PATH_INFO"];
$page = empty($_GET["page"])?1:($_GET["page"]<=0?1:$_GET["page"]);
$format = empty($_GET["format"])?"html":$_GET["format"];

try {
    $birtEngine = BirtEngine::getBirtEngine();

    $reportDocument = $birtEngine->openReportDocument(__DIR__."/temp/docs/$reportDocumentName.rptdocument");
    
    $outputStream = new ByteArrayOutputStream();
    
    $options = new RenderOption();
    $options->setOutputFormat($format);
    $options->setOutputStream($outputStream);
    
    if ($format == "html") {
        $htmlOptions = new HTMLRenderOption($options);
        $htmlOptions->setImageHandler(new HTMLServerImageHandler());
        $htmlOptions->setImageDirectory(__DIR__."/temp/img");
        $htmlOptions->setBaseImageURL("http://localhost/temp/img");
        $htmlOptions->setHtmlPagination(false);
        $htmlOptions->setHtmlRtLFlag(false);
        $htmlOptions->setEmbeddable(false);
    }
    
    $task = $birtEngine->createRenderTask($reportDocument);
    $task->setRenderOption($options);
    $task->setPageRange("$page-$page");
    $task->render();
    
    $reportDocument->close();
    $task->close();
    
    if ($format == "pdf") {
        header("Content-Type: application/pdf");
    }
    echo $outputStream->toByteArray();
} catch (JavaException $e) {
    echo "<pre>$e</pre>";
}