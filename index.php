<?php

require "RestRequest.inc.php";

$request = new RestRequest('https://www.ptisp.pt/api/cloud/pg0000/1/status', 'GET');
$request->setUsername("pt0000");
$request->setPassword("asdafagasgsagasgasgdasgdasgasgdsgdag");
$request->execute();

echo '<pre>' . indent($request->getResponseBody()) . '</pre>';
?>
