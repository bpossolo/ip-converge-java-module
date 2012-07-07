<?php

// IP.Converge requires that some of the data sent to it be an encoded PHP array object.
// Since this is not easy to do natively within Java.
// The ip-converge-java-module issues HTTP POST requests to this PHP file which does
// the encoding work. This file accepts a JSON message in the HTTP POST body.
// It decodes the JSON message into a PHP array, serializes the array,
// base-64 encodes it, then responds back to the ip-converge-java-module with the result.
// In order to use this file, you must place it on a webserver that is accessible to the
// application being integrated with IP.Converge.
// Within your Java application, the net.styleguise.converge.ConvergeServiceDelegate
// (which you are responsible for implementing) must specify the URL where this PHP
// file is located.
// Our recommendation is to place this file on the same machine as your IP.Converge installation.

$json = json_decode(file_get_contents('php://input'), true);
echo base64_encode(serialize($json));
?>