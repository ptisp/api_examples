<?php

require "RestRequest.inc.php";

$request = new RestRequest('https://api.ptisp.pt/domains/example.com/info', 'GET');
$request->setUsername("example@example.example");
$request->setPassword("asdafagasgsagasgasgdasgdasgasgdsgdag");
$request->execute();

echo '<pre>' . $request->getResponseBody() . '</pre>';
?>
