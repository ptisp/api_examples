<?php

require "RestRequest.inc.php";

$request = new RestRequest('https://api.ptisp.pt/cloud/1/status', 'GET');
$request->setUsername("pt0000");
$request->setPassword("asdafagasgsagasgasgdasgdasgasgdsgdag");
$request->execute();

echo '<pre>' . $request->getResponseBody() . '</pre>';
?>
