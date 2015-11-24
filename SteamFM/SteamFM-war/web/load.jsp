<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Loading</title>
        <style>
            * {padding: 0;margin: 0;border: 0;box-sizing: border-box;}
            html, body {height: 100%;}
            body {background-color: #222;overflow: hidden;}
            .loader {width: 200px;height: 200px;position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;border-radius: 50%;box-shadow: 0 0 5px #444;}
            .loader:before {content: "";position: absolute;width: 100%;height: 100%;border-radius: 50%;background: linear-gradient(#f05050, black 60%);animation: spin .5s infinite linear;}
            .loader:after {content: "";position: absolute;width: 95%;height: 95%;top: 2.5%;left: 2.5%;background-color: #222;border-radius: 50%;box-shadow: inset 0 0 5px #444;}
            @keyframes spin {to {transform: rotate(360deg);}}
        </style>
    </head>
    <body>
        <div class='loader'></div>
        <script>document.onload(window.location = "Controller?command=${param.command}");</script>
    </body>
</html>
